package TowerDefense;

import org.newdawn.slick.opengl.Texture;

import static Util.Clock.*;
import static Util.Artist.*;

public class Bullet {
    private Texture texture;
    float x,y,speed;
    int damage;


    public Bullet(Texture texture, float x, float y, float speed, int damage) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
    }

    public void Update(){
        x += Delta()*speed;
        draw();
    }

    public void draw(){
            DrawQuadTex(texture, x,y,32,32);
    }
}
