package ullile.sae201;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Teenager class
 * @author Valentin Thuillier, Romain Degez
 */
public class Teenager {

    private final String NAME, FORENAME, COUNTRY;
    private final Date DATENAISS;
    private Teenager history = null;

    private HashMap<CriterionName, Criterion> requirements = new HashMap<>(){{
        for (CriterionName criterionName : CriterionName.values()) put(criterionName, new Criterion("", criterionName));
    }};

    /**
     * Teenager constructor
     * @param name (String) - The name of the teenager
     * @param forename (String) - The forename of the teenager
     * @param dateNaiss (Date) - The date of birth of the teenager
     * @param country (String) - The country of the teenager
     */
    public Teenager(String name, String forename, Date dateNaiss, String country) {
        this.NAME = name;
        this.FORENAME = forename;
        this.DATENAISS = dateNaiss;
        this.COUNTRY = country;
    }

    /**
     * Purge the invalid requirements of the teenager
     */
    public void purgeInvalidRequirement() {
        List<CriterionName> toRemove = new ArrayList<>();
        for (CriterionName criterionName : this.requirements.keySet()) {
            if (!this.requirements.get(criterionName).isValid()) {
                toRemove.add(criterionName);
            }
        }
        for (CriterionName criterionName : toRemove) {
            this.requirements.remove(criterionName);
        }
    }

    public boolean addRequirement(CriterionName criterionName, String value) {
        Criterion criterion = new Criterion(value, criterionName);
        if (criterion.isValid()) {
            this.requirements.replace(criterionName, criterion);
            return true;
        }
        return false;
    }

    public boolean addRequirement(Criterion criterion) {
        if (criterion.isValid()) {
            this.requirements.replace(criterion.getLabel(), criterion);
            return true;
        }
        return false;
    }

    /**
     * Check if the teenager is compatible with an another teenager
     * @param guest (Teenager) - The other teenager
     * @return (boolean) - True if the teenager is compatible with the other teenager, false otherwise
     */
    public boolean compatibleWithGuest(Teenager guest) {
        if(booleanConverter(guest,CriterionName.GUEST_ANIMAL_ALLERGY,Criterion.YES) && booleanConverter(this,CriterionName.HOST_HAS_ANIMAL,Criterion.YES)){
            return false;
        }
        if(!this.compatibleFood(guest)){ return false; }
        if(!this.compatibleHistory(guest)) return false;
        return true;
    }

    /**
     * Convert a requirement in boolean
     * @param t (Teenager) - The teenager
     * @param cn (CriterionName) - The name of the criterion
     * @param criterion (String) - The value to test
     * @return (boolean) - True if the requirement is valid, false otherwise
     */
    public static boolean booleanConverter(Teenager t, CriterionName cn, String criterion){
        return t.getCriterionValue(cn).equals(criterion);
    }

    /**
     * Check if the teenager's food is compatible with an another teenager's food
     * @param guest (Teenager) - The other teenager
     * @return (boolean) - True if the teenager'food is compatible with the other teenager's food, false otherwise
     */
    private boolean compatibleFood(Teenager guest){
        boolean temp;
        String[] hostFoods = this.getCriterionValue(CriterionName.HOST_FOOD).split(",");
        String[] guestFoods = guest.getCriterionValue(CriterionName.GUEST_FOOD).split(",");
        for(String gfood : guestFoods){
            temp = false;
            for(String hfood : hostFoods){
                if(gfood.equals(hfood)){
                    temp = true;
                }
            }
            if(!temp){
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the teenager can be with the same teenager than the last year
     * @param guest (Teenager) - The other teenager
     * @return (boolean) - False if the teenager doesn't want to be with the same teenager than the last year, true otherwise
     */
    private boolean compatibleHistory(Teenager guest){
        if(!this.history.equals(guest)){
            return true;
        }
        if(booleanConverter(this, CriterionName.HISTORY, "other") || booleanConverter(guest, CriterionName.HISTORY, "other")){
            return false;
        }
        return true;
    }

    /**
     * Get the name of the teenager
     * @return (String) - The name of the teenager
     */
    public String getName() {
        return NAME;
    }

    /**
     * Get the forename of the teenager
     * @return (String) - The forename of the teenager
     */
    public String getForename() {
        return FORENAME;
    }

    /**
     * Get the country of the teenager
     * @return (String) - The country of the teenager
     */
    public String getCountry() {
        return COUNTRY;
    }

    /**
     * Get the date of birth of the teenager
     * @return (Date) - The date of birth of the teenager
     */
    public Date getDateNaiss() {
        return DATENAISS;
    }

    /**
     * Get the requirements of the teenager
     * @return (HashMap<CriterionName, Criterion>) - The requirements of the teenager
     */
    public HashMap<CriterionName, Criterion> getRequirements() {
        return requirements;
    }

    /**
     * Get the history of the teenager
     * @return (Teenager) - The history of the teenager
     */
    public Teenager getHistory() {
        return history;
    }
    
    /**
     * Set the history of the teenager
     * @param history (Teenager) - The other teenager
     */
    public void setHistory(Teenager history) {
        this.history = history;
    }

    /**
     * Get value from key
     * @param criterionName (CriterionName) - The name of the criterion
     * @return (Criterion) - The value from the hashmap
     */
    private String getCriterionValue(CriterionName criterionName){
        return this.getRequirements().get(criterionName).getValue();
    }

    /**
     * Hashcode method
     * @return (int) - Hashcode
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((NAME == null) ? 0 : NAME.hashCode());
        result = prime * result + ((FORENAME == null) ? 0 : FORENAME.hashCode());
        result = prime * result + ((COUNTRY == null) ? 0 : COUNTRY.hashCode());
        result = prime * result + ((DATENAISS == null) ? 0 : DATENAISS.hashCode());
        result = prime * result + ((requirements == null) ? 0 : requirements.hashCode());
        return result;
    }

    /**
     * Equals method
     * @return (boolean) - True if equals, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Teenager other = (Teenager) obj;
        if (NAME == null) {
            if (other.NAME != null)
                return false;
        } else if (!NAME.equals(other.NAME))
            return false;
        if (FORENAME == null) {
            if (other.FORENAME != null)
                return false;
        } else if (!FORENAME.equals(other.FORENAME))
            return false;
        if (COUNTRY == null) {
            if (other.COUNTRY != null)
                return false;
        } else if (!COUNTRY.equals(other.COUNTRY))
            return false;
        if (DATENAISS == null) {
            if (other.DATENAISS != null)
                return false;
        } else if (!DATENAISS.equals(other.DATENAISS))
            return false;
        if (requirements == null) {
            if (other.requirements != null)
                return false;
        } else if (!requirements.equals(other.requirements))
            return false;
        return true;
    }

    

    

}
