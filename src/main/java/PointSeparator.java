

import java.util.ArrayList;

public class PointSeparator {
    public static ArrayList<ArrayList<Pixel>> separatePoints(ArrayList<Pixel> allAvailablePixels){
        ArrayList<ArrayList<Pixel>> separadedPixelGroups = new ArrayList<ArrayList<Pixel>>();
        ArrayList<Pixel> initNode = new ArrayList<Pixel>();
        initNode.add(allAvailablePixels.get(0));
        separadedPixelGroups.add(initNode);
        /*перебираем общий пул точек*/
        for(int allAvailablePixelsIndex = 1; allAvailablePixelsIndex < allAvailablePixels.size();allAvailablePixelsIndex++) {
            //*перебераем списки "разделенные" *//
            for (int separadedPixelGroupsOuterIndex = 0; separadedPixelGroupsOuterIndex < separadedPixelGroups.size(); separadedPixelGroupsOuterIndex++ ) {
                //*перебераем точки внутри одного, конкретного листа *//*
                for (int separadedPixelGroupsInnerIndex = 0; separadedPixelGroupsInnerIndex < separadedPixelGroups.get(separadedPixelGroupsOuterIndex).size(); separadedPixelGroupsInnerIndex++) {
                    //*смотрим как далеко точки находятся друг от друга*//*
                    if(isPixelBelongsGroup(separadedPixelGroups.get(separadedPixelGroupsOuterIndex).get(separadedPixelGroupsInnerIndex),allAvailablePixels.get(allAvailablePixelsIndex)))
                   {
                        separadedPixelGroups.get(separadedPixelGroupsOuterIndex).add(allAvailablePixels.get(allAvailablePixelsIndex));
                        break;
                    }
                    //* если нет точки рядом, и это последняя точка последнего массива создаем новый контейнер для точек*//*
                    else if(separadedPixelGroupsInnerIndex == separadedPixelGroups.get(separadedPixelGroupsOuterIndex).size() - 1 && separadedPixelGroupsOuterIndex == separadedPixelGroups.size() - 1){
                        ArrayList<Pixel> newCoordinates = new ArrayList<Pixel>();
                        newCoordinates.add(allAvailablePixels.get(allAvailablePixelsIndex));
                        separadedPixelGroups.add(newCoordinates);
                        break;
                    }
                }
            }
        }
        return separadedPixelGroups;
    }
    private static boolean isPixelBelongsGroup(Pixel inner, Pixel outer){
        if(Math.abs((inner.getX() - outer.getX())) < 20 && Math.abs(inner.getY() - outer.getY()) < 20){
            return true;
        }
        else return false;
    }
}

