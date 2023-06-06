package sae;

import org.junit.Before;
import org.junit.Test;
import ullile.sae201.CriterionName;
import ullile.sae201.Platform;
import ullile.sae201.Teenager;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * PlatformTest class - Test the Platform class
 *
 * @author Valentin Thuillier
 */
public class PlatformTest {

    private Platform p1;
    private Teenager t1, t2, t3, t4;
    private LocalDate d1, d2, d3, d4;

    @Before
    public void setUp() {
        p1 = new Platform();
        d1 = LocalDate.of(1999, 1, 1);
        d2 = LocalDate.of(2000, 1, 1);
        d3 = LocalDate.of(2001, 1, 1);
        d4 = LocalDate.of(2002, 1, 1);

        /* Instance of Teenager */
        t1 = new Teenager("Toto", "Machin", d1, "France");
        t2 = new Teenager("Tata", "Truc", d2, "Spain");
        t3 = new Teenager("Titi", "Bidule", d3, "Italy");
        t4 = new Teenager("Tati", "Chouette", d4, "germany");
    }

    public void addRequirements() {
        try {
            //T1
            t1.addRequirement(CriterionName.GUEST_ANIMAL_ALLERGY, "yes");
            t1.addRequirement(CriterionName.HOST_HAS_ANIMAL, "no");
            t1.addRequirement(CriterionName.GENDER, "male");
            t1.addRequirement(CriterionName.PAIR_GENDER, "female");
            t1.addRequirement(CriterionName.HOBBIES, "video games,music");
            //T2
            t2.addRequirement(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
            t2.addRequirement(CriterionName.HOST_HAS_ANIMAL, "yes");
            t2.addRequirement(CriterionName.GENDER, "female");
            t2.addRequirement(CriterionName.GUEST_FOOD, "vegetarian");
            t2.addRequirement(CriterionName.HOBBIES, "music");
            t2.addRequirement(CriterionName.HISTORY, "other");
            //T3
            t3.addRequirement(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
            t3.addRequirement(CriterionName.HOST_HAS_ANIMAL, "no");
            t3.addRequirement(CriterionName.GENDER, "other");
            t3.addRequirement(CriterionName.PAIR_GENDER, "male");
            t3.addRequirement(CriterionName.HOBBIES, "video games");
            //T4
            t4.addRequirement(CriterionName.GUEST_ANIMAL_ALLERGY, "yes");
            t4.addRequirement(CriterionName.HOST_HAS_ANIMAL, "yes");
            t4.addRequirement(CriterionName.GENDER, "male");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testAddTeenager() {
        p1.addTeenager(t1);
        p1.addTeenager(t2);
        p1.addTeenager(t3);
        p1.addTeenager(t4);
        assert (p1.getTeenagers().size() == 4);
    }

    @Test
    public void testRemoveTeenager() {
        p1.addTeenager(t1);
        p1.addTeenager(t2);
        p1.addTeenager(t3);
        p1.addTeenager(t4);
        p1.removeTeenager(t1);
        assert (p1.getTeenagers().size() == 3);
    }

    @Test
    public void testpurgeIncoherentTeenager() {
        addRequirements();
        p1.addTeenager(t1);
        p1.addTeenager(t2);
        p1.addTeenager(t3);
        p1.addTeenager(t4);
        assertTrue(p1.purgeIncoherentTeenager());
        assertEquals(3, p1.getTeenagers().size());
    }

}
