package protectors;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
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
    private ArrayList<Sprite> targetArrows;

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
        targetArrows = new ArrayList<Sprite>();

        menuPanel = new JPanel();

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
        // missionsPanel.setBackground(Color.GRAY);
        missionsPanel.setBounds(50, 50, 910, 740);
        missionsPanel.setVisible(false);
        this.add(missionsPanel);
        missionsPanel.setLayout(new BoxLayout(missionsPanel, BoxLayout.PAGE_AXIS));

        charactersPanel = new CharacterSelectionPanel();
        // charactersPanel.setBackground(Color.GRAY);
        charactersPanel.setBounds(50, 50, 910, 740);
        charactersPanel.setVisible(false);
        this.add(charactersPanel);
        charactersPanel.setLayout(new BoxLayout(charactersPanel, BoxLayout.PAGE_AXIS));

        fightPanel = new JPanel();

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
                    cast();
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
                if (state == "FIGHT") {
                    // System.out.println("Ability 2 has been cast");
                    tmpAbility = Script.getCurrentCharacter().getAbility2();
                    cast();
                }
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
                if (state == "FIGHT") {
                    // System.out.println("Ability 3 has been cast");
                    tmpAbility = Script.getCurrentCharacter().getAbility3();
                    cast();
                }
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
                    if ("attack".equals(tmpType) && tmpAbility.getTargetCount() == 1
                            && "enemy".equals(tmpAbility.getTargetType())
                            && "normal".equals(tmpAbility.getAbilityModifier())) {
                        singletargetNormalAttack(x, y);
                    }
                    if ("attack".equals(tmpType) && tmpAbility.getTargetCount() != 1
                            && "enemy".equals(tmpAbility.getTargetType())
                            && "normal".equals(tmpAbility.getAbilityModifier())) {
                        multitargetNormalAttack(x, y);
                    }
                    if ("attack".equals(tmpType) && tmpAbility.getTargetCount() != 1
                            && "enemy".equals(tmpAbility.getTargetType())
                            && "random".equals(tmpAbility.getAbilityModifier())) {
                        multitargetRandomAttack();
                    }
                    if ("attack".equals(tmpType) && tmpAbility.getTargetCount() == 1
                            && "enemy".equals(tmpAbility.getTargetType())
                            && "random".equals(tmpAbility.getAbilityModifier())) {
                        singletargetRandomAttack();
                    }
                    if ("heal".equals(tmpType) && tmpAbility.getTargetCount() == 1
                            && "ally".equals(tmpAbility.getTargetType())
                            && Script.getCurrentCharacter().getCurrResource() >= tmpAbility.getCost()) {
                        singletargetNormalHeal(x, y);
                    }
                    if ("resurrect".equals(tmpType) && tmpAbility.getTargetCount() == 1
                            && "ally".equals(tmpAbility.getTargetType())
                            && Script.getCurrentCharacter().getCurrResource() >= tmpAbility.getCost()) {
                        singletargetResurrect(x, y);
                    }
                    if ("stun".equals(tmpType) && tmpAbility.getTargetCount() == 1
                            && "enemy".equals(tmpAbility.getTargetType())
                            && Script.getCurrentCharacter().getCurrResource() >= tmpAbility.getCost()) {
                        singletargetStun(x, y);
                    }
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
                if (playerTeam.isEmpty()) {
                    Ability Slash = new Ability("Slash", 0, 0, 30, "slashing", "enemy", 1, "attack", "normal");
                    Ability Heal = new Ability("Heal", 10, 2, -45, "none", "ally", 1, "heal", "normal");
                    Ability Resurrect = new Ability("Resurrect", 20, 4, 0, "none", "ally", 1, "resurrect", "normal");
                    Image tmpSprite = new ImageIcon("data/images/characters/playable/knight.png").getImage();
                    Character Knight = new Character(150, 150, 50, 50, tmpSprite, "BasicKnight", 120, "mana", 20, 10,
                            Slash, Heal, Resurrect, 6);
                    playerTeam.add(Knight);
                }
                if (currentMission == null) {
                    currentMission = new Training();
                }
                Script.Setup(currentMission, playerTeam);
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
        }); // Menu -> Character select

        menuButtonR = new JButton("Menu");

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
            public void actionPerformed(ActionEvent ae) {
                playerTeam = charactersPanel.getChosenCharacters();
                state = "MENU";
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
        if (state == "FIGHT") {
            g.drawImage(currentMission.getBackground(), 0, 0, 1000, 800, null);
            ArrayList<Character> PlayerTeam = Script.getPlayerTeam();
            ArrayList<Character> EnemyTeam = Script.getEnemyTeam();
            for (Character c : PlayerTeam) {
                c.draw(g);
            }
            for (Character c : EnemyTeam) {
                c.draw(g);
            }
            if (casting) {
                for (Sprite s : targetArrows) {
                    s.draw(g);
                }
            }
        }
    }

    private void cast() {
        if (Script.getCurrentCharacter().getCurrResource() >= tmpAbility.getCost()) {
            tmpType = tmpAbility.getAbilityType();
            casting = true;
            targetArrowsSetup();
        } else {
            System.out.println("Not enough " + Script.getCurrentCharacter().getResourceName() + " ("
                    + Script.getCurrentCharacter().getCurrResource() + "/" + tmpAbility.getCost() + ")");
        }
    }

    private void targetArrowsSetup() {
        Image arrowSprite = new ImageIcon("data/images/ui/arrow.png").getImage();
        targetArrows = new ArrayList();
        if ("self".equals(tmpAbility.getTargetType())) {
            Sprite arrow = new Sprite(
                    Script.getCurrentCharacter().getX() + Script.getCurrentCharacter().getWidth() / 2 - 5,
                    Script.getCurrentCharacter().getY() - 15, 10, 10, arrowSprite);
            targetArrows.add(arrow);
        } else if ("ally".equals(tmpAbility.getTargetType())) {
            for (Character c : Script.getPlayerTeam()) {
                if ("resurrect".equals(tmpType)) {
                    if (!c.isAlive()) {
                        Sprite arrow = new Sprite(c.getX() + c.getWidth() / 2 - 5, c.getY() - 15, 10, 10, arrowSprite);
                        targetArrows.add(arrow);
                    }
                } else {
                    if (c.isAlive()) {
                        Sprite arrow = new Sprite(c.getX() + c.getWidth() / 2 - 5, c.getY() - 15, 10, 10, arrowSprite);
                        targetArrows.add(arrow);
                    }
                }
            }
        } else if ("enemy".equals(tmpAbility.getTargetType())) {
            for (Character c : Script.getEnemyTeam()) {
                if (c.isAlive()) {
                    Sprite arrow = new Sprite(c.getX() + c.getWidth() / 2 - 5, c.getY() - 15, 10, 10, arrowSprite);
                    targetArrows.add(arrow);
                }
            }
        } else if ("all".equals(tmpAbility.getTargetType())) {
            for (Character c : Script.getInitiativeTeam()) {
                if (c.isAlive()) {
                    Sprite arrow = new Sprite(c.getX() + c.getWidth() / 2 - 5, c.getY() - 15, 10, 10, arrowSprite);
                    targetArrows.add(arrow);
                }
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

    public void singletargetNormalAttack(int x, int y) {
        for (Character c : Script.getEnemyTeam()) {
            if (x >= c.getX() && x <= (c.getX() + c.getWidth()) && y >= c.getY() && y <= (c.getY() + c.getHeight())) {
                targets.add(c);
            }
        }
        if (targets.size() > 0) {
            Script.getCurrentCharacter().castAbility(tmpAbility, targets);
            tmpAbility = null;
            tmpType = null;
            casting = false;
            targets = null;
            Script.manage();
        }
    }

    public void multitargetNormalAttack(int x, int y) {
        for (int i = 0; i < Script.getEnemyTeam().size(); i++) {
            Character c = Script.getEnemyTeam().get(i);
            if (tmpAbility.getTargetCount() >= Script.getEnemyTeam().size()) {
                targets.addAll(Script.getEnemyTeam());
            } else {
                if (x >= c.getX() && x <= (c.getX() + c.getWidth()) && y >= c.getY()
                        && y <= (c.getY() + c.getHeight())) {
                    targets.add(c);
                    if (tmpAbility.getTargetCount() == 2) {
                        if (i != 0) {
                            targets.add(Script.getEnemyTeam().get(i - 1));
                        } else {
                            targets.add(Script.getEnemyTeam().get(i + 1));
                        }
                    }
                    if (tmpAbility.getTargetCount() == 3) {
                        if (i != 0 && i != Script.getEnemyTeam().size() - 1) {
                            targets.add(Script.getEnemyTeam().get(i - 1));
                            targets.add(Script.getEnemyTeam().get(i + 1));
                        } else if (i == 0) {
                            targets.add(Script.getEnemyTeam().get(i + 1));
                            targets.add(Script.getEnemyTeam().get(i + 2));
                        } else if (i == Script.getEnemyTeam().size() - 1) {
                            targets.add(Script.getEnemyTeam().get(i - 1));
                            targets.add(Script.getEnemyTeam().get(i - 2));
                        }
                    }
                    if (tmpAbility.getTargetCount() == 4) {
                        if (i != 0 && i != 1 && i != Script.getEnemyTeam().size() - 1) {
                            targets.add(Script.getEnemyTeam().get(i - 2));
                            targets.add(Script.getEnemyTeam().get(i - 1));
                            targets.add(Script.getEnemyTeam().get(i + 1));
                        } else if (i == 0) {
                            targets.add(Script.getEnemyTeam().get(i + 1));
                            targets.add(Script.getEnemyTeam().get(i + 2));
                            targets.add(Script.getEnemyTeam().get(i + 3));
                        } else if (i == 1) {
                            targets.add(Script.getEnemyTeam().get(i - 1));
                            targets.add(Script.getEnemyTeam().get(i + 2));
                            targets.add(Script.getEnemyTeam().get(i + 1));
                        } else if (i == Script.getEnemyTeam().size() - 1) {
                            targets.add(Script.getEnemyTeam().get(i - 1));
                            targets.add(Script.getEnemyTeam().get(i - 2));
                            targets.add(Script.getEnemyTeam().get(i - 3));
                        }
                    }
                }
            }
        }
        if (targets.size() > 0) {
            Script.getCurrentCharacter().castAbility(tmpAbility, targets);
            tmpAbility = null;
            tmpType = null;
            casting = false;
            targets = null;
            Script.manage();
        }
    }

    public void multitargetRandomAttack() {
        for (int i = 0; i < tmpAbility.getTargetCount(); i++) {
            Random rand = new Random();
            int randomTarget = rand.nextInt(tmpAbility.getTargetCount());
            targets.add(Script.getEnemyTeam().get(randomTarget));
        }
        if (targets.size() > 0) {
            Script.getCurrentCharacter().castAbility(tmpAbility, targets);
            tmpAbility = null;
            tmpType = null;
            casting = false;
            targets = null;
            Script.manage();
        }
    }

    public void singletargetRandomAttack() {
        Random rand = new Random();
        int randomTarget = rand.nextInt(tmpAbility.getTargetCount());
        targets.add(Script.getEnemyTeam().get(randomTarget));
        if (targets.size() > 0) {
            Script.getCurrentCharacter().castAbility(tmpAbility, targets);
            tmpAbility = null;
            tmpType = null;
            casting = false;
            targets = null;
            Script.manage();
        }
    }

    public void singletargetNormalHeal(int x, int y) {
        for (Character c : Script.getPlayerTeam()) {
            if (x >= c.getX() && x <= (c.getX() + c.getWidth()) && y >= c.getY() && y <= (c.getY() + c.getHeight())) {
                targets.add(c);
            }
        }
        if (targets.size() > 0) {
            Script.getCurrentCharacter().castAbility(tmpAbility, targets);
            tmpAbility = null;
            tmpType = null;
            casting = false;
            targets = null;
            Script.manage();
        }
    }

    public void singletargetResurrect(int x, int y) {
        for (Character c : Script.getPlayerTeam()) {
            if (x >= c.getX() && x <= (c.getX() + c.getWidth()) && y >= c.getY() && y <= (c.getY() + c.getHeight())
                    && !c.isAlive()) {
                targets.add(c);
            }
        }
        if (targets.size() > 0) {
            Script.getCurrentCharacter().castAbility(tmpAbility, targets);
            tmpAbility = null;
            tmpType = null;
            casting = false;
            targets = null;
            Script.manage();
        }
    }

    public void singletargetStun(int x, int y) {
        for (Character c : Script.getEnemyTeam()) {
            if (x >= c.getX() && x <= (c.getX() + c.getWidth()) && y >= c.getY() && y <= (c.getY() + c.getHeight())) {
                targets.add(c);
            }
        }
        if (targets.size() > 0) {
            Script.getCurrentCharacter().castAbility(tmpAbility, targets);
            tmpAbility = null;
            tmpType = null;
            casting = false;
            targets = null;
            Script.manage();
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
