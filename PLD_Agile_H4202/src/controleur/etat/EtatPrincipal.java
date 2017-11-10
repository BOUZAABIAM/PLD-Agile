package controleur.etat;

import java.io.File;

import controleur.ControleurDonnees;
import controleur.commande.CommandeChargerDemande;
import controleur.commande.CommandeChargerPlan;
import controleur.commande.CommandeException;

/**
 * Etat principal de l'application après avoir calculé la tournée. Les fonctionnalités sont débloqués
 * @author DELL
 */
public class EtatPrincipal implements EtatInterface {

    /** Le contrôleur de données */
    private final ControleurDonnees CONTROLEUR_DONNEES;

    /**
     * Constructeur de l'état principal
     * @param controleurDonnees Le contrôleur de données
     */
    public EtatPrincipal(ControleurDonnees controleurDonnees) {
        this.CONTROLEUR_DONNEES = controleurDonnees;
        controleurDonnees.notifierObservateurOuvrirDemande(true);
        controleurDonnees.notifierObservateurOuvrirPlan(true);
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
        return new EtatDemandeChargee(CONTROLEUR_DONNEES);
    }

    @Override
    public EtatInterface clicSurPlan(long intersectionId) {
        throw new UnsupportedOperationException("Cet état ne permet pas de charger un plan");
    }

    @Override
    public EtatInterface calculerTournee() {
    	// Ne fait rien
        return this;
    }

    @Override
    public EtatInterface clicDroit() {
    	// Ne fait rien
        return this;
    }

}
