import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Stack;

import javax.swing.JPanel;

public class DrawAlgorithm extends JPanel implements MouseListener, MouseMotionListener {

    // tools constants
    private final int PENCIL_TOOL = 0;
    private final int LINE_TOOL = 1;
    private final int RECTANGLE_TOOL = 2;
    private final int CIRCLE_TOOL = 3;
    private final int ERASER_TOOL = 4;

    // shapes constants
    private final int LINE = 1;
    private final int RECTANGLE = 2;
    private final int CIRCLE = 3;

    private Graphics2D graphics2D;

    private int grouped;

    // stack : last in, first out
    Stack<ShapePaint> shapes;
    Stack<ShapePaint> removedShapes;
    Stack<ShapePaint> previewShapes;

    private BufferedImage canvas;

    // brush size
    BasicStroke stroke = new BasicStroke((float) 1);

    // mouse coordinates
    int x1, y1, x2, y2;

    // canva Default settings
    int active_tool = 0;
    Color currentColor = Color.black;
    Color eraserColor = Color.white;
    private boolean isTransparent = true;
    private boolean isDragged = false;

    private DrawingFrame drawFrame;
    int canvaHeight, canvaWidth;

    DrawAlgorithm() {
        setBackground(Color.WHITE);
        shapes = new Stack<>();
    }

    DrawAlgorithm(DrawingFrame drawFrame, int canvaHeight, int canvaWidth) {
        this.drawFrame = drawFrame;

        grouped = 1;

        shapes = new Stack<>();
        removedShapes = new Stack<>();
        previewShapes = new Stack<>();

        setLayout(null);
        setBackground(Color.WHITE);

        addMouseListener(this);
        addMouseMotionListener(this);

        this.canvaHeight = canvaHeight;
        this.canvaWidth = canvaWidth;

        // canvaSize();
    }

    // algorithme pour dessiner
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (canvas == null) {
            canvas = new BufferedImage(canvaWidth, canvaHeight, BufferedImage.TYPE_INT_ARGB);
            graphics2D = canvas.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //clear();
        }

        Graphics2D g2d = (Graphics2D) g;

        for (ShapePaint s : shapes) {
            g2d.setColor(s.getColor());
            g2d.setStroke(s.getStroke());

            if (s.getShape() == LINE) {
                g2d.drawLine(s.getX1(), s.getY1(), s.getX2(), s.getY2());
            } else if (s.getShape() == RECTANGLE) {
                g2d.drawRect(s.getX1(), s.getY1(), s.getX2(), s.getY2());
            } else if (s.getShape() == CIRCLE) {
                g2d.drawOval(s.getX1(), s.getY1(), s.getX2(), s.getY2());
            } else if (s.getShape() == ERASER_TOOL) {
                g2d.drawLine(s.getX1(), s.getY1(), s.getX2(), s.getY2());
            }
        }

        // 
        if (previewShapes.size() > 0) {
            ShapePaint s = previewShapes.pop();
            g2d.setColor(s.getColor());
            g2d.setStroke(s.getStroke());

            if (s.getShape() == RECTANGLE) {
                g2d.drawRect(s.getX1(), s.getY1(), s.getX2(), s.getY2());
            } else if (s.getShape() == CIRCLE) {
                g2d.drawOval(s.getX1(), s.getY1(), s.getX2(), s.getY2());
            } else if (s.getShape() == LINE) {
                g2d.drawLine(s.getX1(), s.getY1(), s.getX2(), s.getY2());
            }
        }
    }

    



}
