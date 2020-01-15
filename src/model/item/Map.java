package model.item;

import game.*;
import model.util.*;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.layout.Pane;


public class Map {
    private Pane layer;
    private boolean pause;
    private String filename;
    private String state;

    private Sprite[][] background;
    private ArrayList<Bullet> bullets;

    private int difficulty;
    private int wave_nb;
    private Wave wave;

    private int player_id; // Settings.ID_HUMAN(i)
    private Human[] humans; // Human player => humans[player_id]

    // loading on a default map (hardcoded)
    public Map(Sprite[][] bg, Human[] players, int difficulty) {
        this.pause = false;
        this.filename = "NONE";
        this.state = "NONE";

        this.background = bg;
        this.humans = players;
        this.difficulty = difficulty;

        this.wave_nb = 0;
        this.wave = new Wave(this.difficulty);
    }

    // load map from file (TBD)
    // public Map(String filename, int difficulty) {
    //     Map(null, null, difficulty);
    //     this.filename = filename;
    //     this.loadMap();
    // }

    // TBD
    public void loadMap() {}

    public void processInput(InputManager in, long now) {
        if (in.isExit()) {
            Platform.exit();
            System.exit(0);
        } else if (in.isPause()) {
            this.pause = !this.pause;
            System.out.println(this.pause);
        }

        if (in.isFire()) {
            // Human h = this.humans[this.player_id];
            // h.fire()
            System.out.println("FIRE (primary)");
        } else if (in.isFireSecond()) {
            System.out.println("FIRE (secondary)");
        }

        Human h = this.humans[this.player_id];
        if (in.isUP()) {
            h.changeDirection(h.directionX(), -1);
        } else if (in.isDOWN()) {
            h.changeDirection(h.directionX(), 1);
        } else {
            h.changeDirection(h.directionX(), 0);
        }

        if (in.isLEFT()) {
            h.changeDirection(-1, h.directionY());
        } else if (in.isRIGHT()) {
            h.changeDirection(1, h.directionY());
        } else {
            h.changeDirection(0, h.directionY());
        }

        int angle = h.computeAngle(in.getMouseX(), in.getMouseY());
        System.out.println("x: " + (in.getMouseX() - h.getX()));
        System.out.println("y: " + (in.getMouseY() - h.getY()));
        System.out.println("angle: " + h.getAngle());
        h.rotateHuman(angle);

    }

    public void update() {
        for (Human h : this.humans) {
            h.move();
        }
    }

    public boolean isOver() {
        for (Human h : this.humans) {
            if (h != null && h.isAlive())
                return false;
        }
        this.state = "OVER";
        return true;
    }

    public boolean onPause() { return this.pause; }

}
