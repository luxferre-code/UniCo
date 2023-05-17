package sae;

import java.beans.Transient;
import java.util.Calendar;

/**
 * @author Elise Leroy
 */
public class AffectationUtilTest {
    
    private Teenager t1, t2, t3, t4;
    private Date d1, d2, d3, d4;
    
    @Before
    public void setUp(){
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
        t1.addRequirement(CriterionName.GENDER, "male");
        t1.addRequirement(CriterionName.PAIR_GENDER, "female");
        t1.addRequirement(CriterionName.HOBBIES, "video games,music");
        //t2
        t2.addRequirement(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t2.addRequirement(CriterionName.HOST_HAS_ANIMAL, "yes");
        t2.addRequirement(CriterionName.GENDER, "female");
        t2.addRequirement(CriterionName.GUEST_FOOD, "vegetarian");
        t2.addRequirement(CriterionName.HOBBIES, "music");
        t2.addRequirement(CriterionName.HISTORY, "other");
        //t3
        t3.addRequirement(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t3.addRequirement(CriterionName.HOST_HAS_ANIMAL, "no");
        t3.addRequirement(CriterionName.GENDER, "other");
        t3.addRequirement(CriterionName.PAIR_GENDER, "male");
        t3.addRequirement(CriterionName.HOBBIES, "video games");
        //t4
        t4.addRequirement(CriterionName.GUEST_ANIMAL_ALLERGY, "yes");
        t4.addRequirement(CriterionName.HOST_HAS_ANIMAL, "yes");
        t4.addRequirement(CriterionName.GENDER, "male");
    }

    @Test
    public void testWeight(){

    }

}
