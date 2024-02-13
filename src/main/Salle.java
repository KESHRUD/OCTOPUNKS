public class Salle {
    private Champ[][] contenu; // Représentation de ce qui se trouve dans la salle
    private Map<Integer, String> portes; // Représentation des portes numérotées

    // Constructeur
    public Salle() {
        this.contenu = new Champ[5][5];
        initialiserContenu();
        this.portes = new HashMap<>();
    }

    private void initialiserContenu() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                contenu[i][j] = new Champ();
            }
        }
    }

    // Méthode pour ajouter une porte
    public void ajouterPorte(int numeroPorte, String destination) {
        portes.put(numeroPorte, destination);
    }

    // Méthode pour vérifier si un champ est occupé
    public boolean estOccupe(int x, int y) {
        return contenu[x][y].estOccupe();
    }

    // Méthode pour occuper un champ
    public void occuperChamp(int x, int y) {
        contenu[x][y].occuper();
    }

    // Méthode pour libérer un champ
    public void libererChamp(int x, int y) {
        contenu[x][y].liberer();
    }

    // Getters et setters
    public Champ[][] getContenu() {
        return contenu;
    }

    public void setContenu(Champ[][] contenu) {
        this.contenu = contenu;
    }

    public Map<Integer, String> getPortes() {
        return portes;
    }

    public void setPortes(Map<Integer, String> portes) {
        this.portes = portes;
    }
}
