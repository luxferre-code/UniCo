package ullile.sae201.exception;

/**
 * RequirementNotFound class
 * Exception thrown when a requirement is not found
 * @see Exception
 * @author Valentin Thuillier
 */
public class RequirementNotFound extends Exception{

    private final String hobbiesNotFound;

    public RequirementNotFound(String hobbiesNotFound) {
        this.hobbiesNotFound = hobbiesNotFound;
    }

    public String getHobbiesNotFound() {
        return this.hobbiesNotFound;
    }
}
