package src.files;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TableauDynamique<E> extends TypeFichier<E> {
    private ArrayList<E> liste;
    private int index;

    public TableauDynamique() {
        liste = new ArrayList<E>();
        index = 0;
    }

    public int getIndex() {
        return index;
    }

    /** Renvoie l'arrayList */
    public ArrayList<E> getTableauDynamique() {
        return liste;
    }

    
    /** Pas sur */
    public void add(E elt) {
        if(elt == null)
            throw new NullPointerException();
        liste.add(elt);
    
    }

    /*Pas sur */
    public void remove() {  
        try {
            liste.remove(index);
        } catch (NoSuchElementException e) {
            System.out.println("Il n'y a plus d'element dans la file");
        }

        if(index >= this.size())
            index--;
    }


    public int size() {
        return liste.size();
    }

    public void clear() {
        liste.clear();
        liste = null;
    }

    /** Renvoie un iterateur sur la pile avec l'index du début de la pile */
    public ListIterator<E> getIterator(int index) {
        return liste.listIterator(index);
    }

    public void nextIndex() {
        index++;
    }

    public void previousIndex() {
        index--;
    }

    public E getCurrentElement() {
        return liste.get(index);
    }

}
