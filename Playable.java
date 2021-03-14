package com.mycompany.protectors;

import java.awt.image.BufferedImage;

/*
    Project Name: Project Protectors
    Members: Árvai Balázs, Bíró Benjámin, Fodor Ádám, Zászlós Dorottya Beáta
    @author Bíró Benjámin
*/
public class Playable extends Character{
    
    /**
     * Constructor that calls the Character's parent class' constructor.
     * @param x             The x coord for the image.
     * @param y             The y coord for the image.
     * @param width         The width of the image.
     * @param height        The height of the image.
     * @param image         The sprite for the image.
     * @param name          Name of the character.
     * @param level         Level the character starts on.
     * @param maxHealth     The max health points the character starts with.
     * @param resourceName  Resource the character uses.
     */
    public Playable(int x, int y, int width, int height, BufferedImage image, String name, String resourceName, int level, double maxHealth){
        super(x, y, width, height, image, name, resourceName);
        this.setLevel(1);
        this.setMaxHealth(100);             //If it needs to be set.
        this.setCurrHealth(maxHealth);      //If it needs to be set.
    }
}
