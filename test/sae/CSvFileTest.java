package sae;

import org.junit.Before;
import org.junit.Test;
import ullile.sae201.*;

import java.io.File;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * CSVFileTest class - Test the CSVFile class
 *
 * @author Valentin Thuillier
 * @see CSVFile
 */
public class CSvFileTest {

    private static final String FILENAME = "testCSVReader.csv";

    Platform platform;
    Teenager t1, t2, t3;

    @Before
    public void setUp() throws Exception {
        /*
        Mazhug;Jalai;ITALY;2010-06-01;culture,science;no;no;;;male;male;
        Gnemdiss;Inora;GERMANY;2009-11-26;;no;no;;nonuts,vegetarian;female;;other
        Snallbam;Abin;FRANCE;2008-03-06;science;no;no;;;female;;
         */
        platform = new Platform();
        t1 = new Teenager("Jalai", "Mazhug", LocalDate.of(2010, 6, 1), Country.ITALY);
        t1.addRequirement(new Criterion("culture,science", CriterionName.HOBBIES));
        t1.addRequirement(new Criterion("no", CriterionName.GUEST_ANIMAL_ALLERGY));
        t1.addRequirement(new Criterion("no", CriterionName.HOST_HAS_ANIMAL));
        t1.addRequirement(new Criterion("", CriterionName.GUEST_FOOD));
        t1.addRequirement(new Criterion("", CriterionName.HOST_FOOD));
        t1.addRequirement(new Criterion("male", CriterionName.GENDER));
        t1.addRequirement(new Criterion("male", CriterionName.PAIR_GENDER));
        t1.addRequirement(new Criterion("", CriterionName.HISTORY));

        t2 = new Teenager("Inora", "Gnemdiss", LocalDate.of(2009, 11, 26), Country.GERMANY);
        t2.addRequirement(new Criterion("", CriterionName.HOBBIES));
        t2.addRequirement(new Criterion("no", CriterionName.GUEST_ANIMAL_ALLERGY));
        t2.addRequirement(new Criterion("no", CriterionName.HOST_HAS_ANIMAL));
        t2.addRequirement(new Criterion("", CriterionName.GUEST_FOOD));
        t2.addRequirement(new Criterion("nonuts,vegetarian", CriterionName.HOST_FOOD));
        t2.addRequirement(new Criterion("female", CriterionName.GENDER));
        t2.addRequirement(new Criterion("", CriterionName.PAIR_GENDER));
        t2.addRequirement(new Criterion("other", CriterionName.HISTORY));

        t3 = new Teenager("Abin", "Snallbam", LocalDate.of(2008, 3, 6), Country.FRANCE);
        t3.addRequirement(new Criterion("science", CriterionName.HOBBIES));
        t3.addRequirement(new Criterion("no", CriterionName.GUEST_ANIMAL_ALLERGY));
        t3.addRequirement(new Criterion("no", CriterionName.HOST_HAS_ANIMAL));
        t3.addRequirement(new Criterion("", CriterionName.GUEST_FOOD));
        t3.addRequirement(new Criterion("", CriterionName.HOST_FOOD));
        t3.addRequirement(new Criterion("female", CriterionName.GENDER));
        t3.addRequirement(new Criterion("", CriterionName.PAIR_GENDER));
        t3.addRequirement(new Criterion("", CriterionName.HISTORY));

        platform.addTeenager(t1);
        platform.addTeenager(t2);
        platform.addTeenager(t3);

    }

    @Test
    public void testRead() {
        Platform p = CSVFile.read(FILENAME, true);
        assertEquals(p.getTeenagers().size(), platform.getTeenagers().size());
        for (Teenager t : platform.getTeenagers()) {
            boolean found = false;
            for (Teenager t2 : p.getTeenagers()) {
                if (t.equals(t2)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found);
        }
    }

    @Test
    public void testWrite() {
        CSVFile.exportData(platform, FILENAME + ".test");
        Platform p = CSVFile.read(FILENAME + ".test", true);
        assertEquals(p.getTeenagers().size(), platform.getTeenagers().size());
        for (Teenager t : platform.getTeenagers()) {
            boolean found = false;
            for (Teenager t2 : p.getTeenagers()) {
                if (t.equals(t2)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found);
        }
        File f = new File(CSVFile.getDirWhoResourcesIs() + "resources" + CSVFile.FIlE_DELIMITER + FILENAME + ".test");
        f.delete();
    }


}
