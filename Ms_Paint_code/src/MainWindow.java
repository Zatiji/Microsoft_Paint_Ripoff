import javax.swing.JFrame;
import javax.swing.JToolBar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class MainWindow extends JFrame {
    private int MAIN_WINDOW_WIDTH = 900;
    private int MAIN_WINDOW_HEIGHT = 600;
    private Toolkit toolKit = getToolkit();
    
    public MainWindow() {
        // creating the elements 
        JToolBar mainToolBar = new JToolBar();

        // adding the elements
        add(mainToolBar, BorderLayout.NORTH);

        // basic settings for the window
        setSize(MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
        setTitle("Microsoft NOT paint");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Centering the window
        Dimension screenSize = toolKit.getScreenSize();
        setLocation(screenSize.width/2 - getWidth()/2, 
                    screenSize.height/2 - getHeight()/2);
    }
}
