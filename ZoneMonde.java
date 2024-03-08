package src.view;

import src.model.Coordonnees;
import src.model.Robot;
import src.model.Salle;
import src.model.TypeCellule;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class ZoneMonde extends JPanel
{
    private int dimensionsSalle = 5;

    private Jeu jeu;

    protected int widthCellule;
    protected int heightCellule;
    /**
     * L'espace entre chaque cellule
     */
    private int espaceEntreCases;
    protected JLabel caseEntree;

    private int largeurMonde;
    private int longueurMonde;


    protected Coordonnees[][] salle1_coordonnesGraphiques;
    protected Coordonnees[][] salle2_coordonnesGraphiques;
    protected Coordonnees[][] salle3_coordonnesGraphiques;

    /**
     * Contient les coordonnées graphiques de chaque cellule
     */
    protected Coordonnees[][] coordonneesCases;

    /**
     * CONSTRUCTEUR
     * @param octopunks le main
     * @param jeu l'instance de la classe jeu dans laquelle se trouve cette classe
     * @throws IOException si une image est mal lue
     */
    public ZoneMonde(Octopunks octopunks, Jeu jeu)
    {
        this.jeu = jeu;
        this.setLayout(null);

        this.setSize((int)octopunks.getDimension().getWidth()*3/4, (int)octopunks.getDimension().getHeight()*5/6);
        this.setLocation(0,0);
        this.setBackground(Color.BLUE);

        this.coordonneesCases = null; // initialisation dans loadNiveau()
        this.espaceEntreCases = 30;

        setDimensionsMonde();


        loadNiveau();

        setSousTableauxCoordonneesSalles();

        infoCoordonnees();
    }

    
    /**
     * Permet d'afficher une cellule
     * @param typeCellule le type de la cellule
     * @param xGraphique la position de la cellule sur l'écran selon l'axe x
     * @param yGraphique la position de la cellule sur l'écran selon l'axe y
     * @throws IOException si l'image de la cellule n'a pas pu être lue
     */
    public void afficherCellule(TypeCellule typeCellule, int xGraphique, int yGraphique) throws IOException
    {
        JLabel image;
        this.widthCellule = 20;
        this.heightCellule = 20;
        switch(typeCellule)
        {
            case EXA1 : image = jeu.robot1.getRobotLabel();
                        image.setBounds(xGraphique,yGraphique,widthCellule,heightCellule);            
                        this.add(image);

                        image = new JLabel(new ImageIcon(ImageIO.read(new File("images/Vide.png"))));
                        image.setSize(widthCellule,heightCellule);
                        image.setLocation(xGraphique,yGraphique);           
                        this.add(image);
                        break;
            
            case EXA2 : image = jeu.robot2.getRobotLabel();
                        image.setBounds(xGraphique,yGraphique,widthCellule,heightCellule);            
                        this.add(image);

                        image = new JLabel(new ImageIcon(ImageIO.read(new File("images/Vide.png"))));
                        image.setSize(widthCellule,heightCellule);
                        image.setLocation(xGraphique,yGraphique);           
                        this.add(image);
                        break;
            
            case MUR :  image = new JLabel(new ImageIcon(ImageIO.read(new File("images/mur.png"))));
                        image.setSize(widthCellule,heightCellule);
                        image.setLocation(xGraphique,yGraphique);           
                        this.add(image);                
                        break;

            case ENTREE :   this.caseEntree = new JLabel(new ImageIcon(ImageIO.read(new File("images/Carre_jaune.png"))));
                            caseEntree.setBounds(xGraphique,yGraphique,widthCellule,heightCellule);
                            this.add(caseEntree);
                            break;

            case ARRIERE :  image = new JLabel(new ImageIcon(ImageIO.read(new File("images/Carre_vert.png"))));
                            image.setSize(widthCellule,heightCellule);
                            image.setLocation(xGraphique,yGraphique);
                            this.add(image);
                            break;

            case LINK : image = new JLabel(new ImageIcon(ImageIO.read(new File("images/Lien.png"))));
                        image.setSize(widthCellule,heightCellule);
                        image.setLocation(xGraphique,yGraphique);
                        this.add(image);                
                        break;
            
            case ENTRE_DEUX_SALLES :  image = new JLabel(new ImageIcon(ImageIO.read(new File("images/Entree_salle.png"))));
                                image.setSize(widthCellule,heightCellule);
                                image.setLocation(xGraphique,yGraphique);
                                this.add(image);
                                break;

            default :   image = new JLabel(new ImageIcon(ImageIO.read(new File("images/Vide.png"))));
                        image.setSize(widthCellule,heightCellule);
                        image.setLocation(xGraphique,yGraphique);
                        this.add(image);
                        break;
        }
        
    }

    public Coordonnees getCoordonnees(int x, int y)
    {
        if(y>=getLongueurMonde())
        {
            y = getLongueurMonde()-1;
        }
        if(x >= getLargeurMonde())
        {
            x = getLargeurMonde();
        }
        return this.coordonneesCases[y][x];
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


    public void infoCoordonnees()
    {
        int ligne = 0;
        int colonne = 0;
        int id = 0;
        for(id = 0; id < 3; id++)
        {
            for(ligne = 0; ligne < getLongueurMonde(); ligne++)
            {
                for(colonne = 0; colonne < getLargeurMonde(); colonne ++)
                {
                    //System.out.println("Ligne "+ligne+" colonne "+colonne+" salle"+id);
                    System.out.print("I ");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
        }
        
    }

    /**
     * Permet d'afficher le niveau dans la page de jeu.
     */
    public void loadNiveau()
    {
        try {
            String fichierJeuTextuel = "levels/Niveau1.txt";
            
            this.coordonneesCases = new Coordonnees[getLongueurMonde()][getLargeurMonde()];

            BufferedReader fichierBuffer = new BufferedReader(new FileReader(fichierJeuTextuel));
            String ligneFichier;

            int xGraphique = 20;
            int yGraphique = 20;

            int ligne = 0;

            int numSalle = 0;
            Salle salle = jeu.salle1;

            int x = 0;
            int y = 0;

            while ((ligneFichier = fichierBuffer.readLine()) != null)
            {
                int colonne = 0;
                x = 0;

                for(colonne = 0; colonne < ligneFichier.length(); colonne++)
                {
                    char symbol = ligneFichier.charAt(colonne);

                    TypeCellule typeCellule = TypeCellule.fromSymbol(symbol);
                        
                    this.coordonneesCases[ligne][colonne] = new Coordonnees(x, y, xGraphique, yGraphique, salle);
                    afficherCellule(typeCellule,xGraphique,yGraphique);
                    xGraphique+=espaceEntreCases;
                    //System.out.println("x : "+x+" et y : "+y+" - carac : "+symbol);
                    x++;
                }
                xGraphique = 20;
                yGraphique += espaceEntreCases;
                
                ligne++;
                y++;
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

    public void setSousTableauxCoordonneesSalles()
    {
        this.salle1_coordonnesGraphiques = new Coordonnees[5][5];
        this.salle2_coordonnesGraphiques = new Coordonnees[5][5];
        this.salle3_coordonnesGraphiques = new Coordonnees[5][5];

        int i = 1;
        int ii = 1;
        for(i = 0; i<5; i++)
        {
            salle1_coordonnesGraphiques[i][ii] = coordonneesCases[i][ii];
            //salle2_coordonnesGraphiques[i][ii] = coordonneesCases[i+8][ii];
            //salle3_coordonnesGraphiques[i][ii] = coordonneesCases[i+16][ii];
        }
    }

    public void linkGraphique()
    {
        Robot robot = jeu.robot1;
        int xGraphique = robot.getPositionX();
        int yGraphique = robot.getPositionY();
        //robot.setPositionLabel();
    }
}
