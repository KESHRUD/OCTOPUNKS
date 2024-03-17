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
    private JButton boutonStop;
    private Timer timer;
    private Random random;

    private int delai = 1000; // délai en millisecondes

    private int vitesse = 100;

    private boolean isRunning;

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

        this.isRunning = false;
        this.random = new Random();

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

        this.affichageVitesse = new JLabel("Vitesse : "+ZoneCommandes.this.vitesse+"%");
        this.affichageVitesse.setSize(150,20);
        this.affichageVitesse.setLocation(this.commandesLabel.getX()+this.commandesLabel.getWidth(), this.commandesLabel.getY());
        this.add(this.affichageVitesse);
    }


    private void actionBoutonAutomatique(Jeu jeu)
    {
        // Désactiver la modification de la zone d'assemblage
        jeu.getZoneAssembleur().disableModification();
        jeu.robot1.setLesInstructions(jeu.zoneAssembleur.parse(jeu.zoneAssembleur.getAssembleurRobot1()));
        jeu.robot2.setLesInstructions(jeu.zoneAssembleur.parse(jeu.zoneAssembleur.getAssembleurRobot2()));

        int index = jeu.robot1.getIndex();
        int size = jeu.robot1.getLesInstructions().size();

        timer = new Timer(delai, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < size && isRunning) {
                    // Exécuter l'instruction
                    if(random.nextInt(2) == 0)
                    {
                        try {
                            jeu.robot1.executeInstruction();
                            /*
                            System.out.println("FR1 : "+ jeu.robot1.getRegistreF());
                            System.out.println("MR1 : "+ jeu.robot1.getRegistreM());
                            System.out.println("TR1 : "+ jeu.robot1.getRegistreT());
                            System.out.println("XR1 : "+ jeu.robot1.getRegistreX());
                            System.out.println();
                            */
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                        jeu.robot1.moveGraphique(jeu.zoneMonde);
                    } else {
                        try {
                            jeu.robot2.executeInstruction();
                            /*
                            System.out.println("FR2 : "+ jeu.robot2.getRegistreF());
                            System.out.println("MR2 : "+ jeu.robot2.getRegistreM());
                            System.out.println("TR2 : "+ jeu.robot2.getRegistreT());
                            System.out.println("XR2 : "+ jeu.robot2.getRegistreX());
                            System.out.println();
                            */

                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        jeu.robot2.moveGraphique(jeu.zoneMonde);
                    }
                    jeu.zoneMemoire.update();
                    jeu.zoneAssembleur.update();
                } else {
                    // On arrête le timer lorsque toutes les instructions autont été exécutées
                    ((Timer) e.getSource()).stop();
                    jeu.getZoneAssembleur().enableModification();
                }
            }
        });
        timer.start();
    }

    private void actionBoutonPas(Jeu jeu) throws IOException
    {
        //System.out.println("SSS");
        jeu.getZoneAssembleur().disableModification();
        jeu.robot1.setLesInstructions(jeu.zoneAssembleur.parse(jeu.zoneAssembleur.getAssembleurRobot1()));
        jeu.robot2.setLesInstructions(jeu.zoneAssembleur.parse(jeu.zoneAssembleur.getAssembleurRobot2()));

        if(random.nextInt(2) == 0)
        {
            jeu.robot1.executeInstruction();
            // on fait -1 car le tableau va de 0 à 4 <=> 1-1 à 5-1
                
            jeu.robot1.moveGraphique(jeu.zoneMonde);
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
                if(ZoneCommandes.this.delai > 250 && (ZoneCommandes.this.vitesse <175))
                {
                    ZoneCommandes.this.delai -= 250;
                    ZoneCommandes.this.vitesse += 25;
                    ZoneCommandes.this.affichageVitesse.setText("Vitesse : "+ZoneCommandes.this.vitesse+"%");
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
                ZoneCommandes.this.isRunning = true;
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
                if(ZoneCommandes.this.delai < 2000 && (ZoneCommandes.this.vitesse > 25))
                {
                    ZoneCommandes.this.delai += 250;
                    ZoneCommandes.this.vitesse -= 25;
                    ZoneCommandes.this.affichageVitesse.setText("Vitesse : "+ZoneCommandes.this.vitesse+"%");
                }
            }
        });
        this.add(this.boutonRalentir);
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
                ZoneCommandes.this.isRunning = false;
            }
        });
        this.add(this.boutonStop);
    }

}
