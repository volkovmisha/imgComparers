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
            for (int i = widthA - 1; i > 0; i--){
                for(int j = heightA - 1; j > 0; j--){
                    int pA = images.imageA.getRGB(i,j);
                    int pB = images.imageB.getRGB(i,j);
                    if((Math.abs(pA >> 24 & 0xff) - (pB >> 24 & 0xff)) + (Math.abs (pA >> 16 & 0xff) - (pB >> 16 & 0xff)) +
                            (Math.abs (pA >> 8 & 0xff) - (pB >> 8 & 0xff)) + (Math.abs (pA & 0xff) - (pB & 0xff)) > 102){
                        differences.add(new Pixel(i,j));
                    }
                }
            }

          /*  int leftMost = differences.get(0).X;
            int rightMost = differences.get(0).X;
            int bottomMost = differences.get(0).Y;
            int topMost = differences.get(0).Y;*/

            for(int i = 1; i < differences.size();i++) {
                /* проверка что точки рядом*/
                if(Math.abs(differences.get(i-1).X - differences.get(i).X) < 20 &&
                        Math.abs(differences.get(i-1).Y - differences.get(i).Y) < 20
                        ){
                   // System.out.print("pew");
                }
                System.out.println(differences.get(i).X + " " + differences.get(i).Y);
            }
              /*  if(leftMost < differences.get(i).X){
                    leftMost = differences.get(i).X;
                }
                if(rightMost > differences.get(i).X){
                    rightMost = differences.get(i).X;
                }
                if(topMost < differences.get(i).Y){
                    topMost = differences.get(i).Y;
                }
                if(bottomMost > differences.get(i).Y){
                    bottomMost = differences.get(i).Y;
                }
            }
            System.out.println("leftMost" + leftMost + " rightMost" + rightMost + "\n topMost" + topMost + " bottomMost" + bottomMost);*/
            BufferedImage output = images.imageB;


            Color red =  new Color(255, 0, 8);
            int myRed = red.getRGB();
/*
            leftMost-=50; rightMost+=50; topMost-=50; bottomMost+=50;
            for (int horizontalRed = leftMost; horizontalRed < rightMost; horizontalRed++ ){
                output.setRGB(horizontalRed,topMost,myRed);
                output.setRGB(horizontalRed,bottomMost,myRed);
            }
            for (int verticalRed = bottomMost;verticalRed > topMost; verticalRed--){
                output.setRGB(verticalRed,leftMost,myRed);
                output.setRGB(verticalRed,rightMost,myRed);
            } */
            for(int i = 0; i < differences.size();i++) {
                output.setRGB(differences.get(i).X,differences.get(i).Y,myRed);
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




