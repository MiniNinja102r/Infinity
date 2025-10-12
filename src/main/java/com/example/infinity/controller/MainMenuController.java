package com.example.infinity.controller;

import com.example.infinity.service.scene.SceneManager;
import com.example.infinity.service.scene.SceneType;
import com.example.infinity.storage.Config;
import com.example.infinity.util.InfinityConstants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lombok.extern.java.Log;

import java.io.IOException;

@Log
public final class MainMenuController {

    private final SceneManager sceneManager = SceneManager.getInstance();

    @FXML
    private Label welcomeText;

    @FXML
    private Button researchButton;

    @FXML
    private Button playButton;

    @FXML
    private Button settingsButton;

    @FXML
    void onResearchClick(ActionEvent event) {
        log.info("Нажата кнопка: исследования");
        // TODO: загрузка исследований
    }

    @FXML
    void onPlayClick(ActionEvent event) {
        log.info("Нажата кнопка: старт");
        // TODO: запуск игры
    }

    @FXML
    void onSettingsClick(ActionEvent event) {
        sceneManager.switchScene(SceneType.SETTINGS);
    }

    @FXML
    public void initialize() {
        welcomeText.setText(String.format("Добро пожаловать в %s", InfinityConstants.INFINITY_GAME_NAME));

        //playButton.setOnAction(e -> sceneManager.switchScene(SceneType.GAME));
        //researchButton.setOnAction(e -> sceneManager.switchScene(SceneType.RESEARCH));
//        settingsButton.setOnAction(e -> sceneManager.switchScene(SceneType.SETTINGS));
    }
}
