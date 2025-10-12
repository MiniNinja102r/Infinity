package com.example.infinity.storage;

import lombok.extern.java.Log;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log
public final class Config {
    private static Map<String, Object> data;

    // Подавление создания стандартного конструктора.
    private Config() {}

    public static void load() {
        try (InputStream input = Config.class.getResourceAsStream("/com/example/infinity/config.yml")) {
            if (input == null) {
                log.severe("Config file not found");
                return;
            }
            final Yaml yaml = new Yaml();
            data = yaml.load(input);
            loadResources();
            loadAudio();
        } catch (IOException e) {
            log.severe("Error reading resource configuration: " + e);
        }
    }

    private static void loadResources() {
        Map<String, Object> resource = (Map<String, Object>) data.get("resource");
        if (resource == null)
            log.severe("Resource section not found in config");
        else {
            Resource.PATH = (String) resource.get("path");
            Resource.FXML_PATH = (String) resource.get("fxml");
            Resource.IMG_PATH = (String) resource.get("image");
            Resource.AUDIO_PATH = (String) resource.get("audio");
        }
    }

    private static void loadAudio() {
        Map<String, Object> audio = (Map<String, Object>) data.get("audio");
        if (audio == null)
            log.severe("Audio section not found in config");
        else {
            Audio.MAX_RETRY_COUNTS = (int) audio.get("max_retry_counts");

            final List<String> sounds = (List<String>) audio.get("main_menu_sounds_names");
            if (sounds == null || sounds.isEmpty()) {
                log.severe("Main Menu sounds is empty or null");
                Audio.MAIN_MENU_SOUNDS_NAMES = List.of(); // Пустой список музыки по умолчанию.
            } else
                Audio.MAIN_MENU_SOUNDS_NAMES = new ArrayList<>(sounds);
        }
    }

    private static void loadSettings() {
        Map<String, Object> settings = (Map<String, Object>) data.get("settings");
        if (settings == null)
            log.severe("Settings section not found in config");
        else {
            Settings.DEFAULT_MUSIC_ENABLED = (boolean) settings.get("default_music_enabled");
            Settings.DEFAULT_MUSIC_VOLUME = (double) settings.get("default_music_volume");
        }
    }

    public static class Resource {

        // Подавление создания стандартного конструктора.
        private Resource() {}

        public static String PATH;
        public static String FXML_PATH;
        public static String IMG_PATH;
        public static String AUDIO_PATH;
    }

    public static class Audio {

        // Подавление создания стандартного конструктора.
        private Audio() {}

        public static int MAX_RETRY_COUNTS;
        public static List<String> MAIN_MENU_SOUNDS_NAMES = new ArrayList<>();
    }

    public static class Settings {

        // Подавление создания стандартного конструктора.
        private Settings() {}

        public static boolean DEFAULT_MUSIC_ENABLED;
        public static double DEFAULT_MUSIC_VOLUME;
    }
}