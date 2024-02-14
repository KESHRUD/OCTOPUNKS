import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Color;

public class ZoneMemoire extends JPanel
{

    public ZoneMemoire(Octopunks octopunks)
    {
        this.setSize(200,200);
        this.setLocation(0,(int)octopunks.getDimension().getHeight()-getHeight());
        this.setBackground(Color.PINK);
        this.setLayout(null);
    }
}
