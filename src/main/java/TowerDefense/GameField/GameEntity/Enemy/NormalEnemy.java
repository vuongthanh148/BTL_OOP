package TowerDefense.GameField.GameEntity.Enemy;

import TowerDefense.GameField.GameEntity.GameTile.Tile.TileGrid;

import static Util.Drawer.QuickLoad;

public class NormalEnemy extends Enemy {

    public NormalEnemy(int x, int y, TileGrid grid){
        super(x, y, grid);
        this.texture = QuickLoad("normal_enemy.png");
        health = 500;
        speed = 100;
        reward = 5;
    }

}
