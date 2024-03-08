package src.model;

import java.util.Objects;

public class Link
{
    private final int id;
    private final Salle salleAvant;
    private final Salle salleApres;
    private final boolean bidirectionnel;

    /**
     * CONSTRUCTEUR
     * @param id l'identifiant de la salle
     * @param salle1 la salle 1
     * @param salle2 la salle 2
     * @param bidirectionnel le lien est bidectionnel ou non
     */
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

    /**
     * @return l'identifiant de la salle
     */
    public int getId() {
        return this.id;
    }

    /**
     * @return la salle après le lien
     */
    public Salle getSalleApres() {
        return this.salleApres;
    }

    /**
     * @return la salle avant le lien
     */
    public Salle getSalleAvant() {
        return this.salleAvant;
    }

    /**
     * @return true si le lien est bidirectionnel, false sinon
     */
    public boolean isBidirectionnel() {
        return this.bidirectionnel;
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
