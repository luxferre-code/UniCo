package ullile.sae201.ihm;

import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ullile.sae201.CSVFile;
import ullile.sae201.Platform;
import ullile.sae201.exception.InvalidCSVException;

/**
 * Depot class
 * @author Elise Leroy
 */


public class Depot extends Application {
  public static String filename;
  public static Platform paltform;

  public static Stage s;

  public void start(Stage stage) {
    this.s = stage;
    VBox root;
    root = new VBox();
    VBox conteneurPrincipal = new VBox();

    Label titre = new Label("UniCo  | Dépôt du fichier");
    titre.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 34));
    titre.setPadding(new Insets(20, 0, 0, 20));
    
    Label deposer = new Label("Déposer votre fichier CSV");
    deposer.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 32));
    deposer.setPadding(new Insets(0,0,25,0));
 
    Button depot = new Button("Dépôt...");
    depot.setStyle("-fx-border-style: solid;"+
                   "-fx-border-color: lightgrey;"+
                   "-fx-border-radius: 40px;"+
                   "-fx-padding: 5 40;"+
                   "-fx-background-color: lightgrey;");
    depot.addEventHandler(ActionEvent.ACTION, new buttonDepotAction());

    Label marchePas = new Label("Ça ne marche pas ...");
    marchePas.setFont(Font.font("Bahnschrift", FontWeight.NORMAL, null, 15));
    marchePas.setPadding(new Insets(10,0,250,0));

    Button continuer = new Button("Continuer");
    continuer.setStyle("-fx-border-style: solid;"+
            "-fx-border-color: lightgreen;"+
            "-fx-background-radius: 10px;"+
            "-fx-border-radius: 10px;"+
            "-fx-padding: 10 30;"+
            "-fx-font-size: 16px;"+
            "-fx-background-color: lightgreen;");
    continuer.addEventHandler(ActionEvent.ACTION, new buttonContinuerAction());
    VBox boxContinuer = new VBox();

    boxContinuer.getChildren().add(continuer);
    boxContinuer.setAlignment(Pos.CENTER);

    conteneurPrincipal.getChildren().addAll(deposer, depot, marchePas);
    conteneurPrincipal.setAlignment(Pos.CENTER);
    conteneurPrincipal.setPadding(new Insets(150, 0, 0, 0));

    root.getChildren().addAll(titre, conteneurPrincipal, boxContinuer);


    Scene scene = new Scene(root, 1194, 834);
    stage.setScene(scene);
    stage.setTitle("UniCo - Dépôt du fichier");
    stage.show();
  }

  class buttonDepotAction implements EventHandler<ActionEvent> {
    public void handle(ActionEvent event){
      FileChooser fc = new FileChooser();
      File selectedFile = fc.showOpenDialog(null);
  
      if(selectedFile != null) {
        Depot.filename = selectedFile.getAbsolutePath();
      } else {
        System.out.println("Fichier invalide");
      }
    }
  }

  class buttonContinuerAction implements EventHandler<ActionEvent> {
    public void handle(ActionEvent event){
      if(Depot.filename == null || !Depot.filename.substring(Depot.filename.length()-3, Depot.filename.length()).equals("csv")){
       
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Une erreur s'est produite");
        alert.setHeaderText("Fichier incompatible !");
        alert.setContentText("Veuillez déposer un fichier de type .csv");
        alert.showAndWait();
        
      } else {
        try {
          Depot.paltform = CSVFile.read(Depot.filename, true);
          if(Depot.paltform != null) {
            Depot.s.close();
            ModifPonderation tmp = new ModifPonderation();
            tmp.start(new Stage());
          }
        } catch (InvalidCSVException e){
          Alert alert2 = new Alert(AlertType.WARNING);
          alert2.setTitle("Une erreur s'est produite");
          alert2.setHeaderText("Fichier incompatible !");
          alert2.setContentText("Le format du csv n'est pas le bon il doit être de la forme : FORENAME;NAME;COUNTRY;BIRTH_DATE;HOBBIES;GUEST_ANIMAL_ALLERGY;HOST_HAS_ANIMAL;GUEST_FOOD;HOST_FOOD;GENDER;PAIR_GENDER;HISTORY");
          alert2.showAndWait();
        } 
      }

      
  
      
    }
  }

  public static void main(String[] args) {
    Application.launch(args);
  }

}



