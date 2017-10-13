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
public class Plan {
    private Map<String,Intersection> intersections;
    private List<Troncon> troncons;

    public Plan(Map<String, Intersection> intersections, List<Troncon> troncons) {
        this.intersections = intersections;
        this.troncons = troncons;
    }


    public Plan() {
    }
    
    
}
