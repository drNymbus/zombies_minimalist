package model.util;

import javafx.scene.paint.Color;

/*
 * Enum to init Weapon class
 */
public enum WeaponType {
    // WEAPON_NAME (id, name, dmg, fire_rate, reload_time, velocity, range, magazine, ammo_max, color, width, height, b_color, b_width, b_height)
    // FISTS (2, "FISTS", 5, 20, 1, -1, )
    M4A1 (0, "M4A1", 15, 60, 50, 20, 1000, 30, 300, Color.BLACK, 7, 10, Color.ORANGE, 3, 5),
    GLOCK (1, "Glock 17", 55, 40, 30, 18, 500, 20, 200, Color.BLACK, 5, 7, Color.ORANGE, 2, 5);

    private int id;
    private String name;

    private int dmg;
    private int fire_rate;
    private int reload_time;
    private double velocity;
    private int range;
    private int magazine;
    private int ammo;

    private Color color;
    private int width, height;

    private Color bullet_color;
    private int bullet_width, bullet_height;

    WeaponType(int id, String name, int dmg, int fire_rate, int reload_time, double vel, int range, int mag, int ammo, Color c, int w, int h, Color bc, int bw, int bh) {
        this.id = id; this.name = name;
        this.dmg = dmg; this.magazine = mag; this.ammo = ammo;
        this.fire_rate = fire_rate; this.reload_time = reload_time;
        this.velocity = vel; this.range = range;
        this.color = c; this.width = w; this.height = h;
        this.bullet_color = bc; this.bullet_width = bw; this.bullet_height = bh;
    }

    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public int getDamage() { return this.dmg; }
    public int getMagazine() { return this.magazine; }
    public int getAmmoMax() { return this.ammo; }

    public int getFireRate() { return this.fire_rate; }
    public int getReloadTime() { return this.reload_time; }
    public double getVelocity() { return this.velocity; }
    public int getRange() { return this.range; }

    public Color getColor() { return this.color; }
    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }

    public Color getBulletColor() { return this.bullet_color; }
    public int getBulletWidth() { return this.bullet_width; }
    public int getBulletHeight() { return this.bullet_height; }

}
