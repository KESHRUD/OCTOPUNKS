import javax.swing.*;

import java.awt.*;


public class ZoneAssembleur extends JPanel
{
    private JLabel labelAssembleur;
    private JScrollPane scrollBar1;
    private JScrollPane scrollBar2;

    private JTextArea zoneTexte1;
    private JTextArea zoneTexte2;
    private Font police;

    /**
     * On crée la zone qui concerne les entrées de code assembleur.
     */
    public ZoneAssembleur(Octopunks octopunks)
    {
        this.setLayout(null);

        this.setSize((int)octopunks.getDimension().getWidth()/4,(int)octopunks.getDimension().getHeight()*2/3);
        this.setLocation(100,100);
        this.setBackground(Color.RED);

        labelAssembleur = new JLabel("Instructions assembleur");
        labelAssembleur.setBounds(30,30,160,10);
        this.add(labelAssembleur);

        this.police = new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 25);

        zoneTexte1 = new JTextArea();
        zoneTexte1.setSize(this.getWidth()-25,(int)(this.getHeight()*0.4));
        zoneTexte1.setLocation(10,60);
        zoneTexte1.setFont(this.police);
        scrollBar1 = new JScrollPane(zoneTexte1);
        scrollBar1.setBounds(zoneTexte1.getX(), zoneTexte1.getY(), zoneTexte1.getWidth(), zoneTexte1.getHeight());
        this.add(scrollBar1);

        zoneTexte2 = new JTextArea();
        zoneTexte2.setSize(zoneTexte1.getSize());
        zoneTexte2.setLocation(zoneTexte1.getX(),zoneTexte1.getHeight()+zoneTexte1.getY()+20);
        zoneTexte2.setFont(this.police);
        scrollBar2 = new JScrollPane(zoneTexte2);
        scrollBar2.setBounds(zoneTexte2.getX(), zoneTexte2.getY(), zoneTexte2.getWidth(), zoneTexte2.getHeight());
        this.add(scrollBar2);
    }

    /**
     * Permet de bloquer la modification d'une zone de saisie de code assembleur
     * @param zoneTexte la zone de saisie du code assembleur
     */
    public void disableModification()
    {
        this.zoneTexte1.setEditable(false);
        this.zoneTexte2.setEditable(false);
    }

    /**
     * Permet d'autoriser la modification d'une zone de saisie de code assembleur
     * @param zoneTexte la zone de saisie du code assembleur
     */
    public void enableModification()
    {
        this.zoneTexte1.setEditable(true);
        this.zoneTexte2.setEditable(true);
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


    /**
     * On veut pouvoir enregistrer le code assembleur des deux zone de saisie.
     * Il faut qu'on puisse les charger au lancement du programme.
     */
    public void saveAssembleur()
    {
        // Compléter
    }
}
