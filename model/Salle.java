import java.util.HashMap;
import java.util.Map;

public class Salle {
    private int[][] contenu; // Représentation de ce qui se trouve dans la salle
    private Map<Integer, String> portes; // Représentation des portes numérotées

    // Constructeur
    public Salle() {
        this.contenu = new int[5][5];
        this.portes = new HashMap<>();
    }

    // Méthode pour ajouter une porte
    public void ajouterPorte(int numeroPorte, String destination) {
        portes.put(numeroPorte, destination);
    }

    // Méthode pour vérifier si un champ est occupé
    public boolean estOccupe(int x, int y) {
        return contenu[x][y] == 1;
    }

    // Méthode pour occuper un champ
    public void occuperChamp(int x, int y) {
        contenu[x][y] = 1;
    }

    // Méthode pour libérer un champ
    public void libererChamp(int x, int y) {
        contenu[x][y] = 0;
    }

    // Getters et setters
    public int[][] getContenu() {
        return contenu;
    }

    public void setContenu(int[][] contenu) {
        this.contenu = contenu;
    }

    public Map<Integer, String> getPortes() {
        return portes;
    }

    public void setPortes(Map<Integer, String> portes) {
        this.portes = portes;
    }
}
