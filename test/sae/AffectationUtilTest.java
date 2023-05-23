package sae;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;

import ullile.sae201.CriterionName;
import ullile.sae201.Teenager;
import ullile.sae201.exception.InvalidCriterion;
import ullile.sae201.graphe.AffectationUtil;

/**
 * @author Elise Leroy
 */
public class AffectationUtilTest {
    
    private Teenager t1, t2, t3, t4;
    private Date d1, d2, d3, d4;
    
    @Before
    public void setUp() throws InvalidCriterion{
    /*Instancing birth dates */
        d1 = new Date();
        d2 = new Date();
        d3 = new Date();
        d4 = new Date();
        Calendar c = Calendar.getInstance();
        c.set(1999, Calendar.JANUARY, 1);
        d1 = c.getTime();
        c.set(2000, Calendar.JANUARY, 1);
        d2 = c.getTime();
        c.set(2001, Calendar.JANUARY, 1);
        d3 = c.getTime();
        c.set(2002, Calendar.JANUARY, 1);
        d4 = c.getTime();

        /*Instancing teenagers */
        t1 = new Teenager("Toto", "Machin", d1, "France");
        t2 = new Teenager("Tata", "Truc",  d2, "Spain");
        t3 = new Teenager("Titi", "Bidule", d3, "Italy");
        t4 = new Teenager("Tati", "Chouette", d4, "germany");

        /*Adding requirement to the test teenagers */
        //t1
        t1.addRequirement(CriterionName.GUEST_ANIMAL_ALLERGY, "yes");
        t1.addRequirement(CriterionName.HOST_HAS_ANIMAL, "no");
        t1.addRequirement(CriterionName.HOBBIES, "video games,music,sport,film");
        //t2
        t2.addRequirement(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t2.addRequirement(CriterionName.HOST_HAS_ANIMAL, "yes");
        t2.addRequirement(CriterionName.HOBBIES, "music");
        //t3
        t3.addRequirement(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t3.addRequirement(CriterionName.HOST_HAS_ANIMAL, "no");
        t3.addRequirement(CriterionName.HOBBIES, "video games,music,flowers,film,party");
        //t4
        t4.addRequirement(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t4.addRequirement(CriterionName.HOST_HAS_ANIMAL, "yes");
        t4.addRequirement(CriterionName.HOBBIES, "flowers,party");
    }

    @Test
    public void testWeight(){
        //Double.valueOf nécessaire car ambiguité voir : https://stackoverflow.com/questions/1811103/java-junit-the-method-x-is-ambiguous-for-type-y
        assertEquals(Double.valueOf(199), AffectationUtil.weight(t2, t1));
        assertEquals(Double.valueOf(97), AffectationUtil.weight(t1, t3)); 
        assertEquals(Double.valueOf(200), AffectationUtil.weight(t4, t1)); 
        assertEquals(Double.valueOf(99), AffectationUtil.weight(t2, t3));
        assertEquals(Double.valueOf(100), AffectationUtil.weight(t2, t4));
        assertEquals(Double.valueOf(98), AffectationUtil.weight(t3, t4));
    }
}
