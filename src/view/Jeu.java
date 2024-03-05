package src.view;

import src.model.Coordonnees;
import src.model.Robot;
import src.model.Salle;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;

public class Jeu extends JPanel implements KeyListener
{
    private Octopunks octopunks;

    protected ZoneAssembleur zoneAssembleur;
    protected ZoneCommandes zoneCommandes;
    protected ZoneMemoire zoneMemoire;
    protected ZoneMonde zoneMonde;
    private JScrollPane scrollBar;
    protected Double vitesseExecution;

    protected Robot robot1;
    protected Robot robot2;


    public Jeu(Octopunks octopunks) throws IOException
    {
        if(octopunks == null)
        {
            throw new NullPointerException("Octopunks est null dans la classe Jeu.");
        }
        this.octopunks = octopunks;

        this.robot1 = new Robot(new Coordonnees(0, 0, new Salle(1)));
        this.robot2 = new Robot(new Coordonnees(0, 0, new Salle(1)));

        this.zoneAssembleur = new ZoneAssembleur(octopunks);
        this.zoneCommandes = new ZoneCommandes(octopunks, this);
        this.zoneMemoire = new ZoneMemoire(octopunks,this);
        this.zoneMonde = new ZoneMonde(octopunks, this);

        this.vitesseExecution = 1.0;
        this.setSize((int)octopunks.getDimension().getWidth(),(int)octopunks.getDimension().getHeight());
        this.setLocation(0,0);
        
        
        /**
         * On ajoute les différentes zones dans le panel.
         */
        this.add(this.zoneAssembleur);
        this.add(this.zoneCommandes);
        this.add(this.zoneMemoire);
        this.add(this.zoneMonde);

        this.addKeyListener(this);
        this.setSize(this.octopunks.getDimension());
        this.setLayout(null);
    }


    public JScrollPane getScrollBar()
    {
        if(this.scrollBar == null)
        {
            throw new NullPointerException("La scrollbar est null.");
        }
        return this.scrollBar;
    }

    public Double getVitesseExecution()
    {
        return this.vitesseExecution;
    }

    public ZoneAssembleur getZoneAssembleur()
    {
        if(this.zoneAssembleur == null)
        {
            throw new NullPointerException("La zone assembleur est null.");
        }
        return this.zoneAssembleur;
    }

    public ZoneCommandes getZoneCommandes()
    {
        if(this.zoneCommandes == null)
        {
            throw new NullPointerException("La zone de commandes est null.");
        }
        return this.zoneCommandes;
    }

    public ZoneMemoire getZoneMemoire()
    {
        if(this.zoneMemoire == null)
        {
            throw new NullPointerException("La zone mémoire est null.");
        }
        return this.zoneMemoire;
    }


    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("La touche " + e.getKeyCode() + " est appuyée !");

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("La touche " + e.getKeyCode() + " est pressée !");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("La touche " + e.getKeyCode() + " est relâchée !");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        Color couleurGauche = Color.RED;
        Color couleurDroite = Color.BLACK;
        GradientPaint gp = new GradientPaint(0, 0, couleurGauche, this.getWidth(), this.getHeight(), couleurDroite);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
