package medimenu;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Medimenu {

    // =========================================================================================
    // Définition des parametres
    // =========================================================================================
    // Configuration
    Model model;
    int nombreDeSlot;
    int nombreAliments = 0; // Valeur initiale avant ajout
    int nombreDeNutriments = 0; // Valeur initiale avant ajout
    int nombreDeMenu = 3000;
    int timeout = 0;
    int coefApproximation = 100; // Plus cette valeur est grande, plus l'approximation en entier est negligable
    ArrayList<String> aliments;
    ArrayList<int[]> alimentsNutriments;
    ArrayList<String> nutriments;

    // Affichage et log
    String output = "      <details><summary>Plus de détails sur la résolution...</summary>\n" +
            "          <div class=\"code\">";
    String imprimable="";
    String imprimableNutritioniste="";

    // Groupes
    HashMap<String, Groupe> groups;
    private class Groupe {
        String name;
        ArrayList<Integer> aliments;

        public Groupe(String name) {
            this.name = name;
            this.aliments = new ArrayList<>();
        }
    }
    int nombreDeGroupes = 0;

    // Constructeur
    public Medimenu() {
        this.model = new Model();
        nutriments = new ArrayList<>();
    }
    // =========================================================================================

    ArrayList<String> groupesCoches=new ArrayList<String>();
    ArrayList <String> contraintesNutriments=new ArrayList<String>();

    // Get et set
    public ArrayList<String> getGroupesCoches(){
        return this.groupesCoches;
    }
    public ArrayList<String> getContraintesNutriments(){
        return this.contraintesNutriments;
    }
    private Enregistrement enregistrement=new Enregistrement();
    public void setMenuUtilisateur(String menu){
        this.enregistrement.setMenuUtilisateur(menu);
    }
    public String getMenuUtilisateur(){
        return this.enregistrement.getMenuUtilisateur();
    }
    public void setNomRegime(String nom){
        this.enregistrement.setNomRegime(nom);
    }
    public void setNomMenuUtilisateur(String nom){
        this.enregistrement.setNomMenuUtilisateur(nom);
    }
    public void setNomMenuNutritionniste(String nom){
        this.enregistrement.setNomMenuNutritionniste(nom);
    }
    public String getNomMenuNutritionniste(){
        return this.enregistrement.getNomMenuNutritionniste();
    }
    public void setMenuNutritionniste(String menu){
        this.enregistrement.setMenuNutritionniste(menu);
    }
    public String getMenuNutritionniste(){
        return this.enregistrement.getMenuNutritionniste();
    }
    public String getNomRegime(){
        return this.enregistrement.getNomRegime();
    }
    public String getNomMenuUtilisateur(){
        return this.enregistrement.getNomMenuUtilisateur();
    }
    public void setSauvegardeRegime(boolean b){
        this.enregistrement.setSauvegardeRegime(b);
    }
    public void setSauvegardeMenuUtilisateur(boolean b){
        this.enregistrement.setSauvegardeMenuUtilisateur(b);
    }
    public void setSauvegardeMenuNutritionniste(boolean b){
        this.enregistrement.setSauvegardeMenuNutritionniste(b);
    }
    public boolean getSauvegardeRegime(){
        return(this.enregistrement.getSauvegardeRegime());
    }
    public  boolean getSauvegardeMenuUtilisateur(){
        return(this.enregistrement.getSauvegardeMenuUtilisateur());
    }
    public  boolean getSauvegardeMenuNutritionniste(){
        return(this.enregistrement.getSauvegardeMenuNutritionniste());
    }
    public String sauvegarde(String contenu){
        return(this.enregistrement.sauvegarde(contenu));
    }
    public String getImprimableNutritioniste(){return imprimableNutritioniste;}


    // =========================================================================================
    // Etape 1 - Configuration generale
    // =========================================================================================
    public void setNbJours(int nb){
        this.nombreDeJour=nb;
    }
    public void setNbSlots(int nb){
        this.nombreDeSlot =nb;
    }
    public void setTimeout(int nb){
        this.timeout=nb;
    }
    public int getNbSlots(){return this.nombreDeSlot;}
    // =========================================================================================


    /**
     * @param nom
     * @param idCategorie
     * @return Id du nutriment ou -1 si erreur
     */
    // =========================================================================================
    // Etape 2 - Configuration des nutriments
    // =========================================================================================
    int addNutriment(String nom, String idCategorie){
        if((nutriments != null) && (aliments == null)){
            nombreDeNutriments++;
            nutriments.add(nom);
            output += "addNutriment(): " + nom + " SUCCESS with id "+(nombreDeNutriments-1)+".<br/>";
            return nombreDeNutriments-1;
        }
        output += "addNutriment(): " + nom + " ERROR.<br/>";
        return -1;
    }
    // =========================================================================================


    /**
     * @param name
     * @param id
     * @return Le nombre de groupe total
     */
    // ========================================================================================f=
    // Etape 3 - Configuration des groupes
    // =========================================================================================
    int addGroup(String name, String id){
        if (groups == null){
            groups = new HashMap<>();
            output += "addGroup(): CREATION.<br/>";
        }
        nombreDeGroupes++;
        groups.put(id, new Groupe(name));
        output += "addGroup(): " + name + " SUCCESS with id "+id+".<br/>";
        return nombreDeGroupes;
    }
    // =========================================================================================


    /**
     * @param name
     * @param groupsAliment
     * @param values
     * @return L'id de l'aliment
     */
    // =========================================================================================
    // Etape 4 - Configuration des aliments
    // =========================================================================================
    int addAliment(String name, String[] groupsAliment, float[] values){
        // Initialisation
        if (aliments == null) {
            aliments = new ArrayList<>();
        }
        if(alimentsNutriments == null){
            alimentsNutriments=new ArrayList<>();
        }

        aliments.add(name);
        nombreAliments++; // Devient l'ID de l'aliment ajouté

        // Je convertis les valeurs en INT * approximation
        int[] valuesInt = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            valuesInt[i] = Math.round(values[i]*coefApproximation);
        }
        alimentsNutriments.add(valuesInt);

        for (String idGroupe:groupsAliment) {
            if (groups.get(idGroupe) != null){
                groups.get(idGroupe).aliments.add(nombreAliments);
            }
        }
        return nombreAliments;
    }
    // =========================================================================================



    // =========================================================================================
    // Etape 5 - Configuration des variables du modeles
    // =========================================================================================


    // --------------------------------------------------------------------------
    // Variables
    int nombreDeJour = 3;
    int quantiteMinParAliment = 50;
    int quantiteMaxParAliment = 500;
    int quantiteMaxParNutriment = IntVar.MAX_INT_BOUND;
    int nombreDeRepasParJour = 4;

    // Choix de l'aliment par son id
    IntVar[][][] A = new IntVar[nombreDeRepasParJour][][];
    // Choix du poids
    IntVar[][][] W = new IntVar[nombreDeRepasParJour][][];
    // Quantite de nutriment de Ciqual sans prise en compte du poids
    // Pour un nutriment, pour un repas, pour un jour, pour un slot
    IntVar[][][][] QuantiteNutrimentRelative;
    // Quantite de nutriment pour l'aliment dans A selon W
    IntVar[][][][] QuantiteNutrimentPondere;
    // Quantite de nutriment par j, par nutriments, par slot
    // correspond a un changement de variables pour QuantiteNutrimentPondere;
    IntVar[][][] QuantiteNutrimentParJour;
    // Applanissement totales des variables pour les occurences
    IntVar[] AllVarsA;
    IntVar[] Occurences;

    // --------------------------------------------------------------------------


    /**
     * @param req Requéte
     * @param k numero de repas
     * @param s numero de slot
     * @return Pour un repas k et un slot s, liste de tous les elements possible
     */
    private int[] getDomaine(HttpServletRequest req, int k, int s) {
        // Je remplis l'ArrayList
        ArrayList<Integer> domaine = new ArrayList<>();
        for (String g:groups.keySet()) {
            if(req.getParameter(g+"_"+k+"_"+s)!=null){
                groupesCoches.add(g+"_"+k+"_"+s);
                domaine.addAll(groups.get(g).aliments);
            }
        }
        // Je convertis en int[]
        int[] out = new int[domaine.size()];
        for (int i = 0; i < domaine.size(); i++) {
            out[i] = domaine.get(i).intValue();
        }
        if (domaine.size()>0){
            return out;
        }
        else{
            return new int[]{0};
        }
    }

    /**
     * @param req
     * @return 1 sauf erreur
     */
    public int initialisationVariablesChoco(HttpServletRequest req){
        // ---------------------------------------------------------------------------------------
        // --------------------------------------- Données ---------------------------------------

        // Quantite de nutriment de Ciqual sans prise en compte du poids
        // Pour un nutriment, pour un repas, pour un jour, pour un slot
        QuantiteNutrimentRelative = new IntVar[nombreDeNutriments][][][];
        // Quantite de nutriment pour l'aliment dans A selon W
        QuantiteNutrimentPondere = new IntVar[nombreDeNutriments][][][];

        // Données nutriments
        int[][] quantiteRelative = generateTableNutriments(); //aliment,nutriment

        // ---------------------------------------------------------------------------------------
        // -------------------------------- Variables de décision --------------------------------
        // Variables donnant l'id de l'aliment limité par son domaine dans la base de données
        for (int k = 0; k < nombreDeRepasParJour; k++) {
            A[k]= new IntVar[nombreDeSlot][];
            for (int s = 0; s < nombreDeSlot; s++) {
                A[k][s] = model.intVarArray("Aliment "+k+s, nombreDeJour, getDomaine(req, k, s));
            }
        }

        // Variables donnant le poids des aliments
        for (int k = 0; k < nombreDeRepasParJour; k++) {
            W[k]= new IntVar[nombreDeSlot][];
            for (int s = 0; s < nombreDeSlot; s++) {
                W[k][s] = model.intVarArray("Poids "+k+s, nombreDeJour, quantiteMinParAliment, quantiteMaxParAliment);
            }
        }

        // --------------------------quantiteMaxParAliment-------------------------------------------------------------
        // -------------------------------- Variables de calculs ---------------------------------
        // Quantité relative
        for (int n = 0; n < nombreDeNutriments; n++) {
            QuantiteNutrimentRelative[n] = new IntVar[nombreDeRepasParJour][][];
            for (int k = 0; k < nombreDeRepasParJour; k++) {
                QuantiteNutrimentRelative[n][k] = model.intVarMatrix(nombreDeSlot, nombreDeJour, 0, quantiteMaxParNutriment);
            }
        }
        // Contrainte de la variable
        for (int n = 0; n < nombreDeNutriments; n++) {
            for (int k = 0; k < nombreDeRepasParJour; k++) {
                for (int j = 0; j < nombreDeJour; j++) {
                    for (int s = 0; s < nombreDeSlot; s++) {
                        if (quantiteRelative != null) {
                            model.element(QuantiteNutrimentRelative[n][k][s][j],quantiteRelative[n],A[k][s][j]).post();
                        }
                    }
                }
            }
        }

        // Quantité pondéré
        for (int n = 0; n < nombreDeNutriments; n++) {
            QuantiteNutrimentPondere[n] = new IntVar[nombreDeRepasParJour][][];
            for (int k = 0; k < nombreDeRepasParJour; k++) {
                QuantiteNutrimentPondere[n][k] = model.intVarMatrix(nombreDeSlot, nombreDeJour, 0, quantiteMaxParNutriment);
            }
        }

        // Contrainte de la variable
        for (int n = 0; n < nombreDeNutriments; n++) {
            for (int k = 0; k < nombreDeRepasParJour; k++) {
                for (int j = 0; j < nombreDeJour; j++) {
                    for (int s = 0; s < nombreDeSlot; s++) {
                        model.times(QuantiteNutrimentRelative[n][k][s][j],W[k][s][j],QuantiteNutrimentPondere[n][k][s][j]).post();
                    }
                }
            }
        }

        // J'applatis la derniere variables pour faire une somme sur les jours
        QuantiteNutrimentParJour = new IntVar[nombreDeNutriments][][];
        for (int n = 0; n < nombreDeNutriments; n++) {
            QuantiteNutrimentParJour[n] = new IntVar[nombreDeJour][];
            for (int j = 0; j < nombreDeJour; j++) {
                QuantiteNutrimentParJour[n][j] = new IntVar[nombreDeRepasParJour*nombreDeSlot];
                for (int k = 0; k < nombreDeRepasParJour; k++) {
                    for (int s = 0; s < nombreDeSlot; s++) {
                        QuantiteNutrimentParJour[n][j][k*nombreDeSlot+s] = QuantiteNutrimentPondere[n][k][s][j];
                    }
                }
            }
        }

        // J'applatis la derniere variables pour faire une somme sur les jours et pour AllVarsA
        AllVarsA = new IntVar[nombreDeJour*nombreDeRepasParJour*nombreDeSlot];
        int pivot = 0;
        for (int j = 0; j < nombreDeJour; j++) {
            for (int k = 0; k < nombreDeRepasParJour; k++) {
                for (int s = 0; s < nombreDeSlot; s++) {
                    AllVarsA[pivot] = A[k][s][j];
                    pivot++;
                }
            }
        }

        return 1;
    }

    // --------------------------------------------------------------------------
    // =========================================================================================


    /**
     * @param req Requéte contenant les contraintes
     * @return true si correct
     */
    // =========================================================================================
    // Etape 6 - Configuration des contraintes
    // =========================================================================================
    public boolean readConstraints(HttpServletRequest req){
        for (int n = 0; n < nombreDeNutriments; n++) {
            if (req.getParameter(n+"_on")!=null) {
                int valueMin = Integer.parseInt(req.getParameter(n + "_min"));
                int valueMax = Integer.parseInt(req.getParameter(n + "_max"));
                int nombreDeJourDeRespectStrict = Integer.parseInt(req.getParameter(n + "_strict"));
                int pourcentRespect = Integer.parseInt(req.getParameter(n + "_pourcent"));
                //this.contraintesNutriments.add(nutriments.get(n)+"_max_"+valueMax+"_min_"+valueMin+"_strict_"+nombreDeJourDeRespectStrict+"_pourcent_"+pourcentRespect);
                this.contraintesNutriments.add(nutriments.get(n)+"__"+valueMax+"_"+valueMin+"_"+nombreDeJourDeRespectStrict+"_"+pourcentRespect); //TODO WARNING__
                createConstraint(n, valueMin, valueMax, nombreDeJourDeRespectStrict, pourcentRespect);
            }
        }
        return true;
    }

    /**
     * @param indexNutriment
     * @param valueMin
     * @param valueMax
     * @param nombreDeJourDeRespectStrict
     * @param pourcentRespect
     */
    void createConstraint(int indexNutriment, int valueMin, int valueMax, int nombreDeJourDeRespectStrict, int pourcentRespect){
        output += "CONTRAINTE " + " , index " + indexNutriment + " , " + valueMin + " au min, " + valueMax + " au max , " + nombreDeJourDeRespectStrict + " jours , " + pourcentRespect + " pourcents <br/>";

        // -------------------------------------------------------------
        // Contraintes flexible
        for (int j = 0; j < nombreDeJour; j++) {
            model.sum(QuantiteNutrimentParJour[indexNutriment][j], ">=", valueMin*coefApproximation - Math.round(valueMin*(((float) (pourcentRespect))/100)*coefApproximation)).post();
            model.sum(QuantiteNutrimentParJour[indexNutriment][j], "<=", Math.round(valueMax*(((float) (100+pourcentRespect))/100)*coefApproximation)).post();
        }
        // -------------------------------------------------------------

        // -------------------------------------------------------------
        // Contrainte stricts sur plusieurs jours
        for (int j = 0; j < nombreDeJour-nombreDeJourDeRespectStrict; j++) {
            IntVar[] arrayForXdays = new IntVar[nombreDeJourDeRespectStrict*nombreDeSlot];
            for (int jstrict = 0; jstrict < nombreDeJourDeRespectStrict; jstrict++) {
                for (int s = 0; s < nombreDeSlot; s++) {
                    arrayForXdays[(jstrict*nombreDeSlot)+s] = QuantiteNutrimentParJour[indexNutriment][j+jstrict][s];
                }
            }
             model.sum(arrayForXdays, ">=", valueMin*coefApproximation * nombreDeJourDeRespectStrict);
             model.sum(arrayForXdays, "<=", valueMax*coefApproximation * nombreDeJourDeRespectStrict);
        }
        // -------------------------------------------------------------

    }
    // =========================================================================================


    /**
     * @return Tableau de nutriment adapté pour ChocoSolver
     */
    private int[][] generateTableNutriments() {
        // On doit retourner le tableau pour que les donnees soit coherente
        int[][] output = new int[nombreDeNutriments][nombreAliments];
        for (int a = 0; a < nombreAliments; a++) {
            for (int n = 0; n < nombreDeNutriments; n++) {
                output[n][a] = alimentsNutriments.get(a)[n];
            }
        }
        return output;
    }


    // --------------------------------------------------------------------------
    // Variables
    IntVar occMax;
    IntVar occMaxPondere; // Pour donner plus d'importance au cardinal
    IntVar plusGrandPoids;
    IntVar objective;
    // --------------------------------------------------------------------------

    /**
     * Fixe la fonction objective
     */
    private void setObjectif() {

        int[] values = new int[nombreAliments];
        for (int i = 0; i < nombreAliments; i++) {
            values[i] = i;
        }
        Occurences = model.intVarArray("Occurences", nombreAliments, 0, quantiteMaxParAliment);

        model.globalCardinality(AllVarsA,values,Occurences,true).post();

        occMax = model.intVar("OccMax",0,quantiteMaxParAliment);
        for (int i = 0; i < nombreAliments; i++) {
            model.arithm(Occurences[i], "<=", occMax).post();
        }

        plusGrandPoids = model.intVar("plusGrandPoids",0,quantiteMaxParAliment);
        // Contrainte de la variable
        for (int k = 0; k < nombreDeRepasParJour; k++) {
            for (int j = 0; j < nombreDeJour; j++) {
                for (int s = 0; s < nombreDeSlot; s++) {
                    model.arithm(W[k][s][j], "<=", plusGrandPoids).post();
                }
            }
        }

        occMaxPondere = model.intVar("occMaxPondere",0,quantiteMaxParAliment);
        model.times(occMax,2,occMaxPondere).post();

        objective = model.intVar("objective",0,quantiteMaxParAliment);
        model.sum(new IntVar[]{plusGrandPoids, occMaxPondere}, "<", objective).post();
        model.setObjective(false,objective); // On minimise
    }

    /**
     * @param req
     * @return La sortie contient l'ensemble de l'affichage
     */
    // =========================================================================================
    // Etape 7 - Calcul final
    // =========================================================================================
    public String getResultat(HttpServletRequest req) {
        setObjectif();

        // ----------------------------------------------------------------------------
        // Recherche et sortie de la solution
        int nombreTrouve = 0;
        Solver solver = model.getSolver();
        output += "           </div>\n";
        solver.limitTime(timeout*1000); // Le temps est limité en ms
        while (solver.solve() && nombreTrouve < nombreDeMenu){
            nombreTrouve ++;
        }
        output += "</details>";
        if(solver.hasEndedUnexpectedly()||nombreTrouve<=0){
            output += "Le solveur ne peut trouver de solution, vérifiez les contraintes.<br/>";
            output += solver.getContradictionException();
            imprimable+="Le solveur ne peut trouver de solution, vérifiez les contraintes.";
            imprimableNutritioniste+="Le solveur ne peut trouver de solution, vérifiez les contraintes.";
        }
        else{
            // Affichage du menu
            printMenu();
            menuImprimable();
            menuImprimableNutritioniste();
            enregistrement.setMenuUtilisateur(imprimable);
            enregistrement.setMenuNutritionniste(imprimableNutritioniste);


        }
        return output; // Retour pour affichage
    }

    /**
     * @return Date du jour
     */
    private String today() {
        final Date date = new Date();
        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }

    private void menuImprimable(){
        imprimable+="Menu généré le "+today()+"\n \n";
        for (int j = 0; j < nombreDeJour; j++) {
            imprimable += "Jour "+(j+1)+"\n";
            for (int k = 0; k < nombreDeRepasParJour; k++) {
                switch (k){
                    case 0: imprimable += "   * petit déjeuner \n"; break;
                    case 1: imprimable += "   * déjeuner \n"; break;
                    case 2: imprimable += "   * gouter \n"; break;
                    case 3: imprimable += "   * diner \n"; break;
                }
                for (int s = 0; s < nombreDeSlot; s++) {
                    imprimable += "        -"+(W[k][s][j].getValue())+" grammes de "+getAlimentName(A[k][s][j].getValue())+"\n";
                }
            }
        }

    }

    /**
     * Retour pour impression nutritioniste
     */
    private void menuImprimableNutritioniste(){
        imprimableNutritioniste+="Menu généré le "+today()+"\n \n";
        int[] totalNutriments = new int[nombreDeNutriments];
        for (int n: totalNutriments) {n = 0;}
        for (int j = 0; j < nombreDeJour; j++) {
            imprimableNutritioniste += "Jour "+(j+1)+"\n";
            for (int k = 0; k < nombreDeRepasParJour; k++) {
                switch (k){
                    case 0: imprimableNutritioniste += "   * petit déjeuner \n"; break;
                    case 1: imprimableNutritioniste += "   * déjeuner \n"; break;
                    case 2: imprimableNutritioniste += "   * gouter \n"; break;
                    case 3: imprimableNutritioniste += "   * diner \n"; break;
                }
                for (int s = 0; s < nombreDeSlot; s++) {
                    imprimableNutritioniste += "        - "+(W[k][s][j].getValue())+" grammes de "+getAlimentName(A[k][s][j].getValue())+"\n";
                    for (int n = 0; n < nombreDeNutriments; n++) {
                        imprimableNutritioniste += "             -> "+(QuantiteNutrimentPondere[n][k][s][j].getValue()/coefApproximation)+" "+nutriments.get(n)+"\n";
                        totalNutriments[n] += QuantiteNutrimentPondere[n][k][s][j].getValue()/coefApproximation;
                    }
                }
            }
        }
        for (int n = 0; n < nombreDeNutriments; n++) {
            imprimableNutritioniste+="\n \n Total des nutriments sur la semaine :\n \n";
            imprimableNutritioniste += "  -> "+totalNutriments[n]+" "+nutriments.get(n)+"\n";
        }

    }

    /**
     * Affichage menu standard
     */
    private void printMenu() {
        output += "<div class=\"menu\">";
        for (int j = 0; j < nombreDeJour; j++) {
            output += "<h1>Jour "+(j+1)+"</h1>";
            for (int k = 0; k < nombreDeRepasParJour; k++) {
                output += "<h2>Pour le ";
                switch (k){
                    case 0: output += "petit déjeuner"; break;
                    case 1: output += "déjeuner"; break;
                    case 2: output += "gouter"; break;
                    case 3: output += "diner"; break;
                }
                output += "</h2><ul>";
                for (int s = 0; s < nombreDeSlot; s++) {
                    output += "<li>"+(W[k][s][j].getValue())+" grammes de "+getAlimentName(A[k][s][j].getValue())+"</li>";
                }
                output += "</ul>";
            }
        }
        output += "</div>";
    }

    /**
     * @param id
     * @return Nom de l'aliment id
     */
    private String getAlimentName(int id) {
        return aliments.get(id);
    }
    // =========================================================================================

}
