package com.github._13alazs;

/*
    Project Name: Project Protectors
    Members: Árvai Balázs, Bíró Benjámin, Fodor Ádám, Zászlós Dorottya Beáta
*/

import java.awt.Dimension;
import javax.swing.JFrame;

public class GameFrame {
    private JFrame frame;
    private GameEngine panel;

    public GameFrame() {
        frame = new JFrame("Project Protectors");
        frame.setPreferredSize(new Dimension(1450, 850));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new GameEngine();
        frame.getContentPane().add(panel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
