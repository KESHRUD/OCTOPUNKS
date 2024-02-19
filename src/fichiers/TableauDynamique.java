import java.util.ArrayList;
import java.util.List;

public class TableauDynamique extends Fichier {
    private List<Integer> tableau;

    public TableauDynamique() {
        tableau = new ArrayList<>();
    }

    @Override
    public void lire() {
        if (!tableau.isEmpty()) {
            int lastIndex = tableau.size() - 1;
            int valeur = tableau.remove(lastIndex);
            System.out.println("Lecture de la valeur : " + valeur);
        } else {
            System.out.println("Le tableau dynamique est vide !");
        }
    }

    @Override
    public void ecrire(int valeur) {
        tableau.add(valeur);
        System.out.println("Ecriture de la valeur : " + valeur);
    }

    @Override
    public boolean estVide() {
        return tableau.isEmpty();
    }

    @Override
    public void vider() {
        tableau.clear();
        System.out.println("Le tableau dynamique a été vidé.");
    }

    @Override
    public void voidF() {
        if (!tableau.isEmpty()) {
            tableau.remove(tableau.size() - 1);
            System.out.println("La dernière valeur du tableau dynamique a été retirée.");
        } else {
            System.out.println("Le tableau dynamique est vide !");
        }
    }

    @Override
    public boolean testEOF() {
        return estVide();
    }

    public static TableauDynamique make() {
        return new TableauDynamique();
    }
}
