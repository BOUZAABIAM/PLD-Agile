package controleur.etat;

import java.io.File;

import controleur.ControleurDonnees;
import controleur.commande.CommandeException;

/**
 * Cet état correspond à l'état dans lequel on se trouve quand on clic sur le
 * bouton ajouter. Dans cet état on doit cliquer sur une intersection dans le
 * plan pour passer à l'étatAjout2
 *
 * @author DELL
 */
public class EtatAjout implements EtatInterface {

    /**
     * Le contrôleur de données
     */
    private final ControleurDonnees DONNEES;

    /**
     * Constructeur du premier état d'ajout
     *
     * @param donnees Le contrôleur de données
     */
    public EtatAjout(ControleurDonnees donnees) {
        this.DONNEES = donnees;
        donnees.notifierObservateurOuvrirPlan(false);
        donnees.notifierObservateurOuvrirDemande(false);
        donnees.notifierObservateursMessage("[AJOUT] Où souhaitez-vous ajouter une livraison ? Choisissez l'adresse de livraison en cliquant sur une intersection de le plan. Clic droit pour sortir du mode d'ajout.");
    }

    @Override
    public EtatInterface clicSurLivraison(long livraisonId) {
        DONNEES.notifierObservateursMessage("Veuillez choisir l'adresse de la livraison en cliquant sur une intersection sur le plan. Clic droit pour sortir du mode d'ajout?");
        return this;
    }

    @Override
    public EtatInterface chargerPlan(File plan) throws CommandeException {
        throw new RuntimeException("Cet état ne permet pas de charger un plan");
    }

    @Override
    public EtatInterface chargerLivraisons(File livraisons) throws CommandeException {
        throw new RuntimeException("Cet état ne permet pas de charger une demande de livraison");
    }

    @Override
    public EtatInterface clicSurPlan(long intersectionId) {
        return new EtatAjout2(DONNEES, intersectionId);
    }

    @Override
    public EtatInterface calculerTournee() {
        throw new RuntimeException("Cet état ne permet pas de calculer la tournée");
    }

    @Override
    public EtatInterface clicDroit() {
        DONNEES.notifierObservateursMessage("Choisissez une action à effectuer pour modifier la tournée à votre guise.");
        return new EtatPrincipal(DONNEES);
    }

}
