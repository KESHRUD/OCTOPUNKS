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

    /**
     * Le modèle de bouton standard est un carré de 60px * 60px,
     * puis on ajuste en fonction de nos envies.
     */
    private int boutonWidth = 60;
    private int boutonHeight = boutonWidth;

    public ZoneCommandes(Octopunks octopunks, ZoneAssembleur zoneAssembleur)
    {
        this.setSize(zoneAssembleur.getWidth(), (int)octopunks.getDimension().getHeight()/3);
        this.setLocation(800,500);
        this.setBackground(Color.GREEN);
        this.setLayout(null);

        this.commandesLabel = new JLabel("Commandes");
        this.commandesLabel.setSize(100,20);
        this.commandesLabel.setLocation(60, 15);
        this.add(this.commandesLabel);

        this.boutonPas = new JButton("Pas");
        
        this.boutonPas.setBounds(40,80,boutonWidth,boutonHeight);
        this.boutonPas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                zoneAssembleur.disableModification();
            }
        });
        this.add(this.boutonPas);

        // Initialisation du bouton STOP
        this.boutonStop = new JButton("STOP");
        this.boutonStop.setBounds(100,80,boutonWidth,boutonHeight);
        
        this.boutonStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                zoneAssembleur.enableModification();
            }
        });
        this.add(this.boutonStop);


        // Initialisation du bouton pour l'exécution automatique
        this.boutonAutomatique = new JButton("Automatique");
        this.boutonAutomatique.setBounds(40,150,boutonWidth*2,boutonHeight);
        
        this.boutonAutomatique.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                zoneAssembleur.disableModification();
            }
        });
        this.add(this.boutonAutomatique);
    }
}
