import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class ColorPanel extends JPanel implements ActionListener {

    private JButton blackBtn, blueBtn, greenBtn, redBtn, magentaBtn, yellowBtn, orangeBtn, brownBtn, purpleBtn, cyanBtn, grayBtn, indigoBtn, whiteBtn;
    private DrawingFrame myFrame;

    ColorPanel(DrawingFrame myFrame) {

        this.myFrame = myFrame;

        // initializing the buttons
        blackBtn = new JButton("");
        blackBtn.setSize(25, 25);
        blackBtn.setBackground(Color.BLACK);
        blackBtn.addActionListener(this);

        whiteBtn = new JButton("");
        whiteBtn.setSize(25, 25);
        whiteBtn.setBackground(Color.WHITE);
        whiteBtn.addActionListener(this);

        redBtn = new JButton("");
        redBtn.setSize(25, 25);
        redBtn.setBackground(Color.RED);
        redBtn.addActionListener(this);

        blueBtn = new JButton("");
        blueBtn.setSize(25, 25);
        blueBtn.setBackground(Color.BLUE);
        blueBtn.addActionListener(this);

        yellowBtn = new JButton("");
        yellowBtn.setSize(25, 25);
        yellowBtn.setBackground(Color.YELLOW);
        yellowBtn.addActionListener(this);

        greenBtn = new JButton("");
        greenBtn.setSize(25, 25);
        greenBtn.setBackground(Color.GREEN);
        greenBtn.addActionListener(this);

        orangeBtn = new JButton("");
        orangeBtn.setSize(25, 25);
        orangeBtn.setBackground(Color.ORANGE);
        orangeBtn.addActionListener(this);

        magentaBtn = new JButton("");
        magentaBtn.setSize(25, 25);
        magentaBtn.setBackground(Color.MAGENTA);
        magentaBtn.addActionListener(this);

        cyanBtn = new JButton("");
        cyanBtn.setSize(25, 25);
        cyanBtn.setBackground(Color.CYAN);
        cyanBtn.addActionListener(this);

        grayBtn = new JButton("");
        grayBtn.setSize(25, 25);
        grayBtn.setBackground(Color.GRAY);
        grayBtn.addActionListener(this);
        
        brownBtn = new JButton("");
        brownBtn.setSize(25, 25);
        brownBtn.setBackground(new Color(49, 14, 14));
        brownBtn.addActionListener(this);

        purpleBtn = new JButton("");
        purpleBtn.setSize(25, 25);
        purpleBtn.setBackground(new Color(163, 73, 164));
        purpleBtn.addActionListener(this);

        indigoBtn = new JButton("");
        indigoBtn.setSize(25, 25);
        indigoBtn.setBackground(new Color(163, 73, 164));
        indigoBtn.addActionListener(this);

        // adding the buttons to the panel
        add(redBtn);
        add(blueBtn);
        add(yellowBtn);
        add(greenBtn);
        add(orangeBtn);
        add(purpleBtn);
        add(magentaBtn);
        add(cyanBtn);
        add(brownBtn);
        add(indigoBtn);
        add(grayBtn);
        add(whiteBtn);
        add(blackBtn);
        
        // setting the layout
        setLayout(new GridLayout(3, 3, 5, 5));
        setBorder(new TitledBorder("Colors"));




    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        myFrame.getCanva().currentColor = btn.getBackground();
    }



}
