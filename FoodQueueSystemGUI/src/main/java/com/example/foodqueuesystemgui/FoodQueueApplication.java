package com.example.foodqueuesystemgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class FoodQueueApplication extends Application {
//    Reference: https://www.youtube.com/watch?v=ObQoMh4y_fM&t=916s
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FoodQueueApplication.class.getResource("FoodQueue-view.fxml"));
//        The Scene's width will be 608 pixels, and its height will be 440 pixels
        Scene scene = new Scene(fxmlLoader.load(), 608, 440);
//        Set the title of the JavaFX Stage (window) to "View the status of the queues"
        stage.setTitle("View the status of the queues");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch();
    }

}