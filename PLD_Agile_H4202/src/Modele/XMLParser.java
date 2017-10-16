/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.io.*;
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
            Intersection intersection = new Intersection(id, x, y);

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

            Troncon troncon = new Troncon(rueNom, idIntersectionEnd, idIntersectionStart, longueur);
            Intersection origine = intersections.get(idIntersectionStart);

            origine.addTroncon(troncon);

        }

        return new Plan(intersections.values());
    }

}
