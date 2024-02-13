package main;
import java.util.HashMap;
import java.util.Map;

public class Champ {
    private boolean occupe;

    public Champ() {
        this.occupe = false;
    }

    public boolean estOccupe() {
        return occupe;
    }

    public void occuper() {
        occupe = true;
    }

    public void liberer() {
        occupe = false;
    }
}
