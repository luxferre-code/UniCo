package sae;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import ullile.sae201.Criterion;
import ullile.sae201.CriterionName;
import ullile.sae201.Teenager;

import static org.junit.Assert.*;

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
        c.set(1999, Calendar.FEBRUARY, 1);
        d1 = c.getTime();
        c.set(2000, Calendar.APRIL, 1);
        d2 = c.getTime();
        c.set(2001, Calendar.JULY, 1);
        d3 = c.getTime();
        c.set(2002, Calendar.OCTOBER, 1);
        d4 = c.getTime();

        /* Instance of Teenager */
        t1 = new Teenager("Toto", "Machin", d1, "France");
        t2 = new Teenager("Tata", "Truc",  d2, "Australia");
        t3 = new Teenager("Titi", "Bidule",  d3, "USA");
        t4 = new Teenager("Tati", "Chouette",  d4, "France");
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
        assertEquals("France", t1.getCountry());
        assertEquals("Australia", t2.getCountry());
        assertEquals("USA", t3.getCountry());
        assertEquals("France", t4.getCountry());
    }

    public void addRequirements() {
        //T1
        t1.addRequirement(CriterionName.GUEST_ANIMAL_ALLERGY, "yes");
        t1.addRequirement(CriterionName.HOST_HAS_ANIMAL, "no");
        t1.addRequirement(CriterionName.GENDER, "male");
        t1.addRequirement(CriterionName.PAIR_GENDER, "female");
        t1.addRequirement(CriterionName.HOBBIES, "video games,music");
        //T2
        t2.addRequirement(CriterionName.GUEST_ANIMAL_ALLERGY, "maybe");
        t2.addRequirement(CriterionName.HOST_HAS_ANIMAL, "yes");
        t2.addRequirement(CriterionName.GENDER, "female");
        t2.addRequirement(CriterionName.GUEST_FOOD, "vegetarian");
        t2.addRequirement(CriterionName.HOBBIES, "music");
        t2.addRequirement(CriterionName.HISTORY, "other");
        //T3
        t3.addRequirement(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t3.addRequirement(CriterionName.HOST_HAS_ANIMAL, "no");
        t3.addRequirement(CriterionName.GENDER, "other");
        t3.addRequirement(CriterionName.PAIR_GENDER, "males");
        t3.addRequirement(CriterionName.HOBBIES, "video games");
        //T4
        t4.addRequirement(CriterionName.GUEST_ANIMAL_ALLERGY, "yes");
        t4.addRequirement(CriterionName.HOST_HAS_ANIMAL, "yes");
        t4.addRequirement(CriterionName.GENDER, "");
        t4.addRequirement(CriterionName.GUEST_FOOD, "nonuts");
        t4.addRequirement(CriterionName.HOBBIES, "music");
        t4.addRequirement(CriterionName.HISTORY, "same");
    }

    @Test
    public void testPurgeInvalidRequirement() {
        addRequirements();
        HashMap<CriterionName, Criterion> req = t1.getRequirements();
        assertEquals(8, req.size());
        t1.purgeInvalidRequirement();
        req = t1.getRequirements();
        assertEquals(8, req.size());

        req = t2.getRequirements();
        assertEquals(8, req.size());
        t2.purgeInvalidRequirement();
        req = t2.getRequirements();
        assertEquals(6, req.size()); // PAIR_GENDER AND GUEST_ANIMAL_ALLERGY are invalid

        req = t3.getRequirements();
        assertEquals(8, req.size());
        t3.purgeInvalidRequirement();
        req = t3.getRequirements();
        assertEquals(7, req.size()); // PAIR_GENDER is invalid

        req = t4.getRequirements();
        assertEquals(8, req.size());
        t4.purgeInvalidRequirement();
        req = t4.getRequirements();
        assertEquals(6, req.size()); // GENDER AND PAIR_GENDER are invalid
    }

    @Test
    public void testEquals() {
        Teenager t5 = new Teenager("Toto", "Machin", d1, "France");
        assertEquals(t1, t5);
        assertNotEquals(t1, t2);
        assertNotEquals(t2, t5);
    }

    @Test
    public void testCompatibleWithGuest() {
        addRequirements();
        assertFalse(t1.compatibleWithGuest(t2));
        assertTrue(t1.compatibleWithGuest(t3));
        assertFalse(t1.compatibleWithGuest(t4));
        assertFalse(t2.compatibleWithGuest(t1));
        assertTrue(t2.compatibleWithGuest(t3));
        assertFalse(t2.compatibleWithGuest(t4));
        assertTrue(t3.compatibleWithGuest(t1));
        assertFalse(t3.compatibleWithGuest(t2));
        assertFalse(t3.compatibleWithGuest(t4));
        assertFalse(t4.compatibleWithGuest(t1));
        assertFalse(t4.compatibleWithGuest(t2));
        assertTrue(t4.compatibleWithGuest(t3));
    }

    @Test
    public void testHavingIncoherent() {
        //TODO
    }

}