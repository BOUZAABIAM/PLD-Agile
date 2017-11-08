package controleur.etat;

import java.io.File;

import controleur.ControleurDonnees;
import controleur.commande.CommandeException;
import controleur.commande.CommandeChargerPlan;

/**
 * Premier état de l'application au lancement. A partir de cet état on ne peut que charger un plan.
 * @author Maxou
 */
public class EtatInitial implements EtatInterface {
	
    /** Le contrôleur de données */
    private final ControleurDonnees CONTROLEUR_DONNEES;

    /**
     * Constructeur de l'état initial
     * @param controleurDonnees Le contrôleur de données
     */
    public EtatInitial(ControleurDonnees controleurDonnees) {
        this.CONTROLEUR_DONNEES = controleurDonnees;
    }

    @Override
    public EtatInterface clicSurLivraison(long livraisonId) {
        throw new RuntimeException("Cet état ne permet pas d'interagir avec la liste.");
    }

    @Override
    public EtatInterface chargerPlan(File plan) throws CommandeException {
        new CommandeChargerPlan(CONTROLEUR_DONNEES, plan).executer();
        return new EtatPlanCharge(CONTROLEUR_DONNEES);
    }

    @Override
    public EtatInterface chargerLivraisons(File livraisons)  throws CommandeException {
        throw new RuntimeException("Cet état ne permet pas de charger un fichier de livraison");
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
