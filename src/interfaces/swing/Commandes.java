import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Commandes extends JPanel
{
    public Commandes()
    {
        this.setBounds(800,500,200,250);
        this.setBackground(Color.GREEN);
        this.setLayout(null);

        JLabel commandesLabel = new JLabel("Commandes");
        commandesLabel.setBounds(60, 15, 100, 20);
        this.add(commandesLabel);

        JButton boutonPas = new JButton("Pas");
        // Le modèle de bouton standard est un carré, puis on ajuste en fonction de nos envies.
        int boutonWidth = 60;
        int boutonHeight = boutonWidth;
        boutonPas.setBounds(40,80,boutonWidth,boutonHeight);
        boutonPas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("On fait un pas.");
            }
        });
        this.add(boutonPas);

        // Création du bouton STOP
        JButton boutonStop = new JButton("STOP");
        boutonStop.setBounds(100,80,boutonWidth,boutonHeight);
        
        boutonStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("STOOOP");
            }
        });
        this.add(boutonStop);


        // Création du bouton pour l'exécution automatique
        JButton boutonAutomatique = new JButton("Automatique");
        boutonAutomatique.setBounds(40,150,boutonWidth*2,boutonHeight);
        
        boutonAutomatique.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Mode automatique.");
            }
        });
        this.add(boutonAutomatique);
    }
}