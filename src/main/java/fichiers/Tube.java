package fichiers;

import java.util.concurrent.ConcurrentLinkedDeque;

public class Tube extends Fichier {
    private ConcurrentLinkedDeque<Integer> file;

    public Tube() {
        file = new ConcurrentLinkedDeque<>();
    }

    @Override
    public void lire() {
        if (!file.isEmpty()) {
            System.out.println("Valeur lue depuis le tube : " + file.pollFirst());
        } else {
            System.out.println("Erreur : le tube est vide !");
        }
    }

    @Override
    public void ecrire(int valeur) {
        file.offerLast(valeur);
    }

    @Override
    public void vider() {
        file.clear();
    }

    @Override
    public boolean estVide() {
        return file.isEmpty();
    }
}
