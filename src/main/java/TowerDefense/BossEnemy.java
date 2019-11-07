package TowerDefense;

import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static Util.Artist.QuickLoad;

public class BossEnemy extends Enemy {

    public BossEnemy(int x, int y, TileGrid grid){
        super(x, y, grid);
        this.texture = QuickLoad("boss_enemy.png");
        health = 800;
        speed = 80;
        reward = 10;
    }

}
