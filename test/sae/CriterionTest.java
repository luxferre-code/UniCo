package sae;

import org.junit.Before;
import org.junit.Test;
import ullile.sae201.Criterion;
import ullile.sae201.CriterionName;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * CriterionTest class - Test the Criterion class
 * @author Valentin Thuillier
 */
public class CriterionTest {

    private Criterion c1, c2, c3, c4;

    /**
     * Set up the criterion
     */
    @Before
    public void setUp() {
        c1 = new Criterion("male", CriterionName.GENDER);
        c2 = new Criterion("test", CriterionName.HOST_HAS_ANIMAL);
        c3 = new Criterion("yes", CriterionName.GUEST_ANIMAL_ALLERGY);
        c4 = new Criterion("other", CriterionName.HISTORY);
    }

    /**
     * Test the isValid method
     */
    @Test
    public void testIsValid() {
        assertTrue(c1.isValid());
        assertFalse(c2.isValid());
        assertTrue(c3.isValid());
        assertTrue(c4.isValid());
    }

}
