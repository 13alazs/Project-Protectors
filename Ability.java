package com.mycompany.protectors;

/*
    Project Name: Project Protectors
    Members: Árvai Balázs, Bíró Benjámin, Fodor Ádám, Zászlós Dorottya Beáta
    @author Bíró Benjámin
*/
public class Ability{
    private final String name;
    
    private final double cost;              //The resource it requires to fire the spell.
    private final int cooldown;             //The turns it takes untill it can be used again.
    private int cooldownRem;                //The remaining cooldown on the ability.
    
    private final double attackDamage;      //How much damage it deals.
    private final String attackType;        //eg.:Fire, Ice, Physical...
    
    /**
     * Constructor for the Ability class.
     * @param name          Name of the ability.
     * @param cost          Cost to cast the ability.
     * @param cooldown      Cooldown of the ability.
     * @param attackDamage  How much damage it deals.
     * @param attackType    The type of the damage.
     */
    public Ability(String name, double cost, int cooldown, double attackDamage, String attackType){
        this.name = name;
        this.cost = cost;
        this.cooldown = cooldown;
        this.attackDamage = attackDamage;
        this.attackType = attackType;
    }

    /**
     * Returns the name of the ability.
     * @return          Name of the ability.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the cost of the ability.
     * @return          Cost of the ability.
     */
    public double getCost() {
        return cost;
    }

    /**
     * Returns the cooldown of the ability.
     * @return          Cooldown of the ability.
     */
    public int getCooldown() {
        return cooldown;
    }

    /**
     * Returns the remaining cooldown on the ability.
     * @return          Remaining cooldown.
     */
    public int getCooldownRem() {
        return cooldownRem;
    }
    
    /**
     * Returns how much damage the ability deals.
     * @return          Attack rating.
     */
    public double getAttackDamage() {
        return attackDamage;
    }

    /**
     * Returns the type of damage the ability deals.
     * @return          Attack type.
     */
    public String getAttackType() {
        return attackType;
    }

    
    /**
     * Sets the remaining cooldown on the ability.
     * @param cooldownRem   Remaining cooldown.
     */
    public void setCooldownRem(int cooldownRem) {
        this.cooldownRem = cooldownRem;
    }
}
