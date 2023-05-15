package ullile.sae201.graphe;

import ullile.sae201.Teenager;
import ullile.sae201.exception.RequirementNotFound;

/**
 * AffectationUtil class
 * @author Romain Degez, Valentin Thuillier, Eise Leroy
 */
public class AffectationUtil {

    private static final int MAX_HOBBIES_COUNT = 3;
    private static final int HOBBY_WEIGHT = 2;


    /** Calcule le poids de l’arête entre host et visitor dans le graphe modèle.
     * Doit faire appel à la méthode compatibleWithGuest(Teenager) de Teenager.
     * Peut avoir d’autres paramètres si nécessaire.
     * @param host (Teenager) - The teenager
     * @param visitor (Teenager) - The other teenager
     * @return (double) - The edge weight
     */
    public static double weight(Teenager host, Teenager visitor) {
        double weight = 100.0;
        if(!host.compatibleWithGuestGraphe(visitor)) weight += 100;

        try {
            weight -= hobbiesWeight(host, visitor);
        } catch(RequirementNotFound e) {
            System.out.println("Une des deux personnes n'a pas de hobby défini !");
        }

        return weight;
    }

    /**
     * Calculate the weight value withe the hobbies to remove from the total weight
     * @param host (Teenager) - The teenager
     * @param visitor (Teenager) - The other teenager
     * @return (int) - The edge weight got from the hobbies
     */
    public static int hobbiesWeight(Teenager host, Teenager visitor) throws RequirementNotFound {
        int hobbiesCount = 0;
        for(String hobby : host.getHobbies()){
            if(visitor.getHobbies().contains(hobby) && hobbiesCount <= MAX_HOBBIES_COUNT) {
                hobbiesCount++;
            }
        }
        return HOBBY_WEIGHT*hobbiesCount;
    }
}
