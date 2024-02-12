package src.main.java;

public class Instruction {
    private final InstructionType instructionType;
    private String[] arguments;

    public Instruction(InstructionType instructionType) {
        this.instructionType = instructionType;
    }

    public Instruction(InstructionType instructionType, String... arguments) {
        this.instructionType = instructionType;
        this.arguments = arguments;
    }

    public InstructionType getInstructionType() {
        return instructionType;
    }

    public String[] getArguments() {
        return arguments;
    }

    public void printInstruction() {
        System.out.print(instructionType);
        if (arguments != null) {
            for (String arg : arguments) {
                System.out.print(" " + arg);
            }
        }
        System.out.println();
    }

    /*public void execute(Monde monde, Robot robot) {
        switch (instructionType) {
            case COPY:
                monde.copy(robot, arguments[0]);
                break;
            case ADDI:
                robot.add(arguments[0]);
                break;
            case MULI:
                robot.multiply(arguments[0]);
                break;
            case SUBI:
                robot.subtract(arguments[0]);
                break;
            case JUMP:
                robot.jump(Integer.parseInt(arguments[0]));
                break;
            case FJMP:
                robot.conditionalJump(Integer.parseInt(arguments[0]));
                break;
            case LINK:
                robot.link(arguments[0]);
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
                robot.divide(arguments[0]);
                break;
            case MODI:
                robot.modulo(arguments[0]);
                break;
            case SWIZ:
                robot.swizzle();
                break;
            case TEST:
                robot.test(arguments[0]);
                break;
            case KILL:
                robot.kill();
                break;
            case MODE:
                robot.mode(arguments[0]);
                break;
            case TEST_MRD:
                robot.testMemory(arguments[0]);
                break;
            case VOID_M:
                robot.voidMemory();
                break;
            case SEEK:
                robot.seek(Integer.parseInt(arguments[0]));
                break;
            case MAKE:
                robot.make(arguments[0]);
                break;
            case WIPE:
                robot.wipe();
                break;
            case VOID_F:
                robot.voidFile();
                break;
            default:
                throw new UnsupportedOperationException("Instruction non prise en charge : " + instructionType);
        }
    }*/

    public enum InstructionType {
        COPY,
        ADDI,
        MULI,
        SUBI,
        JUMP,
        FJMP,
        LINK,
        TEST_EOF,
        GRAB,
        DROP,
        NOOP,
        DIVI,
        MODI,
        SWIZ,
        TEST,
        KILL,
        MODE,
        TEST_MRD,
        VOID_M,
        SEEK,
        MAKE,
        WIPE,
        VOID_F,
        HALT
    }
}
