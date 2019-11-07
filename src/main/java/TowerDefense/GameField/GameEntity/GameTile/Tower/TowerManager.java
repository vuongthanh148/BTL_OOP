package TowerDefense.GameField.GameEntity.GameTile.Tower;

import TowerDefense.GameField.GameEntity.Enemy.Enemy;
import TowerDefense.GameField.GameEntity.GameTile.Tile.Tile;

import java.util.ArrayList;

public class TowerManager extends Tower {
    public TowerManager(TowerType type, Tile startTile, ArrayList<Enemy> enemies){
        super(type,startTile,enemies);
    }
}
