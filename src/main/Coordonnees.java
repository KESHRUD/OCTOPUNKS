public class Coordonnees {
    private final int x;
    private final int y;

    // Constructeur
    public Coordonnees(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
        return x == other.x && y == other.y;
    }

    // Méthode pour générer le hashcode
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
