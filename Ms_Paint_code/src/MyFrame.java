import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class MyFrame extends JFrame {

    private Canva canva = new Canva();
    private JButton clearBtn, blackBtn, blueBtn, greenBtn, redBtn, magentaBtn, yellowBtn, orangeBtn;

    // actionslistener for the buttons
    private ActionListener SwitchColor = new ActionListener() {
        
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

        setTitle("Cheap Paint 2");
        getContentPane();
        setLayout(new BorderLayout());
        

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);

        this.add(canva);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}

