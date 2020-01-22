package model.item;

import game.*;
import model.draw.*;
import model.util.*;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.layout.Pane;


public class Map {
    private Pane layer;
    private boolean pause;
    private String filename;
    private String state;

    private int width, height;

    private Sprite[][] background;
    private ArrayList<Bullet> bullets;

    private int difficulty;
    private int wave_nb;
    private Wave wave;

    private int player_id; // Settings.ID_HUMAN(i)
    private Human[] humans; // Human player => humans[player_id]

    // loading on a default map (hardcoded)
    public Map(Sprite[][] bg, Human[] players, int id, int difficulty) {
        this.pause = false;
        this.filename = "NONE";
        this.state = "NONE";

        this.background = bg;
        this.bullets = new ArrayList<Bullet>();

        this.difficulty = difficulty;
        this.wave_nb = 0;
        this.wave = new Wave(this.difficulty);

        this.player_id = id;
        this.humans = players;
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

        Human h = this.humans[this.player_id];

        int dx = 0, dy = 0;
        if (in.isUP()) {
            dy = -1;
        } else if (in.isDOWN()) {
            dy = 1;
        }

        if (in.isLEFT()) {
            dx = -1;
        } else if (in.isRIGHT()) {
            dx = 1;
        }
        h.changeDirection(dx, dy);
        // System.out.println(h.directionX() + "," + h.directionY() + " | " + h.getX() + "," + h.getY());

        double angle = h.computeAngle(in.getMouseX(), in.getMouseY());
        h.rotateHuman(angle);

        if (in.isFire()) {
            Bullet b = h.getWeapon().fire();
            if (b != null) {
                double bdx = Math.cos(Math.toRadians(angle));
                double bdy = Math.sin(Math.toRadians(angle));
                b.changeDirection(bdx, bdy);
                this.bullets.add(b);
            }
        } else if (in.isFireSecond()) {
            System.out.println("FIRE (secondary)");
        } else if (in.isReload()) {
            h.getWeapon().reload();
        }

    }

    public void update() {
        for (Human h : this.humans) {
            if (h != null) {
                // h.move();
                // System.out.println(h.directionX() + "," + h.directionY() + " | " + h.getX() + "," + h.getY());
                h.moveHuman();
                h.getWeapon().update();
                // System.out.println(h.getPosition().toString());
                // h.rotateHuman(h.getAngle() + 1);
                // System.out.println(h.getAngle());
            }
        }

        for (int i=0; i < this.bullets.size(); i++) {
            Bullet b = this.bullets.get(i);
            b.move();
            System.out.println(b.directionX() + "," + b.directionY());
            Position pos = b.getPosition();
            if ((pos.getX() < 0 || pos.getX() > this.width) && (pos.getY() < 0 || pos.getY() > this.height)) this.bullets.remove(i);
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
