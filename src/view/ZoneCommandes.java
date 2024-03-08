package src.view;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;


/**
 * Classe qui contient les commandes de mouvement du robot ( pas, stop, automatique…).
 * Il y a des choses à rajouter (keylistener).
 */
public class ZoneCommandes extends JPanel
{
    private JLabel commandesLabel;
    private JButton boutonPas;
    private JButton boutonStop;
    private JButton boutonAutomatique;
    private JLabel affichageVitesse;
    private int vitesse;
    private JButton boutonAccelerer;
    private JButton boutonRalentir;

    /**
     * Le modèle de bouton standard est un carré de 60px * 60px,
     * puis on ajuste en fonction de nos envies.
     */
    private int boutonSize = 60;

    public ZoneCommandes(Octopunks octopunks, Jeu jeu)
    {
        this.setLayout(null);
        this.setSize(jeu.zoneAssembleur.getWidth(), (int)octopunks.getDimension().getHeight()/3);
        this.setLocation((int)(jeu.zoneAssembleur.getX()),(int)(jeu.zoneAssembleur.getHeight()));
        this.setBackground(Color.darkGray);

        this.commandesLabel = new JLabel("Commandes");
        this.commandesLabel.setSize(100,20);
        this.commandesLabel.setLocation(60, 15);
        this.add(this.commandesLabel);


        this.boutonPas = new JButton("Pas");
        this.boutonPas.setBounds(40,80,boutonSize,boutonSize);
        this.boutonPas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                jeu.getZoneAssembleur().disableModification();

                int x = jeu.robot1.getPositionX();
                jeu.robot1.setPositionX(x+1);
                int y = jeu.robot1.getPositionY();
                jeu.robot1.setPositionY(y+3);

                //int diffSalle = jeu.robot1.getCurrentSalle().getLienEntrant().getSalleAvant().getId() -jeu.robot1.getCurrentSalle().getId();

                //System.out.println("x : "+jeu.robot1.getPositionX()+" et y : "+jeu.robot1.getPositionY());

                jeu.robot1.setPositionLabel(jeu.zoneMonde.getCoordonnees(y, x));
                jeu.zoneMemoire.update();
            }
        });
        this.add(this.boutonPas);


        // Initialisation du bouton pour l'exécution automatique
        this.boutonAutomatique = new JButton("Automatique");
        this.boutonAutomatique.setBounds(40,150,boutonSize*2,boutonSize);
        
        this.boutonAutomatique.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                jeu.zoneAssembleur.disableModification();
                jeu.robot1.setLesInstructions(jeu.zoneAssembleur.parse(jeu.zoneAssembleur.getAssembleurRobot1()));
                jeu.robot2.setLesInstructions(jeu.zoneAssembleur.parse(jeu.zoneAssembleur.getAssembleurRobot2()));
            }
        });
        this.add(this.boutonAutomatique);


        // Initialisation du bouton STOP
        this.boutonStop = new JButton("STOP");
        this.boutonStop.setBounds(100,80,boutonSize,boutonSize);
        
        this.boutonStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                jeu.zoneAssembleur.enableModification();
            }
        });
        this.add(this.boutonStop);


        this.boutonAccelerer = new JButton("+");
        this.boutonAccelerer.setSize(boutonSize,boutonSize);
        this.boutonAccelerer.setLocation(this.boutonStop.getX()+this.boutonStop.getWidth()+30,this.boutonStop.getY());
        this.boutonAccelerer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                // CPT
            }
        });
        this.add(this.boutonAccelerer);

        this.boutonRalentir = new JButton("-");
        this.boutonRalentir.setSize(boutonSize,boutonSize);

        this.boutonRalentir.setLocation(this.boutonAccelerer.getX(),this.boutonAutomatique.getY());
        this.boutonRalentir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                // CPT
            }
        });
        this.add(this.boutonRalentir);

        this.vitesse = 10;

        this.affichageVitesse = new JLabel("Vitesse : "+this.vitesse);
        this.affichageVitesse.setSize(100,20);
        this.affichageVitesse.setLocation(this.commandesLabel.getX()+this.commandesLabel.getWidth(), this.commandesLabel.getY());
        this.add(this.affichageVitesse);
    }
}
