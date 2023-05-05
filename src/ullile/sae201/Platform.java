package ullile.sae201;

import java.util.HashSet;
import java.util.Set;

/**
 * Platform class
 * @author Valentin Thuillier
 */
public class Platform {

    private final HashSet<Teenager> teenagers = new HashSet<>();

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

    /**
     * Purge all the teenagers having incoherent criterion
     * @param minOnTheSet (int) - The minimum number of teenager on the set
     * @return (boolean) - True if the process is successful, false otherwise
     */
    public boolean purgeIncoherentTeenager(int minOnTheSet) {
        if(this.teenagers.size() < minOnTheSet) return false;
        HashSet<Teenager> toRemove = new HashSet<>();
        for(Teenager t : this.teenagers) {
            if(this.teenagers.size() - toRemove.size() < minOnTheSet) break;
            if(t.havingIncoherent()) {
                toRemove.add(t);
            }
        }
        this.teenagers.removeAll(toRemove);
        return true;
    }

    /**
     * Purge all the teenagers having incoherent criterion
     * @return (boolean) - True if the process is successful, false otherwise
     */
    public boolean purgeIncoherentTeenager() {
        return this.purgeIncoherentTeenager(0); // 0 = no minimum
    }

    /**
     * Purge all the teenagers having incoherent criterion
     *
     * @param numberToDelete (int) - The number of teenager to delete
     * @return (boolean) - True if the process is successful, false otherwise
     */
    public boolean purgeIncoherentTeenagerByNumber(int numberToDelete) {
        return this.purgeIncoherentTeenager(this.teenagers.size() - numberToDelete);
    }

    /**
     * Getter of the Set
     *
     * @return (Set)
     */
    public Set<Teenager> getTeenagers() {
        return teenagers;
    }
}
