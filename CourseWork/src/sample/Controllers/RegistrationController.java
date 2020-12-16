package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Main;
import sample.Objects.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class RegistrationController {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private TextField regName;
    @FXML
    private TextField regLogin;
    @FXML
    private TextField regPassword;

    public void initialize(){
        mainPane.setStyle("-fx-background-color: rgba(180,225,255,0.3);");
    }

    public void acceptRegistration() throws IOException {
        for (User user : Main.usersList) {
            if (user.getLogin().equals(regLogin.getText())){
                regLogin.setStyle("-fx-border-color: red");
                return;
            }
        }
        regLogin.setStyle("-fx-border-color: none");
        File usersFile = new File("src/Data/users");
        PrintStream filePrintStream = new PrintStream(new FileOutputStream(usersFile, true));
        filePrintStream.println(regLogin.getText() + "#" + regPassword.getText() + "#" + regName.getText() + "#0");
        System.out.println(regLogin.getText() + "#" + regPassword.getText() + "#" + regName.getText()+ "#0");
        User user = new User();
        user.setName(regName.getText());
        user.setLogin(regLogin.getText());
        user.setPassword(regPassword.getText());
        Main.usersList.add(user);

        Stage stage = (Stage)regName.getScene().getWindow();
        stage.close();
    }
}
