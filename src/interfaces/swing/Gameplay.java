import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Dans cette page, on va régler les paramètres de jeu (niveau, difficulté),
 * et on pourra ensuite démarrer le jeu.
 * N.B : Le choix du niveau, c'est pour avoir plus de 10 (c'est donc optionnel).
 */
public class Gameplay extends JPanel
{
    private int niveau;

    /**
     * Crée la page de choix du gameplay.
     */
    public Gameplay()
    {
        this.setBounds(0,0,(int)Octopunks.dimension.getWidth(),(int)Octopunks.dimension.getHeight());
        this.setLayout(null);
        this.setBackground(Color.BLUE);

        /**
         * Ajout d'un "titre" de section (i.e la section de choix du niveau) :
         * - replacement et redimensionnement
         * - choix arbitraire de la police d'écriture du label
         * - choix arbitraire de la couleur du label
         * - ajout du label dans la fenêtre
         */
        JLabel titreSection = new JLabel("Choisissez un niveau :");
        titreSection.setSize(300, 30);
        titreSection.setLocation((int)(Octopunks.dimension.getWidth()/2-titreSection.getWidth()/2),20);
        titreSection.setFont(new Font(Font.SERIF,Font.BOLD,25));
        titreSection.setForeground(Color.GREEN);
        this.add(titreSection);


        /**
         * Création du conteneur des boutons :
         * - setLayout(null) permet de faire des modifications d'affichage dans le panel
         * - replacement et redimensionnement
         * - choix arbitraire de la couleur de l'arrière-plan (À CHANGER)
         * - création des boutons dans la zone dédiée aux boutons (le panel zoneBoutons)
         *   [ N.B : cette méthode sert juste à rendre le code du constructeur plus propre,
         *    c'est pourquoi elle est privée.]
         * - ajout de la zone de boutons dans le panel principal
         */
        JPanel zoneBoutons = new JPanel();
        zoneBoutons.setLayout(null);
        zoneBoutons.setSize((int)Octopunks.dimension.getWidth()/2,(int)Octopunks.dimension.getHeight()/2);
        zoneBoutons.setLocation((int)Octopunks.dimension.getWidth()/4,(int)Octopunks.dimension.getHeight()/4);
        zoneBoutons.setBackground(Color.PINK);
        creerBoutons(zoneBoutons);
        this.add(zoneBoutons);
    }


    /**
     * Crée les boutons de niveau.
     * Modifie les polices, les dimensions.
     * @param panel le panel dans lequel on met les détails de choix du niveau.
     * @requires !(panel == null)
     * @ensures panel.contains(niveaux_liste) À VERIFIER
     * @throws new NullPointerException si le panel est null.
     */
    private void creerBoutons(JPanel panel)
    {
        if(panel == null)
        {
            throw new NullPointerException("Le panel est null.");
        }



        JLabel descriptif = new JLabel("");
        descriptif.setBounds(275, 300, 600, 300);
        panel.add(descriptif);

        // Création du 2ème bouton
        JButton bouton2 = new JButton("Geek du vendredi soir");
        bouton2.setSize(200,70);
        bouton2.setLocation((int)(Octopunks.dimension.getWidth()/2 - bouton2.getWidth())/2, 100);
        bouton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                descriptif.setAlignmentX(JPanel.CENTER_ALIGNMENT);
                descriptif.setText("Destiné à ceux qui n'ont plus de vie sociale à cause du niveau 1.");
                niveau = 2;
                System.out.println("Niveau : " + niveau);
            }
        });
        panel.add(bouton2);

        // Création du 1er bouton
        JButton bouton1 = new JButton("Octo-bébé");
        bouton1.setSize(100,70);
        bouton1.setLocation(bouton2.getX() - bouton1.getWidth(), 100);
        bouton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                descriptif.setAlignmentX(JPanel.CENTER_ALIGNMENT);
                descriptif.setText("Pour les nouveaux qui découvrent le jeu.");
                niveau = 1;
                System.out.println("Niveau : " + niveau);
            }
        });
        panel.add(bouton1);

        

        // Création du 3ème bouton
        JButton bouton3 = new JButton("Maître Poulpe");
        bouton3.setBounds(bouton2.getX()+bouton2.getWidth(), 100, 100, 70);
        bouton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                descriptif.setAlignmentX(JPanel.CENTER_ALIGNMENT);
                descriptif.setText("Réservé exclusivement aux nerds qui ont Octopunks dans les veines.");
                niveau = 3;
                System.out.println("Niveau : " + niveau);
            }
        });
        panel.add(bouton3);

        /**
         * Création du 4ème bouton : un bouton de confirmation qui va permettre
         * de passer à la page suivante.
         */
        JButton bouton4 = new JButton("Confirmer");
        bouton4.setSize(100,70);
        bouton4.setLocation(bouton3.getX(), panel.getHeight()/2);
        bouton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                // À MODIFIER
            }
        });
        panel.add(bouton4);
    }

    public int getNiveau()
    {
        return this.niveau;
    }

    public void setActionListener(ActionListener l)
    {

    }
}
