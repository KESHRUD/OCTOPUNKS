package fichiers;

public class Arbre extends Fichier {
    private static class Noeud {
        char valeur;
        Noeud gauche;
        Noeud droit;

        Noeud(char valeur) {
            this.valeur = valeur;
            gauche = droit = null;
        }
    }

    private Noeud racine;

    public Arbre() {
        racine = null;
    }

    @Override
    public void lire() {
        if (racine != null) {
            System.out.println("Lecture de l'arbre (parcours infixé) : ");
            parcoursInfixe(racine);
        } else {
            System.out.println("L'arbre est vide !");
        }
    }

    @Override
    public void ecrire(int valeur) {
        if (racine == null) {
            racine = new Noeud((char) valeur);
        } else {
            inserer(racine, valeur);
        }
    }

    @Override
    public void vider() {
        racine = null;
    }

    @Override
    public boolean estVide() {
        return racine == null;
    }

    // Méthode pour parcourir l'arbre de manière infixée (gauche, racine, droit)
    private void parcoursInfixe(Noeud noeud) {
        if (noeud != null) {
            parcoursInfixe(noeud.gauche);
            System.out.print(noeud.valeur + " ");
            parcoursInfixe(noeud.droit);
        }
    }

    // Méthode pour insérer une valeur dans l'arbre
    private void inserer(Noeud noeud, int valeur) {
        if (valeur < noeud.valeur) {
            if (noeud.gauche != null) {
                inserer(noeud.gauche, valeur);
            } else {
                noeud.gauche = new Noeud((char) valeur);
            }
        } else if (valeur > noeud.valeur) {
            if (noeud.droit != null) {
                inserer(noeud.droit, valeur);
            } else {
                noeud.droit = new Noeud((char) valeur);
            }
        }
    }
}
