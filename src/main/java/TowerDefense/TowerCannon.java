package TowerDefense;

import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;
import static TowerDefense.Game.TILE_SIZE;
import static Util.Clock.*;
import static Util.Artist.*;

public class TowerCannon {
    /*private float x,y, timeSinceLastShot, fireRate, angel ;
    public int width, height, damage, range;
    public Texture baseTexture, cannonTexture;
    private Tile startTile;
    private ArrayList<Bullet> bullets;
    private  ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private Enemy target;
    private boolean foundEnemy = false;

    public TowerCannon(Texture baseTexture, Tile startTile, int damage, int range, ArrayList<Enemy> enemies) {
        this.baseTexture = baseTexture;
        this.cannonTexture = QuickLoad("CannonGun.png");
        this.startTile = startTile;
        this.x = startTile.getX();
        this.width = (int) startTile.getWidth();
        this.height = (int) startTile.getHeight();
        this.y = startTile.getY();
        this.damage = damage;
        this.fireRate = 0.5f;
        this.timeSinceLastShot = 0;
        this.bullets = new ArrayList<Bullet>();
        this.enemies = enemies;
        //this.target = findTarget();
        //this.angel = calculateAngel();
        this.range = range;
        this.foundEnemy = false;
    }

    /*private void Shoot(){
        timeSinceLastShot = 0;
        bullets.add(new Bullet(QuickLoad("Bullet.png"), target, x , y , 32, 32, 2000, 10 ));
    }
    public void updateEnemyList(ArrayList<Enemy> newList){
        enemies = newList;
    }

    public void update(){
        if(!foundEnemy) target = findTarget();
        if(target == null || !target.isAlive()) foundEnemy = false;
        else {
            angel = calculateAngel();
            timeSinceLastShot += Delta();
            if(timeSinceLastShot > fireRate) Shoot();

            for(Bullet b: bullets){
                b.update();
            }
        }

        Draw();
    }

    public void Draw(){
        DrawQuadTex(baseTexture, x+16, y+16, width/2, height/2 );
        DrawQuadTexRot(cannonTexture, x+16,y+16 ,width/2,height/2, angel);
    }

    private Enemy findTarget(){ // Still need to check if enemy die nearer to the tower than new enemy :D
        Enemy closetEnemy = null;
        float minDistance = 10000;
        for(Enemy e: enemies){
            if(e.isAlive() && Math.abs((int) (e.getX() - x)) < range && Math.abs(e.getY() -  y) < range
                    && (Math.abs(e.getX() - x) + Math.abs(e.getY() - y)) < minDistance ){
                closetEnemy = e;
                minDistance = Math.abs(e.getX() - x) + Math.abs(e.getY() - y);
            }
        }
        if(closetEnemy != null) foundEnemy = true;
        return closetEnemy;
    }



    private float calculateAngel(){
        return  (float) Math.toDegrees(Math.atan2(target.getY() - y, target.getX() - x)) - 90;
    }*/

}
