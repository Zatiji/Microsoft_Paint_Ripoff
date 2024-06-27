import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JComponent;

// Component for drawing
public class DrawArea extends JComponent {

    // Area where we are drawing
    private Image image;
    // Graphics2D object ==> used to draw on
    private Graphics2D g2;
    // Mouse coordinates
    private int currentMouseX, currentMouseY, oldMouseX, oldMouseY;


    public DrawArea() {
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            
            public void mousePressed(MouseEvent e) {
                // save coord x, y when mouse is pressed
                oldMouseX = e.getX();
                oldMouseY = e.getY();
            }

        });

        addMouseMotionListener(new MouseMotionAdapter() {

            public void mouseDragged(MouseEvent e) {
                currentMouseX = e.getX();
                currentMouseY = e.getY();

                if (g2 != null) {
                    g2.drawLine(oldMouseX, oldMouseY, currentMouseX, currentMouseY);
                    repaint();
                    oldMouseX = currentMouseX;
                    oldMouseY = currentMouseY;
                }
            }

        });
    }
}
