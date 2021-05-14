package io.github._13alazs;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/*
    Project Name: Project Protectors
    Members: Árvai Balázs, Bíró Benjámin, Fodor Ádám, Zászlós Dorottya Beáta
*/

public class Training3 extends Mission {
    public Training3() {
        name = "Training3";
        background = new ImageIcon("data/images/backgrounds/graveyard.jpg").getImage();
    }

    @Override
    public boolean Encounter1() {
        Enemies = new ArrayList();
        Ability Slash = new Ability("Slash", 0, 0, 30, "slashing", "enemy", 1, "attack", "normal",
                new ImageIcon("data/images/spells/Warrior1.jpg").getImage());
        Character Goblin = new Character(860, 150, 30, 30,
                new ImageIcon("data/images/characters/enemies/goblin.png").getImage(), "Goblin", 60, "focus", 20, 5,
                Slash, Slash, Slash, 3);
        Enemies.add(Goblin);
        return true;
    }

    @Override
    public boolean Encounter2() {
        Enemies = new ArrayList();
        Ability Slash = new Ability("Slash", 0, 0, 30, "slashing", "enemy", 1, "attack", "normal",
                new ImageIcon("data/images/spells/Warrior1.jpg").getImage());
        Character Goblin = new Character(860, 150, 30, 30,
                new ImageIcon("data/images/characters/enemies/goblin.png").getImage(), "Goblin", 60, "focus", 20, 5,
                Slash, Slash, Slash, 3);
        Enemies.add(Goblin);
        Character Minotaurus = new Character(860, 300, 60, 60,
                new ImageIcon("data/images/characters/enemies/minotaurus.png").getImage(), "Boglin", 55, "focus", 15, 5,
                Slash, Slash, Slash, 2);
        Enemies.add(Minotaurus);
        return true;
    }

    @Override
    public boolean Encounter3() {
        Enemies = new ArrayList();
        Ability Slash = new Ability("Slash", 0, 0, 30, "slashing", "enemy", 1, "attack", "normal",
                new ImageIcon("data/images/spells/Warrior1.jpg").getImage());
        Ability Bash = new Ability("Bash", 0, 0, 40, "blunt", "enemy", 1, "attack", "normal",
                new ImageIcon("data/images/spells/Warrior3.jpg").getImage());
        Image tmpSprite = new ImageIcon("data/images/tmpSprite.png").getImage();
        Character Goblin = new Character(860, 150, 30, 30,
                new ImageIcon("data/images/characters/enemies/goblin.png").getImage(), "Goblin", 60, "focus", 20, 5,
                Slash, Slash, Slash, 3);
        Enemies.add(Goblin);
        Character Minotaurus = new Character(860, 300, 60, 60,
                new ImageIcon("data/images/characters/enemies/minotaurus.png").getImage(), "Boglin", 55, "focus", 15, 7,
                Slash, Slash, Slash, 2);
        Enemies.add(Minotaurus);
        Character CorMinotaurus = new Character(860, 450, 60, 60,
                new ImageIcon("data/images/characters/enemies/minotaurusCorrupted.png").getImage(), "Hobgoblin", 110,
                "rage", 30, 5, Bash, Bash, Bash, 8);
        Enemies.add(CorMinotaurus);
        return true;
    }

    // If the mission is missing an encounter, just don't override it, in this case, encounters 4, 5, 6 will return
    // false and will not be used
}
