import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SwingPaint {

    private JButton clearBtn, blackBtn, blueBtn, greenBtn, redBtn, magentaBtn;
    // create draw area
    DrawArea drawArea= new DrawArea();
    private ActionListener actionListener = new ActionListener() {
        
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearBtn) {
                drawArea.clear();
            } else if (e.getSource() == blackBtn) {
                drawArea.applyBlack();
            } else if (e.getSource() == blueBtn) {
                drawArea.applyBlue();
            } else if (e.getSource() == greenBtn) {
                drawArea.applyGreen();
            } else if (e.getSource() == redBtn) {
                drawArea.applyRed();
            } else if (e.getSource() == magentaBtn) {
                drawArea.applyMagenta();
            }
        }
    };

    public static void main(String[] args) {
        new SwingPaint().show();
    }

    public void show() {
        // create the main frame
        JFrame frame = new JFrame("Cheap Paint");
        Container content = frame.getContentPane();
        // set layout on content pane
        content.setLayout(new BorderLayout());
        
        // add to content pane
        content.add(drawArea, BorderLayout.CENTER);
        
        // create controls to apply colors and call clear features
        JPanel controls = new JPanel();


        // Buttons
        clearBtn = new JButton("clear");
        clearBtn.addActionListener(actionListener);

        blackBtn = new JButton("Black");
        blackBtn.addActionListener(actionListener);

        blueBtn = new JButton("Blue");
        blueBtn.addActionListener(actionListener);

        greenBtn = new JButton("green");
        greenBtn.addActionListener(actionListener);

        redBtn = new JButton("Red");
        redBtn.addActionListener(actionListener);

        magentaBtn = new JButton("Magenta");
        magentaBtn.addActionListener(actionListener);

        // add to panel
        controls.add(greenBtn);
        controls.add(blueBtn);
        controls.add(blackBtn);
        controls.add(redBtn);
        controls.add(magentaBtn);
        controls.add(clearBtn);

        // add to content pane
        content.add(controls, BorderLayout.NORTH);

        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
