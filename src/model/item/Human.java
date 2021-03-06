package model.item;

import draw.Movable;
import model.util.*;
import game.*;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/*
 * Playable character (extends Sprite)
 */
public class Human extends Movable {
    private int id;

    private int lvl;
    private int xp;
    private int actualXP;
    private int lp;
    private int actual_lp;

    private ArrayList<Weapon> weapons;
    private int actual_weapon;
    private Weapon primary;
    private Weapon secondary;
    private int[] grenades;

    public Human(int id, Pane layer, Color c, double x, double y, int lp, double spd, ArrayList<WeaponType> weapons) {
        super(layer, c, x, y, Settings.SIZE_HUMAN, Settings.SIZE_HUMAN, spd);
        this.id = id;

        this.lvl = 0; this.levelUp();
        this.xp = 200;
        this.actualXP = 0;

        this.lp = lp;
        this.actual_lp = this.lp;

        this.actual_weapon = 0;
        this.weapons = new ArrayList<Weapon>();

        for (int i=0; i < weapons.size(); i++) {
            WeaponType wt = weapons.get(i);
            Weapon w = new Weapon(this.getLayer(), this.getX(), this.getY(), i, this.id, wt);
            this.weapons.add(w);
        }

        this.grenades = new int[4];

    }

    public int getMaxLifePoints() { return this.lp; }
    public int getLifePoints() { return this.actual_lp;}

    public Weapon getWeapon() { return this.weapons.get(this.actual_weapon); }

    public void moveHuman() {
        this.move();
        Weapon w = this.weapons.get(this.actual_weapon);
        w.setX(w.getX() + this.directionX() * this.getSpeed());
        w.setY(w.getY() + this.directionY() * this.getSpeed());
        // w.setX(this.getX());
        // w.setY(this.getY() - this.getHeight()/2);
    }

    public void rotateHuman(double angle) {
        Weapon w = this.weapons.get(this.actual_weapon);
        w.rotate(angle, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public void kill(ZombieType zombie) {
        this.actualXP += zombie.getExperience();
    }

    public void update() {
        this.weapons.get(this.actual_weapon).update();
        if (this.actualXP > this.xp) this.levelUp();
    }

    public void levelUp() {
        this.lvl++;
        this.xp = (int) Math.pow(this.lvl, 35);
        this.actualXP = 0;

        this.lp += this.lvl * 10;
        this.actual_lp += this.lp/33;
        if (this.actual_lp > this.lp) this.actual_lp = this.lp;

        this.setSpeed(this.getSpeed() + 1);
    }

    public void heal(int n) {
        this.actual_lp += n;
        if (this.actual_lp > this.lp) this.actual_lp = this.lp;
    }

    public boolean isAlive() { return (this.actual_lp > 0); }

}
