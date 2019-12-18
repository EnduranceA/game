package ru.itis.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import ru.itis.client.Client;

import java.net.URL;
import java.util.ResourceBundle;

public class ExpectationFXMLController implements Initializable {

    private Client client;

    @FXML
    public AnchorPane room;

    @FXML
    public Label expLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.client = new Client();
    }

    @FXML
    public void setMouseOnClicked() {
        room.setOnMouseClicked(event ->
        {
            Circle r = new Circle(10, Color.DEEPPINK);

            r.setCenterX(event.getX());
            r.setCenterY(event.getY());

            if(event.getButton() == MouseButton.PRIMARY) {
                r.setFill(Color.CRIMSON);
            }
            room.getChildren().add(r);

            //изменение атрибутов за время
            KeyValue kv = new KeyValue(r.centerXProperty(), (r.getCenterX() - 200));

            //провести изменение за 10 секунд
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            Timeline t = new Timeline(kf);

            //выполнение в обе стороны
            t.setAutoReverse(true);
            t.setCycleCount(Animation.INDEFINITE);
            t.play();
        });
    }

    @FXML
    public void setMousePressed() {
        expLabel.setOnMouseEntered(event ->
        {
            expLabel.setScaleX(1.5);
            expLabel.setScaleY(1.5);
        });
    }

    public void setOnMouseExited() {
        expLabel.setOnMouseExited(event ->
        {
            expLabel.setScaleX(1);
            expLabel.setScaleY(1);
        });
    }

}
