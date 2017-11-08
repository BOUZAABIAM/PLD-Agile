/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.LinkedList;
import java.util.List;
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
public class IntersectionTest {
    
    public IntersectionTest() {
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
     * Test of relacherSucc method, of class Intersection.
     */
    @Test
    public void testRelacherSucc() {
        System.out.println("relacherSucc");
        Intersection intersection4 = new Intersection(4,0,1,3);
        List<Intersection> intersections = new LinkedList<Intersection>();
                
        List<Intersection> result = intersection4.relacherSucc();     
        Intersection intersection5 = new Intersection(5,1,1,4);       
        Intersection intersection7 = new Intersection(7,0,2,6);
        intersection5.setCouleur(2);
         intersection7.setCouleur(2);
        Troncon troncon54 = new Troncon("Rue de Montbrillant",intersection5,intersection4,1);
        Troncon troncon74 = new Troncon("Rue Jeanne d'Arc",intersection7,intersection4,1); 
        intersection4.addTroncon(troncon54);
        intersection4.addTroncon(troncon74);
//        intersections.add(intersection5);
//        intersections.add(intersection7);      
        assertEquals(intersections, result);
       
    }

}

