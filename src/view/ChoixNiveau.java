package src.view;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;


/**
 * Dans cette page, on va régler les paramètres de jeu (niveau, difficulté),
 * et on pourra ensuite démarrer le jeu.
 * N.B : Le choix du niveau, c'est pour avoir plus de 10 (c'est donc optionnel).
 */
public class ChoixNiveau extends JPanel
{
    private Octopunks octopunks;

    private JLabel titreSection;
    private JPanel zoneChoixNiveau;
    private JButton bouton1, bouton2, bouton3, bouton4;

    /**
     * Crée la page de choix du gameplay.
     */
    public ChoixNiveau(Octopunks octopunks)
    {
        if(octopunks == null)
        {
            throw new NullPointerException("Octopunks est null dans la classe ChoixNiveau.");
        }
        this.octopunks = octopunks;

        this.setBounds(0,0,(int)octopunks.getDimension().getWidth(),(int)octopunks.getDimension().getHeight());
        this.setLayout(null);
        this.setBackground(Color.BLUE);

        /**
         * Ajout d'un "titre" de section (i.e la section de choix du niveau) :
         * - redimensionnement et replacement 
         * - choix arbitraire de la police d'écriture du label
         * - choix arbitraire de la couleur du label
         * - ajout du label dans la fenêtre
         */
        this.titreSection = new JLabel("Choisissez un niveau :");
        this.titreSection.setSize(300, 30);
        this.titreSection.setLocation((int)(octopunks.getDimension().getWidth()/2-titreSection.getWidth()/2),20);
        this.titreSection.setFont(new Font(Font.SERIF,Font.BOLD,25));
        this.titreSection.setForeground(Color.GREEN);
        this.add(this.titreSection);


        /**
         * Création du conteneur des boutons :
         * - setLayout(null) permet de faire des modifications d'affichage dans le panel
         * - redimensionnement et replacement 
         * - choix arbitraire de la couleur de l'arrière-plan (À CHANGER)
         * - création des boutons dans la zone dédiée aux boutons (le panel zoneBoutons)
         *   [ N.B : cette méthode sert juste à rendre le code du constructeur plus propre,
         *    c'est pourquoi elle est privée.]
         * - ajout de la zone de boutons dans le panel principal
         */
        this.zoneChoixNiveau = new JPanel();
        this.zoneChoixNiveau.setLayout(null);
        this.zoneChoixNiveau.setSize((int)octopunks.getDimension().getWidth()/2,(int)octopunks.getDimension().getHeight()/2);
        this.zoneChoixNiveau.setLocation((int)octopunks.getDimension().getWidth()/4,(int)octopunks.getDimension().getHeight()/4);
        this.zoneChoixNiveau.setBackground(Color.PINK);
        creerBoutons(this.zoneChoixNiveau);
        this.add(this.zoneChoixNiveau);
    }


    /**
     * Crée les boutons de niveau.
     * Modifie les polices, les dimensions.
     * @param panel le panel dans lequel on met les détails de choix du niveau.
     * @requires !(panel == null)
     * @ensures panel.contains(niveaux_liste) À VERIFIER
     * @throws new NullPointerException si le panel est null.
     * (N.B : cette méthode est private car elle ne sert qu'à rendre le code 
     * du constructeur plus propre.)
     */
    private void creerBoutons(JPanel panel)
    {
        if(panel == null)
        {
            throw new NullPointerException("Le panel est null.");
        }

        JLabel descriptif = new JLabel("");
        descriptif.setSize(600,300);

        descriptif.setLocation((int)octopunks.getDimension().getWidth()/2-descriptif.getWidth(), panel.getHeight()/2);
        panel.add(descriptif);

        /** 
         * Création du 2ème bouton : on le créé en premier pour pouvoir le mettre
         * au centre, et donc mettre les autres boutons autour.
         */
        this.bouton2 = new JButton("Geek du vendredi soir");
        this.bouton2.setSize(200,70);
        this.bouton2.setLocation((int)(octopunks.getDimension().getWidth()/2 - this.bouton2.getWidth())/2, 100);
        this.bouton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                descriptif.setAlignmentX(JPanel.CENTER_ALIGNMENT);
                descriptif.setText("Destiné à ceux qui n'ont plus de vie sociale à cause du niveau 1.");
                octopunks.setNiveau(2);
                System.out.println("Niveau : " + octopunks.getNiveau());
            }
        });
        panel.add(this.bouton2);

        // Création du 1er bouton
        this.bouton1 = new JButton("Octo-bébé");
        this.bouton1.setSize(100,70);
        this.bouton1.setLocation(this.bouton2.getX() - this.bouton1.getWidth(), 100);
        this.bouton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                descriptif.setAlignmentX(JPanel.CENTER_ALIGNMENT);
                descriptif.setText("Pour les nouveaux qui découvrent le jeu.");
                octopunks.setNiveau(1);
                System.out.println("Niveau : " + octopunks.getNiveau());
            }
        });
        panel.add(this.bouton1);

        // Création du 3ème bouton pour le niveau 3
        JButton bouton3 = new JButton("Maître Poulpe");
        bouton3.setBounds(bouton2.getX()+bouton2.getWidth(), 100, 100, 70);
        bouton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                descriptif.setAlignmentX(JPanel.CENTER_ALIGNMENT);
                descriptif.setText("Réservé exclusivement aux nerds qui ont Octopunks dans les veines.");
                octopunks.setNiveau(3);
                System.out.println("Niveau : " + octopunks.getNiveau());
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
                if(octopunks.getNiveau() == 0)
                {
                    descriptif.setAlignmentX(JPanel.CENTER_ALIGNMENT);
                    descriptif.setText("Il faut sélectionner un niveau avant de jouer !");
                }
                else
                {
                    octopunks.changerPage("Jeu");
                }
            }
        });
        panel.add(bouton4);
    }

    public int getNiveau()
    {
        return octopunks.getNiveau();
    }
}
