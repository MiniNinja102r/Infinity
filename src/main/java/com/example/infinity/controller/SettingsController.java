package com.example.infinity.controller;

import com.example.infinity.service.SettingsService;
import com.example.infinity.service.scene.SceneManager;
import com.example.infinity.service.scene.SceneType;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public final class SettingsController {
    private final SettingsService settings = SettingsService.getInstance();
    private final SceneManager sceneManager = SceneManager.getInstance();

    @FXML
    private CheckBox musicCheckBox;

    @FXML
    private Slider musicVolumeSlider;

    @FXML
    private Label volumeLabel;

    @FXML
    public void initialize() {
        musicCheckBox.setSelected(settings.isMusicEnabled());
        musicVolumeSlider.setValue(settings.getMusicVolume());
        musicVolumeSlider.valueProperty().setValue(settings.getMusicVolume());

        // Обновление label при движении слайдера
        musicVolumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            updateVolumeLabel(newVal.doubleValue());
        });
    }

    private void updateVolumeLabel(double value) {
        int percent = (int) (value * 100);
        volumeLabel.setText(percent + "%");
    }

    @FXML
    private void onSaveClicked() {
        settings.setMusicEnabled(musicCheckBox.isSelected());
        settings.setMusicVolume(musicVolumeSlider.getValue());

        sceneManager.switchScene(SceneType.MAIN_MENU);
    }
}