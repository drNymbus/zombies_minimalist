package model.item;

import model.draw.*;
import model.util.*;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/*
 * Weapon item (extends Sprite)
 */
public class Weapon extends Sprite {
    private int id;
    private int weapon_id;
    private String name;
    private int owner;
    private int lvl;
    private int xp;
    private int actualXP;

    private String state;

    private int dmg;

    private int fire_rate;
    private int actual_fr;

    private int reload_time;
    private int actual_rt;

    private int magazine;
    private int actual_mag;

    private int ammo;
    private int actual_ammo;

    private Bullet blueprint;

    public Weapon(Pane layer, double x, double y, int id, int owner, WeaponType weapon) {
        super(layer, weapon.getColor(), x, y, weapon.getWidth(), weapon.getHeight());
        this.id = id;
        this.owner = owner;
        this.lvl = 0;
        this.xp = 200;
        this.actualXP = 0;

        this.weapon_id = weapon.getId();
        this.name = weapon.getName();

        this.state = "SHOOT";
        this.dmg = weapon.getDamage();

        this.fire_rate = weapon.getFireRate();
        this.actual_fr = this.fire_rate;

        this.reload_time = weapon.getReloadTime();
        this.actual_rt = this.reload_time;

        this.magazine = weapon.getMagazine();
        this.actual_mag = this.magazine;

        this.blueprint = new Bullet(layer, Color.YELLOW, x, y, weapon.getWidth(), weapon.getHeight(),
                                    owner, weapon.getDamage(), weapon.getVelocity(), weapon.getRange());
        this.blueprint.removeFromLayer();
    }

    public Bullet fire() {
        Bullet b = null;
        if (this.state == "SHOOT") {
            if (this.actual_mag > 0) {
                this.actual_mag--;
                this.actual_fr = 0;
                this.state = "FIRE_RATE";
                b = new Bullet(this.blueprint);
            } else {
                this.state = "RELOAD";
            }
        } else if (this.state == "FIRE_RATE") {
            if (this.actual_fr >= 100) {
                this.state = "SHOOT";
            } else {
                this.actual_fr += this.fire_rate;
            }
        }
        return b;
    }

    public void reload() {
        this.state = "RELOAD";
        this.actual_rt = this.reload_time;
    }

    public void update() {
        // System.out.println(this.state);

        this.blueprint.setX(this.getX());
        this.blueprint.setY(this.getY());
        this.blueprint.setRotate(this.getAngle());

        if (this.state == "RELOAD") {
            if (this.actual_rt > 0) {
                this.actual_rt--;
            } else {
                this.state = "SHOOT";

                while (this.actual_ammo > 0 && this.actual_mag < this.magazine) {
                    this.actual_ammo--;
                    this.actual_mag++;
                }
                this.actual_fr = this.fire_rate;
            }
        } else if (this.state == "FIRE_RATE") {
            if (this.actual_fr >= this.fire_rate) {
                this.state = "SHOOT";
            } else {
                this.actual_fr++;
            }
        }
    }

    public void levelUp() {
        this.lvl++;
        this.magazine += (int) this.magazine/10;
        this.fire_rate += this.fire_rate*5/100;
        this.reload_time -= reload_time*5/100;
    }
}
