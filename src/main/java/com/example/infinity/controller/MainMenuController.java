package com.example.infinity.controller;

import com.example.infinity.service.scene.SceneManager;
import com.example.infinity.service.scene.SceneType;
import com.example.infinity.util.InfinityConstants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lombok.extern.java.Log;

@Log
public final class MainMenuController {

    private final SceneManager sceneManager = SceneManager.getInstance();

    @FXML
    private Button continuePlayButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button mapSelectButton;

    @FXML
    private Button musicPlayerButton;

    @FXML
    private Button researchButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Label welcomeText;

    @FXML
    void onResearchClick(ActionEvent event) {
        log.info("Нажата кнопка: исследования");
        // TODO: загрузка исследований
    }

    @FXML
    void onSettingsClick(ActionEvent event) {
        sceneManager.switchScene(SceneType.SETTINGS);
    }

    @FXML
    void onContinuePlayClick(ActionEvent event) {
        //todo: last active game init.
    }

    @FXML
    void onExitClick(ActionEvent event) {
        //todo: exit
    }

    @FXML
    void onMapSelectClick(ActionEvent event) {
        //todo: map select
    }

    @FXML
    void onMusicPlayerClick(ActionEvent event) {
        //todo: music player
    }

    @FXML
    public void initialize() {
        welcomeText.setText(String.format("Добро пожаловать в %s", InfinityConstants.INFINITY_GAME_NAME));
    }
}
