package controleur.observateur;

/**
 * Joue le rôle d'observeur quand un nouveau plan a été chargé.
 * @author DELL
 */
public interface PlanChargeObservateur {
    /**
     * Notifie la vue qu'un nouveau plan a été chargé.
     */
    void notifierObservateursPlanCharge();
}
