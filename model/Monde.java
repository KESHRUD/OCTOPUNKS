public class Monde {
    private Salle[][] salles; // Représentation des salles dans le monde

    // Constructeur
    public Monde() {
        this.salles = new Salle[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                this.salles[i][j] = new Salle();
            }
        }
    }

    // Méthode pour vérifier si un champ dans une salle est occupé
    public boolean estChampOccupe(int salleX, int salleY, int champX, int champY) {
        return salles[salleX][salleY].estOccupe(champX, champY);
    }

    // Méthode pour occuper un champ dans une salle
    public void occuperChamp(int salleX, int salleY, int champX, int champY) {
        salles[salleX][salleY].occuperChamp(champX, champY);
    }

    // Méthode pour libérer un champ dans une salle
    public void libererChamp(int salleX, int salleY, int champX, int champY) {
        salles[salleX][salleY].libererChamp(champX, champY);
    }

    // Méthode pour vérifier les conditions de victoire
    public boolean checkVictoryCondition(Robot robot1, Robot robot2) {
        // Récupérer les positions des deux robots
        int robot1X = robot1.getPositionX();
        int robot1Y = robot1.getPositionY();
        int robot2X = robot2.getPositionX();
        int robot2Y = robot2.getPositionY();

        // Vérifier si les robots se trouvent sur des cases opposées
        return (robot1X == 0 && robot2X == 4 && robot1Y == robot2Y) ||
               (robot1X == 4 && robot2X == 0 && robot1Y == robot2Y) ||
               (robot1Y == 0 && robot2Y == 4 && robot1X == robot2X) ||
               (robot1Y == 4 && robot2Y == 0 && robot1X == robot2X);
    }

    // Getters et setters
    public Salle[][] getSalles() {
        return salles;
    }

    public void setSalles(Salle[][] salles) {
        this.salles = salles;
    }
}