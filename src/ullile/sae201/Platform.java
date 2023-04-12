package ullile.sae201;

import java.util.HashSet;

/**
 * Platform class
 * @author Valentin Thuillier
 */
public class Platform {

    private HashSet<Teenager> teenagers = new HashSet<>();

    /**
     * Platform constructor
     */
    public Platform() {}

    /**
     * Add a teenager to the platform
     * @param teenager (Teenager) - The teenager to add
     * @return (boolean) - True if the teenager is added, false otherwise
     */
    public boolean addTeenager(Teenager teenager) {
        return this.teenagers.add(teenager);
    }

    /**
     * Remove a teenager from the platform
     * @param teenager (Teenager) - The teenager to remove
     * @return (boolean) - True if the teenager is removed, false otherwise
     */
    public boolean removeTeenager(Teenager teenager) {
        return this.teenagers.remove(teenager);
    }

    /**
     * Verify all the criterion of the teenagers
     */
    public void verifyAllCriterion() {
        for (Teenager teenager : this.teenagers) {
            teenager.purgeInvalidRequirement();
        }
    }





}
