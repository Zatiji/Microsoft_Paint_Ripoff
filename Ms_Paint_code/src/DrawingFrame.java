import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;


public class DrawingFrame extends JFrame {

    private DrawAlgorithm canva = new DrawAlgorithm();
    private JButton clearBtn, blackBtn, blueBtn, greenBtn, redBtn, magentaBtn, yellowBtn, orangeBtn;
    private JToolBar mainToolBar;
    private Container content;

    private ActionListener clearCanva = new ActionListener() {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            canva.clearCanva();
        }
    };


    DrawingFrame() {

        // Setting the frame
        setTitle("Cheap Paint 2");
    

        // adding the buttons to the toolBar
        mainToolBar = new JToolBar();
        mainToolBar.add(greenBtn);
        mainToolBar.add(blueBtn);
        mainToolBar.add(blackBtn);
        mainToolBar.add(redBtn);
        mainToolBar.add(yellowBtn);
        mainToolBar.add(orangeBtn);
        mainToolBar.add(magentaBtn);
        mainToolBar.add(clearBtn);

        // setting the layout and teh content pane
        content = this.getContentPane();
        content.setLayout(new BorderLayout());

        content.add(canva, BorderLayout.CENTER);
        content.add(mainToolBar, BorderLayout.NORTH);

        // Center the window
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int CenterWidth = (screenWidth - this.getWidth())/2;
        int CenterHeight = (screenHeight - this.getHeight())/2;
        setLocation(CenterWidth, CenterHeight);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 1200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    DrawAlgorithm getCanva() {
        return canva;
    }

}

