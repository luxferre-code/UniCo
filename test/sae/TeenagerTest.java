package sae;

import org.junit.Before;
import org.junit.Test;
import ullile.sae201.Country;
import ullile.sae201.CriterionName;
import ullile.sae201.Teenager;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeenagerTest {

    private Teenager t1, t2, t3, t4;
    private LocalDate d1, d2, d3, d4;

    @Before
    public void setUp() {
        /* Instance of dateNaiss */
        d1 = LocalDate.of(1999, 1, 1);
        d2 = LocalDate.of(2000, 1, 1);
        d3 = LocalDate.of(2001, 1, 1);
        d4 = LocalDate.of(2002, 1, 1);

        /* Instance of Teenager */
        t1 = new Teenager("Toto", "Machin", d1, "France");
        t2 = new Teenager("Tata", "Truc",  d2, "Spain");
        t3 = new Teenager("Titi", "Bidule", d3, "Italy");
        t4 = new Teenager("Tati", "Chouette", d4, "germany");
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
        assertEquals(Country.GERMANY, t4.getCountry());
    }

    public void addRequirements() {
        //T1
        try {
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
    public void testAddRequirement() {
        addRequirements();
        assertEquals(5, t1.getRequirements().size());
        assertEquals(6, t2.getRequirements().size());
        assertEquals(5, t3.getRequirements().size());
        assertEquals(3, t4.getRequirements().size());
    }

    @Test
    public void testHavingIncoherent() {
        addRequirements();
        try {
            assertFalse(t1.havingIncoherent());
            assertFalse(t2.havingIncoherent());
            assertFalse(t3.havingIncoherent());
            assertTrue(t4.havingIncoherent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}