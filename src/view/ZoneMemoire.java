import javax.swing.*;

import java.awt.*;

public class ZoneMemoire extends JPanel
{
    private int x;
    private int y;
    
    public ZoneMemoire(Octopunks octopunks, Jeu jeu)
    {
        this.setSize((int)(octopunks.getDimension().getWidth()-jeu.getZoneCommandes().getWidth()),jeu.getZoneCommandes().getHeight());
        this.setLocation(0,(int)octopunks.getDimension().getHeight()-getHeight());
        this.setBackground(Color.PINK);
        this.setLayout(null);
    }
}
