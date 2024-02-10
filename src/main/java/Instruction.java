package src.main.java;

public class Instruction {
    private final String nomInstruction;
    private String[] listArgs;

    public Instruction(String command){

        this.nomInstruction = command;
    }
    public Instruction(String command, String arg1){
        this.nomInstruction = command;
        this.listArgs = new String[1];
        this.listArgs[0] = arg1;
    }

    public Instruction(String command, String arg1, String arg2){
        this.nomInstruction = command;
        this.listArgs = new String[2];
        this.listArgs[0] = arg1;
        this.listArgs[1] = arg2;

    }

    public Instruction(String command, String arg1,String arg2,String arg3 ){
        this.nomInstruction = command;
        this.listArgs = new String[3];
        this.listArgs[0] = arg1;
        this.listArgs[1] = arg2;
        this.listArgs[2] = arg3;
    }

    public String getNomInstruction() {
        return nomInstruction;
    }
    public String[] getInstructionArgs(){
        return listArgs;
    }

    public void printInstruction(){
        System.out.print(nomInstruction);
        if (listArgs != null)
            for (String args : listArgs)
                System.out.print(" " + args);
        System.out.println("\n");
    }
    // Méthode pour exécuter une instruction
  //ces méthodes sont à implementer dans la classe Robot
    public void executer(Monde monde, Robot robot) {
        switch (nomInstruction) {
            case "COPY":
                monde.copy(robot, argument);
                break;
            case "ADDI":
                robot.add(argument);
                break;
            case "MULI":
                robot.multiply(argument);
                break;
            case "SUBI":
                robot.subtract(argument);
                break;
            case "JUMP":
                robot.jump(argument);
                break;
            case "FJMP":
                robot.conditionalJump(argument);
                break;
            case "LINK":
                robot.link(argument);
                break;
            case "TEST_EOF":
                robot.testEndOfFile();
                break;
            case "GRAB":
                robot.grab();
                break;
            case "DROP":
                robot.drop();
                break;
            case "NOOP":
                // Ne rien faire
                break;
            case "DIVI":
                robot.divide(argument);
                break;
            case "MODI":
                robot.modulo(argument);
                break;
            case "SWIZ":
                robot.swizzle();
                break;
            case "TEST":
                robot.test(argument);
                break;
            case "KILL":
                robot.kill();
                break;
            case "MODE":
                robot.mode(argument);
                break;
            case "TEST_MRD":
                robot.testMemory(argument);
                break;
            case "VOID_M":
                robot.voidMemory();
                break;
            case "SEEK":
                robot.seek(argument);
                break;
            case "MAKE":
                robot.make(argument);
                break;
            case "WIPE":
                robot.wipe();
                break;
            case "VOID_F":
                robot.voidFile();
                break;
            default:
                throw new UnsupportedOperationException("Instruction non prise en charge : " + type);
        }
    }
}
