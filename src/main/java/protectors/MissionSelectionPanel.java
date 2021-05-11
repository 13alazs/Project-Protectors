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

public class MissionSelectionPanel extends JPanel {
    private JPanel missionHolderPanel;
    private ArrayList<Mission> allTheMissions = new ArrayList<Mission>();
    private ArrayList<Mission> chosenMission = new ArrayList<Mission>();
    private ArrayList<Boolean> isChosen = new ArrayList<Boolean>();

    public MissionSelectionPanel() {
        super();

        allTheMissions.add(new Training());
        allTheMissions.add(new Training2());
        allTheMissions.add(new Training3());

        GridBagLayout gridLayoutForMissions = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 0;
        c.ipady = 80;
        c.ipadx = 350;
        c.insets = new Insets(0, 0, 5, 5);

        missionHolderPanel = new ImagePanel(new ImageIcon("data/images/ui/uiBackground.png").getImage());
        missionHolderPanel.setLayout(gridLayoutForMissions);
        this.add(missionHolderPanel);
        // missionHolderPanel.setBackground(Color.red);

        for (int i = 0; i < allTheMissions.size(); i++) {
            isChosen.add(false);
            final JPanel tmpPanel = new JPanel(new BorderLayout());
            missionHolderPanel.add(new AlphaContainer(tmpPanel), c);
            final ImagePanel missionImage = new ImagePanel(allTheMissions.get(i).getBackground());
            missionImage.setPreferredSize(new Dimension(100, 100));
            tmpPanel.add(missionImage, BorderLayout.NORTH);
            tmpPanel.setBackground(new Color(0, 0, 0, 1));
            JLabel tmpLabel = new JLabel(allTheMissions.get(i).getName());
            tmpLabel.setForeground(Color.WHITE);
            tmpPanel.add(tmpLabel, BorderLayout.SOUTH);
            tmpPanel.setPreferredSize(new Dimension(40, 40));
            final int num = i;
            tmpPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent event) {
                    if (isChosen.get(num) == false && chosenMission.size() < 1) {
                        chosenMission.add(allTheMissions.get(num));
                        // System.out.println(chosenMission.get(num).getName());
                        tmpPanel.setBackground(Color.GREEN);
                        isChosen.set(num, true);
                    } else {
                        // System.out.println(chosenMission.get(num).getName());
                        chosenMission.remove(allTheMissions.get(num));
                        tmpPanel.setBackground(new Color(0, 0, 0, 1));
                        isChosen.set(num, false);
                    }
                }

                @Override
                public void mouseEntered(MouseEvent event) {
                    tmpPanel.setBorder(new LineBorder(Color.RED));
                }

                @Override
                public void mouseExited(MouseEvent event) {
                    tmpPanel.setBorder(null);
                }
            });
        }
    }

    public Mission getChosenMission() {
        if (chosenMission.size() != 1) {
            return null;
        }
        return chosenMission.get(0);
    }
}
