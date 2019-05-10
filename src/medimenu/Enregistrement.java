package medimenu;

import java.io.*;
import java.util.ArrayList;

public class  Enregistrement {

    private boolean sauvegardeRegime;
    private boolean sauvegardeMenuUtilisateur;
    private boolean sauvegardeMenuNutritionniste;

    private String nomRegime;
    private String nomMenuUtilisateur;
    private String nomMenuNutritionniste;

    private String menuUtilisateur;
    private String menuNutritionniste;

     public Enregistrement() {
        this.sauvegardeRegime = false;
        this.sauvegardeMenuUtilisateur = false;
        this.sauvegardeMenuNutritionniste = false;
    }

    public void setMenuUtilisateur(String menu) {
        this.menuUtilisateur = menu;
    }


    public void setMenuNutritionniste(String menu) {
        this.menuNutritionniste = menu;
    }


    public String getMenuUtilisateur() {
        return this.menuUtilisateur;
    }

    public String getMenuNutritionniste() {
        return this.menuNutritionniste;
    }

    public void setNomRegime(String nom) {
        this.nomRegime = nom;
    }

    public void setNomMenuUtilisateur(String nom) {

        this.nomMenuUtilisateur = nom;
        System.out.println("NOM MENU: -" + this.nomMenuUtilisateur + "-and -" + nom + "-");
    }

    public void setNomMenuNutritionniste(String nom) {
        this.nomMenuNutritionniste = nom;
    }

    public String getNomMenuNutritionniste() {
        return this.nomMenuNutritionniste;
    }

    public String getNomRegime() {
        return this.nomRegime;
    }

    public String getNomMenuUtilisateur() {
        return this.nomMenuUtilisateur;
    }

    public void setSauvegardeRegime(boolean b) {
        this.sauvegardeRegime = b;
    }

    public void setSauvegardeMenuUtilisateur(boolean b) {
        this.sauvegardeMenuUtilisateur = b;
    }

    public void setSauvegardeMenuNutritionniste(boolean b) {
        this.sauvegardeMenuNutritionniste = b;
    }

    public boolean getSauvegardeRegime() {
        return (this.sauvegardeRegime);
    }

    public boolean getSauvegardeMenuUtilisateur() {
        return (this.sauvegardeMenuUtilisateur);
    }


    public boolean getSauvegardeMenuNutritionniste() {
        return (this.sauvegardeMenuUtilisateur);
    }

    public String sauvegarde(String contenu) {
        String resultat = "";
        if (this.sauvegardeRegime) {
            resultat += sauvegardeRegime(contenu);
        }
        if (this.sauvegardeMenuUtilisateur) {
            resultat += sauvegardeMenuUtilisateur();
        }

        if (this.sauvegardeMenuNutritionniste) {
            resultat += sauvegardeMenuNutritionniste();
        }
        return resultat;
    }

    /*Sauvegarde d'un régime */

    public String sauvegardeRegime(String contenu) {
        String nomFichier = "/medimenu/Regimes/" + this.nomRegime + ".txt";
        try {
            File f = new File(nomFichier);
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            PrintWriter writer = new PrintWriter(nomFichier, "UTF-8");
            writer.println(contenu);
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "<h2> Régime enregistré </h2>";
    }

    public String sauvegardeMenuUtilisateur() {
        String nomFichier = "/medimenu/Menus_Utilisateur/" + this.nomMenuUtilisateur + ".txt";
        try {
            File f = new File(nomFichier);
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            PrintWriter writer = new PrintWriter(nomFichier, "UTF-8");
            writer.print(this.menuUtilisateur);
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "<h2> Menu Utilisateur enregistré </h2>";
    }


    public String sauvegardeMenuNutritionniste(){
        String nomFichier="/medimenu/Menus_Nutritionniste/"+this.nomMenuNutritionniste+".txt";
        try {
            File f = new File(nomFichier);
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            PrintWriter writer = new PrintWriter(nomFichier, "UTF-8");
            writer.print(this.menuNutritionniste);
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "<h2> Menu Nutritionniste enregistré </h2>";
    }
    public static String afficherHTMLContenuRepertoire(String chemin) {
        String html = "";
        File repertoire = new File(chemin);
        String liste[] = repertoire.list();

        if (liste != null) {
            for (int i = 0; i < liste.length; i++) {
                html += " <div> <input type=\"radio\" id=\"configuration\" name=\"configuration\" value=\"" + liste[i] + "\" /><label for=\"" + liste[i] + "\">"+liste[i] +"</label></div>";
                System.out.println(liste[i]);
            }
        } else {
            System.err.println("Nom de repertoire invalide");
        }
        return html;
    }

    public static String contenuSauvegardeRegime(int nbMenus, int nbSlots, ArrayList<String> groupes, ArrayList<String> contraintesNutriments){
        String res="";
        res+=nbMenus+";"+nbSlots+";";
        for(String groupeCoche:groupes){
            res+=groupeCoche+",";
        }
        res+=";";
        for(String contrainte:contraintesNutriments){
            res+=contrainte+",";
        }
        res+=";";
        return res;
    }



}
