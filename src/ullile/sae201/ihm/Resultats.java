package ullile.sae201.ihm;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Resultats extends Application{

    public void start(Stage stage){
        Label titre = new Label("UniCo  | Résultat des affectations");
        Label texteExplicatif = new Label("Il s'agit du calcul le plus optimal, les élèves incompatibles sont évités au plus possible.\nCliquer sur un couple pour voir les informations.");
        texteExplicatif.setFont(Font.font("Bahnschrift", FontWeight.NORMAL, null, 18));
        texteExplicatif.setPadding(new Insets(10, 0, 50, 23));
        titre.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 34));
        titre.setPadding(new Insets(20, 0, 0, 20));
        Button continuer = new Button("Retourner au dépôt");
        continuer.setStyle("-fx-border-style: solid;"+
                "-fx-border-color: darksalmon;"+
                "-fx-background-radius: 10px;"+
                "-fx-border-radius: 10px;"+
                "-fx-padding: 10 30;"+
                "-fx-font-size: 16px;"+
                "-fx-background-color: darksalmon;");

        Label titreListe = new Label("Hôte - Invité - Poids");
        titreListe.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 20));
        ListView<String> listeResultat = new ListView<>();
        listeResultat.setPrefWidth(400);

        VBox root = new VBox();
        VBox conteneurListeEtContinuer = new VBox();
        VBox vboxTitreListe = new VBox();

        vboxTitreListe.getChildren().addAll(titreListe, listeResultat);
        conteneurListeEtContinuer.getChildren().addAll(vboxTitreListe, continuer);
        root.getChildren().addAll(titre, texteExplicatif, conteneurListeEtContinuer);

        Scene scene = new Scene(root, 1194, 834);
        stage.setScene(scene);
        stage.setTitle("UniCo - Résultat des affectations");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
