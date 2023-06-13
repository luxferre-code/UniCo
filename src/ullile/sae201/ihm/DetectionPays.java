package ullile.sae201.ihm;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import ullile.sae201.Country;
import ullile.sae201.Teenager;

/**
 * DetectionPays class
 * @author Elise Leroy
 */

public class DetectionPays extends Application{
    public static Stage s;
    ComboBox<Country> paysHote;
    ComboBox<Country> paysInvite;

    public void start(Stage stage) {
        DetectionPays.s = stage;
        Label titre = new Label("UniCo  | Précision des pays");
        titre.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 34));
        titre.setPadding(new Insets(20, 0, 0, 20));
        Button continuer = new Button("Valider et continuer");
        continuer.setStyle("-fx-border-style: solid;"+
                "-fx-border-color: lightgreen;"+
                "-fx-background-radius: 10px;"+
                "-fx-border-radius: 10px;"+
                "-fx-padding: 10 30;"+
                "-fx-font-size: 16px;"+
                "-fx-background-color: lightgreen;");
        continuer.setOnMouseClicked(e ->{
            if(e.getButton()==MouseButton.PRIMARY){
                if(paysHote.getValue() == null || paysInvite.getValue() == null){
                    Alert nullAlert = new Alert(AlertType.ERROR);
                    nullAlert.setTitle("Pays Invalides");
                    nullAlert.setContentText("Erreur");
                    nullAlert.showAndWait();
                    return;
                } else if(paysHote.getValue().equals(paysInvite.getValue())){
                    Alert sameAlert = new Alert(AlertType.ERROR);
                    sameAlert.setTitle("Pays Invalides");
                    sameAlert.setContentText("Erreur, les pays doivent être différents");
                    sameAlert.showAndWait();
                    return;
                } else {
                    DetectionPays.s.close();
                    RetirerEleves tmp = new RetirerEleves();
                    tmp.start(new Stage());
                } 
            }
        });

        VBox root = new VBox();
        VBox conteneurPrincipal = new VBox(280);
        VBox conteneurHote = new VBox(20);
        VBox conteneurInvite = new VBox(20);
        VBox conteneurHoteInvi = new VBox(20);
        Label instructionHote = new Label("Veuillez sélectionner le pays des hôtes");
        instructionHote.setFont(Font.font("Bahnschrift", FontWeight.NORMAL, null, 16));
        Label instructionInvite = new Label("Veuillez sélectionner le pays des invité(e)s");
        instructionInvite.setFont(Font.font("Bahnschrift", FontWeight.NORMAL, null, 16));
        paysHote = new ComboBox<>();
        paysInvite = new ComboBox<>();
        conteneurHote.getChildren().addAll(instructionHote, paysHote);
        conteneurInvite.getChildren().addAll(instructionInvite, paysInvite);
        conteneurHote.setPadding(new Insets(20, 0, 0, 20));
        conteneurHote.setAlignment(Pos.CENTER);
        conteneurInvite.setPadding(new Insets(20, 0, 0, 20));
        conteneurInvite.setAlignment(Pos.CENTER);
        conteneurHoteInvi.getChildren().addAll(conteneurHote, conteneurInvite);
        conteneurPrincipal.getChildren().addAll(conteneurHoteInvi, continuer);
        conteneurPrincipal.setAlignment(Pos.CENTER);
        conteneurPrincipal.setPadding(new Insets(40, 0, 0, 20));
        root.getChildren().addAll(titre, conteneurPrincipal);
        setComboBox();

        paysHote.setOnAction(e ->{
            Depot.platform.setHostCountry(paysHote.getValue());
        });

        paysInvite.setOnAction(e ->{
            Depot.platform.setInviteCountry(paysInvite.getValue());
        });

        Scene scene = new Scene(root, 1000, 700);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("UniCo - Précisez les pays");
        stage.show();
    }
         

    public void setComboBox(){
        List<Country> setPays = new ArrayList<>();
        for(Teenager t : Depot.platform.getTeenagers()){
            if(!setPays.contains(t.getCountry())){
                setPays.add(t.getCountry());
            }
        }
        ObservableList<Country> obsPays = FXCollections.observableList(setPays);

        /*for(Country c : Country.values()){
            obsPays.add(c);
        };*/

        paysHote.setItems(obsPays);
        paysInvite.setItems(obsPays);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
