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
import org.junit.Assert;
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
        Plan instance = this.initiale3point();
        setDL2(instance,instance.getIntersectionsList().get(0),instance.getIntersectionsList().get(1),instance.getIntersectionsList().get(2));
        int[][] expResult = {{0,5040,1440},{5040,0,6480},{1440,6480,0}};
        int[][] result = instance.graphLivraison();
        assertNotNull(result);
        assertArrayEquals(expResult, result);      
    }



    /**
     * Test of getChemin method, of class Plan.
     */
    @Test
    public void testGetChemin_int_int() {
        System.out.println("getChemin");        
        Plan instance = this.initiale3point();
            
        List<Intersection> expResult = new LinkedList<Intersection>();
        expResult.add(instance.getIntersectionsList().get(2));
        expResult.add(instance.getIntersectionsList().get(1));
        expResult.add(instance.getIntersectionsList().get(0));
        setDL2(instance,instance.getIntersectionsList().get(0),instance.getIntersectionsList().get(1),instance.getIntersectionsList().get(2));
        instance.calculSolutionTSP1();
        List<Intersection> result = instance.getChemin(1,2);
        //test le plus court chemin
        assertEquals(expResult.toString(), result.toString());
        // 
       // List<Intersection> result2 = instance.getChemin(1,3);
        
    }
    

    /**
     * Test of getDuree method, of class Plan.
     */
    @Test
    public void testGetDuree() {
        System.out.println("getDuree");
        Plan instance = this.initiale9point();
        int[] expResult = new int[3];
        expResult[0]=900;
        expResult[1]=600;
        expResult[2]=0;
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
       // fail("The test case is a prototype.");
    }

    /**
     * Test of addLivraison method, of class Plan.
	 *A faire avec le chemin null(il y a pas de chemin)
	 *
     */
    @Test
    public void testAddLivraison() {
        System.out.println("addLivraison");  
        Plan instance = this.initiale3point(); 		
	setDL1(instance,instance.getIntersectionsList().get(0),instance.getIntersectionsList().get(2));
	List<ArrayList<Intersection>> result = instance.addLivraison(instance.getIntersectionsList().get(0),instance.getIntersectionsList().get(1));
	Plan instance2 = this.initiale3point();
		
	// test ajouter intersection1
	setDL2(instance2,instance2.getIntersectionsList().get(0),instance2.getIntersectionsList().get(1),instance2.getIntersectionsList().get(2));		
        instance2.calculSolutionTSP1();
        List<ArrayList<Intersection>> expResult = instance2.getSolution2();
	// il y a pas de chemin pour aller a intersection 4
	setDL2(instance2,instance2.getIntersectionsList().get(0),instance2.getIntersectionsList().get(1),instance2.getIntersectionsList().get(3));	
        instance2.calculSolutionTSP1();
        List<ArrayList<Intersection>> expResult2 = instance2.getSolution2();	   
        assertEquals(expResult, result);
    }
	
    /**
     * Test of deleteLivraison method, of class Plan.
     */
    @Test
    public void testDeleteLivraison() {
        System.out.println("deleteLivraison");
        Intersection intersection1 = new Intersection(1,0,0,0);
        Intersection intersection2 = new Intersection(2,1,0,1);
        Intersection intersection3 = new Intersection(3,2,0,2);
	Intersection intersection4 = new Intersection(4,2,0,3);
        Troncon troncon12 = new Troncon("A",intersection1,intersection2,2);
        Troncon troncon13 = new Troncon("B",intersection1,intersection3,10);
        Troncon troncon23 = new Troncon("C",intersection2,intersection3,7);
        Troncon troncon21 = new Troncon("A",intersection2,intersection1,2);
        Troncon troncon31 = new Troncon("B",intersection3,intersection1,10);
        Troncon troncon32 = new Troncon("C",intersection3,intersection2,7);
        intersection1.addTroncon(troncon21);
        intersection1.addTroncon(troncon31);
        intersection2.addTroncon(troncon32);
        intersection2.addTroncon(troncon12);
        intersection3.addTroncon(troncon13);
        intersection3.addTroncon(troncon23);
	ArrayList<ArrayList<Intersection>> expResult = new ArrayList<ArrayList<Intersection>>();
	ArrayList<Intersection> arraylist1 = new ArrayList<Intersection>();
        ArrayList<Intersection> arraylist2 = new ArrayList<Intersection>();
        arraylist1.add(intersection1);
	arraylist1.add(intersection2);
        arraylist1.add(intersection3);
	arraylist2.add(intersection3);
	arraylist2.add(intersection2);
	arraylist2.add(intersection1);	
			
	expResult.add(arraylist1);
	expResult.add(arraylist2);

	Plan instance = this.initiale3point(); 
	setDL2(instance,instance.getIntersectionsList().get(0),instance.getIntersectionsList().get(1),instance.getIntersectionsList().get(2));		
        instance.calculSolutionTSP1();
        List<ArrayList<Intersection>> result = instance.deleteLivraison(intersection2);
	
	assertEquals(expResult.toString(), result.toString());
		
	// suprimer entrepot
	List<ArrayList<Intersection>> result2 = instance.deleteLivraison(intersection1);	
	ArrayList<ArrayList<Intersection>> solution2 = this.solution2();
	assertEquals(solution2.toString(), result2.toString());
		
	// suprimer intersection qui n'est pas un adress de livraison
	List<ArrayList<Intersection>> result3 = instance.deleteLivraison(intersection4);		
	assertEquals(solution2.toString(), result3.toString());
       
    }

    /**
     * Test of getIndiceLivraisonParIntersection method, of class Plan.
     */
    @Test
    public void testGetIndiceLivraisonParIntersection() {
        System.out.println("getIndiceLivraisonParIntersection");
       
        Plan instance = this.initiale9point();
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
       
    }

    /**
     * Test of getLivraisonParIntersection method, of class Plan.
     */
    @Test
    public void testGetLivraisonParIntersection() {
        System.out.println("getLivraisonParIntersection");
        Plan instance = this.initiale9point();
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
       
    }
	
	@Test
	/*
	 * 
	 */
    public void testcalculDuree(){
	Plan instance = this.initiale3point();
	Intersection intersection1 = new Intersection(1,0,0,0);
        Intersection intersection2 = new Intersection(2,1,0,1);
        Intersection intersection3 = new Intersection(3,2,0,2);
		
        Troncon troncon12 = new Troncon("A",intersection1,intersection2,2);
        Troncon troncon13 = new Troncon("B",intersection1,intersection3,10);
        Troncon troncon23 = new Troncon("C",intersection2,intersection3,7);
        Troncon troncon21 = new Troncon("A",intersection2,intersection1,2);
        Troncon troncon31 = new Troncon("B",intersection3,intersection1,10);
        Troncon troncon32 = new Troncon("C",intersection3,intersection2,7);
        intersection1.addTroncon(troncon21);
        intersection1.addTroncon(troncon31);
        intersection2.addTroncon(troncon32);
        intersection2.addTroncon(troncon12);
        intersection3.addTroncon(troncon13);
        intersection3.addTroncon(troncon23);
        Intersection[] intersections = new Intersection[3];
        intersections[0]=intersection1;
        intersections[1]=intersection2;
        intersections[2]=intersection3;
	int[] expResult = new int[3];
        expResult[0]=0;
        expResult[1]=0;
        expResult[2]=0;
	int[] result = null;
        setDL2(instance,instance.getIntersectionsList().get(0),instance.getIntersectionsList().get(1),instance.getIntersectionsList().get(2));
        //result= instance.calculDuree(intersection2,intersections, Integer.MAX_VALUE);
        result= instance.calculDuree(intersection2,intersections, Integer.MAX_VALUE);
        Assert.assertArrayEquals(expResult,result);
    }
	
	
	
	/**
     * Test of adresseEnLivraison method, of class Plan.
	 * 
     */
    @Test
    public void testAdresseEnLivraison() {
        System.out.println("adresseEnLivraison");        
        Plan instance = this.initiale3point();
	setDL2(instance,instance.getIntersectionsList().get(0),instance.getIntersectionsList().get(1),instance.getIntersectionsList().get(2));
        // existe pas
	assertEquals(false, instance.adresseEnLivraison(instance.getIntersectionsList().get(3)));
        
         // existe
        assertEquals(true, instance.adresseEnLivraison(instance.getIntersectionsList().get(1)));
    }
	
    /**
     * Test of calculSolutionTSP1 method, of class Plan.
     */
    
    @Test
    public void testCalculSolutionTSP1() {
        System.out.println("calculSolutionTSP1");
        Plan instance = this.initiale3point();
        Map<Long, Livraison> livraisons = new TreeMap<Long, Livraison>();
	setDL2(instance,instance.getIntersectionsList().get(0),instance.getIntersectionsList().get(1),instance.getIntersectionsList().get(2));
	ArrayList<ArrayList<Intersection>> expResult = this.solution2();
        instance.calculSolutionTSP1();  
        assertEquals(expResult.toString(), instance.getSolution2().toString());
        Plan instance2 = this.initiale3point();
        
        // il y a pas le chemin pour aller a intersection4
        setDL3(instance2,instance2.getIntersectionsList().get(0),instance2.getIntersectionsList().get(1),instance2.getIntersectionsList().get(2),instance2.getIntersectionsList().get(3));        
        instance2.calculSolutionTSP1();  
        assertEquals(expResult.toString(), instance2.getSolution2().toString());
    }
    

   

    private Plan initiale3point(){
        Intersection intersection1 = new Intersection(1,0,0,0);
        Intersection intersection2 = new Intersection(2,1,0,1);
        Intersection intersection3 = new Intersection(3,2,0,2);
        Intersection intersection4 = new Intersection(4,2,0,3);
        Troncon troncon12 = new Troncon("A",intersection1,intersection2,2);
        Troncon troncon13 = new Troncon("B",intersection1,intersection3,10);
        Troncon troncon23 = new Troncon("C",intersection2,intersection3,7);
        Troncon troncon21 = new Troncon("A",intersection2,intersection1,2);
        Troncon troncon31 = new Troncon("B",intersection3,intersection1,10);
        Troncon troncon32 = new Troncon("C",intersection3,intersection2,7);
        intersection1.addTroncon(troncon21);
        intersection1.addTroncon(troncon31);
        intersection2.addTroncon(troncon32);
        intersection2.addTroncon(troncon12);
        intersection3.addTroncon(troncon13);
        intersection3.addTroncon(troncon23);
        List<Intersection> intersections = new LinkedList<Intersection>();
        intersections.add(intersection1);
        intersections.add(intersection2);
        intersections.add(intersection3);
	intersections.add(intersection4);
        Map<Long, Intersection> mapintersections = new TreeMap<Long, Intersection>();
        mapintersections.put((long)1,intersection1);
        mapintersections.put((long)2,intersection2);
        mapintersections.put((long)3,intersection3);
	mapintersections.put((long)4,intersection4);
        Plan plan = new Plan(mapintersections,intersections);
       
        return plan;
    }
	// 1 entrepot 3 livraison
    private void setDL3(Plan plan,Intersection intersection1, Intersection intersection2,Intersection intersection3,Intersection intersection4){
		Map<Long, Livraison> livraisons = new TreeMap<Long, Livraison>();
      
                Livraison livraison2 = new Livraison(intersection2,900);
                Livraison livraison3 = new Livraison(intersection3,600);
                Livraison livraison4 = new Livraison(intersection4,500);
                livraisons.put((long)2, livraison2);
                livraisons.put((long)3, livraison3);
                livraisons.put((long)4, livraison4);
                Time heureDepart = Time.valueOf("8:0:0");
                DemandeLivraison dl = new DemandeLivraison(intersection1,heureDepart,livraisons);
		plan.setDL(dl);
		 
	}
    // 1 entrepot + 2 livraison
	private void setDL2(Plan plan,Intersection intersection1, Intersection intersection2,Intersection intersection3){
		Map<Long, Livraison> livraisons = new TreeMap<Long, Livraison>();
      
                Livraison livraison2 = new Livraison(intersection2,900);
                Livraison livraison3 = new Livraison(intersection3,600);
  
                livraisons.put((long)2, livraison2);
                livraisons.put((long)3, livraison3);
                Time heureDepart = Time.valueOf("8:0:0");
                DemandeLivraison dl = new DemandeLivraison(intersection1,heureDepart,livraisons);
		plan.setDL(dl);
		 
	}
// 1 entrepot + 1 livraison
	private void setDL1(Plan plan,Intersection intersection1, Intersection intersection3){
		Map<Long, Livraison> livraisons = new TreeMap<Long, Livraison>();      
                Livraison livraison3 = new Livraison(intersection3,600);       
                livraisons.put((long)1, livraison3);
                Time heureDepart = Time.valueOf("8:0:0");
                DemandeLivraison dl = new DemandeLivraison(intersection1,heureDepart,livraisons);
		plan.setDL(dl);		 
	}
	
	
    
	private ArrayList<ArrayList<Intersection>> solution2(){
	
            Intersection intersection1 = new Intersection(1,0,0,0);
            Intersection intersection2 = new Intersection(2,1,0,1);
            Intersection intersection3 = new Intersection(3,2,0,2);
            Troncon troncon12 = new Troncon("A",intersection1,intersection2,2);
            Troncon troncon13 = new Troncon("B",intersection1,intersection3,10);
            Troncon troncon23 = new Troncon("C",intersection2,intersection3,7);
            Troncon troncon21 = new Troncon("A",intersection2,intersection1,2);
            Troncon troncon31 = new Troncon("B",intersection3,intersection1,10);
            Troncon troncon32 = new Troncon("C",intersection3,intersection2,7);
            intersection1.addTroncon(troncon21);
            intersection1.addTroncon(troncon31);
            intersection2.addTroncon(troncon32);
            intersection2.addTroncon(troncon12);
            intersection3.addTroncon(troncon13);
            intersection3.addTroncon(troncon23);
            ArrayList<ArrayList<Intersection>> solution = new ArrayList<>();
            ArrayList<Intersection> result1 = new ArrayList<>();
            ArrayList<Intersection> result2 = new ArrayList<>();
            ArrayList<Intersection> result3 = new ArrayList<>();
            result1.add(intersection1);
            result1.add(intersection2);
            result1.add(intersection3);
            result2.add(intersection3);
            result2.add(intersection2);
            result3.add(intersection2);
            result3.add(intersection1);
            solution.add(result1);
            solution.add(result2);
            solution.add(result3);
            return solution;
		
	}
	
	
    private Plan initiale9point(){
        Intersection intersection1 = new Intersection(1,0,0,0);
        Intersection intersection2 = new Intersection(2,1,0,1);
        Intersection intersection3 = new Intersection(3,2,0,2);
        Intersection intersection4 = new Intersection(4,0,1,3);
        Intersection intersection5 = new Intersection(5,1,1,4);
        Intersection intersection6 = new Intersection(6,2,1,5);
        Intersection intersection7 = new Intersection(7,0,2,6);
        Intersection intersection8 = new Intersection(8,1,2,7);
        Intersection intersection9 = new Intersection(9,2,2,8);
      
        Troncon troncon21 = new Troncon("Avenue des Fr",intersection2,intersection1,1);
        Troncon troncon12 = new Troncon("Avenue des Fr",intersection1,intersection2,1);
        Troncon troncon32 = new Troncon("Boulevard Jean XXIII",intersection3,intersection2,1);
        Troncon troncon23 = new Troncon("Boulevard Jean XXIII",intersection2,intersection3,1);
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
        Troncon troncon14 = new Troncon("Place d'Arsonval",intersection1,intersection4,1);
        Troncon troncon25 = new Troncon("Cours Albert Thomas",intersection2,intersection5,1);
        Troncon troncon36 = new Troncon("Rue Professeur Louis Roche",intersection3,intersection6,1);
        Troncon troncon45 = new Troncon("Rue de Montbrillant",intersection4,intersection5,1);
        Troncon troncon56 = new Troncon("Rue Professeur Louis Roche",intersection5,intersection6,1);
        Troncon troncon47 = new Troncon("Rue Jeanne d'Arc",intersection4,intersection7,1);
        Troncon troncon78 = new Troncon("Rue Amiral Courbet",intersection7,intersection8,1);
        Troncon troncon89 = new Troncon("Rue Charles Richard",intersection8,intersection9,1);
        Troncon troncon58 = new Troncon("Rue Guy",intersection5,intersection8,1);
        Troncon troncon69 = new Troncon("Rue du Docteur Bonhomme",intersection6,intersection9,1);
        
        
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


  
//         Intersection intersection1 = new Intersection(1,0,0,0);
//        Intersection intersection2 = new Intersection(2,1,0,1);
//        Intersection intersection3 = new Intersection(3,2,0,2);
//	Intersection intersection3 = new Intersection(4,2,0,3);
//        Troncon troncon12 = new Troncon("A",intersection1,intersection2,2);
//        Troncon troncon13 = new Troncon("B",intersection1,intersection3,10);
//        Troncon troncon23 = new Troncon("C",intersection2,intersection3,7);
//        Troncon troncon21 = new Troncon("A",intersection2,intersection1,2);
//        Troncon troncon31 = new Troncon("B",intersection3,intersection1,10);
//        Troncon troncon32 = new Troncon("C",intersection3,intersection2,7);
//        intersection1.addTroncon(troncon21);
//        intersection1.addTroncon(troncon31);
//        intersection2.addTroncon(troncon32);
//        intersection2.addTroncon(troncon12);
//        intersection3.addTroncon(troncon13);
//        intersection3.addTroncon(troncon23);
//		ArrayList<ArrayList<Intersection>> expResult = new ArrayList<ArrayList<Intersection>>();
//		ArrayList<Intersection> result1 = new ArrayList<Intersection>();
//		result1.add(intersection1);
//		result1.add(intersection2);
//		result1.add(intersection3);
//		result2.add(intersection3);
//		result2.add(intersection2);
//		result2.add(intersection1);		
//		expResult.add(result1);
//		expResult.add(result2);

}
