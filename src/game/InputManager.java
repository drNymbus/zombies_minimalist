package game;

import java.util.BitSet;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
// import static javafx.scene.input.KeyCode.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import java.awt.Robot;

public class InputManager {
	private int mouse_x, mouse_y;
	private MouseButton mouse_button;
	private MouseEvent mouse;


	private BitSet keyboardBitSet;

	private Scene scene;

	public InputManager(Scene scene) {
		this.scene = scene;
		this.keyboardBitSet = new BitSet();

	}

	public void addListeners() {
		this.scene.addEventFilter(KeyEvent.KEY_PRESSED, keyPressedEventHandler);
		this.scene.addEventFilter(KeyEvent.KEY_RELEASED, keyReleasedEventHandler);
		this.scene.addEventFilter(MouseEvent.ANY, mousePressedEventHandler);
		this.scene.addEventFilter(MouseEvent.MOUSE_RELEASED, mouseReleasedEventHandler);
	}

	public void removeListeners() {
		this.scene.removeEventFilter(KeyEvent.KEY_PRESSED, keyPressedEventHandler);
		this.scene.removeEventFilter(KeyEvent.KEY_RELEASED, keyReleasedEventHandler);
		this.scene.removeEventFilter(MouseEvent.ANY, mousePressedEventHandler);
		this.scene.removeEventFilter(MouseEvent.MOUSE_RELEASED, mouseReleasedEventHandler);
	}

	private EventHandler<KeyEvent> keyPressedEventHandler = event -> {
		// register key down
		keyboardBitSet.set(event.getCode().ordinal(), true);
		event.consume();
	};

	private EventHandler<KeyEvent> keyReleasedEventHandler = new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent event) {
			// register key up
			keyboardBitSet.set(event.getCode().ordinal(), false);
			event.consume();
		}
	};

	private EventHandler<MouseEvent> mousePressedEventHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent e) {
			mouse_x = (int) e.getSceneX();
			mouse_y = (int) e.getSceneY();
			mouse_button = e.getButton();
			mouse = e;
		}
	};

	private EventHandler<MouseEvent> mouseReleasedEventHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent e) {
			mouse_x = (int) e.getSceneX();
			mouse_y = (int) e.getSceneY();
			mouse_button = MouseButton.NONE;
			mouse = e;
		}
	};

	public void setMousePos(int x, int y) {
		try {
			x += this.scene.getX();
			y += this.scene.getY();
			System.out.println("setMousePos(" + x + "," + y + ")");
			new Robot().mouseMove(x, y);
		} catch (Exception e) {
			System.out.println("setMousePos(" + x + "," + y + ")");
		}

		// r.mouseMove(x, y);
	}
	public void setMouseX(int x) { this.setMousePos(x, this.getMouseY()); }
	public void setMouseY(int y) { this.setMousePos(this.getMouseX(), y); }

	public int getMouseX() { return this.mouse_x; }
	public int getMouseY() { return this.mouse_y; }

	private boolean is(KeyCode key) { return this.keyboardBitSet.get(key.ordinal()); }
	private boolean is(MouseButton button) {
		boolean res = (this.mouse_button == button);
		this.mouse_button = MouseButton.NONE;
		return res;
	}

    public boolean isPause() { return is(Settings.PAUSE); }
    public boolean isExit() { return is(KeyCode.ESCAPE); }

	public boolean isFire() { return is(Settings.FIRE_PRIMARY); }
	public boolean isFireSecond() { return is(Settings.FIRE_SECONDARY); }
	public boolean isReload() { return is(Settings.RELOAD); }

	public boolean isUP() { return is(Settings.UP); }
	public boolean isDOWN() { return is(Settings.DOWN); }
	public boolean isLEFT() { return is(Settings.LEFT); }
	public boolean isRIGHT() { return is(Settings.RIGHT); }

}
