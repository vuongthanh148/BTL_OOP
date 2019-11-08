package TowerDefense.GameField.GameEntity.GameTile.Tower;

import TowerDefense.GameField.GameEntity.Enemy.Enemy;
import TowerDefense.GameField.GameEntity.GameTile.Tile.Tile;

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
