package ullile.sae201.ihm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import ullile.sae201.Teenager;

/**
 * ForcerAffectation class
 * @author Elise Leroy
 */


public class ForcerAffectation extends Application{
    ArrayList<String> listeNomCompletHote = new ArrayList<>();
    ArrayList<String> listeNomCompletInvite = new ArrayList<>();
    ArrayList<String> hoteEnCoupleForce = new ArrayList<>();
    ArrayList<String> inviteEnCoupleForce = new ArrayList<>();

    //hashMap of arrayLists of teenagers
    public static Map<Teenager, Teenager> mapCouple = new HashMap<>();
    ComboBox<Teenager> listeInvite = new ComboBox<>();
    public static Stage s;

    
    public void start(Stage stage){
        ForcerAffectation.s = stage;

        VBox root = new VBox(30);
        VBox conteneurListeEtBouton = new VBox(30);
        Label titre = new Label("UniCo  | Fixer des affectations");
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
                Button retourDepot = new Button("Retour au dépôt");
        VBox vboxRetourDepot = new VBox();
        vboxRetourDepot.setAlignment(Pos.CENTER_RIGHT);
        vboxRetourDepot.getChildren().add(retourDepot);
        vboxRetourDepot.setPadding(new Insets(0, 30, 0, 0));
        vboxRetourDepot.setAlignment(Pos.TOP_RIGHT);
        retourDepot.setStyle("-fx-border-style: solid;"+
                "-fx-border-color: darksalmon;"+
                "-fx-background-radius: 10px;"+
                "-fx-border-radius: 10px;"+
                "-fx-padding: 5 15;"+
                "-fx-font-size: 12px;"+
                "-fx-background-color: darksalmon;");
        retourDepot.setOnMouseClicked(e ->{
            if(e.getButton()==MouseButton.PRIMARY){
                stage.close();
                new Depot().start(new Stage());
            }
        });

        

        HBox conteneurHoteInvi = new HBox(40);
        
        VBox conteneurHote = new VBox(10);
        Label titreHote = new Label("Hôtes");
        titreHote.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 25));
        VBox conteneurInvite = new VBox(10);
        Label titreInvite = new Label("Invité(e)s");
        titreInvite.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 25));

        Button ajouter = new Button("Ajouter");
        ajouter.setPadding(new Insets(10, 30, 10, 30));
        ListView<String> listeViewCoupleForce = new ListView<>();
        listeViewCoupleForce.setMaxWidth(400);
        listeViewCoupleForce.setMaxHeight(100);

        ComboBox<Teenager> listeHote = new ComboBox<>();
        listeHote.setMaxWidth(200);
        listeNomCompletHote.addAll(getNomComplet(Depot.platform.HOSTS));
        System.out.println(listeNomCompletHote);
        ObservableList<Teenager> listeObsHote = FXCollections.observableArrayList(Depot.platform.HOSTS);
        listeHote.setItems(listeObsHote);

        listeInvite.setMaxWidth(200);
        listeNomCompletInvite.addAll(getNomComplet(Depot.platform.GUESTS));
        ObservableList<Teenager> listeObsinvite = FXCollections.observableArrayList(Depot.platform.GUESTS);
        listeInvite.setItems(listeObsinvite);

        ajouter.setOnMouseClicked(e ->{
            if(e.getButton() == MouseButton.PRIMARY){
                if(listeHote.getValue() != null && listeInvite.getValue() != null){
                    listeViewCoupleForce.getItems().add(listeHote.getValue().minimalToString() + " - " + listeInvite.getValue().minimalToString());
                    mapCouple.put(listeHote.getValue(), listeInvite.getValue());
                    listeHote.getItems().remove(listeHote.getValue());
                    listeInvite.getItems().remove(listeInvite.getValue());
                }
            }
        });

        conteneurHote.getChildren().addAll(titreHote, listeHote);        
        conteneurInvite.getChildren().addAll(titreInvite, listeInvite);
        conteneurHoteInvi.getChildren().addAll(conteneurHote, conteneurInvite);
        conteneurHoteInvi.setAlignment(Pos.CENTER);
        conteneurListeEtBouton.getChildren().addAll(conteneurHoteInvi, ajouter, listeViewCoupleForce, continuer);
        conteneurListeEtBouton.setAlignment(Pos.CENTER);

        root.getChildren().addAll(titre, vboxRetourDepot, conteneurListeEtBouton);

        continuer.setOnMouseClicked(e ->{
            if(e.getButton() == MouseButton.PRIMARY){
                ForcerAffectation.s.close();
                Resultats tmp = new Resultats();
                tmp.start(new Stage());
            }
        });

        Scene scene = new Scene(root, 1000, 740);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("UniCo - Fixer des affectations");
        stage.show();


    }

    private ArrayList<String> getNomComplet(Set<Teenager> liste){
        ArrayList<String> retour = new ArrayList<String>();
        for(Teenager t : liste){
            listeNomCompletInvite.add(t.minimalToString());
        }
        return retour;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
