public class Monde {
    private Niveau level1;

    public Monde(){
        level1 = new Niveau("Niveau1.txt");
    }

    public Robot geRobot1(){
        return level1.getRobot1();
    }

    public Robot geRobot2(){
        return level1.getRobot2();
    }

}
