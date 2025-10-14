package com.example.infinity.application;

import com.example.infinity.service.WindowManager;
import com.example.infinity.service.scene.SceneManager;
import com.example.infinity.service.scene.SceneType;
import com.example.infinity.storage.Config;
import com.example.infinity.service.audio.MusicPlayer;
import javafx.application.Application;
import javafx.stage.Stage;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

public final class MainMenuApplication extends Application {
    @Getter
    private MusicPlayer musicPlayer;

    @Override
    public void start(@NotNull Stage stage) {
        Config.load();

        initializeManagers(stage);

        SceneManager.getInstance().switchScene(SceneType.MAIN_MENU);
        stage.show();

        launchMusic();
    }

    private void initializeManagers(@NotNull Stage stage) {
        WindowManager.initialize(stage);
        SceneManager.initialize(stage);
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
