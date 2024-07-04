import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;


public class MyFrame extends JFrame {

    private Canva canva = new Canva();
    private JButton clearBtn, blackBtn, blueBtn, greenBtn, redBtn, magentaBtn, yellowBtn, orangeBtn;
    private ImageIcon blackIcon, blueIcon, greenIcon, redIcon, magentaIcon, yellowIcon, orangeIcon;
    private JToolBar mainToolBar;
    private Container content;

    // actionslistener for the buttons
    private ActionListener switchColor = new ActionListener() {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == blackBtn) {
                canva.setColorValue(7);
            } else if (e.getSource() == redBtn) {
                canva.setColorValue(1);
            } else if (e.getSource() == blueBtn) {
                canva.setColorValue(2);
            } else if (e.getSource() == yellowBtn) {
                canva.setColorValue(3);
            } else if (e.getSource() == greenBtn) {
                canva.setColorValue(4);
            } else if (e.getSource() == orangeBtn) {
                canva.setColorValue(5);
            } else if (e.getSource() == magentaBtn) {
                canva.setColorValue(6);
            }
        }
    };

    private ActionListener clearCanva = new ActionListener() {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            canva.clearCanva();
        }
    };


    MyFrame() {

        // Setting the frame
        setTitle("Cheap Paint 2");

        // Initialising the icons
        blackIcon = new ImageIcon("images/Black.png");
        redIcon = new ImageIcon("images/Red.png");
        blueIcon = new ImageIcon("images/Blue.png");
        yellowIcon = new ImageIcon("images/Yellow.png");
        greenIcon = new ImageIcon("images/Green.png");
        orangeIcon = new ImageIcon("images/Orange.png");
        magentaIcon = new ImageIcon("images/Magenta.png");
    

        // initialising the buttons
        clearBtn = new JButton("Clear");
        clearBtn.addActionListener(clearCanva);

        blackBtn = new JButton("black");
        //blackBtn.setIcon(blackIcon);
        blackBtn.addActionListener(switchColor);

        redBtn = new JButton("red");
        //redBtn.setIcon(redIcon);
        redBtn.addActionListener(switchColor);

        blueBtn = new JButton("blue");
        //blueBtn.setIcon(blueIcon);
        blueBtn.addActionListener(switchColor);

        yellowBtn = new JButton("yellow");
        //yellowBtn.setIcon(yellowIcon);
        yellowBtn.addActionListener(switchColor);

        greenBtn = new JButton("green");
        //greenBtn.setIcon(greenIcon);
        greenBtn.addActionListener(switchColor);

        orangeBtn = new JButton("orange");
        //orangeBtn.setIcon(orangeIcon);
        orangeBtn.addActionListener(switchColor);

        magentaBtn = new JButton("magenta");
        //magentaBtn.setIcon(magentaIcon);
        magentaBtn.addActionListener(switchColor);

        // adding the buttons to the toolBar
        mainToolBar = new JToolBar();
        mainToolBar.add(greenBtn);
        mainToolBar.add(blueBtn);
        mainToolBar.add(blackBtn);
        mainToolBar.add(redBtn);
        mainToolBar.add(yellowBtn);
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
        //pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}

