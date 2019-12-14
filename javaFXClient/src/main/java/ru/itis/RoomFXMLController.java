package ru.itis;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RoomFXMLController implements Initializable {

    private Client client;
    private ArrayList<Button> buttons;

    @FXML
    //максильмальное количество букв в генерируемом слове
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        client = new Client();
        addAllButtons();
        start();
    }

    private void start() {
        //проверяем текущий статус игрока
        if (Client.status) {
            lockButtons();
        }
    }

    private void lockButtons() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setDisable(true);
        }
    }

    private void addAllButtons() {
        buttons = new ArrayList<>();
        buttons.add(btn1);
        buttons.add(btn2);
        buttons.add(btn3);
        buttons.add(btn4);
        buttons.add(btn4);
        buttons.add(btn5);
        buttons.add(btn6);
        buttons.add(btn7);
        buttons.add(btn8);
        buttons.add(btn9);
        buttons.add(btn10);
        buttons.add(btn11);
        buttons.add(btn12);
        buttons.add(btn13);
    }


}
