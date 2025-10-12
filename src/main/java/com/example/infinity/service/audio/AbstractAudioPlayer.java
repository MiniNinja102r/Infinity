package com.example.infinity.service.audio;

import com.example.infinity.exception.MusicPlayerException;
import com.example.infinity.storage.Config;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public abstract class AbstractAudioPlayer {
    protected MediaPlayer mediaPlayer;
    protected double volume = 1.0;

    public void play(String audioFileName, boolean isLoop) {
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
            onPlayError(e);
        }
    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
    }

    public void setVolume(double newVolume) {
        if (newVolume < 0 || newVolume > 1)
            throw new IllegalArgumentException("Audio volume can be from 0 to 1");
        volume = newVolume;
        mediaPlayer.setVolume(volume);
    }

    public void setMute(boolean isMute) {
        mediaPlayer.setMute(!isMute);
    }

    protected abstract void onPlayError(Exception e);
}
