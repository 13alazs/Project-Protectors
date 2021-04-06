package protectors;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/*
    Project Name: Project Protectors
    Members: Árvai Balázs, Bíró Benjámin, Fodor Ádám, Zászlós Dorottya Beáta
*/

public class GameManager {
    private GameEngine UI;                          //Will be needed to check if the game is in the correct state.
    private ArrayList<Character> playerTeam;        //The player's team.
    private ArrayList<Character> enemyTeam;         //The AI's team.
    private ArrayList<Character> initiativeTeam;    //The player's and the AI's team combined, used to determine initiative.
    private ArrayList<Ability> abilities;           //Might not be needed.
    
    public GameManager(GameEngine UI){
        this.UI = UI;
    }
    
    public void basicSetup(){
        playerTeam = new ArrayList();
        enemyTeam = new ArrayList();
        //initiativeTeam = new ArrayList();
        abilities = new ArrayList();
        Ability Slash = new Ability("Slash", 0, 0, 30, "slashing", "enemy", 1, "attack");
        Ability Heal = new Ability("Heal", 10, 2, -45, "none", "ally", 1, "heal");
        Ability Resurrect = new Ability("Resurrect", 20, 4, 0, "none", "ally", 1, "resurrect");
        Image tmpSprite = new ImageIcon("data/images/tmpSprite.png").getImage();
        Character Knight = new Character(150, 150, 50, 50, tmpSprite, "Knight", 120, "mana", 20, 10, Slash, Heal, Resurrect, 6);
        playerTeam.add(Knight);
        Character Goblin = new Character(450, 450, 30, 30, tmpSprite, "Goblin", 60, "focus", 20, 5, Slash, Slash, Slash, 3);
        enemyTeam.add(Goblin);
    }//Ez csak minta a karakterlétrehozásra, a jövőben inkább úgy kellene majd csinálni, hogy a Setup()-nak átadunk egy küldetést (ami egy encounterekkel ellátott leszármazottja lesz egy Mission ősosztálynak, valamint átadunk neki egy Character listát)

    public void Setup(Mission mission, ArrayList<Character> characters){
        playerTeam = characters;
        if(mission.Encounter1()){
            enemyTeam = mission.getEnemies();
            //initiativeTeam = new ArrayList(); //Itt kellene majd mergelni a két csapatot és sortolni initiative értékük alapján
            
            if(result()=="victory"){ //Ha legyőzte a játékos az ellenfelet, akkor mehetünk tovább
                if(mission.Encounter2()){
                    enemyTeam = mission.getEnemies();
                    
                    if(result()=="victory"){
                        if(mission.Encounter3()){
                            enemyTeam = mission.getEnemies();
                            
                            if(result()=="victory"){
                                if(mission.Encounter4()){
                                    enemyTeam = mission.getEnemies();
                                    
                                    if(result()=="victory"){
                                        if(mission.Encounter5()){
                                            enemyTeam = mission.getEnemies();
                                            
                                            if(result()=="victory"){
                                                if(mission.Encounter6()){
                                                    enemyTeam = mission.getEnemies();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public ArrayList<Character> getPlayerTeam() {
        return playerTeam;
    }

    public void setPlayerTeam(ArrayList<Character> playerTeam) {
        this.playerTeam = playerTeam;
    }

    public ArrayList<Character> getEnemyTeam() {
        return enemyTeam;
    }

    public void setEnemyTeam(ArrayList<Character> enemyTeam) {
        this.enemyTeam = enemyTeam;
    }

    public ArrayList<Character> getInitiativeTeam() {
        return initiativeTeam;
    }

    public void setInitiativeTeam(ArrayList<Character> initiativeTeam) {
        this.initiativeTeam = initiativeTeam;
    }

    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<Ability> abilities) {
        this.abilities = abilities;
    }
    
    public String result(){
        return "victory";
    }
}
