package ullile.sae201;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import ullile.sae201.exception.InvalidCSVException;
import ullile.sae201.graphe.AffectationUtil;

public class Main {

    public static File askForCSVFile() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in)))  {
            System.out.println("Please enter the path to the CSV file: ");
            String path = br.readLine();
            File f = new File(path);
            if(f.exists()) return f;
            else {
                System.out.println("File does not exist!");
                return askForCSVFile();
            }
        } catch(Exception e) {
            System.out.println("Error while reading input!");
            return askForCSVFile();
        }
    }

    public static Country askForACoutry() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in)))  {
            System.out.println("Please enter the name of the country: ");
            String path = br.readLine();
            return Country.valueOf(path);
        } catch(Exception e) {
            System.out.println("Error while reading input!");
            return askForACoutry();
        }
    }

    public static double askForADouble(double origin) throws NumberFormatException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in)))  {
            String path = br.readLine();
            if(path.equals("")) return origin;
            return Double.parseDouble(path);
        } catch(NumberFormatException e) {
            System.out.println("Error while reading input!");
        } catch(IOException ignored) {}
        return origin;
    }

    public static double askModifPonderation(String nom, double valeur) {
        // Demande une nouvelle pondération, mais si il veut laisser la valeur par défaut, il peut juste appuyer sur entrée (texte en anglais)
        System.out.println("Please enter the new value for " + nom + " (default is " + valeur + "): ");
        try {
            return askForADouble(valeur);
        } catch(NumberFormatException e) {
            return valeur;
        }
    }

    public static void main(String[] args) {
        System.err.println("Welcome to UniCo !\nMade with love by\n\t- Romain DEGEZ\n\t- Elise LEROY\n\t- Valentin THUILLIER");
        Platform p;
        try {
            p = CSVFile.read(askForCSVFile().getAbsolutePath(), true);
        } catch (InvalidCSVException e) {
            System.out.println("CSV File given is invalid!");
            return;
        }
        AffectationUtil.ageWeight = askModifPonderation("Bonus de compatibilité du même âge", AffectationUtil.ageWeight);
        AffectationUtil.genderWeight = askModifPonderation("Bonus de compatibilité du même genre", AffectationUtil.genderWeight);
        AffectationUtil.hobbiesWeight = askModifPonderation("Bonus de compatibilité des mêmes hobbies", AffectationUtil.hobbiesWeight);
        AffectationUtil.foodWeight = askModifPonderation("Bonus de compatibilité des mêmes préférences alimentaires", AffectationUtil.foodWeight);
        AffectationUtil.allergyWeight = askModifPonderation("Malus de compatibilité des allergies", AffectationUtil.allergyWeight);
        AffectationUtil.lessCompatibleHistoryWeight = askModifPonderation("Malus de compatibilité des histoires", AffectationUtil.lessCompatibleHistoryWeight);
        AffectationUtil.superCompatibleHistoryWeight = askModifPonderation("Bonus de compatibilité des histoires", AffectationUtil.superCompatibleHistoryWeight);

        System.out.print("Please enter the host country: ");
        Country hostCountry = askForACoutry();
        System.out.print("Please enter the guest country: ");
        Country guestCountry = askForACoutry();

    }

}