package ullile.sae201.ihm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    public static Map<ArrayList<Teenager>, ArrayList<Teenager>> mapCouple;
    ComboBox<String> listeInvite = new ComboBox<>();
    public static Stage s;


    
    public void start(Stage stage){
        ForcerAffectation.s = stage;
       /*  class ComboBox implements ListChangeListener<String>{

            public void onChanged(Change<? extends String> report){
                
                listeNomCompletHote = getNomCompletInvite();
                listeInvite.getItems().clear();
                listeInvite.getItems().addAll(listeNomCompletInvite);

            }
        } */

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
        listeViewCoupleForce.setMaxWidth(200);
        listeViewCoupleForce.setMaxHeight(100);

        ComboBox<String> listeHote = new ComboBox<>();
        listeHote.setPrefWidth(200);
        listeNomCompletHote.addAll(getNomCompletHote());
        System.out.println(listeNomCompletHote);
        ObservableList<String> listeObsHote = FXCollections.observableArrayList(listeNomCompletHote);
        //first row of combobox must be empty
        listeObsHote.add(0, "");
        listeHote.setItems(listeObsHote);

        listeInvite.setMaxWidth(200);
        listeNomCompletInvite.addAll(getNomCompletInvite());
        ObservableList<String> listeObsinvite = FXCollections.observableArrayList(listeNomCompletInvite);
        listeObsinvite.add(0, "");
        listeInvite.setItems(listeObsinvite);

        ajouter.setOnMouseClicked(e ->{
            if(e.getButton() == MouseButton.PRIMARY){
                if(listeHote.getValue() != "" && listeInvite.getValue() != ""){
                    listeViewCoupleForce.getItems().add(listeHote.getValue()+" - "+listeInvite.getValue());
                    hoteEnCoupleForce.add(listeHote.getValue());
                    inviteEnCoupleForce.add(listeInvite.getValue());
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

        root.getChildren().addAll(titre, conteneurListeEtBouton);

        continuer.setOnMouseClicked(e ->{
            if(e.getButton()==MouseButton.PRIMARY){
                mapCouple = new HashMap<>();
                for(String s : listeViewCoupleForce.getItems()){
                    mapCouple.put(getTeenagerHote(), getTeenagerInvite());
                }
                ForcerAffectation.s.close();
                Resultats tmp = new Resultats();
                tmp.start(new Stage());
            }
        });

        Scene scene = new Scene(root, 1000, 700);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("UniCo - Fixer des affectations");
        stage.show();


    }

    private ArrayList<String> getNomCompletHote(){
        ArrayList<String> retour = new ArrayList<String>();
        for(Teenager t : Depot.platform.SORTED_HOSTS){
            listeNomCompletHote.add(t.getName()+" "+t.getForename());
        }
        return retour;
    }

    private ArrayList<String> getNomCompletInvite(){
        ArrayList<String> retour = new ArrayList<String>();
        for(Teenager t : Depot.platform.SORTED_GUESTS){
            listeNomCompletInvite.add(t.getName()+" "+t.getForename());
        }
        return retour;
    }

    private ArrayList<Teenager> getTeenagerHote(){
        ArrayList<Teenager> retour = new ArrayList<Teenager>();
        String name ="";
        String forename="";
        for(int i=0; i<hoteEnCoupleForce.get(0).length(); i++){
            while(hoteEnCoupleForce.get(0).charAt(i) != ' '){
                name += hoteEnCoupleForce.get(0).substring(0, i);
            }
            forename = hoteEnCoupleForce.get(0).substring(i+1, hoteEnCoupleForce.get(0).length());
        }
        Depot.platform.SORTED_HOSTS.remove(Depot.platform.getTeenagerHostByName(name, forename));
        retour.add(Depot.platform.getTeenagerHostByName(name, forename));
        return retour;
    }

    private ArrayList<Teenager> getTeenagerInvite(){
        ArrayList<Teenager> retour = new ArrayList<Teenager>();
        String name="";
        String forename="";
        for(int i=0; i<inviteEnCoupleForce.get(0).length(); i++){
            while(inviteEnCoupleForce.get(0).charAt(i) != ' '){
                name += inviteEnCoupleForce.get(0).substring(0, i);
            }
            forename = inviteEnCoupleForce.get(0).substring(i+1, inviteEnCoupleForce.get(0).length());
        }
        Depot.platform.SORTED_GUESTS.remove(Depot.platform.getTeenagerGuestByName(name, forename));
        retour.add(Depot.platform.getTeenagerGuestByName(name, forename));
        return retour;
    }




    public static void main(String[] args) {
        Application.launch(args);
    }
}
