import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SwingPaint {

    JButton clearBtn, blackBtn, blueBtn, greenBtn, redBtn, magentaBtn;
    DrawArea drawArea;
    ActionListener actionListener = new ActionListener() {
        
        @Override
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

    }

    public void show() {
        // create the main frame
        JFrame frame = new JFrame("Cheap Paint");
        Container content = frame.getContentPane();
        // set layout on content pane
        content.setLayout(new BorderLayout());
        //creat draw area
        final DrawArea drawArea= new DrawArea();
        
        // add to content pane
        content.add(drawArea, BorderLayout.CENTER);
        
        // create controls to apply colors and call clear features
        JPanel controls = new JPanel();
        
        clearBtn = new JButton("clear");
        blackBtn = new JButton("Black");
        blueBtn = new JButton("Blue");
        greenBtn = new JButton("green");
        redBtn = new JButton("Red");
        magentaBtn = new JButton("Magenta");

    }
}
