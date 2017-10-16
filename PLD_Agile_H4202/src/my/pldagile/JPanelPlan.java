/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.pldagile;

import Modele.Intersection;
import Modele.Plan;
import Modele.Troncon;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author epetit
 */
public class JPanelPlan extends JPanel {

    public Plan lePlan;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPlan(g, lePlan);

    }

    public void drawPlan(Graphics g, Plan lePlan) {
        if (lePlan == null) {
            return;
        }
        
        Graphics2D gc = (Graphics2D) g;

        java.util.List<Intersection> intersections = lePlan.getIntersection();
        
        double minX = intersections.get(0).getX();
        double minY = intersections.get(0).getY();
        
        double maxX = intersections.get(0).getX();
        double maxY = intersections.get(0).getY();
        
        for (Intersection inter : intersections) {
            if (inter.getX()<= minX){
                minX = inter.getX();
                if(inter.getY()<= minY){
                  minY = inter.getY();  
                }
            } 
            
            if (inter.getX()>= maxX){
                maxX = inter.getX();
                if(inter.getY()>= maxY){
                  maxY = inter.getY();  
                }
            } 
        }
        
//trouver taille effective du jPanel à la place de 500 530
        double paramLargeur = (maxX - minX)/500;
        double paramHauteur = (maxY - minY)/530;
        double paramMax = Math.max(paramLargeur,paramHauteur);
        
        for (Intersection inter : intersections) {
            int xC = (int) Math.round(((inter.getX() - minX) / paramMax) - 5 / 2);
            int yC = (int) Math.round(((inter.getY() - minY) / paramMax) - 5 / 2);
            gc.fillOval(xC, yC, 5, 5);
        }


        for (Intersection inter : intersections) {
            for(Troncon section : inter.getTroncons()){
                gc.setStroke(new BasicStroke(2));
                int x1 = (int) Math.round((inter.getX() - minX) / paramMax);
                int y1 = (int) Math.round((inter.getY() - minY) / paramMax);
    //ça devrait marcher, section.getDestination()est bien un long, je sais pas pk il pense que c'est un int
                Intersection destination = intersections.get(section.getDestination());
                int x2 = (int) Math.round((destination.getX() - minX) / paramMax);
                int y2 = (int) Math.round((destination.getY() - minY) / paramMax);
                gc.drawLine(x1, y1, x2, y2);
            }
        }

    }

    public void setPlan(Plan lePlan) {
        this.lePlan = lePlan;
    }

}
