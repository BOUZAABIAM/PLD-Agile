/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class Intersection {

    private long id;
    private double x, y;
    private List<Troncon> troncons;
    private int d;
    private Intersection pred;
    private int couleur;

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

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public void setPred(Intersection pred) {
        this.pred = pred;
    }

    public void setCouleur(int couleur) {
        this.couleur = couleur;
    }

    public Intersection getPred() {
        return pred;
    }

    public int getCouleur() {
        return couleur;
    }
    
        
    public void relacher(int newD, Intersection pred){
        if (newD < d){
            this.d = newD;
            this.pred = pred; 
        }
        
    }
    
    public List<Intersection> relacherSucc(){
        List<Intersection> nouveauGris = new LinkedList<Intersection>();
        couleur = 2;
        for(Troncon troncon: troncons){
            Intersection arrive = troncon.getDestination();
            if (arrive.getCouleur() == 0){
                nouveauGris.add(arrive);
                arrive.setCouleur(1);
            }
            if (arrive.getCouleur() != 2){
                arrive.relacher(d+troncon.getDuree(), this);
            }
        }
        return nouveauGris;
    }
    

}
