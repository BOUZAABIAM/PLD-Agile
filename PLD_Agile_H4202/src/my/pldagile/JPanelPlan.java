/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.pldagile;

import Modele.DemandeLivraison;
import Modele.Livraison;
import Modele.Plan;
import Modele.Intersection;
import Modele.Troncon;
import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 *
 * @author epetit
 */
public class JPanelPlan extends JPanel {

    public Plan lePlan;
    public DemandeLivraison laDL;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPlan(g, lePlan);

    }

    public void setPlan(Plan lePlan) {
        this.lePlan = lePlan;
    }
    
    public void setDL(DemandeLivraison laDL) {
        this.laDL = laDL;
    }

    public void drawPlan(Graphics g, Plan lePlan) {
        if (lePlan == null) {
            return;
        }

        Graphics2D gc = (Graphics2D) g;

        java.util.List<Intersection> intersections  = new ArrayList<Intersection>();
        intersections.addAll(lePlan.getIntersection().values());

        double minX = intersections.get(0).getX();
        double minY = intersections.get(0).getY();

        double maxX = intersections.get(0).getX();
        double maxY = intersections.get(0).getY();

        for (Intersection inter : intersections) {
            if (inter.getX() <= minX) {
                minX = inter.getX();
                if (inter.getY() <= minY) {
                    minY = inter.getY();
                }
            }

            if (inter.getX() >= maxX) {
                maxX = inter.getX();
                if (inter.getY() >= maxY) {
                    maxY = inter.getY();
                }
            }
        }

        //trouver taille effective du jPanel à la place de 500 530, ou cacher les valeurs
        double paramLargeur = (maxX - minX) / 500;
        double paramHauteur = (maxY - minY) / 530;
        double paramMax = Math.max(paramLargeur, paramHauteur);

        //dessine les intersections
        for (Intersection inter : intersections) {
            gc.setColor(Color.BLUE);
            int xC = (int) Math.round(((inter.getX() - minX) / paramMax) - 6 / 2);
            int yC = (int) Math.round(((inter.getY() - minY) / paramMax) - 6 / 2);
            gc.fillOval(xC, yC, 6, 6);
        }

        //dessine les tronçons
        for (Intersection origine : intersections) {
            gc.setStroke(new BasicStroke(2));
            gc.setColor(Color.WHITE);
            int x1 = (int) Math.round((origine.getX() - minX) / paramMax);
            int y1 = (int) Math.round((origine.getY() - minY) / paramMax);
            for (Troncon section : origine.getTroncons()) {

            Intersection destination =section.getDestination();

            int x2 = (int) Math.round((destination.getX() - minX) / paramMax);
            int y2 = (int) Math.round((destination.getY() - minY) / paramMax);
            gc.drawLine(x1, y1, x2, y2);
            }
        }
        
        // c'est peut être pas terrible de faire ça avec un if, j'aimerai faire 
        //une deuxième méthode, et une 3e pour dessiner la tournée
        if (laDL != null){
            gc.setColor(Color.BLACK);
            int xEntrepot = (int) Math.round(((laDL.getEntrepot().getX() - minX) / paramMax) - 10 / 2);
            int yEntrepot = (int) Math.round(((laDL.getEntrepot().getY() - minY) / paramMax) - 10 / 2);
            gc.fillOval(xEntrepot, yEntrepot, 10, 10); 

            java.util.List<Livraison> livraisons  = new ArrayList<Livraison>();
            livraisons.addAll(laDL.getLivraison().values());
            for (Livraison livr : livraisons) {           
                gc.setColor(Color.RED);
                int xC = (int) Math.round(((livr.getAdresse().getX() - minX) / paramMax) - 10 / 2);
                int yC = (int) Math.round(((livr.getAdresse().getY() - minY) / paramMax) - 10 / 2);
                gc.fillOval(xC, yC, 10, 10); 
            }
        }
                 

    }

}
