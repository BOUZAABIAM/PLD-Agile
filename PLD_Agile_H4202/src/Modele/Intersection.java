/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

public class Intersection {
	private long id;
	private	double x,y;
	public Intersection(long id,double x,double y) {
	
	this.id=id;
	this.x=x;
	this.y=y;
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
	
}
