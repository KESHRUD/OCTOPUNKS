package src.files;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TableauDynamique<E> extends TypeFichier<E> {
    private ArrayList<E> liste;
    private int index;

    /** Initialise une tableau dynamique vide */
    public TableauDynamique() {
        liste = new ArrayList<E>();
        index = 0;
    }

    /** Revoie l'index de l'élément courant */
    public int getIndex() {
        return index;
    }

    /** Renvoie l'arrayList */
    public ArrayList<E> getTableauDynamique() {
        return liste;
    }

    
    /** Ajoute une élément dans le tableau dynamique */
    public void add(E elt) {
        if(elt == null)
            throw new NullPointerException();
        liste.add(elt);
    
    }

    /** Suprime un élément du le tableau dynamique */
    public void remove() {  
        try {
            liste.remove(index);
        } catch (NoSuchElementException e) {
            System.out.println("Il n'y a plus d'element dans la file");
        }

        if(index >= this.size())
            index--;
    }

    /** Renvoie la taille du tableau dynamique */
    public int size() {
        return liste.size();
    }

    /** Met le tableau dynamique à null */
    public void clear() {
        liste.clear();
        liste = null;
    }

    /** Renvoie un iterateur sur le tableau dynamique */
    public Iterator<E> getIterator() {
        return liste.iterator();
    }

    /** Déplace l'index courant au suivant */
    public void nextIndex() {
        index++;
    }

    /** Déplace l'index courant au précedent */
    public void previousIndex() {
        index--;
    }

    /** Renvoie l'élément courant */
    public E getCurrentElement() {
        return liste.get(index);
    }

}
