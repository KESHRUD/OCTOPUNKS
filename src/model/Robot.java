package src.model;

import java.util.ArrayList;
import java.util.List;

public class Robot {
    private Coordonnees position; //position x y et salle
    private int registreX;//les 4 registres pour chaque robot
    private int registreT;
    private int registreF;//ptr de fichier contient l'id du fichier
    private int registreM;
    private leFichier file; //le fichier que le robot peut tenir
    private boolean estMort; //si le robot est mort ou pas (est sur la salle)
    private List<Instruction> lesInstructions; //liste des instructions que chaque robot va executer
    private int  index; //pour naviguer dans la liste d'instruction
    
    
    // Constructeur
    public Robot(Coordonnees position) {
        file = null;
        estMort = false;
        registreF = 0; 
        registreM = 0;
        registreT = 0;
        registreX = 0;
        index = 0; //premiere instruction a éxecuter;
        this.position = position;
        position.getSalle().occuperChamp(position, TypeCellule.EXA1); //le robot doit occuper une salle
        lesInstructions = new ArrayList<>();
    }
    
    //methode pour verifier si le robot tient un fichier
    public boolean hasAFile(){
        return (this.file != null);
    }
    
    // Méthode pour déplacer le robot en fonction des instructions
    public void move(int deltaX, int deltaY) {
        // Mise à jour de la position du robot en verifiant si le mouvement est possible donc x et y entre 0 et 4
        if(position.getX() + deltaX < 0 || position.getX() + deltaX > 4 ||
           position.getY() + deltaY < 0 || position.getY() + deltaY > 4 ){
               System.err.println("le mouvement est impossible");
        }else{
            setPositionX(deltaX);
            setPositionY(deltaY);    
        }
    }
    public boolean EstMort() {
         return estMort;
     }
 
     public void setEstMort(boolean estMort) {
         this.estMort = estMort;
     }
    public Salle getCurrentSalle() {
        return position.getSalle();
    }
    
    public void setCurrentSalle(Salle currentSalle) {
        this.position.setCurrentSalle(currentSalle);
    }
    
    // Getters et setters
    
    public leFichier getFile(){
        return file;
    }
    
    public void setFile(leFichier newFile){
        this.file = newFile;
        setRegistreM(newFile.getId());
    }
    
    public List<Instruction> getLesInstructions() {
        return lesInstructions;
    }

    public void setLesInstructions(List<Instruction> lesInstructions) {
        this.lesInstructions = lesInstructions;
    }
    public int getPositionX() {
        return position.getX();
    }

    public void setPositionX(int positionX) {
        this.position.setX(positionX);
    }

    public int getPositionY() {
        return position.getY();
    }

    public void setPositionY(int positionY) {
        this.position.setY(positionY);
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
                setRegistreT(value);
                break;
            case "X":
                setRegistreX(value);
                break;
            default:
            throw new IllegalArgumentException("erreur de registre donné en argument");
        }
    }

    //COPY source(R/N) dest(R)
    public void copy(String src, String dest){
        int value;
        switch(src){
            case "F":
                value = getRegistreF();
                break;
            case "M":
                value = getRegistreM();
                break;
            case "T":
                value = getRegistreT();
                break;
            case "X":
                value = getRegistreX();
                break;
            default :
                value = Integer.parseInt(src);
                break;
        }
        setRegisterValue(dest, value);//dest doit etre un registre
    }
    //ADDI a(R/N) b(R/N) dest(R)
    public void add(String a, String b, String dest){
        int valueA, valueB;
        switch(a){
            case "F":
                valueA = getRegistreF();
                break;
            case "M":
                valueA = getRegistreM();
                break;
            case "T":
                valueA = getRegistreT();
                break;
            case "X":
                valueA = getRegistreX();
                break;
            default :
                valueA = Integer.parseInt(a);
                break;
        }
        switch(b){
            case "F":
                valueB = getRegistreF();
                break;
            case "M":
                valueB = getRegistreM();
                break;
            case "T":
                valueB = getRegistreT();
                break;
            case "X":
                valueB = getRegistreX();
                break;
            default :
                valueB = Integer.parseInt(b);
                break;
        }
        int sum = valueA + valueB;
        setRegisterValue(dest, sum);
    }

    public void multiply(String a, String b, String dest){
        int valueA, valueB;
        switch(a){
            case "F":
                valueA = getRegistreF();
                break;
            case "M":
                valueA = getRegistreM();
                break;
            case "T":
                valueA = getRegistreT();
                break;
            case "X":
                valueA = getRegistreX();
                break;
            default :
                valueA = Integer.parseInt(a);
                break;
        }
        switch(b){
            case "F":
                valueB = getRegistreF();
                break;
            case "M":
                valueB = getRegistreM();
                break;
            case "T":
                valueB = getRegistreT();
                break;
            case "X":
                valueB = getRegistreX();
                break;
            default :
                valueB = Integer.parseInt(b);
                break;
        }
        int product = valueA * valueB;
        setRegisterValue(dest, product);
    }

    // SUBI a(R/N) b(R/N) dest(R)
    public void substract(String a, String b, String dest){
        int valueA, valueB;
        switch(a){
            case "F":
                valueA = getRegistreF();
                break;
            case "M":
                valueA = getRegistreM();
                break;
            case "T":
                valueA = getRegistreT();
                break;
            case "X":
                valueA = getRegistreX();
                break;
            default :
                valueA = Integer.parseInt(a);
                break;
        }
        switch(b){
            case "F":
                valueB = getRegistreF();
                break;
            case "M":
                valueB = getRegistreM();
                break;
            case "T":
                valueB = getRegistreT();
                break;
            case "X":
                valueB = getRegistreX();
                break;
            default :
                valueB = Integer.parseInt(b);
                break;
        }
        int diff = valueA - valueB;
        setRegisterValue(dest, diff);
    }

    public void divide (String a , String b, String dest){
        int valueA, valueB;
        switch(a){
            case "F":
                valueA = getRegistreF();
                break;
            case "M":
                valueA = getRegistreM();
                break;
            case "T":
                valueA = getRegistreT();
                break;
            case "X":
                valueA = getRegistreX();
                break;
            default :
                valueA = Integer.parseInt(a);
                break;
        }
        switch(b){
            case "F":
                valueB = getRegistreF();
                break;
            case "M":
                valueB = getRegistreM();
                break;
            case "T":
                valueB = getRegistreT();
                break;
            case "X":
                valueB = getRegistreX();
                break;
            default :
                valueB = Integer.parseInt(b);
                break;
        }
        if(valueB == 0){
            throw new IllegalArgumentException("division by zero is not allowed");
        }
        int quotient = valueA / valueB;
        setRegisterValue(dest, quotient);
    }

    public void modulo(String a , String b, String dest){
        int valueA, valueB;
        switch(a){
            case "F":
                valueA = getRegistreF();
                break;
            case "M":
                valueA = getRegistreM();
                break;
            case "T":
                valueA = getRegistreT();
                break;
            case "X":
                valueA = getRegistreX();
                break;
            default :
                valueA = Integer.parseInt(a);
                break;
        }
        switch(b){
            case "F":
                valueB = getRegistreF();
                break;
            case "M":
                valueB = getRegistreM();
                break;
            case "T":
                valueB = getRegistreT();
                break;
            case "X":
                valueB = getRegistreX();
                break;
            default :
                valueB = Integer.parseInt(b);
                break;
        }
        if(valueB == 0){
            throw new IllegalArgumentException("division by zero is not allowed");
        }
        int mod = valueA % valueB;
        setRegisterValue(dest, mod);
    }

    //SWIZ input(R/N) mask(R/N) dest(R)
    public void swizzle(String input, String mask, String dest){
        //a completer
    }



    //JUMP dest(L
    public void jump(String lines){
        int n = Integer.parseInt(lines);
        if((index + n) < 0 && (index + n) > lesInstructions.size()){
            System.err.println("nombre d'instructions a sauter invalide");
        }else{
            index = index + n;
        }
    }

    //JUMP dest(L
    public void conditionalJump(String lines){
        if(registreT == 0){
            jump(lines);
        }else{
            index++;//sinon on passe a l'instruction suivante
        }
    }

    public void link(String a){
        int valueA = Integer.parseInt(a);
        if(valueA == -1){
            if(getCurrentSalle().getLienEntrant() != null){ //dans le cas ou on fait LINK -1 dans la 1ere salle
                setCurrentSalle(getCurrentSalle().getLienEntrant().getSalleAvant()); //la salle actuelle est la salle d'avant
            }
        }else{
            for(Link unLien : getCurrentSalle().getLiensSortant()){
                if(unLien.getId() == valueA){
                    setCurrentSalle(unLien.getSalleApres());
                    return;
                }
            }
            setEstMort(true);//le robot doit mourir dans ce cas
        }
    }


    public void testEndOfFile(){
        // a completer
    }

    

    public void grab(String theFile){
        if(this.hasAFile()){
            System.err.println("Fatal error: CANNOT GRAB A SECOND FILE");
        }else{
            int idFile = Integer.parseInt(theFile);
            if(getCurrentSalle().getTheFile() != null){
                if(idFile == getCurrentSalle().getTheFile().getId()){
                    this.file = getCurrentSalle().getTheFile();
                    getCurrentSalle().getTheFile().setPosition(this.position); //la position d'un fichier pris par le robot est la position du robot 
                }else{
                    System.err.println("Fatal error: FILE ID NOT FOUND");
                }
            }else{
                System.err.println("Fatal error: FILE ID NOT FOUND");
            }
        }
    }

    public void drop(){
        if(!this.hasAFile()){
            System.err.println("Fatal error: NO FILE IS HELD");
        }else{
            //intervention de Abdulkarim pour drop le fichier sur un champ libre au hasard dans la currentSalle
        }
    }

    public void test(String a, String operation, String b){
        int valueA, valueB;
        switch(a){
            case "F":
                valueA = getRegistreF();
                break;
            case "M":
                valueA = getRegistreM();
                break;
            case "T":
                valueA = getRegistreT();
                break;
            case "X":
                valueA = getRegistreX();
                break;
            default :
                valueA = Integer.parseInt(a);
                break;
        }
        switch(b){
            case "F":
                valueB = getRegistreF();
                break;
            case "M":
                valueB = getRegistreM();
                break;
            case "T":
                valueB = getRegistreT();
                break;
            case "X":
                valueB = getRegistreX();
                break;
            default :
                valueB = Integer.parseInt(b);
                break;
        }
        switch (operation) {
            case "=":
                if(valueA == valueB){
                    setRegistreT(1);
                }else{
                    setRegistreT(0);
                }
                break;
            case ">":
                if(valueA < valueB){
                    setRegistreT(1);
                }else{
                    setRegistreT(0);
                }
                break;
                case "<":
                if(valueA < valueB){
                    setRegistreT(1);
                }else{
                    setRegistreT(0);
                }
                break;
        
            default:
                break;
        }
    }

    public void kill(){
        setEstMort(true);
        getCurrentSalle().libererChamp(position); //faut liberer l'espace d'un robot qui est mort
    }

    public void mode(){
        //a implmenter par abdoulkarim
    }

    public void test_mrd(){
        // a implenter par abdulkarim
    }

    public void void_m(){
        //abdoulaye
    }

    public void seek(String n){
        //abdoulaye
    }

    // crée une fichier de type arrayList
    public void make(){
        this.file = new leFichier(1000, new Coordonnees(3, 3, getCurrentSalle()), 2);
    }

    //crée un fochier de type file
    public void make_fifo(){
        this.file = new leFichier(500,new Coordonnees(4, 3, getCurrentSalle()) , 1);
    }

    //crée un fichier de type pile
    public void make_lifo(){
        this.file = new leFichier(700,new Coordonnees(4, 4, getCurrentSalle()) , 0);
    }


    public void wipe(){
        if(!hasAFile()){
            System.err.println("Fatal error: NO FILE IS HELD");
        }else{
            this.file.libererChamp();// un fichier supprimé est un fichier qui n'est pas sur la salle
            this.file = null;
        }
    }

    public void void_f(){
        //abdulaye
    }

    //ca tue le robot et drop le fichier tenu par le rebot sur la meme place
    public void halt(){
        if(this.hasAFile()){
            this.file.setPosition(this.position); //le fichier va etre deposer sur la meme position que le robot
            setEstMort(true); //le robot meurt
        }else{
            setEstMort(estMort);
            getCurrentSalle().libererChamp(position);
        }
    }

    //permet d'executer l'instruction d'indice i
    public void executeInstruction(){
        if(index < 0 && index >= this.lesInstructions.size()){
            System.err.println("l'indice des instruction est en dehors de l'intervalle");
        }else{
            String[] arguments = lesInstructions.get(index).getArguments();
            switch (lesInstructions.get(index).getInstructionType()) {
                case COPY:
                    if(arguments.length == 2){
                        this.copy(arguments[0],arguments[1]);
                        index++;
                    }else{
                        System.err.println("nombre d'arguments invalable");
                    }
                    break;
                case ADDI:
                    if(arguments.length == 3){
                        this.add(arguments[0], arguments[1], arguments[2]);
                        index++;
                    }else{
                        System.err.println("nombre d'arguments invalable");
                    }
                    break;
                case MULI:
                    if(arguments.length == 3){
                        this.multiply(arguments[0], arguments[1], arguments[2]);
                        index++;
                    }else{
                        System.err.println("nombre d'arguments invalable");
                    }
                    break;
                case SUBI:
                    if(arguments.length == 3){
                        this.substract(arguments[0], arguments[1], arguments[2]);
                        index++;
                    }else{
                        System.err.println("nombre d'arguments invalable");
                    }
                    break;
                case DIVI:
                    if(arguments.length == 3){
                        this.divide(arguments[0], arguments[1], arguments[2]);
                        index++;
                    }else{
                        System.err.println("nombre d'arguments invalable");
                    }
                    break;
                case MODI:
                    if(arguments.length == 3){
                        this.modulo(arguments[0], arguments[1], arguments[2]);
                        index++;
                    }else{
                        System.err.println("nombre d'arguments invalable");
                    }
                    break;
                case SWIZ:
                    if(arguments.length == 3){
                        this.swizzle(arguments[0], arguments[1], arguments[2]);
                        index++;
                    }else{
                        System.err.println("nombre d'arguments invalable");
                    }
                    break;
                case JUMP:
                    if(arguments.length == 1){
                        this.jump(arguments[0]);
                        //seule instruction ou ya pas de index++ vu que c'est inclus dans la methode(voir jump)
                    }else{
                        System.err.println("nombre d'arguments invalable");
                    }
                    break;
                case FJMP:
                    if(arguments.length == 1){
                        this.conditionalJump(arguments[0]);
                        //seule instruction ou ya pas de index++ vu que c'est inclus dans la  methode(voir jump)
                    }else{
                        System.err.println("nombre d'arguments invalable");
                    }
                    break;
                case LINK:
                    if(arguments.length == 1){
                        this.link(arguments[0]);
                        index++;
                    }else{
                        System.err.println("nombre d'arguments invalable");
                    }
                    break;
                    case TEST_EOF:
                    if(arguments.length == 0){
                        this.testEndOfFile();
                        index++;
                    }else{
                        System.err.println("nombre d'arguments invalable");
                    }
                    break;
                case GRAB:
                    if(arguments.length == 1){
                        this.grab(arguments[0]);
                        index++;
                    }else{
                        System.err.println("nombre d'arguments invalable");
                    }
                    break;
                case DROP:
                    if(arguments.length == 1){
                        this.drop();
                        index++;
                    }else{
                        System.err.println("nombre d'arguments invalable");
                    }
                    break;
                case NOOP:
                    if(arguments.length == 0){
                        index++;
                    }else{
                        System.err.println("nombre d'arguments invalable");
                    }
                    break;
                case TEST:
                    if(arguments.length == 3){
                        this.test(arguments[0], arguments[1], arguments[2]);
                        index++;
                    }else{
                        System.err.println("nombre d'arguments invalable");
                    }
                    break;
                case KILL:
                    if(arguments.length == 0){
                        this.kill();
                        index++;
                    }else{
                        System.err.println("nombre d'arguments invalable");
                    }
                    break;
                case MODE:
                    if(arguments.length == 0){
                        this.mode();
                        index++;
                    }else{
                        System.err.println("nombre d'arguments invalable");
                    }
                    break;
                case TEST_MRD:
                    if(arguments.length == 0){
                        this.test_mrd();
                        index++;
                    }else{
                        System.err.println("nombre d'arguments invalable");
                    }
                    break;
                case VOID_M:
                    if(arguments.length == 0){
                        this.void_m();
                        index++;
                    }else{
                        System.err.println("nombre d'arguments invalable");
                    }
                    break;
                case VOID_F:
                    if(arguments.length == 0){
                        this.void_f();
                        index++;
                    }else{
                        System.err.println("nombre d'arguments invalable");
                    }
                    break;
                case SEEK:
                    if(arguments.length == 1){
                        this.seek(arguments[0]);
                        index++;
                    }else{
                        System.err.println("nombre d'arguments invalable");
                    }
                    break;
                case MAKE:
                    if(arguments.length == 0){
                        this.make();
                        index++;
                    }else{
                        System.err.println("nombre d'arguments invalable");
                    }
                    break;
                case MAKEFIFO:
                    if(arguments.length == 0){
                        this.make_fifo();
                        index++;
                    }else{
                        System.err.println("nombre d'arguments invalable");
                    }
                    break;
                case MAKELIFO:
                    if(arguments.length == 0){
                        this.make_lifo();
                        index++;
                    }else{
                        System.err.println("nombre d'arguments invalable");
                    }
                    break;
                case WIPE:
                    if(arguments.length == 0){
                        this.wipe();
                        index++;
                    }else{
                        System.err.println("nombre d'arguments invalable");
                    }
                    break;
                default:
                    System.err.println("type d'instruction invalide");
                    break;
            }
        }

    }
    //methode pour executer toute les instruction a la fois 
    public void executeAllInstruction(){
        while(index < lesInstructions.size()){
            executeInstruction();
        }
    }
}
