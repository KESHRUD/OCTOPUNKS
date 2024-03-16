package src.files;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class Pile<E> extends TypeFichier<E> {
    private LinkedList<E> p; /** La pile */
    
    /** Initialise une pile vide et un pointeur sur la pile  */
    public Pile() {
        p = new LinkedList<E>();
    }
    
    /** Renvoie la pile */
    public LinkedList<E> getPile() {
        return p;
    }
 
    /** Ajoute un element dans la pile */
    public void push(E elt) {
        if(elt == null)
            throw new NullPointerException(); 
        p.add(elt);
        
       
    }

    /** Supprime l'element tout en haut de la pile */
    public void pop() {
        try {
            p.removeLast();
        } catch (NoSuchElementException e) {
            System.out.println("Il n'y plus d'élément dans la pile");
        } 
    }
    
    /** Renvoie l'element tout en haut de la pile ou null */
	public E peek() {
        try {
            return p.peekLast();
        } catch (NoSuchElementException e) {
            System.out.println("Il n'y plus d'élément dans la pile");
            return null;
        }
    }
	
    /** Renvoie la taille de la pile */
    public int height() {
        return p.size();
    }

    /** Efface les element de la pile */
    public void clear() {
        p.clear();
        p = null;
    }

    /** Renvoie un iterateur sur la pile avec l'index du début de la pile */
    public ListIterator<E> getIterator(int index) {
        return p.listIterator(index);
    }
}
