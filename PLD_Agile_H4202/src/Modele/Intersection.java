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

    private int index;
    private long id;
    private double x, y;
    private List<Troncon> troncons;
    private int d;
    private Intersection pred;
    private int predIndex = -1;
    private int couleur;

    public Intersection(long id, double x, double y, int index) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.index = index;
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

    public int getIndex() {
        return index;
    }

    public int getPredIndex() {
        return predIndex;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setPredIndex(int predIndex) {
        this.predIndex = predIndex;
    }
      
    public int getCouleur() {
        return couleur;
    }    
        
    public void relacher(int newD, Intersection pred){
        if (newD < d){
//            System.out.println("relacher");
//            System.out.println("Element courant " + this);
//            System.out.println("Duree courante " + d);
//            System.out.println("Nouvelle duree " + newD);
            this.d = newD;
            this.pred = pred; 
            this.predIndex = this.pred.getIndex();                    
//            System.out.println("Le predeceseur " + this.pred);
//            System.out.println("L'index de predecesseur " + this.predIndex);
//            System.out.println();
        }
    }
    
    public List<Intersection> relacherSucc(){
        List<Intersection> nouveauGris = new LinkedList<Intersection>();
        this.couleur = 2;
        for(Troncon troncon: troncons){
            Intersection arrive = troncon.getDestination();
            if (arrive.getCouleur() == 0){
                nouveauGris.add(arrive);
                arrive.setCouleur(1);
            }
            if (arrive.getCouleur() != 2){
//                System.out.println("relacherSucc");
//                System.out.println("duree " + d);
//                System.out.println("element courant " +this);
//                System.out.println("element arrive de troncon que n'est pas noir " + arrive);
//                System.out.println();
                arrive.relacher(d+troncon.getDuree(), this);
            }
        }
        return nouveauGris;
    }
      
    @Override
    public String toString() {
        return "Intersection{" + "id=" + id + ", x=" + x + ", y=" + y + ", troncons=" + troncons + '}';
    } 
}
