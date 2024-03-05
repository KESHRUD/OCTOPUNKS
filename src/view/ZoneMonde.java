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
    private JLabel caseEntree;
    private Jeu jeu;

    public ZoneMonde(Octopunks octopunks, Jeu jeu) throws IOException
    {
        this.jeu = jeu;
        this.setLayout(null);

        this.setSize((int)octopunks.getDimension().getWidth()*3/4, (int)octopunks.getDimension().getHeight()*5/6);
        this.setLocation(0,0);
        this.setBackground(Color.BLUE);



        loadNiveau();
    }

    public void afficherCellule(TypeCellule typeCellule, int x, int y) throws IOException
    {
        JLabel pic;
        int width = 20;
        int height = 20;
        switch(typeCellule)
        {
            case EXA1 :     pic = new JLabel(jeu.robot1.getImageIcon());
                            pic.setBounds(x,y,width,height);            
                            this.add(pic);

                            pic = new JLabel(new ImageIcon(ImageIO.read(new File("images/Vide.png"))));
                            pic.setBounds(x,y,width,height);
                            this.add(pic);
                            break;
            
            case EXA2 :     pic = new JLabel(jeu.robot2.getImageIcon());
                            pic.setBounds(x,y,width,height);            
                            this.add(pic);

                            pic = new JLabel(new ImageIcon(ImageIO.read(new File("images/Vide.png"))));
                            pic.setBounds(x,y,width,height);
                            this.add(pic);
                            break;
            
            case MUR :      pic = new JLabel(new ImageIcon(ImageIO.read(new File("images/mur.png"))));
                            pic.setSize(width,height);
                            pic.setLocation(x,y);           
                            this.add(pic);
                            break;

            case ENTREE :   this.caseEntree = new JLabel(new ImageIcon(ImageIO.read(new File("images/Carre_jaune.png"))));
                            caseEntree.setBounds(x,y,width,height);           
                            this.add(caseEntree);
                            break;

            case ARRIERE :  pic = new JLabel(new ImageIcon(ImageIO.read(new File("images/Carre_vert.png"))));
                            pic.setBounds(x,y,width,height);            
                            this.add(pic);
                            break;

            case LINK :     pic = new JLabel(new ImageIcon(ImageIO.read(new File("images/Lien.png"))));
                            pic.setBounds(x,y,width,height);
                            this.add(pic);
                            break;
            
            case ENTRE_SALLE :     pic = new JLabel(new ImageIcon(ImageIO.read(new File("images/Entree_salle.png"))));
                            pic.setBounds(x,y,width,height);
                            this.add(pic);
                            break;

            default :       pic = new JLabel(new ImageIcon(ImageIO.read(new File("images/Vide.png"))));
                            pic.setBounds(x,y,width,height);
                            this.add(pic);
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
                
                System.out.println("Ligne "+nbLignes);
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
}
