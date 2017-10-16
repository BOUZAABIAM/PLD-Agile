/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Modele.Intersection;

public class Troncon {
private String nomRue;
private long destination,origine;
private double longueur;

public Troncon(String nomRue, long destination, long origine, double longueur) {
	super();
	this.nomRue = nomRue;
	this.destination = destination;
	this.origine = origine;
	this.longueur = longueur;
}

public String getNomRue() {
	return nomRue;
}


public long getDestination() {
	return destination;
}

public long getOrigine() {
	return origine;
}

public double getLongueur() {
	return longueur;
}

}