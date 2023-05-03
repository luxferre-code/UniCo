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

    private final String NAME, FORENAME;
    private final Country COUNTRY;
    private final Date DATENAISS;
    private Teenager history = null;

    private HashMap<CriterionName, Criterion> requirements = new HashMap<>();

    /**
     * Teenager constructor
     * @param name (String) - The name of the teenager
     * @param forename (String) - The forename of the teenager
     * @param dateNaiss (Date) - The date of birth of the teenager
     * @param country (Country) - The country of the teenager
     */
    public Teenager(String name, String forename, Date dateNaiss, Country country) {
        this.NAME = name;
        this.FORENAME = forename;
        this.DATENAISS = dateNaiss;
        this.COUNTRY = country;
    }

       /**
     * Teenager overcharged constructor
     * @param name (String) - The name of the teenager
     * @param forename (String) - The forename of the teenager
     * @param dateNaiss (Date) - The date of birth of the teenager
     * @param country (String) - The country of the teenager
     */
    public Teenager(String name, String forename, Date dateNaiss, String country) {
        this(name,forename,dateNaiss,Country.valueOf(country.toUpperCase()));
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

    /**
     * Adding a requirement to the teenager
     * @param criterionName (CriterionName) - The name of the criterion
     * @param value (String) - The value of the criterion
     * @return (boolean) - True if the requirement is valid, false otherwise
     */
    public boolean addRequirement(CriterionName criterionName, String value) {
        Criterion criterion = new Criterion(value, criterionName);
        if (criterion.isValid()) {
            this.requirements.replace(criterionName, criterion);
            return true;
        }
        return false;
    }

    /**
     * Adding a requirement to the teenager
     * @param criterion (Criterion) - The criterion
     * @return (boolean) - True if the requirement is valid, false otherwise
     */
    public boolean addRequirement(Criterion criterion) {
        if (criterion.isValid()) {
            this.requirements.put(criterion.getLabel(), criterion);
            return true;
        }
        return false;
    }

    /**
     * Replacing a requirement to the teenager
     * @param criterionName (CriterionName) - The name of the criterion
     * @return (boolean) - True if the requirement is valid, false otherwise
     */
    public boolean replaceRequirement(CriterionName criterionName, String value) {
        Criterion criterion = new Criterion(value, criterionName);
        if (criterion.isValid()) {
            this.requirements.replace(criterionName, criterion);
            return true;
        }
        return false;
    }

    /**
     * Check if the teenager is compatible with a teenager
     * @param guest (Teenager) - The other teenager
     * @return (boolean) - True if the teenager is compatible with the other teenager, false otherwise
     */
    public boolean compatibleWithGuest(Teenager guest) {
        if(booleanConverter(guest,CriterionName.GUEST_ANIMAL_ALLERGY,Criterion.YES) && booleanConverter(this,CriterionName.HOST_HAS_ANIMAL,Criterion.YES)){
            return false;
        }
        if(!this.compatibleFood(guest)){ return false; }
        return this.compatibleHistory(guest);
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
     * Check if the teenager's food is compatible with a teenager's food
     * @param guest (Teenager) - The other teenager
     * @return (boolean) - True if the teenager'food is compatible with the other teenager's food, false otherwise
     */
    private boolean compatibleFood(Teenager guest){
        boolean temp;

        if(!this.requirements.containsKey(CriterionName.HOST_FOOD) && !guest.requirements.containsKey(CriterionName.GUEST_FOOD)){
            return false;
        }

        String[] hostFoods = this.getCriterionValue(CriterionName.HOST_FOOD).split(",");
        String[] guestFoods = guest.getCriterionValue(CriterionName.GUEST_FOOD).split(",");
        for(String gfood : guestFoods){
            temp = false;
            for(String hfood : hostFoods){
                if(gfood.equals(hfood)){
                    temp = true;
                    break;
                }
            }
            if(!temp){
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the teenager can be with the same teenager as the last year
     * @param guest (Teenager) - The other teenager
     * @return (boolean) - False if the teenager doesn't want to be with the same teenager as the last year, true otherwise
     */
    private boolean compatibleHistory(Teenager guest){
        if(this.history == null){
            return true;
        }
        if(!this.history.equals(guest)){
            return true;
        }
        return !booleanConverter(this, CriterionName.HISTORY, "other") && !booleanConverter(guest, CriterionName.HISTORY, "other");
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
    public Country getCountry() {
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
        if(!this.requirements.containsKey(criterionName)) return "";
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
        if(this == obj) { return true; }
        if(obj == null) { return false; }
        if(getClass() != obj.getClass()) { return false; }
        Teenager other = (Teenager) obj;
        return this.NAME.equals(other.NAME) &&
                this.FORENAME.equals(other.FORENAME) &&
                this.COUNTRY.equals(other.COUNTRY) &&
                this.DATENAISS.equals(other.DATENAISS) &&
                this.requirements.equals(other.requirements);
    }

    /**
     * Having incoherent requirement
     * @return (boolean) - True if the teenager has incoherent requirement, false otherwise
     */
    public boolean havingIncoherent() {
        if(this.getCriterionValue(CriterionName.HOST_HAS_ANIMAL).equals(Criterion.YES) && this.getCriterionValue(CriterionName.GUEST_ANIMAL_ALLERGY).equals(Criterion.YES)){
            return true;
        }
        for(Criterion c : this.requirements.values()){
            if(!c.isValid()) {
                return true;
            }
        }
        return false;
    }


}
