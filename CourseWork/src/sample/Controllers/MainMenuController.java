package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainMenuController {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Tab catalogTab;
    @FXML
    private Tab clientsTab;

    public void initialize() throws IOException {
        catalogTab.setStyle("-fx-background-color: rgba(59,186,241,0.3);");
        clientsTab.setStyle("-fx-background-color: rgba(59,186,241,0.3);");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../FXML_Files/Catalog.fxml"));
        catalogTab.setContent(loader.load());
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../FXML_Files/ClientsList.fxml"));
        clientsTab.setContent(loader.load());
    }
}
