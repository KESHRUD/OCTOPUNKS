public enum TypeCellule {
    VIDE(' '),
    EXA('E'),
    FICHIER('F'),
    REGISTRE('R'),
    CIBLE('@'),
    MUR('#'),
    ENTREE('I'),
    ARRIERE('1');

    private final char symbol;

    TypeCellule(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Obtient le symbole de la cellule.
     *
     * @return le symbole de la cellule
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Obtient le type de cellule correspondant à un caractère.
     *
     * @param c le caractère à convertir en type de cellule
     * @return le type de cellule correspondant
     * @throws IllegalArgumentException si le caractère n'est pas reconnu
     */
    public static TypeCellule fromSymbol(char c) {
        return switch (c) {
            case ' ' -> VIDE;
            case 'E' -> EXA;
            case 'F' -> FICHIER;
            case 'R' -> REGISTRE;
            case '@' -> CIBLE;
            case '#' -> MUR;
            case 'I' -> ENTREE;
            case '1' -> ARRIERE;
            default -> throw new IllegalArgumentException("Le caractère " + c + " n'est pas reconnu!");
        };
    }
}
