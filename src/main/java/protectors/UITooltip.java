package protectors;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UITooltip extends JPanel{
    private JLabel tooltipText;
    
    private String text;
    
    public UITooltip(int x, int y, Ability ability, String resource){
        super();
        
        this.setBackground(new Color(0, 0, 0, 192));
        this.setBounds(x, y, 260, 150);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        if("attack".equals(ability.getAbilityType())){
            text = "<html><font style='font-size: 15px;'>" + ability.getName() 
                    + "<br></font>Deal <font color='red'>" + ability.getAttackDamage() 
                    + "</font> damage to the targeted <font color='red'>enemy.</font>" 
                    + "<br>Cooldown: " + ability.getCooldown()
                    + " turns ,<br>" + resource + " cost: <font color='yellow'>" + ability.getCost() + "</font>"
                    + ",<br>target count: " + ability.getTargetCount() + "<html>";
        }else if("heal".equals(ability.getAbilityType())){
            text = "<html><font style='font-size: 15px;'>" + ability.getName() 
                    + "<br></font>Restore <font color='green'>" + Math.abs(ability.getAttackDamage())
                    + "</font> health to the targeted <font color='green'>ally</font>." 
                    + "<br>Cooldown: " + ability.getCooldown()
                    + " turns ,<br>" + resource + " cost: <font color='yellow'>" + ability.getCost() + "</font>"
                    + ",<br>target count: " + ability.getTargetCount() + "<html>";
        }else if("stun".equals(ability.getAbilityType())){
            text = "<html><font style='font-size: 15px;'>" + ability.getName() 
                    + "<br></font>Stuns an <font color='red'>enemy</font> for <font color='red'>" 
                    + "</font> 1 rounds."
                    + "<br>Cooldown: " + ability.getCooldown()
                    + " turns ,<br>" + resource + " cost: <font color='yellow'>" + ability.getCost() + "</font>"
                    + ",<br>target count: " + ability.getTargetCount() + "<html>";
        }else if("resurrect".equals(ability.getAbilityType())){
            text = "<html><font style='font-size: 15px;'>" + ability.getName() 
                    + "<br></font>Bring back an <font color='green'>ally</font> to life, or restore them <br> to full health."
                    + "<br>Cooldown: " + ability.getCooldown()
                    + " turns ,<br>" + resource + " cost: <font color='yellow'>" + ability.getCost() + "</font>"
                    + ",<br>target count: " + ability.getTargetCount() + "<html>";
        }
        
        tooltipText = new JLabel(text, JLabel.CENTER);
        tooltipText.setBounds(5, 5, 250, 140);
        tooltipText.setForeground(Color.WHITE);
        this.add(tooltipText);
    }
}
