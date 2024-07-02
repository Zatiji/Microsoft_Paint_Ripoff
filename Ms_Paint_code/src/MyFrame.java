import javax.swing.JFrame;

public class MyFrame extends JFrame{

    private Canva canva = new Canva();

    MyFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);

        this.add(canva);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
