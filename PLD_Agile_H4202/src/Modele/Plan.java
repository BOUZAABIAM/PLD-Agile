/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.*;
import tsp.TSP;
import tsp.TSP1;
import java.sql.Time;

public class Plan {

    private Map<Long, Intersection> intersections;
    private List<Intersection> intersectionsList;
    private List<Livraison> livraisons;
    private Intersection entrepot;
    private List<int[]> pred;
    private List<ArrayList<Intersection>> solution2;
    private List<Intersection> solution;
    private List<Intersection> chemin;
    private List<Time[]> tempsPassage;
   
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
        this.tempsPassage = new ArrayList<Time[]>();
        List<Livraison> livraisons  = new ArrayList<Livraison>();
        livraisons.addAll(dl.getLivraison().values());
        this.livraisons = livraisons;
        this.entrepot = dl.getEntrepot();
        this.pred = new ArrayList<int[]>();
        Time[] tempsEntrepot = {dl.getHeureDepart(), dl.getHeureDepart()};
        this.tempsPassage.add(tempsEntrepot);
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
            Intersection intersection = intersectionsList.get(i);
            if (intersection == depart){
                depart.setD(0);
                gris[0] = depart;
                nbGris++;
                depart.setCouleur(1);
            }
            else {
               
                intersection.setD(Integer.MAX_VALUE);
                intersection.setCouleur(0);
            } 
        }   
        
        //Calcule de plus court chemi
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
            gris[nbGris-1] = null;
            nbGris--;
            for (Intersection intersection: nouveauGris){
                gris[nbGris] = intersection;
                nbGris++;
            } 
         
        }
        
        int[] d = new int[intersectionLivraison.length];
        for (int i = 0; i < intersectionLivraison.length; i++){
            Intersection intersection = intersectionLivraison[i];
            d[i] = intersection.getD(); 
            //System.out.print(d[i] + "  ");
        } 
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
            
            int colonne=trajet.get(0).getIndex();
            while(pred.get(depart)[colonne]  != indexDepart){
                int predecessor = pred.get(depart)[colonne];
                Intersection intersectionPredecessor = intersectionsList.get(predecessor);
                trajet.addFirst(intersectionPredecessor);
               
                colonne = trajet.get(0).getIndex();
            }      
            
            trajet.addFirst(intersectionsList.get(pred.get(depart)[colonne]));
            colonne = trajet.get(0).getIndex();
//            System.out.println("Le chemin de " + indexDepart + "a " + indexArrive + " :");
            for (Intersection intersection: trajet){
//                System.out.print(intersection.getIndex() + "   ");
            }
//            System.out.println();
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
    public List<ArrayList<Intersection>> addLivraison(Intersection precedent, Intersection livraisonAAjouter){
        if (!this.adresseEnLivraison(livraisonAAjouter)){
        int[] indexes = new int[3];
        
        int positionPrecEnSolution = -1;
        ArrayList<Intersection> listePrec = new ArrayList<Intersection>();
        for (ArrayList list: this.solution2){
            if (list.get(0) == precedent){
                positionPrecEnSolution = this.solution2.indexOf(list);
                listePrec = list;
                break;
            }
        }
        Intersection suivant = listePrec.get(listePrec.size()-1);
        
        Intersection[] intersectionsCalcul = {livraisonAAjouter, precedent, suivant};
        int[] resultCalcul = this.calculDuree(livraisonAAjouter, intersectionsCalcul, pred.size()-1);             
        Livraison livraison = new Livraison(livraisonAAjouter, 500);
        livraisons.add(livraison);
        
        indexes[0] = livraisons.size()-1;
        indexes[1] = this.getIndiceLivraisonParIntersection(precedent);
        indexes[2] = this.getIndiceLivraisonParIntersection(suivant);
        
        
        
        solution2.remove(positionPrecEnSolution);
        ArrayList<Intersection> etapes1 = new ArrayList();
        etapes1.addAll(this.getChemin(indexes[1], indexes[0]));
        solution2.add(positionPrecEnSolution, etapes1);
        ArrayList<Intersection> etapes2 = new ArrayList();
        etapes2.addAll(this.getChemin(indexes[0], indexes[2]));
        solution2.add(positionPrecEnSolution+1, etapes2);
        
        } else {
            System.err.println("Adresse deja en livraison");
        }
        return solution2;
    }
    
    public List<ArrayList<Intersection>> deleteLivraison(Intersection livraisonAEffacer){
        if ((this.adresseEnLivraison(livraisonAEffacer)) || (livraisonAEffacer != entrepot)){
            int[] indexes = new int[2];
        
            int positionEnSolution = -1;
            ArrayList<Intersection> listePrec = new ArrayList<Intersection>();
            for (ArrayList list: this.solution2){
                if (list.get(0) == livraisonAEffacer){
                    positionEnSolution = this.solution2.indexOf(list);
                    listePrec = list;
                    break;
                }
            }
            int positionLivraison = this.getIndiceLivraisonParIntersection(livraisonAEffacer);
            this.livraisons.remove(positionLivraison);
            
            Intersection precedent = solution2.get(positionEnSolution-1).get(0);
            Intersection suivant = listePrec.get(listePrec.size()-1);
            
        
            indexes[0] = this.getIndiceLivraisonParIntersection(precedent);
            indexes[1] = this.getIndiceLivraisonParIntersection(suivant);
        
            this.pred.remove(positionLivraison);
        
            this.solution2.remove(positionEnSolution-1);
            this.solution2.remove(positionEnSolution-1);  
            if (indexes[0] != indexes[1]){
                ArrayList<Intersection> etapes = new ArrayList();
                etapes.addAll(this.getChemin(indexes[0], indexes[1]));
                solution2.add(positionEnSolution-1, etapes);
            }
        } else {
            System.err.println("Il n'y a pas une telle livraison ou cette adresse correspond a l'entrepot");
        }
        return solution2;
    }
    public int getIndiceLivraisonParIntersection(Intersection intersection){
        int i = 0;
        
        boolean found = false;
        
        if((livraisons.size() != 0) && (livraisons.get(0).getAdresse() == intersection)){
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

    public void calculSolutionTSP1(){
        ArrayList<ArrayList<Intersection>> solution2 = new ArrayList<ArrayList<Intersection>>();
    

        int tpsLimite = 100000000;
        int nbSommet = livraisons.size()+1;
        TSP tsp = new TSP1();
        int[][] matrice = this.graphLivraison();

        tsp.chercheSolution(tpsLimite, nbSommet, matrice , this.getDuree());
        
        //Obtenir la solution dans solution
        int[] solution = new int[nbSommet];
        for (int j = 0; j<nbSommet; j++){
            solution[j] = tsp.getMeilleureSolution(j);
        }
        
        //Bouger circulairement pour avoir l'entrepot au debut
        int entrep = 0;
        while(solution[entrep] != (nbSommet-1)){
            entrep++;
        }
        
        //Obtenir la solution en intersection
        Intersection[] sol = new Intersection[nbSommet];
        sol[0] = entrepot;
        int[] solutionPermut = new int[nbSommet];
        solutionPermut[0] = solution[entrep];
        
        for (int i = 1; i < nbSommet; i++){
            if ((entrep + i) < nbSommet){
                sol[i] = this.getAdresseDeLivraison(solution[entrep+i]);
                solutionPermut[i] = solution[entrep+i];
            } else {
                sol[i] = this.getAdresseDeLivraison(solution[entrep+i-nbSommet]);
                solutionPermut[i] = solution[entrep+i-nbSommet];
            }    
            ArrayList<Intersection> etapes = new ArrayList();

            etapes.addAll(this.getChemin(solutionPermut[i-1], solutionPermut[i]));
            solution2.add(etapes);
            
            Time[] tempsNouvelleDestination = new Time[2];
            long tempsDepartPred = tempsPassage.get(i-1)[1].getTime();
            long tempsChemin = matrice[solutionPermut[i-1]][solutionPermut[i]];
            tempsNouvelleDestination[0] = new Time(tempsDepartPred + tempsChemin);
            tempsNouvelleDestination[1] = new Time(tempsNouvelleDestination[0].getTime() + this.livraisons.get(solutionPermut[i]).getDuree()*1000);
            this.tempsPassage.add(tempsNouvelleDestination);
            
        }   
        
        ArrayList<Intersection> lastEtape = new ArrayList();
        lastEtape.addAll(this.getChemin(solutionPermut[nbSommet - 1], solutionPermut[0]));
        solution2.add(lastEtape);
        
        Time[] tempsNouvelleDestination = new Time[2];
        long tempsDepartPred = tempsPassage.get(tempsPassage.size()-1)[1].getTime();
        long tempsChemin = matrice[solutionPermut[solutionPermut.length-1]][matrice.length-1];
        tempsNouvelleDestination[0] = new Time(tempsDepartPred + tempsChemin);
        tempsNouvelleDestination[1] = tempsNouvelleDestination[0];
        this.tempsPassage.add(tempsNouvelleDestination);
        this.solution2 = solution2;
    }

    public List<Time[]> getTempsPassage() {
        return tempsPassage;
    }
    
    
    
    public List<Intersection> getSolution() {
        return solution;
    }
    
    public List<ArrayList<Intersection>> getSolution2() {
        return solution2;
    }

    public List<Intersection> getChemin() {
        return chemin;
    }
    
    public boolean adresseEnLivraison(Intersection intersection){
        boolean found = false;
        
        if((livraisons.get(0).getAdresse() == intersection) || (entrepot == intersection)){
            found = true;
        } 
        int i = 0;
        while(!found){
            i++;
            if (i < livraisons.size()){
                if (livraisons.get(i).getAdresse() == intersection){
                    found = true;
                }
            } else {
                break;
            }
        }
        return found;
    }
    
    public Intersection getAdresseDeLivraison(int index){
        return livraisons.get(index).getAdresse();
    }
    @Override
    public String toString() {
        return "Plan{" + "intersections=" + intersections + '}';
    }

    public List<Livraison> getLivraisons() {
        return livraisons;
    }
    
}
