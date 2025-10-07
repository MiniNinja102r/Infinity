package com.example.infinity.application;

import com.example.infinity.util.InfinityConstants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public final class MainMenuApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenuApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 760);
        stage.setMaximized(true);
        stage.setTitle(InfinityConstants.INFINITY_GAME_NAME);
        stage.setScene(scene);
        stage.show();
    }
}
