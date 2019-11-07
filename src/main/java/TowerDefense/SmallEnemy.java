package TowerDefense;

import static Util.Artist.QuickLoad;

public class SmallEnemy extends Enemy {

    public SmallEnemy(int x, int y, TileGrid grid){
        super(x, y, grid);
        this.texture = QuickLoad("small_enemy.png");
        health = 300;
        speed = 200;
        reward = 2;
    }

}
