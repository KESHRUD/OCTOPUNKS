package src.view;

import javax.swing.*;

import java.awt.*;
import java.util.Iterator;
import java.util.ListIterator;

public class ZoneMemoire extends JPanel
{
    private Jeu jeu;
    private JLabel labelRobot1;
    private JLabel labelRobot2;
    // private JLabel labelInfosFichier;
    private JLabel gameIsFinished;

    private JLabel valeursFichier;
    
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

        this.gameIsFinished = new JLabel("Finissez le jeu");
        this.gameIsFinished.setSize(this.getWidth()*1/3,this.getHeight()*1/4);
        this.gameIsFinished.setLocation(this.labelRobot1.getX()+this.labelRobot1.getWidth(),this.labelRobot1.getY());
        this.add(this.gameIsFinished);

        this.valeursFichier = new JLabel("Valeurs fichier :");
        this.valeursFichier.setSize(200,40);
        this.valeursFichier.setLocation(this.gameIsFinished.getX(),this.gameIsFinished.getY()+50);
        this.add(this.valeursFichier);
    }

    public void setLabelValeursFichier()
    {
        String texte = "Valeurs fichier : ";
        
        if(jeu.robot1.getInstruction().getTypeFichier() != null)
        {
            Iterator<Integer> iter = jeu.robot1.getInstruction().getIteratorFichier();
            while(iter.hasNext())
            {
                texte = texte+iter.next()+" ";
            }
        }
        if(jeu.robot2.getInstruction().getTypeFichier() != null)
        {
            Iterator<Integer> iter = jeu.robot2.getInstruction().getIteratorFichier();
            while(iter.hasNext())
            {
                texte = texte+iter.next()+" ";
            }
        }
        
        this.valeursFichier.setText(texte);
    }

    /**
     * Permet d'informer le joueur qu'il a fini le niveau.
     */
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
        setLabelValeursFichier();
        testConditionDeVictoire();
    }

    public void testConditionDeVictoire()
    {
        if(jeu.robot1.getIndex() == (jeu.robot1.getLesInstructions().size()-1))
        {
            if(jeu.robot2.getIndex() == (jeu.robot2.getLesInstructions().size()-1))
            {
                if(jeu.file.getPosition().getSalle().getId() == 3 && (jeu.file.getPosition().getX() != jeu.robot1.getPositionX()) && (jeu.file.getPosition().getY() != jeu.robot1.getPositionY()))
                {
                    if(jeu.file.getPosition().getX() != jeu.robot2.getPositionX() && (jeu.file.getPosition().getY() != jeu.robot2.getPositionY()))
                    {
                        jeu.isFinished = true;
                        showGameIsFinished();
                    }
                }
            }
        }
    }
}
