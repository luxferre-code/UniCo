package ullile.sae201;

import java.util.HashSet;

/**
 * Criterion class
 *
 * @author Valentin Thuillier, Romain Degez
 */
public class Criterion {

    private final static HashSet<String> GENDERS = new HashSet<>() {{
        add("male");
        add("female");
        add("other");
    }};
    private final static HashSet<String> FOOD = new HashSet<>() {{
        add("nonuts");
        add("vegetarian");
        add("");
    }};

    public final static String YES = "yes";
    public final static String NO = "no";
    private final CriterionName label;
    private final String value;

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
            case 'B' -> {
                return this.value.equals(Criterion.YES) || this.value.equals(Criterion.NO);
            }
            case 'T' -> {
                switch (this.label.name()) {
                    case "GENDER" -> {
                        return Criterion.GENDERS.contains(this.value);
                    }
                    case "PAIR_GENDER" -> {
                        return Criterion.GENDERS.contains(this.value) || this.value.isEmpty();
                    }
                    case "HISTORY" -> {
                        return Criterion.POSSIBILITY_HISTORY.contains(this.value);
                    }
                    default -> {
                        if (this.label.name().equals("HOST_FOOD") || this.label.name().equals("GUEST_FOOD")) {
                            for (String s : this.value.split(",")) {
                                if (!Criterion.FOOD.contains(s)) return false;
                            }
                        }
                    }
                }
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    /**
     * Get the value of the label
     * @return (String) - The value of the label
     */
    public CriterionName getLabel() {
        return this.label;
    }

    /**
     * Get the value of the value
     * @return (String) - The value of the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Hashcode method
     * @return (int) - Hashcode
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((label == null) ? 0 : label.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    /**
     * Equals method
     * @return (boolean) - True if equals, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Criterion other = (Criterion) obj;
        if (label != other.label) return false;
        if (value == null) return other.value == null;
        else return value.equals(other.value);
    }

}