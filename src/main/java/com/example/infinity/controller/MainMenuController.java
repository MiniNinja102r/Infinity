package com.example.infinity.controller;

import com.example.infinity.util.InfinityConstants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lombok.extern.java.Log;

@Log
public class MainMenuController {

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
        log.info("Нажата кнопка: настройки");
        // TODO: загрузка настроек
    }

    @FXML
    public void initialize() {
        welcomeText.setText(String.format("Добро пожаловать в %s", InfinityConstants.INFINITY_GAME_NAME));
    }
}
