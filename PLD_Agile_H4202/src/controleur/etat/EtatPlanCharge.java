package controleur.etat;

import java.io.File;

import controleur.ControleurDonnees;
import controleur.commande.CommandeException;
import controleur.commande.CommandeChargerDemande;
import controleur.commande.CommandeChargerPlan;

/**
 * Etat après le chargement du plan
 *
 * @author DELL
 */
public class EtatPlanCharge implements EtatInterface {

    /**
     * Le contrôleur de données
     */
    private final ControleurDonnees CONTROLEURS_DONNEES;

    /**
     * Constructeur de l'état après le chargement du plan
     *
     * @param controleurDonnees Le contrôleur de données
     */
    public EtatPlanCharge(ControleurDonnees controleurDonnees) {
        this.CONTROLEURS_DONNEES = controleurDonnees;
        controleurDonnees.notifierObservateursActivation(false);
    }

    @Override
    public EtatInterface clicSurLivraison(long livraisonId) {
        throw new RuntimeException("Cet état ne permet pas d'interagir avec la liste.");
    }

    @Override
    public EtatInterface chargerPlan(File plan) throws CommandeException {
        new CommandeChargerPlan(CONTROLEURS_DONNEES, plan).executer();
        return this;
    }

    @Override
    public EtatInterface chargerLivraisons(File livraisons) throws CommandeException {
        new CommandeChargerDemande(CONTROLEURS_DONNEES, livraisons).executer();
        return new EtatDemandeChargee(CONTROLEURS_DONNEES);
    }

    @Override
    public EtatInterface clicSurPlan(long intersectionId) {
        throw new RuntimeException("Cet état ne permet pas d'interagir avec le plan.");
    }

    @Override
    public EtatInterface calculerTournee() {
        throw new RuntimeException("Cet état ne permet pas de calculer la tournee");
    }

    @Override
    public EtatInterface clicDroit() {
        // Ne fait rien
        return this;
    }

}
