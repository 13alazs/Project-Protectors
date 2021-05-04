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

public class CharacterSelectionPanel extends JPanel{
    private JPanel characterHolderPanel;
    
    private ArrayList<Character> allTheCharacters = new ArrayList<Character>();
    private ArrayList<Boolean> isChosen = new ArrayList<Boolean>();
    private ArrayList<Character> chosenCharacters = new ArrayList<Character>();
    private Ability Slash = new Ability("Slash", 0, 0, 30, "slashing", "enemy", 1, "attack");
    private Image tmpSprite = new ImageIcon("data/images/tmpSprite.png").getImage();
    
    public CharacterSelectionPanel(){
        super();
        
        allTheCharacters.add(new Character(860, 150, 30, 30, tmpSprite, "Bald Bob", 60, "energy", 20, 5, Slash, Slash, Slash, 3));
        allTheCharacters.add(new Character(860, 150, 30, 30, tmpSprite, "Super Mario", 60, "shroom", 20, 5, Slash, Slash, Slash, 3));
        allTheCharacters.add(new Character(860, 150, 30, 30, tmpSprite, "Balder Bob", 60, "energy", 20, 5, Slash, Slash, Slash, 3));
        allTheCharacters.add(new Character(860, 150, 30, 30, tmpSprite, "Superer Mario", 60, "shroom", 20, 5, Slash, Slash, Slash, 3));
        allTheCharacters.add(new Character(860, 150, 30, 30, tmpSprite, "Baldest Bob", 60, "energy", 20, 5, Slash, Slash, Slash, 3));
        allTheCharacters.add(new Character(860, 150, 30, 30, tmpSprite, "Supererer Mario", 60, "shroom", 20, 5, Slash, Slash, Slash, 3));
        allTheCharacters.add(new Character(860, 150, 30, 30, tmpSprite, "Random Boy", 60, "cheese", 20, 5, Slash, Slash, Slash, 3));
        
        GridBagLayout gridLayoutForCharacters = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 3;
        c.ipady = 60;
        c.ipadx = 60;
        c.insets = new Insets(0, 0, 5, 5);
        
        characterHolderPanel = new JPanel(gridLayoutForCharacters);
        this.add(characterHolderPanel);
        //characterHolderPanel.setBackground(Color.BLUE);

        for(int i = 0; i < allTheCharacters.size(); i++){
            c.gridx = (i % 3) * 50;
            final JPanel tmpPanel = new JPanel(new BorderLayout());
            characterHolderPanel.add(tmpPanel, c);
            isChosen.add(false);
            tmpPanel.setBackground(Color.GRAY);
            tmpPanel.add(new JLabel(allTheCharacters.get(i).getName()), BorderLayout.SOUTH);
            tmpPanel.setPreferredSize(new Dimension(50, 50));
            final int num = i;
            tmpPanel.addMouseListener(new MouseAdapter(){
                @Override
                public void mousePressed(MouseEvent event){
                    if(isChosen.get(num) == false && chosenCharacters.size() < 5){
                        chosenCharacters.add(allTheCharacters.get(num));
                        //System.out.println(chosenCharacters.size());
                        tmpPanel.setBackground(Color.GREEN);
                        isChosen.set(num, true);
                    }else{
                        chosenCharacters.remove(allTheCharacters.get(num));
                        //System.out.println(chosenCharacters.size());
                        tmpPanel.setBackground(Color.GRAY);
                        isChosen.set(num, false);
                    }
                }
                @Override
                public void mouseEntered(MouseEvent event){
                    tmpPanel.setBorder(new LineBorder(Color.RED));
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
