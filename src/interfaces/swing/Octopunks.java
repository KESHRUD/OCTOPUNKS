import javax.swing.*;

import java.awt.*;


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

   private CardLayout lesPages;
   private JPanel contenu;

   private Menu menu;
   private Gameplay gameplay;
   private Jeu jeu;

   private int niveau;

   /**
    * Lance le jeu. On va devoir changer de page en cliquant sur le
    * bouton correspondant.
    */
   public Octopunks()
   {
      this.dimension = Toolkit.getDefaultToolkit().getScreenSize();

      this.setTitle("Octopunks");

      this.niveau = 0;

      // Création du CardLayout
      this.lesPages = new CardLayout();
      this.contenu = new JPanel(lesPages);

      // Création des différentes pages
      this.menu = new Menu(this);
      this.gameplay = new Gameplay(this);
      this.jeu = new Jeu(this);

      // Ajout des pages au CardLayout
      this.contenu.add(this.menu, "Menu");
      this.contenu.add(this.gameplay, "Gameplay");
      this.contenu.add(this.jeu, "Jeu");

      
      // Ajout du CardLayout à la fenêtre
      this.add(this.contenu);

      this.lesPages.show(this.contenu, "Menu");

      // Réglages de la fenêtre
      this.setExtendedState(JFrame.MAXIMIZED_BOTH);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // On peut modifier la taille de la fenêtre.
      this.setResizable(true);
      //Centre la fenêtre
      this.setLocationRelativeTo(null);
      this.setVisible(true);
   }

   public Dimension getDimension()
   {
      return this.dimension;
   }


   public Gameplay getGameplay()
   {
      if(this.gameplay == null)
      {
         throw new NullPointerException("La page de gameplay est null.");
      }
      return this.gameplay;
   }

   public Jeu getJeu()
   {
      if(this.jeu == null)
      {
         throw new NullPointerException("La page de jeu est null.");
      }
      return this.jeu;
   }

   public void changerPage(String nomPage)
   {
      lesPages.show(contenu, nomPage);
   }

   public Menu getMenu()
   {
      if(this.menu == null)
      {
         throw new NullPointerException("La page de menu est null.");
      }
      return this.menu;
   }

   @SuppressWarnings("unused")
   public int getNiveau()
   {
      return this.niveau;
   }

   public void setNiveau(int niveau)
   {
      this.niveau = niveau;
   }

   public static void main(String[] args)
   {
      SwingUtilities.invokeLater(Octopunks::new);
   }
}
