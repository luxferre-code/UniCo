package ullile.sae201;

import java.util.HashSet;

/**
 * Criterion class
 * @author Valentin Thuillier
 */
public class Criterion {

    private CriterionName label;
    private String value;

    private final static String YES = "yes";
    private final static String NO = "no";
    private final static HashSet<String> GENDERS = new HashSet<>(){{
       add("male");
       add("female");
       add("other");
    }};
    private final static HashSet<String> ENUM_USING_GENDER = new HashSet<>() {{
       add("GENDER");
       add("PAIR_GENDER");
    }};

    private final static HashSet<String> POSSIBILITY_HISTORY = new HashSet<>() {{
        add("same");
        add("other");
        add("");
    }};

    /**
     * Criterion constructor
     * @param value (String) - The value of the criterion
     * @param label (CriterionName) - The label of the criterion
     */
    public Criterion(String value, CriterionName label) {
        this.value = value;
        this.label = label;
    }

    /**
     * Check if the value have the good type and the good caracteristics
     * @return (boolean) - True if the value is valid, false otherwise
     */
    public boolean isValid() {
        switch (this.label.getType()) {
            case 'B':
                return this.value.equals(Criterion.YES) || this.value.equals(Criterion.NO);
            case 'T':
                if (Criterion.ENUM_USING_GENDER.contains(this.label.name())) { return Criterion.GENDERS.contains(this.value); }
                else if(this.label.name().equals("HISTORY")) { return Criterion.POSSIBILITY_HISTORY.contains(this.value); }
                return true;
            default:
                return false;
        }
    }

    /**
     * Get the value of the label
     * @return (String) - The value of the label
     */
    public CriterionName getLabel() {
        return this.label;
    }

}
