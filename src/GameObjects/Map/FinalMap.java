package GameObjects.Map;

import java.util.Random;

public class FinalMap extends Map {


    public FinalMap(int mapLvl, Tile oldHero) {
        floor = new Tile[10][10];
        this.cutScene = false;
        Random randomNumber = new Random();
        int counterSkeletons = 0, keyHolder = randomNumber.nextInt(2);
        int[][] matrixForMap = new int[][]
                        {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                        {0, 0, 0, 0, 0, 0, 0, 0, 3, 4},
                        {2, 0, 0, 0, 0, 0, 0, 0, 3, 4},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
        for (int i = 0; i < matrixForMap.length; i++) {
            for (int j = 0; j < matrixForMap[i].length; j++) {
                if (matrixForMap[i][j] == 0) {
                    this.floor[i][j] = new Floor();
                } else if (matrixForMap[i][j] == 1) {
                    this.floor[i][j] = new Wall();
                } else if (matrixForMap[i][j] == 2) {
                    this.floor[i][j] = oldHero;
                } else if (matrixForMap[i][j] == 3) {
                    if (counterSkeletons == keyHolder) {
                        this.floor[i][j] = new Skeleton(mapLvl, true);
                        counterSkeletons++;
                    } else {
                        this.floor[i][j] = new Skeleton(mapLvl);
                        counterSkeletons++;
                    }
                } else {
                    this.floor[i][j] = new Boss(mapLvl);
                }
            }
        }
    }
}
