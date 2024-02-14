import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;



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

    public ZoneCommandes()
    {
        this.setBounds(800,500,200,250);
        this.setBackground(Color.GREEN);
        this.setLayout(null);

        this.commandesLabel = new JLabel("Commandes");
        this.commandesLabel.setBounds(60, 15, 100, 20);
        this.add(this.commandesLabel);

        this.boutonPas = new JButton("Pas");
        
        this.boutonPas.setBounds(40,80,boutonWidth,boutonHeight);
        this.boutonPas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("On fait un pas.");

            }
        });
        this.add(this.boutonPas);

        // Initialisation du bouton STOP
        this.boutonStop = new JButton("STOP");
        this.boutonStop.setBounds(100,80,boutonWidth,boutonHeight);
        
        this.boutonStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("STOOOP");
            }
        });
        this.add(boutonStop);


        // Initialisation du bouton pour l'exécution automatique
        this.boutonAutomatique = new JButton("Automatique");
        this.boutonAutomatique.setBounds(40,150,boutonWidth*2,boutonHeight);
        
        this.boutonAutomatique.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Mode automatique.");
            }
        });
        this.add(this.boutonAutomatique);

    }
}
