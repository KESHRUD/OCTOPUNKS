package src.view;

import src.model.Coordonnees;
import src.model.Robot;
import src.model.Salle;
import src.model.TypeCellule;

import java.awt.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ZoneMonde extends JPanel
{
    private JLabel caseEntree;

    public ZoneMonde(Octopunks octopunks, Jeu jeu) throws IOException
    {
        this.setLayout(null);

        this.setSize((int)octopunks.getDimension().getWidth()*3/4, (int)octopunks.getDimension().getHeight()*5/6);
        this.setLocation(0,0);
        this.setBackground(Color.BLUE);



        loadNiveau();
    }

    public void afficherCellule(TypeCellule typeCellule, int x, int y) throws IOException
    {
        JLabel pic;
        int width = 30;
        int height = 30;
        switch(typeCellule)
        {
            case EXA1 :     pic = new JLabel(new ImageIcon(ImageIO.read(new File("images/Exapunks_robot.png"))));
                            pic.setBounds(x,y,width,height);            
                            this.add(pic);
                            pic = new JLabel(new ImageIcon(ImageIO.read(new File("images/Vide.png"))));
                            pic.setBounds(x,y,width,height);
                            this.add(pic);
                            break;
            
            case EXA2 :     pic = new JLabel(new ImageIcon(ImageIO.read(new File("images/Exapunks_robot.png"))));
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
                System.out.println("Ligne "+nbLignes++);
                int i = 0;
                if(nbLignes == 8 || nbLignes == 16)
                {
                    for (i = 0; i < ligneFichier.length(); i++)
                    {
                        //System.out.println("Ligne "+i +" : "+ ligneFichier.length() + "carac");

                        char symbol = ligneFichier.charAt(i);
                        if(symbol != ' ')
                        {
                            TypeCellule typeCellule = TypeCellule.fromSymbol(symbol);
                            afficherCellule(typeCellule,x,y);
                        }
                        x+=40;
                    }
                }
                else
                {
                    for (i = 0; i < ligneFichier.length(); i++)
                    {
                        //System.out.println("Ligne "+i +" : "+ ligneFichier.length() + "carac");

                        char symbol = ligneFichier.charAt(i);
                        TypeCellule typeCellule = TypeCellule.fromSymbol(symbol);
                        afficherCellule(typeCellule,x,y);
                        x+=40;

                            // DEBUG
                            if(symbol != ' ')
                        {
                            // System.out.println("Pas espace");
                        }
                        else
                        {
                            // System.out.println("Espace");
                        }
                    }
                    x = 20;
                    y += 40;
                }
            }
            fichierBuffer.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
