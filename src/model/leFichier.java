package src.model;
import java.util.Objects;

import src.files.File;
import src.files.Pile;
import src.files.TableauDynamique;
import src.files.TypeFichier;

public class leFichier
{
    private int id; //200 par exemple dans le premier niveau de exapunks
    private TypeFichier<Integer> fichier; //le type de fichier
    private Coordonnees position; //le fichier aussi a une position


    /**
     * CONSTRUCTEUR
     * @param id l'identifiant du fichier
     * @param laPosition la position du fichier
     * @param type le type du fichier
     */
   public leFichier(int id, Coordonnees laPosition) {
        if(id != 0) {
            this.id = id;
        }
        else {
            throw new IllegalArgumentException("file id is null");
        }
        this.position = laPosition;
        occuperChamp(laPosition);
       laPosition.getSalle().setTheFile(this);
    }

    /**
     * Renvoie le fichier.
     * @return la position u fichier
     */
    public TypeFichier<Integer> getFichier() {
        return this.fichier;
    }
    
    /**
     * Renvoi l'identifiant du fichier.
     * @return l'identifiant du fichier
     */
    public int getId() {
        return id;
    }

    /**
     * Renvoie la position du fichier.
     * @return la position du fichier
     */
    public Coordonnees getPosition() {
        return this.position;
    }

    /**
     * Permet de libérer
     */
    public void libererChamp(){
        position.getSalle().libererChamp(position);
    }

    public void occuperChamp(Coordonnees laPosition){
        laPosition.getSalle().occuperChamp(laPosition, TypeCellule.FICHIER);
    }

    /**
     * Permet d'affecter un fichier spécifié.
     * @param fichier le fichier à affecter.
     */
    public void setFichier(TypeFichier<Integer> fichier) {
        this.fichier = fichier;
    }

    public void setFileOfSalle(){
        getPosition().getSalle().setTheFile(this);
    }

    /**
     * Permet d'affecter un nouvel identifiant.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Permet de modifier la position
     * @param newPosition la nouvelle position
     */
    public void setPosition(Coordonnees newPosition) {
        this.position.getSalle().libererChamp(this.position); //liberer l'ancienne position
        this.position = newPosition;
        occuperChamp(newPosition); //occuper la novelle position
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
