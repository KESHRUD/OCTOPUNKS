package src.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Salle
{
    private int id;
    private List<leFichier> SalleFiles;
    private Map<Coordonnees, Champ> contenu; // Représentation de ce qui se trouve dans la salle
    private List<Link> liensSortant; //entre 0 et 3 liens Sortant pour chaque salle
    private Link lienEntrant; //1 lien entrant pour chaque Salle sauf pour la premiere
    
    /**
     * CONSTRUCTEUR
     * @param id l'identifiant de la salle.
     * @param lienEntrant un lien entrant.
     */
    public Salle(int id,Link lienEntrant) {
        this.id = id;
        this.contenu = new HashMap<>();
        initialiserContenu();
        this.liensSortant = new ArrayList<>(3) ;
        this.lienEntrant = lienEntrant;
        this.SalleFiles = new ArrayList<>();
    }

    /**
     * CONSTRUCTEUR
     * @param id l'identifiant de la salle.
     */
    public Salle(int id){
        this.id = id;
        this.contenu = new HashMap<>();
        initialiserContenu();
        liensSortant = new ArrayList<>(3) ;
        this.lienEntrant = null;
    }

    /**
     * Permet d'ajouter un lien sortant
     * @param lien le lien sortant à ajouter
     */
    public void ajouterLienSortant(Link lien) {
        this.liensSortant.add(lien);
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
     * Modifie le fichier présent dans la salle.
     * @param theFile le nouveau fichier
     */
   
    public List<leFichier> getSalleFiles() {
        return SalleFiles;
    }

    public void setSalleFiles(List<leFichier> salleFiles) {
        SalleFiles = salleFiles;
    }

}
