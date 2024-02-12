import javax.swing.JPanel;

public class Jeu extends JPanel
{
    private Octopunks octopunks;


    public Jeu(Octopunks octopunks)
    {
        this.octopunks = octopunks;

        this.setBounds(0,0,(int)octopunks.getDimension().getWidth(),(int)octopunks.getDimension().getHeight());

        JPanel zoneCommandes = new zoneCommandes();
        JPanel zoneAsm = new ZoneAssembleur();
        JPanel zoneMemoire = new ZoneMemoire(octopunks);

        reglageCommandes(zoneCommandes);
        reglageZoneAsm(zoneAsm);

        this.add(zoneCommandes);
        this.add(zoneAsm);     
        this.add(zoneMemoire);

        this.setSize(octopunks.getDimension());
        this.setLayout(null);
    }

    private void reglageZoneAsm(JPanel zoneAsm)
    {
        zoneAsm.setLocation((int)octopunks.getDimension().getWidth()-zoneAsm.getWidth(),0);
        zoneAsm.setSize(zoneAsm.getWidth(), zoneAsm.getHeight());
    }

    private void reglageCommandes(JPanel zoneCommandes)
    {
        zoneCommandes.setLocation((int)octopunks.getDimension().getWidth()-zoneCommandes.getWidth(), (int)octopunks.getDimension().getHeight()-zoneCommandes.getHeight());

        // DÉBOGAGE
        System.out.println("Largeur fenetre : " + (int)octopunks.getDimension().getWidth());
        System.out.println("Hauteur fenetre : " + (int)octopunks.getDimension().getHeight());
        System.out.println("Position zone boutons : " + ((int)octopunks.getDimension().getHeight() - (int)zoneCommandes.getHeight()));
    }
}
