package model.item;

import draw.Position;
import draw.Sprite;
import game.*;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.PerspectiveCamera;

public class Map {
    private PerspectiveCamera camera;

    private boolean pause;
    private String filename;
    private String state;

    private int width, height;

    private Sprite[] background;
    private Prop[] props;
    private ArrayList<Bullet> bullets;

    private int difficulty;
    private int wave_nb;
    private Wave wave;

    private int player_id; // Settings.ID_HUMAN(i)
    private Human[] humans; // Human player => humans[player_id]

    // loading on a default map (hardcoded)
    public Map(PerspectiveCamera cam, int w, int h, Sprite[] bg, Prop[] props, Human[] players, int id, int difficulty) {
        this.camera = cam;
        this.pause = false;
        this.filename = "NONE";
        this.state = "NONE";

        this.width = w;
        this.height = h;

        this.background = bg;
        // this.background = new Sprite[bg.length];
        // for (int i=0; i < bg.length; i++) {
        //     this.background[i] = bg[i];
        // }

        this.props = props;
        // this.props = new Sprite[props.length];
        // for (int i=0; i < props.length; i++) {
        //     this.props[i] = props[i];
        // }

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

    public void moveCamera(double x, double y) {
        this.camera.setTranslateX(x);
        this.camera.setTranslateZ(y);
    }

    public Human getPlayer() { return this.humans[this.player_id]; }

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
                double angle_rad = Math.toRadians(angle);
        		angle_rad -= Math.PI/2.0;
                double bdx = Math.cos(angle_rad);
                double bdy = Math.sin(angle_rad);

                b.changeDirection(bdx, bdy);
                this.bullets.add(b);
            }
        } else if (in.isFireSecond()) {
            System.out.println("FIRE (secondary)");
        } else if (in.isReload()) {
            h.getWeapon().reload();
        }

        double cx = 0, cy = 0;
        if (in.getMouseX() <= 0) {
            cx = -1 * Settings.SCROLL_SPEED;
            in.setMouseX(0);
        } else if (in.getMouseX() >= Settings.SCENE_WIDTH-1) {
            cx = 1 * Settings.SCROLL_SPEED;
            in.setMouseX(Settings.SCENE_WIDTH - 1);
        }

        if (in.getMouseY() <= 0) {
            cy = -1 * Settings.SCROLL_SPEED;
            in.setMouseY(0);
        } else if (in.getMouseY() >= Settings.SCENE_HEIGHT-1) {
            cy = 1 * Settings.SCROLL_SPEED;
            in.setMouseY(Settings.SCENE_HEIGHT - 1);
        }
        this.moveCamera(cx, cy);

    }

    public void update() {
        for (Human h : this.humans) {
            if (h != null) {
                Position new_pos = h.getNewPos();
                Sprite tmp = new Sprite(h.getLayer(), h.getColor(), new_pos.getX(), new_pos.getY(), h.getWidth(), h.getHeight());
                tmp.removeFromLayer();

                boolean move = true;
                ArrayList<Zombie> zombies = this.wave.getZombies();
                int max = Math.max(zombies.size(), this.props.length);
                for (int i=0; i < max; i++) {
                    if (!move) break;

                    if (i < zombies.size()) {
                        if (tmp.isIn(zombies.get(i))) move = false;
                    }

                    if (i < this.props.length) {
                        if (tmp.isIn(this.props[i])) move = false;
                    }

                }

                if (move) {
                    h.move();
                } else {

                }
                h.getWeapon().update();
            }
        }

        /* BULLET UPDATE */
        for (int i=0; i < this.bullets.size(); i++) {
            Bullet b = this.bullets.get(i);
            b.move();
            Position pos = b.getPosition();
            if ((pos.getX() < 0 || pos.getX() > this.width) && (pos.getY() < 0 || pos.getY() > this.height)) {
                b.removeFromLayer();
                this.bullets.remove(i);
            }

            for (int pi=0; pi < this.props.length; pi++) {
                Prop p = this.props[pi];
                if (b.isIn(p)) {
                    b.removeFromLayer();
                    this.bullets.remove(pi);
                }
            }
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
