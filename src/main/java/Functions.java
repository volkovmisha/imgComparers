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
    private static void BorderPainterVertical (int markerStart, int markerEnd, int borderFirst, int borderSecond, int color, BufferedImage output){
        for (int marker = markerStart ;marker > markerEnd ; marker--){
            output.setRGB(marker,borderFirst,color);
            output.setRGB(marker,borderSecond,color);
        }

    }private static void BorderPainterHorizoltal (int markerStart, int markerEnd, int borderFirst, int borderSecond, int color, BufferedImage output){
        for (int marker = markerStart ;marker > markerEnd ; marker--){
            output.setRGB(borderFirst,marker,color);
            output.setRGB(borderSecond,marker,color);
        }

    }
    static BufferedImage borderPainter(ArrayList<ArrayList<Pixel>> topNode,int myRed, BufferedImage output){
        for (int i = 0; i < topNode.size(); i++) {
            int leftMost = topNode.get(i).get(0).X;
            int rightMost =  topNode.get(i).get(0).X;
            int bottomMost =  topNode.get(i).get(0).Y;
            int topMost =  topNode.get(i).get(0).Y;
            for (int j = 0; j < topNode.get(i).size(); j++) {
                /* поиск самых больших значений */
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
            Functions.BorderPainterVertical(leftMost,rightMost,topMost,bottomMost,myRed,output);
            Functions.BorderPainterHorizoltal(topMost,bottomMost,leftMost,rightMost,myRed,output);
        }
        return output;
    }
    static ArrayList<ArrayList<Pixel>> pointSeparator(ArrayList<Pixel> merged){

        ArrayList<ArrayList<Pixel>> topNode = new ArrayList<ArrayList<Pixel>>();
        ArrayList<Pixel> initNode = new ArrayList<Pixel>();
        initNode.add(merged.get(0));
        topNode.add(initNode);
        for(int mutualArray = 1; mutualArray < merged.size();mutualArray++) {
            for (int divdedOuter = 0; divdedOuter < topNode.size(); divdedOuter++ ) {
                for (int diviedInner = 0; diviedInner < topNode.get(divdedOuter).size(); diviedInner++) {
                    /*смотрим как далеко точки находятся друг от друга*/
                    if(Math.abs(topNode.get(divdedOuter).get(diviedInner).X - merged.get(mutualArray).X) < 20 && Math.abs(topNode.get(divdedOuter).get(diviedInner).Y - merged.get(mutualArray).Y) < 20){
                        topNode.get(divdedOuter).add(merged.get(mutualArray));
                        break;
                    }
                    /* если нет точки рядом, и это последняя точка последнего массива создаем новый контейнер для точек*/
                    else if(diviedInner == topNode.get(divdedOuter).size() - 1 && divdedOuter == topNode.size() - 1){
                        ArrayList<Pixel> newCoordinates = new ArrayList<Pixel>();
                        newCoordinates.add(merged.get(mutualArray));
                        topNode.add(newCoordinates);
                        break;
                    }
                }
            }
        }
        return topNode;
    }
}