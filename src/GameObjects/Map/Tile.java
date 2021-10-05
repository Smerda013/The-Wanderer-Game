package GameObjects.Map;

public abstract class Tile {
    protected boolean stepable;

    public Tile() {

    }

    public boolean isStepable() {
        return stepable;
    }

    public abstract String Status();
}


