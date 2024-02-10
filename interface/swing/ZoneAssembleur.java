import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;


public class ZoneAssembleur extends JPanel
{
    /**
     * Zone de texte pour mettre les instructions assembleur.
     * @param panel le panel qui contiendra la zone de texte.
     */
    public ZoneAssembleur()
    {
        this.setBounds(770,100,400,600);
        this.setBackground(Color.RED);
        this.setLayout(null);

        JLabel labelAssembleur = new JLabel("Instructions assembleur");
        labelAssembleur.setBounds(30,30,160,10);
        this.add(labelAssembleur);

        JTextArea zoneTexte = new JTextArea();
        zoneTexte.setBounds(10,60,375,450);

        JScrollPane scrollBar = new JScrollPane(zoneTexte);
        scrollBar.setBounds(10, 60, 375, 450);

        this.add(scrollBar);
    }
}