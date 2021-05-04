package protectors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MissionSelectionPanel extends JPanel{
    private JPanel missionHolderPanel;
    
    private ArrayList<Mission> allTheMissions = new ArrayList<Mission>();
    private ArrayList<Mission> chosenMission = new ArrayList<Mission>();
    private ArrayList<Boolean> isChosen = new ArrayList<Boolean>();
    
    public MissionSelectionPanel(){
        super();
        
        allTheMissions.add(new Training());
        allTheMissions.add(new Training());
        allTheMissions.add(new Training());
        
        GridBagLayout gridLayoutForMissions = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 0;
        c.ipady = 80;
        c.ipadx = 350;
        c.insets = new Insets(0, 0, 5, 5);
        
        missionHolderPanel = new JPanel(gridLayoutForMissions);
        this.add(missionHolderPanel);
        //missionHolderPanel.setBackground(Color.red);
        
        for(int i = 0; i < allTheMissions.size(); i++){
            isChosen.add(false);
            final JPanel tmpPanel = new JPanel(new BorderLayout());
            missionHolderPanel.add(tmpPanel, c);
            tmpPanel.setBackground(Color.GRAY);
            tmpPanel.add(new JLabel(allTheMissions.get(i).getName()), BorderLayout.SOUTH);
            final int num = i;
            tmpPanel.addMouseListener(new MouseAdapter(){
                @Override
                public void mousePressed(MouseEvent event){
                    if(isChosen.get(num) == false && chosenMission.size() < 1){
                        chosenMission.add(allTheMissions.get(num));
                        //System.out.println(chosenMission.get(num).getName());
                        tmpPanel.setBackground(Color.GREEN);
                        isChosen.set(num, true);
                    }else{
                        //System.out.println(chosenMission.get(num).getName());
                        chosenMission.remove(allTheMissions.get(num));
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
    
    public Mission getChosenMission(){
        if(chosenMission.size() != 1){
            return null;
        }
        return chosenMission.get(0);
    }
}
