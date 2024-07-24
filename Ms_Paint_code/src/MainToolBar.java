import java.awt.BasicStroke;
import java.awt.Color;
//mport java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class MainToolBar extends JToolBar {
    private JLabel strokeLabel;

    private DrawingFrame drawFrame; 

    MainToolBar(DrawingFrame drawFrame) {
        this.drawFrame = drawFrame;

        // on initie les objets
        ColorPanel colorPanel = new ColorPanel(drawFrame);
        DrawingToolsPanel drawingTools = new DrawingToolsPanel();
        DrawingShapesPanel drawingShapes = new DrawingShapesPanel();
        UndoRedoPanel undoRedo = new UndoRedoPanel();

        add(undoRedo);
        add(drawingTools);
        add(new ManageStroke());
        add(drawingShapes);
        add(colorPanel);
        add(new Separator());

        setBackground(Color.WHITE);
        setLayout(new GridLayout(6, 1));
        setFloatable(false);
    }

    // initialising the classes for the main tool bar
    class DrawingToolsPanel extends JPanel {

        DrawingToolsPanel() {

            // initialising the buttons
            JButton pencilBtn = new JButton("Pencil");
            pencilBtn.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    drawFrame.getCanva().active_tool = 0;
                }
            });

            JButton eraserBtn = new JButton("Eraser");
            eraserBtn.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    drawFrame.getCanva().active_tool = 4;
                }
            });

            // add the tools to the panel
            add(pencilBtn);
            add(eraserBtn);
            setBorder(new TitledBorder("Tools"));
        }


    }

    class DrawingShapesPanel extends JPanel {

        DrawingShapesPanel() {
            JButton circleBtn = new JButton("CIRCLE");
            // circleBtn.setPreferredSize(new Dimension(30, 30));
            circleBtn.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    drawFrame.getCanva().active_tool = 3;
                }
            });

            JButton rectangleBtn = new JButton("RECTANGLE");
            // rectangleBtn.setPreferredSize(new Dimension(30, 30));
            rectangleBtn.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    drawFrame.getCanva().active_tool = 2;
                }
            });

            JButton lineBtn = new JButton("LINE");
            // lineBtn.setPreferredSize(new Dimension(30, 30));
            lineBtn.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    drawFrame.getCanva().active_tool = 1;
                }
            });

            add(circleBtn);
            add(rectangleBtn);
            add(lineBtn);

            setBorder(new TitledBorder("Shapes"));
        }
    }

    class ManageStroke extends JPanel {
        ManageStroke() {
            JSlider chooseLineStroke = new JSlider(HORIZONTAL, 1, 15, 2);

            strokeLabel = new JLabel("Size: " + chooseLineStroke.getValue(), SwingConstants.CENTER);
            drawFrame.getCanva().strokeSize = new BasicStroke((float) chooseLineStroke.getValue());
            chooseLineStroke.addChangeListener(e -> {
                strokeLabel.setText("Size: " + chooseLineStroke.getValue());
                drawFrame.getCanva().strokeSize = new BasicStroke((float) chooseLineStroke.getValue());
            });

            add(new Separator());
            add(strokeLabel);
            add(chooseLineStroke);
            add(new Separator());
            setLayout(new GridLayout(4, 1));
        }
    }

    class UndoRedoPanel extends JPanel {
        UndoRedoPanel() {
            JPanel panel = new JPanel();

            JButton undoBtn = new JButton("undo");
            undoBtn.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    drawFrame.getCanva().undo();
                }
            });

            JButton redoBtn = new JButton("redo");
            redoBtn.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    drawFrame.getCanva().redo();
                }
            });

            panel.add(undoBtn);
            panel.add(redoBtn);

            JPanel panelClear = new JPanel();
            JButton clearBtn = new JButton("clear");
            clearBtn.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    drawFrame.getCanva().clearCanva();
                }
            });
            panelClear.add(clearBtn);
            
            add(panel);
            add(panelClear);
            setLayout(new GridLayout(2,1));
        }

    }
}
