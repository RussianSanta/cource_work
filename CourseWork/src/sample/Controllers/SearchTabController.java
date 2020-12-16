package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import sample.Main;
import sample.Objects.Arrears;

import java.io.*;

public class SearchTabController {
    public static ObservableList <Arrears> arrearsData = FXCollections.observableArrayList();
    @FXML
    private AnchorPane mainPane;
    @FXML
    private TableView <Arrears> catalogTable;
        @FXML
        private TableColumn <Arrears, String> arrearsID;
        @FXML
        private TableColumn <Arrears, String> arrearsSum;
        @FXML
        private TableColumn <Arrears, String> arrearsOpenDate;
        @FXML
        private TableColumn <Arrears, String> arrearsCloseDate;
        @FXML
        private TableColumn <Arrears,String> arrearsStatus;

    @FXML
    private Label arrearsIDLabel;
    @FXML
    private Label arrearsSumLabel;
    @FXML
    private Label arrearsOpenDateLabel;
    @FXML
    private Label arrearsCloseDateLabel;
    @FXML
    private Label arrearsStatusLabel;

    public void initialize() throws IOException {
        mainPane.setStyle("-fx-background-color: rgba(180,225,255,0.3);");

        loadArrearsList();
        arrearsID.setCellValueFactory(new PropertyValueFactory<>("id"));
        arrearsSum.setCellValueFactory(new PropertyValueFactory<>("sum"));
        arrearsOpenDate.setCellValueFactory(new PropertyValueFactory<>("openDate"));
        arrearsCloseDate.setCellValueFactory(new PropertyValueFactory<>("closeDate"));
        arrearsStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        catalogTable.setItems(arrearsData);

        showArrearsDetails(null);

        catalogTable.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> showArrearsDetails(newValue)
        );
    }

    private void showArrearsDetails(Arrears product){
        if (product != null) {
            arrearsIDLabel.setText(product.getId());
            arrearsSumLabel.setText(product.getSum());
            arrearsCloseDateLabel.setText(product.getCloseDate());
            arrearsOpenDateLabel.setText(product.getOpenDate());
            arrearsStatusLabel.setText(product.getStatus());
        }
        else {
            arrearsIDLabel.setText("");
            arrearsSumLabel.setText("");
            arrearsCloseDateLabel.setText("");
            arrearsOpenDateLabel.setText("");
        }
    }

    private void loadArrearsList() throws IOException {
        arrearsData = FXCollections.observableArrayList();
        File productsFile = new File("src/Data/arrears");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(productsFile)));
        String line;
        while( (line = br.readLine())!= null ){
            String [] tokens = line.split(" ");
            Arrears arrears = new Arrears(tokens[0],tokens[1],tokens[2],tokens[3],tokens[4],tokens[5],tokens[6],tokens[7],tokens[8]);
            if (arrears.getUserLogin().equals(Main.activeUser.getLogin())) arrearsData.add(arrears);
        }
    }
}
