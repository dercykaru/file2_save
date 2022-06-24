package com.company;

import java.io.Serializable;

public class GameProgress implements Serializable {
    //private static final long serialVersionUID = 01L;

    private int health;
    private int weapons;
    private int level;
    private double distance;

    public GameProgress(int health, int weapons, int level, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.level = level;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Game Progress: " +
                "health = " + health +
                "weapons = " + weapons +
                "level = " + level +
                "distance = " + distance + "___";
    }

}
