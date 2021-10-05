package GameObjects.Map;


public class Map {
    Tile[][] floor;

    public Map() {
        floor = new Tile[10][10];
        int[][] matrixForMap = new int[][]
                {{2, 0, 0, 1, 0, 0, 0, 0, 0, 4},
                {0, 0, 0, 1, 0, 1, 0, 1, 1, 0},
                {0, 1, 1, 1, 0, 1, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 1, 1, 1, 1, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 3, 0},
                {0, 1, 0, 1, 0, 1, 1, 0, 1, 0},
                {0, 0, 3, 0, 0, 1, 1, 0, 1, 0},
                {0, 1, 1, 1, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 3, 1, 1, 0, 0, 0}};

        for (int i = 0; i < matrixForMap.length; i++) {
            for (int j = 0; j < matrixForMap[i].length; j++) {
                if (matrixForMap[i][j] == 0) {
                    this.floor[i][j] = new Floor();
                } else if (matrixForMap[i][j] == 1){
                    this.floor[i][j] = new Wall();
                } else if (matrixForMap[i][j] == 2){
                    this.floor[i][j] = new Hero();
                } else if (matrixForMap[i][j] == 3){
                    this.floor[i][j] = new Skeleton();
                } else {
                    this.floor[i][j] = new Boss();
                }
            }

        }

    }
    public Tile get(int i, int j) {
        return this.floor[i][j];
    }

    public void moveUp(int i, int j){
       this.floor[i-1][j] = this.floor[i][j];
        this.floor[i][j] = new Floor();
    }

    public void moveDown(int i, int j){
      this.floor[i+1][j] = this.floor[i][j];
        this.floor[i][j] = new Floor();
    }
    public void moveLeft(int i, int j){
      this.floor[i][j-1] = this.floor[i][j];
        this.floor[i][j] = new Floor();
    }
    public void moveRight(int i, int j){
        this.floor[i][j+1] = this.floor[i][j];
        this.floor[i][j] = new Floor();
    }

}

