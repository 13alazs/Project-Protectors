package protectors;

/*
    Project Name: Project Protectors
    Members: Árvai Balázs, Bíró Benjámin, Fodor Ádám, Zászlós Dorottya Beáta
    @author Bíró Benjámin and Fodor Ádám
*/
public class Ability{
    private final String name;
    
    private final int cost;                 //The resource it requires to fire the spell.
    private final int cooldown;             //The turns it takes untill it can be used again.
    private int cooldownRem;                //The remaining cooldown on the ability.
    
    private final double attackDamage;      //How much damage it deals (or heals) (use positive numbers for damage, negative for healing)
    private final String attackType;        //eg.:Fire, Ice, Dull, Piercing...
    private final String targetType;        //Whom does this target (self / enemy / ally)
    private final int targetCount;          //How many units does it effect (1-10)
    private final String abilityType;       //What type of ability is it (attack / heal / buff / debuff / resurrect)
    
    /**
     * Constructor for the Ability class.
     * @param name          Name of the ability.
     * @param cost          Cost to cast the ability.
     * @param cooldown      Cooldown of the ability.
     * @param attackDamage  How much damage it deals.
     * @param attackType    The type of the damage.
     * @param targetType    Whom does this target 
     * @param targetCount   How many units does it effect 
     * @param abilityType   What type of ability is it
     */
    
    public Ability(String name, int cost, int cooldown, double attackDamage, String attackType, String targetType, int targetCount, String abilityType){
        this.name = name;
        this.cost = cost;
        this.cooldown = cooldown;
        this.attackDamage = attackDamage;
        this.attackType = attackType;
        this.targetType = targetType;
        this.targetCount = targetCount;
        this.abilityType = abilityType;
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
    public int getCost() {
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

    public String getTargetType() {
        return targetType;
    }

    public int getTargetCount() {
        return targetCount;
    }

    public String getAbilityType() {
        return abilityType;
    }
    
    /**
     * Sets the remaining cooldown on the ability.
     * @param cooldownRem   Remaining cooldown.
     */
    public void setCooldownRem(int cooldownRem) {
        this.cooldownRem = cooldownRem;
    }
}
