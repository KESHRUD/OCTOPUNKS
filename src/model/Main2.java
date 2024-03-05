package src.model;

import java.io.IOException;

public class Main2 {
    static Salle salle1;
    static Salle salle2;
    static Salle salle3;

    public static void main(String[] args) throws IOException {
        salle1 = new Salle(1, null);
        salle2 = new Salle(2, null);
        salle3 = new Salle(3, null);

        salle1.ajouterLienSortant(new Link(400, salle1, salle2, true));
        salle2.setLienEntrant( salle1.getLiensSortant().get(0));

        salle2.ajouterLienSortant(new Link(800, salle2, salle3, true));
        salle3.setLienEntrant(salle2.getLiensSortant().get(0));

        Robot exa = new Robot(new Coordonnees(1, 1, salle1));
        leFichier file = new leFichier(200, new Coordonnees(1, 2, salle2), 0);

        System.out.println("exa1  x = " + exa.getPositionX() + " y = " + exa.getPositionY() + " salle : " + exa.getCurrentSalle().getId());
        System.out.println("200  x = " + file.getPosition().getX() + " y = " + file.getPosition().getY() + " salle : " + file.getPosition().getSalle().getId());
        
        exa.link("400");
        System.out.println(" link 400 : exa1  x = " + exa.getPositionX() + " y = " + exa.getPositionY() + " salle : " + exa.getCurrentSalle().getId());
        
        /*exa.link("800");
        System.out.println(" link 800 : exa1  x = " + exa.getPositionX() + " y = " + exa.getPositionY() + " salle : " + exa.getCurrentSalle().getId());
        
        exa.link("-1");
        System.out.println(" link -1 : exa1  x = " + exa.getPositionX() + " y = " + exa.getPositionY() + " salle : " + exa.getCurrentSalle().getId());
        */
        exa.getCurrentSalle().setTheFile(file);
        exa.grab("200");
        System.out.println("grab 200  x = "+ file.getPosition().getX() + " y = " + file.getPosition().getY() + " salle : " + file.getPosition().getSalle().getId());
        
        exa.link("800");
        System.out.println(" link 800 : exa1  x = " + exa.getPositionX() + " y = " + exa.getPositionY() + " salle : " + exa.getCurrentSalle().getId());
        System.out.println("grab 200  x = "+ file.getPosition().getX() + " y = " + file.getPosition().getY() + " salle : " + file.getPosition().getSalle().getId());
        
        exa.drop();
        System.out.println(" exa1  x = " + exa.getPositionX() + " y = " + exa.getPositionY() + " salle : " + exa.getCurrentSalle().getId());
        System.out.println(" 200  x = "+ file.getPosition().getX() + " y = " + file.getPosition().getY() + " salle : " + file.getPosition().getSalle().getId());
       
       
    }
}
