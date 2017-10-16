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
        
//trouver taille effective du jPanel à la place de 559 545
        double paramLargeur = (maxX - minX)/559;
        double paramHauteur = (maxY - minY)/545;
        double paramMax = Math.max(paramLargeur,paramHauteur);
        for (Intersection inter : intersections) {
            int xC = (int) Math.round(((inter.getX() - minX) / paramMax) - 5 / 2);
            int yC = (int) Math.round(((inter.getY() - minY) / paramMax) - 5 / 2);
            gc.fillOval(xC, yC,
                    5, 5);
        }

        java.util.List<Troncon> troncons = lePlan.getTroncon();
        for (Troncon section : troncons) {
            gc.setStroke(new BasicStroke(2));
            //gc.setStroke(Color.GREY);
            int x1 = (int) Math.round((section.getOrigine().getX() - minX) / paramMax);
            int y1 = (int) Math.round((section.getOrigine().getY() - minY) / paramMax);
            int x2 = (int) Math.round((section.getDestination().getX() - minX) / paramMax);
            int y2 = (int) Math.round((section.getDestination().getY() - minY) / paramMax);
            gc.drawLine(x1, y1, x2, y2);
        }

    }

    public void setPlan(Plan lePlan) {
        this.lePlan = lePlan;
    }

}
