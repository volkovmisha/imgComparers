import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class Main {
    private static class FrameShower implements Runnable {
        final Frame frame;

        FrameShower(Frame frame) {
            this.frame = frame;
        }
        public void run() {
            frame.show();
        }
    }
    public static void main(String[] args) {
        ImagesInit images = new ImagesInit();

        int widthA = images.imageA.getWidth();
        int heightA = images.imageA.getHeight();

        /*проверяем размер и создаем переменные для хранения координат.*/
        ArrayList<Pixel> differences = Functions.differenceFinder(images, widthA, heightA);

        /*вызываем метод раскладывающий пиксели на группы в зависимости от положения*/
        ArrayList<ArrayList<Pixel>> topNode = Functions.pointSeparator(differences);
        Color red = new Color(255, 0, 8);
        int myRed = red.getRGB();

        /* рисуем границы   */
        BufferedImage output = Functions.borderPainter(topNode, myRed, images.imageB);

        // создаем экран
        Icon icon = new ImageIcon(output);
        JLabel label = new JLabel(icon);
        JFrame frame = new JFrame("Sharp Image");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.pack();
        // выводим
        Runnable runner = new FrameShower(frame);
        EventQueue.invokeLater(runner);
    }
}






