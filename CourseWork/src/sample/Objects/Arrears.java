package sample.Objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Arrears {
    private String id;
    private String userLogin;
    private String surname;
    private String name;
    private String patronymic;
    private String openDate;
    private String closeDate;
    private String sum;
    private String itemName;
    private String itemSum;
    private String status;

    public Arrears() {
        this.surname = "";
        this.name = "";
        this.patronymic = "";
    }

    public Arrears(String id, String userLogin, String surname, String name, String patronymic,
                   String openDate, String closeDate, String sum, String itemName, String itemSum, String status) {
        this.id = id;
        this.userLogin = userLogin;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.sum = sum;
        this.itemName = itemName;
        this.itemSum = itemSum;
        this.status = status;
    }

    public void addArrears() throws FileNotFoundException {
        File productsFile = new File("src/Data/arrears");
        PrintStream filePrintStream = new PrintStream(new FileOutputStream(productsFile, true));
        filePrintStream.println(this.getId() + " " + this.getUserLogin() + " " + this.getSurname() + " "
                + this.getName() + " " + this.getPatronymic() + " " + this.getOpenDate() + " "
                + this.getCloseDate() + " " + this.getSum() + " " + this.getItemName() + " " + this.getItemSum() +
                " " + this.getStatus());
    }

    public String getId() {
        return id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getOpenDate() {
        return openDate;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public String getSum() {
        return sum;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemSum() {
        return itemSum;
    }

    public String getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemSum(String itemSum) {
        this.itemSum = itemSum;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
