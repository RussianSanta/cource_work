package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Objects.Arrears;

import java.io.FileNotFoundException;

public class ArrearsRewriteController {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private TextField idInput;
    @FXML
    private TextField nameInput;
    @FXML
    private TextField sumInput;
    @FXML
    private TextField loginInput;
    @FXML
    private TextField openDateInput;
    @FXML
    private TextField closeDateInput;
    @FXML
    private TextField itemNameInput;
    @FXML
    private TextField itemSumInput;
    @FXML
    private ComboBox<String> statusInput;

    private Stage dialogStage;
    private Arrears arrears;
    private boolean okClicked = false;

    public void initialize() {
        statusInput.getItems().removeAll(statusInput.getItems());
        statusInput.getItems().addAll("Открыт", "Закрыт", "Продан");
        statusInput.getSelectionModel().select("Открыт");
        mainPane.setStyle("-fx-background-color: rgba(180,225,255,0.3);");
    }

    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    public void setProduct(Arrears arrears){
        this.arrears = arrears;
        idInput.setText(arrears.getId());
        if (!arrears.getSurname().equals(""))
            nameInput.setText(arrears.getSurname() + " " + arrears.getName() + " " + arrears.getPatronymic());
        loginInput.setText(arrears.getUserLogin());
        sumInput.setText(arrears.getSum());
        openDateInput.setText(arrears.getOpenDate());
        closeDateInput.setText(arrears.getCloseDate());
        itemNameInput.setText(arrears.getItemName());
        itemSumInput.setText(arrears.getItemSum());
        statusInput.getSelectionModel().select(arrears.getStatus());
    }

    public boolean isOkClicked(){
        return okClicked;
    }

    private boolean isInputValid(){
        boolean isValid = true;
        if (idInput.getText() == null || idInput.getText().length() == 0) {
            isValid = false;
            idInput.setStyle("-fx-border-color: red");
        }
        if (nameInput.getText() == null || nameInput.getText().length() <= 2) {
            isValid = false;
            nameInput.setStyle("-fx-border-color: red");
        }
        if (loginInput.getText() == null || loginInput.getText().length() == 0) {
            isValid = false;
            loginInput.setStyle("-fx-border-color: red");
        }
        if (sumInput.getText() == null || sumInput.getText().length() == 0) {
            isValid = false;
            sumInput.setStyle("-fx-border-color: red");
        }
        if (openDateInput.getText() == null || openDateInput.getText().length() == 0) {
            isValid = false;
            openDateInput.setStyle("-fx-border-color: red");
        }
        if (closeDateInput.getText() == null || closeDateInput.getText().length() == 0) {
            isValid = false;
            closeDateInput.setStyle("-fx-border-color: red");
        }
        if (itemNameInput.getText() == null || itemNameInput.getText().length() == 0) {
            isValid = false;
            itemNameInput.setStyle("-fx-border-color: red");
        }
        if (itemSumInput.getText() == null || itemSumInput.getText().length() == 0) {
            isValid = false;
            itemSumInput.setStyle("-fx-border-color: red");
        }
        return isValid;
    }

    @FXML
    private void okButtonAction() throws FileNotFoundException {
        if (isInputValid()){
            arrears.setId(idInput.getText());
            String [] tokens = nameInput.getText().split(" ");
            arrears.setSurname(tokens[0]);
            arrears.setName(tokens[1]);
            arrears.setPatronymic(tokens[2]);
            arrears.setUserLogin(loginInput.getText());
            arrears.setOpenDate(openDateInput.getText());
            arrears.setCloseDate(closeDateInput.getText());
            arrears.setSum(sumInput.getText());
            arrears.setItemName(itemNameInput.getText());
            arrears.setItemSum(itemSumInput.getText());
            arrears.setStatus(statusInput.getSelectionModel().getSelectedItem());

            arrears.addArrears();
            okClicked = true;
            dialogStage.close();
        }
    }


    @FXML
    private void closeButtonAction(){
        dialogStage.close();
    }
}
