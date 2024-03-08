package src.view;

import javax.swing.*;

import src.model.Instruction;

import java.awt.*;
import java.util.ArrayList;


public class ZoneAssembleur extends JPanel
{
    private JLabel labelAssembleur;
    private JScrollPane scrollBar1;
    private JScrollPane scrollBar2;

    private JTextArea zoneTexteRobot1;
    private JTextArea zoneTexteRobot2;
    private Font police;

    private JLabel FR1;
    private JLabel MR1;
    private JLabel TR1;
    private JLabel XR1;


    /**
     * On crée la zone qui concerne les entrées de code assembleur.
     */
    public ZoneAssembleur(Octopunks octopunks)
    {
        this.setLayout(null);

        this.setSize((int)(octopunks.getDimension().getWidth()/4),(int)octopunks.getDimension().getHeight()*2/3);
        this.setLocation((int)(octopunks.getDimension().getWidth()-this.getWidth()),0);
        this.setBackground(Color.GRAY);


        labelAssembleur = new JLabel("Instructions assembleur");
        labelAssembleur.setBounds(30,30,160,10);
        this.add(labelAssembleur);

        this.police = new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 25);

        zoneTexteRobot1 = new JTextArea();
        zoneTexteRobot1.setSize(this.getWidth()*8/10,(int)(this.getHeight()*0.4));
        zoneTexteRobot1.setLocation(10,60);
        zoneTexteRobot1.setFont(this.police);
        zoneTexteRobot1.setBackground(Color.BLACK);
        zoneTexteRobot1.setForeground(Color.GREEN);
        scrollBar1 = new JScrollPane(zoneTexteRobot1);
        scrollBar1.setBounds(zoneTexteRobot1.getX(), zoneTexteRobot1.getY(), zoneTexteRobot1.getWidth(), zoneTexteRobot1.getHeight());
        this.add(scrollBar1);

        zoneTexteRobot2 = new JTextArea();
        zoneTexteRobot2.setSize(zoneTexteRobot1.getSize());
        zoneTexteRobot2.setLocation(zoneTexteRobot1.getX(),zoneTexteRobot1.getHeight()+zoneTexteRobot1.getY()+20);
        zoneTexteRobot2.setFont(this.police);
        zoneTexteRobot2.setBackground(Color.BLACK);
        zoneTexteRobot2.setForeground(Color.GREEN);
        scrollBar2 = new JScrollPane(zoneTexteRobot2);
        scrollBar2.setBounds(zoneTexteRobot2.getX(), zoneTexteRobot2.getY(), zoneTexteRobot2.getWidth(), zoneTexteRobot2.getHeight());
        this.add(scrollBar2);


        // 4 REGISTRES À AJOUTER X,M,T,F

        this.FR1 = new JLabel("FR1");
        this.FR1.setBounds(this.zoneTexteRobot1.getWidth()+25,50,40,10);
        this.add(this.FR1);

        this.MR1 = new JLabel("MR1");
        this.MR1.setBounds(this.zoneTexteRobot1.getWidth()+25,this.FR1.getY()*2,40,10);
        this.add(this.MR1);
    
        this.TR1 = new JLabel("TR1");
        this.TR1.setBounds(this.zoneTexteRobot1.getWidth()+25,this.FR1.getY()*3,40,10);
        this.add(this.TR1);

        this.XR1 = new JLabel("XR1");
        this.XR1.setBounds(this.zoneTexteRobot1.getWidth()+25,this.FR1.getY()*4,40,10);
        this.add(this.XR1);

    }

    /**
     * Permet de bloquer la modification d'une zone de saisie de code assembleur
     * @param zoneTexte la zone de saisie du code assembleur
     */
    public void disableModification()
    {
        this.zoneTexteRobot1.setEditable(false);
        this.zoneTexteRobot2.setEditable(false);
    }

    /**
     * Permet d'autoriser la modification d'une zone de saisie de code assembleur
     * @param zoneTexte la zone de saisie du code assembleur
     */
    public void enableModification()
    {
        this.zoneTexteRobot1.setEditable(true);
        this.zoneTexteRobot2.setEditable(true);
    }


    /**
     * Renvoie le ccode assembleur du robot 1.
     * @return le code assembleur du robot 1
     * Je modifierai petut-être pour lever une exception si la zone assembleur est vide
     * (dépendra des besoins des autres membres du groupe).
     */
    public String getAssembleurRobot1()
    {
        return zoneTexteRobot1.getText();
    }

    /**
     * Renvoie le ccode assembleur du robot 2.
     * @return le code assembleur du robot 2
     * Je modifierai petut-être pour lever une exception si la zone assembleur est vide
     * (dépendra des besoins des autres membres du groupe).
     */
    public String getAssembleurRobot2()
    {
        return zoneTexteRobot2.getText();
    }

        /**
     * Fonction permettant de lire le code assembleur
     * et de retouner une listes d'objet de type instructions avec leurs paramètres
     * @param listASMB une chaîne de caractères constituée des instructions Assembleur
     * @return Une liste contenant chaque instruction assembleur et ses paramètres;
     *         null si le texte est vide
     */
    public ArrayList<Instruction> parse(String listeASBM)
    {
        if (listeASBM.isBlank()) return null;
        ArrayList<Instruction> listeInstruction = new ArrayList<>();
        String[] liste = listeASBM.split("\n");
        for (String instruction : liste)
        {
            String[] code = instruction.split(" ");
            switch (code.length) {
                case 1:
                    listeInstruction.add(new Instruction(Instruction.InstructionType.valueOf(code[0])));
                    break;
                case 2:
                    listeInstruction.add(new Instruction(Instruction.InstructionType.valueOf(code[0]), code[1]));
                    break;
                case 3:
                    listeInstruction.add(new Instruction(Instruction.InstructionType.valueOf(code[0]), code[1], code[2]));
                    break;
                case 4:
                    listeInstruction.add(new Instruction(Instruction.InstructionType.valueOf(code[0]), code[1], code[2], code[3]));
                    break;
                default:
                    System.out.println("Erreur nombres d'arguments");
                    break;
            }
        }
        return listeInstruction;
    }


    /**
     * SUPPLÉMENTAIRE
     * On veut pouvoir enregistrer le code assembleur des deux zone de saisie.
     * Il faut qu'on puisse les charger au lancement du programme.
     */
    public void saveAssembleur()
    {
        // Compléter
    }
}
