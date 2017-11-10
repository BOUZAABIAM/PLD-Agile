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
    final private long id;
    final private double x, y;
    final private List<Troncon> troncons;
    private int d;
    private Intersection pred;
    private int predIndex = -1;
    /* 
    * La couleur prend les valeurs: 
    * 0 = blanc, 1 = gris, 2 = noir 
     */
    private int couleur;

    public Intersection(long id, double x, double y, int index) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.index = index;
        this.troncons = new ArrayList<>();
    }

    /**
     * Ajoute un tronçon dans la liste des tronçons
     *
     * @param troncon
     */
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

    /**
     * Change les valeurs de d, pred et predIndex si necessaire suite au
     * relanchement. Les valeurs sont changés que si le newD est inferieur au d
     * courant.
     *
     * @param newD la nouvelle durée proposée suite au relanchement
     * @param pred le nouveau precedesseur si la nouvelle durée est plus petite
     * que l'ancienne
     */
    public void relacher(int newD, Intersection pred) {
        if (newD < d) {
            this.d = newD;
            this.pred = pred;
            this.predIndex = this.pred.getIndex();
        }
    }

    /**
     * Relache les successeurs de l'intersection courant. Suite à ça la couleur
     * de l'intersection courante est changé vers 2
     *
     * @return
     */
    public List<Intersection> relacherSucc() {
        List<Intersection> nouveauGris = new LinkedList<>();
        this.couleur = 2;
        for (Troncon troncon : troncons) {
            Intersection arrive = troncon.getDestination();
            if (arrive.getCouleur() == 0) {
                nouveauGris.add(arrive);
                arrive.setCouleur(1);
            }
            if (arrive.getCouleur() != 2) {
                arrive.relacher(d + troncon.getDuree(), this);
            }
        }
        return nouveauGris;
    }

    @Override
    public String toString() {
        return "Intersection{" + "id=" + id + ", x=" + x + ", y=" + y + ", troncons=" + troncons + '}';
    }
}
