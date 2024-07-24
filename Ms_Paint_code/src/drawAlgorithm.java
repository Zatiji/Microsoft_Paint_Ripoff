import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
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
    BasicStroke strokeSize = new BasicStroke((float) 1);

    // mouse coordinates
    int x1, y1, x2, y2;

    // canva Default settings
    int active_tool = 0;
    Color currentColor = Color.black;
    Color eraserColor = Color.white;
    // private boolean isTransparent = true;
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

        canvaSize();
    }

    // MOUSE METHODS
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

    // mouse listeners
    @Override
    public void mouseClicked(MouseEvent e){ }

    @Override
    public void mousePressed(MouseEvent e) {
        getCoordinate(e);
        x1 = e.getX();
        y1 = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (active_tool == RECTANGLE_TOOL && isDragged) {
            if (x1<x2 && y1<y2) {
                shapes.push(new ShapePaint(x1, y1, x2-x1, y2-y1, currentColor, strokeSize, 2));
            } else if (x2<x1 && y1<y2) {
                shapes.push(new ShapePaint(x2, y1, x1-x2, y2-y1, currentColor, strokeSize, 2));
            } else if (x1<x2 && y2<y1) {
                shapes.push(new ShapePaint(x1, y2, x1-x2, y1-y2, currentColor, strokeSize, 2));
            } else if (x2<x1 && y2<y1) {
                shapes.push(new ShapePaint(x2, y2, x2-x1, y2-y1, currentColor, strokeSize, 2));
            }
            repaint();
        } else if (active_tool == CIRCLE_TOOL && isDragged) {
            if (x1<x2 && y1<y2) {
                shapes.push(new ShapePaint(x1, y1, x2-x1, y2-y1, currentColor, strokeSize, 3));
            } else if (x2<x1 && y1<y2) {
                shapes.push(new ShapePaint(x2, y1, x1-x2, y2-y1, currentColor, strokeSize, 3));
            } else if (x1<x2 && y2<y1) {
                shapes.push(new ShapePaint(x1, y2, x1-x2, y1-y2, currentColor, strokeSize, 3));
            } else if (x2<x1 && y2<y1) {
                shapes.push(new ShapePaint(x2, y2, x2-x1, y2-y1, currentColor, strokeSize, 3));
            }
            repaint();
        } else if (active_tool == LINE_TOOL && isDragged) {
            shapes.push(new ShapePaint(x1, y1, x2, y2, currentColor, strokeSize, 1));
        }
        isDragged = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mouseDragged(MouseEvent e) {
        getCoordinate(e);
        isDragged = true;
        x2 = e.getX();
        y2 = e.getY();
        if (active_tool == PENCIL_TOOL) {
            shapes.push(new ShapePaint(x1, y1, x2, y2, currentColor, strokeSize, 1, grouped));
            repaint();
            x1 = x2;
            y1 = y2;
        } else if (active_tool == RECTANGLE_TOOL) {
            if (x1 < x2 && y1 < y2) {
                previewShapes.push(new ShapePaint(x1, y1, x2-x1, y2-y1, currentColor, strokeSize, 2));
            } else if (x2 < x1 && y1 < y2) {
                previewShapes.push(new ShapePaint(x2, y1, x1-x2, y2-y1, currentColor, strokeSize, 2));
            } else if (x1 < x2 && y2 < y1) {
                previewShapes.push(new ShapePaint(x1, y2, x2-x1, y1-y2, currentColor, strokeSize, 2));
            } else if (x2 < x1 && y2 < y1) {
                previewShapes.push(new ShapePaint(x2, y2, x1-x2, y1-y2, currentColor, strokeSize, 2));
            }
            repaint();
        } else if (active_tool == CIRCLE_TOOL) {
            if (x1 < x2 && y1 < y2) {
                previewShapes.push(new ShapePaint(x1, y1, x2-x1, y2-y1, currentColor, strokeSize, 3));
            } else if (x2 < x1 && y1 < y2) {
                previewShapes.push(new ShapePaint(x2, y1, x1-x2, y2-y1, currentColor, strokeSize, 3));
            } else if (x1 < x2 && y2 < y1) {
                previewShapes.push(new ShapePaint(x1, y2, x2-x1, y1-y2, currentColor, strokeSize, 3));
            } else if (x2 < x1 && y2 < y1) {
                previewShapes.push(new ShapePaint(x2, y2, x1-x2, y1-y2, currentColor, strokeSize, 3));
            }
            repaint();
        } else if (active_tool == LINE_TOOL) {
            previewShapes.push(new ShapePaint(x1, y1, x2-x1, y2-y1, currentColor, strokeSize, 1));
            repaint();
        } else if (active_tool == ERASER_TOOL) {
            shapes.push(new ShapePaint(x1, y1, x2, y2, eraserColor, strokeSize, 4, grouped));
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        getCoordinate(e);
    }

    // PUBLIC METHODS

    public void undo() {
        if (shapes.size() > 0 && shapes.peek().getGroup() == 0) {
            removedShapes.push(shapes.pop());
            repaint();
        } else if (shapes.size() > 0 && shapes.peek().getGroup() != 0) {
            ShapePaint lastRemoved = shapes.pop();
            removedShapes.push(lastRemoved);

            while (!shapes.isEmpty() && shapes.peek().getGroup() == lastRemoved.getGroup()) {
                removedShapes.push(shapes.pop());
                repaint();
            }
        }
    }

    public void redo() {
        if (removedShapes.size() > 0 && removedShapes.peek().getGroup() == 0) {
            shapes.push(removedShapes.pop());
            repaint();
        } else if (removedShapes.size() > 0 && removedShapes.peek().getGroup() != 0) {
            ShapePaint lastRemoved = removedShapes.pop();
            shapes.push(lastRemoved);

            while (!removedShapes.isEmpty() && removedShapes.peek().getGroup() == lastRemoved.getGroup()) {
                shapes.push(removedShapes.pop());
                repaint();
            }
        }
    }

    public void setImage(BufferedImage image) {
        graphics2D.dispose();
        setCanva(image.getWidth(), image.getHeight());
        canvas = new BufferedImage(canvaWidth, canvaHeight, BufferedImage.TYPE_INT_ARGB);
        graphics2D = canvas.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public void clear() {
        drawFrame.getCanva().shapes.removeAllElements();
        drawFrame.getCanva().removedShapes.removeAllElements();
        drawFrame.getCanva().previewShapes.removeAllElements();
        drawFrame.getCanva().repaint();
    }

    // PRIVATE METHODS

    private void canvaSize() {
        drawFrame.getCoordinateBar().getSizeText().setText(canvaWidth + " , " + canvaHeight + "px");
    }

    private void setCanva(int width, int height) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics2D = canvas.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        canvaSize();
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));
        clear();
    }

    private void getCoordinate(MouseEvent e) {
        String x = String.valueOf(e.getPoint().x);
        String y = String.valueOf(e.getPoint().y);

        drawFrame.getCoordinateBar().getCoordinateText().setText(x + " , " + y + " px");
    }



}

