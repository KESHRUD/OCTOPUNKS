import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;


/**
 * Fenêtre du jeu.
 * Par défaut en plein écran (PAS ENCORE).
 * On y ajoutera les panels qui correspondent aux différentes
 * sections du jeu (menu, choix du niveau, interface de jeu).
 */
public class Octopunks extends JFrame
{
   /**
    * La dimension de l'écran de l'utilisateur.
    */ 
   static Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

   /**
    * Permet de changer de "page" (les pages sont des panels).
    */
   static CardLayout lesPages;
   /**
    * contenu est le conteneur qui va contenir les différentes pages
    * (qui sont des panels)
    */
   static Container contenu;


   /**
    * Lance le jeu. On va devoir changer de page en cliquant sur le
    * bouton correspondant.
    */
   public Octopunks()
   {
      this.setTitle("Octopunks");

      contenu = getContentPane();
      lesPages = new CardLayout();
      
      JPanel menu = new Menu();
      JPanel gameplay = new Gameplay();
      JPanel jeu = new Jeu();

      contenu.add(menu);
      contenu.add(gameplay);
      contenu.add(jeu);




      // Réglages de la fenêtre

      this.setLayout(null);
      this.setSize(1000,800);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // On peut modifier la taille de la fenêtre.
      this.setResizable(true);
      //Centre la fenêtre
      this.setLocationRelativeTo(null);
      this.setVisible(true);

   }

   public void changerPage()
   {
      removeAll();
      
      repaint();
   }


   public static void main(String[] args)
   {
      Octopunks octopunks = new Octopunks();
   }
}
