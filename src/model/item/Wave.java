package model.item;

import model.draw.*;
import model.util.*;

import java.util.Random;
import java.util.ArrayList;

import javafx.scene.layout.Pane;

/*
 * Wave class, handles Zombie spawns and diffculty
 */
public class Wave {
    private int wave_nb;
    private int difficulty;

    private int nb_zombies;
    private ArrayList<Zombie> zombies;

    public Wave(int difficulty) {
        this.wave_nb = 1;
        this.difficulty = difficulty;

        this.nb_zombies = difficulty * 10;
        this.zombies = new ArrayList<Zombie>();
    }

    public void spawnZombies(Pane layer, Position[] spawn_points) {
        Random rnd = new Random();
        int max = 20;
        while (this.nb_zombies > 0 && max > 0) {
            Position pos = spawn_points[rnd.nextInt(spawn_points.length)];
            ZombieType zt = ZombieType.ZOMBIE_EZ;
            // int i_rnd = rnd.nexInt(100);

            Zombie z = new Zombie(layer, pos.getX(), pos.getY(), this.nb_zombies, zt);
            max--;
            this.nb_zombies--;
        }
    }

    public ArrayList<Zombie> getZombies() { return this.zombies; }

    public void upgradeWave() {
        if (wave_nb%5 == 0) this.difficulty++;
        this.nb_zombies = this.difficulty * 10 + this.wave_nb;
    }

}
