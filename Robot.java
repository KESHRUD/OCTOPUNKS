public class Robot {
    private int positionX;
    private int positionY;
    private int[] memory;
    private int registreX;
    private int registreT;
    private int registreF;
    private int registreM;

    // Constructeur
    public Robot(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.memory = new int[2]; // Deux zones de mémoire par robot
    }

    // Méthode pour déplacer le robot en fonction des instructions
    public void move(int deltaX, int deltaY) {
        // Mise à jour de la position du robot
        positionX += deltaX;
        positionY += deltaY;
    }

    // Méthode pour vérifier si le robot a atteint la condition de victoire
    public boolean checkVictoryCondition(Monde monde, Robot autreRobot) {
        // Vérifier si les robots sont sur des cases opposées
        return (positionX == 0 && autreRobot.getPositionX() == 4 && positionY == autreRobot.getPositionY()) ||
               (positionX == 4 && autreRobot.getPositionX() == 0 && positionY == autreRobot.getPositionY()) ||
               (positionY == 0 && autreRobot.getPositionY() == 4 && positionX == autreRobot.getPositionX()) ||
               (positionY == 4 && autreRobot.getPositionY() == 0 && positionX == autreRobot.getPositionX());
    }

    // Getters et setters
    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int[] getMemory() {
        return memory;
    }

    public void setMemory(int[] memory) {
        this.memory = memory;
    }

    public int getRegistreX() {
        return registreX;
    }

    public void setRegistreX(int registreX) {
        this.registreX = registreX;
    }

    public int getRegistreT() {
        return registreT;
    }

    public void setRegistreT(int registreT) {
        this.registreT = registreT;
    }

    public int getRegistreF() {
        return registreF;
    }

    public void setRegistreF(int registreF) {
        this.registreF = registreF;
    }

    public int getRegistreM() {
        return registreM;
    }

    public void setRegistreM(int registreM) {
        this.registreM = registreM;
    }
}
