package sae;

import org.junit.Before;
import org.junit.Test;
import ullile.sae201.Criterion;
import ullile.sae201.CriterionName;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

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
        c2 = new Criterion("test", CriterionName.PAIR_GENDER);
        c3 = new Criterion("same", CriterionName.HISTORY);
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
