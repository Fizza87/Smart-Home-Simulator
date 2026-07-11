package app;

import app.util.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        SceneManager.setPrimaryStage(primaryStage);
        SceneManager.switchScene("/fxml/login.fxml", "Smart Home Simulator - Login");
    }

    public static void main(String[] args) {
        launch(args);
    }
}