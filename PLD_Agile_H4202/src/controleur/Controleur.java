package controleur;

import Modele.DemandeLivraison;
import Modele.ExceptionXML;
import Modele.Intersection;
import java.io.File;

import controleur.observateur.*;

import Modele.Plan;
import Modele.XMLParser;
import Vue.IHMLivraisons;
import controleur.commande.CommandeException;
import controleur.etat.EtatAjout;
import controleur.etat.EtatInitial;
import controleur.etat.EtatInterface;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import org.jdom2.JDOMException;
import org.xml.sax.SAXException;


/**
 * Implémente l'interface contrôleur. Point d'entrée principal pour toutes les intéractions avec le package vue.
 */
public class Controleur implements ControleurInterface {

    /**
     * Etat actuel de l'application
     */
    private EtatInterface etat;
    
    /**
     * Le contrôleur qui intéragit avec le package modèle
     */
    private final ControleurDonnees controleurDonnees;

    /**
     * Constructeur public du contrôleur 
     */
    public Controleur() {
        controleurDonnees = new ControleurDonnees();
        etat = new EtatInitial(controleurDonnees);
    }

    @Override
    public void ajouterActivationFonctionnalitesObservateur(ActivationFonctionnalitesObservateur observer) {
        controleurDonnees.ajouterActivationObservateur(observer);
    }

    @Override
    public void ajouterModeleObservateur(ModeleObservateur observer) {
        controleurDonnees.ajouterModeleObservateur(observer);
    }

    @Override
    public void ajouterActivationOuvrirDemandeObservateur(ActivationOuvrirDemandeObservateur planObserveur) {
        controleurDonnees.ajouterPlanObservateur(planObserveur);
    }

    @Override
    public void clicAnnuler() {
        controleurDonnees.getHist().annuler();
    }

    @Override
    public void clicRetablir() {
        try {
            controleurDonnees.getHist().executer();
        } catch (CommandeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Plan parserPlan(File selectedFile) throws Exception {
        etat = etat.chargerPlan(selectedFile);
        Plan planDeVille = null;

        try {
            XMLParser parser = new XMLParser();
            planDeVille = parser.getPlan(selectedFile);
        } catch (SAXException | ExceptionXML | JDOMException | ParserConfigurationException | IOException | ParseException ex) {
            Logger.getLogger(IHMLivraisons.class.getName()).log(Level.SEVERE, null, ex);
        }

        return planDeVille;
    }

    @Override
    public DemandeLivraison parserLivraisons(File fichierLivraisons, Plan planActuel) throws Exception {
        etat = etat.chargerLivraisons(fichierLivraisons);
        DemandeLivraison dl = null;
        try {
                    XMLParser parser = new XMLParser();
                    dl = parser.getDL(fichierLivraisons, planActuel);
                } catch (SAXException | ExceptionXML |JDOMException |ParserConfigurationException| IOException | ParseException ex) {
                    Logger.getLogger(IHMLivraisons.class.getName()).log(Level.SEVERE, null, ex);
                }
        return dl;
    }

    @Override
    public void clicOutilAjouter() {
        etat = new EtatAjout(controleurDonnees);
    }

   

    

    @Override
    public void clicDroit() {
        etat = etat.clicDroit();
    }

    

    @Override
    public void clicSurPlan(long intersectionId) {
        etat = etat.clicSurPlan(intersectionId);
    }

    @Override
    public List<ArrayList<Intersection>> calculTournee(Plan planActuel) {
        etat = etat.calculerTournee();
        //Création de calcul tournée
        planActuel.calculSolutionTSP1();
        List<ArrayList<Intersection>> solution = planActuel.getSolution2();
        
        
        return solution;
//        System.out.println("Solutions : ");
//        for (int j=0; j<solution.size(); j++){
//            System.out.println(solution.get(j).get(0).toString());
//        }
//        int s1 = solution.size()-1;
//        int s2 = solution.get(s1).size()-1;
//        System.out.println(solution.get(s1).get(s2).toString());
//
//        System.out.println("Itinéraire : ");
//        for (int j=0; j<solution.size(); j++){
//            for (int k=0; k<solution.get(j).size(); k++){
//                System.out.println(solution.get(j).get(k).toString());
//            }
//        }
    }
    
    @Override
    public List<Time[]> calculDuree(Plan planActuel){
        return planActuel.getTempsPassage();
    }

    @Override
    public Plan getPlanDeVille() {
        Plan plan = controleurDonnees.getPlan();
        if (plan == null)
            throw new RuntimeException(
                    "Plan n'existe pas, il faut charger le fichier xml avant d'appeler cette méthode");
        return plan;
    }

    @Override
    public void clicSurLivraison(long livraisonId) {
       etat = etat.clicSurLivraison(livraisonId);
    }

    @Override
    public void ajouterTourneeObservateur(ActivationFonctionnalitesObservateur tourneeObserveur) {
        controleurDonnees.ajouterTourneeObservateur(tourneeObserveur);
    }

    @Override
    public void ajouterAnnulerCommandeObservateur(AnnulerCommandeObservateur annulerCommandeObserveur) {
        controleurDonnees.ajouterAnnulerCommandeObservateur(annulerCommandeObserveur);
    }

    @Override
    public void ajouterRetablirCommandeObservateur(RetablirCommandeObservateur retablirCommandeObserveur) {
        controleurDonnees.ajouterRetablirCommandeObservateur(retablirCommandeObserveur);
    }

	@Override
	public void genererFeuilleDeRoute(File fichier) throws CommandeException {
		// A definir ........
	}

    @Override
    public void ajouterMessageObservateur(MessageObservateur obs) {
		controleurDonnees.ajouterMessageObservateur(obs);
	}

    @Override
    public void ajouterPlanChargeObserveur(PlanChargeObservateur planChargeObservateur) {
        controleurDonnees.ajouterPlanChargeObservateur(planChargeObservateur);
    }

    @Override
	public void ajouterActivationOuvrirPlanObservateur(ActivationOuvrirPlanObservateur chargementPlanObserveur) {
		controleurDonnees.ajouterChargementPlanObservateur(chargementPlanObserveur);
	}
}
