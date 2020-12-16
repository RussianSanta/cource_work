package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.Objects.User;

import java.io.*;

public class Main extends Application {
    public static ObservableList<User> usersList = FXCollections.observableArrayList();
    public static User activeUser;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Files/Enter.fxml"));
        primaryStage.setTitle("Вход");
        primaryStage.setScene(new Scene(root, 300, 255));
        primaryStage.getIcons().add(new Image(this.getClass().getResource("../Data/img/icon.png").toString()));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {
        File usersFile = new File("src/Data/users");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(usersFile)));
        String line;
        while( (line = br.readLine())!= null ){
            User user = new User();
            String [] tokens = line.split("#");
            user.setLogin(tokens[0]);
            user.setPassword(tokens[1]);
            user.setName(tokens[2]);
            user.setAccessProfile(tokens[3]);
            usersList.add(user);
        }

        launch(args);
    }
}
