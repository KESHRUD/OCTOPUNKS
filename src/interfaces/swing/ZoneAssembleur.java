import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Color;


public class ZoneAssembleur extends JPanel
{
    private JLabel labelAssembleur;
    private JScrollPane scrollBar1;
    private JScrollPane scrollBar2;

    private JTextArea zoneTexte1;
    private JTextArea zoneTexte2;


    /**
     * On crée la zone qui concerne les entrées de code assembleur.
    */
    public ZoneAssembleur()
    {
        this.setBounds(770,100,400,600);
        this.setBackground(Color.RED);
        this.setLayout(null);

        labelAssembleur = new JLabel("Instructions assembleur");
        labelAssembleur.setBounds(30,30,160,10);
        this.add(labelAssembleur);

        zoneTexte1 = new JTextArea();
        zoneTexte1.setBounds(10,60,375,225);
        scrollBar1 = new JScrollPane(zoneTexte1);
        scrollBar1.setBounds(zoneTexte1.getX(), zoneTexte1.getY(), zoneTexte1.getWidth(), zoneTexte1.getHeight());
        this.add(scrollBar1);

        zoneTexte2 = new JTextArea();
        zoneTexte2.setBounds(zoneTexte1.getX(),zoneTexte1.getHeight()+zoneTexte1.getY()+20,375,225);
        scrollBar2 = new JScrollPane(zoneTexte2);
        scrollBar2.setBounds(zoneTexte2.getX(), zoneTexte2.getY(), zoneTexte2.getWidth(), zoneTexte2.getHeight());
        this.add(scrollBar2);
    }

    /**
     * Permet de bloquer la modification d'une zone de saisie de code assembleur
     * @param zoneTexte la zone de saisie du code assembleur
     */
    public void disableModification(JTextArea zoneTexte)
    {
        zoneTexte.setEditable(false);
    }

    /**
     * Permet d'autoriser la modification d'une zone de saisie de code assembleur
     * @param zoneTexte la zone de saisie du code assembleur
     */
    public void enableModification(JTextArea zoneTexte)
    {
        zoneTexte.setEditable(true);
    }


    /**
     * Renvoie le contenu de la zone de code assembleur passée en paramètre.
     * @param zoneTexte la zone de saisie du code assembleur
     * @return le code assembleur
     * Je modifierai petut-être pour lever une exception si la zone assembleur est vide
     * (dépendra des besoins des autres membres du groupe).
     */
    public String getAssembleur(JTextArea zoneTexte)
    {
        return zoneTexte.getText();
    }
}
