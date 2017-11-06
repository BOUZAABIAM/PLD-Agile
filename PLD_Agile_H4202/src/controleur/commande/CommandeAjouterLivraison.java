package controleur.commande;

import controleur.ControleurDonnees;
import Vue.IHMLivraisons;


/**
 * Répresente une commande d'ajout de livraison
 */
public class CommandeAjouterLivraison extends CommandeAnnulable {
    IHMLivraisons ihm;

    /**
     * Le cotrôleur de données
     */
    private final ControleurDonnees controleurDonnees;
    
    /**
     * L'identifiant de la livraison avant la nouvelle livraison
     */
    private final long idLivraisonPrecedent;
    
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
    public CommandeAjouterLivraison(ControleurDonnees controleurDonnees, long idLivraisonPrecedent, long idIntersectionLivraison, IHMLivraisons ihm) {
        this.controleurDonnees = controleurDonnees;
        this.ihm = ihm;
        this.idLivraisonPrecedent = idLivraisonPrecedent;
        this.idIntersectionLivraison = idIntersectionLivraison;
    }


    @Override
    public void executer() throws CommandeException {

    }

    @Override
    public void annuler() {
       // A definir..........................................
    }

}
