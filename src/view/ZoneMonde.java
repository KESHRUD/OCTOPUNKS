package src.view;

import src.model.Coordonnees;
import src.model.Robot;
import src.model.Salle;
import src.model.TypeCellule;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ZoneMonde extends JPanel
{
    protected JLabel caseEntree;
    private Jeu jeu;

    private int espaceEntreCases;

    private int largeurMonde;
    private int longueurMonde;

    public Coordonnees[][] tableauCoordonneesCases;

    protected int widthCellule;
    protected int heightCellule;

    public ZoneMonde(Octopunks octopunks, Jeu jeu) throws IOException
    {
        this.jeu = jeu;
        this.setLayout(null);

        this.setSize((int)octopunks.getDimension().getWidth()*3/4, (int)octopunks.getDimension().getHeight()*5/6);
        this.setLocation(0,0);
        this.setBackground(Color.BLUE);

        setDimensionsMonde();

        loadNiveau();
    }

    public void afficherCellule(TypeCellule typeCellule, int x, int y) throws IOException
    {
        JLabel image;
        this.widthCellule = 20;
        this.heightCellule = 20;
        switch(typeCellule)
        {
            case EXA1 : image = jeu.robot1.getRobotLabel();
                        image.setBounds(x,y,widthCellule,heightCellule);            
                        this.add(image);

                        image = new JLabel(new ImageIcon(ImageIO.read(new File("images/Vide.png"))));
                        image.setSize(widthCellule,heightCellule);
                        image.setLocation(x,y);           
                        this.add(image);
                        break;
            
            case EXA2 : image = jeu.robot2.getRobotLabel();
                        image.setBounds(x,y,widthCellule,heightCellule);            
                        this.add(image);

                        image = new JLabel(new ImageIcon(ImageIO.read(new File("images/Vide.png"))));
                        image.setSize(widthCellule,heightCellule);
                        image.setLocation(x,y);           
                        this.add(image);
                        break;
            
            case MUR :  image = new JLabel(new ImageIcon(ImageIO.read(new File("images/mur.png"))));
                        image.setSize(widthCellule,heightCellule);
                        image.setLocation(x,y);           
                        this.add(image);                
                        break;

            case ENTREE :   this.caseEntree = new JLabel(new ImageIcon(ImageIO.read(new File("images/Carre_jaune.png"))));
                            caseEntree.setBounds(x,y,widthCellule,heightCellule);           
                            this.add(caseEntree);
                            break;

            case ARRIERE :  image = new JLabel(new ImageIcon(ImageIO.read(new File("images/Carre_vert.png"))));
                            image.setSize(widthCellule,heightCellule);
                            image.setLocation(x,y);           
                            this.add(image);                
                            break;

            case LINK : image = new JLabel(new ImageIcon(ImageIO.read(new File("images/Lien.png"))));
                        image.setSize(widthCellule,heightCellule);
                        image.setLocation(x,y);           
                        this.add(image);                
                        break;
            
            case ENTRE_SALLE :  image = new JLabel(new ImageIcon(ImageIO.read(new File("images/Entree_salle.png"))));
                                image.setSize(widthCellule,heightCellule);
                                image.setLocation(x,y);           
                                this.add(image);                        
                                break;

            default :   image = new JLabel(new ImageIcon(ImageIO.read(new File("images/Vide.png"))));
                        image.setSize(widthCellule,heightCellule);
                        image.setLocation(x,y);           
                        this.add(image);                
                        break;
        }
        
    }


    /**
     * Permet d'afficher le niveau dans la page de jeu.
     */
    public void loadNiveau()
    {
        try {
            String fichierJeuTextuel = "levels/Niveau1.txt";

            BufferedReader fichierBuffer = new BufferedReader(new FileReader(fichierJeuTextuel));
            String ligneFichier;
            int x = 20;
            int y = 20;

            int nbLignes = 0;

            while ((ligneFichier = fichierBuffer.readLine()) != null)
            {
                nbLignes++;
                
                int i = 0;

                for (i = 0; i < ligneFichier.length(); i++)
                {
                    char symbol = ligneFichier.charAt(i);
                    TypeCellule typeCellule = TypeCellule.fromSymbol(symbol);
                    afficherCellule(typeCellule,x,y);
                    x+=30;
                }
                x = 20;
                y += 30;
            }
        fichierBuffer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Récupère les dimensions du monde.
     * @throw NullPointerException si on n'arrive pas à créer un nouveau buffer sur le fichier.
     */
    public void setDimensionsMonde()
    {
        try {
            String fichierJeuTextuel = "levels/Niveau1.txt";

            BufferedReader fichierBuffer = new BufferedReader(new FileReader(fichierJeuTextuel));
            String ligneFichier;
            
            int nbLignes = 0;
            while ((ligneFichier = fichierBuffer.readLine()) != null)
            {
                nbLignes++;
            }
            fichierBuffer.close();
            this.longueurMonde = nbLignes;
            fichierBuffer = new BufferedReader(new FileReader(fichierJeuTextuel));
            
            if((ligneFichier = fichierBuffer.readLine()) == null)
            {
                fichierBuffer.close();
                throw new NullPointerException("la ligne est null => largeur non initialisée.");
            }

            this.largeurMonde = ligneFichier.length();
            fichierBuffer.close();       
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @return la largeur du monde.
     */
    public int getLargeurMonde()
    {
        return this.largeurMonde;
    }

    /**
     * @return la longueur du monde.
     */
    public int getLongueurMonde()
    {
        return this.longueurMonde;
    }
}
