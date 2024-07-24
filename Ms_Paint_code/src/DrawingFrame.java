import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;


public class DrawingFrame extends JFrame {
    private JPanel contentPanel;
    private JToolBar mainToolBar;
    private DrawAlgorithm drawAlgorithmPanel;
    private JScrollPane scrollPane;

    private int canvaWidth = 1100;
    private int canvaHeight = 600;

    DrawingFrame() {
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
    }
 
    

}

