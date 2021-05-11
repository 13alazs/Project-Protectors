package protectors;

import java.awt.Image;
import java.util.ArrayList;

/*
    Project Name: Project Protectors
    Members: Árvai Balázs, Bíró Benjámin, Fodor Ádám, Zászlós Dorottya Beáta
    @author Bíró Benjámin
*/

public class Character extends Sprite {

    private final String name;

    private final double maxHealth; // Maximum health points.
    private double currHealth; // Current health points.

    private final String resourceName; // If we plan on using anything other than mana.
    private final int maxResource; // Maximum resource points.
    private int currResource; // Current resource points.

    private final double armor; // Reduces damage taken.
    // private final ArrayList<String> resistances; //Also reduces damage taken, but only from certain abilities.
    // private final ArrayList<String> vulnerabilities; //Increases damage taken, but only from certain abilities.

    private final Ability ability1;
    private final Ability ability2;
    private final Ability ability3;

    private final int initiative; // Determines when the character gets to go.

    private boolean alive; // Tracks if the character is alive or dead.
    private boolean stunned; // Tracks if the character is stunned or not.

    /**
     * Constructor that calls the Sprite parent class' constructor.
     * 
     * @param x
     *            The x coord for the image.
     * @param y
     *            The y coord for the image.
     * @param width
     *            The width of the image.
     * @param height
     *            The height of the image.
     * @param image
     *            The sprite for the image.
     * @param name
     *            Name of the character.
     * @param maxHealth
     *            The max health points the character starts with.
     * @param resourceName
     *            Resource the character uses.
     * @param maxResource
     *            The maximum amount of resource a character can have.
     * @param armor
     *            The amount of damage reduction.
     * @param ability1
     *            Should always be a single target attack that costs 0. (This is for the stupid AI)
     * @param ability2
     *            Can be anything.
     * @param ability3
     *            Can be anything.
     * @param initiative
     *            Determines when the character gets to go
     */
    public Character(int x, int y, int width, int height, Image image, String name, int maxHealth, String resourceName,
            int maxResource, double armor, Ability ability1, Ability ability2, Ability ability3, int initiative) {
        super(x, y, width, height, image);
        this.name = name;
        this.maxHealth = maxHealth;
        this.currHealth = maxHealth;
        this.resourceName = resourceName;
        this.maxResource = maxResource;
        this.currResource = 5;
        this.armor = armor;
        this.ability1 = ability1;
        this.ability2 = ability2;
        this.ability3 = ability3;
        this.initiative = initiative;
        this.alive = true;
        this.stunned = false;
    }

    /**
     * Player casts the choosen ability, if has enough resource.Then the players resource points decreases as much as
     * the ability costs.
     * 
     * @param ab
     *            The ability to cast.
     * @param targets
     *            The targets to cast it on.
     * 
     * @return Can the ability be cast (will be used to determine if the character's turn is over)
     */
    public boolean castAbility(Ability ab, ArrayList<Character> targets) {
        if (currResource >= ab.getCost()) {
            setCurrResource(getCurrResource() - ab.getCost());
            for (Character target : targets) {
                if ("attack".equals(ab.getAbilityType())) {
                    target.healthChange(ab.getAttackDamage() - target.getArmor());
                } else if ("heal".equals(ab.getAbilityType())) {
                    target.healthChange(ab.getAttackDamage());
                } else if ("resurrect".equals(ab.getAbilityType())) {
                    target.resurrect();
                } else if ("stun".equals(ab.getAbilityType())) {
                    target.stun();
                } // TODO: DoTs, HoTs, buffs and debuffs (maybe)
            }
            return true;
        }
        return false;
    }

    private void healthChange(double amount) {
        if (alive) {
            if (currHealth - amount <= 0) {
                currHealth = 0;
                alive = false;
            } else {
                currHealth -= amount;
            }
        }
    } // Used for both taking damage and healing

    private void resurrect() {
        if (!alive) {
            alive = true;
            currHealth = 1;
        } else {
            currHealth = maxHealth;
        }
    } // Used resurrecting characters, but if they are already alive, this works as a full heal instead

    private void stun() {
        if (alive) {
            stunned = true;
        }
    }

    public Ability getAbility1() {
        return ability1;
    }

    public Ability getAbility2() {
        return ability2;
    }

    public Ability getAbility3() {
        return ability3;
    }

    /**
     * Returns the nam of the character.
     * 
     * @return Name of the character.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the max health points the character has.
     * 
     * @return Max health points.
     */
    public double getMaxHealth() {
        return maxHealth;
    }

    /**
     * Returns the current health points the character has.
     * 
     * @return Current health points.
     */
    public double getCurrHealth() {
        return currHealth;
    }

    /**
     * Returns the name of the resource the player has.
     * 
     * @return Name of the resource (e.g.: Mana).
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * Returns the max resource points the character has.
     * 
     * @return Max resource points.
     */
    public int getMaxResource() {
        return maxResource;
    }

    /**
     * Returns the current resource points the character has.
     * 
     * @return Current resource points.
     */
    public int getCurrResource() {
        return currResource;
    }

    /**
     * Returns the armor rating the character has.
     * 
     * @return Armor rating.
     */
    public double getArmor() {
        return armor;
    }

    /**
     * Returns if the character is alive or not.
     * 
     * @return Alive or not.
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Sets the current health points of the character.
     * 
     * @param currHealth
     *            Current health points.
     */
    public void setCurrHealth(double currHealth) {
        this.currHealth = currHealth;
    }

    /**
     * Sets the current resource points the character has.
     * 
     * @param currResource
     *            Current resource points.
     */
    public void setCurrResource(int currResource) {
        this.currResource = currResource;
    }

    /**
     * Sets the character dead or alive.
     * 
     * @param alive
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getInitiative() {
        return initiative;
    }

    public boolean isStunned() {
        return stunned;
    }

    public void setStunned(boolean stunned) {
        this.stunned = stunned;
    }
}
