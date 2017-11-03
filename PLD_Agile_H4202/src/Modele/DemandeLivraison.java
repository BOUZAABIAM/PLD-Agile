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
    
    private Intersection entrepot;
    private Time heureDepart;
    private Map<Long, Livraison> livraisons;

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

    @Override
    public String toString() {
        return "DemandeLivraison{" + "entrepot=" + entrepot.getId() + ", heureDepart=" + heureDepart + ", " + livraisons + '}';
    }
    
    
    
}
