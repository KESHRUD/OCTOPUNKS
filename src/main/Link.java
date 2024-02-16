public class Link {
    private final int id;
    private final Salle salleAvant;
    private final Salle salleApres;

    public Link(int id, Salle salle1, Salle salle2) {
        this.id = id;
        this.salleAvant = salle1;
        this.salleApres = salle2;

        // Définir la réciprocité du lien entre les salles
        salleAvant.ajouterLienSortant(this);
        salleApres.setLienEntrant(this);
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
}
/* 
    
     * Méthode pour ajouter un lien à une salle.
     * Cette méthode est appelée lors de la création du lien et sert à établir la réciprocité.
     * @param lien Le lien à ajouter à la salle
     *
    private void ajouterLien(Link lien) {
        if (this.equals(lien.getSalleAvant())) {
            this.salleAvant.ajouterLienSortant(lien);
        } else if (this.equals(lien.getSalleApres())) {
            this.salleApres.ajouterLienSortant(lien);
        }
    }
}
*/
