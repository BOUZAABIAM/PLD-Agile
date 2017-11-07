/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.DemandeLivraison;
import Modele.Livraison;
import Modele.Plan;
import Modele.Intersection;
import Modele.Troncon;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import controleur.ControleurInterface;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Ellipse;
import javafx.util.Pair;

/**
 *
 * @author epetit
 */
public class JPanelPlan extends JPanel {

    private Plan lePlan;
    private DemandeLivraison laDL;
    private java.util.List<ArrayList<Intersection>> laSolution;
    
    public void setPlan(Plan lePlan) {
        this.lePlan = lePlan;
    }

    public void setDL(DemandeLivraison laDL) {
        this.laDL = laDL;
    }

    public void setSolution(java.util.List<ArrayList<Intersection>> solution) {
        this.laSolution = solution;
    }
    
    /** La liste des livraisons */
    private java.util.List<Livraison> livraisons = null;
     /** Le contrôleur de l'application */
    private ControleurInterface controleurApplication;
    /**
     * Contient tous les points graphiques actuellement afficher pour le plan
     * grâce à leur id, et pour chaque intersection, ses arcs graphiques ainsi
     * que l'intersection ciblée
     */
    private Map<Long, Pair<Ellipse, Collection<Long>>> intersectionsGraphiques = null;
    /**
     * Liste des id des livraisons récupéreés après avoir chargé la demande, associés au numéro de la fenêtre
     */
    private Map<Long, Long> listeIdLivraison;
    /**
     * Identifiant (adresse) de l'intersection où se situe l'entrepot
     */
    private long entrepot = -1;
    /**
     * Le canvas graphique sur lequel on dessinera les éléments graphiques
     */
    private StackPane canvas;
    
    
    /**
     * Constructeur de la vue graphique
     *
     * @param canvas Le canvas sur lequel on dessinera les éléments graphiques
     * @param group Le group de la partie graphique
     * @param scrollPane La barre de défilement
     * @param slider Le slide de zoom
     */
//    public JPanelPlan(StackPane canvas) {
//        this.canvas = canvas;
//        //this.group = group;
//        //this.scrollPane = scrollPane;
//        //this.sliderZoom = slider;
//        //activerZoom();
//       
//        canvas.setOnMouseMoved(new HoverGraphiqueGestionnaireEvenement());
//        canvas.setOnMouseClicked(new ClicGraphiqueGestionnaireEvenement());
//        
//    }
    
     /**
     * Retourne la livraison si les coordonnées paramètres correspondent à la position de la livraison
     *
     * @param x Coordonnées X du canvas graphique
     * @param y Coordonnées Y du canvas graphique
     * @return null si les coordonnées ne sont sur aucune livraison
     */
    public Livraison estSurLivraison(double x, double y) {
        if (livraisons == null || livraisons.isEmpty() || intersectionsGraphiques == null) {
            return null;
        }

        for (Livraison l : livraisons) {
            Ellipse e = intersectionsGraphiques.get(l.getAdresse().getId()).getKey();
            if (estSurEllipse(e, x, y)) {
                return l;
            }
        }

        return null;
    }

    /**
     * Renvoie l'id de l'intersection sur laquelle on a cliqué
     *
     * @param x La position x sur le canvas graphique
     * @param y La position y sur le canvas graphique
     * @return -1 si les positions ne sont pas sur une intersection
     */
    public long estSurIntersection(double x, double y) {
        if (intersectionsGraphiques == null || intersectionsGraphiques.isEmpty()) {
            return -1;
        }

        for (Map.Entry<Long, Pair<Ellipse, Collection<Long>>> pair : intersectionsGraphiques.entrySet()) {
            Ellipse e = pair.getValue().getKey();
            if (estSurEllipse(e, x, y)) {
                return pair.getKey();
            }
        }
        return -1;
    }

    /** Détermine si les positions x et y sont sur le l'ellipse
     * @param e L'ellipse à tester
     * @param x Coordonnée abscisse
     * @param y Coordonnée ordonnée
     * @return Vrai si superposition
     */
    public boolean estSurEllipse(Ellipse e, double x, double y) {
        return e.getCenterX() - ConstantesGraphique.DIAMETRE_PERMISSION <= x && x <= e.getCenterX() + ConstantesGraphique.DIAMETRE_PERMISSION
                && e.getCenterY() - ConstantesGraphique.DIAMETRE_PERMISSION <= y && y <= e.getCenterY() + ConstantesGraphique.DIAMETRE_PERMISSION;
    }

    /**
     * Contient les constantes définissant certaines propriétés (taille, marge, couleur,...) de la fenêtre
     */
    public static class ConstantesGraphique {
        /**
         * La taille sur l'interface graphique d'une intersection du plan de la
         * ville
         */
        private final static double DIAMETRE_INTERSECTION = 7;

        private final static double DIAMETRE_PERMISSION = 13;

        /**
         * La marge à laisser sur les côté du canvas graphique afin d'avoir plus
         * du lisibilité
         */
        private final static int MARGE_INTERSECTION = 10;

        

        /**
         * Coefficient mutliplicateur des ellipse pour les livraisons
         */
        private final static double COEFFICIENT_INTERSECTION_SURBRILLANCE = 1.5;

        private final static javafx.scene.paint.Paint COULEUR_INTERSECTION = javafx.scene.paint.Color.WHITE;
        private final static javafx.scene.paint.Paint COULEUR_TRONCON = javafx.scene.paint.Color.WHITE;

        private final static javafx.scene.paint.Paint COULEUR_ENTREPOT = javafx.scene.paint.Color.RED;

        private final static javafx.scene.paint.Paint[] COULEURS_FENETRES = new javafx.scene.paint.Paint[]{
                javafx.scene.paint.Color.LIGHTSEAGREEN,
                javafx.scene.paint.Color.BLUE,
                javafx.scene.paint.Color.GREEN,
                javafx.scene.paint.Color.VIOLET,
                javafx.scene.paint.Color.ORANGE,
                javafx.scene.paint.Color.CHARTREUSE,
                javafx.scene.paint.Color.DARKSLATEBLUE
        };
    }

    /** Gère la subrillance quand on passe sur la zone graphique
     */
    public class HoverGraphiqueGestionnaireEvenement implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            Livraison l = estSurLivraison(event.getX(), event.getY());
            if (l == null) {
                desactiverSurbrillance();
                return;
            }

            surbrillanceLivraison(l);
        }
    }
    /**
     * Affiche une livraison en surbrillance sur la partie graphique
     *
     * @param livraison La livraison à mettre en surbrillance
     */
    public void surbrillanceLivraison(Livraison livraison) {
        surbrillanceLivraison(livraison, true);
    }
    /** Met en surbrillance une livraison
     * @param livraison La livraison à faire surbriller
     * @param desactiverSurbrillance Si vrai, désactivation d'abord de toutes les surbrillances activées 
     */
    public void surbrillanceLivraison(Livraison livraison, boolean desactiverSurbrillance) {
        // Repeindre toutes les intersections en couleur normal (pour parvenir aux entrées et sorties non détectées) si demandé
        if (desactiverSurbrillance) {
            desactiverSurbrillance();
        }

        // Mise en surbrillance d'une intersection + agrandissement
        Ellipse livraisonGraphique = intersectionsGraphiques.get(livraison.getAdresse().getId()).getKey();

        colorerEllipse(livraison.getAdresse().getId(), livraisonGraphique);

        changerTailleEllipse(livraisonGraphique,
                ConstantesGraphique.DIAMETRE_INTERSECTION * ConstantesGraphique.COEFFICIENT_INTERSECTION_SURBRILLANCE);
    }
    /**
     * Change la couleur d'une ellipse
     *
     * @param idEllipse L'identifiant de l'ellipse
     * @param couleur   La nouvelle couleur
     */
    public void colorerEllipse(long idEllipse, javafx.scene.paint.Paint couleur) {
        colorerEllipse(intersectionsGraphiques.get(idEllipse).getKey(), couleur);
    }

    /**
     * Change la couleur d'une ellipse
     *
     * @param e L'ellipse à modifier
     * @param couleur La nouvelle couleur
     */
    public void colorerEllipse(Ellipse e, javafx.scene.paint.Paint couleur) {
        e.setFill(couleur);
    }

    /** Colore une ellipse
     * @param idIntersection L'identifiant de l'intersection à colorer
     * @param e L'ellipse à colorer
     */
    public void colorerEllipse(long idIntersection, Ellipse e) {
        // Choix de la bonne couleur
        javafx.scene.paint.Paint couleur;

        if (idIntersection == entrepot) {
            couleur = ConstantesGraphique.COULEUR_ENTREPOT;
        } else if (listeIdLivraison != null && listeIdLivraison.containsKey(idIntersection)) {
            couleur = ConstantesGraphique.COULEURS_FENETRES[(int)(listeIdLivraison.get(
                    idIntersection) % ConstantesGraphique.COULEURS_FENETRES.length)];
        } else {
            couleur = ConstantesGraphique.COULEUR_INTERSECTION;
        }

        colorerEllipse(e, couleur);
    }

    /**
     * Change le rayon d'une ellipse (qui est un cercle)
     *
     * @param e L'ellipse à modifier
     * @param rayon Le nouveau rayon
     */
    public void changerTailleEllipse(Ellipse e, double rayon) {
        e.setRadiusX(rayon);
        e.setRadiusY(rayon);
    }
    /**
     * Désactive la surbrillance pour toutes les surbrillances
     */
    public void desactiverSurbrillance() {
        if (intersectionsGraphiques == null || listeIdLivraison == null) {
            return;
        }

        Iterator<Map.Entry<Long, Pair<Ellipse, Collection<Long>>>> it = intersectionsGraphiques.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<Long, Pair<Ellipse, Collection<Long>>> pairIntersection =
                    (Map.Entry<Long, Pair<Ellipse, Collection<Long>>>) it.next();

            long idIntersection = pairIntersection.getKey();
            Ellipse intersection = pairIntersection.getValue().getKey();
            changerTailleEllipse(intersection, ConstantesGraphique.DIAMETRE_INTERSECTION);

            colorerEllipse(idIntersection, intersection);
        }
    }


    /** Gère le clic sur la partie graphique
     */
    public class ClicGraphiqueGestionnaireEvenement implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            if (event.getButton() == MouseButton.SECONDARY) { // clic droit
                controleurApplication.clicDroit();
            } else if (event.getButton() == MouseButton.PRIMARY) { // clic gauche
                long idIntersection = estSurIntersection(event.getX(), event.getY());
                if (idIntersection == -1) {
                    return;
                }
                controleurApplication.clicSurPlan(idIntersection);
            }

        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPlan(g, lePlan);

    }

    //fait la rotation d'un point(x,y) d'une angle de -Pi/2 autour de centre de coordonnees (xc,yc)
    public int[] rotationPoint(int x, int y, int xc, int yc) {
        int coordonnees[] = {x, y};
        coordonnees[0] = y - yc + xc;
        coordonnees[1] = xc - x + yc;
        return coordonnees;
    }

    public void drawPlan(Graphics g, Plan lePlan) {
        if (lePlan == null) {
            return;
        }

        Graphics2D gc = (Graphics2D) g;

        Map<Long, Intersection> intersections = lePlan.getIntersectionsMap();
        Map.Entry<Long, Intersection> first = intersections.entrySet().iterator().next();
        Intersection value = first.getValue();
        double minX = value.getX();
        double minY = value.getY();

        double maxX = value.getX();
        double maxY = value.getY();

        for (Map.Entry<Long, Intersection> entry : intersections.entrySet()) {
            Intersection inter = entry.getValue();
            if (inter.getX() <= minX) {
                minX = inter.getX();
                
            }
            if (inter.getY() <= minY) {
                    minY = inter.getY();
                }
            if (inter.getX() >= maxX) {
                maxX = inter.getX();
                
            }
            if (inter.getY() >= maxY) {
                    maxY = inter.getY();
                }
        }

        //trouver taille effective du jPanel à la place de 500 530, ou cacher les valeurs
        double paramLargeur = (maxX - minX) / 500;
        double paramHauteur = (maxY - minY) / 530;

        //Coordonnees du centre de rotation
        int coordonnees[];
        int xCentre = (int) Math.round((((maxX / 2 + minX / 2) - minX) / (paramLargeur)));
        int yCentre = (int) Math.round((((maxY / 2 + minY / 2) - minY) / (paramHauteur)));
        // Vecteur de translation OT : O(0,0) et T(xT,yT)
        int translation[] = rotationPoint((int) Math.round((maxX - minX) / paramLargeur), 0, xCentre, yCentre);

        //dessine les tronçons
        for (Map.Entry<Long, Intersection> entry : intersections.entrySet()) {
            Intersection origine = entry.getValue();
            gc.setStroke(new BasicStroke(2));
            gc.setColor(Color.BLACK);
            int x1 = (int) Math.round((origine.getX() - minX) / paramLargeur);
            int y1 = (int) Math.round((origine.getY() - minY) / paramHauteur);
            coordonnees = rotationPoint(x1, y1, xCentre, yCentre);
            x1 = coordonnees[0] - translation[0]+ ConstantesGraphique.MARGE_INTERSECTION;
            y1 = coordonnees[1] - translation[1]+ ConstantesGraphique.MARGE_INTERSECTION;

            for (Troncon section : origine.getTroncons()) {
                Intersection destination = section.getDestination();

                int x2 = (int) Math.round((destination.getX() - minX) / paramLargeur);
                int y2 = (int) Math.round((destination.getY() - minY) / paramHauteur);
                coordonnees = rotationPoint(x2, y2, xCentre, yCentre);
                x2 = coordonnees[0] - translation[0]+ ConstantesGraphique.MARGE_INTERSECTION;
                y2 = coordonnees[1] - translation[1]+ ConstantesGraphique.MARGE_INTERSECTION;
                gc.drawLine(x1, y1, x2, y2);
            }
        }
        if (laDL != null) {
            //dessine l'entrepot
            gc.setColor(Color.RED);
            int xEntrepot = (int) Math.round(((laDL.getEntrepot().getX() - minX) / paramLargeur));
            int yEntrepot = (int) Math.round(((laDL.getEntrepot().getY() - minY) / paramHauteur));
            coordonnees = rotationPoint(xEntrepot, yEntrepot, xCentre, yCentre);
            xEntrepot = coordonnees[0] - translation[0]+ ConstantesGraphique.MARGE_INTERSECTION;
            yEntrepot = coordonnees[1] - translation[1]+ ConstantesGraphique.MARGE_INTERSECTION;
            gc.fillOval(xEntrepot - 4, yEntrepot - 4, 8, 8);

            //dessine les livraisons
            Map<Long, Livraison> livraisons = laDL.getLivraison();
            for (Map.Entry<Long, Livraison> entry : livraisons.entrySet()) {
                Livraison livr = entry.getValue();
                gc.setColor(Color.BLUE);
                int xC = (int) Math.round(((livr.getAdresse().getX() - minX) / paramLargeur));
                int yC = (int) Math.round(((livr.getAdresse().getY() - minY) / paramHauteur));
                coordonnees = rotationPoint(xC, yC, xCentre, yCentre);
                xC = coordonnees[0] - translation[0]+ ConstantesGraphique.MARGE_INTERSECTION;
                yC = coordonnees[1] - translation[1]+ ConstantesGraphique.MARGE_INTERSECTION;
                gc.fillOval(xC - 4, yC - 4, 8, 8);
            }
        }

        if (laSolution != null) {

            for (ArrayList<Intersection> inter : laSolution) {
                
                for (int i =0; i< inter.size()-1; i++) {
                    gc.setColor(Color.YELLOW);
                    // proporionel
                    gc.setStroke(new BasicStroke(6));
                    Intersection origineT = inter.get(i);
                    Intersection destinationT = inter.get(i+1);

                    int x1 = (int) Math.round((origineT.getX() - minX) / paramLargeur);
                    int y1 = (int) Math.round((origineT.getY() - minY) / paramHauteur);
                    coordonnees = rotationPoint(x1, y1, xCentre, yCentre);
                    x1 = coordonnees[0] - translation[0]+ ConstantesGraphique.MARGE_INTERSECTION;;
                    y1 = coordonnees[1] - translation[1]+ ConstantesGraphique.MARGE_INTERSECTION;;

                    int x2 = (int) Math.round((destinationT.getX() - minX) / paramLargeur);
                    int y2 = (int) Math.round((destinationT.getY() - minY) / paramHauteur);
                    coordonnees = rotationPoint(x2, y2, xCentre, yCentre);
                    x2 = coordonnees[0] - translation[0]+ ConstantesGraphique.MARGE_INTERSECTION;;
                    y2 = coordonnees[1] - translation[1]+ ConstantesGraphique.MARGE_INTERSECTION;;
                    gc.drawLine(x1, y1, x2, y2);
                }
                
                if (laDL.getEntrepot().getId() == inter.get(0).getId()){
                    gc.setColor(Color.RED);
                }else{
                    gc.setColor(Color.BLUE);
                }
                int xEtape = (int) Math.round(((inter.get(0).getX() - minX) / paramLargeur));
                int yEtape = (int) Math.round(((inter.get(0).getY() - minY) / paramHauteur));
                coordonnees = rotationPoint(xEtape, yEtape, xCentre, yCentre);
                xEtape = coordonnees[0] - translation[0]+ ConstantesGraphique.MARGE_INTERSECTION;;
                yEtape = coordonnees[1] - translation[1]+ ConstantesGraphique.MARGE_INTERSECTION;;
                gc.fillOval(xEtape - 5, yEtape - 5, 10, 10);
            }
           
        }
    }

}
