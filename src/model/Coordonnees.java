package src.model;

import java.util.Objects;

public class Coordonnees
{
    private int x;
    private int y;

    private int xGraphique;
    private int yGraphique;

    private Salle laSalle;

    /**
     * CONSTRUCTEUR
     * @param x la position x
     * @param y la position y
     * @param xGraphique la position x graphique
     * @param yGraphique la position y graphique
     * @param newSalle la salle
     */
    public Coordonnees(int x, int y, Salle newSalle) {
        this.x = x;
        this.y = y;
        this.xGraphique = 0;
        this.yGraphique = 0;
        this.laSalle = newSalle;
    }

    public Salle getSalle() {
        return this.laSalle;
    }
    
    /**
     * @return la position x dans la salle
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return la position x graphique
     */
    public int getXGraphique()
    {
        return this.xGraphique;
    }

    /**
     * @return la position y dans la salle
     */
    public int getY() {
        return this.y;
    }

    /**
     * @return la position y graphique
     */
    public int getYGraphique()
    {
        return this.yGraphique;
    }

    /**
     * @return true si la salle est occupée, false sinon.
     */
    public boolean isOccuped(){
        return laSalle.estOccupe(this);
    }

    /**
     * Permet de modifier les coordonnées.
     * @param x la nouvelle position x
     * @param y la nouvelle position y
     * @param laSalle la nouvelle salle
     */
    public void setCoordonnees(int x, int y, Salle laSalle)
    {
        this.x = x;
        this.y = y;
        this.laSalle = laSalle;
    }

    /**
     * Permet de modifier la salle actuelle
     * @param newSalle la nouvelle salle
     */
    public void setCurrentSalle(Salle newSalle) {
        this.laSalle = newSalle;
    }
    
    /**
     * Permet de modifier la position x.
     * @param x la nouvelle position x
     */
    public void setX(int x) {
        this.x = x;
    }
   
    /**
     * Permet de modifier la position x graphique
     * @param xGraphique la nouvelle position x graphique
     */
    public void setXGraphique(int xGraphique)
    {
        this.xGraphique = xGraphique;
    }

    /**
     * Permet de modifier la position y.
     * @param y la nouvelle position y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Permet de modifier la position y graphique
     * @param yGraphique la nouvelle position y graphique
     */
    public void setYGraphique(int yGraphique)
    {
        this.yGraphique = yGraphique;
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
}
