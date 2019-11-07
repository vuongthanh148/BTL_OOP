package TowerDefense.GameField.GameEntity.Enemy;

import TowerDefense.GameField.GameEntity.GameTile.Tile.TileGrid;

import static Util.Artist.QuickLoad;

public class TankerEnemy extends Enemy {

    public TankerEnemy(int x, int y, TileGrid grid){
        super(x, y, grid);
        this.texture = QuickLoad("tanker_enemy.png");
        health = 800;
        speed = 100;
        reward = 8;
    }

}
