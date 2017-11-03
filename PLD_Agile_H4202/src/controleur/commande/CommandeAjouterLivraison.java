package controleur.commande;

import controleur.ControleurDonnees;
import Modele.Livraison;

/**
 * Répresente une commande d'ajout de livraison
 */
public class CommandeAjouterLivraison extends CommandeAnnulable {

    /**
     * Le cotrôleur de données
     */
    private final ControleurDonnees controleurDonnees;
    
    /**
     * L'identifiant de la livraison avant la nouvelle livraison
     */
    private final long idLivraisonAvant;
    
    /**
     * L'identifiant de l'intersection de la nouvelle livraison
     */
    private final long idIntersectionLivraison;

    /**
     * Crée une nouvelle de commande d'ajout de livraison
     *
     * @param idLivraisonAvant identifiant de la livraison qui se trouve avant celle qu'on veut ajouter
     * @param idIntersectionLivraison intersection où on veut ajouter la livraison
     */
    public CommandeAjouterLivraison(ControleurDonnees controleurDonnees, long idLivraisonAvant, long idIntersectionLivraison) {
        this.controleurDonnees = controleurDonnees;
        this.idLivraisonAvant = idLivraisonAvant;
        this.idIntersectionLivraison = idIntersectionLivraison;
    }


    @Override
    public void executer() throws CommandeException {
       // A definir...............
    }

    @Override
    public void annuler() {
       // A definir..........................................
    }

}
