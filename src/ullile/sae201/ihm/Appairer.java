package ullile.sae201.ihm;

import java.io.File;
import java.text.ParseException;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ullile.sae201.CSVFile;
import ullile.sae201.Platform;
import ullile.sae201.exception.InvalidCSVException;


public class Appairer extends Application {
  public static String filename;
  public static Platform paltform;

  public void start(Stage stage) {
    //faire en GridPane
    BorderPane root = new BorderPane();
    root.setLeft(getMenu());
    root.setCenter(getPageCSV());

    Scene scene = new Scene(root, 1000, 600);
    stage.setScene(scene);
    stage.setTitle("Appairer");
    stage.show();
  }

  private VBox getMenu() {
    VBox menu = new VBox();
    VBox vBoxButton = new VBox(4);
    vBoxButton.setPadding(new Insets(25, 0, 0, 0));
    menu.setPadding(new Insets(15, 20, 0, 20));
    menu.setStyle("-fx-border-style : hidden solid hidden hidden;"+
                   "-fx-border-color: lightgrey;");

    Label logo = new Label("UniCo");
    logo.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 24));
    

    Button liste = new Button("Liste");
    liste.setStyle("-fx-background-color: transparent");
    liste.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 16));
    vBoxButton.getChildren().add(liste);

    Button appairer = new Button("Appairer");
    appairer.setStyle("-fx-background-color: transparent");
    appairer.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 16));
    vBoxButton.getChildren().add(appairer);

    menu.getChildren().addAll(logo, vBoxButton);

    return menu;
  }

  private HBox getPageCSV() {
    HBox centerCSV = new HBox();
    VBox titreCSV = new VBox();
    VBox menuDepotCSV = new VBox();
    


    centerCSV.setStyle("-fx-border-style : hidden solid hidden hidden;"+
    "-fx-border-color: red;");

    titreCSV.setPadding(new Insets(15, 20, 20, 20));
    titreCSV.setPrefWidth(centerCSV.getPrefWidth());
    menuDepotCSV.setStyle("-fx-border-style : hidden solid hidden solid;"+
    "-fx-border-color: lightgrey;");
    menuDepotCSV.setPrefWidth(centerCSV.getPrefWidth());
    menuDepotCSV.setAlignment(Pos.CENTER_LEFT);

    Label appairer = new Label("Appairer");
    appairer.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 20));
    titreCSV.getChildren().add(appairer);

    Label deposer = new Label("Déposer votre fichier CSV");
    deposer.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 15));
    deposer.setPadding(new Insets(0,0,25,0));
    menuDepotCSV.getChildren().add(deposer);

    Button depot = new Button("Dépôt");
    depot.setStyle("-fx-border-style: solid;"+
                   "-fx-border-color: lightgrey;"+
                   "-fx-border-radius: 40px;"+
                   "-fx-padding: 5 40;"+
                   "-fx-background-color: lightgrey;");
    depot.addEventHandler(ActionEvent.ACTION, new buttonDepotAction());
    menuDepotCSV.getChildren().add(depot);

    Label marchePas = new Label("Ça ne marche pas ...");
    marchePas.setFont(Font.font("Bahnschrift", FontWeight.NORMAL, null, 10));
    marchePas.setPadding(new Insets(10,0,200,0));
    menuDepotCSV.getChildren().add(marchePas);

    Button continuer = new Button("Continuer");
    continuer.setStyle("-fx-border-style: solid;"+
                   "-fx-border-color: lightgreen;"+
                   "-fx-background-radius: 50px;"+
                   "-fx-border-radius: 50px;"+
                   "-fx-padding: 15 30;"+
                   "-fx-background-color: lightgreen;");
    continuer.addEventHandler(ActionEvent.ACTION, new buttonContinuerAction());
    menuDepotCSV.getChildren().add(continuer);

    centerCSV.getChildren().addAll(titreCSV, menuDepotCSV);
    return centerCSV;
  }

  /*
    public static boolean exportData(Platform p, String nameFile) {

        File f = new File(nameFile);
        if (f.exists()) {
            f.delete();
        }

        try (BufferedWriter br = new BufferedWriter(new FileWriter(nameFile))) {
            br.write("FORENAME;NAME;COUNTRY;BIRTH_DATE;HOBBIES;GUEST_ANIMAL_ALLERGY;HOST_HAS_ANIMAL;GUEST_FOOD;HOST_FOOD;GENDER;PAIR_GENDER;HISTORY\n");
            for (Teenager t : p.getTeenagers()) {
                br.write(CSVFile.exportLineTeenager(t));
                br.newLine();
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }*/
  

  class buttonDepotAction implements EventHandler<ActionEvent> {
    public void handle(ActionEvent event){
      FileChooser fc = new FileChooser();
      File selectedFile = fc.showOpenDialog(null);
  
      if(selectedFile != null) {
        Appairer.filename = selectedFile.getAbsolutePath();
      } else {
        System.out.println("Fichier invalide");
      }
    }
  }

  class buttonContinuerAction implements EventHandler<ActionEvent> {
    public void handle(ActionEvent event){
      System.out.println(Appairer.filename);
      System.out.println(Appairer.filename.substring(Appairer.filename.length()-3, Appairer.filename.length()));
      if(Appairer.filename == null || !Appairer.filename.substring(Appairer.filename.length()-3, Appairer.filename.length()).equals("csv")){
       
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Une erreur s'est produite");
        alert.setHeaderText("Fichier incompatible !");
        alert.setContentText("Veuillez déposer un fichier de type .csv");
        alert.showAndWait();
        
      } else {
        try {
          Appairer.paltform = CSVFile.read(Appairer.filename, true);
        } catch (InvalidCSVException e){
          Alert alert2 = new Alert(AlertType.WARNING);
          alert2.setTitle("Une erreur s'est produite");
          alert2.setHeaderText("Fichier incompatible !");
          alert2.setContentText("Le format du csv n'est pas le bon il doit être de la forme : FORENAME;NAME;COUNTRY;BIRTH_DATE;HOBBIES;GUEST_ANIMAL_ALLERGY;HOST_HAS_ANIMAL;GUEST_FOOD;HOST_FOOD;GENDER;PAIR_GENDER;HISTORY");
          alert2.showAndWait();
        } catch (ParseException e) {
          Alert alert3 = new Alert(AlertType.WARNING);
          alert3.setTitle("Une erreur s'est produite");
          alert3.setHeaderText("Fichier incompatible !");
          alert3.setContentText("Le date doit être au format dd/MM/yyy");
          alert3.showAndWait();
        }
        //Appairer.filename = "";
      }
  
      
    }
  }

  public static void main(String[] args) {
    Application.launch(args);
  }

}

