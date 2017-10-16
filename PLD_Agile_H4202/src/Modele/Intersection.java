/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.List;
import java.util.ArrayList;

public class Intersection {

    private long id;
    private double x, y;
    private List<Troncon> troncons;

    public Intersection(long id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.troncons = new ArrayList<Troncon>();
    }

    public void addTroncon(Troncon troncon) {
        troncons.add(troncon);
    }

    public long getId() {
        return this.id;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public List<Troncon> getTroncons() {
        return troncons;
    }

    @Override
    public String toString() {
        return "Intersection{" + "id=" + id + ", x=" + x + ", y=" + y + ", troncons=" + troncons + '}';
    }
    
}
