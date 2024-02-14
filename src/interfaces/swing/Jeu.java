import javax.swing.JPanel;

public class Jeu extends JPanel
{
    private Octopunks octopunks;

    private ZoneAssembleur zoneAssembleur;
    private ZoneCommandes zoneCommandes;
    private ZoneMemoire zoneMemoire;


    public Jeu(Octopunks octopunks)
    {
        if(octopunks == null)
        {
            throw new NullPointerException("Octopunks est null.");
        }
        this.octopunks = octopunks;
        this.zoneAssembleur = new ZoneAssembleur();
        this.zoneCommandes = new ZoneCommandes(this.zoneAssembleur);
        this.zoneMemoire = new ZoneMemoire(octopunks,this);

        this.setBounds(0,0,(int)octopunks.getDimension().getWidth(),(int)octopunks.getDimension().getHeight());

        reglageCommandes(zoneCommandes);
        reglageZoneAsm(this.zoneAssembleur);

        this.add(this.zoneCommandes);
        this.add(this.zoneAssembleur);     
        this.add(this.zoneMemoire);

        this.setSize(octopunks.getDimension());
        this.setLayout(null);
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

    private void reglageZoneAsm(ZoneAssembleur zoneAssembleur)
    {
        zoneAssembleur.setLocation((int)octopunks.getDimension().getWidth()-zoneAssembleur.getWidth(),0);
        zoneAssembleur.setSize(zoneAssembleur.getWidth(), zoneAssembleur.getHeight());
    }

    private void reglageCommandes(ZoneCommandes zoneCommandes)
    {
        zoneCommandes.setLocation((int)octopunks.getDimension().getWidth()-zoneCommandes.getWidth(), (int)octopunks.getDimension().getHeight()-zoneCommandes.getHeight());

        // DÉBOGAGE
        System.out.println("Largeur fenetre : " + (int)octopunks.getDimension().getWidth());
        System.out.println("Hauteur fenetre : " + (int)octopunks.getDimension().getHeight());
        System.out.println("Position zone boutons : " + ((int)octopunks.getDimension().getHeight() - (int)zoneCommandes.getHeight()));
    }
}
