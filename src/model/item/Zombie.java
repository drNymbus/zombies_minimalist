package model.item;

import draw.Movable;
import model.util.*;

import javafx.scene.layout.Pane;


/*
 * Ennemy character (Zombies)
 * Some methods are the same as Human class
 */
public class Zombie extends Movable {
    private int id;
    private int lp;

    private String state;
    private int dmg;
    private int fire_rate;
    private int actual_fr;
    private int xp;

    public Zombie(Pane layer, double x, double y, int id, ZombieType zombie) {
        super(layer, zombie.getColor(), x, y, zombie.getWidth(), zombie.getHeight(), zombie.getSpeed());
        this.id = id;
        this.lp = zombie.getLifePoint();

        this.state = "SHOOT";
        this.dmg = zombie.getDamage();
        this.fire_rate = zombie.getFireRate();
        this.actual_fr = this.fire_rate;

        this.xp = zombie.getExperience();
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

    // public void changeDirection(Human[] humans) {
    //     Human target = humans[0];
    //     double min = this.distance(target);
    //     for (int i=1; i < humans.length; i++) {
    //         Human h = humans[i];
    //         if (min > this.distance(h)) {
    //             target = humans[i];
    //             min = this.distance(h);
    //         }
    //     }
    //
    //     this.dx = (this.getX() > target.getX()) ? -1 : 1;
    //     this.dy = (this.getY() > target.getY()) ? -1 : 1;
    // }
}
