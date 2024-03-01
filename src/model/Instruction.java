package src.model;

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
        MAKEFIFO,
        MAKELIFO,
        WIPE,
        VOID_F,
        HALT;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instruction)) return false;
        Instruction instruction = (Instruction) o;
        return instructionType == instruction.instructionType && Arrays.equals(arguments, instruction.arguments);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(instructionType);
        result = 31 * result + Arrays.hashCode(arguments);
        return result;
    }
}
