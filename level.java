import java.util.ArrayList;
import java.util.List;

public class level {
    private int levelNumber;
    private List<Instruction> instructions;
    private List<Robot> robots;

    /**
     * initialisation d'une nouvelle instance level
     * (niveau)
     */
    public level(int levelNumber){
        this.levelNumber = levelNumber;
        this.instructions = new ArrayList<>();
        this.robots = new ArrayList<>();
    }

    public int getLevelNumber(){
        return levelNumber;
    }

    public void addInstruction(Instruction Instruction){
        this.instructions.add(Instruction);
    }

    public List<Instruction> getInstructions(){
        return instructions;
    }

    public void addRobot(Robot robot){
        this.robots.add(robot);
    }

    public List<Robot> getRobots(){
        return this.robots;
    }

}
