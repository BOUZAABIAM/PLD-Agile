package controleur.etat;

import controleur.ControleurDonnees;
import controleur.commande.CommandeCalculerTournee;
import controleur.commande.CommandeChargerDemande;
import controleur.commande.CommandeChargerPlan;
import controleur.commande.CommandeException;

import java.io.File;

/**
 * On se retrouve dans cet état après avoir chargé la demande de livraisons mais sans avoir calculé la tournée encore.
 *
 */
public class EtatDemandeChargee implements EtatInterface {

    /** Le contrôleur de données */
    private final ControleurDonnees CONTROLEUR_DONNEES;

    /**
     * Constructeur de l'état demande chargée
     * @param controleurDonnees Le contrôleur de données
     */
    public EtatDemandeChargee(ControleurDonnees controleurDonnees) {
        // On désactive les fonctionnalités principales
    	//controleurDonnees.notifierObservateursFonctionnalites(false);
        controleurDonnees.notifierObservateursActivation(false);
        this.CONTROLEUR_DONNEES = controleurDonnees;
    }

    @Override
    public EtatInterface clicSurLivraison(long livraisonId) {
    	// Ne fait rien
        return this;
    }

    @Override
    public EtatInterface chargerPlan(File plan) throws CommandeException {
        new CommandeChargerPlan(CONTROLEUR_DONNEES, plan).executer();
        return new EtatPlanCharge(CONTROLEUR_DONNEES);
    }

    @Override
    public EtatInterface chargerLivraisons(File livraisons) throws CommandeException {
        new CommandeChargerDemande(CONTROLEUR_DONNEES, livraisons).executer();
        return this;
    }

    @Override
    public EtatInterface clicSurPlan(long intersectionId) {
        // Ne fait rien
        return this;
    }

    @Override
    public EtatInterface calculerTournee() {
        try {
            new CommandeCalculerTournee(CONTROLEUR_DONNEES).executer();
        } catch (CommandeException ex) {
            throw new RuntimeException("Un problème est survenu lors du calcul de la tournée");
        }
        CONTROLEUR_DONNEES.notifierObservateursCalculTournee(false);
        return new EtatPrincipal(CONTROLEUR_DONNEES);
    }

    @Override
    public EtatInterface clicDroit() {
        // Ne fait rien
    	return this;
    }
    
}
