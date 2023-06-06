module ullile.sae201.ihm {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens ullile.sae201.ihm to javafx.fxml;
    exports ullile.sae201.ihm;
}