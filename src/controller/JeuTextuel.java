
package src.interfaces.jeuTextuel;

import src.main.Instruction;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
                    lancerNiveau("src/levels/Niveau1.txt");
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
        boolean executionEnCours = true;
        boolean niveauEnCours = true;
        int j = 0;
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

            switch (choix) {
                case "1" : 
                    executerPasAPas(codeAssembleur, cheminNiveau);
                    executionEnCours = false;
                    break;

                case "2":
                    System.out.println("Exécution du code assembleur :");
                    while (j < codeAssembleur.size()) {
                        codeAssembleur.get(j).printInstruction();
                        j++;
                    }
                    afficheNiveau(cheminNiveau);
                    executionEnCours = false;
                    break;
                
                default :
                    System.out.println("Entrez le bon caractère !!");
                    break;
            }
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

    public void executerPasAPas(ArrayList<Instruction> codeAssembleur, String cheminNiveau){
        System.out.println("Exécuter pas à pas votre code Assembleur en appuyant sur 'Entrée'");
        for (int i = 0; i < codeAssembleur.size(); i++){
            console.nextLine(); // Permet d'attendre que l'utilisateur appuie sur ENTREE
            codeAssembleur.get(i).printInstruction();
            afficheNiveau(cheminNiveau);
        }   
    }

    public static void main(String[] var0) {
        new JeuTextuel();
    }
}
