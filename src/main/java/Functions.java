import java.awt.image.BufferedImage;
import java.util.ArrayList;

class Functions {
    static ArrayList<Pixel> differenceFinder(ImagesInit images, int widthA, int heightA) {
        ArrayList<Pixel> differences = new ArrayList<Pixel>();
        if (widthA == images.imageB.getWidth() && heightA == images.imageB.getHeight()) {
            System.out.println("size not different, go deeper");
            for (int width = widthA - 1; width > 0; width--) {
                for (int height = heightA - 1; height > 0; height--) {
                    int pixelA = images.imageA.getRGB(width, height);
                    int pixelB = images.imageB.getRGB(width, height);
                    /*проверяем на скольк оотличаются цвета, допустимый люфт не более 10%*/
                    if ((Math.abs(pixelA >> 24 & 0xff) - (pixelB >> 24 & 0xff)) + (Math.abs(pixelA >> 16 & 0xff) - (pixelB >> 16 & 0xff)) +
                            (Math.abs(pixelA >> 8 & 0xff) - (pixelB >> 8 & 0xff)) + (Math.abs(pixelA & 0xff) - (pixelB & 0xff)) > 102) {
                        differences.add(new Pixel(width, height));
                    }
                }
            }
            return differences;
        }
        else {
            System.out.println("images have different sizes, bye");
            return null;
        }
    }


}