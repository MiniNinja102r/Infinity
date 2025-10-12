package com.example.infinity.util;

import com.example.infinity.storage.Config;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class MusicPlayer {
    private static MusicPlayer instance;
    private MediaPlayer mediaPlayer;

    // Подавление создания стандартного конструктора.
    private MusicPlayer() {}

    @NotNull
    public static synchronized MusicPlayer getInstance() {
        if (instance == null)
            instance = new MusicPlayer();
        return instance;
    }

    public void playLoop() {
        stop();

        final Media media = new Media(Objects.requireNonNull(getClass().getResource(
                Config.Resource.AUDIO_PATH + "MainTheme-1.mp3")).toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public void playLoop(String path) {
        stop();

        final Media media = new Media(Objects.requireNonNull(getClass().getResource(path)).toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
    }
}
