import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagesInit extends JPanel {

    BufferedImage imageA;
    BufferedImage imageB;

    public ImagesInit() {
        try {
            imageA = ImageIO.read(new File("img/image1.png"));
            imageB = ImageIO.read(new File("img/image2.png"));
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(imageB, 0, 0, null);

    }


}
