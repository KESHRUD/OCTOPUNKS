public abstract class Instruction {
    private OpCode opcode;
    private int operand;

    
    public Instruction(OpCode opcode, int operand, OpCode opCode){
        this.opcode = opCode;
        this.operand = operand;
    }

    public OpCode getOpCode(){
        return opcode;
    }

    public int getOperand(){
        return operand;
    }
    public abstract void execute(Robot robot, Grid grid);
}
