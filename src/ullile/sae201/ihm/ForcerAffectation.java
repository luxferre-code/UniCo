package ullile.sae201.ihm;

import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    String[] listeNomCompletHote;
    String[] listeNomCompletInvite;

    ListView<String> listeInvite = new ListView<>();
    public static Stage s;

    public void start(Stage stage){
        ForcerAffectation.s = stage;
        class ListOnClickListener implements ListChangeListener<String>{

            public void onChanged(Change<? extends String> report){
                
                listeNomCompletHote = getNomCompletInvite();
                listeInvite.getItems().clear();
                listeInvite.getItems().addAll(listeNomCompletInvite);

            }
        }

        VBox root = new VBox(30);
        VBox conteneurListeEtBouton = new VBox(75);
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
        continuer.setOnMouseClicked(e ->{
            if(e.getButton()==MouseButton.PRIMARY){
                ForcerAffectation.s.close();
                Resultats tmp = new Resultats();
                tmp.start(new Stage());
            }
        });

        HBox conteneurHoteInvi = new HBox(40);
        
        VBox conteneurHote = new VBox(10);
        Label titreHote = new Label("Hôtes");
        titreHote.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 25));
        ListView<String> listeHote = new ListView<>();
        listeNomCompletHote = new String[Depot.platform.SORTED_HOSTS.size()];
        listeNomCompletHote = getNomCompletHote();
        for(int i=0; i<listeNomCompletHote.length; i++){
            listeHote.getItems().add(listeNomCompletHote[i]);
        }
        conteneurHote.getChildren().addAll(titreHote, listeHote);

        listeNomCompletInvite = new String[Depot.platform.SORTED_GUESTS.size()];
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

        Scene scene = new Scene(root, 1000, 700);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("UniCo - Fixer des affectations");
        stage.show();


    }

    private String[] getNomCompletHote(){
        int i = 0;

        for(Teenager t : Depot.platform.SORTED_HOSTS){
            listeNomCompletHote[i] = t.getName()+" "+t.getForename();
            i++;
        }

        return listeNomCompletHote;
    }

    private String[] getNomCompletInvite(){
        int i = 0;

        for(Teenager t : Depot.platform.SORTED_GUESTS){
            listeNomCompletInvite[i] = t.getName()+" "+t.getForename();
            i++;
        }

        return listeNomCompletInvite;
    }




    public static void main(String[] args) {
        Application.launch(args);
    }
}
