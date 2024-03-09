package src.view;

import javax.swing.*;

import java.awt.*;

public class ZoneMemoire extends JPanel
{
    private Jeu jeu;
    private JLabel labelRobot1;
    private JLabel labelRobot2;
    private JLabel gameIsFinished;
    
    public ZoneMemoire(Octopunks octopunks, Jeu jeu)
    {
        this.setLayout(null);
        this.jeu = jeu;

        this.setSize((int)(jeu.getZoneCommandes().getWidth()*3),jeu.getZoneCommandes().getHeight()/2);
        this.setLocation((int)(0),(int)jeu.zoneAssembleur.getHeight()+this.getHeight());
        this.setBackground(Color.YELLOW);
    
        this.labelRobot1 = new JLabel("XR1 : " + jeu.robot1.getPositionX() + " - YR1 : " + jeu.robot1.getPositionY() + " - Salle "+jeu.robot1.getCurrentSalle().getId());
        this.labelRobot1.setSize(this.getWidth()/2, this.getHeight()*1/4);
        this.labelRobot1.setLocation(0,(int)this.getHeight()*1/8);
        this.add(this.labelRobot1);

        this.labelRobot2 = new JLabel("XR2 : " + jeu.robot2.getPositionX() + " - YR2 : " + jeu.robot2.getPositionY() + " - Salle "+jeu.robot2.getCurrentSalle().getId());
        this.labelRobot2.setSize(this.getWidth(), this.getHeight()*1/4);
        this.labelRobot2.setLocation(0,this.getHeight()*3/8);
        this.add(this.labelRobot2);

        this.gameIsFinished = new JLabel("TEST");
        this.gameIsFinished.setSize(this.getWidth()*1/3,this.getHeight()*1/4);
        this.gameIsFinished.setLocation(this.labelRobot1.getX()+this.labelRobot1.getWidth(),this.labelRobot1.getY());
        this.add(this.gameIsFinished);


    }

    public void showGameIsFinished()
    {
        this.gameIsFinished.setText("Félicitations !");
    }

    /**
     * Permet de mettre à jour les valeurs de la zone mémoire
     */
    public void update()
    {
        this.labelRobot1.setText("XR1 : " + jeu.robot1.getPositionX() + " - YR1 : " + jeu.robot1.getPositionY() + " - Salle "+jeu.robot1.getCurrentSalle().getId());
        this.labelRobot2.setText("XR2 : " + jeu.robot2.getPositionX() + " - YR2 : " + jeu.robot2.getPositionY() + " - Salle "+jeu.robot2.getCurrentSalle().getId());
    }
}
