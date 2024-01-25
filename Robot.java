import java.awt.Point;

public class Robot {
    private Point position;
    private int codePointer;

    public Robot(Point position, int codePointer){
        this.position = position;
        this.codePointer = codePointer;
    }
    
    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getCodePointer() {
        return codePointer;
    }

    public void setCodePointer(int codePointer) {
        this.codePointer = codePointer;
    }

    public void executeInstruction(Instruction instruction, Grid grid){
        instruction.execute(this, grid);
    }
}
