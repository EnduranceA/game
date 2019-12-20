package itis.client;

import javafx.application.Application;
import javafx.stage.Stage;
import itis.manager.SceneManager;


public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        SceneManager.primaryStage = primaryStage;

        //setResizable(false) - для того, чтобы нельзя было поменять размер окна
        primaryStage.setResizable(false);
        primaryStage.setTitle("Слова из слов");
        SceneManager.setMenuScene();

        //устанавливает размеры окна в соответствии с размерами содержимого объекта Scene
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
