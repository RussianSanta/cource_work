package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import sample.Main;
import sample.Objects.Arrears;
import sample.Objects.User;

public class ClientsListController {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Pane leftPane;
    @FXML
    private Label surnameLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label patronymicLabel;
    @FXML
    private Label loginLabel;
    @FXML
    private Label sumLabel;
    @FXML
    private Label openedCount;
    @FXML
    private Label closedCount;
    @FXML
    private Label soldCount;
    @FXML
    private TableView<User> clientTable;
        @FXML
        private TableColumn<User,String> clientName;
        @FXML
        private TableColumn<User,String> clientLogin;
    @FXML
    private TextField clientSearchField;

    public void initialize(){
        mainPane.setStyle("-fx-background-color: rgba(180,225,255,0.3);");
        leftPane.setStyle("-fx-background-color: rgba(127,210,255,0.3);");

        clientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clientLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        clientTable.setItems(Main.usersList);

        clientTable.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> showUserDetails(newValue)
        );
    }

    private void showUserDetails(User user) {
        if (user != null){
            int sum = 0;
            int opened = 0;
            int closed = 0;
            int sold = 0;
            for (Arrears arrears:CatalogController.arrearsData) {
                if (arrears.getUserLogin().equals(user.getLogin())){
                    sum += Integer.parseInt(arrears.getSum());
                    switch (arrears.getStatus()) {
                        case "Открыт":
                            opened++;
                            break;
                        case "Закрыт":
                            closed++;
                            break;
                        case "Продан":
                            sold++;
                            break;
                    }
                }
            }
            String [] tokens = user.getName().split(" ");
            surnameLabel.setText(tokens[0]);
            nameLabel.setText(tokens[1]);
            patronymicLabel.setText(tokens[2]);
            loginLabel.setText(user.getLogin());
            sumLabel.setText(Integer.toString(sum));
            openedCount.setText(Integer.toString(opened));
            closedCount.setText(Integer.toString(closed));
            soldCount.setText(Integer.toString(sold));
        } else {
            surnameLabel.setText("");
            nameLabel.setText("");
            patronymicLabel.setText("");
            loginLabel.setText("");
            sumLabel.setText("");
            openedCount.setText("");
            closedCount.setText("");
            soldCount.setText("");
        }
    }

    @FXML
    private void clientSearchButton() {
        ObservableList<User> usersSearchedData = FXCollections.observableArrayList();
        for (User user:Main.usersList) {
            String [] tokens = user.getName().split(" ");
            if (user.getLogin().equals(clientSearchField.getText()) || tokens[0].equals(clientSearchField.getText())
            || tokens[1].equals(clientSearchField.getText()) || tokens[2].equals(clientSearchField.getText()))
                usersSearchedData.add(user);
        }
        clientTable.setItems(usersSearchedData);
    }
    @FXML
    private void defaultButtonAction(){
        clientTable.setItems(Main.usersList);
        clientSearchField.setText("");
    }
}