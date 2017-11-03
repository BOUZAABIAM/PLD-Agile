/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

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

    private Plan lePlan;
    private DemandeLivraison laDL;
    private Intersection[] laSolution;
    private java.util.List<Intersection> leChemin;

    public void setPlan(Plan lePlan) {
        this.lePlan = lePlan;
    }

    public void setDL(DemandeLivraison laDL) {
        this.laDL = laDL;
    }

    public void setSolution(Intersection[] solution) {
        this.laSolution = solution;
    }

    public void setChemin(java.util.List<Intersection> chemin) {
        this.leChemin = chemin;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPlan(g, lePlan);

    }

    //fait la rotation d'un point(x,y) d'une angle de -Pi/2 autour de centre de coordonnees (xc,yc)
    public int[] rotationPoint(int x, int y, int xc, int yc) {
        int coordonnees[] = {x, y};
        coordonnees[0] = y - yc + xc;
        coordonnees[1] = xc - x + yc;
        return coordonnees;
    }

    public void drawPlan(Graphics g, Plan lePlan) {
        if (lePlan == null) {
            return;
        }

        Graphics2D gc = (Graphics2D) g;

        Map<Long, Intersection> intersections = lePlan.getIntersectionsMap();
        Map.Entry<Long, Intersection> first = intersections.entrySet().iterator().next();
        Intersection value = first.getValue();
        double minX = value.getX();
        double minY = value.getY();

        double maxX = value.getX();
        double maxY = value.getY();

        for (Map.Entry<Long, Intersection> entry : intersections.entrySet()) {
            Intersection inter = entry.getValue();
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
        double paramLargeur = (maxX - minX) / 525;
        double paramHauteur = (maxY - minY) / 445;

        //Coordonnees du centre de rotation
        int coordonnees[];
        int xCentre = (int) Math.round((((maxX / 2 + minX / 2) - minX) / (paramLargeur)));
        int yCentre = (int) Math.round((((maxY / 2 + minY / 2) - minY) / (paramHauteur)));
        // Vecteur de translation OT : O(0,0) et T(xT,yT)
        int translation[] = rotationPoint((int) Math.round((maxX - minX) / paramLargeur), 0, xCentre, yCentre);

        //dessine les tronçons
        for (Map.Entry<Long, Intersection> entry : intersections.entrySet()) {
            Intersection origine = entry.getValue();
            gc.setStroke(new BasicStroke(2));
            gc.setColor(Color.BLACK);
            int x1 = (int) Math.round((origine.getX() - minX) / paramLargeur);
            int y1 = (int) Math.round((origine.getY() - minY) / paramHauteur);
            coordonnees = rotationPoint(x1, y1, xCentre, yCentre);
            x1 = coordonnees[0] - translation[0];
            y1 = coordonnees[1] - translation[1];

            for (Troncon section : origine.getTroncons()) {
                Intersection destination = section.getDestination();

                int x2 = (int) Math.round((destination.getX() - minX) / paramLargeur);
                int y2 = (int) Math.round((destination.getY() - minY) / paramHauteur);
                coordonnees = rotationPoint(x2, y2, xCentre, yCentre);
                x2 = coordonnees[0] - translation[0];
                y2 = coordonnees[1] - translation[1];
                gc.drawLine(x1, y1, x2, y2);
            }
        }
        if (laDL != null) {
            //dessine l'entrepot
            gc.setColor(Color.RED);
            int xEntrepot = (int) Math.round(((laDL.getEntrepot().getX() - minX) / paramLargeur));
            int yEntrepot = (int) Math.round(((laDL.getEntrepot().getY() - minY) / paramHauteur));
            coordonnees = rotationPoint(xEntrepot, yEntrepot, xCentre, yCentre);
            xEntrepot = coordonnees[0] - translation[0];
            yEntrepot = coordonnees[1] - translation[1];
            gc.fillOval(xEntrepot - 4, yEntrepot - 4, 8, 8);

            //dessine les livraisons
            Map<Long, Livraison> livraisons = laDL.getLivraison();
            for (Map.Entry<Long, Livraison> entry : livraisons.entrySet()) {
                Livraison livr = entry.getValue();
                gc.setColor(Color.BLUE);
                int xC = (int) Math.round(((livr.getAdresse().getX() - minX) / paramLargeur));
                int yC = (int) Math.round(((livr.getAdresse().getY() - minY) / paramHauteur));
                coordonnees = rotationPoint(xC, yC, xCentre, yCentre);
                xC = coordonnees[0] - translation[0];
                yC = coordonnees[1] - translation[1];
                gc.fillOval(xC - 4, yC - 4, 8, 8);
            }
        }

        if ((leChemin != null) && (laSolution != null)) {

            for (int i =0; i< leChemin.size()-1; i++) {
                gc.setColor(Color.YELLOW);
                // proporionel
                gc.setStroke(new BasicStroke(6));
                Intersection origineT = leChemin.get(i);
                Intersection destinationT = leChemin.get(i+1);
                
                int x1 = (int) Math.round((origineT.getX() - minX) / paramLargeur);
                int y1 = (int) Math.round((origineT.getY() - minY) / paramHauteur);
                coordonnees = rotationPoint(x1, y1, xCentre, yCentre);
                x1 = coordonnees[0] - translation[0];
                y1 = coordonnees[1] - translation[1];
                
                int x2 = (int) Math.round((destinationT.getX() - minX) / paramLargeur);
                int y2 = (int) Math.round((destinationT.getY() - minY) / paramHauteur);
                coordonnees = rotationPoint(x2, y2, xCentre, yCentre);
                x2 = coordonnees[0] - translation[0];
                y2 = coordonnees[1] - translation[1];
                gc.drawLine(x1, y1, x2, y2);
               
            }
            for (Intersection inter : laSolution) {
                System.out.println(inter.getId());
                gc.setColor(Color.RED);
                int xEntrepot = (int) Math.round(((inter.getX() - minX) / paramLargeur));
                int yEntrepot = (int) Math.round(((inter.getY() - minY) / paramHauteur));
                coordonnees = rotationPoint(xEntrepot, yEntrepot, xCentre, yCentre);
                xEntrepot = coordonnees[0] - translation[0];
                yEntrepot = coordonnees[1] - translation[1];
                gc.fillOval(xEntrepot - 5, yEntrepot - 5, 10, 10);
            }
        }

    }

}
