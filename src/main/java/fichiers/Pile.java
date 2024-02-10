package fichiers;

import java.util.Stack;

public class Pile extends Fichier {
    private Stack<Integer> pile;

    public Pile() {
        pile = new Stack<>();
    }

    @Override
    public void lire() {
        if (!pile.isEmpty()) {
            int valeur = pile.pop();
            System.out.println("Lecture de la valeur : " + valeur);
        } else {
            System.out.println("La pile est vide !");
        }
    }

    @Override
    public void ecrire(int valeur) {
        pile.push(valeur);
        System.out.println("Ecriture de la valeur : " + valeur);
    }

    @Override
    public boolean estVide() {
        return pile.isEmpty();
    }
}
