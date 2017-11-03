package controleur.commande;

import controleur.ControleurDonnees;

/**
 * La commande de calcul de tournée
 */
public class CommandeCalculerTournee extends CommandeNonAnnulable {

    /**
     * Le contrôleur de données
     */
    private final ControleurDonnees controleurDonnees;

    /**
     * Constructeur de la commande de calcul de tournée
     * @param controleurDonnees Le contrôleur de données
     */
    public CommandeCalculerTournee(ControleurDonnees controleurDonnees) {
        this.controleurDonnees = controleurDonnees;
    }

    @Override
    public void executer() throws CommandeException {
        // Calcul de la tournée
        //..............................
    }

}
