/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modele.*;
import java.util.*;
 

/**
 *
 * @author carhiliuc
 */
public class CalculTurnee {
    private List<Troncon> troncons;
    private List<Livraison> livraisons;
    private int totalIntersections;

    public CalculTurnee(List<Troncon> troncons, List<Livraison> livraisons, int totalIntersections) {
        this.troncons = troncons;
        this.livraisons = livraisons;
        this.totalIntersections = totalIntersections;
    }

       
    public AdjacencyList planGraph(){
           AdjacencyList graph = new AdjacencyList(totalIntersections);
           
           return new AdjacencyList(5);
    }
    
}
