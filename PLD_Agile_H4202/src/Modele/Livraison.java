/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;
import java.util.*;
import java.sql.Time;
/**
 *
 * @author carhiliuc
 */
public class Livraison {
    private Intersection adresse;
    private int duree;
    private Time debutPlage;
    private Time finPlage;

   
    public Livraison(Intersection adresse, int duree, String debutPlage, String finPlage) {
        this.adresse = adresse;
        this.duree = duree;
        //String dPlage = goodTimeForm(debutPlage);
        //String fPlage = goodTimeForm(finPlage);
        this.debutPlage = Time.valueOf(debutPlage);
        this.finPlage = Time.valueOf(finPlage);
    }

    public Livraison(Intersection adresse, int duree) {
        this.adresse = adresse;
        this.duree = duree;
    }

    public Livraison(Intersection adresse, int duree, Time debutPlage, Time finPlage) {
        this.adresse = adresse;
        this.duree = duree;
        this.debutPlage = debutPlage;
        this.finPlage = finPlage;
    }
        
    
     private String goodTimeForm(String time){
        String newTime = time;
        int prec = -1;    
        for (int i = 0; i < newTime.length(); i++){
            if (newTime.charAt(i) == ':' && ((i - prec) < 3)){
                    prec = i;
                    newTime = newTime.substring(0, i-2) + 0 + newTime.substring(i-2, newTime.length());
            }
        }
        return newTime;
    }

    public Intersection getAdresse() {
        return adresse;
    }

    public int getDuree() {
        return duree;
    }

    public Time getDebutPlage() {
        return debutPlage;
    }

    public Time getFinPlage() {
        return finPlage;
    }

    public void setAdresse(Intersection adresse) {
        this.adresse = adresse;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setDebutPlage(Time debutPlage) {
        this.debutPlage = debutPlage;
    }

    public void setFinPlage(Time finPlage) {
        this.finPlage = finPlage;
    }

    @Override
    public String toString() {
        return "Livraison{" + "adresse=" + adresse.getTroncons().get(0).getNomRue() + ", duree=" + duree + ", debutPlage=" + debutPlage + ", finPlage=" + finPlage + '}';
    }
    
}
