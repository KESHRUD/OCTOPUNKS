import java.awt.event.KeyListener;

import javax.swing.*;
import java.awt.event.*;

public class Jeu extends JPanel implements KeyListener
{
    private Octopunks octopunks;

    protected ZoneAssembleur zoneAssembleur;
    protected ZoneCommandes zoneCommandes;
    protected ZoneMemoire zoneMemoire;
    private JScrollPane scrollBar;


    public Jeu(Octopunks octopunks)
    {
        if(octopunks == null)
        {
            throw new NullPointerException("Octopunks est null dans la classe Jeu.");
        }
        this.octopunks = octopunks;
        this.zoneAssembleur = new ZoneAssembleur(this.octopunks);
        this.zoneCommandes = new ZoneCommandes(this.octopunks, this.zoneAssembleur);
        this.zoneMemoire = new ZoneMemoire(this.octopunks,this);

        this.setBounds(0,0,(int)octopunks.getDimension().getWidth(),(int)this.octopunks.getDimension().getHeight());

        reglageCommandes();
        reglageZoneAsm();

        this.add(this.zoneCommandes);
        this.add(this.zoneAssembleur);     
        this.add(this.zoneMemoire);

        this.addKeyListener(this);
        this.setSize(this.octopunks.getDimension());
        this.setLayout(null);

        //this.scrollBar = new JScrollPane(this);
        //this.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public JScrollPane getScrollBar()
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

    private void reglageZoneAsm()
    {
        this.zoneAssembleur.setSize(this.zoneAssembleur.getWidth(), this.zoneAssembleur.getHeight());
        this.zoneAssembleur.setLocation((int)this.octopunks.getDimension().getWidth()-this.zoneAssembleur.getWidth(),0);
    }

    private void reglageCommandes()
    {
        this.zoneCommandes.setLocation((int)this.octopunks.getDimension().getWidth()-this.zoneCommandes.getWidth(), (int)octopunks.getDimension().getHeight()-zoneCommandes.getHeight());
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
}
