import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class Main {
    private static class FrameShower implements Runnable {
        final Frame frame;

        public FrameShower(Frame frame) {
            this.frame = frame;
        }

        public void run() {
            frame.show();
        }
    }
    public static void main(String[] args) {
        ImagesInit images = new ImagesInit();
    /*проверяем размер и создаем переменные для хранения координат.*/
        int widthA = images.imageA.getWidth();
        int heightA = images.imageA.getHeight();
        ArrayList<Pixel> differences = new ArrayList<Pixel>();
        if (widthA == images.imageB.getWidth() && heightA == images.imageB.getHeight()){
            System.out.println("size not different, go deeper");
            for (int i = widthA - 1; i > 0; i--) {
                for (int j = heightA - 1; j > 0; j--) {
                    int pA = images.imageA.getRGB(i, j);
                    int pB = images.imageB.getRGB(i, j);
                    if ((Math.abs(pA >> 24 & 0xff) - (pB >> 24 & 0xff)) + (Math.abs(pA >> 16 & 0xff) - (pB >> 16 & 0xff)) +
                            (Math.abs(pA >> 8 & 0xff) - (pB >> 8 & 0xff)) + (Math.abs(pA & 0xff) - (pB & 0xff)) > 102) {
                        differences.add(new Pixel(i, j));
                    }
                }
            }
            ArrayList<ArrayList<Pixel>> topNode = new ArrayList<ArrayList<Pixel>>();
            ArrayList<Pixel> initNode = new ArrayList<Pixel>();
            initNode.add(differences.get(0));
            topNode.add(initNode);
            for(int i = 1; i < differences.size();i++) {
                for (int j = 0; j < topNode.size(); j++ ) {
                    for (int k = 0; k < topNode.get(j).size(); k++) {
                        if(Math.abs(topNode.get(j).get(k).X - differences.get(i).X) < 20 && Math.abs(topNode.get(j).get(k).Y - differences.get(i).Y) < 20){
                            topNode.get(j).add(differences.get(i));
                            break;
                        }
                        else if(k == topNode.get(j).size() - 1 && j == topNode.size() - 1){
                            ArrayList<Pixel> newCoordinates = new ArrayList<Pixel>();
                            newCoordinates.add(differences.get(i));
                            topNode.add(newCoordinates);
                            break;
                        }
                    }
                }
            }
            BufferedImage output = images.imageB;
            Color red =  new Color(255, 0, 8);
            int myRed = red.getRGB();
            for (int i = 0; i < topNode.size(); i++) {
                int leftMost = topNode.get(i).get(0).X;
                int rightMost =  topNode.get(i).get(0).X;
                int bottomMost =  topNode.get(i).get(0).Y;
                int topMost =  topNode.get(i).get(0).Y;
                for (int j = 0; j < topNode.get(i).size(); j++) {
                    if(leftMost < topNode.get(i).get(j).X){
                        leftMost = topNode.get(i).get(j).X;
                    }
                    if(rightMost > topNode.get(i).get(j).X){
                        rightMost = topNode.get(i).get(j).X;
                    }
                    if(topMost < topNode.get(i).get(j).Y){
                        topMost = topNode.get(i).get(j).Y;
                    }
                    if(bottomMost > topNode.get(i).get(j).Y){
                        bottomMost = topNode.get(i).get(j).Y;
                    }
                }
                System.out.println("leftMost " + leftMost + " rightMost " + rightMost + " topMost " + topMost + " bottomMost " + bottomMost);
                for (int markerHorizontal = leftMost ;markerHorizontal > rightMost ; markerHorizontal--){
                    output.setRGB(markerHorizontal,bottomMost,myRed);
                    output.setRGB(markerHorizontal,topMost,myRed);
                }
                for (int markerVertical = topMost ;markerVertical > rightMost ; markerVertical--){
                    System.out.println("leftMost" + leftMost + "rightMost" + rightMost);
                    output.setRGB(leftMost,markerVertical,myRed);
                    output.setRGB(rightMost,markerVertical,myRed);
                }
            }
            // Make screen
            Icon icon = new ImageIcon(output);
            JLabel label = new JLabel(icon);
            JFrame frame = new JFrame("Sharp Image");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(label, BorderLayout.CENTER);
            frame.pack();
            // Show
            Runnable runner = new FrameShower(frame);
            EventQueue.invokeLater(runner);
        }
        else {
            System.out.println("images have different sizes, bye");
        }
    }
}





