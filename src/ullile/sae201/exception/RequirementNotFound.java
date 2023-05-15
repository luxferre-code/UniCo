package ullile.sae201.exception;

public class RequirementNotFound extends Exception{

    private final String hobbiesNotFound;

    public RequirementNotFound(String hobbiesNotFound) {
        this.hobbiesNotFound = hobbiesNotFound;
    }

    public String getHobbiesNotFound() {
        return this.hobbiesNotFound;
    }
}
