import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class CoordinateBar extends JToolBar{
    private JLabel coordinateText, sizeText;
    private Separator separator;

    CoordinateBar() {
        separator = new Separator();
        add(separator);

        JLabel coordinateLabel = new JLabel("Coord :");
        add(coordinateLabel);

        separator = new Separator();
        add(separator);

        coordinateText = new JLabel(" 0 , 0 px");
        add(coordinateText);

        separator = new Separator();
        add(separator);

        JLabel sizeLabel = new JLabel("Size :");
        add(sizeLabel);

        separator = new Separator();
        add(separator);

        sizeText = new JLabel(" 0 x 0 ");
        add(sizeText);

        setFloatable(false);
        setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
    }

    public JLabel getCoordinateText() {
        return coordinateText;
    }
}
