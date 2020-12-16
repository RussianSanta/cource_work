package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Objects.Arrears;

import java.io.*;

public class CatalogController {
    public static ObservableList <Arrears> arrearsData = FXCollections.observableArrayList();
    @FXML
    private AnchorPane mainPane;
    @FXML
    private AnchorPane searchPane;
    @FXML
    private TextField searchFieldInput;
    @FXML
    private ComboBox<String> searchSelectInput;
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
    private Label surnameLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label patronymicLabel;
    @FXML
    private Label arrearsSumLabel;
    @FXML
    private Label arrearsOpenDateLabel;
    @FXML
    private Label arrearsCloseDateLabel;
    @FXML
    private Label arrearsStatusLabel;

    public void initialize() throws IOException {
        searchPane.setStyle("-fx-background-color: rgba(127,210,255,0.3);");
        mainPane.setStyle("-fx-background-color: rgba(180,225,255,0.3);");

        searchSelectInput.getItems().removeAll(searchSelectInput.getItems());
        searchSelectInput.getItems().addAll("По номеру займа", "По фамилии заемщика", "По логину заемщика", "По сумме займа", "По дате открытия", "По дате закрытия", "По статусу");
        searchSelectInput.getSelectionModel().select("По номеру займа");

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

    private void showArrearsDetails(Arrears arrears){
        if (arrears != null) {
            arrearsIDLabel.setText(arrears.getId());
            surnameLabel.setText(arrears.getSurname());
            nameLabel.setText(arrears.getName());
            patronymicLabel.setText(arrears.getPatronymic());
            arrearsSumLabel.setText(arrears.getSum());
            arrearsCloseDateLabel.setText(arrears.getCloseDate());
            arrearsOpenDateLabel.setText(arrears.getOpenDate());
            arrearsStatusLabel.setText(arrears.getStatus());
        }
        else {
            arrearsIDLabel.setText("");
            surnameLabel.setText("");
            nameLabel.setText("");
            patronymicLabel.setText("");
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
            arrearsData.add(arrears);
        }
    }

    @FXML
    private void addButtonAction() {
        Arrears arrears = new Arrears();

        boolean okClicked = showArrearsEditDialog(arrears);
        if (okClicked){
            arrearsData.add(arrears);
        }
    }
    @FXML
    private void rewriteButtonAction() throws IOException {
        Arrears arrears = catalogTable.getSelectionModel().getSelectedItem();
        if (arrears != null){
            boolean okClicked = showArrearsEditDialog(arrears);
            if (okClicked){
                showArrearsDetails(arrears);
                int selectedIndex = catalogTable.getSelectionModel().getSelectedIndex();
                arrearsData.set(selectedIndex,arrears);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(null);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Нет выбранного Займа");
            alert.setContentText("Выберите Займ в таблице");
            alert.showAndWait();
            }
        updateArrearsFile();
    }
    @FXML
    private void deleteButtonAction() throws IOException {
        int selectedIndex;
        selectedIndex = catalogTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            catalogTable.getItems().remove(selectedIndex);
            updateArrearsFile();
        }
    }

    public static void updateArrearsFile() throws IOException {
        File productsFile = new File("src/Data/arrears");
        PrintWriter delete = new PrintWriter(new FileWriter(productsFile,false),false);
        delete.flush();
        delete.close();
        PrintStream filePrintStream = new PrintStream(new FileOutputStream(productsFile, true));
        for (Arrears arrears: arrearsData) {
            filePrintStream.println(arrears.getId() + " " + arrears.getUserLogin() + " " + arrears.getSurname() + " "
            + arrears.getName() + " " + arrears.getPatronymic() + " " + arrears.getOpenDate() + " "
                    + arrears.getCloseDate() + " " + arrears.getSum() + " " + arrears.getStatus());
        }
    }

    public boolean showArrearsEditDialog(Arrears arrears){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ArrearsRewriteController.class.getResource("../FXML_Files/ArrearsRewrite.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Долг");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(null);
            Scene scene = new Scene(page);
            dialogStage.getIcons().add(new Image(this.getClass().getResource("../../Data/img/icon.png").toString()));
            dialogStage.setScene(scene);

            ArrearsRewriteController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProduct(arrears);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void searchButtonAction() {
        ObservableList <Arrears> arrearsSearchedData = FXCollections.observableArrayList();
        for (Arrears arrears:arrearsData) {
            switch (searchSelectInput.getSelectionModel().getSelectedItem()){
                case("По номеру займа"):
                    if (arrears.getId().equals(searchFieldInput.getText())) arrearsSearchedData.add(arrears);
                    break;
                case("По фамилии заемщика"):
                    if (arrears.getSurname().equals(searchFieldInput.getText())) arrearsSearchedData.add(arrears);
                    break;
                case("По логину заемщика"):
                    if (arrears.getUserLogin().equals(searchFieldInput.getText())) arrearsSearchedData.add(arrears);
                    break;
                case("По сумме займа"):
                    if (arrears.getSum().equals(searchFieldInput.getText())) arrearsSearchedData.add(arrears);
                    break;
                case("По дате открытия"):
                    if (arrears.getOpenDate().equals(searchFieldInput.getText())) arrearsSearchedData.add(arrears);
                    break;
                case("По дате закрытия"):
                    if (arrears.getCloseDate().equals(searchFieldInput.getText())) arrearsSearchedData.add(arrears);
                    break;
                case("По статусу"):
                    if (arrears.getStatus().equals(searchFieldInput.getText())) arrearsSearchedData.add(arrears);
                    break;
            }
        }
        catalogTable.setItems(arrearsSearchedData);
    }

    @FXML
    private void defaultButtonAction(){
        catalogTable.setItems(arrearsData);
        searchFieldInput.setText("");
    }
}
