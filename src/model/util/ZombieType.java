package model.util;

import game.*;

import javafx.scene.paint.Color;

/*
 * Enum to init Zombie class
 */
public enum ZombieType {
    // ZombieType (name, lp, dmg, fire_rate, spd, xp, color, width, height)
    ZOMBIE_EZ ("RUNNER", 100, 5, 50, 3, 0, Settings.COLOR_ZOMBIE, Settings.SIZE_ZOMBIE, Settings.SIZE_ZOMBIE),
    ZOMBIE_NRML ("WALKER", 300, 20, 50, 3, 0, Settings.COLOR_ZOMBIE, Settings.SIZE_ZOMBIE, Settings.SIZE_ZOMBIE),
    ZOMBIE_DFCLT ("HEAVY", 700, 45, 10, 3, 0, Settings.COLOR_ZOMBIE, Settings.SIZE_ZOMBIE, Settings.SIZE_ZOMBIE);

    private String name;
    private int lp;
    private int dmg;
    private int fire_rate;
    private int spd;
    private int xp;

    private Color color;
    private int width, height;

    ZombieType(String name, int lp, int dmg, int fr, int spd, int xp, Color c, int w, int h) {
        this.name = name;
        this.lp = lp;
        this.dmg = dmg;
        this.fire_rate = fr;
        this.spd = spd;
        this.xp = xp;
        this.color = c;
        this.width = w;
        this.height = h;
    }

    public int getLifePoint() { return this.lp; }
    public int getDamage() { return this.dmg; }
    public int getFireRate() { return this.fire_rate; }
    public int getSpeed() { return this.spd; }
    public int getExperience() { return this.xp; }
    public Color getColor() { return this.color; }
    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }

}
