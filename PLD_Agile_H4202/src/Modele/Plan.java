/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.*;

public class Plan {

    private Map<Long, Intersection> intersections;

    private void addIntersection(Intersection intersection) {
        this.intersections.put(intersection.getId(), intersection);
    }

    public Plan(Map<Long, Intersection> intersections) {
        this.intersections = intersections;
    }

    public Map<Long, Intersection> getIntersection() {
        return intersections;
    }

    @Override
    public String toString() {
        return "Plan{" + "intersections=" + intersections + '}';
    }

}
