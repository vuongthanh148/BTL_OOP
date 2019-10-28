package TowerDefense;

import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static TowerDefense.Game.TILE_SIZE;
import static Util.Artist.*;
import static Util.Clock.*;

public abstract class Tower implements  Entity {
    private float x,y, timeSinceLastShot, firingSpeed, angle;
    private int width, height, damage, range;
    private Enemy target;
    private Texture[] textures;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> bullets;
    private boolean foundTarget, outOfRange;


    public Tower(TowerType type, Tile startTile, ArrayList<Enemy> enemies)  {
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width = startTile.getWidth();
        this.height = startTile.getHeight();
        this.textures = type.textures;
        this.damage = type.damage;
        this.range = type.range;
        this.enemies = enemies;
        this.foundTarget= false;
        this.timeSinceLastShot = 0f;
        this.bullets = new ArrayList<Bullet>();
        this.firingSpeed = type.firingSpeed;
        this.angle = 0f;
        this.outOfRange = false;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() { return height; }

    public void setX(float x) {
        this.x=x;
    }

    public void setY(float y) {
        this.y=y;
    }

    public void setWidth(int width) {
        this.width= width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void update() {
        if(!foundTarget || outOfRange){
            target = findTarget();
        }
        if( target == null || !target.isAlive()) foundTarget = false;
        else {
            timeSinceLastShot += Delta();
            if (timeSinceLastShot > firingSpeed) Shoot();
            for (Bullet b : bullets) b.update();
            angle = calculateAngel();
        }
        draw();
    }

    public void draw(){
        DrawQuadTex(textures[0],x,y,width,height);
        if(textures.length >= 1)
            for(int i = 1; i < textures.length; i++){
                DrawQuadTexRot(textures[i],x,y,width,height,angle );
            }
    }

    private Enemy findTarget(){ //Aiming to enemy || DONT TOUCH THIS || :D
        Enemy closest = null;
        float minDistance = 10000;
        for( Enemy e: enemies){
            if( e.isAlive() && Math.abs(e.getX() - x) + Math.abs(e.getY() - y) < minDistance ){
                if(Math.abs(e.getX() - x) < range && Math.abs(e.getY() - y) < range)
                {
                    closest = e;
                    minDistance = Math.abs(e.getX() - x) + Math.abs(e.getY() - y);
                }
                else outOfRange = true;
            }
        }
        if(closest != null)
            foundTarget = true;
        return closest;
    }

    private float calculateAngel(){
        return  (float) Math.toDegrees(Math.atan2(target.getY() - y, target.getX() - x)) - 90;
    }

    private void Shoot(){
        timeSinceLastShot = 0;
        bullets.add(new Bullet(QuickLoad("Bullet.png"), target, x + TILE_SIZE / 4, y + TILE_SIZE / 4, 32, 32, 2000, damage )); // sua lai width, height
    }

    public void updateEnemyList( ArrayList<Enemy> newList){
        enemies = newList;
    }

}
