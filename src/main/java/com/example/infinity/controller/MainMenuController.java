package com.example.infinity.controller;

import com.example.infinity.util.InfinityConstants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
        // TODO: загрузка исследований
    }

    @FXML
    void onPlayClick(ActionEvent event) {
        // TODO: запуск игры
    }

    @FXML
    void onSettingsClick(ActionEvent event) {
        // TODO: загрузка настроек
    }

    @FXML
    public void initialize() {
        welcomeText.setText(String.format("Добро пожаловать в %s", InfinityConstants.INFINITY_GAME_NAME));
    }
}
