/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.*;

public class Plan {

    private Map<Long, Intersection> intersections;
    private List<Intersection> intersectionsList;
    private List<Livraison> livraisons;
    private Intersection entrepot;
    private List<int[]> pred;
   

    private void addIntersection(Intersection intersection) {
        this.intersections.put(intersection.getId(), intersection);
        this.intersectionsList.add(intersection);
    }

    public Plan(Map<Long, Intersection> intersections, List<Intersection> intersectionsList) {
        this.intersections = intersections;
        this.intersectionsList = intersectionsList;
    }

    
    public Map<Long, Intersection> getIntersectionsMap() {
        return intersections;
    }

    public List<Intersection> getIntersectionsList() {
        return intersectionsList;
    }

    public void setDL(DemandeLivraison dl){
        List<Livraison> livraisons  = new ArrayList<Livraison>();
        livraisons.addAll(dl.getLivraison().values());
        this.livraisons = livraisons;
        this.entrepot = dl.getEntrepot();
        this.pred = new ArrayList<int[]>();
    }
    
    public void deleteDL(){
        this.livraisons = null;
        this.entrepot = null;
        this.pred = null;
    }
    
    public int[][] graphLivraison(){
       Intersection[] intersectionsLivraisons = new Intersection[livraisons.size()+1];
       int[][] matriceLivraison = new int[intersectionsLivraisons.length][intersectionsLivraisons.length];
       
      
       for (int i = 0; i < (intersectionsLivraisons.length-1); i++){
           intersectionsLivraisons[i] = livraisons.get(i).getAdresse();
       }
       
       intersectionsLivraisons[intersectionsLivraisons.length-1] = entrepot;
       
       for (int i = 0; i < intersectionsLivraisons.length; i++){
           int[] durees = calculDuree(intersectionsLivraisons[i], intersectionsLivraisons, Integer.MAX_VALUE) ; 
           for (int j = 0; j < intersectionsLivraisons.length; j++){
               matriceLivraison[i][j] = durees[j];
           }
       }
       
       return matriceLivraison;
    } 
    /**
     * 
     * @param depart
     * @param intersectionLivraison
     * @param position la position ou il faut ajouter dans le tableau de precedents, si Integer.MAX_VALUE - ajout simple
     * @return un array avec toutes les durees jusqu'au intersections de intersectionLivraison
     */
    private int[] calculDuree(Intersection depart, Intersection[] intersectionLivraison, int position){
        
        Intersection[] gris = new Intersection[intersectionsList.size()];
        int nbGris = 0;
        
        //Initialisation de graph (distances et couleurs)
        for (int i = 0; i < intersectionsList.size(); i++){
            if (intersectionsList.get(i) == depart){
                depart.setD(0);
                gris[0] = depart;
                nbGris++;
                depart.setCouleur(1);
            }
            else {
                intersectionsList.get(i).setD(Integer.MAX_VALUE);
                intersectionsList.get(i).setCouleur(0);
            } 
        }   
        
        //Calcule de plus court chemin
        while (nbGris != 0){
            // Determination de minimum gris
            int minDuree = Integer.MAX_VALUE;
            Intersection minValeur = depart;
            int posArray = 0;
            for (int i = 0; i < nbGris; i++){
                if(gris[i].getD() < minDuree){
                    minDuree = gris[i].getD();
                    minValeur = gris[i];
                    posArray = i;
                }
            }
            // Relacher le minimum de gris
            List<Intersection> nouveauGris = minValeur.relacherSucc();
            gris[posArray] = gris[nbGris-1];
            nbGris--;
            for (Intersection intersection: nouveauGris){
                gris[nbGris] = intersection;
                nbGris++;
            }           
            
        }
        
        int[] d = new int[intersectionLivraison.length];
        for (int i = 0; i < intersectionLivraison.length; i++){
            d[i] = intersectionLivraison[i].getD(); 
            //System.out.print(d[i] + "  ");
        } 
        //System.out.println();
        int[] pred = new int[intersectionsList.size()];
        for (int i = 0; i < intersectionsList.size(); i++){
            pred[i] = intersectionsList.get(i).getPredIndex();
//            System.out.println(intersections.get(i).getPredIndex());
        }
        if(position == Integer.MAX_VALUE){
            this.pred.add(pred);
        } else {
            this.pred.add(position, pred);
        }
//        this.affichePrec();
        return d;
    }
    
      
    public void affichePrec(){
        for (int i = 0; i < pred.size(); i++){
            for (int j = 0; j < pred.get(i).length; j++){
                System.out.print(pred.get(i)[j] + "  ");
            }
            //System.out.println();
        }
        //System.out.println();
    }
    
    public List<Intersection> getChemin(int depart, int arrive){
        //this.affichePrec();
        if ((depart >= 0) && (depart < livraisons.size()+1) && (arrive >= 0) && (arrive < livraisons.size()+1)){
            Intersection intersectionDepart;
            Intersection intersectionArrive;
            
            if (depart == livraisons.size()){
                intersectionDepart = entrepot;
            } else {
                intersectionDepart = livraisons.get(depart).getAdresse();
            }
            
            if (arrive == livraisons.size()){
                intersectionArrive = entrepot;
            } else {                
                intersectionArrive = livraisons.get(arrive).getAdresse();
            }
                                    
            LinkedList<Intersection> trajet = new LinkedList<Intersection>();
            
            int indexDepart = intersectionDepart.getIndex();
            int indexArrive = intersectionArrive.getIndex();
            
            trajet.add(intersectionArrive);
//            System.out.println(trajet);
//            System.out.println("Depart " + depart);
//            System.out.println("Arrive " + arrive);
//            System.out.println("Index Depart" + indexDepart);
//            System.out.println("Index Arrive" + indexArrive);
            
            int colonne=trajet.get(0).getIndex();
            while(pred.get(depart)[colonne]  != indexDepart){             
//                System.out.println("Dernier intersection ajoute " + colonne);                
//                System.out.println();
                
                trajet.addFirst(intersectionsList.get(pred.get(depart)[colonne]));
                colonne = trajet.get(0).getIndex();
            }      
            
            trajet.addFirst(intersectionsList.get(pred.get(depart)[colonne]));
            colonne = trajet.get(0).getIndex();
            trajet.add(intersectionArrive);
            return trajet;
            
        } else {
            System.err.println("Attention, les nombres dans le getChemin ne correspondent pas avec les livraisons :");
            System.err.println("Dimension tableau livraison : " + (livraisons.size()+1) + ", données fournies : " + depart + ", " + arrive);
            return null;
        }
    }
    
    public int[] getDuree(){
        int nbSommet = livraisons.size()+1;
        int[] duree = new int[nbSommet];
        for (int i=0; i<nbSommet-1; i++){
            duree[i] = livraisons.get(i).getDuree();
        }
        duree[nbSommet-1] = 0;
        return duree;
    }
    
    /**
     * 
     * @param precedent, l'intersection de la livraison qui precede celle a ajouter
     * @param suivant, l'intersection de la livraison qui suit celle a ajouter
     * @param livraisonAAjouter, l'intersection de la livraison a ajouter
     * @return un tableau avec 2 lignes et trois colonnes. 
     * La premiere ligne a les indices dans la liste de livraisons de livraison a ajouter, livraison precent et livraison suivant.
     * La deuxieme ligne donne la duree entre les livraisons
     */
    public int[][] addLivraison(Intersection precedent, Intersection suivant, Intersection livraisonAAjouter){
        
        int[][] result = new int[2][3];
        
        Intersection[] intersectionsCalcul = {livraisonAAjouter, precedent, suivant};
        int[] resultCalcul = this.calculDuree(livraisonAAjouter, intersectionsCalcul, pred.size()-1);
        for (int i = 0; i < 3; i++){
            result[1][i] = resultCalcul[i];
        }        
        Livraison livraison = new Livraison(livraisonAAjouter, 500);
        livraisons.add(livraison);
        result[0][0] = livraisons.size();
        result[0][1] = this.getIndiceLivraisonParIntersection(precedent);
        result[0][2] = this.getIndiceLivraisonParIntersection(suivant);
        
        return result;
    }
    
    public int getIndiceLivraisonParIntersection(Intersection intersection){
        int i = 0;
        
        boolean found = false;
        
        if(livraisons.get(0).getAdresse() == intersection){
            found = true;
        }
        while(!found){
            i++;
            if (i < livraisons.size()){
                if (livraisons.get(i).getAdresse() == intersection){
                    found = true;
                }
            } else if(entrepot == intersection) {
                return livraisons.size();
            } else {
                System.err.println("Une des intersections donnees ne correspond pas a une livraison");
                return Integer.MAX_VALUE;
            }
           
        }
        return i;
    }
    
    public Livraison getLivraisonParIntersection(Intersection intersection) {
        int indice = this.getIndiceLivraisonParIntersection(intersection);
        if (indice < livraisons.size()){
            return livraisons.get(indice);
        } else if(indice == livraisons.size()) {
            System.err.println("Ce n'est pas une livraison, mais l'entrepot");
            return null;
        } else {
            System.err.println("Une des intersections donnees ne correspond pas a une livraison");
            return null;
        }
    }
    
    public Intersection getAdresseDeLivraison(int index){
        return livraisons.get(index).getAdresse();
    }
    @Override
    public String toString() {
        return "Plan{" + "intersections=" + intersections + '}';
    }

}
