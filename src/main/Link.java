public class Link {
    private int idLien;
    private char typePorteDepart;
    private char typePorteArrivee;

    public Link(int idLien, char typePorteDepart, char typePorteArrivee) {
        this.idLien = idLien;
        if (typePorteDepart != 'I' && typePorteDepart != '1') {
            throw new IllegalArgumentException("Le type de porte au départ doit être 'I' ou '1'");
        }
        if (typePorteArrivee != 'I' && typePorteArrivee != '1') {
            throw new IllegalArgumentException("Le type de porte à l'arrivée doit être 'I' ou '1'");
        }
        this.typePorteDepart = typePorteDepart;
        this.typePorteArrivee = typePorteArrivee;
    }

    public int getIdLien() {
        return idLien;
    }

    public void setIdLien(int idLien) {
        this.idLien = idLien;
    }

    public char getTypePorteDepart() {
        return typePorteDepart;
    }

    public void setTypePorteDepart(char typePorteDepart) {
        if (typePorteDepart != 'I' && typePorteDepart != '1') {
            throw new IllegalArgumentException("Le type de porte au départ doit être 'I' ou '1'");
        }
        this.typePorteDepart = typePorteDepart;
    }

    public char getTypePorteArrivee() {
        return typePorteArrivee;
    }

    public void setTypePorteArrivee(char typePorteArrivee) {
        if (typePorteArrivee != 'I' && typePorteArrivee != '1') {
            throw new IllegalArgumentException("Le type de porte à l'arrivée doit être 'I' ou '1'");
        }
        this.typePorteArrivee = typePorteArrivee;
    }
}
