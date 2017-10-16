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
public class JPanelPlan extends JPanel{
    public Plan lePlan;
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawPlan(g,lePlan);
        
    }
     public void drawPlan(Graphics g, Plan plan) {
       if(plan == null){
           return;
       }
         Graphics2D gc = (Graphics2D) g;
        java.util.List<Troncon> troncons = plan.getTroncon();
        for (Troncon section : troncons) {
            gc.setStroke(new BasicStroke(2));
            //gc.setStroke(Color.GREY);
            int x1 = (int)Math.round(section.getOrigine().getX());
            int y1 = (int)Math.round(section.getOrigine().getY());
            int x2 = (int)Math.round(section.getDestination().getX());
            int y2 = (int)Math.round(section.getDestination().getY());
            gc.drawLine(x1, y1, x2, y2);
        }

        java.util.List<Intersection> intersections = plan.getIntersection();
        for (Intersection inter : intersections) {
            int xC = (int)Math.round(inter.getX() - 8 / 2);
            int yC = (int)Math.round(inter.getY() - 8 / 2);
            gc.fillOval(xC, yC,
                    8, 8);
        }
    }
    public void setPlan(Plan lePlan){
        this.lePlan = lePlan;
    }

}
