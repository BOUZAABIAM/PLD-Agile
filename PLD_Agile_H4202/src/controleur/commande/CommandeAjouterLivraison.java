package controleur.commande;

import controleur.ControleurDonnees;
import Vue.IHMLivraisons;


/**
 * Répresente une commande d'ajout de livraison
 * @author DELL
 */
public class CommandeAjouterLivraison extends CommandeAnnulable {
    
    /**
     * Notre IHM actuelle
     */
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
     * @param controleurDonnees
     * @param idIntersectionLivraison intersection où on veut ajouter la livraison
     * @param idLivraisonPrecedent id intersection de livraison qui précede la nouvelle livraison à ajouter
     * @param ihm notre IHM actuelle
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

