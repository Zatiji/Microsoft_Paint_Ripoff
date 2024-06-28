import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
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
                // coord x, y when dragMouse
                currentMouseX = e.getX();
                currentMouseY = e.getY();

                if (g2 != null) {
                    // draw a line if g2 is not null(if its in the draw area)
                    g2.drawLine(oldMouseX, oldMouseY, currentMouseX, currentMouseY);
                    // -------------g2.drawRect(currentMouseX, currentMouseY, 5, 5);
                    // refresh draw area to repaint
                    repaint();
                    // store currents coords x, y as old coords
                    oldMouseX = currentMouseX;
                    oldMouseY = currentMouseY;
                }
            }

        });
    }

    protected void paintComponent(Graphics g) {
        if (image == null) {
            // image to draw null ==> we create
            image = createImage(getSize().width, getSize().height);
            g2 = (Graphics2D) image.getGraphics();
            // enable antialiasing
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // clear draw area
            clear();
        }

        g.drawImage(image, 0, 0, null);
    }

    // now we create exposed methods

    public void clear() {
        g2.setPaint(Color.white);
        // draw white on entire draw area to clear
        g2.fillRect(0, 0, getSize().width, getSize().height);
        g2.setPaint(Color.black);
        repaint();
    }

    public void applyRed() {
        g2.setPaint(Color.red);
    }

    public void applyBlack() {
        g2.setPaint(Color.black);
    }

    public void applyMagenta() {
        g2.setPaint(Color.magenta);
    }

    public void applyGreen() {
        g2.setPaint(Color.green);
    }

    public void applyBlue() {
        g2.setPaint(Color.blue);
    }


}
