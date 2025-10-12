package com.example.infinity.application;

import com.example.infinity.storage.Config;
import com.example.infinity.util.InfinityConstants;
import com.example.infinity.util.MusicPlayer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;

public final class MainMenuApplication extends Application {
    @Getter
    private MusicPlayer musicPlayer;

    @Override
    public void start(Stage stage) throws Exception {
        Config.load();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(Config.Resource.FXML_PATH + "main-menu.fxml"));
        Scene scene = new Scene(loader.load(), 1080, 760);

        stage.setTitle(InfinityConstants.INFINITY_GAME_NAME);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

        launchMusic();
    }

    private void launchMusic() {
        musicPlayer = MusicPlayer.getInstance();
        musicPlayer.playMainMenuMusic();
    }

    @Override
    public void stop() {
        if (musicPlayer != null) {
            musicPlayer.stop();
        }
    }
}
