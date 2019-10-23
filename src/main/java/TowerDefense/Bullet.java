package TowerDefense;

import org.newdawn.slick.opengl.Texture;

import static Util.Clock.*;
import static Util.Artist.*;

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
            DrawQuadTex(texture, x,y,32,32);
    }

    private void calculateDirection(){
        float totalVelocity = 1.0f;
        float xDistanceFromTarget = Math.abs(target.getX() - x);
        float yDistanceFromTarget = Math.abs(target.getY() - y);
        float totalDistance = xDistanceFromTarget + yDistanceFromTarget;
        xVelocity = xDistanceFromTarget/totalDistance;
        yVelocity = totalVelocity - xVelocity;
        if(target.getX() < x) xVelocity*=-1;
        if(target.getY() < y) yVelocity*=-1;
    }
}
