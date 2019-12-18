package ru.itis.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.itis.client.MainApp;

public class MenuFXMLController implements Initializable {

    private Stage primaryStage;
    public static String name;

    @FXML
    public TextField userName;

    @FXML
    private Button btnExit;

    @FXML
    public void onClickExit() {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.primaryStage = MainApp.primaryStage;
    }

    @FXML
    public void onClickPlay(ActionEvent event) throws IOException {
        name = userName.getText();
        //загружаем сцену для комнаты
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/expectation.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Waiting");
        primaryStage.show();
    }
}
