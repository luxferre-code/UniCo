package ullile.sae201.ihm;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FicheEtudiant extends Application{

    public static Stage s;

    public void start(Stage stage){
        this.s = stage;
        Label titre = new Label("UniCo  | Modification des pondérations");
        titre.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 34));
        titre.setPadding(new Insets(20, 0, 0, 20));
        Button fermer = new Button("Valider et continuer");
        fermer.setStyle("-fx-border-style: solid;"+
                "-fx-border-color: lightgreen;"+
                "-fx-background-radius: 10px;"+
                "-fx-border-radius: 10px;"+
                "-fx-padding: 10 30;"+
                "-fx-font-size: 16px;"+
                "-fx-background-color: lightgreen;");
        fermer.setOnMouseClicked(e ->{
            if(e.getButton()==MouseButton.PRIMARY){
                FicheEtudiant.s.close();
            }
        });



        VBox root = new VBox();



        Scene scene = new Scene(root, 1000, 700);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("UniCo - Fiche du couple");
        stage.show();
    }

    public static void creationFiche(){
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
