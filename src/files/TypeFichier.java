package src.files;


public abstract class TypeFichier<E> {
    public abstract void add(E elt); //Ajoute un élément
    public abstract void remove(); //Supprime un élément
    public abstract void clear(); //Vide la structure
    public abstract ListIterator<E> getIterator(int index); //Renvoie un itérateur sur la structure
}
