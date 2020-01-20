package game;

import model.item.*;
import model.util.*;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Main class Réalise la partie graphique
 */
public class Game extends Application {
	private Random rnd = new Random();
	private Text msg = new Text();

	private Pane layer;
	private Scene scene;
	private AnimationTimer gameLoop;
	private int turn;
    private Map map;
	private InputManager input;
	private ArrayList<Text> text = new ArrayList<>();
	private VBox tmp;
	private HBox endMsg;
	Group root;

	@Override
	public void start(Stage stage){
		root = new Group();
		scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
		scene.setFill(Color.GREY);
		stage.setScene(scene);
		stage.setResizable(true);
		stage.show();
		stage.setTitle("Zomb!es");
		layer = new Pane();
		root.getChildren().add(layer);
		loadGame();

		gameLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (map.isOver()) {
					// System.out.println("Over.");
				} else {
					// System.out.println("not Over.");
					map.processInput(input, now);
					map.update();
				}

			}
		};
		gameLoop.start();

	};

	public void displayInfo() {
	}

	public void updateDisplay() {
	}

    public void loadGame() {
		layer.setPrefSize(650, 800);
		layer.setLayoutX(0);
		layer.setLayoutY(0);
		// layer.setStyle("-fx-background-color: PapayaWhip ;");

		input = new InputManager(scene);
		input.addListeners();

		Human[] players = new Human[Settings.NB_HUMANS];
		ArrayList<WeaponType> wp = new ArrayList<WeaponType>();
		wp.add(WeaponType.GLOCK);

		for (int i=0; i < Settings.NB_HUMANS; i++) players[i] = null;
		players[0] = new Human(Settings.ID_HUMAN0, layer, Settings.COLOR_HUMAN0, 100, 100, 100, 1, wp);
		// players[1] = new Human(Settings.ID_HUMAN1, layer, Settings.COLOR_HUMAN1, 200, 100, 100, 1, wp);
		// players[2] = new Human(Settings.ID_HUMAN2, layer, Settings.COLOR_HUMAN2, 100, 200, 100, 1, wp);
		// players[3] = new Human(Settings.ID_HUMAN3, layer, Settings.COLOR_HUMAN3, 200, 200, 100, 1, wp);

		this.map = new Map(null, players, 0);
    }

	public static void main(String[] args) {
		launch(args);
	}

}
