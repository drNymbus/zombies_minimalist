package model.util;

import model.item.*;

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
        this.wave_nb = 0;
        this.difficulty = difficulty;

        this.nb_zombies = 0;
        this.zombies = new ArrayList<Zombie>();
    }

    public ArrayList<Zombie> getZombies() { return this.zombies; }

}
