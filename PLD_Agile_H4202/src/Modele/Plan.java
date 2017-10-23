/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.*;

public class Plan {

    private Map<Long, Intersection> intersections;
    private List<Intersection> intersectionsList;

    private void addIntersection(Intersection intersection) {
        this.intersections.put(intersection.getId(), intersection);
        this.intersectionsList.add(intersection);
    }

    public Plan(Map<Long, Intersection> intersections, List<Intersection> intersectionsList) {
        this.intersections = intersections;
        this.intersectionsList = intersectionsList;
    }

    
    public Map<Long, Intersection> getIntersectionsMap() {
        return intersections;
    }

    public List<Intersection> getIntersectionsList() {
        return intersectionsList;
    }

    
    @Override
    public String toString() {
        return "Plan{" + "intersections=" + intersections + '}';
    }

}
