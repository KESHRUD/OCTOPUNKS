package levels;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import src.files.File;
import src.files.Pile;
import src.files.TableauDynamique;
import src.model.Champ;
import src.model.Coordonnees;
import src.model.Link;
import src.model.Robot;
import src.model.Salle;
import src.model.TypeCellule;
import src.model.leFichier;


public class Niveau {
    private Salle salle1;
    public Salle getSalle1() {
        return salle1;
    }

    private Salle salle2;
    public Salle getSalle2() {
        return salle2;
    }

    private Salle salle3;
    public Salle getSalle3() {
        return salle3;
    }

    private Robot robot1;
    private Robot robot2;
    private Pile<Integer> pile;
    private File<Integer> file;
    private TableauDynamique<Integer> tableauDynamique;
    private boolean victoire;

    public Niveau(String nomFichier) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) { 
            salle1 = new Salle(2,new Link(800, salle1, salle2, false)); // salle1 avec lien sortant I
            salle2 = new Salle(2,new Link(900, salle2, salle1, true)); // salle2 avec lien entrant 1 et lien sortant I
            salle3 = new Salle(2,new Link(1, salle3, salle2, false)); // salle3 avec lien entrant 1
            robot1 = new Robot(new Coordonnees(0, 0, salle1)); // robot1 dans salle1
            robot2 = new Robot(new Coordonnees(0, 1, salle1)); // robot2 dans salle1
            leFichier fichierTest = robot1.getFile();
            victoire = false;  // par défaut, il n'y a pas encore de victoire
            for (String line; (line = br.readLine()) != null; ) {
                String[] cells = line.split("");
                for (int i = 0; i < cells.length; i++) {
                    switch (cells[i]) {
                        case "E":
                            salle1.getContenu().put(new Coordonnees(i, 1, salle1), new Champ(TypeCellule.EXA1));
                            break;
                        case "F":
                            salle2.getContenu().put(new Coordonnees(i, 1, salle2), new Champ(TypeCellule.FICHIER));
                            break;
                        case "I":
                            salle3.getContenu().put(new Coordonnees(i, 1, salle3), new Champ(TypeCellule.ENTREE));
                            break;
                        case "1":
                            salle3.getContenu().put(new Coordonnees(i, 1, salle3), new Champ(TypeCellule.ARRIERE));
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean verifierConditionVictoire() {
        boolean victoireRobotsOpposes = Math.abs(robot1.getPositionX() - robot2.getPositionX()) == 4 ||
                Math.abs(robot1.getPositionY() - robot2.getPositionY()) == 4;

        return victoire && salle3.getContenu().values().stream().anyMatch(champ -> champ.getType() == TypeCellule.FICHIER) && victoireRobotsOpposes;
    }

    //getter pour les 2 robots
    public Robot getRobot1() {
        return robot1;
    }

    public Robot getRobot2() {
        return robot2;
    }
}
