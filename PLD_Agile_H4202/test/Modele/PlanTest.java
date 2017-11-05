/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.Time;
import java.util.ArrayList;
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
 * @author A-Kira
 */
public class PlanTest {
    
    public PlanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

   

    /**
     * Test of graphLivraison method, of class Plan.
     */
    @Test
    public void testGraphLivraison() {
        System.out.println("graphLivraison");
        Plan instance = this.initiale();
        int[][] expResult = null;
        int[][] result = instance.graphLivraison();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }



    /**
     * Test of getChemin method, of class Plan.
     */
    @Test
    public void testGetChemin_int_int() {
        System.out.println("getChemin");
        int depart = 0;
        int arrive = 0;
        Plan instance = this.initiale();
        List<Intersection> expResult = null;
        List<Intersection> result = instance.getChemin(depart, arrive);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDuree method, of class Plan.
     */
    @Test
    public void testGetDuree() {
        System.out.println("getDuree");
        Plan instance = this.initiale();
        int[] expResult = new int[2];
        expResult[0]=900;
        expResult[0]=600;
        expResult[0]=0;
        Intersection intersection5 = new Intersection(5,1,1,4);
        Map<Long, Livraison> livraisons = new TreeMap<Long, Livraison>();
        Livraison livraison5 = new Livraison(intersection5,900);
        Intersection intersection6 = new Intersection(6,2,1,5);
        Intersection entrepot = new Intersection(1,0,0,0);
        Livraison livraison6 = new Livraison(intersection6,600);
        livraisons.put((long)5, livraison5);
        livraisons.put((long)6, livraison6);
        Time heureDepart = Time.valueOf("8:0:0");
        DemandeLivraison dl = new DemandeLivraison(entrepot,heureDepart,livraisons);
        instance.setDL(dl);
        int[] result = instance.getDuree();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addLivraison method, of class Plan.
     */
    @Test
    public void testAddLivraison() {
        System.out.println("addLivraison");
        
        Intersection livraisonAAjouter = new Intersection(3,2,0,2);   
        Intersection intersection5 = new Intersection(5,1,1,4);
        Intersection intersection6 = new Intersection(6,2,1,5);
        Livraison livraison5 = new Livraison(intersection5,900);
        Livraison livraison = new Livraison(livraisonAAjouter, 500);
        List<Livraison> livraisons = new LinkedList<Livraison>();
        livraisons.add(livraison5);
        
        
        Plan instance = this.initiale();     
        instance.addLivraison(intersection5, intersection6, livraisonAAjouter);
        assertEquals(livraisons, instance.getDL());
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIndiceLivraisonParIntersection method, of class Plan.
     */
    @Test
    public void testGetIndiceLivraisonParIntersection() {
        System.out.println("getIndiceLivraisonParIntersection");
       
        Plan instance = this.initiale();
        int expResult = 0;
        Intersection intersection5 = new Intersection(5,1,1,4);
        Map<Long, Livraison> livraisons = new TreeMap<Long, Livraison>();
        Livraison livraison5 = new Livraison(intersection5,900);
        Intersection intersection6 = new Intersection(6,2,1,5);
        Intersection entrepot = new Intersection(1,0,0,0);
        Livraison livraison6 = new Livraison(intersection6,600);
        livraisons.put((long)5, livraison5);
        livraisons.put((long)6, livraison6);
        Time heureDepart = Time.valueOf("8:0:0");
        DemandeLivraison dl = new DemandeLivraison(entrepot,heureDepart,livraisons);
        instance.setDL(dl);
        int result = instance.getIndiceLivraisonParIntersection(intersection5);
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLivraisonParIntersection method, of class Plan.
     */
    @Test
    public void testGetLivraisonParIntersection() {
        System.out.println("getLivraisonParIntersection");
       
        Plan instance = this.initiale();
        Intersection intersection5 = new Intersection(5,1,1,4);
        Map<Long, Livraison> livraisons = new TreeMap<Long, Livraison>();
        Livraison livraison5 = new Livraison(intersection5,900);
        Intersection intersection6 = new Intersection(6,2,1,5);
        Intersection entrepot = new Intersection(1,0,0,0);
        Livraison livraison6 = new Livraison(intersection6,600);
        livraisons.put((long)5, livraison5);
        livraisons.put((long)6, livraison6);
        Time heureDepart = Time.valueOf("8:0:0");
        DemandeLivraison dl = new DemandeLivraison(entrepot,heureDepart,livraisons);
        instance.setDL(dl);      
        assertEquals(livraison5, instance.getLivraisonParIntersection(intersection5));
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculSolutionTSP1 method, of class Plan.
     */
    @Test
    public void testCalculSolutionTSP1() {
        System.out.println("calculSolutionTSP1");
        Plan instance = null;
        instance.calculSolutionTSP1();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

  
    private Plan initiale(){
        Intersection intersection1 = new Intersection(1,0,0,0);
        Intersection intersection2 = new Intersection(2,1,0,1);
        Intersection intersection3 = new Intersection(3,2,0,2);
        Intersection intersection4 = new Intersection(4,0,1,3);
        Intersection intersection5 = new Intersection(5,1,1,4);
        Intersection intersection6 = new Intersection(6,2,1,5);
        Intersection intersection7 = new Intersection(7,0,2,6);
        Intersection intersection8 = new Intersection(8,1,2,7);
        Intersection intersection9 = new Intersection(9,3,2,8);
      
        Troncon troncon21 = new Troncon("Avenue des FrÃ¨res LumiÃ¨re",intersection2,intersection1,1);
        Troncon troncon32 = new Troncon("Boulevard Jean XXIII",intersection3,intersection2,1);
        Troncon troncon41 = new Troncon("Place d'Arsonval",intersection4,intersection1,1);
        Troncon troncon52 = new Troncon("Cours Albert Thomas",intersection5,intersection2,1);
        Troncon troncon63 = new Troncon("Rue Professeur Louis Roche",intersection6,intersection3,1);
        Troncon troncon54 = new Troncon("Rue de Montbrillant",intersection5,intersection4,1);
        Troncon troncon65 = new Troncon("Rue Professeur Louis Roche",intersection6,intersection5,1);
        Troncon troncon74 = new Troncon("Rue Jeanne d'Arc",intersection7,intersection4,1);
        Troncon troncon87 = new Troncon("Rue Amiral Courbet",intersection8,intersection7,1);
        Troncon troncon98 = new Troncon("Rue Charles Richard",intersection9,intersection8,1);
        Troncon troncon85 = new Troncon("Rue Guy",intersection8,intersection5,1);
        Troncon troncon96 = new Troncon("Rue du Docteur Bonhomme",intersection9,intersection6,1);
        
        
        intersection1.addTroncon(troncon21);
        intersection1.addTroncon(troncon41);
        intersection2.addTroncon(troncon32);
        intersection2.addTroncon(troncon52);
        intersection3.addTroncon(troncon63);
        intersection4.addTroncon(troncon54);
        intersection4.addTroncon(troncon74);
        intersection7.addTroncon(troncon87);
        intersection8.addTroncon(troncon98);
        intersection5.addTroncon(troncon85);
        intersection6.addTroncon(troncon96);
        intersection5.addTroncon(troncon65);
        
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
        mapintersections.put((long)1,intersection1);
        mapintersections.put((long)2,intersection2);
        mapintersections.put((long)3,intersection3);
        mapintersections.put((long)4,intersection4);
        mapintersections.put((long)5,intersection5);
        mapintersections.put((long)6,intersection6);
        mapintersections.put((long)7,intersection7);
        mapintersections.put((long)8,intersection8);
        mapintersections.put((long)9,intersection9);
        
        Plan plan = new Plan(mapintersections,intersections);
        return plan;
    } 

   
}
