package TowerDefense;

import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static Util.Artist.QuickLoad;

public class BossEnemy extends Enemy {

    public BossEnemy(int x, int y, TileGrid grid){
        super(x, y, grid);
        this.texture = QuickLoad("enemy.png");
        health = 10;
        speed = 100;
    }

}
