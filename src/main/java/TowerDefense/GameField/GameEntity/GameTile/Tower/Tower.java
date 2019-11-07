package TowerDefense.GameField.GameEntity.GameTile.Tower;

import TowerDefense.GameField.GameEntity.Bullet.Bullet;
import TowerDefense.GameField.GameEntity.Enemy.Enemy;
import TowerDefense.GameField.GameEntity.GameEntity;
import TowerDefense.GameField.GameEntity.GameTile.Tile.Tile;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static TowerDefense.GameField.GameEntity.Enemy.Enemy.startTile;
import static TowerDefense.GameStage.Game.*;
import static Util.Artist.DrawQuadTexRot;
import static Util.Sound.machinegunSound;
import static Util.Sound.sniperSound;
import static Util.Timer.Delta;

public abstract class Tower implements GameEntity {
    private float x,y, timeSinceLastShot, firingSpeed, angle;
    private int width, height, damage, range, price;
    private Enemy target;
    public Texture[] textures;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> bullets;
    private boolean foundTarget, outOfRange;
    public TowerType type;
    public static int towerLevel = 1;


    public Tower(TowerType type, Tile startTile, ArrayList<Enemy> enemies)  {
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width = startTile.getWidth();
        this.height = startTile.getHeight();
        this.type = type;
        this.textures = type.textures;
        this.damage = type.damage;
        this.range = type.range;
        this.enemies = enemies;
        this.foundTarget= false;
        this.timeSinceLastShot = 0f;
        this.bullets = new ArrayList<Bullet>();
        this.firingSpeed = type.firingSpeed / gameSpeed;
        this.angle = 0f;
        this.outOfRange = false;
        this.price = type.price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void update() {
        if(!foundTarget || outOfRange){
            target = findTarget();
        }
        if( target == null || !target.isAlive()) target = findTarget();
        else {
            if(!pause){
                timeSinceLastShot += Delta();
                if (timeSinceLastShot > firingSpeed) Shoot();
            }
            angle = calculateAngel();
        }
        for (int i=0; i<bullets.size();i++) {
            if(bullets.get(i).isAlive()) bullets.get(i).update();
            else bullets.remove(i);
        }
        draw();

    }

    public void draw(){
        DrawQuadTexRot(textures[type.towerLevel-1],x,y,width,height,angle );
    }

    private Enemy findTarget(){ //Aiming to enemy || DONT TOUCH THIS || :D
        Enemy closest = null;
        float minDistance = 10000;
        for( Enemy e: enemies){
            if( e.isAlive() && Math.abs(e.getX() - x) + Math.abs(e.getY() - y) < minDistance ){
                if(Math.abs(e.getX() - x) < range && Math.abs(e.getY() - y) < range && (int)e.getY()/64 != startTile.getY() && (int) e.getX()/64 != startTile.getX())
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

    public void Shoot(){
        timeSinceLastShot = 0;
        if(sound){
            if(type == TowerType.NormalTower){
                machinegunSound();
            }
            else if(type == TowerType.MachineGunTower){
                machinegunSound();
            }
            else if(type == TowerType.SniperTower){
                sniperSound();
            }
        }
        bullets.add(new Bullet(type.textures[2], target, x + TILE_SIZE / 4, y + TILE_SIZE / 4, 32, 32, type.bulletSpeed, damage ));// sua lai width, height
    }

    public void updateEnemyList( ArrayList<Enemy> newList){
        enemies = newList;
    }

    public Enemy getTarget() {
        return target;
    }
}
