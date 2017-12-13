import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BorderPainter {

    static BufferedImage paintBorders(ArrayList<ArrayList<Pixel>> topNode, int myRed, BufferedImage output){
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
            borderPainterVertical(leftMost,rightMost,topMost,bottomMost,myRed,output);
            borderPainterHorizoltal(topMost,bottomMost,leftMost,rightMost,myRed,output);
        }
        return output;
    }
    private static void borderPainterVertical(int markerStart, int markerEnd, int borderFirst, int borderSecond, int color, BufferedImage output){
        for (int marker = markerStart ;marker > markerEnd ; marker--){
            output.setRGB(marker,borderFirst,color);
            output.setRGB(marker,borderSecond,color);
        }

    }private static void borderPainterHorizoltal(int markerStart, int markerEnd, int borderFirst, int borderSecond, int color, BufferedImage output){
        for (int marker = markerStart ;marker > markerEnd ; marker--){
            output.setRGB(borderFirst,marker,color);
            output.setRGB(borderSecond,marker,color);
        }

    }
}
