package src.model;

import java.util.Objects;

public class Champ
{
    private TypeCellule type;
    private boolean occupe; // Indique si le champ est occupé
    private String contenu; // Contenu du champ (fichier, registre, robot, etc.)

    public Champ(TypeCellule type) {
        this.type = type;
        this.occupe = false; // Initialise le champ comme non occupé par défaut
        this.contenu = null; // Initialise le contenu comme vide par défaut
    }

    // Méthode estOccupe pour le type EXA
    public boolean estOccupeEXA() {
        return this.occupe && (type == TypeCellule.EXA1 || type == TypeCellule.EXA2);
    }

    // Méthode estOccupe pour le type FICHIER
    public boolean estOccupeFICHIER() {
        return occupe && type == TypeCellule.FICHIER;
    }

    public String getContenu() {
        return contenu;
    }

    public TypeCellule getType() {
        return type;
    }

    public void liberer() {
        this.occupe = false;
        this.contenu = null;
    }

    public void occuper(String contenu) {
        if (type == TypeCellule.EXA1 || type == TypeCellule.FICHIER || type == TypeCellule.EXA2) {
            this.occupe = true;
            this.contenu = contenu;
        } else {
            System.out.println("Impossible d'occuper ce type de champ avec " + type);
        }
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setType(TypeCellule type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Champ)) return false;
        Champ champ = (Champ) o;
        return occupe == champ.occupe && Objects.equals(type, champ.type) && Objects.equals(contenu, champ.contenu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, occupe, contenu);
    }
}
