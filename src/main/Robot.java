public class Robot {
    private Coordonnees position; //position x y et salle
    private int registreX;//les 4 registres pour chaque robot
    private int registreT;
    private int registreF;
    private int registreM;//ptr de fichier contient l'id du fichier
    private Fichier file; //le fichier que le robot peut tenir
    private boolean estMort; //si le robot est mort ou pas (est sur la salle)


    
    // Constructeur
    public Robot(Coordonnees position) {
        this.position = position;
        file = null;
        estMort = false;
    }
    
    //methode pour verifier si le robot tient un fichier
    public boolean hasAFile(){
        return (this.file != null);
    }
    
    // Méthode pour déplacer le robot en fonction des instructions
    public void move(int deltaX, int deltaY) {
        // Mise à jour de la position du robot
        position.setX(deltaX);
        position.setY(deltaY);
    }
    
    // Méthode pour vérifier si le robot a atteint la condition de victoire
    public boolean checkVictoryCondition(Monde monde, Robot autreRobot) {
        // Vérifier si les robots sont sur des cases opposées
        return (position.getX() == 0 && autreRobot.getPositionX() == 4 && position.getY() == autreRobot.getPositionY()) ||
        (position.getX() == 4 && autreRobot.getPositionX() == 0 && getPositionY() == autreRobot.getPositionY()) ||
        (position.getY() == 0 && autreRobot.getPositionY() == 4 && getPositionX() == autreRobot.getPositionX()) ||
        (getPositionY() == 4 && autreRobot.getPositionY() == 0 && getPositionX() == autreRobot.getPositionX());
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

    public Fichier getFile(){
        return file;
    }

    public void setFile(Fichier newFile){
        this.file = newFile;
        setRegistreM(newFile.getId());
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
        //a completer faut que je recupere d'abord une arraylist d'instruction
    }

    //JUMP dest(L
    public void conditionalJump(String lines){
        if(registreT == 0){
            jump(lines);
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

    public void drop(String theFile){
        int idFile = Integer.parseInt(theFile);
        if(!this.hasAFile() || idFile != this.file.getId()){
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

    public void mode(String m){
        //pas comprise je demande au prof
    }

    public void test_mrd(){
        // je ne peux pas l'implmenter avant la tache de abdoulaye
    }

    public void void_m(){
        //abdoulaye
    }

    public void seek(String n){
        //abdoulaye
    }

    public void make(){
        //abdoulkarim
    }

    public void wipe(){
        if(!hasAFile()){
            System.err.println("Fatal error: NO FILE IS HELD");
        }else{
            this.file.libererChamp();// un fichier supprimé est un fichier qui n'est pas sur la salle
        }
    }

    public void void_f(){
        //abdulaye
    }
}
