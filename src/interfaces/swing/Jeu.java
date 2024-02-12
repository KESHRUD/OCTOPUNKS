import javax.swing.*;
import java.awt.*;


public class Jeu extends JPanel
{
    private Dimension dimension;

    public Jeu()
    {
        this.dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(0,0,(int)dimension.getWidth(),(int)dimension.getHeight());
        //this.setTitle("Octopunks");

        JPanel zoneBoutons = new Commandes();
        JPanel zoneAsm = new ZoneAssembleur();

        reglageZoneBoutons(zoneBoutons);
        reglageZoneAsm(zoneAsm);

        this.add(zoneBoutons);
        this.add(zoneAsm);        

        //System.out.println("Hauteur écran : " + dim.getHeight());
        //System.out.println("Largeur écran : " + dim.getWidth());
        
        
        this.setSize(this.dimension);
        this.setLayout(null);
        //this.setLocationRelativeTo(null);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setResizable(true);
        this.setVisible(true);
    }

    private void reglageZoneAsm(JPanel zoneAsm)
    {
        zoneAsm.setLocation((int)this.dimension.getWidth()-zoneAsm.getWidth(),0);
        zoneAsm.setSize(zoneAsm.getWidth(), zoneAsm.getHeight());
    }

    private void reglageZoneBoutons(JPanel zoneBoutons)
    {
        zoneBoutons.setLocation((int)this.dimension.getWidth()-zoneBoutons.getWidth(), (int)this.dimension.getHeight()-zoneBoutons.getHeight());

        System.out.println("Largeur fenetre : " + (int)this.dimension.getWidth());
        System.out.println("Hauteur fenetre : " + (int)this.dimension.getHeight());

        System.out.println("Position zone boutons : " + ((int)this.dimension.getHeight() - (int)zoneBoutons.getHeight()));
    }
}
