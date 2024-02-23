package src.model;

public class leFichier {
    private int id; //200 par exemple dans le premier niveau de exapunks
    private Fichier fichier; //le type de fichier
    private Coordonnees position; //le fichier aussi a une position


    // Constructeur
    public leFichier(int id, Coordonnees laPosition, int type ){ //0 pour pile 1 pour file 2 pour arrayList
        if(type == 0){
            fichier = new Pile();
        }else if(type == 1){
            fichier = new File();
        }else if (type == 2){
            fichier = new TableauDynamique();
        }else{
            System.err.println("type de fichier inconnu");
        }
        this.id = id;
        position = laPosition;
        occuperChamp(laPosition);
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
    public void setPosition(Coordonnees newPosition) {
        this.position.getSalle().libererChamp(this.position);//liberer l'ancienne position
        this.position = newPosition;
        occuperChamp(newPosition);//occuper la novelle position
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

    public void occuperChamp(Coordonnees laPosition){
        laPosition.getSalle().occuperChamp(laPosition, TypeCellule.FICHIER);
    }
}
