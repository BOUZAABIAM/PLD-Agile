package controleur.commande;

import java.io.File;
import java.io.IOException;

import Modele.XMLParser;
import Modele.ExceptionXML;
import org.xml.sax.SAXException;

import controleur.ControleurDonnees;
import java.text.ParseException;
import javax.xml.parsers.ParserConfigurationException;
import org.jdom2.JDOMException;

/**
 * La commande de chargement d'un plan
 *
 * @author DELL
 */
public class CommandeChargerPlan extends CommandeNonAnnulable {

    /**
     * Le contrôleur de données
     */
    private final ControleurDonnees CONTROLEUR_DONNEES;

    /**
     * Le fichier de plan
     */
    private final File PLAN_FICHIER;

    /**
     * Le constructeur du chargement du plan
     *
     * @param controleurDonnees Le contrôleur de données
     * @param plan Le fichier de plan
     */
    public CommandeChargerPlan(ControleurDonnees controleurDonnees, File plan) {
        this.CONTROLEUR_DONNEES = controleurDonnees;
        PLAN_FICHIER = plan;
    }

    @Override
    public void executer() throws CommandeException {
        try {
            // Remplacer plan qui est chargé d'un nouveau plan (si et seulement le chargement du xml a reussi)
            CONTROLEUR_DONNEES.setPlan(XMLParser.getInstance().getPlan(PLAN_FICHIER));

            CONTROLEUR_DONNEES.notifierObservateursActivation(false);
            CONTROLEUR_DONNEES.notifierObservateurOuvrirDemande(true);
            CONTROLEUR_DONNEES.notifierObservateursCalculTournee(false);
            CONTROLEUR_DONNEES.notifierPlanChargeObservateur();
            CONTROLEUR_DONNEES.notifierObservateursMessage(String.format("Plan de la ville (%s) chargé avec succès ! Veuillez charger la demande de livraison maintenant.", PLAN_FICHIER.getName()));
            CONTROLEUR_DONNEES.effacerHistorique();
        } catch (SAXException | JDOMException | ExceptionXML | ParserConfigurationException | IOException | ParseException ex) {
            throw new CommandeException(ex.getMessage());
        }
    }

}
