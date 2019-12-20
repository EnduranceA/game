package itis.manager;

import itis.controller.RoomFXMLController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {


    public static Stage primaryStage;
    public static RoomFXMLController gameController;

    public static void setMenuScene(){
        primaryStage.setScene(createMenuScene());
    }
    private static Scene createMenuScene(){
        Parent menu = null;
        try {
            menu = FXMLLoader.load(SceneManager.class.getResource("/fxml/menu.fxml"));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        Scene scene = new Scene(menu);
        scene.getStylesheets().add("styles/styles.css");
        return scene;
    }

    public static void setExpectationScene(){
        primaryStage.setScene(createExpectationScene());
    }

    private static Scene createExpectationScene(){
        //загружаем сцену для комнаты
        Parent root = null;
        try {
            root = FXMLLoader.load(SceneManager.class.getResource("/fxml/expectation.fxml"));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add("styles/styles.css");
        return scene;
    }

    public static void setGameScene(boolean wait){
        Platform.runLater(() -> {
            primaryStage.setScene(createGameScene());
            if(wait){
                gameController.waitYourTurn();
            } else {
                gameController.yourTurn();
            }
        });
    }

    private static Scene createGameScene(){
        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/fxml/room.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add("styles/styles.css");
        gameController = loader.getController();
        return scene;
    }

    public static void setEndScene(){
        Platform.runLater(() -> primaryStage.setScene(createMenuScene()));
    }

    private static Scene createEndScene(){
        Parent root = null;
        try {
            root = FXMLLoader.load(SceneManager.class.getResource("/fxml/end.fxml"));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add("styles/styles.css");
        return scene;
    }

}
