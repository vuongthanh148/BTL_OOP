package TowerDefense;

import Util.Artist;
import org.lwjgl.Sys;
import org.newdawn.slick.opengl.Texture;

import static TowerDefense.Game.TILE_SIZE;
import static Util.Artist.*;
import static Util.Clock.*;

public class Bullet {
    private Texture texture;
    float x,y,speed, width, height, xVelocity, yVelocity;
    int damage;
    private Enemy target;
    private boolean alive;

    public Bullet(Texture texture, Enemy target, float x, float y, float width, float height, float speed, int damage) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.damage = damage;
        this.target = target;
        this.alive = true;
        this.xVelocity = 0f;
        this.yVelocity = 0f;
        calculateDirection();
    }

    public void update() {
        if (alive) {
            calculateDirection();
            x += xVelocity * Delta() * speed;
            y += yVelocity * Delta() * speed;
            if (target.isCollided(this)){
                target.takeDamage(damage);
                alive = false;
            }
            draw();
        }
    }

    public void draw(){
            DrawQuadTexRot(texture, x , y ,width,height, calculateAngel());
    }

    private float calculateAngel(){
        return  (float) Math.toDegrees(Math.atan2(target.getY() - y, target.getX() - x)) - 90;
    }

    private void calculateDirection(){
        float totalVelocity = 1.0f;
        float xDistanceFromTarget = Math.abs(target.getX() - x + TILE_SIZE / 4); // Aiming to center of enemy
        float yDistanceFromTarget = Math.abs(target.getY() - y + TILE_SIZE / 4); // Aiming to center of enemy
        float totalDistance = xDistanceFromTarget + yDistanceFromTarget;
        xVelocity = xDistanceFromTarget/totalDistance;
        yVelocity = totalVelocity - xVelocity;
        if(target.getX() < x) xVelocity*=-1;
        if(target.getY() < y) yVelocity*=-1;
    }

}
