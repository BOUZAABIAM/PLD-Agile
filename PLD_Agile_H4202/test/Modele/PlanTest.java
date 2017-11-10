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
        Plan instance = this.initiale4point();
        setDL2(instance,instance.getIntersectionsList().get(0),instance.getIntersectionsList().get(1),instance.getIntersectionsList().get(2));
        int[][] expResult = {{0,1,0},{1,0,1},{0,1,0}};
        int[][] result = instance.graphLivraison();        
        assertArrayEquals(expResult, result);      
    }



    /**
     * Test of getChemin method, of class Plan.
     */
    @Test
    public void testGetChemin_int_int() {
        System.out.println("getChemin");        
        
        //test le plus court chemin entre 2 intersection1 et intersection3 de plan de 4 point
        // il passe selon chemin intersection3 intersection2 intersection1 
        Plan instance = this.initiale4point();            
        List<Intersection> expResult = new LinkedList<Intersection>();
        expResult.add(instance.getIntersectionsList().get(2));
        expResult.add(instance.getIntersectionsList().get(1));
        expResult.add(instance.getIntersectionsList().get(0));
        setDL2(instance,instance.getIntersectionsList().get(0),instance.getIntersectionsList().get(1),instance.getIntersectionsList().get(2));
        instance.calculSolutionTSP1();
        List<Intersection> result = instance.getChemin(1,2);

        assertEquals(expResult.toString(), result.toString());
        // test le plus court chemin entre 2 intersection1 et intersection4 de plan de 4 point
        // il n'y a pas de chemin entre intersection1 et intersection4 donc renvoyer'nul'
        Plan instance2 = this.initiale4point();
        setDL3(instance2,instance2.getIntersectionsList().get(0),instance2.getIntersectionsList().get(1),instance2.getIntersectionsList().get(2),instance2.getIntersectionsList().get(3));
        List<Intersection> result2 = instance.getChemin(1,3);
        assertNull(result2);
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
    }

    /**
     * Test of addLivraison method, of class Plan.
     */
    @Test
    public void testAddLivraison() {
        System.out.println("addLivraison"); 
        Plan plan = this.initiale4point();     
	Plan plan2 = this.initiale4point();         
        setDL1(plan,plan.getIntersectionsList().get(0),plan.getIntersectionsList().get(1));
	setDL2(plan2,plan2.getIntersectionsList().get(0),plan2.getIntersectionsList().get(1),plan2.getIntersectionsList().get(2));
        // ajouter intersection1 qui n'est pas un livraison
        plan.calculSolutionTSP1();
        plan2.calculSolutionTSP1();
        plan.addLivraison(plan.getIntersectionsList().get(1),plan.getIntersectionsList().get(2));
        plan.calculSolutionTSP1();
	assertEquals(plan.getSolution2().toString(), plan2.getSolution2().toString());
		
	// ajouter intersection1 qui est deja en Livraison
        Plan plan3 = this.initiale4point();
	setDL2(plan3,plan3.getIntersectionsList().get(0),plan3.getIntersectionsList().get(1),plan3.getIntersectionsList().get(2));		
        plan3.calculSolutionTSP1();
        plan3.addLivraison(plan3.getIntersectionsList().get(1),plan3.getIntersectionsList().get(2));
        assertEquals(plan.getSolution2().toString(), plan3.getSolution2().toString());      
	
        
    }
	
    /**
     * Test of deleteLivraison method, of class Plan.
     */
    @Test
    public void testDeleteLivraison() {
        System.out.println("deleteLivraison");
        Plan planexpResult = this.initiale4point();     
	Plan planResult = this.initiale4point();         
        setDL1(planexpResult,planexpResult.getIntersectionsList().get(0),planexpResult.getIntersectionsList().get(1));
	setDL2(planResult,planResult.getIntersectionsList().get(0),planResult.getIntersectionsList().get(1),planResult.getIntersectionsList().get(2));		
        planResult.calculSolutionTSP1();
        planexpResult.calculSolutionTSP1();
        // supprimer intersection 3 qui est adress d'un livraison
        planResult.deleteLivraison(planResult.getIntersectionsList().get(2));
	assertEquals(planexpResult.getSolution2().toString(), planResult.getSolution2().toString());
	 //suprimer intersection qui n'est pas un livraison
	planResult.deleteLivraison(planResult.getIntersectionsList().get(3));
	assertEquals(planexpResult.getSolution2().toString(), planResult.getSolution2().toString());
	// suprimer entrepot
	planResult.deleteLivraison(planResult.getIntersectionsList().get(0));
	assertEquals(planexpResult.getSolution2().toString(), planResult.getSolution2().toString());
      
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
	// intersection est une adrsse de livraison
        int result = instance.getIndiceLivraisonParIntersection(intersection5);        
        assertEquals(expResult, result);
        // intersection n'est pas une adresse de livraison
        int result2 = instance.getIndiceLivraisonParIntersection(instance.getIntersectionsList().get(2)); 
        assertEquals(Integer.MAX_VALUE, result2);
        // intersection est entrepot
        int result3 = instance.getIndiceLivraisonParIntersection(entrepot); 
        assertEquals(2, result3);
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
        // intersention est un livraison
        assertEquals(livraison5, instance.getLivraisonParIntersection(intersection5));
        // intersection est pas un livraison
        assertNull(instance.getLivraisonParIntersection(entrepot));
        //intersection est entrepot
        assertNull(instance.getLivraisonParIntersection(instance.getIntersectionsList().get(2)));
    }
	
	@Test 
	/*
	 * testcalculDuree
	 */
    public void testcalculDuree(){
	Plan instance = this.initiale4point();
        Intersection[] intersections = new Intersection[3];
        intersections[0]=instance.getIntersectionsList().get(0);
        intersections[1]=instance.getIntersectionsList().get(1);
        intersections[2]=instance.getIntersectionsList().get(2);
	int[] expResult = new int[3];
        expResult[0]=0;
        expResult[1]=0;
        expResult[2]=1;
	int[] result = null;
        setDL2(instance,instance.getIntersectionsList().get(0),instance.getIntersectionsList().get(1),instance.getIntersectionsList().get(2));        
        result= instance.calculDuree(intersections[0],intersections, Integer.MAX_VALUE);
        Assert.assertArrayEquals(expResult,result);
    }
	
	
	
	
    /**
     * Test of adresseEnLivraison method, of class Plan.
     * 
     */
    @Test
    public void testAdresseEnLivraison() {
        System.out.println("adresseEnLivraison");        
        Plan instance = this.initiale4point();
	setDL2(instance,instance.getIntersectionsList().get(0),instance.getIntersectionsList().get(1),instance.getIntersectionsList().get(2));
        // intersection n'est pas en livraion renvoyer 'false'
	assertEquals(false, instance.adresseEnLivraison(instance.getIntersectionsList().get(3)));        
         // intersection est en livraion renvoyer 'true'
        assertEquals(true, instance.adresseEnLivraison(instance.getIntersectionsList().get(1)));
    }
	
    /**
     * Test of calculSolutionTSP1 method, of class Plan.
     */
    
    @Test
    public void testCalculSolutionTSP1() {
        System.out.println("calculSolutionTSP1");
        
        // test en cas normal 
        Plan instance = this.initiale4point();       
	setDL2(instance,instance.getIntersectionsList().get(0),instance.getIntersectionsList().get(1),instance.getIntersectionsList().get(2));
	ArrayList<ArrayList<Intersection>> expResult = this.solution2();
        instance.calculSolutionTSP1();  
        assertEquals(expResult.toString(), instance.getSolution2().toString());
        
        
        // en cas de il n'y a pas le chemin pour aller a intersection4
       Plan instance2 = this.initiale4point();
       setDL3(instance2,instance2.getIntersectionsList().get(0),instance2.getIntersectionsList().get(1),instance2.getIntersectionsList().get(2),instance2.getIntersectionsList().get(3));        
       instance2.calculSolutionTSP1();  
       assertEquals(expResult.toString(), instance2.getSolution2().toString());
    }
    

   
     /**
     * initiale le Plan avec 4 intersection de 1 à 4
     * intersection 1,2,3 sont liés:
     * intersection1 <-> intersection2 = 2
     * intersection2 <-> intersection3 = 7
     * intersection3 <-> intersection1 = 10
     * intersection 4 est isolé , il y a de chemin pour aller à intersection 4
     */
    
    private Plan initiale4point(){
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
     /**
     * @Param plan 
     * @Param entrepot  
     * @Param intersection1: adresse de livraison 1
     * @Param intersection2: adresse de livraison 2
     * @Param intersection3: adresse de livraison 3
     * charger sur plan liste de livraisons qui contient entrepot 3 livraisons 
     */
    private void setDL3(Plan plan,Intersection entrepot, Intersection intersection1,Intersection intersection2,Intersection intersection3){
		Map<Long, Livraison> livraisons = new TreeMap<Long, Livraison>();
      
                Livraison livraison1 = new Livraison(intersection1,900);
                Livraison livraison2 = new Livraison(intersection2,600);
                Livraison livraison3 = new Livraison(intersection3,500);
                livraisons.put((long)2, livraison1);
                livraisons.put((long)3, livraison2);
                livraisons.put((long)4, livraison3);
                Time heureDepart = Time.valueOf("8:0:0");
                DemandeLivraison dl = new DemandeLivraison(entrepot,heureDepart,livraisons);
		plan.setDL(dl);
		 
	}
      /**
     * @Param plan 
     * @Param entrepot  
     * @Param intersection1: adresse de livraison 1
     * @Param intersection2: adresse de livraison 2    
     * charger sur plan liste de livraisons qui contient entrepot 2 livraisons 
     */
	private void setDL2(Plan plan,Intersection entrepot, Intersection intersection1,Intersection intersection2){
		Map<Long, Livraison> livraisons = new TreeMap<Long, Livraison>();
      
                Livraison livraison1 = new Livraison(intersection1,900);
                Livraison livraison2 = new Livraison(intersection2,600);
  
                livraisons.put((long)2, livraison1);
                livraisons.put((long)3, livraison2);
                Time heureDepart = Time.valueOf("8:0:0");
                DemandeLivraison dl = new DemandeLivraison(entrepot,heureDepart,livraisons);
		plan.setDL(dl);
		 
	}
    /**
     * @Param plan 
     * @Param entrepot  
     * @Param intersection1: adresse de livraison 1     
     * charger sur plan liste de livraisons qui contient entrepot 2 livraisons 
     */
	private void setDL1(Plan plan,Intersection entrepot, Intersection intersection1){
		Map<Long, Livraison> livraisons = new TreeMap<Long, Livraison>();      
                Livraison livraison1 = new Livraison(intersection1,600);       
                livraisons.put((long)1, livraison1);
                Time heureDepart = Time.valueOf("8:0:0");
                DemandeLivraison dl = new DemandeLivraison(entrepot,heureDepart,livraisons);
		plan.setDL(dl);		 
	}
	
    
    /**
     * chemin de livraison pour le plan de 4 point 
     * Avec:
     * intersection1: entrêpot 
     * intersection2: livraison 0
     * intersection3: livraison 1
     */
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
	
    /**
     * initiale le Plan avec 9 intersection de 1 à 9
     * il n'a pas de intersection isolant
     */
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

}
