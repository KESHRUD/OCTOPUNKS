package files;

public abstract class Fichier {
    
    public abstract void lire();
    
    public abstract void ecrire(int valeur);
    
    public abstract boolean estVide();

    public abstract void vider();
}
