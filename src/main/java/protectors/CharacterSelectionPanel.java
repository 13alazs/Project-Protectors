package protectors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class CharacterSelectionPanel extends JPanel {
    // private final Image image;

    private ImagePanel characterHolderPanel;

    private ArrayList<Character> allTheCharacters = new ArrayList<Character>();
    private ArrayList<Boolean> isChosen = new ArrayList<Boolean>();
    private ArrayList<Character> chosenCharacters = new ArrayList<Character>();
    private Ability Slash = new Ability("Slash", 0, 0, 30, "slashing", "enemy", 1, "attack", "normal");
    private Ability Stab = new Ability("Stab", 0, 0, 25, "piercing", "enemy", 1, "attack", "normal");
    private Ability Punch = new Ability("Punch", 0, 0, 20, "blunt", "enemy", 1, "attack", "normal");
    private Ability Shoot = new Ability("Shoot", 0, 0, 35, "piercing", "enemy", 1, "attack", "normal");
    private Ability Fireball = new Ability("Fireball", 0, 0, 30, "fire", "enemy", 1, "attack", "normal");
    private Ability Cleave = new Ability("Cleave", 5, 1, 20, "slashing", "enemy", 2, "attack", "normal");
    private Ability Whirlwind = new Ability("Whirlwind", 10, 3, 15, "slashing", "enemy", 5, "attack", "normal");
    private Ability Firestorm = new Ability("Firestorm", 10, 3, 20, "fire", "enemy", 3, "attack", "random");
    private Ability Chaosbolt = new Ability("Chaosbolt", 15, 2, 45, "fire", "enemy", 1, "attack", "random");
    private Ability Blindfire = new Ability("Blindfire", 10, 3, 45, "piercing", "enemy", 1, "attack", "random");
    private Ability Piercingshot = new Ability("Piercingshot", 10, 1, 25, "piercing", "enemy", 2, "attack", "normal");
    private Ability Heal = new Ability("Heal", 10, 2, -45, "none", "ally", 1, "heal", "normal");
    private Ability Resurrect = new Ability("Resurrect", 20, 4, 0, "none", "ally", 1, "resurrect", "normal");
    private Ability Stun = new Ability("Stun", 10, 2, 0, "blunt", "enemy", 1, "stun", "normal");

    public CharacterSelectionPanel() {
        super();

        // this.image = new ImageIcon("data/images/ui/uiBackground.png").getImage();

        allTheCharacters.add(
                new Character(860, 150, 50, 50, new ImageIcon("data/images/characters/playable/archer.png").getImage(),
                        "Gerrie the Archer", 50, "focus", 10, 5, Shoot, Blindfire, Piercingshot, 2));
        allTheCharacters.add(
                new Character(860, 150, 50, 50, new ImageIcon("data/images/characters/playable/druid.png").getImage(),
                        "Runetotem", 40, "mana", 40, 0, Punch, Stun, Heal, 4));
        allTheCharacters.add(
                new Character(860, 150, 50, 50, new ImageIcon("data/images/characters/playable/knight.png").getImage(),
                        "Knight Lautrec", 100, "rage", 20, 10, Slash, Cleave, Whirlwind, 6));
        allTheCharacters.add(
                new Character(860, 150, 50, 50, new ImageIcon("data/images/characters/playable/mage.png").getImage(),
                        "Khadgar", 40, "mana", 40, 0, Fireball, Firestorm, Stun, 4));
        allTheCharacters.add(
                new Character(860, 150, 50, 50, new ImageIcon("data/images/characters/playable/monk.png").getImage(),
                        "Asmon Bald", 60, "focus", 10, 2, Punch, Stun, Fireball, 3));
        allTheCharacters.add(
                new Character(860, 150, 50, 50, new ImageIcon("data/images/characters/playable/paladin.png").getImage(),
                        "The Ashbringer", 90, "mana", 20, 20, Slash, Stun, Heal, 7));
        allTheCharacters.add(
                new Character(860, 150, 50, 50, new ImageIcon("data/images/characters/playable/priest.png").getImage(),
                        "High Priest", 40, "mana", 30, 0, Stab, Heal, Resurrect, 5));
        allTheCharacters.add(
                new Character(860, 150, 50, 50, new ImageIcon("data/images/characters/playable/rogue.png").getImage(),
                        "Shadow", 50, "focus", 10, 0, Stab, Stun, Whirlwind, 1));
        allTheCharacters.add(
                new Character(860, 150, 50, 50, new ImageIcon("data/images/characters/playable/warlock.png").getImage(),
                        "Ebonlocke", 60, "mana", 15, 10, Fireball, Firestorm, Chaosbolt, 4));
        allTheCharacters.add(
                new Character(860, 150, 50, 50, new ImageIcon("data/images/characters/playable/warrior.png").getImage(),
                        "Lo'Gosh", 80, "rage", 20, 5, Slash, Cleave, Whirlwind, 6));

        GridBagLayout gridLayoutForCharacters = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 4;
        c.ipady = 70;
        c.ipadx = 60;
        c.insets = new Insets(0, 0, 5, 5);

        characterHolderPanel = new ImagePanel(new ImageIcon("data/images/ui/uiBackground.png").getImage());
        characterHolderPanel.setLayout(gridLayoutForCharacters);
        this.add(characterHolderPanel);

        for (int i = 0; i < allTheCharacters.size(); i++) {
            c.gridx = (i % 4) * 50;
            final JPanel tmpPanel = new JPanel(new BorderLayout());
            characterHolderPanel.add(new AlphaContainer(tmpPanel), c);
            final ImagePanel characterImage = new ImagePanel(allTheCharacters.get(i).getImage());
            characterImage.setPreferredSize(new Dimension(100, 100));
            tmpPanel.add(characterImage, BorderLayout.NORTH);
            isChosen.add(false);
            tmpPanel.setBackground(new Color(0, 0, 0, 128));
            final JLabel tmpLabel = new JLabel((allTheCharacters.get(i).getName()));
            tmpLabel.setForeground(Color.BLACK);
            tmpPanel.add(tmpLabel, BorderLayout.SOUTH);
            tmpPanel.setPreferredSize(new Dimension(50, 50));
            final int num = i;
            tmpPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent event) {
                    if (isChosen.get(num) == false && chosenCharacters.size() < 5) {
                        chosenCharacters.add(allTheCharacters.get(num));
                        // System.out.println(chosenCharacters.size());
                        tmpPanel.setBackground(new Color(128, 255, 0, 128));
                        isChosen.set(num, true);
                    } else {
                        chosenCharacters.remove(allTheCharacters.get(num));
                        // System.out.println(chosenCharacters.size());
                        tmpPanel.setBackground(new Color(0, 0, 0, 128));
                        isChosen.set(num, false);
                    }
                }

                @Override
                public void mouseEntered(MouseEvent event) {
                    tmpPanel.setBorder(new LineBorder(Color.DARK_GRAY));
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
