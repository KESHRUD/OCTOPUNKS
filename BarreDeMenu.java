package src.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BarreDeMenu extends JMenuBar
{
    public BarreDeMenu(Octopunks octopunks)
    {
        JMenu menuFenetre = new JMenu("Paramètres");
        JMenuItem boutonMenu = new JMenuItem("Aller au menu");
        boutonMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                octopunks.changerPage("Menu");
            }
        });
        menuFenetre.add(boutonMenu);

        JMenuItem boutonAgrandir = new JMenuItem("Agrandir l'écran");
        boutonAgrandir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                octopunks.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });
        menuFenetre.add(boutonAgrandir);

        JMenuItem boutonPleinEcran = new JMenuItem("Passer en plein écran");
        boutonPleinEcran.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

                if (device.isFullScreenSupported()) {
                    String message = "Le mode plein écran enlève les décorations.";
                    JOptionPane.showMessageDialog(null, message, "Info plein écran", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Pas de plein écran.", "PE", JOptionPane.WARNING_MESSAGE);
                    octopunks.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }
            }
        });
        menuFenetre.add(boutonPleinEcran);
        this.add(menuFenetre);
    }
}
