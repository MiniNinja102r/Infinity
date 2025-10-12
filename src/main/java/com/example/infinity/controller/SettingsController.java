package com.example.infinity.controller;

import com.example.infinity.service.SettingsService;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public final class SettingsController {

    private final SettingsService settings = SettingsService.getInstance();

    @FXML
    private CheckBox musicCheckBox;

    @FXML
    private Slider volumeSlider;

    @FXML
    private Label volumeLabel;

    @FXML
    public void initialize() {
        musicCheckBox.setSelected(settings.isMusicEnabled());
        volumeSlider.setValue(settings.getMusicVolume());

        updateVolumeLabel(volumeSlider.getValue());

        // Обновление label при движении слайдера
        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
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
        settings.setMusicVolume(volumeSlider.getValue());

        //Stage stage = (Stage) musicCheckBox.getScene().getWindow();
        //stage.close();
    }
}