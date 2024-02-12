package fichiers;

import java.util.LinkedList;

public class Pile extends Fichier {

    private LinkedList<Integer> pile;

    public Pile() {
        pile = new LinkedList<>();
    }

    @Override
    public void lire() {
        if (!pile.isEmpty()) {
            int valeur = pile.removeLast();
            System.out.println("Valeur lue depuis la pile : " + valeur);
        } else {
            System.out.println("La pile est vide !");
        }
    }

    @Override
    public void ecrire(int valeur) {
        pile.addLast(valeur);
        System.out.println("Valeur " + valeur + " ajoutée à la pile.");
    }

    @Override
    public boolean estVide() {
        return pile.isEmpty();
    }

    @Override
    public void vider() {
        pile.clear();
        System.out.println("La pile a été vidée.");
    }
}
