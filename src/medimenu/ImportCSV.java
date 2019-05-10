package medimenu;

import java.io.*;
import java.util.*;

public class ImportCSV {

    public static class CodeNomGroupe {
        String code;
        String nom;

        public CodeNomGroupe(String code, String nom) {
            this.code = code;
            this.nom = nom;
        }

        public String getCode() {
            return this.code;
        }

        public String getNom() {
            return this.nom;
        }

        public String toString() {
            return this.code + " " + this.nom;
        }

        public int hashCode() {
            return (int) nom.hashCode() * code.hashCode();
        }


        public boolean equals(Object o) {
            if (o instanceof CodeNomGroupe) {
                CodeNomGroupe toCompare = (CodeNomGroupe) o;
                return this.nom.equals(toCompare.nom) && this.code.equals((toCompare.code));
            }
            return false;
        }

    }

    // Importe les aliments du CSV de Ciqual

    public static void importCSV(int nbCol, Medimenu med) {
        String csvFile = "/medimenu/Ciqual.csv";
        String cvsSplitBy = ",";
        String[] ligne;
        int debut = 7;
        int tailleTab = nbCol - debut - 1;
        int nbLigne = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            //Lecture d'une nouvelle ligne
            while ((line = br.readLine()) != null) {
                ligne = line.split(cvsSplitBy);

                //Ne pas prendre en compte la première ligne (noms des colonnes)
                if (!ligne[0].equals("alim_grp_code")) {
                    String name = ligne[debut];
                    String[] groupes = new String[3];
                    float[] tabNutriments = new float[tailleTab];
                    for (int i = 0; i < tailleTab; i++) {
                        tabNutriments[i] = 0;
                    }

                    Float flottant;
                    for (int i = 9; i < nbCol; i++) {
                        try {
                            if (ligne[i] != null) {

                                groupes[0] = ligne[0];
                                groupes[1] = ligne[1];
                                groupes[2] = ligne[2];

                                if (ligne[i].equals("-") || ligne[i].equals("traces") || ligne[i].equals("")) {
                                    tabNutriments[i - 9] = 0;
                                } else if (ligne[i].contains("<")) {
                                    flottant = Float.parseFloat(ligne[i].substring(1, ligne[i].length()));
                                    tabNutriments[i - 9] = flottant;

                                } else {

                                    flottant = Float.parseFloat(ligne[i]);

                                    //System.out.println("FLottant: " + flottant);
                                    tabNutriments[i - 9] = flottant / 100;
                                }

                                //System.out.println(tab[i]);
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Unbound value");
                        }

                    }
                    med.addAliment(name, groupes, tabNutriments);
                }
                nbLigne++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //Construit la hashmap permettant de récupérer

    public static HashMap<Integer, String> Columns(int nbCol) {
        HashMap<Integer, String> hm = new HashMap<Integer, String>();
        String csvFile = "/medimenu/Ciqual.csv";
        String cvsSplitBy = ",";
        String[] nomsColonnes = new String[nbCol];

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            String line = br.readLine();

            // use comma as separator
            nomsColonnes = line.split(cvsSplitBy);


        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < nbCol; i++) {
            if (nomsColonnes[i] != null) {
                hm.put(i, nomsColonnes[i]);
                System.out.println(i + " " + nomsColonnes[i]);
            }
        }
        return hm;
    }

    /*
    Crée le code HTML ui va permettre d'afficher les noms de colonnes
     */
    public static String getColumnsHTML(int nbCol, Medimenu med,Preconfiguration preconfiguration) {
        String csvFile = "/medimenu/Ciqual.csv";
        String cvsSplitBy = ",";
        String[] nomsColonnes = new String[nbCol];
        String HTML = "";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line = br.readLine();

            // use comma as separator
            nomsColonnes = line.split(cvsSplitBy);


        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 9; i < nbCol; i++) {
            if (nomsColonnes[i] != null) {
                int id = med.addNutriment(nomsColonnes[i], "");

                String max = "10000";
                String min = "0";
                String strict = "1";
                String pourcent = "0";
                String checked="";

                /*
                System.out.println("CONTRAINTES");
                for (String s:preconfiguration.getContraintesNutriments().keySet()){
                    System.out.println(s);
                }*/

                if (preconfiguration.getContraintesNutriments().get(nomsColonnes[i]) !=null) {
                    String[] contrainte;
                    contrainte = preconfiguration.getContraintesNutriments().get(nomsColonnes[i]).split("_");
                    max = contrainte[0];
                    min = contrainte[1];
                    strict = contrainte[2];
                    pourcent = contrainte[3];
                    checked=" checked=\"checked\"";
                }

                HTML += "<fieldset>" +
                        "   <details>\n" +
                        "    <summary><b> <input type=\"checkbox\" id=\"" + id + "_on" + "\" name=\"" + id + "_on\"" + checked+"/> <label for=\"" + id + "_on" + "\">" + nomsColonnes[i] + "</label> </b></summary>\n" +
                        "        <p>La quantité de\n" +
                        nomsColonnes[i] + "\n" +
                        "        doit être superieur à\n" +
                        "        <input type=\"number\" id=\"" + id + "_min" + "\" name=\"" + id + "_min" + "\" min=\"0\" max=\"10000\" value=\"" + min + "\">\n" +
                        "        et inferieur à\n" +
                        "        <input type=\"number\" id=\"" + id + "_max" + "\" name=\"" + id + "_max" + "\" min=\"0\" max=\"10000\" value=\"" + max + "\">\n" +
                        "        . </br>\n" +
                        "        Cette limite doit être respecté strictement sur\n" +
                        "        <input type=\"number\" id=\"" + id + "_strict" + "\" name=\"" + id + "_strict" + "\" min=\"1\" max=\"10\" value=\"" + strict + "\">\n" +
                        "        jours et flexible à\n" +
                        "        <input type=\"number\" id=\"" + id + "_pourcent" + "\" name=\"" + id + "_pourcent" + "\" min=\"0\" max=\"100\" value=\"" + pourcent + "\">\n" +
                        "        pourcents sur un jour.</p>\n" +
                        "   </details>\n" +
                        "</fieldset>\n";
            }

        }

        return HTML;

    }


    //On crée pour une ligne la deuxième colonne
    //Qui est un tableau de checkbox
    public static String createHTMLSlots(int nbSlots, String name, Preconfiguration preconfiguration) {
        String name_ = name;
        String HTML =
                "<td><table>\n" +
                        " <tr> ";
        for (int k = 0; k < nbSlots; k++) {
            name_ += k;
            HTML += "<td> <input type=\"checkbox\" id=\"" + name_ + "\" name=\"" + name_ + "\"" ;


            if(preconfiguration.getGroupesCoches().contains(name_)) {
                HTML+="checked=\"checked\"";
            }
                  HTML+=  "></td>";
            name_ = name;
        }
        HTML += "</tr> \n" +
                " </table>\n </td>";

        return HTML;
    }

    /*Affiche sous-groupes
     */
    public static String groups(int nbLignes, int nbSlotsPetitDej, int nbSlotsDej, int nbSlotsGouter, int nbSlotsDiner, Medimenu medimenu, Preconfiguration preconfiguration) {
        String csvFile = "/medimenu/Ciqual.csv";
        String cvsSplitBy = ",";

        //HashMap permettant d'obtenir la hiérarchie Groupes, Sous-groupes et Sous-sous groupes
        HashMap<CodeNomGroupe, HashMap<CodeNomGroupe, ArrayList<CodeNomGroupe>>> groups = new HashMap<CodeNomGroupe, HashMap<CodeNomGroupe, ArrayList<CodeNomGroupe>>>();

        //C'est un tableau qui a pour première colonne groupe
        //La deuxième colonne contient un tableau de 4 colonnes sur le type de repas (petit-déjeuner, déjeuner;..)
        String HTML = "<table> \n " +
                "       <tr> \n" +
                "           <td> Groupe </td> \n" +
                "           ";
        //On vérifie à chaque fois que le nombre de slots n'est pas null, pour ne pas afficher le nom de la colonne
        if (nbSlotsPetitDej > 0) {
            HTML += "<td> <table> \n" +
                    "<tr> \n" +
                    "   <td> Petit-déjeuner </td> \n" +
                    "</tr>\n" +
                    "</table></td> \n";
        }
        if (nbSlotsDej > 0) {
            HTML += "<td> <table> \n" +
                    "<tr> \n" +
                    "<td> Déjeuner </td> \n" +
                    "</tr> \n" +
                    "</table> </td> \n";
        }
        if (nbSlotsGouter > 0) {
            HTML += "<td> <table> <tr><td> Gouter </td> </tr></table></td>";
        }
        if (nbSlotsDiner > 0) {
            HTML += "<td> <table> <tr><td> Diner </td> </tr></table></td>";
        }
        HTML += "</tr>";

        String[] lignes = new String[nbLignes];
        CodeNomGroupe grp;
        CodeNomGroupe ssGrp;
        CodeNomGroupe ssSsGrp;
        int i = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while (i < nbLignes) {
                String line = br.readLine();
                lignes = line.split(cvsSplitBy);

                //Codes
                grp = new CodeNomGroupe(lignes[0], lignes[3]);
                ssGrp = new CodeNomGroupe(lignes[1], lignes[4]);
                ssSsGrp = new CodeNomGroupe(lignes[2], lignes[5]);

                // Hashmap contenant la hiérarchie sous-groupes et sous-sous groupes
                HashMap<CodeNomGroupe, ArrayList<CodeNomGroupe>> ssGrps = new HashMap<CodeNomGroupe, ArrayList<CodeNomGroupe>>();

                //ArrayList contenant les sous-sous groupes
                ArrayList<CodeNomGroupe> ssSsGrps = new ArrayList<CodeNomGroupe>();


                if (!grp.getNom().equals("alim_grp_nom_fr")) {
                    //
                    if (groups.containsKey(grp)) {
                        ssGrps = groups.get(grp);
                        if (ssGrps.containsKey(ssGrp)) {
                            ssSsGrps = ssGrps.get(ssGrp);
                        }
                    }
                    // [Par exemple, il arrive qu'il n'y ait pas de sous-sous groupes.
                    //C'est important de le savoir car il faut afficher les checkbox pour les sous-groupes (au lieu des sous-sous groupes)]

                    if (ssSsGrp.getNom().equals("-")) {
                        //Sous-sous groupe inexistant
                        if (ssGrp.getNom().equals("-")) {
                            //Sous groupe inexistant
                            groups.put(grp, null);
                            //groups.addMulti(grp,null);
                        } else {
                            //Sous groupe existant
                            ssGrps.put(ssGrp, null);
                            groups.put(grp, ssGrps);
                        }
                    } else {
                        if (!ssSsGrps.contains(ssSsGrp)) {
                            ssSsGrps.add(ssSsGrp);
                            ssGrps.put(ssGrp, ssSsGrps);
                        }
                    }
                }
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //On récupère les noms des groupes pour les affiches dans la première colonne
        Set<CodeNomGroupe> groupes = groups.keySet();
        for (CodeNomGroupe g : groupes) {
            HTML += "<tr>\n" +
                    "<td>" + g.getNom() + "</td>\n" +
                    " </tr> \n";
            //De meme pour affiches les sous-groupes
            HashMap<CodeNomGroupe, ArrayList<CodeNomGroupe>> sousGroupes = groups.get(g);
            Set<CodeNomGroupe> sousGrps = sousGroupes.keySet();
            for (CodeNomGroupe sg : sousGrps) {
                //Et pour les sous-sous groupes
                ArrayList<CodeNomGroupe> sousSsGroupes = sousGroupes.get(sg);
                HTML += "<tr>\n <td> --" + sg.getNom() + "</td>\n ";

                //Cas où il n'y a pas de sous-sous groupes
                if (sousSsGroupes == null) {
                    //Petit-dej
                    HTML += createHTMLSlots(nbSlotsPetitDej, sg.getCode() + "_0_" ,preconfiguration);
                    //Dej
                    HTML += createHTMLSlots(nbSlotsDej, sg.getCode() + "_1_", preconfiguration);
                    //Gouter
                    HTML += createHTMLSlots(nbSlotsGouter, sg.getCode() + "_2_", preconfiguration);
                    //Diner
                    HTML += createHTMLSlots(nbSlotsDiner, sg.getCode() + "_3_", preconfiguration);
                    HTML += "</tr>";

                    // J'ajoute l'information dans Medimenu
                    medimenu.addGroup(sg.getNom(), sg.getCode());
                } else {
                    HTML += "</tr>";
                    for (CodeNomGroupe ssg : sousSsGroupes) {
                        HTML += "<tr>\n <td>----" + ssg.getNom() + "</td>\n ";
                        //Petit-dej
                        HTML += createHTMLSlots(nbSlotsPetitDej, ssg.getCode() + "_0_", preconfiguration);
                        //Dej
                        HTML += createHTMLSlots(nbSlotsDej, ssg.getCode() + "_1_", preconfiguration);
                        //Gouter
                        HTML += createHTMLSlots(nbSlotsGouter, ssg.getCode() + "_2_", preconfiguration);
                        //Diner
                        HTML += createHTMLSlots(nbSlotsDiner, ssg.getCode() + "_3_", preconfiguration);

                        // J'ajoute l'information dans Medimenu
                        medimenu.addGroup(ssg.getNom(), ssg.getCode());
                    }
                }
            }
        }
        HTML += "</table>";
        return HTML;
    }
}

