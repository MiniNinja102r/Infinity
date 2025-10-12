package com.example.infinity.service;

import com.example.infinity.storage.Config;
import com.example.infinity.util.MusicPlayer;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

public final class SettingsService {
    private static SettingsService instance;
    private static MusicPlayer musicPlayer = MusicPlayer.getInstance();

    @Getter @Setter
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

    public void setMusicVolume(double newVolume) {
        if (newVolume < 0 || newVolume > 1) {
            throw new IllegalArgumentException("Music volume can be from 0 to 1");
        }

        musicVolume = newVolume;
        musicPlayer.setMusicVolume(musicVolume);
    }
}
