package ullile.sae201;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Teenager class
 * @author Valentin Thuillier
 */
public class Teenager {

    private final String NAME, FORENAME, COUNTRY;
    private final Date DATENAISS;
    private ArrayList<Teenager> history = new ArrayList<>();

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
     * Check if the teenager is compatible with a another teenager
     * @param guest (Teenager) - The other teenager
     * @return (boolean) - True if the teenager is compatible with the other teenager, false otherwise
     */
    public boolean compatibleWithGuest(Teenager guest) {
        //TODO
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

    

}
