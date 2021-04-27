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

public class GameEngine extends JPanel{
    private String state = "MENU";
    
    private JPanel menuPanel; //Main menu
    private JPanel buttonPanel; //Extra panel for main menu, helps with button placement
    private JPanel missionsPanel; //Will be for mission selection
    private JPanel charactersPanel; //Will be for character selection
    private JPanel fightPanel; //Will be for the battle screen
    private JPanel resultPanel; //After the mission is finished either way, this will redirect to main menu
    
    private JButton missionsButton;
    private JButton exitButton;
    private JButton charactersButton;
    private JButton startButton;
    private JButton menuButtonM;
    private JButton menuButtonR;
    private JButton menuButtonC;
    
    private JLabel resultLabel;
    
    private JButton ability1Button;
    private JButton ability2Button;
    private JButton ability3Button;
    
    private GameManager Script;
    private Timer timer;
    private final int FPS=60;
    
    public GameEngine(){
        super();
        
        menuPanel=new JPanel();
        this.add(menuPanel);
        menuPanel.setBackground(Color.GRAY);
        menuPanel.setBounds(375, 200, 180, 350);
        menuPanel.setVisible(true);
        
        buttonPanel=new JPanel();
        buttonPanel.setBackground(Color.GRAY);
        buttonPanel.setBounds(375, 200, 180, 350);
        buttonPanel.setLayout(new GridLayout(4, 1));
        menuPanel.add(buttonPanel);
        
        missionsPanel=new JPanel();
        missionsPanel.setBackground(Color.GRAY);
        missionsPanel.setBounds(375, 200, 180, 350);
        missionsPanel.setVisible(false);
        this.add(missionsPanel);
        
        charactersPanel=new JPanel();
        charactersPanel.setBackground(Color.GRAY);
        charactersPanel.setBounds(375, 200, 180, 350);
        charactersPanel.setVisible(false);
        this.add(charactersPanel);
           
        fightPanel=new JPanel();
        fightPanel.setBackground(Color.GRAY);
        fightPanel.setBounds(375, 700, 270, 30);
        fightPanel.setVisible(false);
        fightPanel.setLayout(new GridLayout(1,3));
        ability1Button=new JButton("Ability1");
        ability1Button.setBackground(Color.ORANGE);
        ability1Button.setBorder(new LineBorder(Color.BLACK));
        ability1Button.setPreferredSize(new Dimension(90, 30));
        fightPanel.add(ability1Button);
        ability1Button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                System.out.println("Ability 1 has been cast");
            }
        });
        
        ability2Button=new JButton("Ability2");
        ability2Button.setBackground(Color.ORANGE);
        ability2Button.setBorder(new LineBorder(Color.BLACK));
        ability2Button.setPreferredSize(new Dimension(90, 30));
        fightPanel.add(ability2Button);
        ability2Button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                System.out.println("Ability 2 has been cast");
            }
        });
        
        ability3Button=new JButton("Ability3");
        ability3Button.setBackground(Color.ORANGE);
        ability3Button.setBorder(new LineBorder(Color.BLACK));
        ability3Button.setPreferredSize(new Dimension(90, 30));
        fightPanel.add(ability3Button);
        ability3Button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                System.out.println("Ability 3 has been cast");
            }
        });
        
        fightPanel.setVisible(false);
        this.add(fightPanel);
        
        resultPanel=new JPanel();
        resultLabel=new JLabel("Result");
        resultLabel.setBounds(425, 360, 100, 30);  
        resultPanel.add(resultLabel);
        resultPanel.setBackground(Color.GRAY);
        resultPanel.setBounds(375, 200, 180, 100);
        resultPanel.setVisible(false);
        resultPanel.setLayout(new GridLayout(2, 1));
        this.add(resultPanel);
        
        timer=new Timer(2000 / FPS, new NewFrameListener());
        Script=new GameManager(this);
        
        startButton=new JButton("Start mission");
        startButton.setBackground(Color.ORANGE);
        startButton.setBorder(new LineBorder(Color.BLACK));
        startButton.setPreferredSize(new Dimension(90, 30));
        buttonPanel.add(startButton);
        startButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                state="FIGHT";
                fightPanel.setVisible(true);
                menuPanel.setVisible(false);
                timer.start();
                
                //Script.basicSetup();
                
                ArrayList<Character> playerTeam = new ArrayList();
                Ability Slash = new Ability("Slash", 0, 0, 30, "slashing", "enemy", 1, "attack");
                Ability Heal = new Ability("Heal", 10, 2, -45, "none", "ally", 1, "heal");
                Ability Resurrect = new Ability("Resurrect", 20, 4, 0, "none", "ally", 1, "resurrect");
                Image tmpSprite = new ImageIcon("data/images/tmpSprite.png").getImage();
                Character Knight = new Character(150, 150, 50, 50, tmpSprite, "Knight", 120, "mana", 20, 10, Slash, Heal, Resurrect, 6);
                playerTeam.add(Knight);
                Training m = new Training();
                Script.Setup(m, playerTeam);
            }
        }); //Menu -> Battle
        
        missionsButton=new JButton("Mission select");
        missionsButton.setBackground(Color.ORANGE);
        missionsButton.setBorder(new LineBorder(Color.BLACK));
        missionsButton.setPreferredSize(new Dimension(90, 30));
        buttonPanel.add(missionsButton);
        missionsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                state="MISSIONS";
                missionsPanel.setVisible(true);
                menuPanel.setVisible(false);
            }
        }); //Menu -> Mission Select
        
        charactersButton=new JButton("Character select");
        charactersButton.setBackground(Color.ORANGE);
        charactersButton.setBorder(new LineBorder(Color.BLACK));
        charactersButton.setPreferredSize(new Dimension(90, 30));
        buttonPanel.add(charactersButton);
        charactersButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                state="CHARACTERS";
                charactersPanel.setVisible(true);
                menuPanel.setVisible(false);
            }
        }); //Menu -> haracter select
        
        menuButtonR=new JButton("Menu");
        menuButtonR.setBackground(Color.ORANGE);
        menuButtonR.setBorder(new LineBorder(Color.BLACK));
        menuButtonR.setPreferredSize(new Dimension(90, 30));
        resultPanel.add(menuButtonR);
        menuButtonR.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                state="MENU";
                resultPanel.setVisible(false);
                menuPanel.setVisible(true);
                timer.stop();
            }
        }); //Result screen -> Menu
        
        menuButtonM=new JButton("Menu");
        menuButtonM.setBackground(Color.ORANGE);
        menuButtonM.setBorder(new LineBorder(Color.BLACK));
        menuButtonM.setPreferredSize(new Dimension(90, 30));
        missionsPanel.add(menuButtonM);
        menuButtonM.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                state="MENU";
                missionsPanel.setVisible(false);
                menuPanel.setVisible(true);
            }
        }); //Mission select -> Menu
        
        menuButtonC=new JButton("Menu");
        menuButtonC.setBackground(Color.ORANGE);
        menuButtonC.setBorder(new LineBorder(Color.BLACK));
        menuButtonC.setPreferredSize(new Dimension(90, 30));
        charactersPanel.add(menuButtonC);
        menuButtonC.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                state="MENU";
                charactersPanel.setVisible(false);
                menuPanel.setVisible(true);
            }
        }); //Character select -> Menu
        
        exitButton=new JButton("Exit");
        exitButton.setBackground(Color.ORANGE);
        exitButton.setBorder(new LineBorder(Color.BLACK));
        exitButton.setPreferredSize(new Dimension(90, 30));
        buttonPanel.add(exitButton);
        exitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                System.exit(0);
            }
        }); //Exits to desktop
        
        this.setLayout(null);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        //g.drawImage(background, 0, 0, 1000, 800, null);
        if(state=="FIGHT"){
            ArrayList<Character> PlayerTeam = Script.getPlayerTeam();
            ArrayList<Character> EnemyTeam = Script.getEnemyTeam();
            for(Character c : PlayerTeam){
                c.draw(g);
            }
            for(Character c : EnemyTeam){
                c.draw(g);
            }
        }
    }
    
    class NewFrameListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae){
            repaint();
        }
    }
    
    public String getState(){
        return state;
    }

    public void setState(String state, boolean hasWon) {
        this.state = state;
        if(hasWon){
            resultLabel.setText("Mission complete");
        }
        else{
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

    public void setCharactersPanel(JPanel charactersPanel) {
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
    
    
}
