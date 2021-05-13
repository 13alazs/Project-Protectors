package protectors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class CharacterSelectionPanel extends JPanel {
    // private final Image image;

    private JPanel characterHolderPanel;

    private ArrayList<Character> allTheCharacters = new ArrayList<Character>();
    private ArrayList<Boolean> isChosen = new ArrayList<Boolean>();
    private ArrayList<Character> chosenCharacters = new ArrayList<Character>();

    // Archer
    private Ability Shoot = new Ability("Shoot", 0, 0, 35, "piercing", "enemy", 1, "attack", "normal",
            new ImageIcon("data/images/spells/Archer1.jpg").getImage());
    private Ability Blindfire = new Ability("Blindfire", 10, 3, 45, "piercing", "enemy", 1, "attack", "random",
            new ImageIcon("data/images/spells/Archer2.jpg").getImage());
    private Ability Piercingshot = new Ability("Piercingshot", 10, 1, 25, "piercing", "enemy", 2, "attack", "normal",
            new ImageIcon("data/images/spells/Archer3.jpg").getImage());
    // Druid
    private Ability SolarBeam = new Ability("Solar Beam", 0, 0, 25, "nature", "enemy", 1, "attack", "normal",
            new ImageIcon("data/images/spells/Druid1.jpg").getImage());
    private Ability DeadlySpores = new Ability("Deadly Spores", 10, 2, 20, "nature", "enemy", 4, "attack", "random",
            new ImageIcon("data/images/spells/Druid2.jpg").getImage()); // This
    // could
    // be
    // DoT
    // or
    // HoT
    private Ability Nurture = new Ability("Nurture", 10, 2, -45, "nature", "ally", 1, "heal", "normal",
            new ImageIcon("data/images/spells/Druid3.jpg").getImage());
    // Knight
    private Ability Slash = new Ability("Slash", 0, 0, 25, "slashing", "enemy", 1, "attack", "normal",
            new ImageIcon("data/images/spells/Knight1.jpg").getImage());
    private Ability ShieldBash = new Ability("Shield Bash", 10, 2, 0, "blunt", "enemy", 1, "stun", "normal",
            new ImageIcon("data/images/spells/Knight2.jpg").getImage());
    private Ability PiercingHowl = new Ability("Piercing Howl", 10, 3, 20, "none", "enemy", 5, "attack", "normal",
            new ImageIcon("data/images/spells/Knight3.jpg").getImage());
    // Mage
    private Ability Fireball = new Ability("Fireball", 0, 0, 30, "fire", "enemy", 1, "attack", "normal",
            new ImageIcon("data/images/spells/Mage1.jpg").getImage());
    private Ability SnowStorm = new Ability("Snow Storm", 10, 4, 30, "frost", "enemy", 3, "attack", "random",
            new ImageIcon("data/images/spells/Mage2.jpg").getImage());
    private Ability DeepFreeze = new Ability("Deep Freeze", 10, 2, 0, "frost", "enemy", 1, "stun", "normal",
            new ImageIcon("data/images/spells/Mage3.jpg").getImage());
    // Monk
    private Ability Punch = new Ability("Punch", 0, 0, 30, "blunt", "enemy", 1, "attack", "normal",
            new ImageIcon("data/images/spells/Monk1.jpg").getImage());
    private Ability Kick = new Ability("Kick", 15, 1, 0, "blunt", "enemy", 1, "stun", "normal",
            new ImageIcon("data/images/spells/Monk2.jpg").getImage());
    private Ability InnerPeace = new Ability("Inner Peace", 10, 1, -25, "none", "ally", 1, "heal", "normal",
            new ImageIcon("data/images/spells/Monk3.jpg").getImage());
    // Paladin
    private Ability RighteousHammer = new Ability("Righteous Hammer", 0, 0, 25, "holy", "enemy", 1, "attack", "normal",
            new ImageIcon("data/images/spells/Paladin1.jpg").getImage());
    private Ability ShieldSlam = new Ability("Shield Slam", 5, 3, 0, "blunt", "enemy", 1, "stun", "normal",
            new ImageIcon("data/images/spells/Paladin2.jpg").getImage());
    private Ability HolyLight = new Ability("Holy Light", 10, 1, -30, "holy", "ally", 1, "heal", "normal",
            new ImageIcon("data/images/spells/Paladin3.jpg").getImage());
    // Priest
    private Ability Smite = new Ability("Smite", 0, 0, 25, "holy", "enemy", 1, "attack", "normal",
            new ImageIcon("data/images/spells/Priest1.jpg").getImage());
    private Ability CleansingLight = new Ability("Cleansing Light", 15, 2, -55, "holy", "ally", 1, "heal", "normal",
            new ImageIcon("data/images/spells/Priest2.jpg").getImage());
    private Ability Resurrect = new Ability("Resurrect", 20, 4, 0, "holy", "ally", 1, "resurrect", "normal",
            new ImageIcon("data/images/spells/Priest3.jpg").getImage());
    // Rogue
    private Ability Stab = new Ability("Stab", 0, 0, 35, "piercing", "enemy", 1, "attack", "normal",
            new ImageIcon("data/images/spells/Rogue1.jpg").getImage());
    private Ability PoisonousFlask = new Ability("Poisonous Flask", 10, 3, 25, "nature", "enemy", 3, "attack", "normal",
            new ImageIcon("data/images/spells/Rogue2.jpg").getImage()); // Could be DoT
    private Ability Backstab = new Ability("Backstab", 15, 1, 0, "piercing", "enemy", 1, "stun", "normal",
            new ImageIcon("data/images/spells/Rogue3.jpg").getImage());
    // Warlock
    private Ability SiphonSoul = new Ability("Siphon Soul", 0, 0, 15, "shadow", "enemy", 2, "attack", "random",
            new ImageIcon("data/images/spells/Warlock1.jpg").getImage());
    private Ability TouchOfDarkness = new Ability("Touch of Darkness", 10, 3, 25, "shadow", "enemy", 1, "attack",
            "normal", new ImageIcon("data/images/spells/Warlock2.jpg").getImage()); // DoT
    private Ability Doom = new Ability("Doom", 15, 2, 45, "shadow", "enemy", 3, "attack", "random",
            new ImageIcon("data/images/spells/Warlock3.jpg").getImage()); // Mass DoT
    // Warrior
    private Ability Cleave = new Ability("Cleave", 0, 0, 15, "slashing", "enemy", 2, "attack", "normal",
            new ImageIcon("data/images/spells/Warrior1.jpg").getImage());
    private Ability BlindingRage = new Ability("Blinding Rage", 15, 3, 20, "slashing", "enemy", 3, "attack", "random",
            new ImageIcon("data/images/spells/Warrior2.jpg").getImage());
    private Ability Whirlwind = new Ability("Whirlwind", 10, 3, 15, "slashing", "enemy", 5, "attack", "normal",
            new ImageIcon("data/images/spells/Warrior3.jpg").getImage());

    public CharacterSelectionPanel() {
        super();

        // this.image = new ImageIcon("data/images/ui/uiBackground.png").getImage();

        allTheCharacters.add(
                new Character(860, 150, 50, 50, new ImageIcon("data/images/characters/playable/archer.png").getImage(),
                        "Gerrie the Archer", 50, "focus", 10, 5, Shoot, Blindfire, Piercingshot, 2));
        allTheCharacters.add(
                new Character(860, 150, 50, 50, new ImageIcon("data/images/characters/playable/druid.png").getImage(),
                        "Runetotem", 40, "mana", 40, 0, SolarBeam, DeadlySpores, Nurture, 4));
        allTheCharacters.add(
                new Character(860, 150, 50, 50, new ImageIcon("data/images/characters/playable/knight.png").getImage(),
                        "Knight Lautrec", 100, "rage", 20, 10, Slash, ShieldBash, PiercingHowl, 6));
        allTheCharacters.add(
                new Character(860, 150, 50, 50, new ImageIcon("data/images/characters/playable/mage.png").getImage(),
                        "Khadgar", 40, "mana", 40, 0, Fireball, SnowStorm, DeepFreeze, 4));
        allTheCharacters.add(
                new Character(860, 150, 50, 50, new ImageIcon("data/images/characters/playable/monk.png").getImage(),
                        "Asmon Bald", 60, "focus", 10, 2, Punch, Kick, InnerPeace, 3));
        allTheCharacters.add(
                new Character(860, 150, 50, 50, new ImageIcon("data/images/characters/playable/paladin.png").getImage(),
                        "The Ashbringer", 90, "mana", 20, 20, RighteousHammer, ShieldSlam, HolyLight, 7));
        allTheCharacters.add(
                new Character(860, 150, 50, 50, new ImageIcon("data/images/characters/playable/priest.png").getImage(),
                        "High Priest", 40, "mana", 30, 0, Smite, CleansingLight, Resurrect, 5));
        allTheCharacters.add(
                new Character(860, 150, 50, 50, new ImageIcon("data/images/characters/playable/rogue.png").getImage(),
                        "Shadow", 50, "focus", 10, 0, Stab, PoisonousFlask, Backstab, 1));
        allTheCharacters.add(
                new Character(860, 150, 50, 50, new ImageIcon("data/images/characters/playable/warlock.png").getImage(),
                        "Ebonlocke", 60, "mana", 15, 10, SiphonSoul, TouchOfDarkness, Doom, 4));
        allTheCharacters.add(
                new Character(860, 150, 50, 50, new ImageIcon("data/images/characters/playable/warrior.png").getImage(),
                        "Lo'Gosh", 80, "rage", 20, 5, Cleave, BlindingRage, Whirlwind, 6));

        GridBagLayout gridLayoutForCharacters = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 3;
        c.ipady = 70;
        c.ipadx = 60;
        c.insets = new Insets(0, 0, 5, 5);

        characterHolderPanel = new JPanel();
        characterHolderPanel.setOpaque(false);
        characterHolderPanel.setLayout(gridLayoutForCharacters);
        this.add(characterHolderPanel);

        for (int i = 0; i < allTheCharacters.size(); i++) {
            c.gridx = (i % 3) * 50;
            final JPanel tmpPanel = new JPanel(new BorderLayout());
            characterHolderPanel.add(new AlphaContainer(tmpPanel), c);
            final ImagePanel characterImage = new ImagePanel(allTheCharacters.get(i).getImage());
            characterImage.setPreferredSize(new Dimension(100, 100));
            tmpPanel.add(characterImage, BorderLayout.NORTH);
            isChosen.add(false);
            tmpPanel.setBackground(new Color(0, 0, 0, 128));
            final JLabel tmpLabel = new JLabel(allTheCharacters.get(i).getName(), SwingConstants.CENTER);
            tmpLabel.setForeground(Color.WHITE);
            tmpLabel.setFont(new Font("Arial", Font.BOLD, 12));
            tmpPanel.add(tmpLabel, BorderLayout.SOUTH);
            tmpPanel.setPreferredSize(new Dimension(50, 50));
            final int num = i;
            tmpPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent event) {
                    if (isChosen.get(num) == false && chosenCharacters.size() < 5) {
                        chosenCharacters.add(allTheCharacters.get(num));
                        tmpPanel.setBackground(Color.WHITE);
                        tmpLabel.setForeground(Color.BLACK);
                        isChosen.set(num, true);
                    } else {
                        chosenCharacters.remove(allTheCharacters.get(num));
                        tmpPanel.setBackground(new Color(0, 0, 0, 128));
                        tmpLabel.setForeground(Color.WHITE);
                        isChosen.set(num, false);
                    }
                }

                @Override
                public void mouseEntered(MouseEvent event) {
                    tmpPanel.setBorder(new LineBorder(Color.WHITE, 2));
                }

                @Override
                public void mouseExited(MouseEvent event) {
                    tmpPanel.setBorder(null);
                }
            });
        }
    }

    public ArrayList<Character> getAllTheCharacters() {
        return allTheCharacters;
    }

    public ArrayList<Character> getChosenCharacters() {
        return chosenCharacters;
    }
}
