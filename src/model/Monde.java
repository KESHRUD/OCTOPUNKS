package src.model;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import levels.Niveau;

public class Monde
{
    private Niveau level1;
    @SuppressWarnings("unused")
    private List<Instruction> InstruExa1;
    @SuppressWarnings("unused")
    private List<Instruction> InstruExa2;

    /**
     * CONSTRUCTEUR
     */
    public Monde() {
        this.level1 = new Niveau("Niveau1.txt");
        this.InstruExa1 = new ArrayList<>() ;
        this.InstruExa2 = new ArrayList<>();
    }

    /*
     * Steeve doit nous donner 2 String (un pour chaque zone de terminal)
     * Adrien intervient pour changer pour parser ça et le mettre dans les deux listes
     * on copies les deux listes dans chaque liste de chaque robot 
     */

    
    /**
     * Exécute les instructions du robot 1
     * @throws IOException 
     */
    public void executeAutoExa1() throws IOException{
        this.getRobot1().executeAllInstruction();
    }

    /**
     * Exécute les instructions du robot 2
     * @throws IOException 
     */
    public void executeAutoExa2() throws IOException{
        this.getRobot2().executeAllInstruction();
    }

    /**
     * Exécute une instruction sur le robot 1
     * @throws IOException 
     */
    public void executePasExa1() throws IOException{
        this.getRobot1().executeInstruction();
    }

    /**
     * Exécute une instruction sur le robot 2
     * @throws IOException 
     */
    public void executePasExa2() throws IOException{
        this.getRobot2().executeInstruction();
    }

    /**
     * @return le robot 1
     */
    public Robot getRobot1(){
        return level1.getRobot1();
    }

    /**
     * @return le robot 1
     */
    public Robot getRobot2(){
        return level1.getRobot2();
    }
}
