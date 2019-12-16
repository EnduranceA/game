package ru.itis.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    public static Stage primaryStage;

    //все сцены
    private static Parent menu;
    private static Parent room;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainApp.primaryStage = primaryStage;
        //primaryStage - графическое окно с несколькими scene
        menu = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
        Scene scene = new Scene(menu);
        scene.getStylesheets().add("/styles/styles.css");

        //setResizable(false) - для того, чтобы нельзя было поменять размер окна
        primaryStage.setResizable(false);

        primaryStage.setTitle("Слова из слов");
        primaryStage.setScene(scene);

        //устанавливает размеры окна в соответствии с размерами содержимого объекта Scene
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
