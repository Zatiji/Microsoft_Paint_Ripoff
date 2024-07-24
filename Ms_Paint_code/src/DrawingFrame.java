import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;


public class DrawingFrame extends JFrame {
    private JPanel contentPanel;
    private JToolBar mainToolBar, menuBarPaint;
    private CoordinateBar coordinateBar;
    private DrawAlgorithm canva;
    private JScrollPane scrollPane;

    private int canvaWidth = 1100;
    private int canvaHeight = 600;

    DrawingFrame() {
        // Content panel
        contentPanel = new JPanel();
        contentPanel.setLayout(null);

        // toolbars
        menuBarPaint = new MenuBarPaint(this).getMenuBar();
        coordinateBar = new CoordinateBar();

        // creating the canva
        canva = new DrawAlgorithm(this, canvaWidth, canvaHeight);

        mainToolBar = new MainToolBar(this);

        // set the scrollbar
        scrollPane = new JScrollPane();
        scrollPane.setLocation(5, 5);
        scrollPane.setViewportView(canva);
        scrollPane.setSize(canvaWidth, canvaHeight);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // putting the scroll bar in the content panel
        contentPanel.add(scrollPane);
        contentPanel.setBackground(new Color(145, 179, 203));

        // adding the elements in the frame
        add(menuBarPaint, BorderLayout.NORTH);
        add(coordinateBar, BorderLayout.SOUTH);
        add(mainToolBar, BorderLayout.EAST);
        add(contentPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public CoordinateBar getCoordinateBar() {
        return coordinateBar;
    }

    public JToolBar getMainToolBar() {
        return mainToolBar;
    }

    public DrawAlgorithm getCanva() {
        return canva;
    }
 
    

}

