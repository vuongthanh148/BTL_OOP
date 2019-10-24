package TowerDefense;

import org.newdawn.slick.opengl.Texture;

import static Util.Clock.*;
import static Util.Artist.*;
import static sun.dc.pr.Rasterizer.TILE_SIZE;

public class Bullet {
    private Texture texture;
    float x,y,speed, xVelocity, yVelocity;
    int damage;
    private Enemy target;

    public Bullet(Texture texture, Enemy target, float x, float y, float speed, int damage) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
        this.target = target;
        this.xVelocity = 0f;
        this.yVelocity = 0f;
        calculateDirection();
    }

    public void update(){
        x += xVelocity*Delta()*speed;
        y += yVelocity*Delta()*speed;
        draw();
    }

    public void draw(){
            DrawQuadTex(texture, x,y,TILE_SIZE,TILE_SIZE);
    }

    private void calculateDirection(){
        float totalVelocity = 1.0f;
        float xDistanceFromTarget = Math.abs(target.getX() - x + TILE_SIZE / 2); // Aiming to center of enemy
        float yDistanceFromTarget = Math.abs(target.getY() - y + TILE_SIZE / 2); // Aiming to center of enemy
        float totalDistance = xDistanceFromTarget + yDistanceFromTarget;
        xVelocity = xDistanceFromTarget/totalDistance;
        yVelocity = totalVelocity - xVelocity;
        if(target.getX() < x) xVelocity*=-1;
        if(target.getY() < y) yVelocity*=-1;
    }
}
