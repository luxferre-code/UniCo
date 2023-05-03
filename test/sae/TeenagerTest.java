package sae;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import ullile.sae201.Country;
import ullile.sae201.CriterionName;
import ullile.sae201.Teenager;

public class TeenagerTest {

    private Teenager t1, t2, t3, t4;
    private Date d1, d2, d3, d4;

    @Before
    public void setUp() {
        /* Instance of dateNaiss */
        d1 = new Date();
        d2 = new Date();
        d3 = new Date();
        d4 = new Date();
        Calendar c = Calendar.getInstance();
        c.set(1999, 1, 1);
        d1 = c.getTime();
        c.set(2000, 1, 1);
        d2 = c.getTime();
        c.set(2001, 1, 1);
        d3 = c.getTime();
        c.set(2002, 1, 1);
        d4 = c.getTime();

        /* Instance of Teenager */
        t1 = new Teenager("Toto", "Machin", d1, "France");
        t2 = new Teenager("Tata", "Truc",  d2, "Spain");
        t3 = new Teenager("Titi", "Bidule",  d3, "Italy");
        t4 = new Teenager("Tati", "Chouette",  d4, "germain");
    }

    @Test
    public void testGetter() {
        //Name
        assertEquals("Toto", t1.getName());
        assertEquals("Tata", t2.getName());
        assertEquals("Titi", t3.getName());
        assertEquals("Tati", t4.getName());
        //Forename
        assertEquals("Machin", t1.getForename());
        assertEquals("Truc", t2.getForename());
        assertEquals("Bidule", t3.getForename());
        assertEquals("Chouette", t4.getForename());
        //DateNaiss
        assertEquals(d1, t1.getDateNaiss());
        assertEquals(d2, t2.getDateNaiss());
        assertEquals(d3, t3.getDateNaiss());
        assertEquals(d4, t4.getDateNaiss());
        //Country
        assertEquals(Country.FRANCE, t1.getCountry());
        assertEquals(Country.SPAIN, t2.getCountry());
        assertEquals(Country.ITALY, t3.getCountry());
        assertEquals(Country.GERMAIN, t4.getCountry());
    }

    public void addRequirements() {
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
    }

}