import java.awt.image.BufferedImage;
import java.util.ArrayList;

class Functions {
    static ArrayList<Pixel> differnceFinder(ImagesInit images, int widthA, int heightA) {
        ArrayList<Pixel> differences = new ArrayList<Pixel>();
        if (widthA == images.imageB.getWidth() && heightA == images.imageB.getHeight()) {
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
            return differences;
        }
        else {
            System.out.println("images have different sizes, bye");
            return null;
        }
    }
    static void BorderPainterVertical (int markerStart, int markerEnd, int borderFirst, int borderSecond, int color, BufferedImage output){
        for (int marker = markerStart ;marker > markerEnd ; marker--){
            output.setRGB(marker,borderFirst,color);
            output.setRGB(marker,borderSecond,color);
        }

    }
    static void BorderPainterHorizoltal (int markerStart, int markerEnd, int borderFirst, int borderSecond, int color, BufferedImage output){
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
                /* */
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
    static ArrayList<ArrayList<Pixel>> pointSepapator(ArrayList<Pixel> merged){

        ArrayList<ArrayList<Pixel>> topNode = new ArrayList<ArrayList<Pixel>>();
        ArrayList<Pixel> initNode = new ArrayList<Pixel>();
        initNode.add(merged.get(0));
        topNode.add(initNode);
        for(int i = 1; i < merged.size();i++) {
            for (int j = 0; j < topNode.size(); j++ ) {
                for (int k = 0; k < topNode.get(j).size(); k++) {
                    if(Math.abs(topNode.get(j).get(k).X - merged.get(i).X) < 20 && Math.abs(topNode.get(j).get(k).Y - merged.get(i).Y) < 20){
                        topNode.get(j).add(merged.get(i));
                        break;
                    }
                    else if(k == topNode.get(j).size() - 1 && j == topNode.size() - 1){
                        ArrayList<Pixel> newCoordinates = new ArrayList<Pixel>();
                        newCoordinates.add(merged.get(i));
                        topNode.add(newCoordinates);
                        break;
                    }
                }
            }
        }
        return topNode;
    }
}