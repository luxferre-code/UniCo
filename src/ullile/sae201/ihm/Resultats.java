package ullile.sae201.ihm;


import java.util.ArrayList;

import fr.ulille.but.sae2_02.graphes.Arete;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import ullile.sae201.Teenager;
import ullile.sae201.graphe.AffectationUtil;

/**
 * Resultats class
 * @author Elise Leroy
 */

public class Resultats extends Application{
    static Stage s;
    private GrapheNonOrienteValue<Teenager> graphe;
    private CalculAffectation<Teenager> affectation;
    ArrayList<Arete<Teenager>> appariment = new ArrayList<Arete<Teenager>>(); 

    public void start(Stage stage){
        Label titre = new Label("UniCo  | Résultat des affectations");
        Label texteExplicatif = new Label("Il s'agit du calcul le plus optimal, les élèves incompatibles sont évités au plus possible.\nCliquer sur un couple pour voir les informations.");
        texteExplicatif.setFont(Font.font("Bahnschrift", FontWeight.NORMAL, null, 18));
        texteExplicatif.setPadding(new Insets(10, 0, 50, 23));
        titre.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 34));
        titre.setPadding(new Insets(20, 0, 0, 20));
        Button boutonDepot = new Button("Retour au dépôt");
        Button continuer = new Button("Exporter le fichier");
        continuer.setStyle("-fx-border-style: solid;"+
                "-fx-border-color: lightgreen;"+
                "-fx-background-radius: 10px;"+
                "-fx-border-radius: 10px;"+
                "-fx-padding: 10 30;"+
                "-fx-font-size: 16px;"+
                "-fx-background-color: lignthgreen;");

        boutonDepot.setStyle("-fx-border-style: solid;"+
                "-fx-border-color: darksalmon;"+
                "-fx-background-radius: 10px;"+
                "-fx-border-radius: 10px;"+
                "-fx-padding: 10 30;"+
                "-fx-font-size: 16px;"+
                "-fx-background-color: darksalmon;");
        boutonDepot.setOnMouseClicked(e ->{
            if(e.getButton()==MouseButton.PRIMARY){
                Resultats.s.close();
                Depot tmp = new Depot();
                tmp.start(new Stage());
            }
        });

        Label titreListe = new Label("Hôte - Invité - Poids");
        titreListe.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 20));

        ListView<String> listeResultat = new ListView<>();
        listeResultat.setMaxWidth(400);
        ArrayList<Teenager> listeHost = new ArrayList<Teenager>();
        ArrayList<Teenager> listeGuest = new ArrayList<Teenager>();
        listeHost.addAll(Depot.platform.SORTED_HOSTS);
        listeGuest.addAll(Depot.platform.SORTED_GUESTS);

        //for each mapCouple in mapCouple creerGrapheTeenagerV2 et calculerAffectation et ajouter dans ligneParLigne
        ArrayList<String[]> ligneParLigneForce = new ArrayList<String[]>();
        for(var entry : ForcerAffectation.mapCouple.entrySet()){
            ArrayList<Teenager> host = entry.getKey();
            ArrayList<Teenager> guest = entry.getValue();
            graphe = AffectationUtil.creerGrapheTeenagerV2(host, guest);
            affectation = new CalculAffectation<Teenager>(graphe,listeHost,listeGuest);
            appariment.addAll(affectation.calculerAffectation());
            ligneParLigneForce.add(AffectationUtil.tableauAfficherAppariementIHM(appariment));
            appariment.clear();
        }
         
        graphe = AffectationUtil.creerGrapheTeenagerV2(listeHost, listeGuest);
        affectation = new CalculAffectation<Teenager>(graphe,listeHost,listeGuest);
        appariment.addAll(affectation.calculerAffectation());
        String[] ligneParLigneReste = AffectationUtil.tableauAfficherAppariementIHM(appariment);

        for(int i = 0; i < ligneParLigneReste.length; i++){
            listeResultat.getItems().add(ligneParLigneReste[i]);
        }

        listeResultat.setOnMouseClicked(e ->{
            if(e.getButton()==MouseButton.PRIMARY){
                
            }
        });

        VBox root = new VBox();
        VBox conteneurListeEtContinuer = new VBox();
        VBox vboxTitreListe = new VBox();

        vboxTitreListe.getChildren().addAll(titreListe, listeResultat);
        vboxTitreListe.setAlignment(Pos.CENTER);
        vboxTitreListe.setPadding(new Insets(0, 0, 90, 0));
        conteneurListeEtContinuer.getChildren().addAll(vboxTitreListe, continuer);
        conteneurListeEtContinuer.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(titre, texteExplicatif, conteneurListeEtContinuer);

        Scene scene = new Scene(root, 1000, 700);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("UniCo - Résultat des affectations");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
