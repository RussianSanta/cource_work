package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Main;
import sample.Objects.User;

import java.io.IOException;

public class EnterController {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    @FXML
    private Hyperlink regLink;
    @FXML
    private Button button1;

    public void initialize(){
        mainPane.setStyle("-fx-background-color: rgba(180,225,255,0.3);");
        regLink.setStyle("-fx-text-fill: #000000;");
    }

    public void enterButtonAction() throws IOException {
        if (checkProfile(textField1.getText(),textField2.getText()) == 0) {
            openSearchTab();
        } else
        if (checkProfile(textField1.getText(),textField2.getText()) == 1) {
            openMainMenu();
        } else {
            textField2.setText("");
            textField1.setStyle("-fx-border-color: red");
            textField2.setStyle("-fx-border-color: red");
        }
    }

    public void registration() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Files/Registration.fxml"));
        stage.setTitle("Регистрация");
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image(this.getClass().getResource("../../Data/img/icon.png").toString()));
        stage.setResizable(false);
        stage.show();
    }

    public void clearButtonAction() {
        textField1.setText("");
        textField2.setText("");
        textField1.setStyle("-fx-border-color: none");
        textField2.setStyle("-fx-border-color: none");
    }

    private void openMainMenu() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Files/MainMenu.fxml"));
        stage.setTitle("Главное меню");
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image(this.getClass().getResource("../../Data/img/icon.png").toString()));
        stage.setResizable(false);
        stage.show();
        Stage stage1 = (Stage) button1.getScene().getWindow();
        stage1.close();
    }

    private void openSearchTab() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Files/SearchTab.fxml"));
        stage.setTitle("Ваши задолженности");
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image(this.getClass().getResource("../../Data/img/icon.png").toString()));
        stage.setResizable(false);
        stage.show();
        Stage stage1 = (Stage) button1.getScene().getWindow();
        stage1.close();
    }

    private int checkProfile(String login, String password){
        for (User user : Main.usersList) {
            boolean isTrue = user.getLogin().equals(login) && user.getPassword().equals(password);
            if (isTrue) {
                Main.activeUser = user;
                return Integer.parseInt(user.getAccessProfile());
            }
        }
    return -1;
    }

    @FXML
    private void handleKeyPressed(KeyEvent ke) throws IOException {
        if (ke.getCode().equals(KeyCode.ENTER)) {
            enterButtonAction();
        }
    }
}

