package src.model;


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
        if (bidirectionnel && salleAvant != null) {
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

    /* Méthode pour ajouter un lien à une salle.
     * Cette méthode est appelée lors de la création du lien et sert à établir la réciprocité.
     * @param lien Le lien à ajouter à la salle
     */
    private void ajouterLien(Link lien) {
        if (this.equals(lien.getSalleAvant())) {
            this.salleAvant.ajouterLienSortant(lien);
            if (lien.isBidirectionnel()) {
                this.salleApres.setLienEntrant(lien);
            }
        } else if (this.equals(lien.getSalleApres())) {
            this.salleApres.ajouterLienSortant(lien);
            if (lien.isBidirectionnel()) {
                this.salleAvant.setLienEntrant(lien);
            }
        }
    }
}
