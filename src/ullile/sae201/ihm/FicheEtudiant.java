package ullile.sae201.ihm;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import ullile.sae201.Teenager;

public class FicheEtudiant extends Application{

    public static Stage s;

    private Teenager host, guest;

    public FicheEtudiant(Teenager host, Teenager guest) {
        this.guest = guest;
        this.host = host;
    }

    public void start(Stage stage){
        s = stage;
        Label titre = new Label("UniCo  | Détails du couple");
        titre.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 34));
        titre.setPadding(new Insets(20, 0, 0, 20));
        Button fermer = new Button("Fermer la fenêtre");
        fermer.setStyle("-fx-border-style: solid;"+
                "-fx-border-color: lightgrey;"+
                "-fx-background-radius: 10px;"+
                "-fx-border-radius: 10px;"+
                "-fx-padding: 10 30;"+
                "-fx-font-size: 16px;"+
                "-fx-background-color: lightgrey;");
        fermer.setOnMouseClicked(e ->{
            if(e.getButton()==MouseButton.PRIMARY){
                FicheEtudiant.s.close();
            }
        });

        VBox root = new VBox(30);
        VBox conteneurPrincipal = new VBox(100);
        HBox conteneurAllHoteInvite = new HBox(20);
        VBox conteneurTitreHote = new VBox(5);
        VBox conteneurTitreInvite = new VBox(5);
        VBox detailsHote = new VBox(5);
        VBox detailsInvite = new VBox(5);
        VBox conteneurHote = new VBox(10);
        VBox conteneurInvite = new VBox(10);
        
        //Création du détails de l'hôte
        Label titreHote = new Label("Hôte | "+host.getCountry());
        Label nomHote = new Label(host.getName()+" "+host.getForename());
        Label ageHote = new Label(host.getAge()+" ans");
        conteneurTitreHote.getChildren().addAll(titreHote, nomHote, ageHote);
        HBox conteneurGenreHote = new HBox(10);
        HBox conteneurAnimalHote = new HBox(10);
        HBox conteneurAlimentationHote = new HBox(10);
        HBox conteneurHobbiesHote = new HBox(10);
        Label genreHote = new Label("Genre");
        Label animalHote = new Label("Animal");
        Label alimentationHote = new Label("Alimentation");
        Label hobbiesHote = new Label("Hobbies");
        Label genreHotePrecise = new Label(host.getGender());
        Label alimentationHotePrecise = new Label(host.foodToString(true));
        Label animalHotePrecise = new Label((host.getAnimalSpecification(true))+"");
        Label hobbiesHotePrecise = new Label(host.hobbiesToString());
        conteneurGenreHote.getChildren().addAll(genreHote, genreHotePrecise);
        conteneurAnimalHote.getChildren().addAll(animalHote, animalHotePrecise);
        conteneurAlimentationHote.getChildren().addAll(alimentationHote, alimentationHotePrecise);
        conteneurHobbiesHote.getChildren().addAll(hobbiesHote, hobbiesHotePrecise);
        detailsHote.getChildren().addAll(conteneurGenreHote, conteneurAnimalHote, conteneurAlimentationHote,  conteneurHobbiesHote);
        conteneurHote.getChildren().addAll(conteneurTitreHote, detailsHote);

        detailsHote.getStyleClass().add("color-palette");
        detailsHote.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        titreHote.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 20));
        nomHote.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 15));
        ageHote.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 15));
        genreHote.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 15));
        animalHote.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 15));
        alimentationHote.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 15));
        hobbiesHote.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 15));
        genreHotePrecise.setFont(Font.font("Bahnschrift", FontWeight.NORMAL, null, 15));
        animalHotePrecise.setFont(Font.font("Bahnschrift", FontWeight.NORMAL, null, 15));
        alimentationHotePrecise.setFont(Font.font("Bahnschrift", FontWeight.NORMAL, null, 15));
        hobbiesHotePrecise.setFont(Font.font("Bahnschrift", FontWeight.NORMAL, null, 15));
        conteneurGenreHote.setAlignment(Pos.CENTER_LEFT);
        conteneurAnimalHote.setAlignment(Pos.CENTER_LEFT);

        conteneurTitreHote.setAlignment(Pos.CENTER);
        detailsHote.setAlignment(Pos.CENTER);
        

        //Création du détails de l'invité
        Label titreInvite = new Label("Invité(e) | "+guest.getCountry());
        Label nomInvite = new Label(guest.getName()+" "+guest.getForename());
        Label ageInvite = new Label(guest.getAge()+" ans");
        conteneurTitreInvite.getChildren().addAll(titreInvite, nomInvite, ageInvite);
        HBox coteneurGenreInvite = new HBox(10);
        HBox conteneurAllergyInvite = new HBox(10);
        HBox conteneurAlimentationInvite = new HBox(10);
        HBox conteneurHobbiesInvite = new HBox(10);
        Label genreInvite = new Label("Genre");
        Label allergyInvite = new Label("Allergies");
        Label alimentationInvite = new Label("Alimentation");
        Label hobbiesInvite = new Label("Hobbies");
        Label genreInvitePrecise = new Label(guest.getGender());
        Label allergyInvitePrecise = new Label(guest.getAnimalSpecification(false)+"");
        Label alimentationInvitePrecise = new Label(guest.foodToString(false));
        Label hobbiesInvitePrecise = new Label(guest.hobbiesToString());
        coteneurGenreInvite.getChildren().addAll(genreInvite, genreInvitePrecise);
        conteneurAllergyInvite.getChildren().addAll(allergyInvite, allergyInvitePrecise);
        conteneurAlimentationInvite.getChildren().addAll(alimentationInvite, alimentationInvitePrecise);
        conteneurHobbiesInvite.getChildren().addAll(hobbiesInvite, hobbiesInvitePrecise);
        detailsInvite.getChildren().addAll(coteneurGenreInvite, conteneurAllergyInvite, conteneurAlimentationInvite, conteneurHobbiesInvite);
        conteneurInvite.getChildren().addAll(conteneurTitreInvite, detailsInvite);

        detailsInvite.getStyleClass().add("color-palette");
        detailsInvite.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        titreInvite.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 20));
        nomInvite.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 15));
        ageInvite.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 15));
        genreInvite.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 15));
        allergyInvite.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 15));   
        alimentationInvite.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 15));
        hobbiesInvite.setFont(Font.font("Bahnschrift", FontWeight.BOLD, null, 15));
        genreInvitePrecise.setFont(Font.font("Bahnschrift", FontWeight.NORMAL, null, 15));
        allergyInvitePrecise.setFont(Font.font("Bahnschrift", FontWeight.NORMAL, null, 15));
        alimentationInvitePrecise.setFont(Font.font("Bahnschrift", FontWeight.NORMAL, null, 15));
        hobbiesInvitePrecise.setFont(Font.font("Bahnschrift", FontWeight.NORMAL, null, 15));
        coteneurGenreInvite.setAlignment(Pos.CENTER_LEFT);
        conteneurAllergyInvite.setAlignment(Pos.CENTER_LEFT);



        conteneurTitreInvite.setAlignment(Pos.CENTER);
        detailsInvite.setAlignment(Pos.CENTER);

        conteneurAllHoteInvite.getChildren().addAll(conteneurHote, conteneurInvite);
        conteneurPrincipal.getChildren().addAll(conteneurAllHoteInvite, fermer);

        conteneurPrincipal.setAlignment(Pos.CENTER);
        conteneurAllHoteInvite.setAlignment(Pos.CENTER);

        root.getChildren().addAll(titre, conteneurPrincipal);

        Scene scene = new Scene(root, 800, 550);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("UniCo - Détails du couple");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
