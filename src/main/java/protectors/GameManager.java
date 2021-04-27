package protectors;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.util.Random;

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
    private boolean playerTurn=false;
    private boolean casting=false;
    private Character currentCharacter;
    private int currentID=-1;
    
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
        Character Goblin = new Character(860, 150, 30, 30, tmpSprite, "Goblin", 60, "focus", 20, 5, Slash, Slash, Slash, 3);
        enemyTeam.add(Goblin);
    }//Ez csak minta a karakterlétrehozásra, a jövőben inkább úgy kellene majd csinálni, hogy a Setup()-nak átadunk egy küldetést (ami egy encounterekkel ellátott leszármazottja lesz egy Mission ősosztálynak, valamint átadunk neki egy Character listát)

    public void Setup(Mission mission, ArrayList<Character> characters){
        playerTeam = characters;
        if(mission.Encounter1()){
            enemyTeam = mission.getEnemies();
            manage();
            if(bothTeamsAlive()==1){ //Ha legyőzte a játékos az ellenfelet, akkor mehetünk tovább
                if(mission.Encounter2()){
                    enemyTeam = mission.getEnemies();
                    manage();
                    
                    if(bothTeamsAlive()==1){
                        if(mission.Encounter3()){
                            enemyTeam = mission.getEnemies();
                            manage();
                            
                            if(bothTeamsAlive()==1){
                                if(mission.Encounter4()){
                                    enemyTeam = mission.getEnemies();
                                    manage();
                                    
                                    if(bothTeamsAlive()==1){
                                        if(mission.Encounter5()){
                                            enemyTeam = mission.getEnemies();
                                            manage();
                                            
                                            if(bothTeamsAlive()==1){
                                                if(mission.Encounter6()){
                                                    enemyTeam = mission.getEnemies();
                                                    manage();
                                                }
                                                else{
                                                    showResult(true);
                                                }
                                            }
                                            else{
                                                showResult(false);
                                            }
                                        }
                                        else{
                                            showResult(true);
                                        }
                                    }
                                    else{
                                        showResult(false);
                                    }
                                }
                                else{
                                    showResult(true);
                                }
                            }
                            else{
                                showResult(false);
                            }
                        }
                        else{
                            showResult(true);
                        }
                    }
                    else{
                        showResult(false);
                    }
                }
                else{
                    showResult(true);
                }
            }
            else{
                showResult(false);
            }
        }
        else{
            showResult(true);
        }
    }
    
    private void showResult(boolean res){
        UI.setState("RESULT", res);
        UI.getResultPanel().setVisible(true);
        UI.getFightPanel().setVisible(false);
    }
    
    private void manage(){
        createInitiative();
        if(bothTeamsAlive()==0){
            currentSelect();
            if(!playerTurn){
                int target=selectTarget();
                ArrayList<Character> Targets=new ArrayList();
                Targets.add(playerTeam.get(target));
                currentCharacter.castAbility(currentCharacter.getAbility1(), Targets);
                System.out.println(currentCharacter.getName() + " used " + currentCharacter.getAbility1().getName());
            }
            else{
                System.out.println(currentCharacter.getName() + " had their turn");
            }
            Manage();
        }
    }
    
    private void Manage(){
        if(bothTeamsAlive()==0){
            currentSelect();
            if(!playerTurn){
                int target=selectTarget();
                ArrayList<Character> Targets=new ArrayList();
                Targets.add(playerTeam.get(target));
                currentCharacter.castAbility(currentCharacter.getAbility1(), Targets);
                System.out.println(currentCharacter.getName() + " used " + currentCharacter.getAbility1().getName());
            }
            else{
                System.out.println(currentCharacter.getName() + " had their turn");
            }
            Manage();
        }
    }
    
    private int selectTarget(){
        Random rand = new Random();
        int target=rand.nextInt(playerTeam.size());
        if(!playerTeam.get(target).isAlive()){
            target=selectTarget();
        }
        return target;
    }
    
    private void currentSelect(){
        if(currentID==-1 || (currentID+1)>=initiativeTeam.size()){
            currentID=0;
        }
        else{
            currentID++;
        }
        if(!initiativeTeam.get(currentID).isAlive()){
            currentSelect();
        }
        currentCharacter=initiativeTeam.get(currentID);
        playerTurn=false;
        for(Character P : playerTeam){
            //System.out.println("Player's turn: " + playerTurn);
            //System.out.println(P.getName() + " = " + currentCharacter.getName() + " ?");
            if(P.getName().equals(currentCharacter.getName())){
                playerTurn=true;
            }
        }
    }
    
    private int bothTeamsAlive(){
        boolean playerAlive=false;
        boolean enemyAlive=false;
        for(Character P : playerTeam){
            if(P.getCurrHealth()>0){
                playerAlive=true;
            }
        }
        for(Character E : enemyTeam){
            if(E.getCurrHealth()>0){
                enemyAlive=true;
            }
        }
        if(playerAlive && enemyAlive){
            return 0;
        }
        else if(!playerAlive && enemyAlive){
            return -1;
        }
        else if(playerAlive && !enemyAlive){
            return 1;
        }
        return -1;
    }
    
    private void createInitiative(){
        initiativeTeam=new ArrayList();
        initiativeTeam.addAll(playerTeam);
        initiativeTeam.addAll(enemyTeam);
        Character temp;
        for(int i=0; i<initiativeTeam.size()-1; i++){
            for(int j=i+1; j<initiativeTeam.size(); j++){
                if(initiativeTeam.get(j).getInitiative()<=initiativeTeam.get(i).getInitiative()){
                    temp=initiativeTeam.get(i);
                    initiativeTeam.set(i, initiativeTeam.get(j));
                    initiativeTeam.set(j, temp);
                }
            }
        }
        System.out.println("Team initiatives have been set");
        //System.out.println(initiativeTeam.size() + " characters are in the initiative team");
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
}
