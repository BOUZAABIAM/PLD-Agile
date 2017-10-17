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

    public Plan(Iterable<Intersection> intersections) {
        this.intersections = new TreeMap<Long, Intersection>();

        for (Intersection intersection : intersections) {
            this.addIntersection(intersection);
        }
    }

    public List<Intersection> getIntersection() {
        List<Intersection> listIntersection = new ArrayList<Intersection>();
        listIntersection.addAll(this.intersections.values());
        return listIntersection;
    }

    @Override
    public String toString() {
        return "Plan{" + "intersections=" + intersections + '}';
    }

}
