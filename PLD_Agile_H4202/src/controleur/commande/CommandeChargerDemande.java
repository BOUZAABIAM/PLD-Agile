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
import org.jdom2.JDOMException;

/**
 * La commande de chargement de la demande
 */
public class CommandeChargerDemande extends CommandeNonAnnulable {

    /**
     * Le contrôleur de données
     */
    private final ControleurDonnees CONTROLEUR_DONNEES;
    
    /**
     * Le fichier de livraion
     */
    private final File LIVRAISONS_FICHIER;

    /**
     * Constructeur de la commande de chargement de la demande
     * @param controleurDonnees Le contrôleur de données
     * @param livraisons Le fichier de demande de livraisons
     */
    public CommandeChargerDemande(ControleurDonnees controleurDonnees, File livraisons) {
        this.CONTROLEUR_DONNEES = controleurDonnees;
        LIVRAISONS_FICHIER = livraisons;
    }

    @Override
    public void executer() throws CommandeException {
        try {
            Plan plan = CONTROLEUR_DONNEES.getPlan();
            DemandeLivraison demande = XMLParser.getInstance().getDL(LIVRAISONS_FICHIER, plan);
           // controleurDonnees.setModele(new Modele(plan, demande));

            // Permettre de calculer la tournee
            CONTROLEUR_DONNEES.notifierObservateursCalculTournee(true);
            
            // Notifier les observeurs que il y a un model maintenant
            CONTROLEUR_DONNEES.notifierObservateursModele();

            CONTROLEUR_DONNEES.notifierObservateursMessage(String.format("Demande de livraisons (%s) chargée avec succès ! Veuillez calculer la tournée maintenant.", LIVRAISONS_FICHIER.getName()));
            CONTROLEUR_DONNEES.effacerHistorique();
        } catch (SAXException | ExceptionXML| JDOMException|ParserConfigurationException| IOException | ParseException ex) {
            throw new CommandeException(ex.getMessage());
        }
    }
}

