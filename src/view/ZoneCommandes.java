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
    private int boutonWidth = 60;
    private int boutonHeight = boutonWidth;

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
        
        this.boutonPas.setBounds(40,80,boutonWidth,boutonHeight);
        this.boutonPas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                jeu.getZoneAssembleur().disableModification();
                jeu.robot1.setPositionX(2);
                jeu.robot1.setPositionLabel(jeu.zoneMonde.coordonneesCases[0][4].getX(), jeu.zoneMonde.coordonneesCases[1][0].getY());
                jeu.zoneMemoire.update();
            }
        });
        this.add(this.boutonPas);

        // Initialisation du bouton STOP
        this.boutonStop = new JButton("STOP");
        this.boutonStop.setBounds(100,80,boutonWidth,boutonHeight);
        
        this.boutonStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                jeu.zoneAssembleur.enableModification();
            }
        });
        this.add(this.boutonStop);


        // Initialisation du bouton pour l'exécution automatique
        this.boutonAutomatique = new JButton("Automatique");
        this.boutonAutomatique.setBounds(40,150,boutonWidth*2,boutonHeight);
        
        this.boutonAutomatique.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                jeu.zoneAssembleur.disableModification();
            }
        });
        this.add(this.boutonAutomatique);


        this.boutonAccelerer = new JButton("+");
        this.boutonAccelerer.setSize(boutonWidth,boutonHeight);
        this.boutonAccelerer.setLocation(this.boutonStop.getX()+this.boutonStop.getWidth()+30,this.boutonStop.getY());
        this.boutonAccelerer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                // CPT
            }
        });
        this.add(this.boutonAccelerer);

        this.boutonRalentir = new JButton("-");
        this.boutonRalentir.setSize(boutonWidth,boutonHeight);

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
