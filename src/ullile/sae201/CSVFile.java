package ullile.sae201;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

import fr.ulille.but.sae2_02.graphes.Arete;
import ullile.sae201.exception.InvalidCSVException;
import ullile.sae201.exception.InvalidCriterion;

/**
 * CSVFile class
 *
 * @author Valentin Thuillier, Elise Leroy
 */
public class CSVFile {

    public static final String FIlE_DELIMITER = System.getProperty("file.separator");
    private static final String DELIMITER = ";";
    private static List<String> HEADER = new ArrayList<String>() {{
        add("FORENAME");
        add("NAME");
        add("COUNTRY");
        add("BIRTH_DATE");
        add("HOBBIES");
        add("GUEST_ANIMAL_ALLERGY");
        add("HOST_HAS_ANIMAL");
        add("GUEST_FOOD");
        add("HOST_FOOD");
        add("GENDER");
        add("PAIR_GENDER");
        add("HISTORY");
    }};

    /**
     * Read a line of the CSV file and return a Teenager object
     *
     * @param line (String) - The line to read
     * @return (Teenager) - The teenager object
     * @throws IllegalArgumentException - If the line is not valid
     * @throws InvalidCriterion         - If the criterion is not valid
     */
    public static Teenager readLine(String line) throws IllegalArgumentException, InvalidCriterion, NoSuchElementException {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(DELIMITER);

        Map<String, String> map = new HashMap<>();

        for(int idx = 0; idx < HEADER.size(); idx++) {
            // Si c'est le dernier
            if(idx == HEADER.size() - 1) {
                try { // Des fois, etant donné que la derniere colonne est vide, il y a une exception
                    map.put(HEADER.get(idx), scanner.next().replace("\"", ""));
                } catch (NoSuchElementException e) {
                    map.put(HEADER.get(idx), "");
                }
            } else {
                map.put(HEADER.get(idx), scanner.next().replace("\"", ""));
            }
        }
        scanner.close();

        Teenager t = new Teenager(map.get("NAME"), map.get("FORENAME"), LocalDate.parse(map.get("BIRTH_DATE")), Country.valueOf(map.get("COUNTRY")));
        t.addRequirement(CriterionName.HOBBIES, map.get("HOBBIES"));
        t.addRequirement(CriterionName.GUEST_ANIMAL_ALLERGY, map.get("GUEST_ANIMAL_ALLERGY"));
        t.addRequirement(CriterionName.HOST_HAS_ANIMAL, map.get("HOST_HAS_ANIMAL"));
        t.addRequirement(CriterionName.GUEST_FOOD, map.get("GUEST_FOOD"));
        t.addRequirement(CriterionName.HOST_FOOD, map.get("HOST_FOOD"));
        t.addRequirement(CriterionName.GENDER, map.get("GENDER"));
        t.addRequirement(CriterionName.PAIR_GENDER, map.get("PAIR_GENDER"));
        t.addRequirement(CriterionName.HISTORY, map.get("HISTORY"));
        return t;
    }

    /**
     * Get the directory who contains the resources directory
     *
     * @param dir (String) - The directory to check
     * @return (String) - The directory who contains the resources directory
     */
    public static String getDirWhoResourcesIs(String dir) {
        File f = new File(dir);
        if (!f.exists()) {
            return getDirWhoResourcesIs(dir + ".." + FIlE_DELIMITER);
        }
        List<String> filesList = Arrays.asList(Objects.requireNonNull(f.list()));
        if (filesList.contains("resources")) {
            return dir + FIlE_DELIMITER;
        } else {
            return getDirWhoResourcesIs(dir + ".." + FIlE_DELIMITER);
        }
    }

    /**
     * Change the header of the CSV file
     * @param line (String) - The new header
     */
    public static void headerModifier(String line) {
        HEADER = Arrays.asList(line.split(DELIMITER));
    }

    /**
     * Get the directory who contains the resources directory
     *
     * @return (String) - The directory who contains the resources directory
     */
    public static String getDirWhoResourcesIs() {
        return getDirWhoResourcesIs(".");
    }

    /**
     * Read the CSV file and return a Platform object
     *
     * @param fileName   (String) - The name of the file to read
     * @param haveHeader (boolean) - If the file have a header
     * @return (Platform) - The platform object
     * @throws InvalidCSVException
     */
    public static Platform read(String fileName, boolean haveHeader) throws InvalidCSVException {
        Platform p = new Platform();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            if (haveHeader) {
                headerModifier(br.readLine());
            }
            while ((line = br.readLine()) != null) {
                try {
                    p.addTeenager(readLine(line));
                } catch (IllegalArgumentException | NoSuchElementException e) {
                    System.out.println("Error while reading the line");
                    //e.printStackTrace();
                    throw new InvalidCSVException();
                } catch (InvalidCriterion e) {
                    System.out.println("Invalid criterion found ! Teenager not added");
                    throw new InvalidCSVException();
                }
            }
        } catch (RuntimeException | IOException e) {
            System.out.println("Error while reading the file");
            //e.printStackTrace();
            throw new InvalidCSVException();
        }

        return p;
    }

    public static Platform read(String filename) throws InvalidCSVException {
        return read(filename, true);
    }

    /**
     * Export the teenager data in a line of a CSV file
     *
     * @param t (Platform) - The teenager to export
     * @return (String) - The string to write in the file
     */
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

    /**
     * Export the data of the platform in a CSV file
     *
     * @param p        (Platform) - The platform to export
     * @param nameFile (String) - The name of the file to export
     * @return (boolean) - If the export is a success, false otherwise
     */
    public static boolean exportData(Platform p, String nameFile) {

        File f = new File(nameFile);
        if (f.exists()) {
            f.delete();
        }

        try (BufferedWriter br = new BufferedWriter(new FileWriter(nameFile))) {
            br.write("FORENAME;NAME;COUNTRY;BIRTH_DATE;HOBBIES;GUEST_ANIMAL_ALLERGY;HOST_HAS_ANIMAL;GUEST_FOOD;HOST_FOOD;GENDER;PAIR_GENDER;HISTORY\n");
            for (Teenager t : p.getTeenagers()) {
                br.write(exportLineTeenager(t));
                br.newLine();
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws InvalidCSVException {
        Platform p = read("testCSVReader.csv");
        System.out.println(p);
        exportData(p, "test2.csv");
    }

    public static void exportAppariement(ArrayList<Arete<Teenager>> appariment, String fileDir) {
        fileDir += FIlE_DELIMITER + "appariement.csv";
        File f = new File(fileDir);
        if (f.exists()) {
            f.delete();
        }

        try (BufferedWriter br = new BufferedWriter(new FileWriter(fileDir))) {
            br.write("FORENAME;NAME;COUNTRY;BIRTH_DATE;HOBBIES;GUEST_ANIMAL_ALLERGY;HOST_HAS_ANIMAL;GUEST_FOOD;HOST_FOOD;GENDER;PAIR_GENDER;HISTORY;");
            br.write("FORENAME;NAME;COUNTRY;BIRTH_DATE;HOBBIES;GUEST_ANIMAL_ALLERGY;HOST_HAS_ANIMAL;GUEST_FOOD;HOST_FOOD;GENDER;PAIR_GENDER;HISTORY_guest\n".replace(";", "_guest;"));
            for(Arete<Teenager> a : appariment) {
                br.write(exportLineTeenager(a.getExtremite1()));
                br.write(DELIMITER);
                br.write(exportLineTeenager(a.getExtremite1()));
                br.newLine();
            }
        } catch (IOException ignored) {}
    }


}
