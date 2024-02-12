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
   private Dimension dimension;

   /**
    * Permet de changer de "page" (les pages sont des panels).
    */
   static CardLayout lesPages;
   /**
    * contenu est le conteneur qui va contenir les différentes pages
    * (qui sont des panels)
    */
   static JPanel contenu;

   
   private Menu menu;
   private Gameplay gameplay;
   private Jeu jeu;

   /**
    * Lance le jeu. On va devoir changer de page en cliquant sur le
    * bouton correspondant.
    */
   public Octopunks()
   {
      dimension = Toolkit.getDefaultToolkit().getScreenSize();

      this.setTitle("Octopunks");

      lesPages = new CardLayout();
      contenu = new JPanel(lesPages);

      this.add(contenu);
      contenu.setLayout(lesPages);
      
      this.menu = new Menu(this);
      menu.getBouton().addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e)
         {
            lesPages.show(contenu,"gameplay");
         }
      });
      
      this.gameplay = new Gameplay(this);

      this.jeu = new Jeu(this);

      contenu.add(menu, "menu");
      contenu.add(gameplay, "gameplay");
      contenu.add(jeu, "jeu");

      lesPages.show(contenu,"jeu");

      // Réglages de la fenêtre
      this.setContentPane(contenu);
      this.setLayout(null);
      this.setSize((int)dimension.getWidth(),(int)dimension.getHeight());
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // On peut modifier la taille de la fenêtre.
      this.setResizable(true);
      //Centre la fenêtre
      this.setLocationRelativeTo(null);
      this.setVisible(true);

   }

   public Dimension getDimension()
   {
      return dimension;
   }

   public static void main(String[] args)
   {
      Octopunks octopunks = new Octopunks();
   }
}
