import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


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

   // Les gestionnaires d'affichage des pages
   private CardLayout lesPages;
   private JPanel contenu;

   private BarreDeMenu barreDeMenu;
   protected Menu menu;
   protected ChoixNiveau choixNiveau;
   protected Jeu jeu;

   private int niveau;

   /**
    * Lance le jeu. On va devoir changer de page en cliquant sur le
    * bouton correspondant.
    */
   public Octopunks()
   {

      this.dimension = Toolkit.getDefaultToolkit().getScreenSize();
      //System.out.println(dimension.toString());
      this.setTitle("Octopunks");

      // L'attribut niveau vaut initialement 0
      this.niveau = 0;

      // Création du CardLayout et du conteneur qui permet de changer de page
      this.lesPages = new CardLayout();
      this.contenu = new JPanel(lesPages);

      /**
       * Création des différentes pages/interfaces de contrôle :
       * - la barre de menu qui sert à passer en plein écran ou à quitter le jeu
       * - la page de menu
       * - la page de choix du niveau
       * - la page de jeu
       */
      this.barreDeMenu = new BarreDeMenu(this);
      this.setJMenuBar(this.barreDeMenu);

      this.menu = new Menu(this);
      this.choixNiveau = new ChoixNiveau(this);
      this.jeu = new Jeu(this);

      // Ajout des pages au CardLayout
      this.contenu.add(this.menu, "Menu");
      this.contenu.add(this.choixNiveau, "Choix du niveau");
      this.contenu.add(this.jeu, "Jeu");

      // Ajout du CardLayout à la fenêtre
      this.add(this.contenu);

      // Ajout de la barre de menu à la fenêtre
      //this.setJMenuBar(this.barreDeMenu);

      this.lesPages.show(this.contenu, "Menu");


      
      GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
      //Est ce que le mode plein ecran est disponible ?
      if (device.isFullScreenSupported()) {
         device.setFullScreenWindow(this);
      } else {
         System.out.println("Le mode plein ecran n'est pas disponible");
      }

      /**
       * Réglages de la fenêtre :
       * - on maximise la dimension de la fenêtre
       * - on permet la fermeture de la fenêtre avec un clic sur le bouton fermer
       * - on permet le redimensionnement de la fenêtre (même si c'est inutile)
       * - on centre la fenêtre (même si c'est inutile)
       * - on rend la fenêtre visible
       */ 
      this.setExtendedState(JFrame.MAXIMIZED_BOTH);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setResizable(true);
      this.setLocationRelativeTo(null);
      this.setVisible(true);
   }

   /**
    * @return la dimension de l'écran principal de l'utilisateur.
    */
   public Dimension getDimension()
   {
      return this.dimension;
   }

   /**
    * @return la page gameplay
    * @throws NullPointerException si l'attribut gameplay est null.
    */
   public ChoixNiveau getChoixNiveau()
   {
      if(this.choixNiveau == null)
      {
         throw new NullPointerException("La page du choix de niveau est null.");
      }
      return this.choixNiveau;
   }

   /**
    * @return la page jeu.
    * @throws NullPointerException si l'attribut jeu est null.
    */
   public Jeu getJeu()
   {
      if(this.jeu == null)
      {
         throw new NullPointerException("La page de jeu est null.");
      }
      return this.jeu;
   }

   /**
    * Permet de changer de page.
    * @param nomPage
    * @requires !(nomPage == null)
    * @throws NullPointerException si le paramètre est null.
    */
   public void changerPage(String nomPage)
   {
      if(nomPage == null)
      {
         throw new NullPointerException("Le nom de la page est null.");
      }
      lesPages.show(contenu, nomPage);
   }

   /**
    * @return la page de menu
    * @throws NullPointerException si la page de menu est null
    */
   public Menu getMenu()
   {
      if(this.menu == null)
      {
         throw new NullPointerException("La page de menu est null.");
      }
      return this.menu;
   }

   /**
    * @return la valeur du niveau
    */
   public int getNiveau()
   {
      return this.niveau;
   }

   /**
    * Permet de modifier la valeur du niveau (donc de changer de niveau)
    * @param niveau la valeur du niveau que l'on souhaite jouer
    * @throws IllegalArgumentException si (niveau < 1) ou (niveau > 3)
    */
   public void setNiveau(int niveau)
   {
      if((niveau > 3) || (niveau < 1))
      {
         throw new IllegalArgumentException("Le niveau spécifié n'existe pas.");
      }
      this.niveau = niveau;
   }

   public static void main(String[] args)
   {
      SwingUtilities.invokeLater(Octopunks::new);
   }
}
