/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.io.File;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nguyen
 */
public class XMLParserTest {

    public XMLParserTest() {
    }

    /**
     * Test de getPlan de la classe XMLParser. 
     * Compare la methode getPlan sur le fichier planLyon9points.xml avec un 
     * plan de 9 points intialisé manuellement
     */
    @Test
    public void testGetPlan() throws Exception {
        System.out.println("getPlan");
        File xmlFile = new File("\\\\servif-home\\homes\\dnguyen1\\Mes documents\\NetBeansProjects\\Nouveau dossier\\PLD_Agile_H4202\\fichiersXML\\planLyon9points.xml");
        XMLParser instance = new XMLParser();
        Plan result = instance.getPlan(xmlFile);
        Plan expResult = this.initiale9point();
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test de getDL method de la classe XMLParser. 
     * Compare la methode getDL sur le fichier DLmini3.xml avec une livraison 
     * intialiséz manuellement
     */
    @Test
    public void testGetDL() throws Exception {
        System.out.println("getDL");
        XMLParser instance = new XMLParser();
        File xmlFileDL = new File("\\\\servif-home\\homes\\dnguyen1\\Mes documents\\NetBeansProjects\\Nouveau dossier\\PLD_Agile_H4202\\fichiersXML\\DLmini3.xml");
        Plan plan = this.initiale9point();
        Map<Long, Livraison> livraisons = new TreeMap<Long, Livraison>();
        DemandeLivraison result = instance.getDL(xmlFileDL, plan);
        Livraison livraison5 = new Livraison(plan.getIntersectionsList().get(4), 900);
        Livraison livraison6 = new Livraison(plan.getIntersectionsList().get(5), 600);
        livraisons.put((long) 5, livraison5);
        livraisons.put((long) 6, livraison6);
        Time heureDepart = Time.valueOf("8:0:0");
        DemandeLivraison expResult = new DemandeLivraison(plan.getIntersectionsList().get(0), heureDepart, livraisons);

        assertEquals(expResult.toString(), result.toString());

    }

    /**
     * initialise le Plan avec 9 intersections de 1 à 9 
     * il n'a pas de intersection isolée
     */

    private Plan initiale9point() {
        Intersection intersection1 = new Intersection(1, 0, 0, 0);
        Intersection intersection2 = new Intersection(2, 1, 0, 1);
        Intersection intersection3 = new Intersection(3, 2, 0, 2);
        Intersection intersection4 = new Intersection(4, 0, 1, 3);
        Intersection intersection5 = new Intersection(5, 1, 1, 4);
        Intersection intersection6 = new Intersection(6, 2, 1, 5);
        Intersection intersection7 = new Intersection(7, 0, 2, 6);
        Intersection intersection8 = new Intersection(8, 1, 2, 7);
        Intersection intersection9 = new Intersection(9, 2, 2, 8);

        Troncon troncon21 = new Troncon("Avenue des Frères Lumière", intersection2, intersection1, 1);
        Troncon troncon12 = new Troncon("Avenue des Frères Lumière", intersection1, intersection2, 1);
        Troncon troncon32 = new Troncon("Boulevard Jean XXIII", intersection3, intersection2, 1);
        Troncon troncon23 = new Troncon("Boulevard Jean XXIII", intersection2, intersection3, 1);
        Troncon troncon41 = new Troncon("Place d'Arsonval", intersection4, intersection1, 1);
        Troncon troncon52 = new Troncon("Cours Albert Thomas", intersection5, intersection2, 1);
        Troncon troncon63 = new Troncon("Rue Professeur Louis Roche", intersection6, intersection3, 1);
        Troncon troncon54 = new Troncon("Rue de Montbrillant", intersection5, intersection4, 1);
        Troncon troncon65 = new Troncon("Rue Professeur Louis Roche", intersection6, intersection5, 1);
        Troncon troncon74 = new Troncon("Rue Jeanne d'Arc", intersection7, intersection4, 1);
        Troncon troncon87 = new Troncon("Rue Amiral Courbet", intersection8, intersection7, 1);
        Troncon troncon98 = new Troncon("Rue Charles Richard", intersection9, intersection8, 1);
        Troncon troncon85 = new Troncon("Rue Guy", intersection8, intersection5, 1);
        Troncon troncon96 = new Troncon("Rue du Docteur Bonhomme", intersection9, intersection6, 1);
        Troncon troncon14 = new Troncon("Place d'Arsonval", intersection1, intersection4, 1);
        Troncon troncon25 = new Troncon("Cours Albert Thomas", intersection2, intersection5, 1);
        Troncon troncon36 = new Troncon("Rue Professeur Louis Roche", intersection3, intersection6, 1);
        Troncon troncon45 = new Troncon("Rue de Montbrillant", intersection4, intersection5, 1);
        Troncon troncon56 = new Troncon("Rue Professeur Louis Roche", intersection5, intersection6, 1);
        Troncon troncon47 = new Troncon("Rue Jeanne d'Arc", intersection4, intersection7, 1);
        Troncon troncon78 = new Troncon("Rue Amiral Courbet", intersection7, intersection8, 1);
        Troncon troncon89 = new Troncon("Rue Charles Richard", intersection8, intersection9, 1);
        Troncon troncon58 = new Troncon("Rue Guy", intersection5, intersection8, 1);
        Troncon troncon69 = new Troncon("Rue du Docteur Bonhomme", intersection6, intersection9, 1);

        intersection1.addTroncon(troncon21);
        intersection1.addTroncon(troncon41);
        intersection2.addTroncon(troncon32);
        intersection2.addTroncon(troncon52);
        intersection2.addTroncon(troncon12);
        intersection3.addTroncon(troncon63);
        intersection3.addTroncon(troncon23);
        intersection4.addTroncon(troncon54);
        intersection4.addTroncon(troncon74);
        intersection4.addTroncon(troncon14);
        intersection5.addTroncon(troncon65);
        intersection5.addTroncon(troncon85);
        intersection5.addTroncon(troncon25);
        intersection5.addTroncon(troncon45);
        intersection6.addTroncon(troncon96);
        intersection6.addTroncon(troncon36);
        intersection6.addTroncon(troncon56);
        intersection7.addTroncon(troncon87);
        intersection7.addTroncon(troncon47);
        intersection8.addTroncon(troncon98);
        intersection8.addTroncon(troncon78);
        intersection8.addTroncon(troncon58);
        intersection9.addTroncon(troncon89);
        intersection9.addTroncon(troncon69);

        List<Intersection> intersections = new LinkedList<Intersection>();
        intersections.add(intersection1);
        intersections.add(intersection2);
        intersections.add(intersection3);
        intersections.add(intersection4);
        intersections.add(intersection5);
        intersections.add(intersection6);
        intersections.add(intersection7);
        intersections.add(intersection8);
        intersections.add(intersection9);

        Map<Long, Intersection> mapintersections = new TreeMap<Long, Intersection>();
        mapintersections.put((long) 1, intersection1);
        mapintersections.put((long) 2, intersection2);
        mapintersections.put((long) 3, intersection3);
        mapintersections.put((long) 4, intersection4);
        mapintersections.put((long) 5, intersection5);
        mapintersections.put((long) 6, intersection6);
        mapintersections.put((long) 7, intersection7);
        mapintersections.put((long) 8, intersection8);
        mapintersections.put((long) 9, intersection9);

        Plan plan = new Plan(mapintersections, intersections);
        return plan;
    }
}
