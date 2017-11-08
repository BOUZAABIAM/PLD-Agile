package tsp;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class TemplateTSP implements TSP {
	
	private Integer[] meilleureSolution;
	private int coutMeilleureSolution = 0;
	private Boolean tempsLimiteAtteint;
        private int[][] tempsPassage;
        private int[][] plagesHoraires;
	
	public Boolean getTempsLimiteAtteint(){
		return tempsLimiteAtteint;
	}
	
	public void chercheSolution(int tpsLimite, int nbSommets, int[][] cout, int[] duree, int[][] plagesHoraires){
                this.plagesHoraires = plagesHoraires;
                this.tempsPassage = new int[nbSommets][2];
		tempsLimiteAtteint = false;
		coutMeilleureSolution = Integer.MAX_VALUE;
		meilleureSolution = new Integer[nbSommets];
                ArrayList<Integer> nonVus = new ArrayList<Integer>();
		for (int i=0; i<nbSommets-1; i++) nonVus.add(i);
		ArrayList<Integer> vus = new ArrayList<Integer>(nbSommets);
		vus.add(nbSommets-1); // le premier sommet visite est 0
                
                int[][] tmpTempsPassage = new int[nbSommets][2];
                tmpTempsPassage[nbSommets-1][0] = 0;
                tmpTempsPassage[nbSommets-1][1] = 0;
		branchAndBound(nbSommets-1, nonVus, vus, 0, cout, duree, System.currentTimeMillis(), tpsLimite, tmpTempsPassage);
	
	}
	
	public Integer getMeilleureSolution(int i){
		if ((meilleureSolution == null) || (i<0) || (i>=meilleureSolution.length))
			return null;
		return meilleureSolution[i];
	}
	
	public int getCoutMeilleureSolution(){
		return coutMeilleureSolution;
	}
	
	/**
	 * Methode devant etre redefinie par les sous-classes de TemplateTSP
	 * @param sommetCourant
	 * @param nonVus : tableau des sommets restant a visiter
	 * @param cout : cout[i][j] = duree pour aller de i a j, avec 0 <= i < nbSommets et 0 <= j < nbSommets
	 * @param duree : duree[i] = duree pour visiter le sommet i, avec 0 <= i < nbSommets
	 * @return une borne inferieure du cout des permutations commencant par sommetCourant, 
	 * contenant chaque sommet de nonVus exactement une fois et terminant par le sommet 0
	 */
	protected abstract int bound(Integer sommetCourant, ArrayList<Integer> nonVus, int[][] cout, int[] duree);
	
	/**
	 * Methode devant etre redefinie par les sous-classes de TemplateTSP
	 * @param sommetCrt
	 * @param nonVus : tableau des sommets restant a visiter
	 * @param cout : cout[i][j] = duree pour aller de i a j, avec 0 <= i < nbSommets et 0 <= j < nbSommets
	 * @param duree : duree[i] = duree pour visiter le sommet i, avec 0 <= i < nbSommets
	 * @return un iterateur permettant d'iterer sur tous les sommets de nonVus
	 */
	protected abstract Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, int[][] cout, int[] duree);
	
	/**
	 * Methode definissant le patron (template) d'une resolution par separation et evaluation (branch and bound) du TSP
	 * @param sommetCrt le dernier sommet visite
	 * @param nonVus la liste des sommets qui n'ont pas encore ete visites
	 * @param vus la liste des sommets visites (y compris sommetCrt)
	 * @param coutVus la somme des couts des arcs du chemin passant par tous les sommets de vus + la somme des duree des sommets de vus
	 * @param cout : cout[i][j] = duree pour aller de i a j, avec 0 <= i < nbSommets et 0 <= j < nbSommets
	 * @param duree : duree[i] = duree pour visiter le sommet i, avec 0 <= i < nbSommets
	 * @param tpsDebut : moment ou la resolution a commence
	 * @param tpsLimite : limite de temps pour la resolution
	 */	
	 void branchAndBound(int sommetCrt, ArrayList<Integer> nonVus, ArrayList<Integer> vus, int coutVus, int[][] cout, int[] duree, long tpsDebut, int tpsLimite, int[][] tmpTempsPassage){
		 if (System.currentTimeMillis() - tpsDebut > tpsLimite){
			 tempsLimiteAtteint = true;
			 return;
		 }
	    if (nonVus.size() == 0){ // tous les sommets ont ete visites
	    	coutVus += cout[sommetCrt][cout.length-1];
	    	if (coutVus < coutMeilleureSolution){ // on a trouve une solution meilleure que meilleureSolution
	    		vus.toArray(meilleureSolution);
	    		coutMeilleureSolution = coutVus;
                        for (int i = 0; i < tempsPassage.length; i++){
                            tempsPassage[i][0] = tmpTempsPassage[i][0];
                            tempsPassage[i][1] = tmpTempsPassage[i][1];
                        }
	    	}
	    } else if (coutVus + bound(sommetCrt, nonVus, cout, duree) < coutMeilleureSolution){
                
	        Iterator<Integer> it = iterator(sommetCrt, nonVus, cout, duree);
	        while (it.hasNext()){
	        	Integer prochainSommet = it.next();
	        	vus.add(prochainSommet);                        
	        	nonVus.remove(prochainSommet);
                        tmpTempsPassage[prochainSommet][0] = tmpTempsPassage[sommetCrt][1] + cout[sommetCrt][prochainSommet];
                        tmpTempsPassage[prochainSommet][1] = tmpTempsPassage[prochainSommet][0] + duree[prochainSommet];
 
                        
                        if (plagesHoraires[prochainSommet][0] == -1){
                            branchAndBound(prochainSommet, nonVus, vus, coutVus + cout[sommetCrt][prochainSommet] + duree[prochainSommet], cout, duree, tpsDebut, tpsLimite, tmpTempsPassage);
                        } else if(plagesHoraires[prochainSommet][1] < tmpTempsPassage[prochainSommet][0]){
                            vus.remove(prochainSommet);
                            nonVus.add(prochainSommet);
                            break;
                        } else {
                            if (plagesHoraires[prochainSommet][0] > tmpTempsPassage[prochainSommet][0]){    
                                int calcul = plagesHoraires[prochainSommet][0] - tmpTempsPassage[prochainSommet][0];
                                tmpTempsPassage[prochainSommet][0] = plagesHoraires[prochainSommet][0];
                                tmpTempsPassage[prochainSommet][1] = plagesHoraires[prochainSommet][0] + duree[prochainSommet];
                                branchAndBound(prochainSommet, nonVus, vus, coutVus + cout[sommetCrt][prochainSommet] + duree[prochainSommet] + calcul, cout, duree, tpsDebut, tpsLimite, tmpTempsPassage);
                            } else {
                                 branchAndBound(prochainSommet, nonVus, vus, coutVus + cout[sommetCrt][prochainSommet] + duree[prochainSommet], cout, duree, tpsDebut, tpsLimite, tmpTempsPassage);
                            }
                        }
	        	vus.remove(prochainSommet);
	        	nonVus.add(prochainSommet);
	        }	    
	    }
	}
         
        public int[] getTempsPassage(int i){
            int[] tempsPassage = new int[2];
            tempsPassage[0] = this.tempsPassage[i][0];
            tempsPassage[1] = this.tempsPassage[i][1];
            
            return tempsPassage;
        };
}

