package TowerDefense.GameField.GameEntity.GameTile.Tower;

import org.newdawn.slick.opengl.Texture;

import static Util.Drawer.QuickLoad;

public enum TowerType {
    NormalTower(new Texture[]{QuickLoad("NormalTower.png"), QuickLoad("NormalTower2.png"), QuickLoad("normalbullet.png"), QuickLoad("range.png")},          15, 250, 300,0.2f, 10),
    MachineGunTower(new Texture[]{QuickLoad("MachineGunTower.png"), QuickLoad("MachineGunTower2.png"), QuickLoad("machinebullet.png"), QuickLoad("range.png")}, 10, 220, 350,0.04f,20),
    SniperTower(new Texture[]{QuickLoad("SniperTower.png"), QuickLoad("SniperTower2.png"), QuickLoad("sniperbullet.png"), QuickLoad("range.png")},          100,350, 1000,0.8f, 30);

    public Texture[] textures;
    public int damage, range, price, bulletSpeed/*, towerLevel = 1*/;
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
