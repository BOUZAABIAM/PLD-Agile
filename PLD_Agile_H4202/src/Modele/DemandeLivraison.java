/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.*;
import java.sql.Time;

/**
 *
 * @author Elise
 */
public class DemandeLivraison {

    final private Intersection entrepot;
    final private Time heureDepart;
    final private Map<Long, Livraison> livraisons;

    public DemandeLivraison(Intersection entrepot, Time heureDepart, Map<Long, Livraison> livraison) {
        this.entrepot = entrepot;
        this.heureDepart = heureDepart;
        this.livraisons = livraison;
    }

    public Intersection getEntrepot() {
        return entrepot;
    }

    public Time getHeureDepart() {
        return heureDepart;
    }

    public Map<Long, Livraison> getLivraison() {
        return livraisons;
    }

    /**
     * Ajoute une livraison à la liste de livraisons de la demande de livraison
     *
     * @param livr la livraison à ajouter
     */
    public void addLivraison(Livraison livr) {
        livraisons.put(livr.getAdresse().getId(), livr);
    }

    @Override
    public String toString() {
        return "DemandeLivraison{" + "entrepot=" + entrepot.getId() + ", heureDepart=" + heureDepart + ", " + livraisons + '}';
    }

}
