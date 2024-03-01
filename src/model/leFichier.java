package src.model;
import java.util.Objects;

import src.files.File;
import src.files.Pile;
import src.files.TableauDynamique;
import src.files.TypeFichier;

public class leFichier {
    private int id; //200 par exemple dans le premier niveau de exapunks
    private TypeFichier<Integer> fichier; //le type de fichier
    private Coordonnees position; //le fichier aussi a une position


    // Constructeur
    public leFichier(int id, Coordonnees laPosition, int type ){ //0 pour pile 1 pour file 2 pour arrayList
        if(type == 0){
            fichier = new Pile<Integer>();
        }else if(type == 1){
            fichier = new File<Integer>();
        }else if (type == 2){
            fichier = new TableauDynamique<Integer>();
        }else{
            System.err.println("type de fichier inconnu");
        }
        if(id != 0){
            this.id = id;
        }
        else{
            throw new IllegalArgumentException("file id is null");
        }
        position = laPosition;
        occuperChamp(laPosition);
    }
    public void setFileOfSalle(){
        getPosition().getSalle().setTheFile(this);
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
    public TypeFichier<Integer> getFichier() {
        return fichier;
    }
    //setter pour le type de fichier
    public void setFichier(TypeFichier<Integer> fichier) {
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


    public void libererChamp(){
        position.getSalle().libererChamp(position);
    }

    public void occuperChamp(Coordonnees laPosition){
        laPosition.getSalle().occuperChamp(laPosition, TypeCellule.FICHIER);
    }

    @Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof leFichier)) return false;
    leFichier leFichier = (leFichier) o;
    return id == leFichier.id && Objects.equals(fichier, leFichier.fichier) && Objects.equals(position, leFichier.position);
}

@Override
public int hashCode() {
    return Objects.hash(id, fichier, position);
}
}
