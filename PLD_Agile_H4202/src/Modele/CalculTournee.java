/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.*;
 

/**
 *
 * @author carhiliuc
 */
public class CalculTournee {
    private List<Livraison> livraisons;
    private List<Intersection> intersections;
    private Intersection entrepot;
    private int[][] pred;

    public CalculTournee(List<Livraison> livraisons, List<Intersection> intersections, Intersection entrepot) {
        this.livraisons = livraisons;
        this.intersections = intersections;
        this.entrepot = entrepot;
        this.pred = new int[livraisons.size()+1][intersections.size()];
    }

         
    
    private int[] calculDuree(Intersection depart, Intersection[] intersectionLivraison, int indexDepart){
        
        Intersection[] gris = new Intersection[intersections.size()];
        int nbGris = 0;
        
        //Initialisation de graph (distances et couleurs)
        for (int i = 0; i < intersections.size(); i++){
            if (intersections.get(i) == depart){
                depart.setD(0);
                gris[0] = depart;
                nbGris++;
                depart.setCouleur(1);
            }
            else {
                intersections.get(i).setD(Integer.MAX_VALUE);
                intersections.get(i).setCouleur(0);
            } 
        }   
        
        //Calcule de plus court chemin
        while (nbGris != 0){
            // Determination de minimum gris
            int minDuree = Integer.MAX_VALUE;
            Intersection minValeur = depart;
            int posArray = 0;
            for (int i = 0; i < nbGris; i++){
                if(gris[i].getD() < minDuree){
                    minDuree = gris[i].getD();
                    minValeur = gris[i];
                    posArray = i;
                }
            }
           List<Intersection> nouveauGris = minValeur.relacherSucc();
           gris[posArray] = gris[nbGris-1];
           nbGris--;
           for (Intersection intersection: nouveauGris){
               gris[nbGris] = intersection;
               nbGris++;
           }           
            
        }
        
        int[] d = new int[intersectionLivraison.length];
        for (int i = 0; i < intersectionLivraison.length; i++){
            d[i] = intersectionLivraison[i].getD();            
        } 
        
        for (int i = 0; i < intersections.size(); i++){
            pred[indexDepart][i] = intersections.get(i).getPredIndex();
        }
        
        return d;
    }
    
    public int[][] graphLivraison(){
       Intersection[] intersectionsLivraisons = new Intersection[livraisons.size()+1];
       int[][] matriceLivraison = new int[intersectionsLivraisons.length][intersectionsLivraisons.length];
       
      
       for (int i = 0; i < (intersectionsLivraisons.length-1); i++){
           intersectionsLivraisons[i] = livraisons.get(i).getAdresse();
       }
       
       intersectionsLivraisons[intersectionsLivraisons.length-1] = entrepot;
       
       for (int i = 0; i < intersectionsLivraisons.length; i++){
           int[] durees = calculDuree(intersectionsLivraisons[i], intersectionsLivraisons, i) ; 
           for (int j = 0; j < intersectionsLivraisons.length; j++){
               matriceLivraison[i][j] = durees[j];
           }
       }
       
       return matriceLivraison;
    } 
    
    public List<Intersection> getChemin(int depart, int arrive){
        
        if ((depart > 0) && (depart < livraisons.size()+1) && (arrive > 0) && (arrive < livraisons.size()+1)){
            Intersection intersectionDepart;
            Intersection intersectionArrive;
            
            if (depart == livraisons.size()){
                intersectionDepart = entrepot;
            } else {
                intersectionDepart = livraisons.get(depart).getAdresse();
            }
            
            if (arrive == livraisons.size()){
                intersectionArrive = entrepot;
            } else {
                intersectionArrive = livraisons.get(depart).getAdresse();
            }
            
                        
            LinkedList<Intersection> trajet = new LinkedList<Intersection>();
            
            int indexDepart = intersectionDepart.getIndex();
            int indexArrive = intersectionArrive.getIndex();
            
            trajet.add(intersectionArrive);
            while(trajet.get(0).getIndex()  != depart){
                trajet.addFirst(intersections.get(pred[depart][trajet.get(0).getIndex()]));
            }                    
            
            return trajet;
        } else {
            System.err.println("Attention, les nombres dans le getChemin ne correspond pas a des livraisons");
            return null;
        }
        
    }
    
}
