package src.view;

import javax.swing.*;

import src.model.Coordonnees;

import java.awt.event.*;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.awt.*;


/**
 * Classe qui contient les commandes de mouvement du robot ( pas, stop, automatique…).
 * Il y a des choses à rajouter (keylistener).
 */
public class ZoneCommandes extends JPanel
{
    private JLabel affichageVitesse;
    private JLabel commandesLabel;
    private JButton boutonAccelerer;
    private JButton boutonAutomatique;
    private JButton boutonPas;
    private JButton boutonRalentir;
    private JButton boutonReset;
    private JButton boutonStop;
    private Timer timer;

    private int delai = 1000; // délai en millisecondes

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

        jeu.robot1.setLesInstructions(jeu.zoneAssembleur.parse(jeu.zoneAssembleur.getAssembleurRobot1()));
        jeu.robot2.setLesInstructions(jeu.zoneAssembleur.parse(jeu.zoneAssembleur.getAssembleurRobot2()));

        setBoutonPas(jeu);
        setBoutonAutomatique(jeu);
        setBoutonStop(jeu);
        
        setBoutonAccelerer(jeu);
        setBoutonRalentir(jeu);
        setBoutonReset(jeu);


        this.affichageVitesse = new JLabel("Vitesse : "+ZoneCommandes.this.delai/10 + "%");
        this.affichageVitesse.setSize(100,20);
        this.affichageVitesse.setLocation(this.commandesLabel.getX()+this.commandesLabel.getWidth(), this.commandesLabel.getY());
        this.add(this.affichageVitesse);
    }


    private void actionBoutonAutomatique(Jeu jeu)
    {
        Random random = new Random();

        // Désactiver la modification de la zone d'assemblage
        jeu.getZoneAssembleur().disableModification();
        jeu.robot1.setLesInstructions(jeu.zoneAssembleur.parse(jeu.zoneAssembleur.getAssembleurRobot1()));
        jeu.robot2.setLesInstructions(jeu.zoneAssembleur.parse(jeu.zoneAssembleur.getAssembleurRobot2()));

        int index = jeu.robot1.getIndex();
        int size = jeu.robot1.getLesInstructions().size();

        timer = new Timer(delai, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < size) {
                    // Exécuter l'instruction
                    if(random.nextInt(2) == 0)
                    {
                        try {
                            jeu.robot1.executeInstruction();
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        // on fait -1 car le tableau va de 0 à 4 <=> 1-1 à 5-1
                
                        jeu.robot1.moveGraphique(jeu.zoneMonde);
                    } else {
                        try {
                            jeu.robot2.executeInstruction();
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        // on fait -1 car le tableau va de 0 à 4 <=> 1-1 à 5-1
                        jeu.robot2.moveGraphique(jeu.zoneMonde);
                    }
                    jeu.zoneMemoire.update();
                    jeu.zoneAssembleur.update();
                } else {
                    // Arrêter le timer lorsque toutes les instructions ont été exécutées
                    ((Timer) e.getSource()).stop();
                    jeu.getZoneAssembleur().enableModification();
                }
            }
        });

        // Démarrer le timer
        timer.start();
    }

    private void actionBoutonPas(Jeu jeu) throws IOException
    {
        Random random = new Random();
        //System.out.println("SSS");
        jeu.getZoneAssembleur().disableModification();
        jeu.robot1.setLesInstructions(jeu.zoneAssembleur.parse(jeu.zoneAssembleur.getAssembleurRobot1()));
        jeu.robot2.setLesInstructions(jeu.zoneAssembleur.parse(jeu.zoneAssembleur.getAssembleurRobot2()));

        if(random.nextInt(2) == 0)
        {
            jeu.robot1.executeInstruction();
            // on fait -1 car le tableau va de 0 à 4 <=> 1-1 à 5-1
                
            jeu.robot2.moveGraphique(jeu.zoneMonde);
        }
        else
        {
            jeu.robot2.executeInstruction();
            // on fait -1 car le tableau va de 0 à 4 <=> 1-1 à 5-1
            jeu.robot2.moveGraphique(jeu.zoneMonde);
        }

        jeu.zoneMemoire.update();
        jeu.zoneAssembleur.update();
    }

    private void setBoutonAccelerer(Jeu jeu)
    {
        this.boutonAccelerer = new JButton("+");
        this.boutonAccelerer.setSize(boutonSize,boutonSize);
        this.boutonAccelerer.setLocation(this.boutonStop.getX()+this.boutonStop.getWidth()+30,this.boutonStop.getY());
        this.boutonAccelerer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if(ZoneCommandes.this.delai > 250)
                {
                    ZoneCommandes.this.delai -= 250;
                    ZoneCommandes.this.affichageVitesse.setText("Vitesse : "+ZoneCommandes.this.delai/10 + "%");
                }
            }
        });
        this.add(this.boutonAccelerer);
    }

    private void setBoutonAutomatique(Jeu jeu)
    {
        // Initialisation du bouton pour l'exécution automatique
        this.boutonAutomatique = new JButton("Automatique");
        this.boutonAutomatique.setBounds(40,150,boutonSize*2,boutonSize);
        
        this.boutonAutomatique.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                // System.out.println("SSS");
                ZoneCommandes.this.actionBoutonAutomatique(jeu);

            }
        });
        this.add(this.boutonAutomatique);
    }

    private void setBoutonPas(Jeu jeu)
    {
        this.boutonPas = new JButton("Pas");
        this.boutonPas.setBounds(40,80,boutonSize,boutonSize);
        this.boutonPas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    ZoneCommandes.this.actionBoutonPas(jeu);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        this.add(this.boutonPas);
    }

    private void setBoutonRalentir(Jeu jeu)
    {
        this.boutonRalentir = new JButton("-");
        this.boutonRalentir.setSize(boutonSize,boutonSize);

        this.boutonRalentir.setLocation(this.boutonAccelerer.getX(),this.boutonAutomatique.getY());
        this.boutonRalentir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if(ZoneCommandes.this.delai < 2000)
                {
                    ZoneCommandes.this.delai += 250;
                    ZoneCommandes.this.affichageVitesse.setText("Vitesse : "+ZoneCommandes.this.delai/10 + "%");
                }
            }
        });
        this.add(this.boutonRalentir);
    }

    private void setBoutonReset(Jeu jeu)
    {
        this.boutonReset = new JButton("Reset");
        this.boutonReset.setSize(boutonSize,boutonSize);

        this.boutonReset.setLocation(this.boutonAutomatique.getX(),this.boutonAutomatique.getY()+boutonReset.getHeight());
        this.boutonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                jeu.robot1.setCurrentSalle(jeu.salle1);
                jeu.robot1.setIndexToUn();
                jeu.robot2.setPositionX(2);
                jeu.robot2.setPositionY(1);
                jeu.robot2.setCurrentSalle(jeu.salle1);
                jeu.robot2.setIndexToUn();
            }
        });
        this.add(this.boutonReset);
    }

    private void setBoutonStop(Jeu jeu)
    {
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
    }

}
