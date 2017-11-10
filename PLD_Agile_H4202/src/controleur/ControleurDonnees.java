package controleur;

import java.util.ArrayList;
import java.util.Collection;

import controleur.observateur.*;
import Modele.Plan;
import controleur.commande.Commande;
import controleur.commande.Historique;

/**
 * Cette classe contient les données nécessaires pour la gestion des états. On
 * pourrait dire qu'elle représente seulement des données et devrait du coup
 * mieux être située dans le package modèle. Par contre elle est liée à une
 * seule IHM, du coup il y a de bonnes raisons de la laisser ici dans le
 * controleur.
 *
 * @author DELL
 */
public class ControleurDonnees {

    /**
     * Collection des observateurs (pour GUI avec functionalités réduites si
     * plan et livraisons ne sont pas encore chargés)
     */
    private final Collection<ActivationFonctionnalitesObservateur> ACTIVATION_FONCTIONNALITES_OBSERVATEURS = new ArrayList<ActivationFonctionnalitesObservateur>();

    /**
     * Collection des observateurs pour le modèle
     */
    private final Collection<ModeleObservateur> MODELE_OBSERVATEURS = new ArrayList<ModeleObservateur>();

    /**
     * Collection des observateurs pour la possibilité d'annuler des
     * intéractions effectuées
     */
    private final Collection<AnnulerCommandeObservateur> ANNULER_COMMANDE_OBSERVATEURS = new ArrayList<AnnulerCommandeObservateur>();

    /**
     * Collection des observateurs pour la possibilité de rétablir des
     * intéractions effectuées.
     */
    private final Collection<RetablirCommandeObservateur> RETABLIR_COMMANDE_OBSERVATEURS = new ArrayList<RetablirCommandeObservateur>();

    /**
     * Collection des observateurs des modifications du plan
     */
    private final Collection<ActivationOuvrirDemandeObservateur> PLAN_OBSERVATEURS = new ArrayList<ActivationOuvrirDemandeObservateur>();

    /**
     * Collection des observateurs du chargement du plan
     */
    private final Collection<ActivationOuvrirPlanObservateur> CHARGEMENT_PLAN_OBSERVATEURS = new ArrayList<ActivationOuvrirPlanObservateur>();

    /**
     * Collection des observateurs d'activation/désactivation de composants
     */
    private final Collection<ActivationFonctionnalitesObservateur> TOURNEE_OBSERVATEURS = new ArrayList<ActivationFonctionnalitesObservateur>();

    /**
     * Collection des observateurs des messages envoyés à la vue
     */
    private final Collection<MessageObservateur> MESSAGE_OBSERVATEURS = new ArrayList<MessageObservateur>();

    private final Collection<PlanChargeObservateur> PLAN_CHARGE_OBSERVATEURS = new ArrayList<>();

    /**
     * Le plan de la ville
     */
    private Plan plan = null;

    /**
     * L'historique des commandes
     */
    private Historique hist = new Historique();

    /**
     * Retourne le plan de la ville
     *
     * @return Le plan de la ville
     */
    public Plan getPlan() {
        return plan;
    }

    /**
     * Affecte le plan de la ville
     *
     * @param plan Le nouveau plan de la ville
     */
    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Historique getHist() {
        return hist;
    }

    /**
     * Affecte l'historique
     *
     * @param hist Le nouvel historique
     */
    public void setHistorique(Historique hist) {
        this.hist = hist;
    }

    /**
     * Ajoute un observateur d'activation
     *
     * @param obs L'objet observateur
     */
    public void ajouterActivationObservateur(ActivationFonctionnalitesObservateur obs) {
        ACTIVATION_FONCTIONNALITES_OBSERVATEURS.add(obs);
    }

    /**
     * Ajoute un observateur du modèle
     *
     * @param obs L'objet observateur
     */
    public void ajouterModeleObservateur(ModeleObservateur obs) {
        MODELE_OBSERVATEURS.add(obs);
    }

    /**
     * Ajoute un observateur de la commande annuler
     *
     * @param obs L'objet observateur
     */
    public void ajouterAnnulerCommandeObservateur(AnnulerCommandeObservateur obs) {
        ANNULER_COMMANDE_OBSERVATEURS.add(obs);
    }

    /**
     * Ajoute un observateur de la commande rétablir
     *
     * @param obs L'objet observateur
     */
    public void ajouterRetablirCommandeObservateur(RetablirCommandeObservateur obs) {
        RETABLIR_COMMANDE_OBSERVATEURS.add(obs);
    }

    public void ajouterPlanObservateur(ActivationOuvrirDemandeObservateur planObserveur) {
        PLAN_OBSERVATEURS.add(planObserveur);
    }

    /**
     * Ajoute un observateur du chargement du plan
     *
     * @param chargementPlanObserveur L'objet observateur
     */
    public void ajouterChargementPlanObservateur(ActivationOuvrirPlanObservateur chargementPlanObserveur) {
        CHARGEMENT_PLAN_OBSERVATEURS.add(chargementPlanObserveur);
    }

    /**
     * Ajoute un observateur pour l'activation de la génération de la tournée
     *
     * @param tourneeObserveur L'objet observateur
     */
    void ajouterTourneeObservateur(ActivationFonctionnalitesObservateur tourneeObserveur) {
        TOURNEE_OBSERVATEURS.add(tourneeObserveur);
    }

    /**
     * Ajoute un observateur pour l'envoie d'un message
     *
     * @param obs L'objet observateur
     */
    void ajouterMessageObservateur(MessageObservateur obs) {
        MESSAGE_OBSERVATEURS.add(obs);
    }

    /**
     * Ajoute une commande à l'historique
     *
     * @param commande Une commande exécutée
     */
    public void ajouterCommande(Commande commande) {
        hist.ajouterCommande(commande);
    }

    /**
     * Notifie les observateurs du plan
     *
     * @param activer Vrai s'il faut envoyer un message d'activation aux
     * observateurs
     */
    public void notifierObservateurOuvrirDemande(boolean activer) {
        PLAN_OBSERVATEURS.forEach(planObserveur -> planObserveur.notifierObservateurOuvrirDemande(activer));
    }

    /**
     * Notifie les observateurs du chargement du plan
     *
     * @param activer Vrai s'il faut envoyer un message d'activation aux
     * observateurs
     */
    public void notifierObservateurOuvrirPlan(boolean activer) {
        CHARGEMENT_PLAN_OBSERVATEURS.forEach(chargementPlanObserveur -> chargementPlanObserveur.notifierObservateursOuvrirPlan(activer));
    }

    /**
     * Notifie les observateurs de l'activation
     *
     * @param etat Vrai s'il faut activer les observateurs
     */
    public void notifierObservateursActivation(boolean etat) {
        ACTIVATION_FONCTIONNALITES_OBSERVATEURS.forEach(obs -> obs.notifierObservateursActivation(etat));
    }

    /**
     * Notifie les observateurs du changemetn du modèle
     */
    public void notifierObservateursModele() {
        MODELE_OBSERVATEURS.forEach(obs -> obs.notifierObservateursModele());
    }

    /**
     * Notifie les observateurs qu'il y a eu une annulation
     *
     * @param activation Vrai si les observateurs doivent s'activer dans ce cas
     * d'annulation
     */
    public void notifierObservateursAnnuler(boolean activation) {
        ANNULER_COMMANDE_OBSERVATEURS.forEach(obs -> obs.notifierObservateurAnnulerCommande(activation));
    }

    /**
     * Notifie les observateurs qu'il y eu un rétablissement
     *
     * @param activation Vrai si les observateurs doivent s'activer dans ce cas
     * de rétablissement
     */
    public void notifierObservateursRetablir(boolean activation) {
        RETABLIR_COMMANDE_OBSERVATEURS.forEach(obs -> obs.notifierObservateurRetablirCommande(activation));
    }

    /**
     * Notifie les observateurs du calcul de la tournée
     *
     * @param activation Vrai si les observateurs doivent s'activer
     */
    public void notifierObservateursCalculTournee(boolean activation) {
        TOURNEE_OBSERVATEURS.forEach(obs -> obs.notifierObservateursActivation(activation));
    }

    /**
     * Notifie les observateurs qu'il y a un message
     *
     * @param message Le message envoyé
     */
    public void notifierObservateursMessage(String message) {
        MESSAGE_OBSERVATEURS.forEach(obs -> obs.notifierObservateursMessage(message));
    }

    public void notifierPlanChargeObservateur() {
        PLAN_CHARGE_OBSERVATEURS.forEach(obs -> obs.notifierObservateursPlanCharge());
    }

    public void ajouterPlanChargeObservateur(PlanChargeObservateur planChargeObservateur) {
        PLAN_CHARGE_OBSERVATEURS.add(planChargeObservateur);
    }

    /**
     * Efface la liste des commandes à retablir et notifie la vue qu'elle doit
     * désactiver l'élément du menu correspondant
     */
    public void effacerCommandesARetablir() {
        hist.effacerCommandeARetablir();
        notifierObservateursRetablir(false);
    }

    /**
     * Efface la liste des commandes à annuler et notifie la vue qu'il doir
     * desactiver l'élément du menu correspondant
     */
    public void effacerCommandeAAnnuler() {
        hist.effacerCommandesAAnnuler();
        notifierObservateursAnnuler(false);
    }

    /**
     * Efface l'historique (vide les commandes annulable et rétablissable(?))
     */
    public void effacerHistorique() {
        effacerCommandeAAnnuler();
        effacerCommandesARetablir();
    }
}
