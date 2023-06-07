package ullile.sae201.graphe;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import ullile.sae201.Criterion;
import ullile.sae201.CriterionName;
import ullile.sae201.Teenager;
import ullile.sae201.exception.RequirementNotFound;
import fr.ulille.but.sae2_02.graphes.Arete;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;


/**
 * AffectationUtil class
 * @author Romain Degez, Valentin Thuillier
 */
public class AffectationUtil {

    private static final int MAXHOBBIESCOUNT = 3;
    private static final int MAXFOODCOUNT = 3;

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
            if(!host.compatibleWithGuest(visitor)) weight += 100;
        } catch(RequirementNotFound e) {
            weight += 100;
        }

        try {
            weight -= hobbiesWeight(host, visitor);
        } catch(RequirementNotFound e) {
            System.out.println("Une des deux personnes n'a pas de hobbies définis !");
        }

        weight -= historyWeight(host, visitor);  
        
        weight -= genderWeight(host, visitor);

        weight -= ageWeight(host, visitor);

        return weight;
    }

    /** Calcule le poids de l’arête entre host et visitor dans le graphe modèle (Graphe Version 1).
     * Doit faire appel à la méthode compatibleWithGuest(Teenager) de Teenager.
     * Peut avoir d’autres paramètres si nécessaire.
     * @param host (Teenager) - The teenager
     * @param visitor (Teenager) - The other teenager
     * @return (double) - The edge weight
     */
    public static double weightV1(Teenager host, Teenager visitor) {
        double weight = 100;
        try {
            if(!host.compatibleWithGuestGrapheV1(visitor)) weight += 100;
        } catch(RequirementNotFound e) {
            weight += 100;
        }

        try {
            weight -= hobbiesWeight(host, visitor);
        } catch(RequirementNotFound e) {
            System.out.println("Une des deux personnes n'a pas de hobbies définis !");
        }

        return weight;
    }

        /** Calcule le poids de l’arête entre host et visitor dans le graphe modèle.
     * Doit faire appel à la méthode compatibleWithGuest(Teenager) de Teenager.
     * Peut avoir d’autres paramètres si nécessaire.
     * @param host (Teenager) - The teenager
     * @param visitor (Teenager) - The other teenager
     * @return (double) - The edge weight
     */
    public static double weightV2(Teenager host, Teenager visitor) {
        double weight = 100;
        try {
            if(!host.compatibleWithGuestGrapheV2(visitor)) weight += 100;
        } catch(RequirementNotFound e) {

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
    private static double hobbiesWeight(Teenager host, Teenager visitor) throws RequirementNotFound {
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
    private static double historyWeight(Teenager host, Teenager visitor){
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

    /**
     * Calculate the weight value to remove from the total weight with the gender criterion and gender of both Teenager  
     * @param host (Teenager) - The teenager
     * @param visitor (Teenager) - The other teenager
     * @return (double) - The edge weight got from the pair_gender and gender
     */
    private static double genderWeight(Teenager host, Teenager visitor) {
        double genderWeight = 0.0;
        String genderHost = "", genderVisitor = "";
        try {
            genderHost = host.getCriterionValue(CriterionName.GENDER) ;
            genderVisitor = visitor.getCriterionValue(CriterionName.GENDER) ;
        } catch (Exception e) {
            System.out.println("Une des deux personnes n'a pas de genre définis !");
        }
        try {
            if(host.getCriterionValue(CriterionName.PAIR_GENDER).equals(genderVisitor)){
                genderWeight++;
            }
        } catch(RequirementNotFound e) {
            genderWeight++;
        }
        try {
            if(visitor.getCriterionValue(CriterionName.PAIR_GENDER).equals(genderHost)){
                genderWeight++;
            }
        } catch(RequirementNotFound e) {
            genderWeight++;
        }
        return genderWeight;
    }

    /**
     * Calculate the weight value to remove from the total weight with the difference between the dateBirth of both Teenager  
     * @param host (Teenager) - The teenager
     * @param visitor (Teenager) - The other teenager
     * @return (double) - The edge weight got from the dateBirth
     */
    private static double ageWeight(Teenager host, Teenager visitor) {
        LocalDate hostBirthDate = LocalDate.of(host.getDateNaiss().getYear(), host.getDateNaiss().getMonth(), host.getDateNaiss().getDay());
        LocalDate visitorBirthDate = LocalDate.of(visitor.getDateNaiss().getYear(), visitor.getDateNaiss().getMonth(), visitor.getDateNaiss().getDay());
        if(hostBirthDate.until(visitorBirthDate, ChronoUnit.MONTHS) <= 18){
            return 2;
        }
        return 0;
    }

    /**
     * Calculate the weight value to add to the total weight with the guest_food and host_food criterion of both Teenager for weightAdvanced 
     * @param host (Teenager) - The teenager
     * @param visitor (Teenager) - The other teenager
     * @return (double) - The edge weight got from the guest_food and host_food
     */
    private static double compatibleFoodWeightAdvanced(Teenager host, Teenager visitor) {
        boolean temp;
        double foodWeight = 0.0;
        String[] guestFoods, hostFoods;

        try { guestFoods = visitor.getCriterionValue(CriterionName.GUEST_FOOD).split(","); }
        catch (RequirementNotFound e) { return 0.0; }

        try { hostFoods = host.getCriterionValue(CriterionName.HOST_FOOD).split(","); }
        catch (RequirementNotFound e) { return guestFoods.length ; }

        for(String gfood : guestFoods){
            temp = false;
            for(String hfood : hostFoods){
                if(gfood.equals(hfood)){
                    temp = true;
                    break;
                }
            }
            if(!temp || foodWeight <MAXFOODCOUNT){
                foodWeight++;
            }
        }
        return foodWeight;
    }

    /**
     * Calculate the weight value with the history to add to the total weight for weightAdvanced
     * @param host (Teenager) - The teenager
     * @param visitor (Teenager) - The other teenager
     * @return (double) - The edge weight got from the history
     */
    private static double compatibleHistoryWeightAdvanced(Teenager host, Teenager visitor){
        if(host.getHistory() == null){
            return 0.0;
        }
        if(!host.getHistory().equals(visitor)){
            return 0.0;
        }
        try {
            if(Teenager.booleanConverter(host, CriterionName.HISTORY, "other")){
                return 6.0;
            }
        } catch (Exception e) {
            return 0.0;
        }
        try {
            if(Teenager.booleanConverter(visitor, CriterionName.HISTORY, "other")){
                return 6.0;
            }
        } catch (Exception e) {
            return 0.0;
        }
        return 0.0;
    }

    /**
     * Calculate the weight value with the history to remove from the total weight for weightAdvanced
     * @param host (Teenager) - The teenager
     * @param visitor (Teenager) - The other teenager
     * @return (double) - The edge weight got from the history
     */
    private static double historyWeightAdvanced(Teenager host, Teenager visitor){
        if(host.getHistory().equals(visitor)){
            try {
                if(Teenager.booleanConverter(host, CriterionName.HISTORY, "same")){
                    if(Teenager.booleanConverter(visitor, CriterionName.HISTORY, "same")){
                        return 6;
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

        /**
     * Calculate the weight value with the allergy to add to the total weight for weightAdvanced
     * @param host (Teenager) - The teenager
     * @param visitor (Teenager) - The other teenager
     * @return (double) - The edge weight got from the allergy
     */
    private static double compatibleAllergyWeightAdvanced(Teenager host, Teenager visitor){
        try {
            if(Teenager.booleanConverter(visitor,CriterionName.GUEST_ANIMAL_ALLERGY,Criterion.YES) && Teenager.booleanConverter(host,CriterionName.HOST_HAS_ANIMAL,Criterion.YES)){
                return 3;
            }
        } catch (Exception e) {
            return 0.0;
        }
        return 0.0;
    }


        /** Calcule le poids de l’arête entre host et visitor dans le graphe modèle en prenant l'incompatibilité comme un malus 
     * Doit faire appel à la méthode compatibleWithGuest(Teenager) de Teenager.
     * Peut avoir d’autres paramètres si nécessaire.
     * @param host (Teenager) - The teenager
     * @param visitor (Teenager) - The other teenager
     * @return (double) - The edge weight
     */
    public static double weightAdvanced(Teenager host, Teenager visitor) {
        double weight = 100;

        try {
            weight -= hobbiesWeight(host, visitor);
        } catch(RequirementNotFound e) {
            System.out.println("Une des deux personnes n'a pas de hobbies définis !");
        }  
        
        weight -= genderWeight(host, visitor);

        weight -= ageWeight(host, visitor);

        weight += compatibleFoodWeightAdvanced(host, visitor);

        weight += compatibleHistoryWeightAdvanced(host, visitor);

        weight -= historyWeightAdvanced(host, visitor);

        weight += compatibleAllergyWeightAdvanced(host, visitor);

        return weight;
    }

    /**
     * Crée un graphe GrapheNonOrienteValue<Teenager> à partir d'une liste de Teenager hosts et d'une liste de Teenager guest en définissant les Teenager comme sommets du graphe et en définissant entre chaque étudiant host et chaque étudiants guest une arête dont le poids est défini par la fonction {@link #weight(Teenager, Teenager) }
     * @param hosts Une liste de Teenager hôtes
     * @param guests Une lsite de Teenager visiteurs
     * @return (GrapheNonOrienteValue<Teenager>) - Le graphe créé à partir des étudiants
     */
    public static GrapheNonOrienteValue<Teenager> creerGrapheTeenager(ArrayList<Teenager> hosts, ArrayList<Teenager> guests){
        GrapheNonOrienteValue<Teenager> teenagerGraphe = new GrapheNonOrienteValue<Teenager>();
        for (Teenager host : hosts) {
            teenagerGraphe.ajouterSommet(host);
        }
        for (Teenager guest : guests) {
            teenagerGraphe.ajouterSommet(guest);
        }

        for (Teenager host : hosts) {
            for (Teenager guest : guests) {
                teenagerGraphe.ajouterArete(host, guest, weight(host, guest));
            }
        }
        return teenagerGraphe;
    }

    /**
     * Crée un graphe GrapheNonOrienteValue<Teenager> à partir d'une liste de Teenager hosts et d'une liste de Teenager guest en définissant les Teenager comme sommets du graphe et en définissant entre chaque étudiant host et chaque étudiants guest une arête dont le poids est défini par la fonction {@link #weightV1(Teenager, Teenager) }
     * @param hosts Une liste de Teenager hôtes
     * @param guests Une lsite de Teenager visiteurs
     * @return (GrapheNonOrienteValue<Teenager>) - Le graphe créé à partir des étudiants
     */
    public static GrapheNonOrienteValue<Teenager> creerGrapheTeenagerV1(ArrayList<Teenager> hosts, ArrayList<Teenager> guests){
        GrapheNonOrienteValue<Teenager> teenagerGraphe = new GrapheNonOrienteValue<Teenager>();
        for (Teenager host : hosts) {
            teenagerGraphe.ajouterSommet(host);
        }
        for (Teenager guest : guests) {
            teenagerGraphe.ajouterSommet(guest);
        }

        for (Teenager host : hosts) {
            for (Teenager guest : guests) {
                teenagerGraphe.ajouterArete(host, guest, weightV1(host, guest));
            }
        }
        return teenagerGraphe;
    }

    /**
     * Crée un graphe GrapheNonOrienteValue<Teenager> à partir d'une liste de Teenager hosts et d'une liste de Teenager guest en définissant les Teenager comme sommets du graphe et en définissant entre chaque étudiant host et chaque étudiants guest une arête dont le poids est défini par la fonction {@link #weightV2(Teenager, Teenager) }
     * @param hosts Une liste de Teenager hôtes
     * @param guests Une lsite de Teenager visiteurs
     * @return (GrapheNonOrienteValue<Teenager>) - Le graphe créé à partir des étudiants
     */
    public static GrapheNonOrienteValue<Teenager> creerGrapheTeenagerV2(ArrayList<Teenager> hosts, ArrayList<Teenager> guests){
        GrapheNonOrienteValue<Teenager> teenagerGraphe = new GrapheNonOrienteValue<Teenager>();
        for (Teenager host : hosts) {
            teenagerGraphe.ajouterSommet(host);
        }
        for (Teenager guest : guests) {
            teenagerGraphe.ajouterSommet(guest);
        }

        for (Teenager host : hosts) {
            for (Teenager guest : guests) {
                teenagerGraphe.ajouterArete(host, guest, weightV2(host, guest));
            }
        }
        return teenagerGraphe;
    }

        /**
     * Crée et affiche l'appariement optimal à partir d'un graphe de Teenager, d'une liste de Teenager hosts et d'une liste de Teenager guest grâce à la méthode {@link CalculAffectation#calculerAffectation() }
     * @param hosts Une liste de Teenager hôtes
     * @param guests Une lsite de Teenager visiteurs
     * @return (GrapheNonOrienteValue<Teenager>) - Le graphe créé à partir des étudiants
     */
    public static String afficherAppariement(ArrayList<Arete<Teenager>> appariment){
        String affichage = "";;
        for (Arete<Teenager> arete : appariment) {
           affichage += arete.getExtremite1() + "--" + arete.getExtremite2() + "(" + arete.getPoids() + ")\n"; 
        }
        return affichage;
    }




}
