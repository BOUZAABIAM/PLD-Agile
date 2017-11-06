package controleur;

import Modele.ExceptionXML;
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
import java.text.ParseException;
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
    public void chargerPlan(File fichierPlan) throws Exception {
        etat = etat.chargerPlan(fichierPlan);
    }

    @Override
    public void chargerLivraisons(File fichierLivraisons) throws Exception {
        etat = etat.chargerLivraisons(fichierLivraisons);
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
    public void clicCalculTournee() {
        etat = etat.clicCalculerTournee();
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
        
    public Plan ajouterPlan() {
         JFileChooser xml_map = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML Files", "xml");
        
        xml_map.setFileFilter(filter);
        Plan planDeVille = null;
        String exception="";
        JOptionPane jop; // fenetre d'alerte
        if (xml_map.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = xml_map.getSelectedFile();
            //Vérifier le format du fichier xml ou non
            if(filter.accept(selectedFile)==false){
                exception = "Format Fichier Plan Incorrect !";
                jop = new JOptionPane();
                jop.showMessageDialog(null, exception, "Attention", JOptionPane.WARNING_MESSAGE);
                
                return null;
            }

            try {
                XMLParser parser = new XMLParser();
                planDeVille = parser.getPlan(selectedFile);
            } catch (SAXException | ExceptionXML|JDOMException |ParserConfigurationException| IOException | ParseException ex) {
                Logger.getLogger(IHMLivraisons.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return planDeVille;
    }

}
