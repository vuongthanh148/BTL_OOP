package TowerDefense;

import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static Util.Artist.QuickLoad;

public class NormalEnemy extends Enemy {

    public NormalEnemy(int x, int y, TileGrid grid){
        super(x, y, grid);
        this.texture = QuickLoad("normal_enemy.png");
        health = 10;
        speed = 100;
    }

}