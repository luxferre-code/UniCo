package ullile.sae201;

/**
 * CriterionName enum
 * @author Valentin Thuillier
 */
public enum CriterionName {

    GUEST_ANIMAL_ALLERGY('B'),
    HOST_HAS_ANIMAL('B'),
    GUEST_FOOD('T'),
    HOST_FOOD('T'),
    HOBBIES('T'),
    GENDER('T'),
    PAIR_GENDER('T'),
    HISTORY('T');

    private final char TYPE;

    /**
     * CriterionName constructor
     * @param type (char) - The type of the criterion
     */
    private CriterionName(char type) { this.TYPE = type; }

    /**
     * Get the type of the criterion
     * @return (char) - The type of the criterion
     */
    public char getType() { return this.TYPE; }

}
