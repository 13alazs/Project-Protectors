package com.mycompany.protectors;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*
    Project Name: Project Protectors
    Members: Árvai Balázs, Bíró Benjámin, Fodor Ádám, Zászlós Dorottya Beáta
    @author Bíró Benjámin
*/

public class Player extends Sprite{
    
    private final String name;
    
    private double currExperience;          //If used.
    private double maxExperience;           //If used.
    private int level;                      //If used.
    
    private double maxHealth;
    private double currHealth;              //Current health points.
    
    private final String resourceName;      //If we plan on using anything other than mana.
    private double maxResource;             //Mana or something.
    private double currResource;            //Current resource points.
    
    private double armor;
    //private ArrayList<???> resistances;   //Now idea how resistances should be done, if we even need it..
    
    private double attackDamage;            //Only used if Basic Attack is not a seperate ability.
    private String attackType;              //e.g.: Fire, Ice, Physical...
    
    private ArrayList<Ability> abilities;   //Skills the chracter has.
    
    private ClassType type;                 //Waiting for class ideas.
    
    /**
     * Constructor that calls the Sprite parent class' constructor.
     * @param x         The x coord for the image.
     * @param y         The y coord for the image.
     * @param width     The width of the image.
     * @param height    The height of the image.
     * @param image     The sprite for the image.
     */
    public Player(int x, int y, int width, int height, BufferedImage image, String name, String resourceName){
        super(x, y, width, height, image);
        this.name = name;
        this.level = 1;
        this.maxHealth = 100;             //If it needs to be set.
        this.currHealth = maxHealth;      //If it needs to be set.
        this.resourceName = resourceName;
    }

    /**
     * Player casts the choosen ability, if has enough resource. Then the players
     * resource points decreases as much as the ability costs.
     * @param ab        The ability to cast.
     */
    public void castAbility(Ability ab){
        if(currResource >= ab.getCost()){
            setCurrResource(getCurrResource() - ab.getCost());
            //...
        }
    }
    
    /**
     * Returns the nam of the character.
     * @return          Name of the character.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the current experience points the character has.
     * @return          Current experience points.
     */
    public double getCurrExperience() {
        return currExperience;
    }
    
    /**
     * Returns the max experience points the character can acquire this level.
     * @return          Max ecperience points.
     */
    public double getMaxExperience() {
        return maxExperience;
    }
    
    /**
     * Returns the level of the character.
     * @return          Level of the character.
     */
    public int getLevel() {
        return level;
    }
    
    /**
     * Returns the max health points the character has.
     * @return          Max health points.
     */
    public double getMaxHealth() {
        return maxHealth;
    }
    
    /**
     * Returns the current health points the character has.
     * @return          Current health points.
     */
    public double getCurrHealth() {
        return currHealth;
    }

    /**
     * Returns the name of the resource the player has.
     * @return          Name of the resource (e.g.: Mana).
     */
    public String getResourceName() {
        return resourceName;
    }
    
    /**
     * Returns the max resource points the character has.
     * @return          Max resource points.
     */
    public double getMaxResource() {
        return maxResource;
    }
    
    /**
     * Returns the current resource points the character has.
     * @return          Current resource points.
     */
    public double getCurrResource() {
        return currResource;
    }
    
    /**
     * Returns the armor rating the character has.
     * @return          Armor rating.
     */
    public double getArmor() {
        return armor;
    }
    
    /**
     * Returns how much damage the character deals.
     * @return          Attack rating.
     */
    public double getAttackDamage() {
        return attackDamage;
    }
    
    /**
     * Returns the attack type the character has.
     * @return          Attack type.
     */
    public String getAttackType() {
        return attackType;
    }
    
    /**
     * Returns the abilities the character has.
     * @return          Abilities.
     */
    public ArrayList<Ability> getAbilities() {
        return abilities;
    }
    
    /**
     * Returns the class type of the character.
     * @return          Class type.
     */
    public ClassType getClassType(){
        return type;
    }
    
    
    /**
     * Sets the current experience the character has.
     * @param currExperience    Current experience points.
     */
    public void setCurrExperience(double currExperience) {
        this.currExperience = currExperience;
    }
    
    /**
     * Sets the max experience needed for the next level.
     * @param maxExperience     Max experience.
     */
    public void setMaxExperience(double maxExperience) {
        this.maxExperience = maxExperience;
    }
    
    /**
     * Sets the characters level.
     * @param level     Level.
     */
    public void setLevel(int level) {
        this.level = level;
    }
    
    /**
     * Sets the max health points the character has.
     * @param maxHealth     Max health points.
     */
    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }
    
    /**
     * Sets the current health points of the character.
     * @param currHealth    Current health points.
     */
    public void setCurrHealth(double currHealth) {
        this.currHealth = currHealth;
    }
    
    /**
     * Sets the max resource points the character has.
     * @param maxResource   Max resource points.
     */
    public void setMaxResource(double maxResource) {
        this.maxResource = maxResource;
    }
    
    /**
     * Sets the current resource points the character has.
     * @param currResource  Current resource points.
     */
    public void setCurrResource(double currResource) {
        this.currResource = currResource;
    }
    
    /**
     * Sets the armor rating the character has.
     * @param armor     Armor rating.
     */
    public void setArmor(double armor) {
        this.armor = armor;
    }
    
    /**
     * Sets the how much damage the character deals.
     * @param attackDamage  Attack rating.
     */
    public void setAttackDamage(double attackDamage) {
        this.attackDamage = attackDamage;
    }
    
    /**
     * Sets the attack type of the character.
     * @param attackType    Attack type.
     */
    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }
    
    /**
     * Sets the class type of the character.
     * @param classType     Class type.
     */
    public void setClassType(ClassType type) {
        this.type = type;
    }
    
    
    /**
     * Adds an ability to the ability list of the character.
     * @param ab            Ability to add.
     */
    public void addAbility(Ability ab){
        abilities.add(ab);
    }
    
    /**
     * Removes an ability from the ability list of the character.
     * @param ab            Ability to remove.
     */
    public void remAbility(Ability ab){
        abilities.remove(ab);
    }
}
