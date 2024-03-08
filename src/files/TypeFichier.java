package src.files;

public abstract class TypeFichier<E> {
    private boolean grab; /** Fichier attrapé ou non par le robot */

    public boolean getGrab() {
        return grab;
    }

    public void grab() {
        grab = true;
    }

    public void drop() {
        grab = false;
    }

    public abstract void clear();
}