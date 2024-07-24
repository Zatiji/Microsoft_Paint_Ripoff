import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuBarPaint extends JToolBar implements ActionListener {

    private JMenuItem openFile;
    private JFileChooser fileChooser;
    private DrawingFrame drawFrame;
    private File file;
    private JMenuItem save;

    MenuBarPaint(DrawingFrame drawFrame) {
        this.drawFrame = drawFrame;

        fileChooser = new JFileChooser(new File("."));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image", "jpg", "png", "jpeg"));

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");

        // Menu items
        JMenuItem newPage = new JMenuItem("New Page");

        openFile = new JMenuItem("Open File");
        openFile.addActionListener(this);

        save = new JMenuItem("save");
        save.addActionListener(this);

        // adding the menu items
        menu.add(newPage);
        menu.add(openFile);
        menu.add(save);

        menuBar.add(menu);
        setFloatable(false);
    }

    public JToolBar getMenuBar() {
        return this;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem item = (JMenuItem) e.getSource();

        if (item == openFile) {
            if (fileChooser.showOpenDialog(drawFrame) == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                System.out.println(file.toString());
                openSelectedFile(file);
            }
        } else if (item == save) {
            try {
                file = new File(fileChooser.getSelectedFile() + ".png");
                if (fileChooser.showOpenDialog(drawFrame) == JFileChooser.APPROVE_OPTION) {
                    saveImage(file);
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    private void saveImage(File f) throws IOException {
        BufferedImage bi = makePanel(drawFrame.getCanva());
        ImageIO.write(bi, "", file);
    }

    private BufferedImage makePanel(JPanel panel) {
        int w = panel.getWidth();
        int h = panel.getHeight();
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        panel.print(g);
        return bi;
    }

    private void openSelectedFile(File file) {
        try {
            drawFrame.getCanva().setImage(ImageIO.read(file));
            Dimension dimension = new Dimension(ImageIO.read(file).getWidth(), ImageIO.read(file).getHeight());
            setDimension(dimension.width, dimension.height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setDimension(int width, int height) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        if (height > dim.height - 160 && width > dim.width) {
            drawFrame.getScrollPane().setSize(dim.width-300, dim.height - 160);
        } else if (width > dim.width - 300) {
            drawFrame.getScrollPane().setSize(dim.width-300, height);
        } else if (height > dim.height - 160) {
            drawFrame.getScrollPane().setSize(width, dim.height - 160);
        } else  {
            drawFrame.getScrollPane().setSize(width, height);
        }
    }
}
