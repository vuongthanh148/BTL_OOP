package TowerDefense;

import java.util.ArrayList;

public class TowerSpecial extends Tower {
    public TowerSpecial(TowerType type, Tile startTile, ArrayList<Enemy> enemies) {
        super(type, startTile, enemies);
    }
    public void Shoot(){
        super.Shoot();
        super.getTarget().setSpeed(4);
    }
}
