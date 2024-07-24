import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

public class App {
    public static void main(String[] args) {
        DrawingFrame frame = new DrawingFrame();
        frame.setTitle("Paint but not as great");
        frame.pack();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2 - frame.getWidth(), dim.height/2 - frame.getHeight());
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
