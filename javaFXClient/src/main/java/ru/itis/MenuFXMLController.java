package ru.itis;

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

public class MenuFXMLController implements Initializable {

    private Stage primaryStage;

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
        this.primaryStage = MainApp.stage;
    }

    @FXML
    public void onClickPlay(ActionEvent event) throws IOException {
        //загружаем сцену для комнаты
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/room.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("GAME STARTED");
        primaryStage.show();
    }
}
