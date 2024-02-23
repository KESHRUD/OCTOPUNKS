package src.model;

import java.util.Objects;

public class Coordonnees {
    private  int x;
    private  int y;
    private Salle laSalle;

    // Constructeur
    public Coordonnees(int x, int y, Salle newSalle) {
        this.x = x;
        this.y = y;
        this.laSalle = newSalle;
    }
    
    // Getters
    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    public Salle getSalle(){
        return this.laSalle;
    }
    public void setCurrentSalle(Salle newSalle){
        this.laSalle = newSalle;
    }

    // Méthode pour vérifier l'égalité de coordonnées
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Coordonnees other = (Coordonnees) obj;
        return x == other.x && y == other.y && other.laSalle.equals(this.laSalle);
    }

    // Méthode pour générer le hashcode
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public boolean isOccuped(){
        return laSalle.estOccupe(this);
    }

}
