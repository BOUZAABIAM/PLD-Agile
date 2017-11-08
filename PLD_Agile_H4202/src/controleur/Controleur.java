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
 */
public class Controleur implements ControleurInterface {
    
    private Plan planActuel;
    private DemandeLivraison DLActuelle;
    private List<ArrayList<Intersection>> solutionActuelle;

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
        DLActuelle = dl;
        planActuel.setDL(dl);
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
    public List<ArrayList<Intersection>> calculTournee() {
        etat = etat.calculerTournee();
        //Création de calcul tournée
        planActuel.calculSolutionTSP1();
        List<ArrayList<Intersection>> solution = planActuel.getSolution2();
        
        solutionActuelle = solution;
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
    public List<Time[]> calculDuree(){
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
    @Override
        public void annuler(){
            DLActuelle=null;
            solutionActuelle=null;
        }
        
    @Override
        public Livraison getLivraisonByID(long id){
            return DLActuelle.getLivraison().get(id);
        }
        
    @Override
        public List<ArrayList<Intersection>> ajouterLivraison(long idAdd, long idPrec){

            Intersection interAdd = planActuel.getIntersectionsMap().get(idAdd);    
            Intersection interPrec = planActuel.getIntersectionsMap().get(idPrec); 

            planActuel.addLivraison(interPrec, interAdd);
            solutionActuelle = planActuel.getSolution2();
            
            Livraison livr = new Livraison(interAdd, 600);
            DLActuelle.addLivraison(livr);
            
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
                       String nomRueprec ="";
                    for (Intersection j : i) { 
                        nomRue = j.getTroncons().get(0).getNomRue();
                        if(!(nomRue.equals(nomRueprec)))
                        { text = text.concat(nomRue + "--->");}
                        System.out.println("0"+text);
                        System.out.println("1"+nomRue);
                        System.out.println("2"+nomRueprec);
                        nomRueprec = nomRue;
                        
                    }

                    Livraison livraison = DLActuelle.getLivraison().get(i.get(0).getId());
                    System.out.println("liv:"+livraison);
                    if (livraison != null) {
                         List<Time[]> heures = this.calculDuree();
                        String debutPlage = "";
                        String finPlage = "";
                        
                        String heureDepart = heures.get(inter)[0].toString();
                        String heureArrive = heures.get(inter)[1].toString();
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
                        document.add(new Paragraph("Livraison : " + adresse, font20));
                        document.add(new Paragraph("Plage horaire : [" + debutPlage + " - " + finPlage + "]\n", font20));
                        document.add(new Paragraph("Heure de départ : "+heureDepart,font20));
                        document.add(new Paragraph("Heure d'arrivé : "+heureArrive,font20));
                        document.add(new Paragraph("Trajet vers la livraison suivante", font20));
                    } else {
                        document.add(new Paragraph("Trajet de l'entrepot vers 1ére adresse", font20));
                    }
                    document.add(new Paragraph(text, font12));

                }
                document.add(new Paragraph("ENTREPOT :" + solutionActuelle.get(0).get(0).getTroncons().get(0).getNomRue(), font20));
            } catch (DocumentException ex) {
                Logger.getLogger(IHMLivraisons.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
