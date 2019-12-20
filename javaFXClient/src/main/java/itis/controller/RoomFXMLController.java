package itis.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import itis.client.Client;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RoomFXMLController implements Initializable {

    private ArrayList<Button> buttons;
    private static String word = "";

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
    private Label rating1;
    @FXML
    private Label player2;
    @FXML
    private Label rating2;
    @FXML
    private HBox composedBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.buttons = new ArrayList<>();
        start();
    }

    private void start() {
        player1.setText(MenuFXMLController.name);
        player2.setText(Client.rival);
        rating1.setText("Текущий рейтинг: "+ 0);
        rating2.setText("Текущий рейтинг: "+ 0);
        addAllButtons();
    }

    public void yourTurn() {
        Platform.runLater(() -> {
            labelOfStatus.setText("Ваш ход!");
            enableButtons();
        });
    }

    public void waitYourTurn() {
        Platform.runLater(() -> {
            labelOfStatus.setText("Ход противника!");
            lockButtons();
        });
    }

    public void changeRating(long rating) {
        Platform.runLater(() -> rating1.setText("Текущий рейтинг: " + rating));
    }

    public void changeRatingOfRival(long rating) {
        Platform.runLater(() -> rating2.setText("Текущий рейтинг: " + rating));
    }

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

    private Button createWordButton(String letter) {
        Button button = new Button();
        button.setText(letter);
        button.setMnemonicParsing(false);
        button.setPrefHeight(45.0);
        button.prefWidth(50.0);
        button.setOnMouseClicked(event -> {
            composedBox.getChildren().add(new Button(button.getText()));
            word = word + button.getText();
            button.setDisable(true);
        });
        return button;
    }

    private void addAllButtons() {
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
    public void setOnMouseClicked() {
        int size = composedBox.getChildren().size();
        composedBox.getChildren().remove(0, size);
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setDisable(false);
        }
        Client.responseService.sendWordToServer(word);
        word = "";
    }
}