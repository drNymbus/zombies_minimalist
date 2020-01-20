package model.item;

import model.drawable.Sprite;
import model.util.*;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


/*
 * Ennemy character (Zombies)
 * Some methods are the same as Human class
 */
public class Zombie extends Sprite {
    private int id;
    private int lp;

    private String state;
    private int dmg;
    private int fire_rate;
    private int actual_fr;
    private int spd;
    private int actual_spd;

    private int dx, dy;

    public Zombie(Pane layer, Color c, int x, int y, int id, ZombieType zombie) {
        super(layer, c, x, y, zombie.getWidth(), zombie.getHeight());
        this.id = id;
        this.lp = zombie.getLifePoint();

        this.state = "SHOOT";
        this.dmg = zombie.getDamage();
        this.fire_rate = zombie.getFireRate();
        this.actual_fr = this.fire_rate;

        this.spd = zombie.getSpeed();
    }

    public int getDamage() {
        int dmg = 0;
        if (this.state == "SHOOT") {
            this.actual_fr = 0;
            this.state = "FIRE_RATE";
            dmg = this.dmg;
        } else if (this.state == "FIRE_RATE") {
            if (this.actual_fr >= 100) {
                this.state = "SHOOT";
            } else {
                this.actual_fr += this.fire_rate;
            }
        }
        return dmg;
    }

    public int directionX() { return this.dx; }
    public int directionY() { return this.dy; }
    public void changeDirection(Human[] humans) {
        Human target = humans[0];
        double min = this.distance(target);
        for (int i=1; i < humans.length; i++) {
            Human h = humans[i];
            if (min > this.distance(h)) {
                target = humans[i];
                min = this.distance(h);
            }
        }

        this.dx = (this.getX() > target.getX()) ? -1 : 1;
        this.dy = (this.getY() > target.getY()) ? -1 : 1;
    }

    public void move() {
        this.setX(this.getX() + this.dx * this.spd);
        this.setY(this.getY() + this.dy * this.spd);
    }
}
