package controleur.observateur;

/**
 * Interface pour l'observateur de l'action qui permet d'ouvrir une demande de livraison.

 */
public interface ActivationOuvrirDemandeObservateur {
	
    /**
     * Notifie les observateurs qu'il faut ou pas activer l'élément du menu qui permet d'ouvrir une demande
     * @param activer Vrai s'il faut s'activer ou se désactiver lors de cette modification
     */
    void notifierObservateurOuvrirDemande(boolean activer);
}
