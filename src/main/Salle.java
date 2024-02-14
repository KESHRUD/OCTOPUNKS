import java.util.HashMap;
import java.util.Map;

public class Salle {
    private Map<Coordonnees, Champ> contenu; // Représentation de ce qui se trouve dans la salle
    private Map<Integer, String> portes; // Représentation des portes numérotées

    // Constructeur
    public Salle() {
        this.contenu = new HashMap<>();
        initialiserContenu();
        this.portes = new HashMap<>();
    }

    private void initialiserContenu() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                contenu.put(new Coordonnees(i, j), new Champ(TypeCellule.VIDE));
            }
        }
    }

    // Méthode pour ajouter une porte
    public void ajouterPorte(int numeroPorte, String destination) {
        portes.put(numeroPorte, destination);
    }

    // Méthode pour vérifier si un champ est occupé
    public boolean estOccupe(Coordonnees coordonnees) {
        return contenu.get(coordonnees).getType() != TypeCellule.VIDE;
    }

    // Méthode pour occuper un champ avec un type de cellule donné
    public void occuperChamp(Coordonnees coordonnees, TypeCellule typeCellule) {
        contenu.get(coordonnees).setType(typeCellule);
    }

    // Méthode pour libérer un champ
    public void libererChamp(Coordonnees coordonnees) {
        contenu.get(coordonnees).setType(TypeCellule.VIDE);
    }

    // Getters et setters
    public Map<Coordonnees, Champ> getContenu() {
        return contenu;
    }

    public void setContenu(Map<Coordonnees, Champ> contenu) {
        this.contenu = contenu;
    }

    public Map<Integer, String> getPortes() {
        return portes;
    }

    public void setPortes(Map<Integer, String> portes) {
        this.portes = portes;
    }
}
