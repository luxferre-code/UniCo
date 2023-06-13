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
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import ullile.sae201.Teenager;

/**
 * RetirerEleves class
 * @author Elise Leroy
 */


public class RetirerEleves extends Application{

    public static Stage s;
    List<HBox> listeHboxHote;
    List<HBox> listeHboxInvite;
    public static ArrayList<Teenager> listeHoteRetire = new ArrayList<>();
    public static ArrayList<Teenager> listeInviteRetire = new ArrayList<>();

    public void start (Stage stage){
        RetirerEleves.s = stage;
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
        continuer.setOnMouseClicked(e ->{
            if(e.getButton()==MouseButton.PRIMARY){
                retirerEleveDeLaListe();
                RetirerEleves.s.close();
                new ForcerAffectation().start(new Stage());
            }
        });

        HBox conteneurHoteInvi = new HBox(100);

        Label titreHote = new Label("Hôtes");
        titreHote.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 25));
        VBox vboxHote = new VBox(10);

        Label titreInvite = new Label("Invité(e)s");
        titreInvite.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 25));
        VBox vboxInvite = new VBox(10);


        //Structure de création de la liste hôte avec checkbox
        this.listeHboxHote = new ArrayList<>();
        for(Teenager t : Depot.platform.SORTED_HOSTS){
            this.listeHboxHote.add(listeCreator(t, true));
        }
        ObservableList<HBox> listeObs = FXCollections.observableList(listeHboxHote);
        ListView<HBox> listViewHote = new ListView<>();
        listViewHote.setItems(listeObs);
        listViewHote.setPrefWidth(350);

        //Structure de création de la liste invités avec checkbox
        this.listeHboxInvite = new ArrayList<>();
        for(Teenager t : Depot.platform.SORTED_GUESTS){
            this.listeHboxInvite.add(listeCreator(t, false));
        }
        ObservableList<HBox> listeObs2 = FXCollections.observableList(listeHboxInvite);
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
        conteneurHoteInvi.setPadding(new Insets(40, 0, 70, 0));


        root.getChildren().addAll(titre, conteneurListeEtContinuer);

        Scene scene = new Scene(root, 1000, 700);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("UniCo - Retirer des élèves");
        stage.show();
    }

    private HBox listeCreator(Teenager t, boolean isHost){
        String name = t.getName();
        String forename = t.getForename();
        HBox ligneListeTeenager = new HBox(4);
        ligneListeTeenager.setAlignment(Pos.CENTER_LEFT);
        Label nomTeenager = new Label(name+" "+forename);
        nomTeenager.setFont(Font.font("Banhnschrift", FontWeight.NORMAL, null, 20));
        nomTeenager.setPadding(new Insets(0, 0, 0, 5));
        CheckBox checkbox = new CheckBox();
        checkbox.setPadding(new Insets(8));
        ligneListeTeenager.getChildren().addAll(checkbox, nomTeenager);

        ligneListeTeenager.setOnMouseClicked(e ->{
            if(e.getButton() == MouseButton.PRIMARY){
                checkbox.setSelected(!checkbox.isSelected());
                if(isHost) {
                    if(listeHoteRetire.contains(t)) listeHoteRetire.remove(t);
                    else listeHoteRetire.add(t);
                } else {
                    if(listeInviteRetire.contains(t)) listeInviteRetire.remove(t);
                    else listeInviteRetire.add(t);
                }
            }
        });
        return ligneListeTeenager;
    }

    private void retirerEleveDeLaListe(){
        for(Teenager t : listeHoteRetire){
            System.out.println("hote: " + t);
            Depot.platform.SORTED_HOSTS.remove(t);
        }
        for(Teenager t : listeInviteRetire){
            System.out.println(t);
            Depot.platform.SORTED_GUESTS.remove(t);
        }
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
