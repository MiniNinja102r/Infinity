package com.example.infinity.service.scene;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SceneType {
    MAIN_MENU("main-menu.fxml"),
    SETTINGS("settings.fxml");

    private final String fxmlFile;
}
