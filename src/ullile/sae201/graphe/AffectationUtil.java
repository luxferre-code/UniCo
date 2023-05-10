package ullile.sae201.graphe;

import ullile.sae201.Teenager;

/**
 * AffectationUtil class
 * @author Romain Degez
 */
public class AffectationUtil {

    /** Calcule le poids de l’arête entre host et visitor dans le graphe modèle.
     * Doit faire appel à la méthode compatibleWithGuest(Teenager) de Teenager.
     * Peut avoir d’autres paramètres si nécessaire.
     * @param host (Teenager) - The teenager
     * @param visitor (Teenager) - The other teenager
     * @return (double) - The edge weight
     */
    public static double weight (Teenager host, Teenager visitor /**, ... */) {
        double weigh = 100;
        if(!host.compatibleWithGuestGraphe(visitor)) weigh += 100;

        return weigh;
    }

    // ... ajouter toutes autres méthodes jugées nécessaires

    /**
     * Calculate the weight value withe the hobbies to remove from the total weight
     * @param host (Teenager) - The teenager
     * @param visitor (Teenager) - The other teenager
     * @return (double) - The edge weight got from the hobbies
     */
    public static double hobbies_weight(Teenager host, Teenager visitor){
        double hobbies_weight = 0;
        // boucle ajutant 2 chaque fois que le hobbie de host est contenue dans hobbies de visitor 
        return hobbies_weight;
    }
}
