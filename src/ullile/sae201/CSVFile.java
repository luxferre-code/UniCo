package ullile.sae201;

import ullile.sae201.exception.InvalidCriterion;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * CSVFile class
 * @author Valentin Thuillier
 */
public class CSVFile {

    private static final String DELIMITER = ";";
    private static final String FIlE_DELIMITER = System.getProperty("user.separator");

    /**
     * Read a line of the CSV file and return a Teenager object
     * @param line (String) - The line to read
     * @return (Teenager) - The teenager object
     * @throws IllegalArgumentException - If the line is not valid
     * @throws InvalidCriterion - If the criterion is not valid
     */
    public static Teenager readLine(String line) throws IllegalArgumentException, InvalidCriterion {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(DELIMITER);
        String forename = scanner.next();
        String name = scanner.next();
        Country country = Country.valueOf(scanner.next());
        LocalDate birthday = LocalDate.parse(scanner.next());
        String hobbies = scanner.next();
        Criterion guestAnimal = new Criterion(scanner.next(), CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion hostHasAnimal = new Criterion(scanner.next(), CriterionName.HOST_HAS_ANIMAL);
        Criterion guestFood = new Criterion(scanner.next(), CriterionName.GUEST_FOOD);
        Criterion hostFood = new Criterion(scanner.next(), CriterionName.HOST_FOOD);
        Criterion gender = new Criterion(scanner.next(), CriterionName.GENDER);
        Criterion pairGender = new Criterion(scanner.next(), CriterionName.PAIR_GENDER);
        Criterion history = new Criterion(scanner.next(), CriterionName.HISTORY);
        Teenager t = new Teenager(name, forename, birthday, country);
        t.addRequirement(guestAnimal);
        t.addRequirement(hostHasAnimal);
        t.addRequirement(guestFood);
        t.addRequirement(hostFood);
        t.addRequirement(gender);
        t.addRequirement(pairGender);
        t.addRequirement(history);
        return t;
    }

    private static String getDirWhoResourcesIs(String dir) {
        //TODO: Not working !
        File f = new File(dir);
        if(!f.exists()) {
            return getDirWhoResourcesIs(dir + ".." + FIlE_DELIMITER);
        }
        List<String> filesList = Arrays.asList(f.list());
        if(filesList.contains("resources")) {
            return FIlE_DELIMITER;
        } else {
            return getDirWhoResourcesIs(dir + ".." + FIlE_DELIMITER);
        }
    }

    private static String getDirWhoResourcesIs() {
        return getDirWhoResourcesIs(".");
    }

    public static Platform read(String fileName) {
        Platform p = new Platform();
        try(BufferedReader br = new BufferedReader(new FileReader(getDirWhoResourcesIs() + "resources" + FIlE_DELIMITER + fileName)))  {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    p.addTeenager(readLine(line));
                } catch(IllegalArgumentException e) {
                    System.out.println("Error while reading the file");
                } catch(InvalidCriterion e) {
                    System.out.println("Invalid criterion found ! Teenager not added");
                }

            }
        } catch (RuntimeException | IOException ignored) { System.out.println("Error while reading the file"); }

        return p;
    }

    public static void main(String[] args) {
        //System.out.println(getDirWhoResourcesIs() + "resources" + FIlE_DELIMITER);
        Platform p = read("adosAleatoires.csv");
        System.out.println(p);
    }

}
