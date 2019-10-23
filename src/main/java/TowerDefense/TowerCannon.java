package TowerDefense;

import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static Util.Clock.*;
import static Util.Artist.*;

public class TowerCannon {
    float x,y, timeSinceLastShot, fireRate, angel ;
    int width, height, damage;
    public Texture baseTexture, cannonTexture;
    private Tile startTile;
    private ArrayList<Bullet> bullets;
    private  ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private Enemy target;

    public TowerCannon(Texture baseTexture, Tile startTile, int damage, ArrayList<Enemy> enemies) {
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
        this.target = findTarget();
        this.angel = calculateAngel();
    }

    private void Shoot(){
        timeSinceLastShot = 0;
        bullets.add(new Bullet(QuickLoad("Bullet.png"), target, x + 32, y + 32, 2000, 10 ));
    }

    public void update(){
        timeSinceLastShot += Delta();
        if(timeSinceLastShot > fireRate) Shoot();

        for(Bullet b: bullets){
            b.update();
        }
        angel = calculateAngel();
        Draw();
    }

    public void Draw(){
        DrawQuadTex(baseTexture, x, y, width, height );
        DrawQuadTexRot(cannonTexture, x,y ,width,height, angel);
    }

    private Enemy findTarget(){
        return enemies.get(0);
    }

    private float calculateAngel(){
        return  (float) Math.toDegrees(Math.atan2(target.getY() - y, target.getX() - x)) - 90;
    }
}
