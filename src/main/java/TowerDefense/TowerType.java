package TowerDefense;

import org.newdawn.slick.opengl.Texture;

import static TowerDefense.Game.TILE_SIZE;
import static Util.Artist.QuickLoad;

public enum TowerType {
    NormalTower(new Texture[]{QuickLoad("NormalTower.png"),QuickLoad("normalbullet.png"), QuickLoad("range.png")},          20, 250, 300,0.2f, 10),
    MachineGunTower(new Texture[]{QuickLoad("MachineGunTower.png"),QuickLoad("machinebullet.png"), QuickLoad("range.png")}, 15, 220, 350,0.04f,20),
    SniperTower(new Texture[]{QuickLoad("SniperTower.png"),QuickLoad("sniperbullet.png"), QuickLoad("range.png")},          150,350, 1000,0.5f, 30);

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
