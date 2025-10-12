package com.example.infinity.service.audio;

import com.example.infinity.exception.MusicPlayerException;
import com.example.infinity.storage.Config;
import javafx.application.Platform;
import javafx.scene.media.MediaPlayer;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

@Log
public final class MusicPlayer extends AbstractAudioPlayer {
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

    public void playMainMenuMusic() {
        try {
            if (Config.Audio.MAIN_MENU_SOUNDS_NAMES.isEmpty()) {
                throw new MusicPlayerException("Main menu sounds is empty");
            }

            final int fileIndex = ThreadLocalRandom.current().nextInt(Config.Audio.MAIN_MENU_SOUNDS_NAMES.size());
            String audioFileName = Config.Audio.MAIN_MENU_SOUNDS_NAMES.get(fileIndex);
            play(audioFileName, false);
            mediaPlayer.setOnEndOfMedia(this::playMainMenuMusic);
        } catch (Exception e) {
            log.severe("Error, can't launch main menu sounds: " + e.getMessage());
        }
    }

    @Override
    protected void onPlayError(Exception e) {
        log.severe("Error with playing audio: " + e.getMessage());
        attemptToPlayMainMenuMusic();
    }

    public void playMusic(String audioFileName) {
        play(audioFileName, false);
    }

    public void playMusicLoop(String audioFileName) {
        play(audioFileName, true);
    }

    private int retryCount = 0;
    private void attemptToPlayMainMenuMusic() {
        if (retryCount++ < Config.Audio.MAX_RETRY_COUNTS) {
            log.warning(String.format("Retrying to play main menu music ($1%d / $2%d)", retryCount, Config.Audio.MAX_RETRY_COUNTS));
            Platform.runLater(this::playMainMenuMusic);
        } else
            log.severe("Max retry attempts reached. Music playback stopped");
    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
    }
}
