package com.example.infinity.service.scene;

import com.example.infinity.service.WindowManager;
import com.example.infinity.storage.Config;
import com.example.infinity.util.InfinityConstants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

@Log
public final class SceneManager {
    private static SceneManager instance;
    private final Stage stage;
    private final WindowManager windowManager = WindowManager.getInstance();
    private final Map<SceneType, Scene> sceneCache = new EnumMap<>(SceneType.class);

    // Подавление создания стандартного конструктора.
    private SceneManager(Stage stage) {
        this.stage = stage;
        this.stage.setTitle(InfinityConstants.INFINITY_GAME_NAME);
        this.stage.setMinWidth(InfinityConstants.GAME_WIDTH);
        this.stage.setMinHeight(InfinityConstants.GAME_HEIGHT);
    }

    public static void initialize(@NotNull Stage stage) {
        if (instance == null)
            instance = new SceneManager(stage);
        else
            throw new IllegalStateException("SceneManager already initialized");
    }

    @NotNull
    public static synchronized SceneManager getInstance() {
        if (instance == null)
            throw new IllegalStateException("SceneManager not initialized");
        return instance;
    }

    public void switchScene(@NotNull SceneType type) {
        try {
            final Scene scene = sceneCache.computeIfAbsent(type, st -> {
                try {
                    return loadScene(st);
                } catch (IOException e) {
                    log.severe(String.format("Error, can't load scene $1%s: $2%s", st, e.getMessage()));
                    return null;
                }
            });
            if (scene == null)
                throw new NullPointerException(String.format("Error, scene %s could not be loaded", type));
            stage.setScene(scene);
            stage.centerOnScreen();
            windowManager.initScaleListeners(type, scene);
        } catch (Exception e) {
            log.severe(String.format("Failed to switch scene to $1%s: $2%s", type, e.getMessage()));
        }
    }

    private Scene loadScene(@NotNull SceneType type) throws IOException {
        final String path = Config.Resource.FXML_PATH + type.getFxmlFile();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();
        return new Scene(root, InfinityConstants.GAME_WIDTH, InfinityConstants.GAME_HEIGHT);
    }
}
