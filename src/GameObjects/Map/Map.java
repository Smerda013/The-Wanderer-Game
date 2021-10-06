package GameObjects.Map;


import java.util.Random;

public class Map {
    Tile[][] floor;

    public Map(int mapLvl) {
        floor = new Tile[10][10];
        Random randomNumber = new Random();
        int counterSkeletons = 0, keyHolder = randomNumber.nextInt(3);
        int[][] matrixForMap = new int[][]
                {{2,0, 0, 1, 0, 0, 0, 0, 0, 4},
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
                    if (counterSkeletons == keyHolder){
                        this.floor[i][j] = new Skeleton(mapLvl,true);
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

    public Map(int mapLvl, Tile oldHero) {
        floor = new Tile[10][10];
        Random randomNumber = new Random();
        int counterSkeletons = 0, keyHolder = randomNumber.nextInt(3);
        int[][] matrixForMap = new int[][]
                {{2,0, 0, 1, 0, 0, 0, 0, 0, 4},
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
                    if (mapLvl == 1) {
                        this.floor[i][j] = new Hero();
                    } else {
                        this.floor[i][j] = oldHero;
                    }
                } else if (matrixForMap[i][j] == 3){
                    if (counterSkeletons == keyHolder){
                        this.floor[i][j] = new Skeleton(mapLvl,true);
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

    public Tile saveHero(int y, int x){
        Tile oldHero = this.floor[y][x];
        return oldHero;
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

    public void enemyDeath(int y, int x){
        this.floor[y][x] = new Floor();
    }

    public void heroDeath(int y, int x) {this.floor[y][x] = new Floor();}

    public boolean isHeroDeath (){
        for (int i = 0; i < this.floor.length; i++) {
            for (int j = 0; j < this.floor[i].length; j++) {
                if (this.floor[i][j] instanceof Hero){
                    return false;
                }
            }
        }
        return true;
    }

    public int heroY(){
        for (int i = 0; i < this.floor.length; i++) {
            for (int j = 0; j < this.floor[i].length; j++) {
                if (this.floor[i][j] instanceof Hero){
                    return i;
                }
            }
        }
        return 0;
    }

    public int heroX(){
        for (int i = 0; i < this.floor.length; i++) {
            for (int j = 0; j < this.floor[i].length; j++) {
                if (this.floor[i][j] instanceof Hero){
                    return j;
                }
            }
        }
        return 0;
    }

    public boolean isBossDeath (){
        for (int i = 0; i < this.floor.length; i++) {
            for (int j = 0; j < this.floor[i].length; j++) {
                if (this.floor[i][j] instanceof Boss){
                    return false;
                }
            }
        }
        return true;
    }

    public Hero getHero(){
        Hero hero;
        for (int i = 0; i < this.floor.length; i++) {
            for (int j = 0; j < this.floor[i].length; j++) {
                if (this.floor[i][j] instanceof Hero){
                    return hero =(Hero) this.floor[i][j];
                }
            }
        }
        return null;
    }

}

