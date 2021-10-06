package GameObjects.Map;

import GameObjects.Map.Tile;

public class Wall extends Tile {

    public Wall() {
        stepable = false;
    }

    @Override
    public String Status() {
        return null;
    }

    @Override
    public int getCurrentHP() {
        return 0;
    }
}
