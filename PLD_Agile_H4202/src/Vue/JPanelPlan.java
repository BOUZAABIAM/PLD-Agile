/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.DemandeLivraison;
import Modele.ExceptionXML;
import Modele.Livraison;
import Modele.Plan;
import Modele.Intersection;
import Modele.Troncon;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import controleur.ControleurInterface;
import controleur.commande.CommandeException;
import java.io.IOException;
import java.text.ParseException;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Ellipse;
import javafx.scene.transform.Rotate;
import javafx.util.Pair;
import javax.xml.parsers.ParserConfigurationException;
import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

/**
 *
 * @author epetit
 */
public class JPanelPlan extends JPanel {

    public Plan lePlan;
    private DemandeLivraison laDL;
    private java.util.List<ArrayList<Intersection>> laSolution;
    public static int t1;
    public static int t2;
    public static int xR;
    public static int yR;
    public static double paramLargeur;
    public static double paramHauteur;
    public static double minX;
    public static double minY;
    public Graphics2D gc;
    /** La liste des livraisons */
    public Map <Integer,Pair<Long, Point>> Listelivraisons = null;
     /** Le contrôleur de l'application */
    private ControleurInterface controleurApplication;
    /**
     * Contient tous les points graphiques actuellement afficher pour le plan
     * grâce à leur id, et pour chaque intersection, ses arcs graphiques ainsi
     * que l'intersection ciblée
     */
    public Map<Long, Intersection> intersections ;
    public Map<Long, Point> intersections2 ;

    /**
     * Identifiant (adresse) de l'intersection où se situe l'entrepot
     */
    private long entrepot = -1;
    
    
    
    
    public void setPlan(Plan lePlan) {
        this.lePlan = lePlan;
    }

    public void setDL(DemandeLivraison laDL) {
        this.laDL = laDL;
    }

    public void setSolution(java.util.List<ArrayList<Intersection>> solution) {
        this.laSolution = solution;
    }
    
     /**
     * Retourne la livraison si les coordonnées paramètres correspondent à la position de la livraison
     *
     * @param x Coordonnées X du canvas graphique
     * @param y Coordonnées Y du canvas graphique
     * @return null si les coordonnées ne sont sur aucune livraison
     */
    public long[] estSurLivraison(int x, int y) {
        
        if (Listelivraisons == null || Listelivraisons.isEmpty() || intersections == null) {
            return null;
        }

        for (Map.Entry<Integer,Pair<Long, Point> >pair : Listelivraisons.entrySet()) {
              int xi = pair.getValue().getValue().x;
              int yi = pair.getValue().getValue().y;
              if (estSurRectangle(xi,yi, x, y)) {
                //gc.fillOval(x-8, y-8, 16, 16);
                long t[]={(long)pair.getKey(),pair.getValue().getKey()};
                return t;
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
    public long estSurIntersection(int x, int y) {
        if (intersections == null || intersections.isEmpty()||intersections2 == null || intersections2.isEmpty()) {
            return -1;
        }

        for (Map.Entry<Long, Point> pair : intersections2.entrySet()) {
           
              int xi = pair.getValue().x;
              int yi = pair.getValue().y;
              if (estSurRectangle(xi,yi, x, y)) {
                return pair.getKey();
              }
        }
        return -1;
    }

    public boolean estSurRectangle(int xi,int yi, int x, int y) {
        return xi - ConstantesGraphique.DIAMETRE_PERMISSION <= x && x <= xi + ConstantesGraphique.DIAMETRE_PERMISSION
                && yi - ConstantesGraphique.DIAMETRE_PERMISSION <= y && y <= yi + ConstantesGraphique.DIAMETRE_PERMISSION;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        JOptionPane jop;
        try{
        drawPlan(g, lePlan);
        }catch(SAXException | ExceptionXML| JDOMException| IOException ex) {
//            jop = new JOptionPane();
//                jop.showMessageDialog(null, ex, "Attention", JOptionPane.WARNING_MESSAGE);
            }

    }

    //fait la rotation d'un point(x,y) d'une angle de -Pi/2 autour de centre de coordonnees (xc,yc)
    public int[] rotationPoint(int x, int y, int xc, int yc) {
        int coordonnees[] = {x, y};
        coordonnees[0] = y - yc + xc;
        coordonnees[1] = xc - x + yc;
        return coordonnees;
    }
    
    //fait la rotation d'un point(x,y) d'une angle de Pi/2 autour de centre de coordonnees (xc,yc)
    public int[] rotationInverse(int x, int y, int xc, int yc) {
        int coordonnees[] = {x, y};
        coordonnees[0] =  xc  - y + yc ;
        coordonnees[1] = yc + x -xc ;
        return coordonnees;
    }

    public void drawPlan(Graphics g, Plan lePlan)throws JDOMException, IOException, SAXException, Modele.ExceptionXML  {
        if (lePlan == null) {
            return;
        }

         gc = (Graphics2D) g;
        intersections = lePlan.getIntersectionsMap();
        intersections2=new HashMap<>();
        Map.Entry<Long, Intersection> first = intersections.entrySet().iterator().next();
        Intersection value = first.getValue();
        minX = value.getX();
        minY = value.getY();
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
         paramLargeur = (maxX - minX) / 500;
         paramHauteur = (maxY - minY) / 530;

        //Coordonnees du centre de rotation
        int coordonnees[];
        int xCentre = (int) Math.round((((maxX / 2 + minX / 2) - minX) / (paramLargeur)));
        int yCentre = (int) Math.round((((maxY / 2 + minY / 2) - minY) / (paramHauteur)));
        xR=xCentre;
        yR=yCentre;
        // Vecteur de translation OT : O(0,0) et T(xT,yT)
        int translation[] = rotationPoint((int) Math.round((maxX - minX) / paramLargeur), 0, xCentre, yCentre);
        t1 =  translation[0];
        t2 =  translation[1];
        //dessine les tronçons
        for (Map.Entry<Long, Intersection> entry : intersections.entrySet()) {
            Intersection origine = entry.getValue();
            gc.setStroke(new BasicStroke(2));
            gc.setColor(Color.BLACK);
            
            int x1 = (int) Math.round((origine.getX() - minX) / paramLargeur);
            int y1 = (int) Math.round((origine.getY() - minY) / paramHauteur);
            coordonnees = rotationPoint(x1, y1, xCentre, yCentre);
            x1 = coordonnees[0] - translation[0] + ConstantesGraphique.MARGE_INTERSECTION;
            y1 = coordonnees[1] - translation[1]+ ConstantesGraphique.MARGE_INTERSECTION;
            Point pt =new Point(x1,y1);
            long id =origine.getId();
            intersections2.put(id, pt);

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
            gc.drawOval(xEntrepot - 6,  yEntrepot -6, 12, 12);
            entrepot=laDL.getEntrepot().getId();

            //dessine les livraisons
            Map<Long, Livraison> livraisons = laDL.getLivraison();
            Listelivraisons = new HashMap<Integer, Pair<Long, Point>>();
            Point point=new Point(xEntrepot,yEntrepot);
            int o =0;
            Listelivraisons.put(o, new Pair<>(entrepot,point));
            o++;
             
            
            for (Map.Entry<Long, Livraison> entry : livraisons.entrySet()) {
                Livraison livr = entry.getValue();
                gc.setColor(Color.BLUE);
                int xC = (int) Math.round(((livr.getAdresse().getX() - minX) / paramLargeur));
                int yC = (int) Math.round(((livr.getAdresse().getY() - minY) / paramHauteur));
                coordonnees = rotationPoint(xC, yC, xCentre, yCentre);
                xC = coordonnees[0] - translation[0]+ ConstantesGraphique.MARGE_INTERSECTION;
                yC = coordonnees[1] - translation[1]+ ConstantesGraphique.MARGE_INTERSECTION;
                gc.fillOval(xC - 4, yC - 4, 8, 8);
                gc.drawOval(xC - 6,  yC -6, 12, 12);
                Point point1=new Point(xC,yC);
                Listelivraisons.put(o, new Pair<>(livr.getAdresse().getId(),point1));
                o++;
            }
           
        }
        

        if (laSolution != null) {

            for (ArrayList<Intersection> inter : laSolution) {
                
                for (int i =0; i< inter.size()-1; i++) {
                    gc.setColor(Color.YELLOW);
                    // proporionel
                    gc.setStroke(new BasicStroke(4));
                    Intersection origineT = inter.get(i);
                    Intersection destinationT = inter.get(i+1);

                    int x1 = (int) Math.round((origineT.getX() - minX) / paramLargeur);
                    int y1 = (int) Math.round((origineT.getY() - minY) / paramHauteur);
                    coordonnees = rotationPoint(x1, y1, xCentre, yCentre);
                    x1 = coordonnees[0] - translation[0]+ ConstantesGraphique.MARGE_INTERSECTION;
                    y1 = coordonnees[1] - translation[1]+ ConstantesGraphique.MARGE_INTERSECTION;

                    int x2 = (int) Math.round((destinationT.getX() - minX) / paramLargeur);
                    int y2 = (int) Math.round((destinationT.getY() - minY) / paramHauteur);
                    coordonnees = rotationPoint(x2, y2, xCentre, yCentre);
                    x2 = coordonnees[0] - translation[0]+ ConstantesGraphique.MARGE_INTERSECTION;
                    y2 = coordonnees[1] - translation[1]+ ConstantesGraphique.MARGE_INTERSECTION;
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
                xEtape = coordonnees[0] - translation[0]+ ConstantesGraphique.MARGE_INTERSECTION;
                yEtape = coordonnees[1] - translation[1]+ ConstantesGraphique.MARGE_INTERSECTION;
                gc.fillOval(xEtape - 5, yEtape - 5, 10, 10);
            }
           
        }
        
    }
    
    /**
     * Contient les constantes définissant certaines propriétés (taille, marge, couleur,...) de la fenêtre
     */
    public static class ConstantesGraphique {
        /**
         * La taille sur l'interface graphique d'une intersection du plan de la
         * ville
         */
        private final static double DIAMETRE_INTERSECTION = 20;

        private final static double DIAMETRE_PERMISSION = 1;

        private final static double MAX_ZOOM = 15;
        
        /**
         * La marge à laisser sur les côté du canvas graphique afin d'avoir plus
         * du lisibilité
         */
        public final static int MARGE_INTERSECTION = 10;

        /**
         * Coefficient mutliplicateur des ellipse pour les livraisons
         */
        private final static double COEFFICIENT_INTERSECTION_SURBRILLANCE = 3;

        private final static javafx.scene.paint.Paint COULEUR_INTERSECTION = javafx.scene.paint.Color.BLACK;
        private final static javafx.scene.paint.Paint COULEUR_LIVRAISON = javafx.scene.paint.Color.BLUE;
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
    

}


