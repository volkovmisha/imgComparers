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

    static ArrayList<ArrayList<Pixel>> pointSeparator(ArrayList<Pixel> mutalList){

        ArrayList<ArrayList<Pixel>> dividedList = new ArrayList<ArrayList<Pixel>>();
        ArrayList<Pixel> initNode = new ArrayList<Pixel>();
        initNode.add(mutalList.get(0));
        dividedList.add(initNode);
        for(int mutualListPixelIndex = 1; mutualListPixelIndex < mutalList.size();mutualListPixelIndex++) {
            for (int divdedOuterIndex = 0; divdedOuterIndex < dividedList.size(); divdedOuterIndex++ ) {
                for (int diviedInnerIndex = 0; diviedInnerIndex < dividedList.get(divdedOuterIndex).size(); diviedInnerIndex++) {
                    /*смотрим как далеко точки находятся друг от друга*/
                    if(Math.abs(dividedList.get(divdedOuterIndex).get(diviedInnerIndex).X - mutalList.get(mutualListPixelIndex).X) < 20 && Math.abs(dividedList.get(divdedOuterIndex).get(diviedInnerIndex).Y - mutalList.get(mutualListPixelIndex).Y) < 20){
                        dividedList.get(divdedOuterIndex).add(mutalList.get(mutualListPixelIndex));
                        break;
                    }
                    /* если нет точки рядом, и это последняя точка последнего массива создаем новый контейнер для точек*/
                    else if(diviedInnerIndex == dividedList.get(divdedOuterIndex).size() - 1 && divdedOuterIndex == dividedList.size() - 1){
                        ArrayList<Pixel> newCoordinates = new ArrayList<Pixel>();
                        newCoordinates.add(mutalList.get(mutualListPixelIndex));
                        dividedList.add(newCoordinates);
                        break;
                    }
                }
            }
        }
        return dividedList;
    }
}