package ru.itis.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import ru.itis.client.Client;
import ru.itis.helper.JSONService;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RoomFXMLController implements Initializable {

    private ArrayList<Button> buttons;
    private static String word;
    private JSONService service;
    private Client client;

    @FXML
    private Button submitButton;
    @FXML
    public Label labelOfStatus;
    @FXML
    public Label answerFromServer;
    @FXML
    private HBox buttonBox;
    @FXML
    private Label player1;
    @FXML
    private Label moves1;
    @FXML
    private Label player2;
    @FXML
    private Label moves2;
    @FXML
    private HBox composedBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.client = new Client();
        this.buttons = new ArrayList<>();
        this.service = new JSONService();

        start();
    }

    private void start() {
        player1.setText(MenuFXMLController.name);
        moves1.setText(String.valueOf(client.moves));
        player2.setText(client.rival);
        moves2.setText(String.valueOf(client.movesOfRival));
        addAllButtons();
        Client.mainWord = client.getResponse();
        //проверяем текущий статус игрока
        if (client.status) {
            labelOfStatus.setText("Ход противника!");
            lockButtons();
        }
        else {
           labelOfStatus.setText("Ваш ход!");
           enableButtons();
        }
        game.start();
    }

    private Thread game = new Thread(() ->{
        //указатель, для дальнейшей остановки игры
        boolean result = true;
        while (result) {
            int isOver = Integer.parseInt(client.getResponse());
            //если конец игры
            switch (isOver) {
                case 1:
                    Platform.runLater(() ->
                            labelOfStatus.setText("Конец игры! Победил игрок:" + client.nameOfWinner));
                    //блокируем все кнопки
                    lockButtons();
                    result = false;
                    break;
                case 0:
                    if (Integer.parseInt(client.getResponse()) == 7) {
                        Platform.runLater(() ->
                                answerFromServer.setText("Нет такого слова, либо оно уже было составлено раннее"));
                    } else {
                        Platform.runLater(() ->
                                answerFromServer.setText("Cлово принято!"));
                        client.status = !client.status;
                        Platform.runLater(() -> {
                            labelOfStatus.setText("Ход противника");
                        });
                    }
                    break;
            }
            break;
        }
        if (client.status) {
            lockButtons();
        } else if (result) {
            enableButtons();
            Platform.runLater(() ->
                    labelOfStatus.setText("Ваш ход. Выберете букву"));

        }

    });


    private void lockButtons() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setDisable(true);
        }
        submitButton.setDisable(true);

    }

    private void enableButtons() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setDisable(false);
        }
        submitButton.setDisable(false);
    }

    private Button createWordButton(String letter){
        Button button = new Button();
        button.setText(letter);
        button.setMnemonicParsing(false);
        button.setPrefHeight(45.0);
        button.prefWidth(50.0);
        button.setOnMouseClicked(event -> {
            composedBox.getChildren().add(new Button(button.getText()));
            button.setDisable(true);
        });
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

    @FXML
    public String setOnMouseClicked() {
        int size = composedBox.getChildren().size();
        for (int i = 0; i < size; i++) {
            Button button = (Button) composedBox.getChildren().get(i);
            word += button.getText();
        }
        composedBox.getChildren().remove(0, size);
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setDisable(false);
        }
        client.send(word);
        return word;
    }
}