package controleur.commande;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import Modele.DemandeLivraison;
import Modele.Plan;
import Modele.XMLParser;
import Modele.ExceptionXML;


import org.xml.sax.SAXException;

import controleur.ControleurDonnees;
import javax.xml.parsers.ParserConfigurationException;

/**
 * La commande de chargement de la demande
 */
public class CommandeChargerDemande extends CommandeNonAnnulable {

    /**
     * Le contrôleur de données
     */
    private final ControleurDonnees controleurDonnees;
    
    /**
     * Le fichier de livraion
     */
    private final File livraisonsFichier;

    /**
     * Constructeur de la commande de chargement de la demande
     * @param controleurDonnees Le contrôleur de données
     * @param livraisons Le fichier de demande de livraisons
     */
    public CommandeChargerDemande(ControleurDonnees controleurDonnees, File livraisons) {
        this.controleurDonnees = controleurDonnees;
        livraisonsFichier = livraisons;
    }

    @Override
    public void executer() throws CommandeException {
        try {
            Plan plan = controleurDonnees.getPlan();
            DemandeLivraison demande = XMLParser.getInstance().getDL(livraisonsFichier, plan);
           // controleurDonnees.setModele(new Modele(plan, demande));

            // Permettre de calculer la tournee
            controleurDonnees.notifierObservateursCalculTournee(true);
            
            // Notifier les observeurs que il y a un model maintenant
            controleurDonnees.notifierObservateursModele();

            controleurDonnees.notifierObservateursMessage(String.format("Demande de livraisons (%s) chargée avec succès ! Veuillez calculer la tournée maintenant.", livraisonsFichier.getName()));
            controleurDonnees.effacerHistorique();
        } catch (SAXException | ExceptionXML |ParserConfigurationException| IOException | ParseException ex) {
            throw new CommandeException(ex.getMessage());
        }
    }
}
