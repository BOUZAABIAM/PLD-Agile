/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.jdom2.*;
//import org.jdom2.input.SAXBuilder;
/**
 *
 * @author carhiliuc
 */
public class XMLJDOMParser {
    public void parsePlan(String chemin){
/*
        try {
            File inputFile = new File(chemin);
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);
            List<Element> elements = document.getRootElement().getChildren();
            
            Map<String,Intersection> intersectionsMap = new HashMap<String,Intersection>();
            List<Troncon> listeTroncons = new ArrayList<Troncon>();
            
            for (int i = 0; i < elements.size(); i++) {
                if(elements.get(i).getName().equals("noeud")){
                    String id = elements.get(i).getAttribute("id").getValue();
                    int x = Integer.parseInt(elements.get(i).getAttribute("x").getValue());
                    int y = Integer.parseInt(elements.get(i).getAttribute("y").getValue());
                    Intersection inter = new Intersection(id, x, y);
                    intersectionsMap.put(id, inter);
                }
            }
            
            for (int j =0; j < elements.size(); j++) {
                if(elements.get(j).getName().equals("troncon")){
                    String origineID = elements.get(j).getAttribute("origine").getValue();
                    // cas d'erreur si id inconnu
                    Intersection origine = intersectionsMap.get(origineID);
                    String destinationID = elements.get(j).getAttribute("destination").getValue();
                    // cas d'erreur si id inconnu
                    Intersection destination = intersectionsMap.get(destinationID);
                    // le string est de la forme xxx.0, il faut mettre en forme pour faire un float
                    int longueur = Integer.parseInt(elements.get(j).getAttribute("longueur").getValue());
                    String nomRue = elements.get(j).getAttribute("nomRue").getValue();
                    Troncon tronc = new Troncon (origine, destination, longueur, nomRue);
                    listeTroncons.add(tronc);
                    
                }
                Plan p = new Plan (intersectionsMap, listeTroncons);
                return p;
            }
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(XMLJDOMParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
    }
}
    
