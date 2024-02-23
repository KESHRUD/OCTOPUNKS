package src.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Salle {
    private List<leFichier> contenu2;
    private Map<Coordonnees, Champ> contenu; // Représentation de ce qui se trouve dans la salle
    private Map<Integer, String> portes; // Représentation des portes numérotées
    private List<Link> liensSortant; //entre 0 et 3 liens Sortant pour chaque salle
    private Link lienEntrant; //1 lien entrant pour chaque Salle sauf pour la premiere
    private leFichier theFile; //le fichier present dans cette salle
    
    // Constructeur
    public Salle(Link lienEntrant) {
        this.contenu2 = new ArrayList<>();
        this.contenu = new HashMap<>();
        initialiserContenu();
        this.portes = new HashMap<>();
        liensSortant = new ArrayList<>(3) ;
        this.lienEntrant = lienEntrant;
        theFile = null;
    }
    public List<leFichier> getContenu2() {
        return contenu2;
    }

    public void setContenu2(List<leFichier> contenu2) {
        this.contenu2 = contenu2;
    }

    private void initialiserContenu() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                contenu.put(new Coordonnees(i, j, this), new Champ(TypeCellule.VIDE));
            }
        }
    }

    // Méthode pour ajouter une porte
    public void ajouterPorte(int numeroPorte, String destination) {
        portes.put(numeroPorte, destination);
    }

    // Méthode pour vérifier si un champ est occupé
    public boolean estOccupe(Coordonnees coordonnees) {
        return contenu.get(coordonnees).getType() != TypeCellule.VIDE;
    }

    // Méthode pour occuper un champ avec un type de cellule donné
    public void occuperChamp(Coordonnees coordonnees, TypeCellule typeCellule) {
        contenu.get(coordonnees).setType(typeCellule);
    }

    // Méthode pour libérer un champ
    public void libererChamp(Coordonnees coordonnees) {
        contenu.get(coordonnees).setType(TypeCellule.VIDE);
    }

    // Getters et setters
    public Map<Coordonnees, Champ> getContenu() {
        return contenu;
    }

    public void setContenu(Map<Coordonnees, Champ> contenu) {
        this.contenu = contenu;
    }

    public Map<Integer, String> getPortes() {
        return portes;
    }

    public void setPortes(Map<Integer, String> portes) {
        this.portes = portes;
    }

    //methode pour gerer les liens
    public Link getLienEntrant() {
        return lienEntrant;
    }
    
    public void setLienEntrant(Link lienEntrant) {
        this.lienEntrant = lienEntrant;
    }

    public void ajouterLienSortant(Link lien){
        this.liensSortant.add(lien);
    }

    public List<Link> getLiensSortant() {
        return liensSortant;
    }

    public void setLiensSortant(List<Link> liensSortant) {
        this.liensSortant = liensSortant;
    }
    

    public leFichier getTheFile() {
        return theFile;
    }

    public void setTheFile(leFichier theFile) {
        this.theFile = theFile;
    }
    
}
