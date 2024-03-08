package src.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Salle
{
    private int id;

    private Map<Coordonnees, Champ> contenu; // Représentation de ce qui se trouve dans la salle
    private Map<Integer, String> portes; // Représentation des portes numérotées
    private List<Link> liensSortant; //entre 0 et 3 liens Sortant pour chaque salle
    private Link lienEntrant; //1 lien entrant pour chaque Salle sauf pour la premiere
    private leFichier theFile; //le fichier present dans cette salle
    
    /**
     * CONSTRUCTEUR
     * @param id l'identifiant de la salle.
     * @param lienEntrant un lien entrant.
     */
    public Salle(int id,Link lienEntrant) {
        this.id = id;
        this.contenu = new HashMap<>();
        initialiserContenu();
        this.portes = new HashMap<>();
        this.liensSortant = new ArrayList<>(3) ;
        this.lienEntrant = lienEntrant;
        this.theFile = null;
    }

    /**
     * CONSTRUCTEUR
     * @param id l'identifiant de la salle.
     */
    public Salle(int id){
        this.id = id;
        this.contenu = new HashMap<>();
        initialiserContenu();
        this.portes = new HashMap<>();
        liensSortant = new ArrayList<>(3) ;
        this.lienEntrant = null;
        theFile = null;
    }

    /**
     * Permet d'ajouter un lien sortant
     * @param lien le lien sortant à ajouter
     */
    public void ajouterLienSortant(Link lien) {
        this.liensSortant.add(lien);
    }

    /**
     * Permet d'ajouter une porte
     * @param numeroPorte le numéro de la porte
     * @param destination la destination de la porte
     */
    public void ajouterPorte(int numeroPorte, String destination) {
        this.portes.put(numeroPorte, destination);
    }

    /**
     * Permet de vérifier si un champ est occupé
     * @param coordonnees les coordonnées du champ à tester
     * @return true si le champ est occupé, false sinon
     */
    public boolean estOccupe(Coordonnees coordonnees) {
        return contenu.get(coordonnees).getType() != TypeCellule.VIDE;
    }

    /**
     * @return le contenu de la salle
     */
    public Map<Coordonnees, Champ> getContenu() {
        return this.contenu;
    }

    /**
     * @return l'identifiant de la salle
     */
    public int getId() {
        return this.id;
    }

    /**
     * @return le lien entrant de la salle
     */
    public Link getLienEntrant() {
        return this.lienEntrant;
    }

    /**
     * @return les liens sortants de la salle
     */
    public List<Link> getLiensSortant() {
        return this.liensSortant;
    }

    /**
     * @return les portes de la salle
     */
    public Map<Integer, String> getPortes() {
        return this.portes;
    }

    /**
     * @return le fichier présent dans la salle
     */
    public leFichier getTheFile() {
        return this.theFile;
    }
 
    /**
     * Permet d'initialiser le contenu de la salle
     */
    private void initialiserContenu() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                contenu.put(new Coordonnees(i, j, this), new Champ(TypeCellule.VIDE ));
            }
        }
    }

    /**
     * Permet de libérer le champ aux coordonnées spécifiées
     * @param coordonnees les cooedonnées du champ à libérer
     */
    public void libererChamp(Coordonnees coordonnees) {
        contenu.get(coordonnees).setType(TypeCellule.VIDE);
    }

    /**
     * Permet d'occuper le champ aux coordonnées spécifiées avec un type de cellules donné
     * @param coordonnees les cooedonnées du champ à occuper
     * @param typeCellule le type de la cellule
     */
    public void occuperChamp(Coordonnees coordonnees, TypeCellule typeCellule) {
        contenu.get(coordonnees).setType(typeCellule);
    }

    /**
     * Permet de modifier le contenu de la salle
     * @param contenu le nouveau contenu de la salle
     */
    public void setContenu(Map<Coordonnees, Champ> contenu) {
        this.contenu = contenu;
    }

    /**
     * Permet de modifier un lien entrant
     * @param lien le nouveau lien entrant
     */
    public void setLienEntrant(Link lien) {
        this.lienEntrant = lien;
    }

    /**
     * Permet de modifier les liens sortants
     * @param liensSortant les liens sortants
     */
    public void setLiensSortant(List<Link> liensSortant) {
        this.liensSortant = liensSortant;
    }

    /**
     * Modifie la porte.
     * @param portes la nouvelle porte
     */
    public void setPortes(Map<Integer, String> portes) {
        this.portes = portes;
    }

    /**
     * Modifie le fichier présent dans la salle.
     * @param theFile le nouveau fichier
     */
    public void setTheFile(leFichier theFile) {
        this.theFile = theFile;
    }
}
