package GameObjects.Map;

public class Floor extends Tile {


    public Floor() {
        stepable = true;
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
