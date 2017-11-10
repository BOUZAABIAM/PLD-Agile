package controleur;

import Modele.DemandeLivraison;
import Modele.ExceptionXML;
import Modele.Intersection;
import Modele.Livraison;
import java.io.File;

import controleur.observateur.*;

import Modele.Plan;
import Modele.XMLParser;
import Vue.IHMLivraisons;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;

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
import javax.xml.parsers.ParserConfigurationException;
import org.jdom2.JDOMException;
import org.xml.sax.SAXException;


/**
 * Implémente l'interface contrôleur. Point d'entrée principal pour toutes les intéractions avec le package vue.
 * @author DELL
 */
public class Controleur implements ControleurInterface {
    
    private Plan planActuel;
    private DemandeLivraison dLActuelle;
    private List<ArrayList<Intersection>> solutionActuelle;

    /**
     * Etat actuel de l'application
     */
    private EtatInterface etat;
    
    /**
     * Le contrôleur qui intéragit avec le package modèle
     */
    private final ControleurDonnees CONTROLEUR_DONNEES;

    /**
     * Constructeur public du contrôleur 
     */
    public Controleur() {
        CONTROLEUR_DONNEES = new ControleurDonnees();
        etat = new EtatInitial(CONTROLEUR_DONNEES);
    }

    @Override
    public void ajouterActivationFonctionnalitesObservateur(ActivationFonctionnalitesObservateur observer) {
        CONTROLEUR_DONNEES.ajouterActivationObservateur(observer);
    }

    @Override
    public void ajouterModeleObservateur(ModeleObservateur observer) {
        CONTROLEUR_DONNEES.ajouterModeleObservateur(observer);
    }

    @Override
    public void ajouterActivationOuvrirDemandeObservateur(ActivationOuvrirDemandeObservateur planObserveur) {
        CONTROLEUR_DONNEES.ajouterPlanObservateur(planObserveur);
    }

    @Override
    public void clicAnnuler() {
        CONTROLEUR_DONNEES.getHist().annuler();
    }

    @Override
    public void clicRetablir() {
        try {
            CONTROLEUR_DONNEES.getHist().executer();
        } catch (CommandeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Plan parserPlan(File selectedFile) throws Exception {
        Plan planDeVille = null;
	try {
	    etat = etat.chargerPlan(selectedFile);
       
            XMLParser parser = new XMLParser();
            planDeVille = parser.getPlan(selectedFile);
        } catch (SAXException | ExceptionXML | JDOMException | ParserConfigurationException | IOException | ParseException ex) {
            Logger.getLogger(IHMLivraisons.class.getName()).log(Level.SEVERE, null, ex);
        }
        planActuel = planDeVille;
        return planDeVille;
    }

    @Override
    public DemandeLivraison parserLivraisons(File fichierLivraisons) throws Exception {
        etat = etat.chargerLivraisons(fichierLivraisons);
        DemandeLivraison dl = null;
        try {
                    XMLParser parser = new XMLParser();
                    dl = parser.getDL(fichierLivraisons, planActuel);
                } catch (SAXException | ExceptionXML |JDOMException |ParserConfigurationException| IOException | ParseException ex) {
                    Logger.getLogger(IHMLivraisons.class.getName()).log(Level.SEVERE, null, ex);
                }
        dLActuelle = dl;
        planActuel.setDL(dl);
        return dl;
    }

    @Override
    public void clicOutilAjouter() {
        etat = new EtatAjout(CONTROLEUR_DONNEES);
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
    public List<ArrayList<Intersection>> calculTournee() {
        etat = etat.calculerTournee();
        //Création de calcul tournée
        planActuel.calculSolutionTSP1();
        List<ArrayList<Intersection>> solution = planActuel.getSolution2();
        
        solutionActuelle = solution;
        return solution;

    }
    
    @Override
    public List<Time[]> calculDuree(){
        return planActuel.getTempsPassage();
    }

    @Override
    public Plan getPlanDeVille() {
        Plan plan = CONTROLEUR_DONNEES.getPlan();
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
        CONTROLEUR_DONNEES.ajouterTourneeObservateur(tourneeObserveur);
    }

    @Override
    public void ajouterAnnulerCommandeObservateur(AnnulerCommandeObservateur annulerCommandeObserveur) {
        CONTROLEUR_DONNEES.ajouterAnnulerCommandeObservateur(annulerCommandeObserveur);
    }

    @Override
    public void ajouterRetablirCommandeObservateur(RetablirCommandeObservateur retablirCommandeObserveur) {
        CONTROLEUR_DONNEES.ajouterRetablirCommandeObservateur(retablirCommandeObserveur);
    }

    @Override
    public void ajouterMessageObservateur(MessageObservateur obs) {
		CONTROLEUR_DONNEES.ajouterMessageObservateur(obs);
	}

    @Override
    public void ajouterPlanChargeObserveur(PlanChargeObservateur planChargeObservateur) {
        CONTROLEUR_DONNEES.ajouterPlanChargeObservateur(planChargeObservateur);
    }

    @Override
	public void ajouterActivationOuvrirPlanObservateur(ActivationOuvrirPlanObservateur chargementPlanObserveur) {
		CONTROLEUR_DONNEES.ajouterChargementPlanObservateur(chargementPlanObserveur);
	}
    @Override
        public void annuler(){
            dLActuelle=null;
            solutionActuelle=null;
        }
        
    @Override
        public Livraison getLivraisonByID(long id){
            return dLActuelle.getLivraison().get(id);
        }
        
    @Override
        public List<ArrayList<Intersection>> ajouterLivraison(long idAdd, long idPrec){

            Intersection interAdd = planActuel.getIntersectionsMap().get(idAdd);    
            Intersection interPrec = planActuel.getIntersectionsMap().get(idPrec); 

            planActuel.addLivraison(interPrec, interAdd);
            solutionActuelle = planActuel.getSolution2();
            
            Livraison livr = new Livraison(interAdd, 600);
            dLActuelle.addLivraison(livr);
            
            return solutionActuelle;
        }
        
    @Override
        public List<ArrayList<Intersection>> supprimerLivraison(long idSuppr){
            
            if (!planActuel.getLivraisons().isEmpty()){
                Intersection interSup;
                interSup = planActuel.getIntersectionsMap().get(idSuppr);
                
                planActuel.deleteLivraison(interSup);
                solutionActuelle = planActuel.getSolution2();
                return solutionActuelle;
            }
            return null;
        }
    
    @Override 
        public void feuilleDeRoute(Document document) {
            Font font14 = new Font(FontFamily.TIMES_ROMAN, 14);
            Font font18 = new Font(FontFamily.TIMES_ROMAN, 18);
            Font font20 = new Font(FontFamily.TIMES_ROMAN, 20);
            Font font12 = new Font(FontFamily.TIMES_ROMAN, 12);
            Font font30 = new Font(FontFamily.TIMES_ROMAN, 30);
            try {
                document.add(new Paragraph("FEUILLE DE ROUTE", font30));
                document.add(new Paragraph("ENTREPOT :" + solutionActuelle.get(0).get(0).getTroncons().get(0).getNomRue(), font20));
                
                int inter =-1;
                for (ArrayList<Intersection> i : solutionActuelle) {
                       inter ++; 
                       String text = "";
                       String nomRue ="";
                       String nomRuePrec ="";
                    for (Intersection j : i) { 
                        nomRue = j.getTroncons().get(0).getNomRue();
                        if(!(nomRue.equals(nomRuePrec)))
                        { text = text.concat(nomRue + "--->");}
                        nomRuePrec = nomRue;
                        
                    }

                    Livraison livraison = dLActuelle.getLivraison().get(i.get(0).getId());
                    if (livraison != null) {
                         List<Time[]> heures = this.calculDuree();
                        String debutPlage = "";
                        String finPlage = "";
                      
                        String heureDepart = heures.get(inter)[0].toString();
                        String heureArrive = heures.get(inter)[1].toString();
                        String dureeFormatee = "";
                int seconds = livraison.getDuree() % 60;
                int totalMinutes = livraison.getDuree() / 60;
                int minutes = totalMinutes % 60;
                int hours = totalMinutes / 60;

                if(hours >0){
                    dureeFormatee += hours + "h ";
                }
                if(minutes >0){
                    dureeFormatee += minutes + "min ";
                }
                if(seconds >0){
                    dureeFormatee += seconds + "s";
                }
                        if (livraison.getDebutPlage() == null) {
                            debutPlage = "**";
                        } else {
                            debutPlage = livraison.getDebutPlage().toString();
                        }
                        if (livraison.getFinPlage() == null) {
                            finPlage = "**";
                        } else {
                            finPlage = livraison.getFinPlage().toString();
                        }
                        String adresse = livraison.getAdresse().getTroncons().get(0).getNomRue();
                        if (livraison.getDebutPlage() == null) {
                            debutPlage = "**";
                        }
                        if (livraison.getFinPlage() == null) {
                            finPlage = "**";
                        }
                        document.add(new Paragraph("Livraison : " + adresse, font18));
                        document.add(new Paragraph("Plage horaire : [" + debutPlage + " - " + finPlage + "]\n", font14));
                        document.add(new Paragraph("Heure de départ : "+heureDepart,font14));
                        document.add(new Paragraph("Heure d'arrivé : "+heureArrive,font14));
                        document.add(new Paragraph("Durée : "+dureeFormatee,font14));

                        document.add(new Paragraph("Trajet vers la livraison suivante", font20));
                    } else {
                        document.add(new Paragraph("Trajet de l'entrepôt vers première adresse", font20));
                    }
                    document.add(new Paragraph(text, font12));

                }
                document.add(new Paragraph("ENTREPOT :" + solutionActuelle.get(0).get(0).getTroncons().get(0).getNomRue(), font20));
            } catch (DocumentException ex) {
                Logger.getLogger(IHMLivraisons.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
