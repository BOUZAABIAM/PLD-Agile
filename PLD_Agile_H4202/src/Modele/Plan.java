/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.*;
import tsp.*;
import java.sql.Time;

public class Plan {

    private Map<Long, Intersection> intersections;
    private List<Intersection> intersectionsList;
    private List<Livraison> livraisons;
    private Intersection entrepot;
    private List<int[]> pred;
    private List<ArrayList<Intersection>> solution2;
    private List<Time[]> tempsPassage;

    /**
     * Ajoute une intersection au plan
     *
     * @param intersection
     */
    private void addIntersection(Intersection intersection) {
        this.intersections.put(intersection.getId(), intersection);
        this.intersectionsList.add(intersection);
    }

    /**
     * Construit un plan à partir d'un Map d'intersection et d'une liste
     * d'intersection
     *
     * @param intersections les intersections ayant comme clé leur id
     * @param intersectionsList la liste des intersections ordonnée par l'index
     */
    public Plan(Map<Long, Intersection> intersections, List<Intersection> intersectionsList) {
        this.intersections = intersections;
        this.intersectionsList = intersectionsList;
    }

    /**
     * Obtenir les intersections sous forme de Map avec les IDs comme clés
     *
     * @return le Map d'intersections
     */
    public Map<Long, Intersection> getIntersectionsMap() {
        return intersections;
    }

    /**
     * Obtenir les intersections sous forme de liste ordonnée par index
     *
     * @return la liste d'intersections
     */
    public List<Intersection> getIntersectionsList() {
        return intersectionsList;
    }

    /**
     * Récupère l'intersection correspondant à l'id passé en paramètre
     *
     * @param idIntersection L'identifiant de l'intersection
     * @return La livraison sur cette adresse, si elle n'existe pas null
     */
    public Intersection getIntersection(long idIntersection) {
        return intersections.get(idIntersection);
    }

    /**
     * Fixe la demande de livraison sur le plan pour les eventuelles traitements
     *
     * @param dl la demande de livraison
     */
    public void setDL(DemandeLivraison dl){
        this.tempsPassage = new ArrayList<>();
        List<Livraison> livraisons  = new ArrayList<>();
        livraisons.addAll(dl.getLivraison().values());
        this.livraisons = livraisons;
        this.entrepot = dl.getEntrepot();
        this.pred = new ArrayList<>();
        Time[] tempsEntrepot = {dl.getHeureDepart(), dl.getHeureDepart()};
        this.tempsPassage.add(tempsEntrepot);
    }

    /**
     * Efface la demande de livraison de plan. Aucun calcul ulterieur n'est pas
     * disponible
     */
    public void deleteDL() {
        this.livraisons = null;
        this.entrepot = null;
        this.pred = null;
    }

    /**
     * Construit le graphe de livraison
     *
     * @return une matrice d'adjacence contenant la durée de parcours de la
     * distance entre les livraisons, entrepôt. Les livraisons sont dans l'ordre
     * de la liste de livraisons, l'entrepôt est à la fin
     */
    public int[][] graphLivraison() {
        Intersection[] intersectionsLivraisons = new Intersection[livraisons.size() + 1];
        int[][] matriceLivraison = new int[intersectionsLivraisons.length][intersectionsLivraisons.length];

        for (int i = 0; i < (intersectionsLivraisons.length - 1); i++) {
            intersectionsLivraisons[i] = livraisons.get(i).getAdresse();
        }

        intersectionsLivraisons[intersectionsLivraisons.length - 1] = entrepot;

        for (int i = 0; i < intersectionsLivraisons.length; i++) {
            int[] durees = calculDuree(intersectionsLivraisons[i], intersectionsLivraisons, Integer.MAX_VALUE);
            for (int j = 0; j < intersectionsLivraisons.length; j++) {
                matriceLivraison[i][j] = durees[j];
            }
        }

        return matriceLivraison;
    }

    /**
     * Calcule les durées pour parcourir la distance entre le point de depart et
     * les points de intersectionLivraison
     *
     * @param depart le point de depart
     * @param intersectionLivraison la liste des intersections jusq'aux quelles
     * on veut savoir la durée
     * @param position la position où il faut ajouter dans le tableau de
     * precedents, si Integer.MAX_VALUE - ajout simple
     * @return un array avec toutes les durées jusqu'au intersections de
     * intersectionLivraison
     */
    public int[] calculDuree(Intersection depart, Intersection[] intersectionLivraison, int position) {

        Intersection[] gris = new Intersection[intersectionsList.size()];
        int nbGris = 0;

        //Initialisation de graph (distances et couleurs)
        for (int i = 0; i < intersectionsList.size(); i++) {
            Intersection intersection = intersectionsList.get(i);
            if (intersection == depart) {
                depart.setD(0);
                gris[0] = depart;
                nbGris++;
                depart.setCouleur(1);
            } else {

                intersection.setD(Integer.MAX_VALUE);
                intersection.setCouleur(0);
            }
        }

        //Calcule de plus court chemi
        while (nbGris != 0) {
            // Determination de minimum gris
            int minDuree = Integer.MAX_VALUE;
            Intersection minValeur = depart;
            int posArray = 0;
            for (int i = 0; i < nbGris; i++) {
                if (gris[i].getD() < minDuree) {
                    minDuree = gris[i].getD();
                    minValeur = gris[i];
                    posArray = i;
                }
            }
            // Relacher le minimum de gris
            List<Intersection> nouveauGris = minValeur.relacherSucc();
            gris[posArray] = gris[nbGris - 1];
            gris[nbGris - 1] = null;
            nbGris--;
            for (Intersection intersection : nouveauGris) {
                gris[nbGris] = intersection;
                nbGris++;
            }

        }

        int[] d = new int[intersectionLivraison.length];
        for (int i = 0; i < intersectionLivraison.length; i++) {
            Intersection intersection = intersectionLivraison[i];
            d[i] = intersection.getD();
            //System.out.print(d[i] + "  ");
        }
        int[] pred = new int[intersectionsList.size()];
        for (int i = 0; i < intersectionsList.size(); i++) {
            pred[i] = intersectionsList.get(i).getPredIndex();
//            System.out.println(intersections.get(i).getPredIndex());
        }
        if (position == Integer.MAX_VALUE) {
            this.pred.add(pred);
        } else {
            this.pred.add(position, pred);
        }
        return d;
    }

    /**
     * Renvoie la liste d'intersections par lesquelles il faut passer pour aller
     * de l'intersection depart à l'intersection arrivée
     *
     * @param depart la position du depart dans la livraison dans la liste de
     * livraisons ou la longeur de la liste de livraisons pour l'entrepôt
     * @param arrive la position de l'arrivée dans livraison dans la liste de
     * livraisons ou la longeur de la liste de livraisons pour l'entrepôt
     * @return la liste d'intersections par lesquelles il faut passer pour aller
     * de l'intersection depart à l'intersection arrivée
     */
    public List<Intersection> getChemin(int depart, int arrive) {
        if ((depart >= 0) && (depart < livraisons.size() + 1) && (arrive >= 0) && (arrive < livraisons.size() + 1)) {
            Intersection intersectionDepart;
            Intersection intersectionArrive;

            if (depart == livraisons.size()) {
                intersectionDepart = entrepot;
            } else {
                intersectionDepart = livraisons.get(depart).getAdresse();
            }

            if (arrive == livraisons.size()) {
                intersectionArrive = entrepot;
            } else {
                intersectionArrive = livraisons.get(arrive).getAdresse();
            }
                                    
            LinkedList<Intersection> trajet = new LinkedList<>();
            
            int indexDepart = intersectionDepart.getIndex();
            
            trajet.add(intersectionArrive);

            int colonne = trajet.get(0).getIndex();
            while (pred.get(depart)[colonne] != indexDepart) {
                int predecessor = pred.get(depart)[colonne];
                Intersection intersectionPredecessor = intersectionsList.get(predecessor);
                trajet.addFirst(intersectionPredecessor);

                colonne = trajet.get(0).getIndex();
            }      
            
            trajet.addFirst(intersectionsList.get(pred.get(depart)[colonne]));            
            return trajet;
        } else {
            System.err.println("Attention, les nombres dans le getChemin ne correspondent pas avec les livraisons :");
            System.err.println("Dimension tableau livraison : " + (livraisons.size() + 1) + ", données fournies : " + depart + ", " + arrive);
            return null;
        }
    }

    /**
     * Renvoie les durees de livrisons de la liste de livraisons en cours
     *
     * @return les durees de livrisons de la liste de livraisons en cours
     */
    public int[] getDuree() {
        int nbSommet = livraisons.size() + 1;
        int[] duree = new int[nbSommet];
        for (int i = 0; i < nbSommet - 1; i++) {
            duree[i] = livraisons.get(i).getDuree();
        }
        duree[nbSommet - 1] = 0;
        return duree;
    }

    /**
     * Ajoute une livraison dans la liste de livraisons et recalcule la solution
     *
     * @param precedent l'intersection corespondante à la livraison qui va
     * préceder celle qu'on vais ajouter
     * @param livraisonAAjouter l'intersection à ajouter comme livraison
     * @return la nouvelle solution pour la tournée
     */
    public List<ArrayList<Intersection>> addLivraison(Intersection precedent, Intersection livraisonAAjouter){
        if (!this.adresseEnLivraison(livraisonAAjouter)){
        int[] indexes = new int[3];
        
        int positionPrecEnSolution = -1;
        ArrayList<Intersection> listePrec = new ArrayList<>();
        for (ArrayList list: this.solution2){
            if (list.get(0) == precedent){
                positionPrecEnSolution = this.solution2.indexOf(list);
                listePrec = list;
                break;
            }
            Intersection suivant = listePrec.get(listePrec.size() - 1);

            Intersection[] intersectionsCalcul = {livraisonAAjouter, precedent, suivant};
            int[] resultCalcul = this.calculDuree(livraisonAAjouter, intersectionsCalcul, pred.size() - 1);
            Livraison livraison = new Livraison(livraisonAAjouter, 500);
            livraisons.add(livraison);

            indexes[0] = livraisons.size() - 1;
            indexes[1] = this.getIndiceLivraisonParIntersection(precedent);
            indexes[2] = this.getIndiceLivraisonParIntersection(suivant);

            solution2.remove(positionPrecEnSolution);
            ArrayList<Intersection> etapes1 = new ArrayList();
            etapes1.addAll(this.getChemin(indexes[1], indexes[0]));
            solution2.add(positionPrecEnSolution, etapes1);
            ArrayList<Intersection> etapes2 = new ArrayList();
            etapes2.addAll(this.getChemin(indexes[0], indexes[2]));
            solution2.add(positionPrecEnSolution + 1, etapes2);

        } else {
            System.err.println("Adresse deja en livraison");
        }
        return solution2;
    }

    /**
     * Ajoute une livraison dans la liste de livraisons et recalcule la solution
     *
     * @param livraisonAEffacer
     * @return la nouvelle solution pour la tournée
     */
    public List<ArrayList<Intersection>> deleteLivraison(Intersection livraisonAEffacer) {
        if ((this.adresseEnLivraison(livraisonAEffacer)) || (livraisonAEffacer != entrepot)) {
            int[] indexes = new int[2];

            int positionEnSolution = -1;
            ArrayList<Intersection> listePrec = new ArrayList<Intersection>();
            for (ArrayList list : this.solution2) {
                if (list.get(0) == livraisonAEffacer) {
                    positionEnSolution = this.solution2.indexOf(list);
                    listePrec = list;
                    break;
                }
            }
            int positionLivraison = this.getIndiceLivraisonParIntersection(livraisonAEffacer);
            this.livraisons.remove(positionLivraison);

            Intersection precedent = solution2.get(positionEnSolution - 1).get(0);
            Intersection suivant = listePrec.get(listePrec.size() - 1);

            indexes[0] = this.getIndiceLivraisonParIntersection(precedent);
            indexes[1] = this.getIndiceLivraisonParIntersection(suivant);

            this.pred.remove(positionLivraison);

            this.solution2.remove(positionEnSolution - 1);
            this.solution2.remove(positionEnSolution - 1);
            if (indexes[0] != indexes[1]) {
                ArrayList<Intersection> etapes = new ArrayList();
                etapes.addAll(this.getChemin(indexes[0], indexes[1]));
                solution2.add(positionEnSolution - 1, etapes);
            }
        } else {
            System.err.println("Il n'y a pas une telle livraison ou cette adresse correspond a l'entrepot");
        }
        return solution2;
    }

    /**
     * Renvoie l'indice de la livraison correspondante à une intersection
     *
     * @param intersection
     * @return l'indice de la livraison correspondante à une intersection si
     * l'intersection corresponde à une livraison, la longeur de la liste de
     * livraisons si l'intersection correspond à l'entrepôt Integer.MAX_VALUE si
     * l'intersection ne corresponde à une livraison et n'est pas l'l'entrepôt
     */
    public int getIndiceLivraisonParIntersection(Intersection intersection) {
        int i = 0;

        boolean found = false;
        
        if((!livraisons.isEmpty()) && (livraisons.get(0).getAdresse() == intersection)){
            found = true;
        }
        while (!found) {
            i++;
            if (i < livraisons.size()) {
                if (livraisons.get(i).getAdresse() == intersection) {
                    found = true;
                }
            } else if (entrepot == intersection) {
                return livraisons.size();
            } else {
                System.err.println("Une des intersections donnees ne correspond pas a une livraison");
                return Integer.MAX_VALUE;
            }
        }
        return i;
    }

    /**
     * Renvoie la livraison correspondante à une intersection
     *
     * @param intersection
     * @return la livraison correspondante à l'intersection null si
     * l'intersection ne corresponde à aucune livraison
     */
    public Livraison getLivraisonParIntersection(Intersection intersection) {
        int indice = this.getIndiceLivraisonParIntersection(intersection);
        if (indice < livraisons.size()) {
            return livraisons.get(indice);
        } else if (indice == livraisons.size()) {
            System.err.println("Ce n'est pas une livraison, mais l'entrepot");
            return null;
        } else {
            System.err.println("Une des intersections donnees ne correspond pas a une livraison");
            return null;
        }
    }

    /**
     * Fait le calcule de tournée et initialise la solution
     */
    public void calculSolutionTSP1() {
        ArrayList<ArrayList<Intersection>> solution2 = new ArrayList<ArrayList<Intersection>>();
        long tempsDepartEntrepot = this.tempsPassage.get(0)[1].getTime();

        int tpsLimite = 100000000;
        int nbSommet = livraisons.size() + 1;
        TSP tsp = new TSP1();
        int[][] matrice = this.graphLivraison();
        int[][] plagesHoraires = new int[nbSommet][2];
        for (int i = 0; i < nbSommet - 1; i++) {
            if (livraisons.get(i).getDebutPlage() != null) {
                plagesHoraires[i][0] = (int) ((livraisons.get(i).getDebutPlage().getTime() - tempsDepartEntrepot) / 1000);
                if (livraisons.get(i).getFinPlage() != null) {
                    plagesHoraires[i][1] = (int) ((livraisons.get(i).getFinPlage().getTime() - tempsDepartEntrepot) / 1000);
                } else {
                    plagesHoraires[i][0] = -1;
                    plagesHoraires[i][1] = -1;
                }
            } else {
                plagesHoraires[i][0] = -1;
            }
        }
        tsp.chercheSolution(tpsLimite, nbSommet, matrice, this.getDuree(), plagesHoraires);
        
        //Obtenir la solution dans solution
        int[] solution = new int[nbSommet];
        for (int j = 0; j < nbSommet; j++) {
            solution[j] = tsp.getMeilleureSolution(j);
        }

        //Obtenir la solution en intersection
        
        for (int i = 1; i < nbSommet; i++){
           
            ArrayList<Intersection> etapes = new ArrayList();
            etapes.addAll(this.getChemin(solution[i-1], solution[i]));
            solution2.add(etapes);

            Time[] tempsNouvelleDestination = new Time[2];
            int[] tempsNoeud = tsp.getTempsPassage(solution[i]);
            tempsNouvelleDestination[0] = new Time(tempsNoeud[0] * 1000 + tempsDepartEntrepot);
            tempsNouvelleDestination[1] = new Time(tempsNoeud[1] * 1000 + tempsDepartEntrepot);
            this.tempsPassage.add(tempsNouvelleDestination);

        }

        ArrayList<Intersection> lastEtape = new ArrayList();
        lastEtape.addAll(this.getChemin(solution[nbSommet - 1], solution[0]));
        solution2.add(lastEtape);

        Time[] tempsNouvelleDestination = new Time[2];
        long tempsDepartPred = tempsPassage.get(tempsPassage.size() - 1)[1].getTime();
        long tempsChemin = matrice[solution[solution.length - 1]][matrice.length - 1];
        tempsNouvelleDestination[0] = new Time(tempsDepartPred + tempsChemin);
        tempsNouvelleDestination[1] = tempsNouvelleDestination[0];
        
        this.tempsPassage.add(tempsNouvelleDestination);
        this.solution2 = solution2;
    }

    public List<Time[]> getTempsPassage() {
        return tempsPassage;
    }

    public List<ArrayList<Intersection>> getSolution2() {
        return solution2;
    }

    /**
     * Verifie si une adresse correspond à une livraison
     *
     * @param intersection
     * @return true si elle correspond, false sinon
     */
    public boolean adresseEnLivraison(Intersection intersection) {
        boolean found = false;

        if ((livraisons.get(0).getAdresse() == intersection) || (entrepot == intersection)) {
            found = true;
        }
        int i = 0;
        while (!found) {
            i++;
            if (i < livraisons.size()) {
                if (livraisons.get(i).getAdresse() == intersection) {
                    found = true;
                }
            } else {
                break;
            }
        }
        return found;
    }

    /**
     * Renvoie l'intersection correspondante à l'adresse la livraison d'indice
     * index
     *
     * @param index
     * @return l'intersection correspondante à l'adresse la livraison d'indice
     * index
     */
    public Intersection getAdresseDeLivraison(int index) {
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
