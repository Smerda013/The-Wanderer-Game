package GameObjects.Map;


public class Map {
    Tile[][] floor;

    public Map() {
        floor = new Tile[10][10];
        int[][] matrixForMap = new int[][]
                {{0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 1, 1, 0},
                {0, 1, 1, 1, 0, 1, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 1, 1, 1, 1, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 1, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 1, 0},
                {0, 1, 1, 1, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 0, 1, 1, 0, 0, 0}};

        for (int i = 0; i < matrixForMap.length; i++) {
            for (int j = 0; j < matrixForMap[i].length; j++) {
                if (matrixForMap[i][j] == 0) {
                    this.floor[i][j] = new Floor();
                } else {
                    this.floor[i][j] = new Wall();
                }
            }

        }

    }
    public Tile get(int i, int j) {
        return this.floor[i][j];
    }
}
