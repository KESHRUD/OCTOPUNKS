import java.util.*;
import java.lang.*;
import java.io.*;

public class jeuTextuel {

    public jeuTextuel(){
        boolean jeuEnCours = true;

        String menu;

        System.out.println("Bienvenue sur Octopunks !\n");
        Scanner console = new Scanner(System.in);
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
                    lancerNiveau("niveau/tutoriel.txt");
                    System.out.print("Tutoriel Octopunks !\n");
                    break;
                case "2":
                    lancerNiveau("niveau/niveau1.txt");
                    System.out.print("Niveau 1\n");
                    break;
                case "3":
                    lancerNiveau("niveau/niveau2.txt");
                    System.out.print("Niveau 2\n");
                    break;
                default:
                    System.out.print("Entrez le bon caractère !!\n");
                    break;
            }
        }
    }
    public void lancerNiveau(String cheminNiveau){
        boolean niveauEnCours = true;
        String asmbCode;
        while (niveauEnCours){
            try {
                FileReader fichierNiveau = new FileReader(cheminNiveau);
                BufferedReader niveau = new BufferedReader(fichierNiveau);
                String ligne = niveau.readLine();
                while (ligne != null) {
                    System.out.println(ligne);
                    ligne = niveau.readLine();
                }
                niveau.close();
            } catch (IOException e){
                    e.printStackTrace();
            }
            System.out.println("Entrez le code assembleur");
            Scanner assembleur = new Scanner(System.in);
            asmbCode = assembleur.nextLine();
        }
    }


    public static void main(String[] var0) {
        new jeuTextuel();
    }
}