package ullile.sae201.ihm;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ModifPonderation extends Application {
    public void start(Stage stage) {

        VBox root = new VBox();
        VBox conteneurPrincipal = new VBox();

        Label titre = new Label("UniCo  | Modification des pondérations");
        titre.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 34));
        Button continuer = new Button("Continuer");
        continuer.setStyle("-fx-border-style: solid;"+
                "-fx-border-color: lightgreen;"+
                "-fx-background-radius: 50px;"+
                "-fx-border-radius: 50px;"+
                "-fx-padding: 15 30;"+
                "-fx-background-color: lightgreen;");
        
        String[] labelTab = new String[7];
        labelTab[0] = "Malus d’incompatibilité alimentaire";
        labelTab[1]= "Bonus de hobby similaire";
        labelTab[2] = "Malus d’allergie aux animaux incompatible";
        labelTab[3] = "Valeur de compatibilité de l’historique 1";
        labelTab[4] = "Valeur de compatibilité de l’historique 2";
        labelTab[5] = "Bonus de compatibilité du genre";
        labelTab[6] = "Bonus de compatibilité du même âge";

        String[] noticeTab = new String[3];
        noticeTab[0] = "/!\\ Attention à la modification de cette valeur";
        noticeTab[1] = "Dans le cas où les deux adolescents souhaiteraient être ensemble ou non";
        noticeTab[2] = "Dans le cas ou seul un des deux adolescents se serait prononcé sur le souhait ou non d’être avec le même hôte/invité";
        
        
        for(int i=0; i<7; i++){
            HBox ligne = new HBox();
            Label label = new Label(labelTab[i]);
            //setStyle label
            Button moins = new Button(" - ");
            Button plus = new Button(" + ");
            TextField valeur = new TextField();
            Button retablir = new Button("Rétablir");
            ligne.getChildren().addAll(label, moins, valeur, plus, retablir);
            conteneurPrincipal.getChildren().add(ligne);
            if(i==2 || i==3 || i==4){
                Label notice = new Label(noticeTab[i-2]);
                //setStyle notice et if i == 2 font color red
                conteneurPrincipal.getChildren().add(notice);
            } 
            
        }

        root.getChildren().addAll(titre, conteneurPrincipal, continuer);

        Scene scene = new Scene(root, 1194, 834);
        stage.setScene(scene);
        stage.setTitle("UniCo - Modification des pondérations");
        stage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
