package TowerDefense.GameField.GameEntity.Enemy;

import TowerDefense.GameField.GameEntity.GameTile.Tile.TileGrid;

import static Util.Drawer.QuickLoad;

public class BossEnemy extends Enemy {

    public BossEnemy(int x, int y, TileGrid grid){
        super(x, y, grid);
        this.texture = QuickLoad("boss_enemy.png");
        health = 800;
        speed = 80;
        reward = 10;
    }

}
