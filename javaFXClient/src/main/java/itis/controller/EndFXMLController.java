package itis.controller;

import itis.client.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class EndFXMLController implements Initializable {

    @FXML
    private Label result;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        result.setText("Победил(а): " + Client.nameOfWinner);
    }
}
