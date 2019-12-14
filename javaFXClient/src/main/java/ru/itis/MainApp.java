package ru.itis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    public static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        //primaryStage - графическое окно с несколькими scene
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        //setResizable(false) - для того, чтобы нельзя было поменять размер окна
        primaryStage.setResizable(false);

        primaryStage.setTitle("Слова из слов");
        primaryStage.setScene(scene);

        //устанавливает размеры окна в соответствии с размерами содержимого объекта Scene
        primaryStage.sizeToScene();
        primaryStage.show();
    }

}
