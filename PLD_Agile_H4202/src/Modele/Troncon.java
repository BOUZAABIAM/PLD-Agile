/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;
/**
 *
 * @author carhiliuc
 */
public class Troncon {
    
    private Intersection origine;
    private Intersection destination;
    private float longueur;
    private String nomRue;

    public Troncon(Intersection origine, Intersection destination, float longueur, String nomRue) {
        this.origine = origine;
        this.destination = destination;
        this.longueur = longueur;
        this.nomRue = nomRue;
    }
    
    
}
