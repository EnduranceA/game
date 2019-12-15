package ru.itis.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import ru.itis.client.Client;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RoomFXMLController implements Initializable {

    private Client client;
    private ArrayList<Button> buttons;

    @FXML
    //максильмальное количество букв в генерируемом слове
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13;

    @FXML
    private Label player1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.client = new Client();
        player1.setText(MenuFXMLController.name);
        addAllButtons();
        start();
    }

    private void start() {
        //TODO: получить главное слово и присвоить буквы кнопке

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

    private void addAllButtons() {
        HBox box = new HBox(0.2);

        String word = Client.mainWord;
        for (int i = 0; i < word.length(); i++) {
            box.getChildren().add(createButton(String.valueOf(word.charAt(i))));
        }
    }

    private Button createButton(String str) {
        Button button = new Button();
        button.setText(str);
        return button;
    }
}

//buttons = new ArrayList<>();
//        buttons.add(btn1);
//        buttons.add(btn2);
//        buttons.add(btn3);
//        buttons.add(btn4);
//        buttons.add(btn4);
//        buttons.add(btn5);
//        buttons.add(btn6);
//        buttons.add(btn7);
//        buttons.add(btn8);
//        buttons.add(btn9);
//        buttons.add(btn10);
//        buttons.add(btn11);
//        buttons.add(btn12);
//        buttons.add(btn13);
