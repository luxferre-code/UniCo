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
    private static final String FIlE_DELIMITER = System.getProperty("file.separator");

    /**
     * Read a line of the CSV file and return a Teenager object
     * @param line (String) - The line to read
     * @return (Teenager) - The teenager object
     * @throws IllegalArgumentException - If the line is not valid
     * @throws InvalidCriterion - If the criterion is not valid
     */
    public static Teenager readLine(String line) throws IllegalArgumentException, InvalidCriterion, NoSuchElementException {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(DELIMITER);
        String forename = scanner.next().replace("\"", "");
        String name = scanner.next().replace("\"", "");
        Country country = Country.valueOf(scanner.next().replace("\"", ""));
        LocalDate birthday = LocalDate.parse(scanner.next().replace("\"", ""));
        String hobbies = scanner.next().replace("\"", "");
        Criterion guestAnimal = new Criterion(scanner.next().replace("\"", ""), CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion hostHasAnimal = new Criterion(scanner.next().replace("\"", ""), CriterionName.HOST_HAS_ANIMAL);
        Criterion guestFood = new Criterion(scanner.next().replace("\"", ""), CriterionName.GUEST_FOOD);
        Criterion hostFood = new Criterion(scanner.next().replace("\"", ""), CriterionName.HOST_FOOD);
        Criterion gender = new Criterion(scanner.next().replace("\"", ""), CriterionName.GENDER);
        Criterion pairGender = new Criterion(scanner.next().replace("\"", ""), CriterionName.PAIR_GENDER);
        Criterion history;
        if(scanner.hasNext()) { history = new Criterion(scanner.next().replace("\"", ""), CriterionName.HISTORY); }
        else { history = new Criterion("", CriterionName.HISTORY); }
        Teenager t = new Teenager(name, forename, birthday, country);
        t.addRequirement(CriterionName.HOBBIES, hobbies);
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
        File f = new File(dir);
        if(!f.exists()) {
            return getDirWhoResourcesIs(dir + ".." + FIlE_DELIMITER);
        }
        List<String> filesList = Arrays.asList(Objects.requireNonNull(f.list()));
        if(filesList.contains("resources")) {
            return dir + FIlE_DELIMITER;
        } else {
            return getDirWhoResourcesIs(dir + ".." + FIlE_DELIMITER);
        }
    }

    private static String getDirWhoResourcesIs() {
        return getDirWhoResourcesIs(".");
    }

    public static Platform read(String fileName, boolean haveHeader) {
        Platform p = new Platform();
        try(BufferedReader br = new BufferedReader(new FileReader(getDirWhoResourcesIs() + "resources" + FIlE_DELIMITER + fileName)))  {
            String line;
            if(haveHeader) { br.readLine(); } // Skip the header if the file have one
            while ((line = br.readLine()) != null) {
                try {
                    p.addTeenager(readLine(line));
                } catch(IllegalArgumentException | NoSuchElementException e) {
                    System.out.println("Error while reading the line");
                } catch(InvalidCriterion e) {
                    System.out.println("Invalid criterion found ! Teenager not added");
                }
            }
        } catch (RuntimeException | IOException e) { System.out.println("Error while reading the file"); e.printStackTrace(); }

        return p;
    }

    private static String exportLineTeenager(Teenager t) {
        StringBuilder sb = new StringBuilder();
        sb.append(t.getForename()).append(DELIMITER);
        sb.append(t.getName()).append(DELIMITER);
        sb.append(t.getCountry()).append(DELIMITER);
        sb.append(t.getDateNaiss().toString()).append(DELIMITER);
        sb.append(t.getRequirements().get(CriterionName.HOBBIES).getValue()).append(DELIMITER);
        sb.append(t.getRequirements().get(CriterionName.GUEST_ANIMAL_ALLERGY).getValue()).append(DELIMITER);
        sb.append(t.getRequirements().get(CriterionName.HOST_HAS_ANIMAL).getValue()).append(DELIMITER);
        sb.append(t.getRequirements().get(CriterionName.GUEST_FOOD).getValue()).append(DELIMITER);
        sb.append(t.getRequirements().get(CriterionName.HOST_FOOD).getValue()).append(DELIMITER);
        sb.append(t.getRequirements().get(CriterionName.GENDER).getValue()).append(DELIMITER);
        sb.append(t.getRequirements().get(CriterionName.PAIR_GENDER).getValue()).append(DELIMITER);
        sb.append(t.getRequirements().get(CriterionName.HISTORY).getValue());
        return sb.toString();
    }

    public static boolean exportData(Platform p, String nameFile) {

        File f = new File(getDirWhoResourcesIs() + "resources" + FIlE_DELIMITER + nameFile);
        if(f.exists()) {
            f.delete();
        }

        try(BufferedWriter br = new BufferedWriter(new FileWriter(getDirWhoResourcesIs() + "resources" + FIlE_DELIMITER + nameFile)))  {
            for(Teenager t : p.getTeenagers()) {
                br.write(exportLineTeenager(t));
                br.newLine();
            }
        } catch(IOException e) {
            return false;
        }
        return true;
    }


}
