package fichiers;

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
}
