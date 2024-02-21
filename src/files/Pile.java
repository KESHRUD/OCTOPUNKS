import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class Pile<E> extends TypeFichier<E> {
    private LinkedList<E> p;

    /** Crée une pile vide */
    public Pile() {
        p = new LinkedList<E>();
    }

    /** Crée une pile avec des valeurs aléatoires */
    public static Pile<Integer> makeLIFO(int length) {
        Pile<Integer> pile = new Pile<Integer>();
        Random rand = new Random();

        for (int i = 0; i < length; i++) {
            pile.push(rand.nextInt(100)); // Choisir la plage de nombres aléatoires selon vos besoins
        }

        return pile;
    }

    /** Renvoie la pile */
    public LinkedList<E> getPile() {
        return p;
    }

    /** Ajoute un élément au sommet de la pile */
    public void push(E elt) {
        if (elt == null)
            throw new NullPointerException();
        p.add(elt);
    }

    /** Supprime l'élément tout en haut de la pile */
    public void pop() {
        try {
            p.removeLast();
        } catch (NoSuchElementException e) {
            System.out.println("La pile est vide !");
        }
    }

    /** Renvoie le dernier élément de la pile ou null*/
    public E peek() {
        if (this.length() >= 1)
            return p.peekLast();
        return null;
    }

    /** Renvoie la taille de la pile */
    public int height() {
        return p.size();
    }

    /** Supprime les éléments de la pile */
    public void clear() {
        p.clear();
    }

    /** Renvoie un itérateur sur la pile avec l'index du début de la pile */
    public ListIterator<E> getIterator(int index) {
        return p.listIterator(index);
    }

    /** Lit la dernière case de la pile en l'effaçant (opération "pop") */
    @Override
    public void lire() {
        pop();
    }

    /** Supprime la dernière case de la pile (opération "pop") */
    @Override
    public void voidF() {
        pop();
    }

    /** Teste si la pile est vide */
    @Override
    public boolean testEOF() {
        return estVide();
    }

}
