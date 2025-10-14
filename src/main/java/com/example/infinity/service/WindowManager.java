package com.example.infinity.service;

import com.example.infinity.service.scene.SceneType;
import com.example.infinity.util.InfinityConstants;
import javafx.beans.value.ChangeListener;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.EqualsAndHashCode;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Log @EqualsAndHashCode
public final class WindowManager {
    private static WindowManager instance;
    private final Stage stage;
    private final List<SceneType> initializedSceneTypes = new ArrayList<>();

    // Подавление создания стандартного конструктора.
    private WindowManager(Stage stage) {
        this.stage = stage;
    }

    public static void initialize(@NotNull Stage stage) {
        if (instance == null)
            instance = new WindowManager(stage);
        else
            throw new IllegalStateException("WindowManager already initialized");
    }

    @NotNull
    public static synchronized WindowManager getInstance() {
        if (instance == null)
            throw new IllegalStateException("WindowManager not initialized");
        return instance;
    }

    public void initScaleListeners(@NotNull SceneType type, @NotNull Scene scene) {
        if (initializedSceneTypes.contains(type)) {
            log.info("SceneType " + type + " already listening"); // Debug-info
            return;
        }
        final Parent root = scene.getRoot();

        ChangeListener<Number> resizeListener = (obs, oldVal, newVal) -> {
            double scaleX = stage.getWidth() / InfinityConstants.GAME_WIDTH;
            double scaleY = stage.getHeight() / InfinityConstants.GAME_HEIGHT;
            final double scale = Math.min(scaleX, scaleY);
            root.setScaleX(scale);
            root.setScaleY(scale);
        };

        stage.widthProperty().addListener(resizeListener);
        stage.heightProperty().addListener(resizeListener);
        initializedSceneTypes.add(type);

        // Инициализация масштаба при старте
        resizeListener.changed(null, null, null);
    }
}
