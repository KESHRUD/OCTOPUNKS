package src.model;

import src.files.InstructionFichier;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.imageio.ImageIO;

public class Robot
{
    private Coordonnees position; //position x y et salle
    private int registreX;//les 4 registres pour chaque robot
    private int registreT;
    private int registreF;//ptr de fichier contient l'id du fichier
    private int registreM;
    private leFichier file; //le fichier que le robot peut tenir
    private boolean estMort; //si le robot est mort ou pas (est sur la salle)
    private List<Instruction> lesInstructions; //liste des instructions que chaque robot va executer
    private int index; //pour naviguer dans la liste d'instruction
    private ImageIcon imageRobot; // l'image du robot
    protected JLabel robotLabel; // le label qui permet d'afficher l'image
    private InstructionFichier<Integer> instruction;

    /**
     * CONSTRUCTEUR
     * @param position la position du robot
     * @throws IOException si l'image u robot n'est pas lu.
     */
    public Robot(Coordonnees position) throws IOException {
        //file = null;
        this.estMort = false;
        this.registreF = 0; 
        this.registreM = 0;
        this.registreT = 0;
        this.registreX = 0;
        this.index = 0; //premiere instruction a éxecuter;
        this.position = position;
        this.position.getSalle().occuperChamp(position, TypeCellule.EXA1); //le robot doit occuper une salle
        this.lesInstructions = new ArrayList<>();

        this.imageRobot = new ImageIcon(ImageIO.read(new File("images/Exapunks_robot.png")));
        this.robotLabel = new JLabel(this.imageRobot);
        instruction = null;
    }

    
    
    //ADDI a(R/N) b(R/N) dest(R)
    public void add(String a, String b, String dest){
        int valueA, valueB;
        switch(a){
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
        setRegisterValue2(dest, sum);
    }

    //JUMP dest(L
    public void conditionalJump(String lines){
        if(registreT == 0){
            jump(lines);
        }else{
            index++;//sinon on passe a l'instruction suivante
        }
    }

    //COPY source(R/N) dest(R)
   public void copy(String src, String dest){
        int value;
        switch(src){
            case "F":
                value = instruction.copy1();
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

    public void divide (String a , String b, String dest){
        int valueA, valueB;
        switch(a){
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
        setRegisterValue2(dest, quotient);
    }

    public void drop(){
        if(!this.hasAFile()){
            System.err.println("Fatal error: NO FILE IS HELD");
        }else{
            //intervention de Abdulkarim pour drop le fichier sur un champ libre au hasard dans la currentSalle
            this.getFile().setPosition(new Coordonnees(this.getPositionX()+1, this.getPositionY() + 1, getCurrentSalle()));
             instruction = null;
        }
    }

    public boolean EstMort() {
        return this.estMort;
    }
    
    //methode pour executer toute les instruction a la fois 
    public void executeAllInstruction(){
        while(index < lesInstructions.size()){
            executeInstruction();
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
                        break;
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
                        this.makeFichier("MAKE");
                        index++;
                    }else{
                        System.err.println("nombre d'arguments invalable");
                    }
                    break;
                case MAKEFIFO:
                    if(arguments.length == 0){
                        this.makeFichier("MAKEFIFO");
                        index++;
                    }else{
                        System.err.println("nombre d'arguments invalable");
                    }
                    break;
                case MAKELIFO:
                    if(arguments.length == 0){
                        this.makeFichier("MAKELIFO");
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
                case HALT:
                    this.halt();
                    break;
                default:
                    System.err.println("type d'instruction invalide");
                    break;
            }
        }

    }
    
    public Salle getCurrentSalle() {
        return this.position.getSalle();
    }

    private  int getDigit(int number, int position) {
        // Récupérer le chiffre à la position spécifiée
        int divisor = (int) Math.pow(10, position);
        return (number / divisor) % 10;
    }

    public leFichier getFile(){
        return this.file;
    }

    public ImageIcon getImageIcon()
    {
        if(this.imageRobot == null) 
        {
            throw new NullPointerException("L'image du robot est null.");
        }
        return this.imageRobot;
    }

    public List<Instruction> getLesInstructions() {
        return this.lesInstructions;
    }

    public Coordonnees getPosition() {
        return this.position;
    }

    public int getPositionX() {
        return this.position.getX();
    }

    public int getPositionY() {
        return this.position.getY();
    }

    public int getRegistreF() {
        return this.registreF;
    }

    public int getRegistreM() {
        return this.registreM;
    }

    public int getRegistreT() {
        return this.registreT;
    }

    public int getRegistreX() {
        return this.registreX;
    }

    public JLabel getRobotLabel()
    {
        if(this.robotLabel == null) 
        {
            throw new NullPointerException("Le label du robot est null.");
        }
        return this.robotLabel;
    }

    public void grab(String theFile){
        if(this.hasAFile()){
            System.err.println("Fatal error: CANNOT GRAB A SECOND FILE");
        }else{
            int idFile = Integer.parseInt(theFile);
            /*for(leFichier unFichier: getCurrentSalle().getContenu2()){
                while(this.file == null){
                    if(idFile == unFichier.getId()){
                        this.file = unFichier; //on grab le fichier
                        unFichier.setPosition(this.position);
                    }
                }
            }
            if(this.file == null){
                throw new IllegalArgumentException("Fatal error : File Id not found");
            }*/
            if(getCurrentSalle().getTheFile() != null){
                if(idFile == getCurrentSalle().getTheFile().getId()){
                    this.file = getCurrentSalle().getTheFile();
                    this.file.setPosition(this.getPosition()); //la position d'un fichier pris par le robot est la position du robot 
                    instruction = new InstructionFichier<Integer>(file.getFichier());
                }else{
                    System.err.println("Fatal error1: FILE ID NOT FOUND");
                }
            }else{
                System.err.println("Fatal error2: FILE ID NOT FOUND");
            }
        }
    }
    
    /**
     * Tue le robot et drop le fichier tenu par le robot sur la même place
    */
    public void halt(){
        if(this.hasAFile()){
            this.file.setPosition(this.position); //le fichier va etre deposer sur la meme position que le robot
            setEstMort(true); //le robot meurt
        }else{
            setEstMort(estMort);
            getCurrentSalle().libererChamp(position);
        }
    }

    /**
     * Permet de vérifier si le robot tient un fichier
     * @return true si le robot tient un fichier, false sinon
    */
    public boolean hasAFile(){
        return (this.file != null);
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

    public void kill(){
        setEstMort(true);
        getCurrentSalle().libererChamp(position); //faut liberer l'espace d'un robot qui est mort
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
            System.err.println("lien pas trouvé");
        }
    }

    // crée une fichier de type arrayList
    public leFichier makeFichier(String cmd) {
        leFichier nvFichier = null;
        nvFichier =  new leFichier(1000, new Coordonnees(3, 3, getCurrentSalle())); /*Modifier la position du fichier*/
        nvFichier.setFichier(instruction.make(cmd));

        return nvFichier;
    }

    public void mode(){
        //a implmenter par abdoulkarim
    }

   public void modulo(String a , String b, String dest){
        int valueA, valueB;
        switch(a){
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
        setRegisterValue2(dest, mod);
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

    public void multiply(String a, String b, String dest){
        int valueA, valueB;
        switch(a){
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
        setRegisterValue2(dest, product);
    }

    public void seek(String n){
       instruction.seek(n);
    }

    public void setCurrentSalle(Salle currentSalle) {
        this.position.setCurrentSalle(currentSalle);
    }

    /**
     * Permet de modifier si le robot est mort ou non.
     * @param estMort l'état du robot (vivant = false, ou mort = true)
     */
    public void setEstMort(boolean estMort) {
        this.estMort = estMort;
     }
    
    /**
      * Permet de modifier le fichier du robot.
      * @param newFile le nouveau fichier
      */
    public void setFile(leFichier newFile){
        this.file = newFile;
        setRegistreM(newFile.getId());
    }
    
    /**
     * Permet de modifier les instructions du robot.
     * @param lesInstructions les nouvelles instructions
     */
    public void setLesInstructions(List<Instruction> lesInstructions) {
        this.lesInstructions = lesInstructions;
    }
    
    /**
     * Permet de modifier la position du robot.
     * @param position la noubvelle position du robot
     */
    public void setPosition(Coordonnees position) {
        this.position = position;
    }

    /**
     * Permet de modifier la position de l'image du robot (le label sur l'interface graphique).
     * @param xGraphique la nouvelle position graphique x de l'image
     * @param yGraphique la nouvelle position graphique y de l'image 
     */
    public void setPositionLabel(Coordonnees coordonnees)
    {
        if(coordonnees == null)
        {
            throw new NullPointerException("Les coordonnées sont null.");
        }
        this.robotLabel.setLocation(coordonnees.getXGraphique(), coordonnees.getYGraphique());
    }

    /**
     * Permet de modifier la position x du robot.
     * @param positionX la nouvelle position x du robot
     */
    public void setPositionX(int positionX) {
        this.position.setX(positionX);
    }

    /**
     * Permet de modifier la position y du robot.
     * @param positionY la nouvelle position y du robot
     */
    public void setPositionY(int positionY) {
        this.position.setY(positionY);
    }

    /**
     * Permet de modifier le registre F du robot.
     * @param registreF le nouveau registre F du robot
     */
    public void setRegistreF(int registreF) {
        this.registreF = registreF;
        instruction.setRegister(this.registreF);
        instruction.copy2();
    }

    /**
     * Permet de modifier le registre M du robot.
     * @param registreM le nouveau registre M du robot
     */
    public void setRegistreM(int registreM) {
        this.registreM = registreM;
    }

    /**
     * Permet de modifier le registre T du robot.
     * @param registreT le nouveau registre T du robot
     */
    public void setRegistreT(int registreT) {
        this.registreT = registreT;
    }

    /**
     * Permet de modifier le registre X du robot.
     * @param registreX le nouveau registre X du robot
     */
    public void setRegistreX(int registreX) {
        this.registreX = registreX;
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

    //version de setRegisterValue qui prend pas en charge le rigistre F pour les instructions arithmétiques
    public void setRegisterValue2(String registre , int value){
        switch(registre){
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

    // SUBI a(R/N) b(R/N) dest(R)
    public void substract(String a, String b, String dest){
        int valueA, valueB;
        switch(a){
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
        setRegisterValue2(dest, diff);
    }

    //SWIZ input(R/N) mask(R/N) dest(R)
    public  void swizzle(String input, String mask, String dest) {
        int inputValue = 0;

        switch (input) {
            case "X":
                inputValue = getRegistreX();
                break;
            case "T":
                inputValue = getRegistreT();
                break;
            case "M":
                inputValue = getRegistreM();
                break;
            default:
                try {
                    inputValue = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Erreur : input non valide.");
                    return;
                }
        }

        // Initialiser un tab pour stocker les chiffres de dest
        int[] destDigits = new int[String.valueOf(inputValue).length()];

        // Parcourir chaque chiffre du mask et maj le tabl
        int maskCopy = Integer.parseInt(mask);
        int index = destDigits.length - 1;

        while (maskCopy > 0) {
            int digitValue = maskCopy % 10;

            if (digitValue == 0 || (digitValue >= 5 && digitValue <= 9)) {
                destDigits[index] = 0;
            } else {
                int inputDigit = getDigit(inputValue, digitValue - 1); // -1 car l'index commence à 0
                destDigits[index] = inputDigit;
            }

            maskCopy /= 10;
            index--;
        }

        // Construire le résultat à partir du tableau destDigits
        int result = 0;
        for (int i = 0; i < destDigits.length; i++) {
            result = result * 10 + destDigits[i];
        }

        // Gérer le signe 
        if (mask.charAt(0) == '1') {
            result = Math.abs(result);
            if (inputValue < 0) {
                result = -result;
            }
        }

        // Mettre à jour la valeur dans le registre correspondant

        setRegisterValue2(dest, result);
    }

    public void test(String a, String operation, String b){
        int valueA, valueB;
        switch(a){
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

    /**
     * À COMPLÉTER
     */
    public void testEndOfFile(){
        if (instruction.test_eof()) {
            setRegistreT(1);
        }
        else {
            setRegistreT(0);
        }
    }

    /**
     * À COMPLÉTER
     */
    public void test_mrd(){
        // a implenter par abdulkarim
    }

    /**
     * À COMPLÉTER
     */
    public void void_f(){
        instruction.void_f();
    }

    /**
     * À COMPLÉTER
     */
    public void void_m(){
        //abdoulaye
    }

    public void wipe(){
        if(!hasAFile()){
            System.err.println("Fatal error: NO FILE IS HELD");
        }else{
            instruction.wipe();
            this.file.libererChamp();// un fichier supprimé est un fichier qui n'est pas sur la salle
            this.file = null;
        }
    }
}
