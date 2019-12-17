package ru.itis.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import ru.itis.client.Client;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RoomFXMLController implements Initializable {

    private ArrayList<Button> buttons;

    @FXML
    private HBox buttonBox;

    @FXML
    private Label player1;

    @FXML
    private Label player2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.buttons = new ArrayList<>();
        player1.setText(MenuFXMLController.name);
        player2.setText(Client.rival);
        System.out.println("player2.setText");
        addAllButtons();
        start();
    }

    private void start() {
        //проверяем текущий статус игрока
        if (Client.status) {
            lockButtons();
        }
        else {
            enableButtons();
        }
    }

    private void lockButtons() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setDisable(true);
        }
    }

    private void enableButtons() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setDisable(false);
        }
    }

    private Button createWordButton(String letter){
        Button button = new Button(letter);
        button.setMnemonicParsing(false);
        button.setPrefHeight(45.0);
        button.prefWidth(50.0);
        return button;
    }

    private void addAllButtons()  {
        String word = Client.mainWord;
        for (int i = 0; i < word.length(); i++) {
            Button button = createWordButton(String.valueOf(word.charAt(i)));
            //добавляем кнопку в лист
            buttons.add(button);
            //для room.fxml
            buttonBox.getChildren().add(button);
        }
    }
}