package ullile.sae201.exception;

/**
 * RequirementNotFound class
 * Exception thrown when a requirement is not found
 *
 * @author Valentin Thuillier
 * @see Exception
 */
public class RequirementNotFound extends Exception {

    private final String hobbiesNotFound;

    public RequirementNotFound(String hobbiesNotFound) {
        this.hobbiesNotFound = hobbiesNotFound;
    }

    public String getHobbiesNotFound() {
        return this.hobbiesNotFound;
    }
}
