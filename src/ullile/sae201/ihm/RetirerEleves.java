package ullile.sae201.ihm;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Depot class
 * @author Elise Leroy
 */


public class RetirerEleves extends Application{
    public void start (Stage stage){
        //Platform temp = new Platform();
        //HashSet<Teenager> teenagers = (HashSet<Teenager>) temp.getTeenagers();

        VBox root = new VBox();
        Label titre = new Label("UniCo  | Retirer des élèves");
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

        HBox conteneurHoteInvi = new HBox(100);

        Label titreHote = new Label("Hôtes");
        titreHote.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 25));
        VBox vboxHote = new VBox(10);

        Label titreInvite = new Label("Invité(e)s");
        titreInvite.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 25));
        VBox vboxInvite = new VBox(10);


        //Structure de création de la liste hôte avec checkbox
        List<HBox> listeHote = new ArrayList<>();
        for(int i=0; i<24; i++){
            listeHote.add(listeCreator("test")); //getTeenganer name if Teenager == hote
        }
        ObservableList<HBox> listeObs = FXCollections.observableList(listeHote);
        ListView<HBox> listViewHote = new ListView<>();
        listViewHote.setItems(listeObs);
        listViewHote.setPrefWidth(350);

        //Structure de création de la liste invités avec checkbox
        List<HBox> listeInvite = new ArrayList<>();
        for(int i=0; i<24; i++){
            listeInvite.add(listeCreator("Louis Jean Joseph De La Vega"));
        }
        ObservableList<HBox> listeObs2 = FXCollections.observableList(listeInvite);
        ListView<HBox> listViewInvite = new ListView<>();
        listViewInvite.setItems(listeObs2);
        listViewInvite.setPrefWidth(350);
        

        vboxInvite.getChildren().addAll(titreInvite, listViewInvite);
        vboxHote.getChildren().addAll(titreHote, listViewHote);
        conteneurHoteInvi.getChildren().addAll(vboxHote, vboxInvite);
        conteneurHoteInvi.setAlignment(Pos.CENTER);

        VBox conteneurListeEtContinuer = new VBox();
        conteneurListeEtContinuer.getChildren().addAll(conteneurHoteInvi, continuer);
        conteneurListeEtContinuer.setAlignment(Pos.CENTER);
        conteneurHoteInvi.setPadding(new Insets(40, 0, 120, 0));


        root.getChildren().addAll(titre, conteneurListeEtContinuer);

        Scene scene = new Scene(root, 1194, 834);
        
        stage.setScene(scene);
        stage.setTitle("UniCo - Retirer des élèves");
        stage.show();
    }

    private HBox listeCreator(String name){
        HBox ligneListeTeenager = new HBox(4);
        ligneListeTeenager.setAlignment(Pos.CENTER_LEFT);
        Label nomTeenager = new Label(name);
        nomTeenager.setFont(Font.font("Banhnschrift", FontWeight.NORMAL, null, 20));
        nomTeenager.setPadding(new Insets(0, 0, 0, 5));
        CheckBox checkbox = new CheckBox();
        checkbox.setPadding(new Insets(8));
        ligneListeTeenager.getChildren().addAll(checkbox, nomTeenager);
        return ligneListeTeenager;
    }



    public static void main(String[] args) {
        Application.launch(args);
    }
}
