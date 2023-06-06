package sae;

import org.junit.Before;
import org.junit.Test;

import fr.ulille.but.sae2_02.graphes.CalculAffectation;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        t5 = new Teenager("Rex","Laris", d5,"GERMANY");
        t6 = new Teenager("Interfector", "Bellatrix", d6, "GERMANY");
        t7 = new Teenager("Crane", "Mave", d7, "GERMANY");
        t8 = new Teenager("Enaxx", "Hinkkost",  d8, "GERMANY");


        /*Adding requirement to the test teenagers */
        //t1
        t1.addRequirement(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t1.addRequirement(CriterionName.HOBBIES, "sports,technology");
        //t2
        t2.addRequirement(CriterionName.GUEST_ANIMAL_ALLERGY, "yes");
        t2.addRequirement(CriterionName.HOBBIES, "culture,science");
        //t3
        t3.addRequirement(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t3.addRequirement(CriterionName.HOBBIES, "science,reading");
        //t4
        t4.addRequirement(CriterionName.HOST_HAS_ANIMAL, "no");
        t4.addRequirement(CriterionName.HOBBIES, "culture,technology");
        //t5
        t5.addRequirement(CriterionName.HOST_HAS_ANIMAL, "yes");
        t5.addRequirement(CriterionName.HOBBIES, "science,reading");
        //t6
        t6.addRequirement(CriterionName.HOST_HAS_ANIMAL, "no");
        t6.addRequirement(CriterionName.HOBBIES, "technology");
        //t7
        t7.addRequirement(CriterionName.GUEST_ANIMAL_ALLERGY, "yes");
        t7.addRequirement(CriterionName.HOST_HAS_ANIMAL, "yes");
        t7.addRequirement(CriterionName.HOBBIES, "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,aa,bb,cc,dd,ee,ff,gg,hh,ii,jj,kk,ll,mm,nn,oo,pp,qq,rr,ss,tt,uu,vv,ww,xx");
        t7.addRequirement(CriterionName.GENDER, "female");
        //t8
        t8.addRequirement(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t8.addRequirement(CriterionName.HOST_HAS_ANIMAL, "yes");
        t8.addRequirement(CriterionName.GENDER, "male");
        //t9
        t9.addRequirement(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t9.addRequirement(CriterionName.HOST_HAS_ANIMAL, "no");
        t9.addRequirement(CriterionName.GENDER, "female");
        //t10
        t10.addRequirement(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t10.addRequirement(CriterionName.HOST_HAS_ANIMAL, "yes");
        t10.addRequirement(CriterionName.HOBBIES, "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,aa,bb,cc,dd,ee,ff,gg,hh,ii,jj,kk,ll,mm,nn,oo,pp,qq,rr,ss,tt,uu,vv,ww,xx");
        t10.addRequirement(CriterionName.GENDER, "male");
    }


    @Test
    public void testweightV1(){
        //Double.valueOf nécessaire car ambiguité voir : https://stackoverflow.com/questions/1811103/java-junit-the-method-x-is-ambiguous-for-type-y 
        assertEquals(Double.valueOf(99),AffectationUtil.weightV1(t4, t1));
        assertEquals(Double.valueOf(99),AffectationUtil.weightV1(t4,t2));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV1(t4,t3));

        assertEquals(Double.valueOf(100),AffectationUtil.weightV1(t5,t1));
        assertEquals(Double.valueOf(199),AffectationUtil.weightV1(t5,t2));
        assertEquals(Double.valueOf(98),AffectationUtil.weightV1(t5,t3));

        assertEquals(Double.valueOf(99),AffectationUtil.weightV1(t6,t1));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV1(t6,t2));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV1(t6,t3));

        assertEquals(Double.valueOf(99),AffectationUtil.weightV1(t6,t1));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV1(t6,t2));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV1(t6,t3));

        //Tests incompatibilityVsHobbies
        assertEquals(Double.valueOf(100),AffectationUtil.weightV1(t7,t8));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV1(t7,t9));
        assertEquals(Double.valueOf(97),AffectationUtil.weightV1(t7,t10));

        assertEquals(Double.valueOf(200),AffectationUtil.weightV1(t8,t7));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV1(t8,t9));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV1(t8,t10));

        assertEquals(Double.valueOf(100),AffectationUtil.weightV1(t9,t7));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV1(t9,t8));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV1(t9,t10));

        assertEquals(Double.valueOf(197),AffectationUtil.weightV1(t10,t7));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV1(t10,t8));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV1(t10,t9));
    }
}
