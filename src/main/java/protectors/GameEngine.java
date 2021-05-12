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
    private JPanel thisPanel = this;

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
    private UITooltip tooltipOne;
    private JButton ability2Button;
    private UITooltip tooltipTwo;
    private JButton ability3Button;
    private UITooltip tooltipThree;

    private GameManager Script;
    private Timer timer;
    private final int FPS = 60;

    public GameEngine() {
        super();

        playerTeam = new ArrayList<Character>();
        targetArrows = new ArrayList<Sprite>();

        menuPanel = new JPanel();

        this.add(menuPanel);
        menuPanel.setOpaque(false);
        menuPanel.setBounds(570, 250, 300, 250);
        menuPanel.setVisible(true);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));
        menuPanel.add(buttonPanel);

        missionsPanel = new MissionSelectionPanel();
        missionsPanel.setBackground(Color.BLACK);
        missionsPanel.setBounds(520, 210, 405, 460);
        missionsPanel.setVisible(false);
        this.add(missionsPanel);
        missionsPanel.setLayout(new BoxLayout(missionsPanel, BoxLayout.PAGE_AXIS));

        charactersPanel = new CharacterSelectionPanel();
        charactersPanel.setBackground(Color.BLACK);
        charactersPanel.setBounds(520, 220, 405, 550);
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
        ability1Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                thisPanel.add(tooltipOne);
            }

            @Override
            public void mouseExited(MouseEvent event) {
                thisPanel.remove(tooltipOne);
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
        ability2Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                thisPanel.add(tooltipTwo);
            }

            @Override
            public void mouseExited(MouseEvent event) {
                thisPanel.remove(tooltipTwo);
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
        ability3Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                thisPanel.add(tooltipThree);
            }

            @Override
            public void mouseExited(MouseEvent event) {
                thisPanel.remove(tooltipThree);
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
        resultLabel = new JLabel("Result", SwingConstants.CENTER);
        resultLabel.setBounds(625, 250, 200, 100);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 35));
        resultLabel.setBackground(Color.BLACK);
        resultLabel.setForeground(Color.WHITE);
        resultPanel.add(resultLabel);
        resultPanel.setBackground(Color.BLACK);
        resultPanel.setBounds(625, 250, 200, 120);
        resultPanel.setVisible(false);
        resultPanel.setLayout(new GridLayout(2, 1));
        this.add(resultPanel);

        timer = new Timer(2000 / FPS, new NewFrameListener());
        Script = new GameManager(this);

        startButton = new JButton("START GAME");
        startButton.setBackground(Color.BLACK);
        startButton.setFont(new Font("Arial", Font.BOLD, 25));
        startButton.setForeground(Color.WHITE);
        startButton.setBorder(new LineBorder(Color.WHITE, 2, true));
        startButton.setPreferredSize(new Dimension(300, 50));
        buttonPanel.add(startButton);
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                startButton.setBackground(Color.WHITE);
                startButton.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent event) {
                startButton.setBackground(Color.BLACK);
                startButton.setForeground(Color.WHITE);
            }
        });
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
                tooltipOne = new UITooltip(ability1Button.getLocationOnScreen().x - 500,
                        ability1Button.getLocationOnScreen().y - 300, Script.getCurrentCharacter().getAbility1(),
                        Script.getCurrentCharacter().getResourceName());
                tooltipTwo = new UITooltip(ability2Button.getLocationOnScreen().x - 500,
                        ability2Button.getLocationOnScreen().y - 300, Script.getCurrentCharacter().getAbility2(),
                        Script.getCurrentCharacter().getResourceName());
                tooltipThree = new UITooltip(ability3Button.getLocationOnScreen().x - 500,
                        ability3Button.getLocationOnScreen().y - 300, Script.getCurrentCharacter().getAbility3(),
                        Script.getCurrentCharacter().getResourceName());
            }
        }); // Menu -> Battle

        missionsButton = new JButton("SELECT MISSION");
        missionsButton.setBackground(Color.BLACK);
        missionsButton.setFont(new Font("Arial", Font.BOLD, 25));
        missionsButton.setForeground(Color.WHITE);
        missionsButton.setBorder(new LineBorder(Color.WHITE, 2, true));
        missionsButton.setPreferredSize(new Dimension(300, 50));
        buttonPanel.add(missionsButton);
        missionsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                missionsButton.setBackground(Color.WHITE);
                missionsButton.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent event) {
                missionsButton.setBackground(Color.BLACK);
                missionsButton.setForeground(Color.WHITE);
            }
        });
        missionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                state = "MISSIONS";
                missionsPanel.setVisible(true);
                menuPanel.setVisible(false);
            }
        }); // Menu -> Mission Select

        charactersButton = new JButton("SELECT CHARACTERS");
        charactersButton.setBackground(Color.BLACK);
        charactersButton.setFont(new Font("Arial", Font.BOLD, 25));
        charactersButton.setForeground(Color.WHITE);
        charactersButton.setBorder(new LineBorder(Color.WHITE, 2, true));
        charactersButton.setPreferredSize(new Dimension(300, 50));
        buttonPanel.add(charactersButton);
        charactersButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                charactersButton.setBackground(Color.WHITE);
                charactersButton.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent event) {
                charactersButton.setBackground(Color.BLACK);
                charactersButton.setForeground(Color.WHITE);
            }
        });
        charactersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                state = "CHARACTERS";
                charactersPanel.setVisible(true);
                menuPanel.setVisible(false);
            }
        }); // Menu -> Select Character

        menuButtonR = new JButton("Back");
        menuButtonR.setBackground(Color.BLACK);
        menuButtonR.setFont(new Font("Arial", Font.BOLD, 20));
        menuButtonR.setForeground(Color.WHITE);
        menuButtonR.setBorder(new LineBorder(Color.WHITE));
        menuButtonR.setPreferredSize(new Dimension(50, 20));
        resultPanel.add(menuButtonR);
        menuButtonR.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                menuButtonR.setBackground(Color.WHITE);
                menuButtonR.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent event) {
                menuButtonR.setBackground(Color.BLACK);
                menuButtonR.setForeground(Color.WHITE);
            }
        });
        menuButtonR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                state = "MENU";
                resultPanel.setVisible(false);
                menuPanel.setVisible(true);
                timer.stop();
            }
        }); // Result screen -> Menu

        menuButtonM = new JButton("BACK");
        menuButtonM.setBackground(Color.BLACK);
        menuButtonM.setFont(new Font("Arial", Font.BOLD, 20));
        menuButtonM.setForeground(Color.WHITE);
        menuButtonM.setBorder(new LineBorder(Color.WHITE));
        menuButtonM.setPreferredSize(new Dimension(100, 40));
        missionsPanel.add(menuButtonM);
        menuButtonM.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                menuButtonM.setBackground(Color.WHITE);
                menuButtonM.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent event) {
                menuButtonM.setBackground(Color.BLACK);
                menuButtonM.setForeground(Color.WHITE);
            }
        });
        menuButtonM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                currentMission = missionsPanel.getChosenMission();
                state = "MENU";
                missionsPanel.setVisible(false);
                menuPanel.setVisible(true);
            }
        }); // Mission select -> Menu

        menuButtonC = new JButton("BACK");
        menuButtonC.setBackground(Color.BLACK);
        menuButtonC.setFont(new Font("Arial", Font.BOLD, 20));
        menuButtonC.setForeground(Color.WHITE);
        menuButtonC.setBorder(new LineBorder(Color.WHITE));
        menuButtonC.setPreferredSize(new Dimension(100, 40));
        charactersPanel.add(menuButtonC);
        menuButtonC.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                menuButtonC.setBackground(Color.WHITE);
                menuButtonC.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent event) {
                menuButtonC.setBackground(Color.BLACK);
                menuButtonC.setForeground(Color.WHITE);
            }
        });
        menuButtonC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                playerTeam = charactersPanel.getChosenCharacters();
                state = "MENU";
                charactersPanel.setVisible(false);
                menuPanel.setVisible(true);
            }
        }); // Character select -> Menu

        exitButton = new JButton("EXIT");
        exitButton.setBackground(Color.BLACK);
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBorder(new LineBorder(Color.WHITE, 2, true));
        exitButton.setPreferredSize(new Dimension(240, 40));
        buttonPanel.add(exitButton);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                exitButton.setBackground(Color.WHITE);
                exitButton.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent event) {
                exitButton.setBackground(Color.BLACK);
                exitButton.setForeground(Color.WHITE);
            }
        });
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
        if (state == "MENU" || state == "MISSIONS" || state == "CHARACTERS" || state == "RESULT") {
            g.drawImage(new ImageIcon("data/images/backgrounds/menu_wide.jpg").getImage(), 0, 0, 1440, 810, null);
        }
        if (state == "FIGHT") {
            g.drawImage(currentMission.getBackground(), 0, 0, 1440, 810, null);
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
            resultLabel.setText("VICTORY");
        } else {
            resultLabel.setText("DEFEAT");
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
            updateTooltips();
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
            updateTooltips();
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
            updateTooltips();
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
            updateTooltips();
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
            updateTooltips();
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
            updateTooltips();
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
            updateTooltips();
        }
    }

    public void updateTooltips() {
        try {
            tooltipOne = new UITooltip(ability1Button.getLocationOnScreen().x - 500,
                    ability1Button.getLocationOnScreen().y - 300, Script.getCurrentCharacter().getAbility1(),
                    Script.getCurrentCharacter().getResourceName());
            tooltipTwo = new UITooltip(ability2Button.getLocationOnScreen().x - 500,
                    ability2Button.getLocationOnScreen().y - 300, Script.getCurrentCharacter().getAbility2(),
                    Script.getCurrentCharacter().getResourceName());
            tooltipThree = new UITooltip(ability3Button.getLocationOnScreen().x - 500,
                    ability3Button.getLocationOnScreen().y - 300, Script.getCurrentCharacter().getAbility3(),
                    Script.getCurrentCharacter().getResourceName());
        } catch (IllegalComponentStateException e) {
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
