package fichiers;

import java.util.Stack;

public class DoublePile extends Fichier {
    private Stack<Integer> pileAvant;
    private Stack<Integer> pileApres;

    public DoublePile() {
        this.pileAvant = new Stack<>();
        this.pileApres = new Stack<>();
    }

    @Override
    public void lire() {
        if (!pileAvant.isEmpty()) {
            System.out.println("Valeur lue depuis la pile avant : " + pileAvant.pop());
        } else if (!pileApres.isEmpty()) {
            System.out.println("Valeur lue depuis la pile après : " + pileApres.pop());
        } else {
            System.out.println("Erreur : les deux piles sont vides !");
        }
    }

    @Override
    public void ecrire(int valeur) {
        pileAvant.push(valeur);
    }

    @Override
    public void vider() {
        pileAvant.clear();
        pileApres.clear();
    }

    @Override
    public boolean estVide() {
        return pileAvant.isEmpty() && pileApres.isEmpty();
    }
}
