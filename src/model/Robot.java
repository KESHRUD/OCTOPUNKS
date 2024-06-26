package src.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import src.files.InstructionFichier;
import src.view.Jeu;
import src.view.ZoneMonde;

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
    public static int idFichier;

    private Jeu jeu;

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
        instruction = new InstructionFichier<Integer>();
        idFichier = 400;
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

    //JUMP 
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

    /**
     * Permet de définir la classe Jeu (src.view) sans avoir à modifier le constructeur.
     * @param jeu la classe jeu à affecter
     */
    public void defineJeu(Jeu jeu)
    {
        if(jeu == null)
        {
            throw new NullPointerException("jeu est null.");
        }
        this.jeu = jeu;
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

    public void drop() throws IOException {
        if (!this.hasAFile()) {
            throw new RuntimeException("Fatal error: NO FILE IS HELD");
        } else {
            instruction.drop();
            
            // Recherche d'une position libre dans la salle
            int x = 1;
            int y = 1;
    
            while (getCurrentSalle().estOccupe(new Coordonnees(x, y, getCurrentSalle()))) {
                // Incrémentation des coordonnées X et réinitialisation si nécessaire
                x++;
                if (x > 5) {
                    x = 1;
                    y++;
                    // Sortie si aucune position libre n'est trouvée
                    if (y > 5) {
                        throw new RuntimeException("There is no free case in this Salle");
                    }
                }
            }
    
            // Définition de la position du fichier à la première position libre trouvée
            this.getFile().setPosition(new Coordonnees(x, y, getCurrentSalle()));
            this.getFile().fichierLabel.setSize(30,30);

            int xGraphique = 0;
            int yGraphique = 0;

            switch(getCurrentSalle().getId())
            {
                case 1 :    xGraphique = jeu.getZoneMonde().getCoordonneesGraphiquesSalle1(x-1, y-1).getXGraphique();
                            yGraphique = jeu.getZoneMonde().getCoordonneesGraphiquesSalle1(x-1, y-1).getYGraphique();
                            System.out.println("x : "+x+" y : "+y+"xg : "+xGraphique+" yg : "+yGraphique);
                            this.getFile().fichierLabel.setLocation(xGraphique, yGraphique);
                            break;
                case 2 :    getFile().afficherGraphique(2);
                            break;
                case 3 :    getFile().afficherGraphique(3);
                            break;
            }
            
            
            System.out.println("Fichier : x = "+getFile().getPosition().getX());
            System.out.println("Fichier : y = "+getFile().getPosition().getY());
            System.out.println("Fichier : salle = "+getFile().getPosition().getSalle().getId());           
        }
    }
    
    public boolean EstMort() {
        return this.estMort;
    }
    
    //methode pour executer toute les instruction a la fois 
    public void executeAllInstruction() throws IOException{
        while(index < lesInstructions.size()){
            executeInstruction();
        }
    }

    //permet d'executer l'instruction d'indice i (le pas a pas)
    public void executeInstruction() throws IOException{
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
                        throw new IllegalArgumentException("nombre d'arguments invalable");
                    }
                    break;
                case ADDI:
                    if(arguments.length == 3){
                        this.add(arguments[0], arguments[1], arguments[2]);
                        index++;
                    }else{
                        throw new IllegalArgumentException("nombre d'arguments invalable");
                    }
                    break;
                case MULI:
                    if(arguments.length == 3){
                        this.multiply(arguments[0], arguments[1], arguments[2]);
                        index++;
                    }else{
                        throw new IllegalArgumentException("nombre d'arguments invalable");
                    }
                    break;
                case SUBI:
                    if(arguments.length == 3){
                        this.substract(arguments[0], arguments[1], arguments[2]);
                        index++;
                    }else{
                        throw new IllegalArgumentException("nombre d'arguments invalable");
                    }
                    break;
                case DIVI:
                    if(arguments.length == 3){
                        this.divide(arguments[0], arguments[1], arguments[2]);
                        index++;
                    }else{
                        throw new IllegalArgumentException("nombre d'arguments invalable");
                    }
                    break;
                case MODI:
                    if(arguments.length == 3){
                        this.modulo(arguments[0], arguments[1], arguments[2]);
                        index++;
                    }else{
                        throw new IllegalArgumentException("nombre d'arguments invalable");
                    }
                    break;
                case SWIZ:
                    if(arguments.length == 3){
                        this.swizzle(arguments[0], arguments[1], arguments[2]);
                        index++;
                    }else{
                        throw new IllegalArgumentException("nombre d'arguments invalable");
                    }
                    break;
                case JUMP:
                    if(arguments.length == 1){
                        this.jump(arguments[0]);
                        //seule instruction ou ya pas de index++ vu que c'est inclus dans la methode(voir jump)
                    }else{
                        throw new IllegalArgumentException("nombre d'arguments invalable");
                    }
                    break;
                case FJMP:
                    if(arguments.length == 1){
                        this.conditionalJump(arguments[0]);
                        //seule instruction ou ya pas de index++ vu que c'est inclus dans la  methode(voir jump)
                    }else{
                        throw new IllegalArgumentException("nombre d'arguments invalable");
                    }
                    break;
                case LINK:
                    if(arguments.length == 1){
                        this.link(arguments[0]);
                        index++;
                    }else{
                        throw new IllegalArgumentException("nombre d'arguments invalable");
                    }
                    break;
                    case TEST_EOF:
                    if(arguments.length == 0){
                        this.testEndOfFile();
                        index++;
                    }else{
                        throw new IllegalArgumentException("nombre d'arguments invalable");
                    }
                    break;
                case GRAB:
                    if(arguments.length == 1){
                        this.grab(arguments[0]);
                        index++;
                    }else{
                        throw new IllegalArgumentException("nombre d'arguments invalable");
                    }
                    break;
                case DROP:
                    if(arguments.length == 1){
                        this.drop();
                        index++;
                    }else{
                        throw new IllegalArgumentException("nombre d'arguments invalable");
                    }
                    break;
                case NOOP:
                    if(arguments.length == 0){
                        index++;
                    }else{
                        throw new IllegalArgumentException("nombre d'arguments invalable");
                    }
                    break;
                case TEST:
                    if(arguments.length == 3){
                        this.test(arguments[0], arguments[1], arguments[2]);
                        index++;
                    }else{
                        throw new IllegalArgumentException("nombre d'arguments invalable");
                    }
                    break;
                case KILL:
                    if(arguments.length == 0){
                        this.kill();
                        index++;
                    }else{
                        throw new IllegalArgumentException("nombre d'arguments invalable");
                    }
                    break;
                case VOID_F:
                    if(arguments.length == 0){
                        this.void_f();
                        index++;
                    }else{
                        throw new IllegalArgumentException("nombre d'arguments invalable");
                    }
                    break;
                case SEEK:
                    if(arguments.length == 1){
                        this.seek(arguments[0]);
                        index++;
                    }else{
                        throw new IllegalArgumentException("nombre d'arguments invalable");
                    }
                    break;
                case MAKE:
                    if(arguments.length == 0){
                        this.makeFichier("MAKE");
                        index++;
                    }else{
                        throw new IllegalArgumentException("nombre d'arguments invalable");
                    }
                    break;
                case MAKEFIFO:
                    if(arguments.length == 0){
                        this.makeFichier("MAKEFIFO");
                        index++;
                    }else{
                        throw new IllegalArgumentException("nombre d'arguments invalable");
                    }
                    break;
                case MAKELIFO:
                    if(arguments.length == 0){
                        this.makeFichier("MAKELIFO");
                        index++;
                    }else{
                        throw new IllegalArgumentException("nombre d'arguments invalable");
                    }
                    break;
                case WIPE:
                    if(arguments.length == 0){
                        this.wipe();
                        index++;
                    }else{
                        throw new IllegalArgumentException("nombre d'arguments invalable");
                    }
                    break;
                case HALT:
                    this.halt();
                    break;
                default:
                throw new IllegalArgumentException("nombre d'arguments invalable");
            }
        }

    }
    
    public Salle getCurrentSalle() {
        return this.position.getSalle();
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

    /**
     * @return l'index dans les instructions du robot
     */
    public int getIndex()
    {
        return this.index;
    }

    public InstructionFichier<Integer>getInstruction()
    {
        if(this.instruction == null)
        {
            throw new NullPointerException("instruction est null");
        }
        return this.instruction;
    }

    /**
     * @return la liste d'instructions du robot
     */
    public List<Instruction> getLesInstructions() {
        return this.lesInstructions;
    }

    /**
     * @return la position du robot
     */
    public Coordonnees getPosition() {
        return this.position;
    }

    /**
     * @return la position x du robot
     */
    public int getPositionX() {
        return this.position.getX();
    }

    /**
     * @return la position y du robot
     */
    public int getPositionY() {
        return this.position.getY();
    }

    /**
     * @return le registre F du robot
     */
    public int getRegistreF() {
        return this.registreF;
    }

    /**
     * @return le registre M du robot
     */
    public int getRegistreM() {
        return this.registreM;
    }

    /**
     * @return le registre T du robot
     */
    public int getRegistreT() {
        return this.registreT;
    }

    /**
     * @return le registre X du robot
     */
    public int getRegistreX() {
        return this.registreX;
    }

    /**
     * @return le label contenant l'image du robot
     */
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
            for(leFichier unFichier: getCurrentSalle().getSalleFiles()){
                while(this.file == null){
                    if(idFile == unFichier.getId()){
                        this.file = unFichier; //on grab le fichier
                        instruction.grab(this.file.getFichier());
                        unFichier.enleverGraphique();
                        unFichier.setPosition(this.position);
                    }
                }
            }
            if(this.file == null){
                throw new NullPointerException("Fatal error : File Id not found");
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
            throw new IllegalArgumentException("nombre d'instructions a sauter invalide");
        }else{
            index = index + n;
        }
    }

    /**
     * Tue le robot.
     */
    public void kill(){
        setEstMort(true);
        getCurrentSalle().libererChamp(position); //faut liberer l'espace d'un robot qui est mort
    }

    public void link(String a) {
        int valueA = Integer.parseInt(a);
        getCurrentSalle().occuperChamp(position, TypeCellule.VIDE);
        if (valueA == -1) {
            if (getCurrentSalle().getLienEntrant() != null) {
                getCurrentSalle().libererChamp(position);
                setCurrentSalle(getCurrentSalle().getLienEntrant().getSalleAvant());
                setPositionX(1);
                setPositionY(1);
                
                // Utilisation d'une boucle do-while pour garantir au moins une itération
                do {
                    while (getCurrentSalle().estOccupe(position)) {
                        // Incrémentation de la position X
                        setPositionX(getPositionX() + 1);
                        
                        // Vérification des limites et réinitialisation si nécessaire
                        if (getPositionX() > 5) {
                            setPositionX(1);
                            setPositionY(getPositionY() + 1);
                            
                            // Vérification des limites et sortie si nécessaire
                            if (getPositionY() > 5) {
                                setEstMort(true);
                                System.err.println("There is no free case in this Salle");
                                return;
                            }
                        }
                    }
                } while (getCurrentSalle().estOccupe(position));
                
                // Une fois qu'une case libre est trouvée, occuper cette case
                getCurrentSalle().occuperChamp(position, TypeCellule.EXA1);
            }
        } else {
            for (Link unLien : getCurrentSalle().getLiensSortant()) {
                if (unLien.getId() == valueA) {
                    getCurrentSalle().libererChamp(position);
                    setCurrentSalle(unLien.getSalleApres());
                    setPositionX(1);
                    setPositionY(1);
    
                    // Utilisation d'une boucle do-while pour garantir au moins une itération
                    do {
                        while (getCurrentSalle().estOccupe(position)) {
                            // Incrémentation de la position X
                            setPositionX(getPositionX() + 1);
    
                            // Vérification des limites et réinitialisation si nécessaire
                            if (getPositionX() > 5) {
                                setPositionX(1);
                                setPositionY(getPositionY() + 1);
    
                                // Vérification des limites et sortie si nécessaire
                                if (getPositionY() > 5) {
                                    setEstMort(true);
                                    System.err.println("There is no free case in this Salle");
                                    return;
                                }
                            }
                        }
                    } while (getCurrentSalle().estOccupe(position));
                    
                    // Une fois qu'une case libre est trouvée, occuper cette case
                    getCurrentSalle().occuperChamp(position, TypeCellule.EXA1);
                    return;
                }
            }
            setEstMort(true);
            System.err.println("lien pas trouvé");
        }
    }

    public leFichier makeFichier(String cmd) throws IOException {
        leFichier nvFichier = null;
    
        int x = 1;
        int y = 1;
    
        while (getCurrentSalle().estOccupe(new Coordonnees(x, y, getCurrentSalle()))) {
            // Incrémentation des coordonnées X et réinitialisation si nécessaire
            x++;
            if (x > 5) {
                x = 1;
                y++;
                // Sortie si aucune position libre n'est trouvée
                if (y > 5) {
                    System.err.println("There is no free case in this Salle");
                    return null; 
                }
            }
        }
    
        // Utilisation des coordonnées trouvées pour créer le fichier
        nvFichier = new leFichier(idFichier, new Coordonnees(x, y, getCurrentSalle()));
        this.getCurrentSalle().getSalleFiles().add(nvFichier);
        nvFichier.setFichier(instruction.make(cmd));
        idFichier += 200;
    
        return nvFichier;
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


    

    public void moveGraphique(ZoneMonde zoneMonde)
    {
        switch(this.getCurrentSalle().getId())
        {
            case 1 :    setPositionLabel(zoneMonde.getCoordonneesGraphiquesSalle1(this.getPositionX()-1, this.getPositionY()-1));
                        break;
            case 2 :    setPositionLabel(zoneMonde.getCoordonneesGraphiquesSalle2(this.getPositionX()-1, this.getPositionY()-1));
                        break;
            case 3 :    setPositionLabel(zoneMonde.getCoordonneesGraphiquesSalle3(this.getPositionX()-1, this.getPositionY()-1));
                        break;
            default :   System.err.println("Salle non trouvée.");
                        break;  
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
    
    public void setIndexToUn()
    {
        this.index = 1;
    }

    public void setImage(String pathname) throws IOException
    {
        this.imageRobot = new ImageIcon(ImageIO.read(new File(pathname)));
        this.robotLabel.setIcon(this.imageRobot);
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
     * Si la valeur passée en paramètre est > 5,
     * on modifie à 5. 
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

    private  int getDigit(int number, int position) {
        // Récupérer le chiffre à la position spécifiée
        int divisor = (int) Math.pow(10, position);
        return (number / divisor) % 10;
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

    
    public void void_f(){
        instruction.void_f();
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

    
