package model.item;

import model.util.*;
import game.*;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Human extends Sprite {
    private int id;

    private int lvl;
    private int xp;
    private int actualXP;
    private int lp;
    private int actualLP;
    private int spd;

    private ArrayList<Weapon> weapons;
    private int actual_weapon;
    private Weapon primary;
    private Weapon secondary;
    private int[] grenades;

    private int dx, dy;

    public Human(int id, Pane layer, Color c, int x, int y, int lp, int spd, ArrayList<WeaponType> weapons) {
        super(layer, c, x, y, Settings.SIZE_HUMAN, Settings.SIZE_HUMAN);
        this.id = id;

        this.lvl = 0; this.levelUp();
        this.xp = 200;
        this.actualXP = 0;

        this.lp = lp;
        this.actualLP = this.lp;
        this.spd = spd;

        this.actual_weapon = 0;
        this.weapons = new ArrayList<Weapon>();

        for (int i=0; i < weapons.size(); i++) {
            WeaponType wt = weapons.get(i);
            Weapon w = new Weapon(this.getLayer(), this.getX(), this.getY() - this.getHeight()/2, i, this.id, wt);
            this.weapons.add(w);
        }

        this.grenades = new int[4];

        this.dx = 0; this.dy = 0;
    }

    public int directionX() { return this.dx; }
    public int directionY() { return this.dy; }
    public void changeDirection(int x, int y) { this.dx = x; this.dy = y; }

    public void move() {
        this.setX(this.getX() + this.dx * this.spd);
        this.setY(this.getY() + this.dy * this.spd);
        Weapon w = this.weapons.get(this.actual_weapon);
        w.setX(this.getX());
        w.setY(this.getY() - this.getHeight()/2);
    }

    public void rotateHuman(int angle) {
        this.setRotate(angle);
        this.weapons.get(this.actual_weapon).rotate(angle, this.getX(), this.getY());
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
        this.actualLP += this.lp/33;
        if (this.actualLP > this.lp) this.actualLP = this.lp;

        this.spd++;
    }

    public void heal(int n) {
        this.actualLP += n;
        if (this.actualLP > this.lp) this.actualLP = this.lp;
    }

    public boolean isAlive() { return (this.actualLP > 0); }

    public Bullet fire() { return this.weapons.get(this.actual_weapon).fire(); }
    public void reload() { this.weapons.get(this.actual_weapon).reload(); }

}
