package protectors;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/*
    Project Name: Project Protectors
    Members: Árvai Balázs, Bíró Benjámin, Fodor Ádám, Zászlós Dorottya Beáta
*/

public class GameEngine extends JPanel{
    private enum gameState{MENU, MISSIONS, CHARACTERS, FIGHT, RESULT}
    gameState state = gameState.MENU;
    
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
        fightPanel.setVisible(false);
        this.add(fightPanel);
        
        resultPanel=new JPanel();
        resultPanel.setBackground(Color.GRAY);
        resultPanel.setBounds(375, 200, 180, 350);
        resultPanel.setVisible(false);
        this.add(resultPanel);
        
        startButton=new JButton("Start mission");
        startButton.setBackground(Color.ORANGE);
        startButton.setBorder(new LineBorder(Color.BLACK));
        startButton.setPreferredSize(new Dimension(90, 30));
        buttonPanel.add(startButton);
        startButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                state=gameState.FIGHT;
                fightPanel.setVisible(true);
                menuPanel.setVisible(false);
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
                state=gameState.MISSIONS;
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
                state=gameState.CHARACTERS;
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
                state=gameState.MENU;
                resultPanel.setVisible(false);
                menuPanel.setVisible(true);
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
                state=gameState.MENU;
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
                state=gameState.MENU;
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
}
