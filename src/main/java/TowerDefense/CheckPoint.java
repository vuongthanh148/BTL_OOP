package TowerDefense;

public class CheckPoint {
    private Tile tile;
    private int xDir, yDir;

    public CheckPoint(Tile tile, int xDir, int yDir){
        this.tile = tile;
        this.xDir = xDir;
        this. yDir = yDir;

    }

    public Tile getTile() {
        return tile;
    }

    public int getxDir() {
        return xDir;
    }

    public int getyDir() {
        return yDir;
    }

}
