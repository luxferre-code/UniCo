package ullile.sae201.ihm;

import ullile.sae201.Teenager;
import ullile.sae201.Platform;

import java.util.HashSet;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class RetirerEleves extends Application{
    public void start (Stage stage){
        Platform temp = new Platform();
        HashSet<Teenager> teenagers = (HashSet<Teenager>) temp.getTeenagers();

        VBox root = new VBox();
        Label titre = new Label("UniCo  | Retirer des élèves");
        titre.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 34));
        titre.setPadding(new Insets(20, 0, 0, 20));
        Button continuer = new Button("Valider et continuer");
        continuer.setStyle("-fx-border-style: solid;"+
                "-fx-border-color: lightgreen;"+
                "-fx-background-radius: 10px;"+
                "-fx-border-radius: 10px;"+
                "-fx-padding: 15 30;"+
                "-fx-background-color: lightgreen;");

        HBox conteneurHoteInvi = new HBox();
        Label titreHote = new Label("Hôtes");
        titreHote.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 25));
        VBox boxHote = new VBox();

        final ObservableList<String> listeHote = FXCollections.observableArrayList();
        for(int i=0; i<teenagers.size(); i++){
            //liste
        } 

        Label titreInvite = new Label("Invité(e)s");
        titreInvite.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 25));
        VBox boxInvite = new VBox();

        conteneurHoteInvi.getChildren().addAll(boxHote, boxInvite);


        root.getChildren().addAll(titre, conteneurHoteInvi, continuer);

        Scene scene = new Scene(root, 1194, 834);
        
        stage.setScene(scene);
        stage.setTitle("UniCo - Retirer des élèves");
        stage.show();
    }



    public static void main(String[] args) {
        Application.launch(args);
    }
}
