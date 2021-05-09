package protectors;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

/*
    Project Name: Project Protectors
    Members: Árvai Balázs, Bíró Benjámin, Fodor Ádám, Zászlós Dorottya Beáta
*/

public class GameEngine extends JPanel {
    private String state = "MENU";


    private JPanel menuPanel; // Main menu
    private JPanel buttonPanel; // Extra panel for main menu, helps with button placement
    private CharacterSelectionPanel charactersPanel; // Will be for character selection
    private MissionSelectionPanel missionsPanel; // Will be for mission selection
    private JPanel fightPanel; // Will be for the battle screen
    private JPanel resultPanel; // After the mission is finished either way, this will redirect to main menu

    private JButton missionsButton;
    private JButton exitButton;
    private JButton charactersButton;
    private JButton startButton;
    private JButton menuButtonM;
    private JButton menuButtonR;
    private JButton menuButtonC;

    private JLabel resultLabel;

    private Ability tmpAbility;
    private String tmpType;
    private boolean casting;

    private ArrayList<Character> targets;
    private ArrayList<Character> playerTeam;

    private Mission currentMission;

    
    private JButton ability1Button;
    private JButton ability2Button;
    private JButton ability3Button;

    private GameManager Script;
    private Timer timer;
    private final int FPS = 60;

    public GameEngine() {
        super();
        
        playerTeam = new ArrayList<Character>();
        
        menuPanel=new JPanel();

        this.add(menuPanel);
        menuPanel.setBackground(Color.GRAY);
        menuPanel.setBounds(375, 200, 180, 350);
        menuPanel.setVisible(true);

        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.GRAY);
        buttonPanel.setBounds(375, 200, 180, 350);
        buttonPanel.setLayout(new GridLayout(4, 1));
        menuPanel.add(buttonPanel);

        missionsPanel = new MissionSelectionPanel();
        missionsPanel.setBackground(Color.GRAY);
        missionsPanel.setBounds(50, 50, 910, 740);
        missionsPanel.setVisible(false);
        this.add(missionsPanel);    
        missionsPanel.setLayout(new BoxLayout(missionsPanel, BoxLayout.PAGE_AXIS));

        charactersPanel=new CharacterSelectionPanel();
        charactersPanel.setBackground(Color.GRAY);
        charactersPanel.setBounds(50, 50, 910, 740);
        charactersPanel.setVisible(false);
        this.add(charactersPanel);
        charactersPanel.setLayout(new BoxLayout(charactersPanel, BoxLayout.PAGE_AXIS));
           
        fightPanel=new JPanel();

        fightPanel.setBackground(Color.GRAY);
        fightPanel.setBounds(375, 700, 270, 30);
        fightPanel.setVisible(false);
        fightPanel.setLayout(new GridLayout(1, 3));
        ability1Button = new JButton("Ability1");
        ability1Button.setBackground(Color.ORANGE);
        ability1Button.setBorder(new LineBorder(Color.BLACK));
        ability1Button.setPreferredSize(new Dimension(90, 30));
        fightPanel.add(ability1Button);
        ability1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (state == "FIGHT") {
                    // System.out.println("Ability 1 has been cast");
                    tmpAbility = Script.getCurrentCharacter().getAbility1();
                    tmpType = tmpAbility.getAbilityType();
                    casting = true;
                }
            }
        });

        ability2Button = new JButton("Ability2");
        ability2Button.setBackground(Color.ORANGE);
        ability2Button.setBorder(new LineBorder(Color.BLACK));
        ability2Button.setPreferredSize(new Dimension(90, 30));
        fightPanel.add(ability2Button);
        ability2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("Ability 2 has been cast");
            }
        });

        ability3Button = new JButton("Ability3");
        ability3Button.setBackground(Color.ORANGE);
        ability3Button.setBorder(new LineBorder(Color.BLACK));
        ability3Button.setPreferredSize(new Dimension(90, 30));
        fightPanel.add(ability3Button);
        ability3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("Ability 3 has been cast");
            }
        });

        fightPanel.setVisible(false);
        this.add(fightPanel);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && casting) {
                    int x = e.getX();
                    int y = e.getY();
                    targets = new ArrayList();
                    if (tmpType == "attack" && tmpAbility.getTargetCount() == 1
                            && tmpAbility.getTargetType() == "enemy") {
                        for (Character c : Script.getEnemyTeam()) {
                            if (x >= c.getX() && x <= (c.getX() + c.getWidth()) && y >= c.getY()
                                    && y <= (c.getY() + c.getHeight())) {
                                targets.add(c);
                            }
                        }
                        Script.getCurrentCharacter().castAbility(tmpAbility, targets);
                    }
                    tmpAbility = null;
                    tmpType = null;
                    casting = false;
                    targets = null;
                    Script.manage();
                }
            }
        });

        resultPanel = new JPanel();
        resultLabel = new JLabel("Result");
        resultLabel.setBounds(425, 360, 100, 30);
        resultPanel.add(resultLabel);
        resultPanel.setBackground(Color.GRAY);
        resultPanel.setBounds(375, 200, 180, 100);
        resultPanel.setVisible(false);
        resultPanel.setLayout(new GridLayout(2, 1));
        this.add(resultPanel);

        timer = new Timer(2000 / FPS, new NewFrameListener());
        Script = new GameManager(this);

        startButton = new JButton("Start mission");
        startButton.setBackground(Color.ORANGE);
        startButton.setBorder(new LineBorder(Color.BLACK));
        startButton.setPreferredSize(new Dimension(90, 30));
        buttonPanel.add(startButton);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                state = "FIGHT";
                fightPanel.setVisible(true);
                menuPanel.setVisible(false);
                timer.start();

                // Script.basicSetup();

                ArrayList<Character> playerTeam = new ArrayList();
                Ability Slash = new Ability("Slash", 0, 0, 30, "slashing", "enemy", 1, "attack");
                Ability Heal = new Ability("Heal", 10, 2, -45, "none", "ally", 1, "heal");
                Ability Resurrect = new Ability("Resurrect", 20, 4, 0, "none", "ally", 1, "resurrect");
                Image tmpSprite = new ImageIcon("data/images/tmpSprite.png").getImage();
                Character Knight = new Character(150, 150, 50, 50, tmpSprite, "Knight", 120, "mana", 20, 10, Slash,
                        Heal, Resurrect, 6);
                playerTeam.add(Knight);
                Training m = new Training();
                Script.Setup(m, playerTeam);
            }
        }); // Menu -> Battle

        missionsButton = new JButton("Mission select");
        missionsButton.setBackground(Color.ORANGE);
        missionsButton.setBorder(new LineBorder(Color.BLACK));
        missionsButton.setPreferredSize(new Dimension(90, 30));
        buttonPanel.add(missionsButton);
        missionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                state = "MISSIONS";
                missionsPanel.setVisible(true);
                menuPanel.setVisible(false);
            }
        }); // Menu -> Mission Select

        charactersButton = new JButton("Character select");
        charactersButton.setBackground(Color.ORANGE);
        charactersButton.setBorder(new LineBorder(Color.BLACK));
        charactersButton.setPreferredSize(new Dimension(90, 30));
        buttonPanel.add(charactersButton);
        charactersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                state = "CHARACTERS";
                charactersPanel.setVisible(true);
                menuPanel.setVisible(false);
            }
        }); //Menu -> Character select
        
        menuButtonR=new JButton("Menu");

        menuButtonR.setBackground(Color.ORANGE);
        menuButtonR.setBorder(new LineBorder(Color.BLACK));
        menuButtonR.setPreferredSize(new Dimension(90, 30));
        resultPanel.add(menuButtonR);
        menuButtonR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                state = "MENU";
                resultPanel.setVisible(false);
                menuPanel.setVisible(true);
                timer.stop();
            }
        }); // Result screen -> Menu

        menuButtonM = new JButton("Menu");
        menuButtonM.setBackground(Color.ORANGE);
        menuButtonM.setBorder(new LineBorder(Color.BLACK));
        menuButtonM.setPreferredSize(new Dimension(90, 30));
        missionsPanel.add(menuButtonM);
        menuButtonM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                currentMission = missionsPanel.getChosenMission();
                state = "MENU";
                missionsPanel.setVisible(false);
                menuPanel.setVisible(true);
            }
        }); // Mission select -> Menu

        menuButtonC = new JButton("Menu");
        menuButtonC.setBackground(Color.ORANGE);
        menuButtonC.setBorder(new LineBorder(Color.BLACK));
        menuButtonC.setPreferredSize(new Dimension(90, 30));
        charactersPanel.add(menuButtonC);
        menuButtonC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                playerTeam = charactersPanel.getChosenCharacters();
                state="MENU";
                charactersPanel.setVisible(false);
                menuPanel.setVisible(true);
            }
        }); // Character select -> Menu

        exitButton = new JButton("Exit");
        exitButton.setBackground(Color.ORANGE);
        exitButton.setBorder(new LineBorder(Color.BLACK));
        exitButton.setPreferredSize(new Dimension(90, 30));
        buttonPanel.add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        }); // Exits to desktop

        this.setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // g.drawImage(background, 0, 0, 1000, 800, null);
        if (state == "FIGHT") {
            ArrayList<Character> PlayerTeam = Script.getPlayerTeam();
            ArrayList<Character> EnemyTeam = Script.getEnemyTeam();
            for (Character c : PlayerTeam) {
                c.draw(g);
            }
            for (Character c : EnemyTeam) {
                c.draw(g);
            }
        }
    }

    class NewFrameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            repaint();
        }
    }

    public String getState() {
        return state;
    }

    public void setState(String state, boolean hasWon) {
        this.state = state;
        if (hasWon) {
            resultLabel.setText("Mission complete");
        } else {
            resultLabel.setText("Mission failed");
        }
    }

    public JPanel getMenuPanel() {
        return menuPanel;
    }

    public void setMenuPanel(JPanel menuPanel) {
        this.menuPanel = menuPanel;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public JPanel getCharactersPanel() {
        return charactersPanel;
    }

    public void setCharactersPanel(CharacterSelectionPanel charactersPanel) {
        this.charactersPanel = charactersPanel;
    }

    public JPanel getFightPanel() {
        return fightPanel;
    }

    public void setFightPanel(JPanel fightPanel) {
        this.fightPanel = fightPanel;
    }

    public JPanel getResultPanel() {
        return resultPanel;
    }

    public void setResultPanel(JPanel resultPanel) {
        this.resultPanel = resultPanel;
    }

    public JButton getAbility1Button() {
        return ability1Button;
    }

    public void setAbility1Button(JButton ability1Button) {
        this.ability1Button = ability1Button;
    }

    public JButton getAbility2Button() {
        return ability2Button;
    }

    public void setAbility2Button(JButton ability2Button) {
        this.ability2Button = ability2Button;
    }

    public JButton getAbility3Button() {
        return ability3Button;
    }

    public void setAbility3Button(JButton ability3Button) {
        this.ability3Button = ability3Button;
    }
}
