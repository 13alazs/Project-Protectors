package com.mycompany.protectors;

/*
    Project Name: Project Protectors
    Members: Árvai Balázs, Bíró Benjámin, Fodor Ádám, Zászlós Dorottya Beáta
    @author Bíró Benjámin
*/
public class ClassType{
    private final String name;                //Name of the class.
    private final String combatType;          //Ranged, Magic, Melee.
    private final String combatPosition;      //Tank, Damage, Healer ?Support?.
    
    /**
     * Constructor for the ClassType class.
     * @param name              The name of the class.
     * @param combatType        The type of combat the class fights in (e.g.: Melee, Ranged...).
     * @param combatPosition    The position the class fights in (e.g.: Healer, Tank...).
     */
    public ClassType(String name, String combatType, String combatPosition){
        this.name = name;
        this.combatType = combatType;
        this.combatPosition = combatPosition;
    }

    /**
     * Returns the name of the class.
     * @return          Name of the class.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the type of combat the class fights in (e.g.: Melee, Ranged...).
     * @return          Combat type.
     */
    public String getCombatType() {
        return combatType;
    }

    /**
     * Returns the position the class fights in (e.g.: Healer, Tank...).
     * @return          Comabt position.
     */
    public String getCombatPosition() {
        return combatPosition;
    }
}
