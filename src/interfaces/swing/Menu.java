import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;


/**
 * Cette classe représente le menu du jeu.
 * Un bouton démarrer le jeu permet de passer à la page suivante.
 */
public class Menu extends JPanel
{
   private JButton boutonJouer;

   public Menu(Octopunks octopunks)
   {
      this.setBounds(0,0,(int)octopunks.getDimension().getWidth(),(int)octopunks.getDimension().getHeight());
      this.setLayout(null);
      this.setBackground(Color.RED);

      System.out.println("Hauteur : " + octopunks.getDimension().getHeight());
      System.out.println("Largeur : " + octopunks.getDimension().getWidth());


      JLabel titre = new JLabel("Octopunks Version Finale");
      titre.setSize(400,200);
      titre.setLocation((int)octopunks.getDimension().getWidth()/2-titre.getWidth()/2,100);
      titre.setFont(new Font(Font.SERIF,Font.BOLD,30));
      this.add(titre);


      boutonJouer = new JButton("Jouer");
      // Positionner et ajouter le bouton au conteneur
      boutonJouer.setSize(200,100);
      boutonJouer.setLocation((int)octopunks.getDimension().getWidth()/2 - boutonJouer.getWidth()/2, 300);
      
      boutonJouer.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // AJOUTER LISTENER
         }
     });
      this.add(boutonJouer);
   }

   public JButton getBouton()
   {
      return this.boutonJouer;
   }
}
