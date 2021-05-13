package protectors;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.util.Random;
import java.lang.Math;

/*
    Project Name: Project Protectors
    Members: Árvai Balázs, Bíró Benjámin, Fodor Ádám, Zászlós Dorottya Beáta
*/

public class GameManager {
    private GameEngine UI; // Will be needed to check if the game is in the correct state.
    private ArrayList<Character> playerTeam; // The player's team.
    private ArrayList<Character> enemyTeam; // The AI's team.
    private ArrayList<Character> initiativeTeam; // The player's and the AI's team combined, used to determine
                                                 // initiative.
    private ArrayList<Ability> abilities; // Might not be needed.
    private boolean playerTurn = false;
    private Character currentCharacter;
    private int currentID = -1;
    private int encounterNumber = 1;
    private Mission mission;
    private int turn;

    public GameManager(GameEngine UI) {
        this.UI = UI;
    }

    public void Setup(Mission m, ArrayList<Character> characters) {
        turn = 0;
        encounterNumber = 1;
        playerTeam = new ArrayList();
        mission = m;
        playerTeam = characters;
        arrangeTeamMembers(playerTeam, "player");
        if (mission.Encounter1()) {
            encounterSetup();
        } else {
            showResult(true);
        }
    }

    public void arrangeTeamMembers(ArrayList<Character> team, String side) {
        if ("player".equals(side)) {
            for (int i = 0; i < team.size(); i++) {
                team.get(i).setX((int) (190 + Math.pow(-1, i) * 40));
                team.get(i).setY(200 + i * 70);
            }
        } else {
            for (int i = 0; i < team.size(); i++) {
                team.get(i).setX((int) (900 + Math.pow(-1, i) * (-1) * 40));
                team.get(i).setY(200 + i * 70);
            }
        }
    }

    public void encounterSetup() {
        enemyTeam = new ArrayList();
        enemyTeam = mission.getEnemies();
        arrangeTeamMembers(enemyTeam, "enemy");
        createInitiative();
        manage();
    }

    public void nextEncounter() {
        encounterNumber++;
        if (encounterNumber == 2) {
            if (mission.Encounter2()) {
                encounterSetup();
            } else {
                showResult(true);
            }
        }
        if (encounterNumber == 3) {
            if (mission.Encounter3()) {
                encounterSetup();
            } else {
                showResult(true);
            }
        }
        if (encounterNumber == 4) {
            if (mission.Encounter4()) {
                encounterSetup();
            } else {
                showResult(true);
            }
        }
        if (encounterNumber == 5) {
            if (mission.Encounter5()) {
                encounterSetup();
            } else {
                showResult(true);
            }
        }
        if (encounterNumber == 6) {
            if (mission.Encounter6()) {
                encounterSetup();
            } else {
                showResult(true);
            }
        }
        if (encounterNumber > 6) {
            showResult(true);
        }
    }

    private void showResult(boolean res) {
        UI.setState("RESULT", res);
        UI.getResultPanel().setVisible(true);
        UI.getFightPanel().setVisible(false);
        for (Character c : playerTeam) {
            c.setCurrHealth(c.getMaxHealth());
            c.setAlive(true);
            c.setStunned(false);
            c.setCurrResource(0);
        }
    }

    public void manage() {
        if (bothTeamsAlive() == 0) {
            currentSelect();
            if (!playerTurn) {
                UI.getFightPanel().setVisible(false);
                ArrayList<Character> Targets = new ArrayList();
                if ("heal".equals(currentCharacter.getAbility2().getAbilityType())
                        && currentCharacter.getCurrHealth() <= currentCharacter.getMaxHealth() * 0.5
                        && currentCharacter.getCurrResource() >= currentCharacter.getAbility2().getCost()) {
                    Targets.add(currentCharacter);
                    currentCharacter.castAbility(currentCharacter.getAbility2(), Targets);
                } else {
                    for (int i = 0; i < currentCharacter.getAbility1().getTargetCount(); i++) {
                        int target = selectTarget();
                        Targets.add(playerTeam.get(target));
                    }
                    currentCharacter.castAbility(currentCharacter.getAbility1(), Targets);
                }
                manage();
            } else {
                UI.getFightPanel().setVisible(true);
                UI.getAbility1Button().setText(currentCharacter.getAbility1().getName());
                UI.getAbility2Button().setText(currentCharacter.getAbility2().getName());
                UI.getAbility3Button().setText(currentCharacter.getAbility3().getName());
            }
        } else if (bothTeamsAlive() == -1) {
            showResult(false);
        } else if (bothTeamsAlive() == 1) {
            nextEncounter();
        }
    }

    private int selectTarget() {
        Random rand = new Random();
        int target = rand.nextInt(playerTeam.size());
        if (!playerTeam.get(target).isAlive()) {
            target = selectTarget();
        }
        return target;
    }

    private void newTurn() {
        turn++;
        for (Character c : initiativeTeam) {
            if (c.isAlive()) {
                c.setCurrHealth(c.getCurrHealth() - c.getDamageOverTime());
                c.setOverTimeDurRem(c.getOverTimeDurRem() - 1);
                if (c.getOverTimeDurRem() <= 0) {
                    c.setDamageOverTime(0);
                }
                if (c.getCurrHealth() <= 0) {
                    c.setAlive(false);
                    c.setOverTimeDurRem(0);
                    c.setDamageOverTime(0);
                }
                if (c.getCurrHealth() > c.getMaxHealth()) {
                    c.setCurrHealth(c.getMaxHealth());
                }
            } else {
                c.setOverTimeDurRem(0);
                c.setDamageOverTime(0);
            }
            resourceGain(c);
        }
    }

    private void resourceGain(Character c) {
        int gain = 3;
        if ("mana".equals(c.getResourceName())) {
            gain = 4;
        } else if ("rage".equals(c.getResourceName())) {
            if (c.getCurrHealth() < c.getMaxHealth() * 0.3) {
                gain = 8;
            } else if (c.getCurrHealth() < c.getMaxHealth() * 0.7) {
                gain = 4;
            } else {
                gain = 2;
            }
        } else if ("focus".equals(c.getResourceName())) {
            int remainingEnemies = 0;
            for (int i = 0; i < enemyTeam.size(); i++) {
                if (enemyTeam.get(i).isAlive()) {
                    remainingEnemies++;
                }
            }
            gain = 2 + remainingEnemies;
        }
        if (c.getCurrResource() + gain <= c.getMaxResource()) {
            c.setCurrResource(c.getCurrResource() + gain);
        } else {
            c.setCurrResource(c.getMaxResource());
        }
    }

    private void currentSelect() {
        if (currentID == -1 || (currentID + 1) >= initiativeTeam.size()) {
            currentID = 0;
            newTurn();
        } else {
            currentID++;
        }
        if (!initiativeTeam.get(currentID).isAlive()) {
            currentSelect();
        }
        if (initiativeTeam.get(currentID).isStunned()) {
            initiativeTeam.get(currentID).setStunned(false);
            currentSelect();
        }
        setCurrentCharacter(initiativeTeam.get(currentID));
        playerTurn = false;
        for (Character P : playerTeam) {
            if (P.getName().equals(currentCharacter.getName())) {
                playerTurn = true;
            }
        }
    }

    private int bothTeamsAlive() {
        boolean playerAlive = false;
        boolean enemyAlive = false;
        for (Character P : playerTeam) {
            if (P.getCurrHealth() > 0) {
                playerAlive = true;
            }
        }
        for (Character E : enemyTeam) {
            if (E.getCurrHealth() > 0) {
                enemyAlive = true;
            }
        }
        if (playerAlive && enemyAlive) {
            return 0;
        } else if (!playerAlive && enemyAlive) {
            return -1;
        } else if (playerAlive && !enemyAlive) {
            return 1;
        }
        return -1;
    }

    private void createInitiative() {
        currentID = -1;
        initiativeTeam = new ArrayList();
        initiativeTeam.addAll(playerTeam);
        initiativeTeam.addAll(enemyTeam);
        Character temp;
        for (int i = 0; i < initiativeTeam.size() - 1; i++) {
            for (int j = i + 1; j < initiativeTeam.size(); j++) {
                if (initiativeTeam.get(j).getInitiative() <= initiativeTeam.get(i).getInitiative()) {
                    temp = initiativeTeam.get(i);
                    initiativeTeam.set(i, initiativeTeam.get(j));
                    initiativeTeam.set(j, temp);
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

    public Character getCurrentCharacter() {
        return currentCharacter;
    }

    public void setCurrentCharacter(Character currentCharacter) {
        this.currentCharacter = currentCharacter;
        for (int i = 0; i < playerTeam.size(); i++) {
            playerTeam.get(i).setActive(false);
        }
        currentCharacter.setActive(true);
        this.currentCharacter.setActive(true);
    }
}
