package ullile.sae201.ihm;

import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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


public class ForcerAffectation extends Application{

    ListView<String> listeInvite = new ListView<>();

    public void start(Stage stage){
        
        class ListOnClickListener implements ListChangeListener<String>{
            public void onChanged(Change<? extends String> report){
                String[] listeNomInvite = new String[]{"Petunia","Robert","Jean","Pierre"};//modifier par getNomInvite()
                listeInvite.getItems().clear();
                listeInvite.getItems().addAll(listeNomInvite);

            }
        }

        VBox root = new VBox(30);
        VBox conteneurListeEtBouton = new VBox(120);
        Label titre = new Label("UniCo  | Modification des pondérations");
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

        HBox conteneurHoteInvi = new HBox(40);
        
        VBox conteneurHote = new VBox(10);
        Label titreHote = new Label("Hôtes");
        titreHote.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 25));
        ListView<String> listeHote = new ListView<>();
        for(int i=0; i<24; i++){
            listeHote.getItems().add("nom Hote"); //getTeenganer name if Teenager == hote
        }
        conteneurHote.getChildren().addAll(titreHote, listeHote);

        listeHote.getSelectionModel().getSelectedItems().addListener(new ListOnClickListener());

        VBox conteneurInvite = new VBox(10);
        Label titreInvite = new Label("Invité(e)s");
        titreInvite.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 25));

        conteneurInvite.getChildren().addAll(titreInvite, listeInvite);
        conteneurHoteInvi.getChildren().addAll(conteneurHote, conteneurInvite);
        conteneurHoteInvi.setAlignment(Pos.CENTER);
        conteneurListeEtBouton.getChildren().addAll(conteneurHoteInvi, continuer);
        conteneurListeEtBouton.setAlignment(Pos.CENTER);

        root.getChildren().addAll(titre, conteneurListeEtBouton);

        Scene scene = new Scene(root, 1194, 834);
        stage.setScene(scene);
        stage.setTitle("UniCo - Modification des pondérations");
        stage.show();


    }




    public static void main(String[] args) {
        Application.launch(args);
    }
}
