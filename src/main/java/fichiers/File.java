package fichiers;

import java.util.LinkedList;
import java.util.Queue;

public class File extends Fichier {
    private Queue<Integer> file;

    public File() {
        file = new LinkedList<>();
    }

    @Override
    public void lire() {
        if (!file.isEmpty()) {
            int valeur = file.poll();
            System.out.println("Lecture de la valeur : " + valeur);
        } else {
            System.out.println("La file est vide !");
        }
    }

    @Override
    public void ecrire(int valeur) {
        file.offer(valeur);
        System.out.println("Ecriture de la valeur : " + valeur);
    }

    @Override
    public boolean estVide() {
        return file.isEmpty();
    }
}
