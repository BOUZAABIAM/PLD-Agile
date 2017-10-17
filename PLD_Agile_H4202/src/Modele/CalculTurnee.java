/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Controller.AdjacencyList;
import Modele.*;
import java.util.*;
import javafx.util.Pair;
 

/**
 *
 * @author carhiliuc
 */
public class CalculTurnee {
    private List<Livraison> livraisons;
    private List<Intersection> intersections;

    public CalculTurnee(List<Livraison> livraisons, List<Intersection> intersections) {
        this.livraisons = livraisons;
        this.intersections = intersections;
    }

    
    public LinkedList< Pair<Intersection, Double> >[] graphVille(){
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

    private LinkedList< Pair<Intersection, Double> >[] graphLivraison(){  
        int nombreLivraisons = this.livraisons.size();
        LinkedList< Pair<Intersection, Double> >[] graph = (LinkedList< Pair<Intersection, Double> >[]) new LinkedList[nombreLivraisons];
        for (int i = 0; i < graph.length; ++i) {
            graph[i] = new LinkedList<>();
            Intersection intersection = livraisons.get(i).getAdresse();
            graph[i].add(new Pair<>(intersection, 0.0));
            
        }        
        return graph;
    } 
    
}
