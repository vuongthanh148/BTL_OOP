package TowerDefense;

import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static Util.Clock.*;
import static Util.Artist.*;

public class TowerCannon {
    float x,y, timeSinceLastShot, fireRate ;
    int width, height, damage;
    public Texture baseTexture, cannonTexture;
    private Tile startTile;
    private ArrayList<Bullet> bullets;

    public TowerCannon(Texture baseTexture, Tile startTile, int damage) {
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

    }

    private void Shoot(){
        timeSinceLastShot = 0;
        bullets.add(new Bullet(QuickLoad("Bullet.png"), x + 32, y + 32, 120, 10 ));
    }

    public void Update(){
        timeSinceLastShot += Delta();
        if(timeSinceLastShot > fireRate) Shoot();

        for(Bullet b: bullets){
            b.Update();
        }

        Draw();
    }

    public void Draw(){
        DrawQuadTex(baseTexture, x, y, width, height );
        DrawQuadTexRot(cannonTexture, x,y ,width,height, -45);
    }
}
