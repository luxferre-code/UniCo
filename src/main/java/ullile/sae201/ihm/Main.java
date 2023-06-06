package ullile.sae201.ihm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ullile.sae201.Platform;

import java.net.URL;

public class Main extends Application {

    public static Platform platform = new Platform();
    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Main.stage = stage;
        FXMLLoader loader = new FXMLLoader();
        URL fxmlFileUrl = getClass().getResource("mainMenu.fxml");
        if (fxmlFileUrl == null) {
            System.out.println("Impossible de charger le fichier fxml");
            System.exit(-1);
        }
        loader.setLocation(fxmlFileUrl);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Main.stage.setScene(scene);
        Main.stage.setTitle("UniConnect | Main Menu");
        Main.stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public static void goTo(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlFileUrl = Main.class.getResource(fxmlFileName);
            if (fxmlFileUrl == null) {
                System.out.println("Impossible de charger le fichier fxml");
                System.exit(-1);
            }
            loader.setLocation(fxmlFileUrl);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Main.stage.close();
            Main.stage = new Stage();
            Main.stage.setScene(scene);
            Main.stage.setTitle("UniConnect | " + fxmlFileName);
            Main.stage.show();
        } catch (Exception e) {
            System.out.println("Impossible de charger le fichier fxml");
            System.exit(-1);
        }
    }

}
