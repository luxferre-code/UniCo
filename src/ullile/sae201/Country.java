package ullile.sae201;

/**
 * CriterionName enum
 * @author Romain Degez
 */
public enum Country {
    FRANCE(true),
    SPAIN(false),
    GERMANY(false),
    ITALY(false);

    private final boolean ISGROUCH;

    /**
     * CriterionName constructor
     * @param isGrouch (boolean) - Character state
     */
    private Country(boolean isGrouch) { this.ISGROUCH = isGrouch; }

    /**
     * Get the character state
     * @return (boolean) - Character state
     */
    public boolean isGrouch() { return ISGROUCH; }

    

}
