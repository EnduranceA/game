package ru.itis;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLController implements Initializable {

    @FXML
    public TextField userName;

    private Stage primaryStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.primaryStage = MainApp.stage;
    }

    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException {
        //загружаем сцену для комнаты
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/RoomScene.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("GAME STARTED");
        primaryStage.show();
    }
}
