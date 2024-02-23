package src.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import levels.Niveau;
import src.model.Champ;
import src.model.Coordonnees;
import src.model.Instruction;
import src.model.Salle;
import src.model.TypeCellule;


public class JeuTextuel {

    Scanner console = new Scanner(System.in);

    public JeuTextuel() {
        boolean jeuEnCours = true;
        String menu;

        System.out.println("Bienvenue sur Octopunks !\n");
        while (jeuEnCours) {
            System.out.println(" _______________________");
            System.out.print("| Choisissez un niveau  |\n| -(1) Niveau 0\t\t|\n| -(2) Niveau 1\t\t|\n| -(3) Niveau 2\t\t|\n| -(0) Quitter\t\t|\n");
            System.out.println("|_______________________|\n");
            menu = console.nextLine();
            switch (menu) {
                case "0":
                    System.out.print("Aurevoir\n");
                    jeuEnCours = false;
                    break;
                case "1":
                    lancerNiveau("levels/Niveau1.txt");
                    break;
                case "2":
                    lancerNiveau("");
                    System.out.print("Niveau 1\n");
                    break;
                case "3":
                    lancerNiveau("");
                    System.out.print("Niveau 2\n");
                    break;
                default:
                    System.out.print("Entrez le bon caractère !!\n");
                    break;
            }
        }
        console.close();
    }

    public void lancerNiveau(String cheminNiveau) {
        Niveau niveau1 = new Niveau(cheminNiveau);
        boolean executionEnCours = true;
        boolean niveauEnCours = true;
        String asmbCode = "";
        String lastLign = "";
        afficheNiveau(cheminNiveau);
        System.out.println("Le but de ce niveau est de vous familiariser avec Octopunks");
        System.out.println("Pour terminer le niveau vous devrez faire en sorte que le robot\namène l'objet dans la 3e grille !");
        System.out.println("Saisissez le code assembleur,\nn'oubliez pas d'utiliser l'instruction HALT pour terminer votre code ASBM.\n(entrez 'Menu' pour retourner au menu)\n");
        while (niveauEnCours && executionEnCours) {

            // INTERFACE TERMINAL CODE ASSEMBLEUR //

            while ((!lastLign.equalsIgnoreCase("HALT")) && (!lastLign.equalsIgnoreCase("Menu"))) {
                lastLign = console.nextLine();
                asmbCode += lastLign + "\n";
            }

            // PARTIE EXECUTION DU CODE ASSEMBLEUR //

            if (lastLign.equalsIgnoreCase("Menu")) {
                niveauEnCours = false;
                break;
            }
            System.out.println("(1) Exécuter pas à pas les instructions\n(2) Exécuter toutes les instructions directement");
            ArrayList<Instruction> codeAssembleur = parse(asmbCode);
            String choix = console.nextLine();
            niveau1.getRobot1().setLesInstructions(codeAssembleur);
            switch (choix) {
                case "1" :
                    for (Instruction instruction : codeAssembleur){
                        console.nextLine();
                        instruction.printInstruction();
                        niveau1.getRobot1().executeInstruction();
                        executionEnCours = false;
                    }
                    break;
                case "2":
                    System.out.println("Exécution du code assembleur :");
                    niveau1.getRobot1().executeAllInstruction();
                    executionEnCours = false;
                    break;
                
                default :
                    System.out.println("Entrez le bon caractère !!");
                    break;
            }
        }
        if (verifierFichierDansSalle3(200, niveau1.getSalle3())) {
            System.out.println("Fichier trouvé dans la salle 3 ! Vous avez terminé le niveau !");
        } else {
            System.out.println("Le fichier n'est pas dans la salle 3 !");
        }
    }

    private void afficheNiveau(String cheminNiveau) {
        try {
            // LECTURE DU FICHIER DU NIVEAU POUR L'AFFICHER //
            FileReader fichierNiveau = new FileReader(cheminNiveau);
            BufferedReader niveau = new BufferedReader(fichierNiveau);
            String ligne = niveau.readLine();
            while (ligne != null) {
                System.out.println(ligne);
                ligne = niveau.readLine();
            }
            niveau.close();
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    /* Fonction permettant de lire le code assembleur
     * et de retouner une listes d'objet de type instructions avec leur paramètres
     *
     * @param listASMB Chaine de caractère constituée des instructions Assembleur
     * @return Une liste contenant chaque instruction assembleur et ses paramètres
     *         null si le texte est vide
     */
    public ArrayList<Instruction> parse(String listeASBM) {
        if (listeASBM.isBlank()) return null;
        ArrayList<Instruction> listeInstruction = new ArrayList<>();
        String[] liste = listeASBM.split("\n");
        for (String instruction : liste) {
            String[] code = instruction.split(" ");
            switch (code.length) {
                case 1:
                    listeInstruction.add(new Instruction(Instruction.InstructionType.valueOf(code[0])));
                    break;
                case 2:
                    listeInstruction.add(new Instruction(Instruction.InstructionType.valueOf(code[0]), code[1]));
                    break;
                case 3:
                    listeInstruction.add(new Instruction(Instruction.InstructionType.valueOf(code[0]), code[1], code[2]));
                    break;
                case 4:
                    listeInstruction.add(new Instruction(Instruction.InstructionType.valueOf(code[0]), code[1], code[2], code[3]));
                    break;
                default:
                    System.out.println("Erreur nombres d'arguments");
                    break;
            }
        }
        return listeInstruction;
    }

    /* 
    public void executerPasAPas(ArrayList<Instruction> codeAssembleur, String cheminNiveau, Niveau niveau){
        System.out.println("Exécuter pas à pas votre code Assembleur en appuyant sur 'Entrée'");
        for (Instruction instruction : codeAssembleur) {
            console.nextLine(); // Permet d'attendre que l'utilisateur appuie sur ENTREE
            instruction.printInstruction();
            niveau.getRobot1().executeInstruction();
            afficheNiveau(cheminNiveau);
        }   
    }*/
    
    public boolean verifierFichierDansSalle3(int idFichier, Salle salle) {
        for (Map.Entry<Coordonnees, Champ> champ : salle.getContenu().entrySet()) {
            Coordonnees coordonnees = champ.getKey();
            Champ contenu = champ.getValue();
            if (contenu.getType() == TypeCellule.FICHIER && coordonnees.getSalle().equals(salle) && coordonnees.getSalle().getTheFile().getId() == idFichier) {
                return true;
            }
        }
        return false;
    }
    
    
    public static void main(String[] var0) {
        new JeuTextuel();
        
    }

}
