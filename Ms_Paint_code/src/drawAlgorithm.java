import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JPanel;

public class drawAlgorithm extends JPanel{

    // static variables
    private int brushSize;
    private Graphics2D g2;

    private Image image;
    private int currentMouseX, currentMouseY, oldMouseX, oldMouseY;

    // public variables
    Color currentColor = Color.black;
    Color eraserColor = Color.white;
    BasicStroke strokeSize = new BasicStroke((float) 1); 
    int active_tool = 0;


    drawAlgorithm() {

        // setting the values of private members
        brushSize = 3;
        
        this.setPreferredSize(new Dimension(1400, 1000));
        setDoubleBuffered(false);

        // algorithm that saves the "old" x and y mouse coordinates
        addMouseListener(new MouseAdapter() {
            
            public void mousePressed(MouseEvent e) {
                // save coord x, y when mouse is pressed
                oldMouseX = e.getX();
                oldMouseY = e.getY();
            }

        });

        // algorithm to draw on canva
        addMouseMotionListener(new MouseMotionAdapter() {

            public void mouseDragged(MouseEvent e) {
                // coord x, y when dragMouse
                currentMouseX = e.getX();
                currentMouseY = e.getY();

                // draw a line if g2 is not null(if its in the draw area)
                if (g2 != null) {
                    // setting size and color of brush
                    g2.setStroke(new BasicStroke(brushSize));
                    // drawing the line
                    g2.drawLine(oldMouseX, oldMouseY, currentMouseX, currentMouseY);
                    // refresh draw area to repaint
                    repaint();
                    // store currents coords x, y as old coords
                    oldMouseX = currentMouseX;
                    oldMouseY = currentMouseY;
                }
            }

        });
    }

    // we create the "actual" canva to draw on
    protected void paintComponent(Graphics g) {

        if (image == null) {
            // image to draw null ==> we create
            image = createImage(getSize().width, getSize().height);
            g2 = (Graphics2D) image.getGraphics();
            // enable antialiasing
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // clear draw area
            clearCanva();
        }

        g.drawImage(image, 0, 0, null);
    }

    public void clearCanva() {

        g2.setPaint(Color.white);
        // draw white on entire draw area to clear
        g2.fillRect(0, 0, getSize().width, getSize().height);
        g2.setPaint(Color.black);
        repaint();
    }


    public void setSizeValue(int newValue) {
        brushSize = newValue;
    }

    

}