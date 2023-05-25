package ullile.sae201;

import ullile.sae201.exception.RequirementNotFound;

import java.util.HashSet;
import java.util.Set;

/**
 * Platform class
 * @author Valentin Thuillier
 */
public class Platform {

    private final HashSet<Teenager> teenagers = new HashSet<>();

    private final static int NOMINONTHESET  = 0;

    /**
     * Platform constructor
     */
    public Platform() {}

    /**
     * Add a teenager to the platform
     *
     * @param teenager (Teenager) - The teenager to add
     */
    public void addTeenager(Teenager teenager) {
        this.teenagers.add(teenager);
    }

    /**
     * Remove a teenager from the platform
     *
     * @param teenager (Teenager) - The teenager to remove
     */
    public void removeTeenager(Teenager teenager) {
        this.teenagers.remove(teenager);
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
            try {
                if(t.havingIncoherent()) {
                    toRemove.add(t);
                }
            } catch(RequirementNotFound e) {
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
        return this.purgeIncoherentTeenager(NOMINONTHESET); // 0 = no minimum
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

    /**
     * Check if two teenagers have commun hobbies
     * @param host (Teenager)
     * @param guest (Teenager)
     * @return (boolean)
     * @throws RequirementNotFound (Exception)
     */
    public static boolean haveCommunHobbies(Teenager host, Teenager guest) throws RequirementNotFound {
        for(String hobby : host.getHobbies()) {
            if(guest.getHobbies().contains(hobby)) return true;
        }
        return false;
    }

    /**
     * Check if two teenagers are grouching (if they are from a grouch country and don't have commun hobbies)
     * @param host - The host
     * @param guest - The guest
     * @return (boolean) - True if they are grouching, false otherwise
     */
    public static boolean grouching(Teenager host, Teenager guest) {
        if(!host.getCountry().isGrouch() && !guest.getCountry().isGrouch()) return false;
        try {
            return !haveCommunHobbies(host, guest);
        } catch (RequirementNotFound e) {
            return true;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Platform : \n");
        for(Teenager t : this.teenagers) {
            sb.append(t.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
