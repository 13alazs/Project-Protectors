package com.github._13alazs;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/*
    Project Name: Project Protectors
    Members: Árvai Balázs, Bíró Benjámin, Fodor Ádám, Zászlós Dorottya Beáta
*/

public class UndeadMarch extends Mission {
    public UndeadMarch() {
        name = "March of the Undead";
        background = new ImageIcon("data/images/backgrounds/caveIce.jpg").getImage();
    }

    @Override
    public boolean Encounter1() {
        Enemies = new ArrayList();
        Ability Slash = new Ability("Slash", 0, 0, 20, "slashing", "enemy", 1, "attack", "normal",
                new ImageIcon("data/images/spells/Warrior1.jpg").getImage());
        Ability Cleave = new Ability("Cleave", 0, 0, 10, "slashing", "enemy", 2, "attack", "normal",
                new ImageIcon("data/images/spells/Warrior1.jpg").getImage());
        Ability Pummel = new Ability("Pummel", 0, 0, 10, "blunt", "enemy", 1, "stun", "normal",
                new ImageIcon("data/images/spells/Warrior1.jpg").getImage());
        Character Skeleton1 = new Character(860, 150, 50, 50,
                new ImageIcon("data/images/characters/enemies/skeleton.png").getImage(), "Little Bones", 40, "focus",
                20, 0, Slash, Slash, Slash, 6);
        Enemies.add(Skeleton1);
        Character Zombie1 = new Character(860, 150, 50, 50,
                new ImageIcon("data/images/characters/enemies/zombie.png").getImage(), "Brainman", 70, "rage", 20, 10,
                Pummel, Pummel, Pummel, 8);
        Enemies.add(Zombie1);
        Character BigSkeleton = new Character(860, 150, 60, 60,
                new ImageIcon("data/images/characters/enemies/skeleton.png").getImage(), "Big Bones", 100, "focus", 20,
                0, Cleave, Cleave, Cleave, 8);
        Enemies.add(BigSkeleton);
        Character Zombie2 = new Character(860, 150, 50, 50,
                new ImageIcon("data/images/characters/enemies/zombie.png").getImage(), "Zed", 70, "rage", 20, 10,
                Pummel, Pummel, Pummel, 8);
        Enemies.add(Zombie2);
        Character Skeleton2 = new Character(860, 150, 50, 50,
                new ImageIcon("data/images/characters/enemies/skeleton.png").getImage(), "Dr Bones", 40, "focus", 20, 0,
                Slash, Slash, Slash, 6);
        Enemies.add(Skeleton2);
        return true;
    }

    @Override
    public boolean Encounter2() {
        Enemies = new ArrayList();
        Ability Darkbolt = new Ability("Darkbolt", 0, 0, 25, "shadow", "enemy", 1, "attack", "normal",
                new ImageIcon("data/images/spells/Warrior1.jpg").getImage());
        Ability ShadowMend = new Ability("ShadowMend", 0, 0, 20, "shadow", "enemy", 1, "attack", "normal",
                new ImageIcon("data/images/spells/Warrior1.jpg").getImage());
        Character Necromancer1 = new Character(860, 150, 50, 50,
                new ImageIcon("data/images/characters/enemies/necromancer.png").getImage(), "Necro", 40, "mana", 20, 0,
                Darkbolt, ShadowMend, Darkbolt, 4);
        Enemies.add(Necromancer1);
        Character Necromancer2 = new Character(860, 300, 50, 50,
                new ImageIcon("data/images/characters/enemies/necromancer.png").getImage(), "Mancer", 40, "mana", 20, 0,
                Darkbolt, ShadowMend, Darkbolt, 4);
        Enemies.add(Necromancer2);
        return true;
    }

    @Override
    public boolean Encounter3() {
        Enemies = new ArrayList();
        Ability Cleave = new Ability("Cleave", 0, 0, 10, "slashing", "enemy", 2, "attack", "normal",
                new ImageIcon("data/images/spells/Warrior1.jpg").getImage());
        Ability Bite = new Ability("Bite", 0, 0, 10, "piercing", "enemy", 2, "DoT", "normal",
                new ImageIcon("data/images/spells/Warrior1.jpg").getImage());
        Ability ShadowMend = new Ability("ShadowMend", 0, 0, 25, "shadow", "enemy", 1, "attack", "normal",
                new ImageIcon("data/images/spells/Warrior1.jpg").getImage());
        Character BigSkeleton = new Character(860, 150, 60, 60,
                new ImageIcon("data/images/characters/enemies/skeleton.png").getImage(), "Big Bones", 100, "focus", 20,
                0, Cleave, Cleave, Cleave, 8);
        Enemies.add(BigSkeleton);
        Character Vampire = new Character(860, 150, 50, 50,
                new ImageIcon("data/images/characters/enemies/skeleton.png").getImage(), "Dracula", 90, "mana", 20, 0,
                Bite, ShadowMend, Bite, 2);
        Enemies.add(Vampire);
        return true;
    }

    @Override
    public boolean Encounter4() {
        Enemies = new ArrayList();
        Ability Cleave = new Ability("Cleave", 0, 0, 10, "slashing", "enemy", 2, "attack", "normal",
                new ImageIcon("data/images/spells/Warrior1.jpg").getImage());
        Ability Bite = new Ability("Bite", 0, 0, 10, "piercing", "enemy", 2, "DoT", "normal",
                new ImageIcon("data/images/spells/Warrior1.jpg").getImage());
        Ability ShadowMend = new Ability("ShadowMend", 0, 0, 20, "shadow", "enemy", 1, "attack", "normal",
                new ImageIcon("data/images/spells/Warrior1.jpg").getImage());
        Ability Darkbolt = new Ability("Darkbolt", 0, 0, 25, "shadow", "enemy", 1, "attack", "normal",
                new ImageIcon("data/images/spells/Warrior1.jpg").getImage());
        Character Vampire = new Character(860, 150, 50, 50,
                new ImageIcon("data/images/characters/enemies/vampire.png").getImage(), "Dracula", 90, "mana", 20, 0,
                Bite, ShadowMend, Bite, 2);
        Enemies.add(Vampire);
        Character Necromancer = new Character(860, 150, 50, 50,
                new ImageIcon("data/images/characters/enemies/necromancer.png").getImage(), "Necromancer", 40, "mana",
                20, 0, Darkbolt, ShadowMend, Darkbolt, 4);
        Enemies.add(Necromancer);
        Character BigSkeleton = new Character(860, 150, 60, 60,
                new ImageIcon("data/images/characters/enemies/skeleton.png").getImage(), "Big Bones", 100, "focus", 20,
                0, Cleave, Cleave, Cleave, 8);
        Enemies.add(BigSkeleton);
        return true;
    }

    @Override
    public boolean Encounter5() {
        Enemies = new ArrayList();
        Ability ShadowMend = new Ability("ShadowMend", 0, 0, 20, "shadow", "enemy", 1, "attack", "normal",
                new ImageIcon("data/images/spells/Warrior1.jpg").getImage());
        Ability Darkbolt = new Ability("Darkbolt", 0, 0, 25, "shadow", "enemy", 1, "attack", "normal",
                new ImageIcon("data/images/spells/Warrior1.jpg").getImage());
        Ability MassDarkbolt = new Ability("Darkbolt", 0, 0, 20, "shadow", "enemy", 3, "attack", "normal",
                new ImageIcon("data/images/spells/Warrior1.jpg").getImage());
        Ability Pummel = new Ability("Pummel", 0, 0, 10, "blunt", "enemy", 1, "stun", "normal",
                new ImageIcon("data/images/spells/Warrior1.jpg").getImage());
        Character Zombie1 = new Character(860, 150, 50, 50,
                new ImageIcon("data/images/characters/enemies/zombie.png").getImage(), "Brainman", 70, "rage", 20, 10,
                Pummel, Pummel, Pummel, 8);
        Enemies.add(Zombie1);
        Character Necrolord = new Character(860, 150, 60, 60,
                new ImageIcon("data/images/characters/enemies/necromancer.png").getImage(), "Necrolord", 80, "mana", 20,
                0, MassDarkbolt, ShadowMend, MassDarkbolt, 10);
        Enemies.add(Necrolord);
        Character Necromancer = new Character(860, 150, 50, 50,
                new ImageIcon("data/images/characters/enemies/necromancer.png").getImage(), "Necromancer", 40, "mana",
                20, 0, Darkbolt, ShadowMend, Darkbolt, 4);
        Enemies.add(Necromancer);
        return true;
    }
}
