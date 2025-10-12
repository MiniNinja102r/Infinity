package com.example.infinity.service;

import com.example.infinity.storage.Config;
import com.example.infinity.service.audio.MusicPlayer;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

public final class SettingsService {
    private static SettingsService instance;
    private static MusicPlayer musicPlayer = MusicPlayer.getInstance();

    @Getter
    private boolean isMusicEnabled = Config.Settings.DEFAULT_MUSIC_ENABLED;

    @Getter
    private double musicVolume = Config.Settings.DEFAULT_MUSIC_VOLUME;

    // Подавление создания стандартного конструктора.
    private SettingsService() {}

    @NotNull
    public static synchronized SettingsService getInstance() {
        if (instance == null)
            instance = new SettingsService();
        return instance;
    }

    public void setMusicEnabled(boolean status) {
        if (isMusicEnabled == status)
            return;
        isMusicEnabled = status;
        musicPlayer.setMute(isMusicEnabled);
    }

    public void setMusicVolume(double newVolume) {
        if (newVolume < 0 || newVolume > 1) {
            throw new IllegalArgumentException("Audio volume can be from 0 to 1");
        }

        musicVolume = newVolume;
        musicPlayer.setVolume(musicVolume);
    }
}
