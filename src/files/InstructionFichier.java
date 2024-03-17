package src.files;

import java.util.Iterator;
import java.util.ListIterator;

public class InstructionFichier<E> {
    private E register = null;
    private TypeFichier<E> f;

    /** Initialise une insance InstructionFichier */
    public InstructionFichier() {
    }

    /** Registre permettent de manipuler l'element courant */
    public E getRegister() {
        return register;
    }

    /** Renvoie un fichiers de n'importe quel type */
    public TypeFichier<E> getTypeFichier()
    {
        return this.f;
    }

    /** Renvoie le registre F */
    public void setRegister(E elt) {
        register = elt;
    }

    /** Renvoie un itérateur sur le fichier */
    public Iterator<E>getIteratorFichier()
    {
        if(f == null)
        {
            throw new NullPointerException("f est null");
        }
        if(f instanceof Pile<?>) {
            Pile<E> pile = (Pile<E>) f;
            return pile.getIterator(0);    
        }
        else if(f instanceof File<?>) {
            File<E> file = (File<E>) f;
            return file.getIterator(0);
        }
        else {
            TableauDynamique<E> td = (TableauDynamique<E>) f;
            return td.getIterator();
        }
    }

    /** Fichier attraper par l'octopunk */
    public void grab(TypeFichier<E> fi) {
        this.f = fi;
    }

    /** Fichier poser par le robot */
    public void drop() {
        f = null;
    }

   /** Test si le fichier est vide */
    public boolean test_eof() {
        if(f == null)
            throw new NullPointerException();

        if(f instanceof Pile<?>) {
            Pile<E> pile = (Pile<E>) f;

            if(pile.height() == 0)
                return true;
            return false;
        }
        else if(f instanceof File<?>) {
            File<E> file = (File<E>) f;

            if(file.length() == 0)
                return true;
            return false;
        }
        else {
            TableauDynamique<E> td = (TableauDynamique<E>) f;

            if(td.size()-1 == td.getIndex())
                return true;
            return false;
        }
    }
    
    /** Supprime un element du fichier */
    public void void_f() {
        if(f == null)
            throw new NullPointerException();

        if(f instanceof Pile<?>) {
            Pile<E> pile = (Pile<E>) f;
            pile.pop();
        }
        else if(f instanceof File<?>) {
            File<E> file = (File<E>) f;
            file.dequeue();
        }
        else {
            TableauDynamique<E> td = (TableauDynamique<E>) f;
            td.remove();
        }
    }
    
    /** Copie une valeur dans un registre */
    public E copy1() {
        if(f == null)
            throw new NullPointerException();

        if(f instanceof Pile<?>) {
            Pile<E> pile = (Pile<E>) f;
            register = pile.peek();
            pile.pop();
            return register;
        }
        else if(f instanceof File<?>) {
            File<E> file = (File<E>) f;
            register = file.head();
            file.dequeue();
            return register;
        }
        else {
            TableauDynamique<E> td = (TableauDynamique<E>) f;
            register = td.getCurrentElement();
            return register;
        }
    }
    
    /** Copie une valeur dans un fichier */
    public void copy2() {
        if(f == null || register == null)
            throw new NullPointerException();

        if(f instanceof Pile<?>) {
            Pile<E> pile = (Pile<E>) f;
            pile.push(register);
            
        }
        else if(f instanceof File<?>) {
            File<E> file = (File<E>) f;
            file.enqueue(register);
        }
        else {
            TableauDynamique<E> td = (TableauDynamique<E>) f;
            td.add(register);
        }
    }

    /** Deplace le curseur dans le fichier */ 
    public  void seek(String curs) {
        if(curs == null)
            throw new NullPointerException();

        int curseur = Integer.parseInt(curs);

        if(f instanceof Pile<?>) {
            Pile<E> pile = (Pile<E>) f;

            while (curseur > 0) {
                pile.pop();
                curseur--;
                if (pile.height() == 0)
                    break;
            }
            
        }
        else if(f instanceof File<?>) {
            File<E> file = (File<E>) f;
            
            while (curseur > 0) {
                file.dequeue();
                curseur--;
                if (file.length() == 0)
                    break;
            }
            
        }
        else {
            TableauDynamique<E> td = (TableauDynamique<E>) f;
           
            while (curseur > 0) {
                td.nextIndex();
                curseur--;
                
                if(td.getIndex() == (td.size()-1))
                    break;
            }
        }
    }
    
    /** Créer un nouveau fichier */
    public TypeFichier<E> make(String cmd) {
        if(cmd == null)
            throw new NullPointerException();
        if(cmd == "MAKEFIFO") {
            return new File<E>();
        }
        else if(cmd == "MAKELIFO") {
            return new Pile<E>();
        }
        else {
            return new TableauDynamique<E>();
        }
        
    }
    
    /** "Supprime" le fichier */
    public void wipe() {
        if(f == null)
            throw new NullPointerException("f est null dans wipe");

        if(f instanceof Pile<?>) {
            Pile<E> pile = (Pile<E>) f;
            pile.clear();    
        }
        else if(f instanceof File<?>) {
            File<E> file = (File<E>) f;
            file.clear();   
        }
        else {
            TableauDynamique<E> td = (TableauDynamique<E>) f;
            td.clear();
        } 
    }
}
