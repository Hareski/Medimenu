package medimenu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Preconfiguration {
    String nbJours;
    String nbSlots;
    ArrayList<String> groupesCoches;
    HashMap<String,String> contraintesNutriments;

    public Preconfiguration(String nbJ, String nbS){
         nbJours=nbJ;
         nbSlots=nbS;
         groupesCoches= new  ArrayList<String>();
         contraintesNutriments= new   HashMap<String,String>() ;
    }

    public String getNbJours() {
        return nbJours;
    }

    public String getNbSlots(){
        return nbSlots;
    }

    public ArrayList<String> getGroupesCoches(){
        return groupesCoches;
    }

    public  HashMap<String,String>  getContraintesNutriments(){
        return contraintesNutriments;
    }

    public void setPreconfigurationFromAFile(String chemin) {
        String[] ligne;

        try (BufferedReader br = new BufferedReader(new FileReader(chemin))) {
            String line;
            String[] groupes;
            String[] nutriments;
            String[] nutrim_contrainte;
            //Lecture d'une nouvelle ligne
            while((line = br.readLine()) != null) {
                ligne = line.split(";");
                nbJours=ligne[0];
                nbSlots=ligne[1];

                groupes=ligne[2].split(",");
                for (int i=0;i<groupes.length;i++) {
                    if (groupes[i] != null) {
                        groupesCoches.add(groupes[i]);
                    }
                }

                nutriments=ligne[3].split(",");


                for(int i=0;i<nutriments.length;i++) {
                    if (nutriments[i] != null) {
                        nutrim_contrainte=nutriments[i].split("__");
                        contraintesNutriments.put(nutrim_contrainte[0],nutrim_contrainte[1]);
                    }
                }

               
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
