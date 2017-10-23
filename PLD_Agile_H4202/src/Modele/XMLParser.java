/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.io.*;
import java.sql.Time;
import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author carhiliuc
 */
public class XMLParser {

    public XMLParser() {
    }

    public Plan getPlan(File xmlFile) throws IOException, SAXException, ParserConfigurationException {
        Map<Long, Intersection> intersections = new TreeMap<Long, Intersection>();
        List<Intersection> intersectionsList = new LinkedList<Intersection>();

        Document mapDocument = null;
        try {
            mapDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
        } catch (IOException e) {
            throw e;
        }

        NodeList nList = mapDocument.getElementsByTagName("noeud");

        for (int i = 0; i < nList.getLength(); i++) {
            long id;
            double x;
            double y;
            Element element = (Element) nList.item(i);

            id = Long.parseLong(element.getAttribute("id"));
            x = (Double.parseDouble(element.getAttribute("x")));
            y = (Double.parseDouble(element.getAttribute("y")));
            Intersection intersection = new Intersection(id, x, y, i);
            
            intersectionsList.add(intersection);
            intersections.put(id, intersection);
        }

        NodeList streetSectionList = mapDocument.getElementsByTagName("troncon");

        for (int i = 0; i < streetSectionList.getLength(); i++) {
            Long idIntersectionStart;
            Long idIntersectionEnd;
            double longueur;
            String rueNom;
            Element element = (Element) streetSectionList.item(i);

            idIntersectionStart = Long.parseLong(element.getAttribute("origine"));
            idIntersectionEnd = Long.parseLong(element.getAttribute("destination"));
            longueur = Double.parseDouble(element.getAttribute("longueur"));

            rueNom = element.getAttribute("nomRue");
            Intersection origine = intersections.get(idIntersectionStart);
            
            Troncon troncon = new Troncon(rueNom, intersections.get(idIntersectionEnd), origine, longueur);
            

            origine.addTroncon(troncon);

        }

        return new Plan(intersections, intersectionsList);
    }
    
    private String goodTimeForm(String time){
        String newTime = time;
        int prec = -1;    
        for (int i = 0; i < newTime.length(); i++){
            if (newTime.charAt(i) == ':' && ((i - prec) < 3)){
                    prec = i;
                    newTime = newTime.substring(0, i-2) + 0 + newTime.substring(i-2, newTime.length());
             
            }
        }
        return newTime;
    }
    
    public DemandeLivraison getDL(File xmlFile, Plan plan) throws IOException, SAXException, ParserConfigurationException {
        Map<Long, Livraison> livraisons = new TreeMap<Long, Livraison>();
        Intersection entrepot = null;
        Time heureDepart = null;
        

        Document mapDocument = null;
        try {
            mapDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
        } catch (IOException e) {
            throw e;
        }
        
        NodeList entrepots = mapDocument.getElementsByTagName("entrepot");
        
        for (int i = 0; i < entrepots.getLength(); i++) {
            Long idAdresse;
            String time;
            Element element = (Element) entrepots.item(i);

            idAdresse = Long.parseLong(element.getAttribute("adresse"));
            entrepot = plan.getIntersectionsMap().get(idAdresse);
            
            time = element.getAttribute("heureDepart");
            //goodTimeForm générait une erreur, ça a l'air de larcher comma ça
            //String newTime = goodTimeForm(time);
            heureDepart = Time.valueOf(time);
        }

        NodeList livr = mapDocument.getElementsByTagName("livraison");

        for (int i = 0; i < livr.getLength(); i++) {
            Livraison livraison;
            long id;
            Integer duree;
            String debutPlage;
            String finPlage;
            Element element = (Element) livr.item(i);

            id = Long.parseLong(element.getAttribute("adresse"));
            Intersection adresse = plan.getIntersectionsMap().get(id);
            
            duree = Integer.parseInt(element.getAttribute("duree"));
            
            debutPlage = element.getAttribute("debutPlage");
            finPlage = element.getAttribute("finPlage");
            
            if(debutPlage.isEmpty()||finPlage.isEmpty()){
                livraison = new Livraison(adresse, duree);
            }else{
                livraison = new Livraison(adresse, duree, debutPlage, finPlage);
            }
            
            livraisons.put(id, livraison);
        }

        return new DemandeLivraison(entrepot, heureDepart, livraisons);
    }

}

