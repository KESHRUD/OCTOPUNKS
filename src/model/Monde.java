package src.model;


import java.util.ArrayList;
import java.util.List;

public class Monde {
    private Niveau level1;
    private List<Instruction> InstruExa1;
    private List<Instruction> InstruExa2;
    public Monde(){
        level1 = new Niveau("Niveau1.txt");
        this.InstruExa1 = new ArrayList<>() ;
        this.InstruExa2 = new ArrayList<>();
    }

    /*
     * Steeve doit nous donner 2 String (un pour chaque zone de terminal)
     * Adrien intervient pour changer pour parser ça et le mettre dans les deux listes
     * on copies les deux listes dans chaque liste de chaque robot 
     */

    public Robot geRobot1(){
        return level1.getRobot1();
    }

    public Robot geRobot2(){
        return level1.getRobot2();
    }

    public void executePasExa1(){
        this.geRobot1().executeInstruction();
    }

    public void executePasExa2(){
        this.geRobot2().executeInstruction();
    }

    public void executeAutoExa1(){
        this.geRobot1().executeAllInstruction();
    }

    public void executeAutoExa2(){
        this.geRobot2().executeAllInstruction();
    }


}
