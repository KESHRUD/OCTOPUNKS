package src.view;

import src.model.Coordonnees;
import src.model.Link;
import src.model.Robot;
import src.model.Salle;
import src.model.leFichier;
import src.model.Main2;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Jeu extends JPanel implements KeyListener
{
    private Octopunks octopunks;

    protected ZoneAssembleur zoneAssembleur;
    protected ZoneCommandes zoneCommandes;
    protected ZoneMemoire zoneMemoire;
    protected ZoneMonde zoneMonde;
    private JScrollPane scrollBar;
    
    protected Robot robot1;
    protected Robot robot2;

    protected Salle salle1;
    protected Salle salle2;
    protected Salle salle3;

    protected leFichier file;


    public Jeu(Octopunks octopunks) throws IOException
    {
        if(octopunks == null)
        {
            throw new NullPointerException("Octopunks est null dans la classe Jeu.");
        }
        this.octopunks = octopunks;


        salle1 = new Salle(1, null);
        salle2 = new Salle(2, null);
        salle3 = new Salle(3, null);

        this.file = new leFichier(200, new Coordonnees(1, 1, salle2));
        this.file.defineJeu(this);
        this.file.occuperChamp(file.getPosition());
        

        this.robot1 = new Robot(new Coordonnees(1, 1, salle1));
        this.robot1.defineJeu(this);
        this.robot1.setImage("images/Exapunks_robot.png");

        this.robot2 = new Robot(new Coordonnees(1, 2, salle1));
        this.robot2.setImage("images/Robot.png");
        this.robot2.defineJeu(this);

        this.salle2.getSalleFiles().add(this.file);

        salle1.ajouterLienSortant(new Link(400, salle1, salle2, true));

        salle2.setLienEntrant(salle1.getLiensSortant().get(0));

        salle2.ajouterLienSortant(new Link(800, salle2, salle3, true));
        salle3.setLienEntrant(salle2.getLiensSortant().get(0));

        

        // System.out.println("robot1  x = " + robot1.getPositionX() + " y = " + robot1.getPositionY() + " salle : " + robot1.getCurrentSalle().getId());
        // System.out.println("200  x = " + file.getPosition().getX() + " y = " + file.getPosition().getY() + " salle : " + file.getPosition().getSalle().getId());
        
        //robot1.link("400");
        //System.out.println(" link 400 : robot1  x = " + robot1.getPositionX() + " y = " + robot1.getPositionY() + " salle : " + robot1.getCurrentSalle().getId());
        

        /*robot1.link("800");
        System.out.println(" link 800 : robot1  x = " + robot1.getPositionX() + " y = " + robot1.getPositionY() + " salle : " + robot1.getCurrentSalle().getId());
        
        robot1.link("-1");
        System.out.println(" link -1 : robot1  x = " + robot1.getPositionX() + " y = " + robot1.getPositionY() + " salle : " + robot1.getCurrentSalle().getId());
        */
        /*
        robot1.getCurrentSalle().setTheFile(file);
        robot1.grab("200");
        System.out.println("grab 200  x = "+ file.getPosition().getX() + " y = " + file.getPosition().getY() + " salle : " + file.getPosition().getSalle().getId());
        
        robot1.link("800");
        System.out.println(" link 800 : robot1  x = " + robot1.getPositionX() + " y = " + robot1.getPositionY() + " salle : " + robot1.getCurrentSalle().getId());
        System.out.println("grab 200  x = "+ file.getPosition().getX() + " y = " + file.getPosition().getY() + " salle : " + file.getPosition().getSalle().getId());
        
        robot1.drop();
        System.out.println(" robot1  x = " + robot1.getPositionX() + " y = " + robot1.getPositionY() + " salle : " + robot1.getCurrentSalle().getId());
        System.out.println(" 200  x = "+ file.getPosition().getX() + " y = " + file.getPosition().getY() + " salle : " + file.getPosition().getSalle().getId());
       */


        this.zoneAssembleur = new ZoneAssembleur(octopunks, this);
        this.zoneMonde = new ZoneMonde(octopunks, this);
        this.zoneCommandes = new ZoneCommandes(octopunks, this);
        this.zoneMemoire = new ZoneMemoire(octopunks,this);

        //this.file.afficher(this);

        System.out.println("Fichier : x = "+file.getPosition().getX()+" et y = "+file.getPosition().getY()+" - salle : "+file.getPosition().getSalle().getId());

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


    private JScrollPane getScrollBar()
    {
        if(this.scrollBar == null)
        {
            throw new NullPointerException("La scrollbar est null.");
        }
        return this.scrollBar;
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

    public ZoneMonde getZoneMonde()
    {
        return this.zoneMonde;
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
