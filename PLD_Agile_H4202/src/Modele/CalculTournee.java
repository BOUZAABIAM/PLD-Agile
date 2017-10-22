/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.*;
import javafx.util.Pair;
 

/**
 *
 * @author carhiliuc
 */
public class CalculTournee {
    private List<Livraison> livraisons;
    private List<Intersection> intersections;
    private Intersection entrepot;

    public CalculTournee(List<Livraison> livraisons, List<Intersection> intersections, Intersection entrepot) {
        this.livraisons = livraisons;
        this.intersections = intersections;
        this.entrepot = entrepot;
    }

         

    
/*    public LinkedList< Pair<Intersection, Double> >[] graphVille(){
        int nombreIntersections = this.intersections.size();
        LinkedList< Pair<Intersection, Double> >[] graph = (LinkedList< Pair<Intersection, Double> >[]) new LinkedList[nombreIntersections];
        
        for (int i = 0; i < graph.length; ++i) {
            graph[i] = new LinkedList<>();
            Intersection intersection = intersections.get(i);
            List<Troncon> liste = intersection.getTroncons();
            graph[i].add(new Pair<>(intersection, 0.0));
            for (int j = 0; j < liste.size(); j++){
                graph[i].add(new Pair<>(liste.get(i).getDestination(), liste.get(i).getLongueur()));
            }
        }        
        return graph;
    }
    
    private double calculDureeChemin(Intersection intersection1, Intersection intersection2, LinkedList< Pair<Intersection, Double> >[] graph){
        
    return 0;
    }
*/
    
    private int[] calculDuree(Intersection depart, Intersection[] intersectionLivraison){
        
        Intersection[] gris = new Intersection[intersections.size()];
        int nbGris = 0;
        
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
           int[] durees = calculDuree(intersectionsLivraisons[i], intersectionsLivraisons) ; 
           for (int j = 0; j < intersectionsLivraisons.length; j++){
               matriceLivraison[i][j] = durees[j];
           }
       }
       
       return matriceLivraison;
    } 
    
}
