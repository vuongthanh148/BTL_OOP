package TowerDefense;

import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static Util.Artist.QuickLoad;

public class TankerEnemy extends Enemy {

    public TankerEnemy(int x, int y, TileGrid grid){
        super(x, y, grid);
        this.texture = QuickLoad("tanker_enemy.png");
        health = 100;
        speed = 100;
    }

}
