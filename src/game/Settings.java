package game;

import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

public class Settings {
    public static final String PROJECT_NAME = "Zomb!es";
    public static final int SCENE_WIDTH = 1920;
    public static final int SCENE_HEIGHT = 1000;

    public static final int SIZE_HUMAN = 20;
    public static final int SIZE_ZOMBIE = 20;

    public static final int NB_HUMANS = 4;

    public static final int ID_HUMAN0 = 0;
    public static final int ID_HUMAN1 = 1;
    public static final int ID_HUMAN2 = 2;
    public static final int ID_HUMAN3 = 3;

    public static final Color COLOR_HUMAN0 = Color.CYAN;
    public static final Color COLOR_HUMAN1 = Color.MAGENTA;
    public static final Color COLOR_HUMAN2 = Color.ORANGE;
    public static final Color COLOR_HUMAN3 = Color.DARKBLUE;
    public static final Color COLOR_ZOMBIE = Color.RED;

    public static final Color COLOR_PROP = Color.BLACK;
    public static final Color COLOR_DIRT = Color.SIENNA;
    public static final Color COLOR_GRASS = Color.GREEN;
    public static final Color COLOR_CEMENT = Color.LIGHTGREY;

    public static final KeyCode PAUSE = KeyCode.ESCAPE;
    public static final MouseButton FIRE_PRIMARY = MouseButton.PRIMARY;
    public static final MouseButton FIRE_SECONDARY = MouseButton.SECONDARY;
    public static final KeyCode RELOAD = KeyCode.R;
    public static final KeyCode SWAP = KeyCode.A;

    public static final KeyCode GRENADE = KeyCode.G;

    public static final KeyCode UP = KeyCode.Z;
    public static final KeyCode DOWN = KeyCode.S;
    public static final KeyCode RIGHT = KeyCode.D;
    public static final KeyCode LEFT = KeyCode.Q;

    public static final KeyCode USE = KeyCode.E;

    public static final int SCROLL_SPEED = 1;
    public static final boolean MOUSE_CAM = true;
    public static final KeyCode CAM_UP = KeyCode.UP;
    public static final KeyCode CAM_DOWN = KeyCode.DOWN;
    public static final KeyCode CAM_RIGHT = KeyCode.RIGHT;
    public static final KeyCode CAM_LEFT = KeyCode.LEFT;
}
