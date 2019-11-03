package TowerDefense;

import org.newdawn.slick.opengl.Texture;

import static TowerDefense.Game.TILE_SIZE;
import static Util.Artist.QuickLoad;

public enum TowerType {
    NormalTower(new Texture[]{QuickLoad("NormalTower.png"), QuickLoad("range.png")}, 5, TILE_SIZE*3, 300,0.05f, 10),
    SniperTower(new Texture[]{QuickLoad("SniperTower.png"), QuickLoad("range.png")}, 10,TILE_SIZE*5, 500,0.5f, 30),
    MachineGunTower(new Texture[]{QuickLoad("MachineGunTower.png"), QuickLoad("range.png")}, 3,TILE_SIZE*2, 200, 0.03f,20);

    public Texture[] textures;
    public int damage, range, price, bulletSpeed;
    float firingSpeed;

    TowerType(Texture[] textures, int damage, int range, int bulletSpeed, float firingSpeed, int price) {
        this.textures = textures;
        this.damage= damage;
        this.range= range;
        this.firingSpeed = firingSpeed;
        this.price = price;
        this.bulletSpeed = bulletSpeed;
    }
}
