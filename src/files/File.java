import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class File<E> extends TypeFichier<E> {
    private LinkedList<E> f;

    /** Creer un file vide */
    public File() {
        f = new LinkedList<E>();
        this.drop();
    }

    /** Creer un file avec des valeurs aléatoires */
    public static File<Integer> makeFIFO(int length) {
        File<Integer> file = new File<Integer>();
        Random rand = new Random();

        for (int i = 0; i < length; i++) {
            file.enqueue(rand.nextInt(100)); // Choisir la plage de nombres aléatoires selon vos besoins
        }

        return file;
    }

    /** Renvoie la file */
    public LinkedList<E> getFile() {
        return f;
    }
    
    /** Ajoute un element a la fin de la file */
    public void enqueue(E elt) {
        if(elt == null)
            throw new NullPointerException(); 
        f.add(elt);
    }

    /** Supprime le premier element de la file */
    public void dequeue() {
        try {
            f.removeFirst();
        } catch (NoSuchElementException e) {
            System.out.println("Il n'y a plus d'element dans la file");
        }
    }

    /** Renvoie le premier element de la file ou null*/
    public E head() {
        if(this.length() >= 1)
            return f.peekFirst();
        return null;
    }

    /** Renvoie le dernier element de la file ou null*/
    public E tail() {
        if(this.length() >= 1)
            return f.peekLast();
        return null;
    }

    /** Renvoie la longueur de la file */
    public int length() {
        return f.size();
    }

    /** Supprime les element de la file */
    public void clear() {
        f.clear();
        f = null;
    }

    /** Renvoie un iterateur sur la file avec l'index du début de la file */
    public ListIterator<E> getIterator(int index) {
        return f.listIterator(index);
    }
}
