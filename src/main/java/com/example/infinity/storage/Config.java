package com.example.infinity.storage;

import lombok.extern.java.Log;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
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
    }
}