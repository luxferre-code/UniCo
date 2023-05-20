package ullile.sae201.graphe;

import ullile.sae201.Teenager;
import ullile.sae201.exception.RequirementNotFound;

/**
 * AffectationUtil class
 * @author Romain Degez, Valentin Thuillier
 */
public class AffectationUtil {

    private static final int MAXHOBBIESCOUNT = 3;

    /** Calcule le poids de l’arête entre host et visitor dans le graphe modèle.
     * Doit faire appel à la méthode compatibleWithGuest(Teenager) de Teenager.
     * Peut avoir d’autres paramètres si nécessaire.
     * @param host (Teenager) - The teenager
     * @param visitor (Teenager) - The other teenager
     * @return (double) - The edge weight
     */
    public static double weight(Teenager host, Teenager visitor) {
        double weigh = 100;
        try {
            if(!host.compatibleWithGuestGraphe(visitor)) weigh += 100;
        } catch(RequirementNotFound e) {
            weigh += 100;
        }


        try {
            weigh -= hobbiesWeight(host, visitor);
        } catch(RequirementNotFound e) {
            System.out.println("Une des deux personnes n'a pas de hobbies définis !");
        }

        return weigh;
    }

    /**
     * Calculate the weight value with the hobbies to remove from the total weight
     * @param host (Teenager) - The teenager
     * @param visitor (Teenager) - The other teenager
     * @return (double) - The edge weight got from the hobbies
     */
    public static double hobbiesWeight(Teenager host, Teenager visitor) throws RequirementNotFound {
        double hobbiesCount = 0.0;
        for(String hobby : host.getHobbies()){
            if(visitor.getHobbies().contains(hobby) && hobbiesCount < MAXHOBBIESCOUNT) {
                hobbiesCount++;
            }
        }
        return hobbiesCount;
    }
}
