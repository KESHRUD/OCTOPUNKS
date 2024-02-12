import javax.swing.*;
import java.awt.*;


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