package io.github._13alazs;

import java.awt.Image;
import java.util.ArrayList;

/*
    Project Name: Project Protectors
    Members: Árvai Balázs, Bíró Benjámin, Fodor Ádám, Zászlós Dorottya Beáta
*/

public class Mission {
    protected String name;
    protected ArrayList<Character> Enemies;
    protected Image background;

    public Mission() {
        Enemies = new ArrayList();
    }

    public boolean Encounter1() {
        Enemies = new ArrayList();
        return false;
    }

    public boolean Encounter2() {
        Enemies = new ArrayList();
        return false;
    }

    public boolean Encounter3() {
        Enemies = new ArrayList();
        return false;
    }

    public boolean Encounter4() {
        Enemies = new ArrayList();
        return false;
    }

    public boolean Encounter5() {
        Enemies = new ArrayList();
        return false;
    }

    public boolean Encounter6() {
        Enemies = new ArrayList();
        return false;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Character> getEnemies() {
        return Enemies;
    }

    public Image getBackground() {
        return background;
    }
}
