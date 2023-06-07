package sae;

import org.junit.Before;
import org.junit.Test;

import fr.ulille.but.sae2_02.graphes.Arete;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ullile.sae201.CriterionName;
import ullile.sae201.Teenager;
import ullile.sae201.exception.InvalidCriterion;
import ullile.sae201.graphe.AffectationUtil;

/**
 * AffectationUtil test class V2
 * @author Romain Degez
 */
public class TestAffectationVersion2 {
    private Teenager t1, t2, t3, t4, t5, t6, t7, t8;
    private Date d1, d2, d3, d4, d5, d6, d7, d8;
    private ArrayList<Teenager> guestList;
    private ArrayList<Teenager> hostList;
    private GrapheNonOrienteValue<Teenager> graphe;
    private CalculAffectation<Teenager> affectation;
    ArrayList<Arete<Teenager>> appariment = new ArrayList<Arete<Teenager>>(); 

    
    @Before
    public void setUp() throws InvalidCriterion{
    /*Instancing birth dates (needed for our constructor)*/
        d1 = new Date();
        d2 = new Date();
        d3 = new Date();
        d4 = new Date();
        d5 = new Date();
        d6 = new Date();
        d7 = new Date();
        d8 = new Date();

        Calendar c = Calendar.getInstance();
        c.set(1999, Calendar.JANUARY, 1);
        d1 = c.getTime();
        c.set(2000, Calendar.JANUARY, 1);
        d2 = c.getTime();
        c.set(2001, Calendar.JANUARY, 1);
        d3 = c.getTime();
        c.set(2002, Calendar.JANUARY, 1);
        d4 = c.getTime();
        c.set(2002, Calendar.JANUARY, 1);
        d5 = c.getTime();
        c.set(2002, Calendar.JANUARY, 1);
        d6 = c.getTime();
        c.set(2009, Calendar.JUNE, 1);
        d7 = c.getTime();
        c.set(2009, Calendar.JUNE, 1);
        d8 = c.getTime();


        /*Instancing teenagers */
        t1 = new Teenager("Tanjin", "Dalthu", d1, "ITALY");
        t2 = new Teenager("Damon", "Lilly",  d2, "ITALY");
        t3 = new Teenager("Ekey", "Jensmebur", d3, "ITALY");
        t4 = new Teenager("Ozith", "Himimtoss", d4, "ITALY");
        guestList = new ArrayList<Teenager>() ;
        guestList.add(t1);
        guestList.add(t2);
        guestList.add(t3);
        guestList.add(t4);


        t5 = new Teenager("Rex","Laris", d5,"GERMANY");
        t6 = new Teenager("Interfector", "Bellatrix", d6, "GERMANY");
        t7 = new Teenager("Crane", "Mave", d7, "GERMANY");
        t8 = new Teenager("Enaxx", "Hinkkost",  d8, "GERMANY");
        hostList = new ArrayList<Teenager>() ;
        hostList.add(t5);
        hostList.add(t6);
        hostList.add(t7);
        hostList.add(t8);


        /*Adding requirement to the test teenagers */
        //t1
        t1.addRequirement(CriterionName.HISTORY, "same");
        t1.setHistory(t7);
        //t2
        t2.addRequirement(CriterionName.HISTORY, "same");
        t2.setHistory(t8);
        //t3
        t3.addRequirement(CriterionName.HISTORY, "same");
        t3.setHistory(t6);
        //t4
        t4.setHistory(t5);
        //t5
        t5.addRequirement(CriterionName.HISTORY, "same");
        t5.setHistory(t4);
        //t6
        t6.addRequirement(CriterionName.HISTORY, "other");
        t6.setHistory(t3);
        //t7
        t7.addRequirement(CriterionName.HISTORY, "same");
        t7.setHistory(t1);
        //t8
        t8.setHistory(t2);
    }


    @Test
    public void testweightV2Exemple1(){
        //Double.valueOf nécessaire car ambiguité voir : https://stackoverflow.com/questions/1811103/java-junit-the-method-x-is-ambiguous-for-type-y 
        assertEquals(Double.valueOf(100),AffectationUtil.weightV2(t1, t5));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV2(t1, t6));
        assertEquals(Double.valueOf(0),AffectationUtil.weightV2(t1, t7));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV2(t1, t8));

        assertEquals(Double.valueOf(100),AffectationUtil.weightV2(t2, t5));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV2(t2, t6));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV2(t2, t7));
        assertEquals(Double.valueOf(96),AffectationUtil.weightV2(t2, t8));

        assertEquals(Double.valueOf(100),AffectationUtil.weightV2(t3, t5));
        assertEquals(Double.valueOf(200),AffectationUtil.weightV2(t3, t6));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV2(t3, t7));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV2(t3, t8));

        assertEquals(Double.valueOf(96),AffectationUtil.weightV2(t4, t5));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV2(t4, t6));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV2(t4, t7));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV2(t4, t8));

        graphe = AffectationUtil.creerGrapheTeenagerV2(hostList, guestList);
        affectation = new CalculAffectation<Teenager>(graphe,hostList,guestList);
        appariment.addAll(affectation.calculerAffectation());

        assertEquals(Double.valueOf(296),affectation.getCout());
    }

    public void addRequirements() {
        try {
            t2.addRequirement(CriterionName.HOBBIES, "reading");
            t3.addRequirement(CriterionName.HOBBIES, "culture");
            t4.addRequirement(CriterionName.HOBBIES, "culture,science");
            t5.addRequirement(CriterionName.HOBBIES, "science");
            t6.addRequirement(CriterionName.HOBBIES, "reading,culture");
            t7.addRequirement(CriterionName.HOBBIES, "sports");
            t8.addRequirement(CriterionName.HOBBIES, "sports,culture,reading");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testweightV2Exemple2() throws InvalidCriterion{
        addRequirements();
        //Double.valueOf nécessaire car ambiguité voir : https://stackoverflow.com/questions/1811103/java-junit-the-method-x-is-ambiguous-for-type-y 
        assertEquals(Double.valueOf(100),AffectationUtil.weightV2(t1, t5));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV2(t1, t6));
        assertEquals(Double.valueOf(0),AffectationUtil.weightV2(t1, t7));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV2(t1, t8));

        assertEquals(Double.valueOf(100),AffectationUtil.weightV2(t2, t5));
        assertEquals(Double.valueOf(99),AffectationUtil.weightV2(t2, t6));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV2(t2, t7));
        assertEquals(Double.valueOf(95),AffectationUtil.weightV2(t2, t8));

        assertEquals(Double.valueOf(100),AffectationUtil.weightV2(t3, t5));
        assertEquals(Double.valueOf(199),AffectationUtil.weightV2(t3, t6));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV2(t3, t7));
        assertEquals(Double.valueOf(99),AffectationUtil.weightV2(t3, t8));

        assertEquals(Double.valueOf(95),AffectationUtil.weightV2(t4, t5));
        assertEquals(Double.valueOf(99),AffectationUtil.weightV2(t4, t6));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV2(t4, t7));
        assertEquals(Double.valueOf(99),AffectationUtil.weightV2(t4, t8));

        graphe = AffectationUtil.creerGrapheTeenagerV2(hostList, guestList);
        affectation = new CalculAffectation<Teenager>(graphe,hostList,guestList);
        appariment.addAll(affectation.calculerAffectation());

        assertEquals(Double.valueOf(293),affectation.getCout());

    }
}
