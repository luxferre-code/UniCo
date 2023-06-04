package ullile.sae201.graphe;

import ullile.sae201.CriterionName;
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
        double weight = 100;
        try {
            if(!host.compatibleWithGuestGraphe(visitor)) weight += 100;
        } catch(RequirementNotFound e) {
            weight += 100;
        }

        try {
            weight -= hobbiesWeight(host, visitor);
        } catch(RequirementNotFound e) {
            System.out.println("Une des deux personnes n'a pas de hobbies définis !");
        }

        weight -= historyWeight(host, visitor);        

        return weight;
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

    /**
     * Calculate the weight value with the history to remove from the total weight
     * @param host (Teenager) - The teenager
     * @param visitor (Teenager) - The other teenager
     * @return (double) - The edge weight got from the history
     */
    public static double historyWeight(Teenager host, Teenager visitor){
        if(host.getHistory().equals(visitor)){
            try {
                if(Teenager.booleanConverter(host, CriterionName.HISTORY, "same")){
                    if(Teenager.booleanConverter(visitor, CriterionName.HISTORY, "same")){
                        return 100;
                    }
                }
            } catch(RequirementNotFound e1) {
                try {
                    if(Teenager.booleanConverter(host, CriterionName.HISTORY, "same")){
                        return 4;
                    }
                } catch(RequirementNotFound e2) {
        
                }
                try {
                    if(Teenager.booleanConverter(visitor, CriterionName.HISTORY, "same")){
                        return 4;
                    }
                } catch(RequirementNotFound e3) {
        
                }
            }
        }
        return 0.0;
    }
}
