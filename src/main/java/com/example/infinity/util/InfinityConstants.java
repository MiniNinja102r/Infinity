package com.example.infinity.util;

public final class InfinityConstants {

    // Подавление создания стандартного конструктора.
    private InfinityConstants() {}

    public static final String INFINITY_GAME_NAME = "Infinity";
    public static final int GAME_WIDTH = 600;
    public static final int GAME_HEIGHT = 400;

    // Tile size:
    public static final int TILE_WIDTH = 10;
    public static final int TILE_HEIGHT = 10;
    public static final int GAME_WIDTH_TILES_COUNT = GAME_WIDTH / TILE_WIDTH;
    public static final int GAME_HEIGHT_TILES_COUNT = GAME_HEIGHT / TILE_HEIGHT;
}
