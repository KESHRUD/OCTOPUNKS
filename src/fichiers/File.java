package fichiers;

import java.util.LinkedList;

public class File extends Fichier {

    private LinkedList<Integer> file;

    public File() {
        file = new LinkedList<>();
    }

    @Override
    public void lire() {
        if (!file.isEmpty()) {
            int valeur = file.removeFirst();
            System.out.println("Valeur lue depuis la file : " + valeur);
        } else {
            System.out.println("La file est vide !");
        }
    }

    @Override
    public void ecrire(int valeur) {
        file.addLast(valeur);
        System.out.println("Valeur " + valeur + " ajoutée à la file.");
    }

    @Override
    public boolean estVide() {
        return file.isEmpty();
    }

    @Override
    public void vider() {
        file.clear();
        System.out.println("La file a été vidée.");
    }
}
