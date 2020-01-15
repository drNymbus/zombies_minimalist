package model.util;

import javafx.scene.paint.Color;

public enum WeaponType {
    // WEAPON_NAME (id, name, dmg, fire_rate, reload_time, velocity, range, magazine, color, width, height)
    // FISTS (2, "FISTS", 5, 20, 1, -1, )
    M4A1 (0, "M4A1", 15, 60, 50, 20, 1000, 30, Color.YELLOW, 7, 10),
    GLOCK (1, "Glock 17", 55, 40, 30, 18, 500, 20, Color.YELLOW, 5, 7);

    private int id;
    private String name;

    private int dmg;
    private int fire_rate;
    private int reload_time;
    private int velocity;
    private int range;
    private int magazine;

    private Color color;
    private int width, height;

    WeaponType(int id, String name, int dmg, int fire_rate, int reload_time, int vel, int range, int mag, Color c, int w, int h) {
        this.id = id; this.name = name;
        this.dmg = dmg; this.magazine = mag;
        this.fire_rate = fire_rate; this.reload_time = reload_time;
        this.velocity = vel; this.range = range;
        this.color = c; this.width = w; this.height = h;
    }

    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public int getDamage() { return this.dmg; }
    public int getMagazine() { return this.magazine; }

    public int getFireRate() { return this.fire_rate; }
    public int getReloadTime() { return this.reload_time; }
    public int getVelocity() { return this.velocity; }
    public int getRange() { return this.range; }

    public Color getColor() { return this.color; }
    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }

}
