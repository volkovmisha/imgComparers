import java.util.ArrayList;

public class PointSeparator {
    static ArrayList<ArrayList<Pixel>> separatePoints(ArrayList<Pixel> mutalList){
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
