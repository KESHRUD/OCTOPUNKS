public class Robot {
    private int positionX;
    private int positionY;
    private int[] memory;
    private int registreX;
    private int registreT;
    private int registreF;
    private int registreM;

    // Constructeur
    public Robot(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.memory = new int[2]; // Deux zones de mémoire par robot
    }

    // Méthode pour déplacer le robot en fonction des instructions
    public void move(int deltaX, int deltaY) {
        // Mise à jour de la position du robot
        positionX += deltaX;
        positionY += deltaY;
    }

    // Méthode pour vérifier si le robot a atteint la condition de victoire
    public boolean checkVictoryCondition(Monde monde, Robot autreRobot) {
        // Vérifier si les robots sont sur des cases opposées
        return (positionX == 0 && autreRobot.getPositionX() == 4 && positionY == autreRobot.getPositionY()) ||
               (positionX == 4 && autreRobot.getPositionX() == 0 && positionY == autreRobot.getPositionY()) ||
               (positionY == 0 && autreRobot.getPositionY() == 4 && positionX == autreRobot.getPositionX()) ||
               (positionY == 4 && autreRobot.getPositionY() == 0 && positionX == autreRobot.getPositionX());
    }

    // Getters et setters
    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int[] getMemory() {
        return memory;
    }

    public void setMemory(int[] memory) {
        this.memory = memory;
    }

    public int getRegistreX() {
        return registreX;
    }

    public void setRegistreX(int registreX) {
        this.registreX = registreX;
    }

    public int getRegistreT() {
        return registreT;
    }

    public void setRegistreT(int registreT) {
        this.registreT = registreT;
    }

    public int getRegistreF() {
        return registreF;
    }

    public void setRegistreF(int registreF) {
        this.registreF = registreF;
    }

    public int getRegistreM() {
        return registreM;
    }

    public void setRegistreM(int registreM) {
        this.registreM = registreM;
    }
    public void setRegisterValue(String registre , int value){
        switch(registre){
            case "F":
                setRegistreF(value);
                break;
            case "M":
                setRegistreM(value);
                break;
            case "T":
                setRegistreM(value);
                break;
            case "X":
                setRegistreX(value);
                break;
            default:
            throw new IllegalArgumentException("erreur de registre donné en argument");
        }
    }

    public void jumpToLabel(int label){
        //d'apres le prof on doit juste untiliser un entier
        // comme ca on saute le nombre indiqué d'instruction
    }

    public boolean end_of_file(){
        //je sais pas encore quel fichier tester 
        //a continuer
        return false;
    }

    public boolean canReadFromM(){
        //j'ai pas bien compris son fonctionnement
        //a completer
        return true;
    }

    /**
     * @param instruction
     */
    public void executeInstruction(Instruction instruction) {
        String[] arguments = instruction.getArguments();
        int valueA;
        int valueB;
    switch (instruction.getInstructionType()) {
        case COPY:
            if (arguments.length == 2) {
                //on doit gerer le cas ou le registre source n'est pas un registre valide mais jsp comment faire vu que les registre sont des int
                valueA = Integer.parseInt(arguments[0]);
                setRegisterValue(arguments[1], valueA); //on doit copier le 2eme argument dans le premier argument
                
            } else {
                throw new IllegalArgumentException("Invalid number of arguments for COPY instruction");
            }
            break;
        case ADDI: //ADDI a(R/N) b(R/N) dest(R)
            if (instruction.getArguments().length == 3) {
                valueA = Integer.parseInt(arguments[0]);
                valueB = Integer.parseInt(arguments[1]);
                int sum = valueA + valueB;
                setRegisterValue(arguments[2], sum);
                
            } else {
                throw new IllegalArgumentException("Invalid number of arguments for ADDI instruction");
            }
            break;
        case MULI: // MULI a(R/N) b(R/N) dest(R)
            if (arguments.length == 3) {
                valueA = Integer.parseInt(arguments[0]);
                valueB = Integer.parseInt(arguments[1]);
                int product = valueA * valueB;
                setRegisterValue(arguments[2], product);
            } else {
                throw new IllegalArgumentException("Invalid number of arguments for MULI instruction");
            }
            break;
        case SUBI:// SUBI a(R/N) b(R/N) dest(R)
            if (arguments.length == 3) {
                valueA = Integer.parseInt(arguments[0]);
                valueB = Integer.parseInt(arguments[1]);
                int diff = valueA - valueB;
                setRegisterValue(arguments[2], diff);
            } else {
                throw new IllegalArgumentException("Invalid number of arguments for SUBI instruction");
            }
            break;
        case DIVI:
            if (arguments.length == 3) {
                valueA = Integer.parseInt(arguments[0]);
                valueB = Integer.parseInt(arguments[1]);
                if (valueB == 0) {
                    throw new IllegalArgumentException("Division by zero is not allowed");
                }
                int quotient = valueA / valueB;
                setRegisterValue(arguments[2], quotient);
            } else {
                throw new IllegalArgumentException("Invalid number of arguments for DIVI instruction");
            }
            break;
        case MODI:
             if (arguments.length == 3) {
                valueA = Integer.parseInt(arguments[0]);
                valueB = Integer.parseInt(arguments[1]);
                if (valueB == 0) {
                    throw new IllegalArgumentException("Division by zero is not allowed");
                }
                int modulo = valueA % valueB;
                setRegisterValue(arguments[2], modulo);
            } else {
                throw new IllegalArgumentException("Invalid number of arguments for MODI instruction");
            }
            break;
        case SWIZ:
            if (arguments.length == 3) {
                //a continuer (logqiue un peu compliqué ca prend du temps je reviens des que je finis les autres)
                throw new IllegalArgumentException("Invalid number of arguments for SWIZ instruction");
            }
            break;
            case JUMP:// JUMP N le on doit sauter N ligne dans le terminal
            if (arguments.length == 1) {
                valueA = Integer.parseInt(instruction.getArguments()[0]);
                jumpToLabel(valueA);//faut revoir instruction snn je peux pas implementer jumpToLabel
            } else {
                throw new IllegalArgumentException("Invalid number of arguments for JUMP instruction");
            }
            break;
        case FJMP:
            if (arguments.length == 1) { //jump avec condition
                valueA = Integer.parseInt(arguments[0]); 
                if (registreT == 0) { //condition 
                    jumpToLabel(valueA); //sauter 
                    //faut revoir instruction snn je peux pas implementer jumpToLabel 
                    //je vais utiliser parse 
                }
            } else {
                throw new IllegalArgumentException("Invalid number of arguments for FJMP instruction");
            }
            break;
        case LINK: //LINK dest(R/N)
            if (arguments.length == 1) {
                int link = Integer.parseInt(arguments[0]);
                //Traverse the link numbered dest. If such a link does not exist, the EXA will crash
                //linkTo(link);
                // A COMPLETER : la logique du code actuel me permet pas de la faire
            } else {
                throw new IllegalArgumentException("Invalid number of arguments for LINK instruction");
            }
            break;
        case TEST_EOF:
            if (arguments.length == 0) {
                if (end_of_file()) { //tester la fin de fichier
                    registreT = 1;
                } else {
                    registreT = 0;
                }
            } else {
                throw new IllegalArgumentException("Invalid number of arguments for TEST_EOF instruction");
            }
            break;
        case GRAB:
            if (arguments.length == 1) {
                //problem lier aux attributs de robots 
                //a mon avis il faut un attribut fichier(que tient le robot) avec ses methodes
            } else {
                throw new IllegalArgumentException("Invalid number of arguments for GRAB instruction");
            }
            break;
        case DROP:
            if (arguments.length == 0) {
                //pareil probleme de fichier
            } else {
                throw new IllegalArgumentException("Invalid number of arguments for DROP instruction");
            }
            break;
        case NOOP:
            if (arguments.length == 0) {
                // Do nothing
            } else {
                throw new IllegalArgumentException("Invalid number of arguments for NOOP instruction");
            }
            break;
        case TEST: //TEST a(R/N) = b(R/N)  avec < et > aussi 
            if (arguments.length == 3) { // 3 arguments 1 pour a 1 pour b 1 pour l'operateur
                valueA = Integer.parseInt(arguments[0]);
                valueB = Integer.parseInt(arguments[2]);
                if(arguments[1].equals("=")){
                    registreT = (valueA == valueB);
                }else if(arguments[1].equals("<")){
                    registreT = (valueA < valueB);
                }else if(arguments[1].equals(">")){
                    registreT = (valueA > valueB);
                }else{
                    throw new IllegalArgumentException("the opperand " + arguments[1] + "is not suppoerted");
                }
                //reste a gerer If a and b are different types the result is always false.
            } else {
                throw new IllegalArgumentException("Invalid number of arguments for TEST instruction");
            }
            break;
        case KILL:
            if (arguments.length == 0) {
                //a mon avis faut l'implmenetr dans monde car ca consiste a tuer l'autre robot
            } else {
                throw new IllegalArgumentException("Invalid number of arguments for KILL instruction");
            }
            break;
        case MODE:
            if (arguments.length == 0) {
                //le manuel dit ca Toggle the M register between the local and global channels.
                //mais j'ai pas compris ce que ca fait 
            } else {
                throw new IllegalArgumentException("Invalid number of arguments for MODE instruction");
            }
            break;
        case TEST_MRD:
            if (arguments.length == 0) {
                if (canReadFromM()) { //a voir mardi
                    registreT = 1;
                } else {
                    registreT = 0;
                }
            } else {
                throw new IllegalArgumentException("Invalid number of arguments for TEST_MRD instruction");
            }
            break;
        case VOID_M:
            if (arguments.length == 0) {
                //pareil a revoir 
            } else {
                throw new IllegalArgumentException("Invalid number of arguments for VOID_M instruction");
            }
            break;
        case SEEK:
            if (arguments.length == 1) {
                //pareil
            } else {
                throw new IllegalArgumentException("Invalid number of arguments for SEEK instruction");
            }
            break;
        case MAKE:
            if (instruction.getArguments().length == 1) {
                //probleme de fichier le robot doit creer et grap un nv fichier 
                //les attribust actuelle ne permettent pas ca 
            } else {
                throw new IllegalArgumentException("Invalid number of arguments for MAKE instruction");
            }
            break;
        case WIPE:
            if (arguments.length == 0) {
                //probleme de fichier 
                //les attribust actuelle ne permettent pas ca 
            } else {
                throw new IllegalArgumentException("Invalid number of arguments for WIPE instruction");
            }
            break;
        case VOID_F:
            if (instruction.getArguments().length == 0) {
                //probleme de fichier le robot doit creer et grap un nv fichier 
                //les attribust actuelle ne permettent pas ca 
            } else {
                throw new IllegalArgumentException("Invalid number of arguments for VOID_F instruction");
            }
            break;
        default:
            throw new IllegalArgumentException("Unknown instruction type: " + instruction.getInstructionType());
}
}
}

