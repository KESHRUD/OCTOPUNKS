package src.view;

import javax.swing.*;

import java.awt.*;

public class ZoneMemoire extends JPanel
{
    private JLabel labelRobot1;
    private JLabel labelRobot2;
    
    public ZoneMemoire(Octopunks octopunks, Jeu jeu)
    {
        this.setLayout(null);

        this.setSize((int)(jeu.getZoneCommandes().getWidth()),jeu.getZoneCommandes().getHeight()/2);
        this.setLocation((int)(jeu.zoneAssembleur.getWidth()*2),(int)jeu.zoneAssembleur.getHeight()+this.getHeight());
        this.setBackground(Color.YELLOW);
    
        this.labelRobot1 = new JLabel("XR1 : " + jeu.robot1.getPositionX() + " - YR1 : " + jeu.robot1.getPositionY());
        this.labelRobot1.setSize(this.getWidth()/2, this.getHeight()*1/4);
        this.labelRobot1.setLocation(0,(int)this.getHeight()*1/8);
        this.add(this.labelRobot1);


        this.labelRobot2 = new JLabel("XR2 : " + jeu.robot2.getPositionX() + " - YR2 : " + jeu.robot2.getPositionY());
        this.labelRobot2.setSize(this.getWidth(), this.getHeight()*1/4);
        this.labelRobot2.setLocation(0,this.getHeight()*3/8);
        this.add(this.labelRobot2);
    }
}
