import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class TableauDynamique<E> extends TypeFichier<E> {
    private ArrayList<E> liste;
    private int index;

    public TableauDynamique() {
        liste = new ArrayList<E>();
        this.drop();
        index = 0;
    }

    public int getIndex() {
        return index;
    }

    /** Renvoie l'arrayList */
    public ArrayList<E> getTableauDynamique() {
        return liste;
    }

    public void add(E elt) {
        if (elt == null)
            throw new NullPointerException();
        liste.add(elt);
    }

    /** Renvoie la longueur du tableau dynamique */
    public int size() {
        return liste.size();
    }

    public void clear() {
        liste.clear();
    }

    /** Renvoie un iterateur sur le tableau dynamique avec l'index du début du tableau */
    public ListIterator<E> getIterator(int index) {
        return liste.listIterator(index);
    }

    /** Déplace le pointeur vers le prochain élément */
    public void nextIndex() {
        index++;
        if (index >= size()) {
            index = 0;
        }
    }

    /** Déplace le pointeur vers le précédent élément */
    public void previousIndex() {
        index--;
        if (index < 0) {
            index = size() - 1;
        }
    }

    /** Renvoie l'élément courant */
    public E getCurrentElement() {
        return liste.get(index);
    }

    /** Renvoie un tableau dynamique avec des valeurs aléatoires */
    public static TableauDynamique<Integer> make(int length) {
        TableauDynamique<Integer> tableau = new TableauDynamique<Integer>();
        Random rand = new Random();

        for (int i = 0; i < length; i++) {
            tableau.add(rand.nextInt(100)); // Choisir la plage de nombres aléatoires selon vos besoins
        }

        return tableau;
    }

    /** Supprime l'élément courant */
    public void voidF() {
        liste.remove(index);
        if (index >= size()) {
            index--;
        }
    }

}
