package game;

// import draw.Position;
// import draw.Sprite;
import draw.*;
import model.item.*;
import model.util.*;

import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.animation.AnimationTimer;

import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.PerspectiveCamera;

import javafx.scene.text.Text;
// import javafx.scene.control.Label;
import javafx.scene.text.Font;


import javafx.scene.paint.Color;

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
	private ArrayList<TextCustom> texts = new ArrayList<TextCustom>();
	// private ArrayList<Label> labels = new ArrayList<Label>();
	// private VBox tmp;
	// private VBox

	Group root;

	@Override
	public void start(Stage stage){
		root = new Group();
		scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
		scene.setFill(Color.GREY);
		stage.setScene(scene);
		stage.setResizable(true);
		stage.show();
		stage.setTitle(Settings.PROJECT_NAME);
		layer = new Pane();
		root.getChildren().add(layer);
		loadGame();
		initHUD();

		gameLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (map.isOver()) {
					// System.out.println("Over.");
				} else {
					// System.out.println("not Over.");
					map.processInput(input, now);
					map.update();
					updateHUD();
				}
			}
		};
		gameLoop.start();

	};

	public void initHUD() {
		Human h = this.map.getPlayer();
		Font font = new Font("../../assets/font/SF-Pro-Display-Regular.ttf", 20);
		TextCustom text;

		// LPs
		text = new TextCustom("" + h.getLifePoints() + "/" + h.getMaxLifePoints(), font);
		text.setPosition(0, Settings.SCENE_HEIGHT - text.getHeight());
		this.texts.add(text);
		this.layer.getChildren().add(text.getText());

		// Current Ammo
		text = new TextCustom("" + h.getWeapon().getMag() + "/" + h.getWeapon().getMaxMag(), font);
		text.setPosition(Settings.SCENE_WIDTH - text.getWidth()*2.5, Settings.SCENE_HEIGHT - text.getHeight());
		this.texts.add(text);
		this.layer.getChildren().add(text.getText());

		// Ammo
		text = new TextCustom("" + h.getWeapon().getAmmo(), font);
		text.setPosition(Settings.SCENE_WIDTH - text.getWidth()*2.5, Settings.SCENE_HEIGHT - this.texts.get(1).getHeight() - text.getHeight());
		this.texts.add(text);
		this.layer.getChildren().add(text.getText());

	}

	public void updateHUD() {
		Human h = this.map.getPlayer();
		this.texts.get(0).setText("" + h.getLifePoints() + "/" + h.getMaxLifePoints());
		this.texts.get(1).setText("" + h.getWeapon().getMag() + "/" + h.getWeapon().getMaxMag());
		this.texts.get(2).setText("" + h.getWeapon().getAmmo());
	}

    public void loadGame() {
		PerspectiveCamera cam = new PerspectiveCamera(false);
		// cam.setTranslateX(-20);
        // cam.setTranslateY(0);
        // cam.setTranslateZ(-50);
		scene.setCamera(cam);
		Position map_size = new Position(Settings.SCENE_WIDTH*2, Settings.SCENE_HEIGHT*2);

		layer.setPrefSize(map_size.getX(), map_size.getY());
		layer.setLayoutX(0);
		layer.setLayoutY(0);
		// layer.setStyle("-fx-background-color: PapayaWhip ;");

		input = new InputManager(scene);
		input.addListeners();

		Sprite[] bg = new Sprite[4];
		bg[0] = new Sprite(this.layer, Settings.COLOR_DIRT, 0, 0, Settings.SCENE_WIDTH/2, Settings.SCENE_HEIGHT/2);
		bg[1] = new Sprite(this.layer, Settings.COLOR_GRASS, (Settings.SCENE_WIDTH-1)/2, 0, Settings.SCENE_WIDTH/2, Settings.SCENE_HEIGHT/2);
		bg[2] = new Sprite(this.layer, Settings.COLOR_CEMENT, 0, (Settings.SCENE_HEIGHT-1)/2, Settings.SCENE_WIDTH/2, Settings.SCENE_HEIGHT/2);
		bg[3] = new Sprite(this.layer, Settings.COLOR_DIRT, (Settings.SCENE_WIDTH-1)/2, Settings.SCENE_HEIGHT/2, Settings.SCENE_WIDTH/2, Settings.SCENE_HEIGHT/2);

		Prop[] props = new Prop[3];
		props[0] = new Prop(this.layer, 120, 200, 0, PropType.NONE);
		props[1] = new Prop(this.layer, 600, 300, 1, PropType.NONE);
		props[2] = new Prop(this.layer, 458, 850, 2, PropType.NONE);

		Human[] players = new Human[Settings.NB_HUMANS];
		ArrayList<WeaponType> wp = new ArrayList<WeaponType>();
		wp.add(WeaponType.GLOCK);

		for (int i=0; i < Settings.NB_HUMANS; i++) players[i] = null;
		players[0] = new Human(Settings.ID_HUMAN0, this.layer, Settings.COLOR_HUMAN0, 100, 100, 100, 1, wp);
		// players[1] = new Human(Settings.ID_HUMAN1, layer, Settings.COLOR_HUMAN1, 200, 100, 100, 1, wp);
		// players[2] = new Human(Settings.ID_HUMAN2, layer, Settings.COLOR_HUMAN2, 100, 200, 100, 1, wp);
		// players[3] = new Human(Settings.ID_HUMAN3, layer, Settings.COLOR_HUMAN3, 200, 200, 100, 1, wp);


		this.map = new Map(cam, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT, bg, props, players, Settings.ID_HUMAN0, 0);
    }

	public static void main(String[] args) {
		launch(args);
	}

}
