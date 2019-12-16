package ru.itis.controller;

import javafx.fxml.Initializable;
import ru.itis.client.Client;

import java.net.URL;
import java.util.ResourceBundle;

//TODO: добавить анимацию
public class ExpectationFXMLController implements Initializable {

    private Client client;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.client = new Client();
    }
}
