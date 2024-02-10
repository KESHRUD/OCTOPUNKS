import java.util.Stack;

public class Instruction {
    private TypeInstruction type;
    private int argument;

    public Instruction(TypeInstruction type, int argument) {
        this.type = type;
        this.argument = argument;
    }

    public TypeInstruction getType() {
        return type;
    }

    public int getArgument() {
        return argument;
    }

    // Méthode pour exécuter une instruction
  //ces méthodes sont à implementer dans la classe Robot
    public void executer(Monde monde, Robot robot) {
        switch (type) {
            case COPY:
                monde.copy(robot, argument);
                break;
            case ADDI:
                robot.add(argument);
                break;
            case MULI:
                robot.multiply(argument);
                break;
            case SUBI:
                robot.subtract(argument);
                break;
            case JUMP:
                robot.jump(argument);
                break;
            case FJMP:
                robot.conditionalJump(argument);
                break;
            case LINK:
                robot.link(argument);
                break;
            case TEST_EOF:
                robot.testEndOfFile();
                break;
            case GRAB:
                robot.grab();
                break;
            case DROP:
                robot.drop();
                break;
            case NOOP:
                // Ne rien faire
                break;
            case DIVI:
                robot.divide(argument);
                break;
            case MODI:
                robot.modulo(argument);
                break;
            case SWIZ:
                robot.swizzle();
                break;
            case TEST:
                robot.test(argument);
                break;
            case KILL:
                robot.kill();
                break;
            case MODE:
                robot.mode(argument);
                break;
            case TEST_MRD:
                robot.testMemory(argument);
                break;
            case VOID_M:
                robot.voidMemory();
                break;
            case SEEK:
                robot.seek(argument);
                break;
            case MAKE:
                robot.make(argument);
                break;
            case WIPE:
                robot.wipe();
                break;
            case VOID_F:
                robot.voidFile();
                break;
            default:
                throw new UnsupportedOperationException("Instruction non prise en charge : " + type);
        }
    }
}
