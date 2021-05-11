package protectors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class CharacterSelectionPanel extends JPanel{
    //private final Image image;
    
    private ImagePanel characterHolderPanel;
    
    private ArrayList<Character> allTheCharacters = new ArrayList<Character>();
    private ArrayList<Boolean> isChosen = new ArrayList<Boolean>();
    private ArrayList<Character> chosenCharacters = new ArrayList<Character>();
    private Ability Slash = new Ability("Slash", 0, 0, 30, "slashing", "enemy", 1, "attack");
    
    public CharacterSelectionPanel(){
        super();
        
        //this.image = new ImageIcon("data/images/ui/uiBackground.png").getImage();
        
        allTheCharacters.add(new Character(860, 150, 30, 30, new ImageIcon("data/images/characters/playable/archer.png").getImage(), "Archer", 60, "rage", 20, 5, Slash, Slash, Slash, 3));
        allTheCharacters.add(new Character(860, 150, 30, 30, new ImageIcon("data/images/characters/playable/druid.png").getImage(), "Druid", 60, "shroom", 20, 5, Slash, Slash, Slash, 3));
        allTheCharacters.add(new Character(860, 150, 30, 30, new ImageIcon("data/images/characters/playable/knight.png").getImage(), "Knight", 60, "energy", 20, 5, Slash, Slash, Slash, 3));
        allTheCharacters.add(new Character(860, 150, 30, 30, new ImageIcon("data/images/characters/playable/mage.png").getImage(), "Mage", 60, "shroom", 20, 5, Slash, Slash, Slash, 3));
        allTheCharacters.add(new Character(860, 150, 30, 30, new ImageIcon("data/images/characters/playable/monk.png").getImage(), "Monk", 60, "energy", 20, 5, Slash, Slash, Slash, 3));
        allTheCharacters.add(new Character(860, 150, 30, 30, new ImageIcon("data/images/characters/playable/paladin.png").getImage(), "Paladin", 60, "shroom", 20, 5, Slash, Slash, Slash, 3));
        allTheCharacters.add(new Character(860, 150, 30, 30, new ImageIcon("data/images/characters/playable/priest.png").getImage(), "Priest", 60, "cheese", 20, 5, Slash, Slash, Slash, 3));
        allTheCharacters.add(new Character(860, 150, 30, 30, new ImageIcon("data/images/characters/playable/rogue.png").getImage(), "Rogue", 60, "cheese", 20, 5, Slash, Slash, Slash, 3));
        allTheCharacters.add(new Character(860, 150, 30, 30, new ImageIcon("data/images/characters/playable/warlock.png").getImage(), "Warlock", 60, "cheese", 20, 5, Slash, Slash, Slash, 3));
        allTheCharacters.add(new Character(860, 150, 30, 30, new ImageIcon("data/images/characters/playable/warrior.png").getImage(), "Warrior", 60, "cheese", 20, 5, Slash, Slash, Slash, 3));
        
        GridBagLayout gridLayoutForCharacters = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 4;
        c.ipady = 70;
        c.ipadx = 60;
        c.insets = new Insets(0, 0, 5, 5);
        
        characterHolderPanel = new ImagePanel(new ImageIcon("data/images/ui/uiBackground.png").getImage());
        characterHolderPanel.setLayout(gridLayoutForCharacters);
        this.add(characterHolderPanel);

        for(int i = 0; i < allTheCharacters.size(); i++){
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
            tmpPanel.addMouseListener(new MouseAdapter(){
                @Override
                public void mousePressed(MouseEvent event){
                    if(isChosen.get(num) == false && chosenCharacters.size() < 5){
                        chosenCharacters.add(allTheCharacters.get(num));
                        //System.out.println(chosenCharacters.size());
                        tmpPanel.setBackground(new Color(128, 255, 0, 128));
                        isChosen.set(num, true);
                    }else{
                        chosenCharacters.remove(allTheCharacters.get(num));
                        //System.out.println(chosenCharacters.size());
                        tmpPanel.setBackground(new Color(0, 0, 0, 128));
                        isChosen.set(num, false);
                    }
                }
                @Override
                public void mouseEntered(MouseEvent event){
                    tmpPanel.setBorder(new LineBorder(Color.DARK_GRAY));
                }
                @Override
                public void mouseExited(MouseEvent event){
                    tmpPanel.setBorder(null);
                }
            });
        }
    }
    
    public ArrayList<Character> getAllTheCharacters(){
        return allTheCharacters;
    }
    
    public ArrayList<Character> getChosenCharacters(){
        return chosenCharacters;
    }
}
