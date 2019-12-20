package itis.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import itis.manager.SceneManager;

public class MenuFXMLController implements Initializable {

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
    }

    @FXML
    public void onClickPlay(ActionEvent event) throws IOException {
        name = userName.getText();
        SceneManager.setExpectationScene();
    }
}
