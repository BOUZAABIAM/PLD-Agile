/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

public class Troncon {

    final private String nomRue;
    final private Intersection destination;
    final private Intersection origine;
    final private double longueur;

    public Troncon(String nomRue, Intersection destination, Intersection origine, double longueur) {
        this.nomRue = nomRue;
        this.destination = destination;
        this.origine = origine;
        this.longueur = longueur;
    }

    public String getNomRue() {
        return nomRue;
    }

    public Intersection getDestination() {
        return destination;
    }

    public Intersection getOrigine() {
        return origine;
    }

    /**
     * Renvoie la durée en seconds pour parcurir le tronçon
     * @return la durée en seconds pour parcurir le tronçon
     */
    public int getDuree() {
        // vitesse en m par s
        double vitesse  = 15.0*1000/3600;
        int resultat = (int)(longueur/vitesse);
        return resultat;
    }

    public double getLongueur() {
        return longueur;
    }

    @Override
    public String toString() {
        return "Troncon{" + "nomRue=" + nomRue + ", destination=" + destination.getId() + ", origine=" + origine.getId() + ", longueur=" + longueur + '}';
    }
}
