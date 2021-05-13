package protectors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class UITooltip extends JPanel {
    private JLabel tooltipText;

    private String name;
    private String resourceName;
    private String cost;
    private String damage;
    private String target;
    private String attackType;  //Fire, Ice, Blunt...
    private String abilityModifier; //Random target, Adjacent..
    private String resourcePerRound;
    
    private String text;

    public UITooltip(int x, int y, Ability ability, String resource) {
        super();

        this.setBackground(new Color(0, 0, 0, 192));
        this.setBorder(new LineBorder(Color.BLACK, 3));
        this.setBounds(x, y, 290, 100);
        this.setLayout(new BorderLayout());
        
        name = "<font style='font-size: 15px'>" + ability.getName() + "</font><br>";
        switch(resource){
            case "mana":
                resourceName = "<font color='rgb(15, 82, 186)'>" + resource + "</font>";
                cost = "<font color='rgb(15, 82, 186)'>" + ability.getCost() + "</font>";
                resourcePerRound = "+4 mana / turn.";
                break;
            case "focus":
                resourceName = "<font color='rgb(240, 230, 140)'>" + resource + "</font>";
                cost = "<font color='rgb(240, 230, 140)'>" + ability.getCost() + "</font>";
                resourcePerRound = "+4 focus / turn for each enemy alive.";
                break;
            case "rage":
                resourceName = "<font color='rgb(199, 0, 57)'>" + resource + "</font>";
                cost = "<font color='rgb(199, 0, 57)'>" + ability.getCost() + "</font>";
                resourcePerRound = "+4 rage / turn for each lost health points.";
                break;
            default:
                break;
        }
        switch(ability.getAttackType()){
            case "fire":
                attackType = "<font color='rgb(255, 87, 51)'>" + ability.getAttackType() + "</font>";
                break;
            case "frost":
                attackType = "<font color='rgb(137, 207, 240)'>" + ability.getAttackType() + "</font>";
                break;
            case "nature":
                attackType = "<font color='rgb(138, 154, 91)'>" + ability.getAttackType() + "</font>";
                break;
            case "holy":
                attackType = "<font color='rgb(225, 193, 110)'>" + ability.getAttackType() + "</font>";
                break;
            case "shadow":
                attackType = "<font color='rgb(54, 69, 79)'>" + ability.getAttackType() + "</font>";
                break;
            default:
                attackType = "<font color='rgb(131, 67, 51)'>" + ability.getAttackType() + "</font>";
                break;
        }
        switch(ability.getAbilityModifier()){
            case "normal":
                abilityModifier = ability.getTargetCount() + " targeted ";
                break;
            case "random":
                abilityModifier = ability.getTargetCount() + " random ";
                break;
        }
        switch(ability.getAbilityType()){
            case "resurrect":
                text = "<html>" + name + "Resurrect " + abilityModifier + " ally or restore them <br> to full health.<br>" +
                        "Cost: " + cost + " " + resourceName + ".<br>" + resourcePerRound + "</html>";
                break;
            case "stun":
                text = "<html>" + name + "Stun " + abilityModifier + " enemy for one round.<br>" +
                        "Cost: " + cost + " " + resourceName + ".<br>" + resourcePerRound + "</html>";
                break;
            case "heal":
                text = "<html>" + name + "Restore " + "<font color='rgb(0, 163, 108)'>" + ability.getAttackDamage() * -1 + "</font>" + " health to " + abilityModifier + "ally.<br>" +
                        "Cost: " + cost + " " + resourceName + ".<br>" + resourcePerRound + "</html>";
                break;
            default:
                text = "<html>" + name + "Deal " + "<font color='rgb(154, 42, 42)'>" + ability.getAttackDamage() + "</font> " + attackType + " damage to " + abilityModifier + " enemy.<br>" +
                        "Cost: " + cost + " " + resourceName + ".<br>" + resourcePerRound + "</html>";
                break;
        }

        tooltipText = new JLabel(text);
        tooltipText.setBounds(10, 0, 290, 100);
        tooltipText.setForeground(Color.WHITE);
        this.add(tooltipText, BorderLayout.NORTH);
    }
}
