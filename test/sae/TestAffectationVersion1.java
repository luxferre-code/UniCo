package sae;

import org.junit.Before;
import org.junit.Test;

import fr.ulille.but.sae2_02.graphes.Arete;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import ullile.sae201.CriterionName;
import ullile.sae201.Teenager;
import ullile.sae201.exception.InvalidCriterion;
import ullile.sae201.graphe.AffectationUtil;

/**
 * AffectationUtil test class V1
 * @author Elise Leroy, Romain Degez
 */
public class TestAffectationVersion1 {
    
    private Teenager t1, t2, t3, t4, t5, t6, t7, t8, t9, t10;
    private LocalDate d1;
    private LocalDate d2;
    private LocalDate d3;
    private LocalDate d4;
    private LocalDate d5;
    private LocalDate d6;
    private LocalDate d7;
    private LocalDate d8;
    private LocalDate d9;
    private LocalDate d10;
    private ArrayList<Teenager> guestList;
    private ArrayList<Teenager> hostList;
    private ArrayList<Teenager> guestListIncompatibility;
    private ArrayList<Teenager> hostListIncompatibility;
    private GrapheNonOrienteValue<Teenager> graphe;
    private CalculAffectation<Teenager> affectation;
    ArrayList<Arete<Teenager>> appariment = new ArrayList<Arete<Teenager>>(); 
    
    @Before
    public void setUp() throws InvalidCriterion{
    /*Instancing birth dates (needed for our constructor)*/

        d1 = LocalDate.of(1999, 1, 1);
        d2 = LocalDate.of(2000, 1, 1);
        d3 = LocalDate.of(2001, 1, 1);
        d4 = LocalDate.of(2002, 1, 1);
        d5 = LocalDate.of(2002, 1, 1);
        d6 = LocalDate.of(2002, 1, 1);
        d7 = LocalDate.of(2009, 6, 1);
        d8 = LocalDate.of(2009, 6, 1);
        d9 = LocalDate.of(2009, 6, 1);
        d10 = LocalDate.of(2009, 6, 1);


        /*Instancing teenagers and List of Teenager */

        //Guests
        t1 = new Teenager("A", "Adonia", d1, "France");
        t2 = new Teenager("B", "Bellatrix",  d2, "France");
        t3 = new Teenager("C", "Callista", d3, "France");
        guestList = new ArrayList<Teenager>() ;
        guestList.add(t1);
        guestList.add(t2);
        guestList.add(t3);

        //Hosts
        t4 = new Teenager("X", "xolag", d4, "Italy");
        t5 = new Teenager("Y","Yak", d5,"Italy");
        t6 = new Teenager("Z", "Zander", d6, "Italy");
        hostList = new ArrayList<Teenager>() ;
        hostList.add(t4);
        hostList.add(t5);
        hostList.add(t6);


        //Guests for incompatibilityVsHobbies
        t7 = new Teenager("A", "A", d7, "ITALY");
        t8 = new Teenager("B", "B",  d8, "ITALY");
        guestListIncompatibility = new ArrayList<Teenager>() ;
        guestListIncompatibility.add(t7);
        guestListIncompatibility.add(t8);
        
        //Guests for incompatibilityVsHobbies
        t9 = new Teenager("C", "C", d9, "GERMANY");
        t10 = new Teenager("D", "D", d10, "GERMANY");
        hostListIncompatibility = new ArrayList<Teenager>() ;
        hostListIncompatibility.add(t9);
        hostListIncompatibility.add(t10);


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
        
        //Test the value calculated by the weight method
        assertEquals(Double.valueOf(99),AffectationUtil.weightV1(t4, t1));
        assertEquals(Double.valueOf(99),AffectationUtil.weightV1(t4,t2));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV1(t4,t3));

        assertEquals(Double.valueOf(100),AffectationUtil.weightV1(t5,t1));
        assertEquals(Double.valueOf(199),AffectationUtil.weightV1(t5,t2));
        assertEquals(Double.valueOf(98),AffectationUtil.weightV1(t5,t3));

        assertEquals(Double.valueOf(99),AffectationUtil.weightV1(t6,t1));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV1(t6,t2));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV1(t6,t3));


        //Create the graphe and calculate affectation
        graphe = AffectationUtil.creerGrapheTeenagerV1(hostList, guestList);
        affectation = new CalculAffectation<Teenager>(graphe,hostList,guestList);
        appariment.addAll(affectation.calculerAffectation());

        //Display the affectation result in the terminal
        System.out.println(AffectationUtil.afficherAppariement(appariment));
        //Have to get this affectation :
        //Yak--Callista(98.0)
        //Xolag--Bellatrix(99.0)
        //Zander--Adonia(99.0)

        //Test the total value got from the affectation
        assertEquals(Double.valueOf(296),affectation.getCout());
    }

    @Test
    public void testweightV1IncompatibilityVsHobbies(){
        //Double.valueOf nécessaire car ambiguité voir : https://stackoverflow.com/questions/1811103/java-junit-the-method-x-is-ambiguous-for-type-y 

        //Test the value calculated by the weight method
        assertEquals(Double.valueOf(100),AffectationUtil.weightV1(t9,t7));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV1(t9,t8));

        assertEquals(Double.valueOf(197),AffectationUtil.weightV1(t10,t7));
        assertEquals(Double.valueOf(100),AffectationUtil.weightV1(t10,t8));


        //Create the graphe and calculate affectation
        graphe = AffectationUtil.creerGrapheTeenagerV1(hostListIncompatibility, guestListIncompatibility);
        affectation = new CalculAffectation<Teenager>(graphe,hostListIncompatibility,guestListIncompatibility);
        appariment.addAll(affectation.calculerAffectation());

        //Display the affectation result in the terminal
        System.out.println(AffectationUtil.afficherAppariement(appariment));
        //Have to get this affectation :
        //C--A(100.0)
        //D--B(100.0)

        //Test the total value got from the affectation
        assertEquals(Double.valueOf(200),affectation.getCout());
    }
}
