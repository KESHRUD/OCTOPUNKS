public class Link {
    private final int id;
    private final Salle salleAvant;
    private final Salle salleApres;
    private final boolean bidirectionnel;

    public Link(int id, Salle salle1, Salle salle2, boolean bidirectionnel) {
        this.id = id;
        this.salleAvant = salle1;
        this.salleApres = salle2;
        this.bidirectionnel = bidirectionnel;

        // Définir la réciprocité du lien entre les salles si bidirectionnel est vrai
        if (bidirectionnel) {
            salleAvant.ajouterLienSortant(this);
            salleApres.setLienEntrant(this);
        }
    }

    public int getId() {
        return id;
    }

    public Salle getSalleAvant() {
        return salleAvant;
    }

    public Salle getSalleApres() {
        return salleApres;
    }
    
    public boolean isBidirectionnel() {
        return bidirectionnel;
    }
}
