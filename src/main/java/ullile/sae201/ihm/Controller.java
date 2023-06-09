package ullile.sae201.ihm;

import javafx.stage.FileChooser;
import ullile.sae201.CSVFile;
import ullile.sae201.exception.InvalidCSVException;

import java.io.File;

public class Controller {

    public static final String MAIN_MENU_FXML = "mainMenu.fxml";
    public static final String APPAIR_FXML = "appairMenu.fxml";

    public void initialize() {
        System.out.println("Controller initialized successfully !");
    }

    public void exitButton() {
        System.exit(0);
    }

    public void goToAppair() {
        Main.goTo(APPAIR_FXML);
    }

    public void goToMainMenu() {
        Main.goTo(MAIN_MENU_FXML);
    }

    public void fileChooser() throws InvalidCSVException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(Main.stage);
        if(file != null) {
            Main.platform = CSVFile.read(file.getAbsolutePath());
        }
        System.out.println(Main.platform);
    }

}
