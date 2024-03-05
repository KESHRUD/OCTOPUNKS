package src.model;

import java.util.Objects;

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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link)) return false;
        Link link = (Link) o;
        return id == link.id && salleAvant.equals(link.salleAvant) && salleApres.equals(link.salleApres) && bidirectionnel == link.bidirectionnel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, salleAvant, salleApres, bidirectionnel);
    }
    
}
