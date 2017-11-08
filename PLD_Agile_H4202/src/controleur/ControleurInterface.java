package controleur;

import Modele.DemandeLivraison;
import Modele.Intersection;
import Modele.Livraison;
import java.io.File;

import controleur.commande.CommandeException;
import controleur.observateur.*;
import Modele.Plan;
import com.itextpdf.text.Document;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Joue le rôle de façade pour le controleur. La vue ne vera que cette façade pour appeler les méthodes du controleur
 */
public interface ControleurInterface {

    /**
     * Ajoute un observateur pour l'activation des fonctionnalités principales de l'application
     * @param observeur
     */
    void ajouterActivationFonctionnalitesObservateur(ActivationFonctionnalitesObservateur observeur);

    /**
     * Ajoute un observateur au changement du modèle
     * @param observeur
     */
    void ajouterModeleObservateur(ModeleObservateur observeur);

    /**
     * Ajoute un observateur des changement du plan
     * @param planObserveur
     */
    void ajouterActivationOuvrirDemandeObservateur(ActivationOuvrirDemandeObservateur planObserveur);
    
    /**
     * Ajoute un observateur du chargement du plan
     * @param chargementPlanObserveur
     */
    void ajouterActivationOuvrirPlanObservateur(ActivationOuvrirPlanObservateur chargementPlanObserveur);
    
    /**
     * Ajoute un observateur à la tournée
     * @param tourneeObserveur
     */
    void ajouterTourneeObservateur(ActivationFonctionnalitesObservateur tourneeObserveur);

    /**
     * Ajoute un observateur à l'annulation d'une commande
     * @param annulerCommandeObserveur
     */
    void ajouterAnnulerCommandeObservateur(AnnulerCommandeObservateur annulerCommandeObserveur);

    /**
     * Ajoute un observateur au rétablissement d'une commande
     * @param retablirCommandeObserveur
     */
    void ajouterRetablirCommandeObservateur(RetablirCommandeObservateur retablirCommandeObserveur);
    

    /**
     * Ajoute un observateur des messages envoyés
     * @param obs
     */
    void ajouterMessageObservateur(MessageObservateur obs);

    void ajouterPlanChargeObserveur(PlanChargeObservateur planChargeObservateur);

    /**
     * Appel quand il y a un clic sur plan
     * @param intersectionId  L'identifiant de l'intersection cliqué
     */
    void clicSurPlan(long intersectionId);
    
    /**
     * Appel quand il y a un clic sur une livraison
     * @param livraisonId L'identifiant de la livraison
     */
    void clicSurLivraison(long livraisonId);

    /**
     * Appel lors d'un clic sur Annuler
     */
    void clicAnnuler();

    /**
     * Appel lors d'un clic sur Rétablir
     */
    void clicRetablir();

    /**
     * Cette methode essaye de convertir un fichier XML dans sa représentation
     * d'objets.
     *
     * @param fichierPlan Objet File qui représente le fichier XML
     * @throws Exception Lance une exception s'il y a une erreur lors du chargement des objets
     */
    Plan parserPlan(File fichierPlan) throws Exception;

    /**
     * Cette methode essaye de convertir un fichier XML dans sa représentation
     * d'objets.
     *
     * @param fichierLivraisons Objet File qui représente le fichier XML
     * @throws Exception Lance une exception s'il y a une erreur lors du chargement des objets
     */
    DemandeLivraison parserLivraisons(File fichierLivraisons) throws Exception;

    /**
     * Appel lors du clic pour passer dans le mode d'ajout
     */
    void clicOutilAjouter();

   
    /**
     * Appel lors du clic droit
     */
    void clicDroit();
    
    
    /**
     * Retourne le plande la ville
     * @return Le plan de la ville actuellement chargé
     */
    Plan getPlanDeVille();

    /**
     * Appel lors du clic sur le calcul de la tournée
     * @return
     */
    List<ArrayList<Intersection>> calculTournee();
    
    List<Time[]> calculDuree();
    
    /**
     * Génère la feuille de route
     * @param fichier Le fichier dans lequel on devra écrire la feuille de route
     * @throws CommandeException Une erreur lors de l'exécution de la commande de génération
     */
    void feuilleDeRoute(Document document);
    void annuler();
    Livraison getLivraisonByID(long id);
    List<ArrayList<Intersection>> ajouterLivraison(long idAdd, long idPrec);
    List<ArrayList<Intersection>> supprimerLivraison(long idSuppr);

}
