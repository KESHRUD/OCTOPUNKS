package src.model;

public enum TypeCellule
{
    VIDE(' '),
    EXA1('E'),
    EXA2('X'),
    FICHIER('F'),
    MUR('#'),
    ENTREE('I'),
    ARRIERE('1'),
    LINK('|'),
    ENTRE_DEUX_SALLES('V');

    private final char symbol;

    TypeCellule(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Retourne le type de cellule correspondant à un caractère.
     * @param c le caractère à convertir en type de cellule
     * @return le type de cellule correspondant
     * @throws IllegalArgumentException si le caractère n'est pas reconnu
     */
    public static TypeCellule fromSymbol(char c) {
        return switch (c) {
            case ' ' -> VIDE;
            case 'E' -> EXA1;
            case 'X' -> EXA2;
            case 'F' -> FICHIER;
            case '#' -> MUR;
            case 'I' -> ENTREE;
            case '1' -> ARRIERE;
            case '|' -> LINK;
            case 'V' -> ENTRE_DEUX_SALLES;
            default -> throw new IllegalArgumentException("Le caractère " + c + " n'est pas reconnu!");
        };
    }

    /**
     * @return le symbole de la cellule
     */
    public char getSymbol() {
        return symbol;
    }
}
