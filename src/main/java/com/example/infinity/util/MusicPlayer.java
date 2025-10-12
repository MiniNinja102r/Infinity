package com.example.infinity.util;

import com.example.infinity.exception.MusicPlayerException;
import com.example.infinity.storage.Config;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Log
public final class MusicPlayer {
    private static final List<String> MAIN_MENU_SOUNDS_NAMES = List.of(
            "MainTheme-1.mp3"
    );
    private static final int MAX_RETRIES = 3;

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
        final int fileIndex = ThreadLocalRandom.current().nextInt(MAIN_MENU_SOUNDS_NAMES.size());
        String audioFileName = MAIN_MENU_SOUNDS_NAMES.get(fileIndex);
        playAudioInternal(audioFileName, false);
        mediaPlayer.setOnEndOfMedia(this::playMainMenuMusic);
    }

    public void playAudio(String audioFileName) {
        playAudioInternal(audioFileName, false);
    }

    public void playAudioLoop(String audioFileName) {
        playAudioInternal(audioFileName, true);
    }

    private void playAudioInternal(String audioFileName, boolean isLoop) throws MusicPlayerException {
        try {
            if (audioFileName == null || audioFileName.isEmpty()) {
                throw new MusicPlayerException("AudioFile is empty or null");
            }

            URL audio = getClass().getResource(Config.Resource.AUDIO_PATH + audioFileName);
            if (audio == null) {
                throw new MusicPlayerException("Audio file not found: " + audioFileName);
            }

            stop();

            final Media media = new Media(audio.toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            if (isLoop)
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } catch (Exception e) {
            log.severe("Error with playing audio: " + e.getMessage());
            attemptToPlayMainMenuMusic();
        }
    }

    private int retryCount = 0;
    private void attemptToPlayMainMenuMusic() {
        if (retryCount++ < MAX_RETRIES) {
            log.warning(String.format("Retrying to play main menu music ($1%d / $2%d)", retryCount, MAX_RETRIES));
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
