import fichiers.Fichier;
import fichiers.File;

public class leFichier {
    private int id; //200 par exemple dans le premier niveau de exapunks
    private Fichier fichier; //le type de fichier
    private Coordonnees position; //le fichier aussi a une position


    // Constructeur
    public leFichier(int id, Coordonnees laPosition){
        this.id = id;
        position = laPosition;
        fichier = randFile(); //a faire par abdoulkarim
    }
    
    //getter pour id
    public int getId() {
        return id;
    }
    
    //setter pour id
    public void setId(int id) {
        this.id = id;
    }
    
    //getter pour l etype de fichier
    public Fichier getFichier() {
        return fichier;
    }
    //setter pour le type de fichier
    public void setFichier(Fichier fichier) {
        this.fichier = fichier;
    }
    //getter pour les position
    public Coordonnees getPosition() {
        return position;
    }

    //setter pour la position
    public void setPosition(Coordonnees position) {
        this.position = position;
    }

    public Fichier randFile(){
        //cette methode doit etre implmenetr par abdoulkarim
        //de facon a ce que a chaque fois qu'on "MAKE" un fichier
        //le type de fichier est random 
        return new File(); //juste un exemple pour ne pas avoir d'erreur
    }

    public void libererChamp(){
        position.getSalle().libererChamp(position);
    }
}
