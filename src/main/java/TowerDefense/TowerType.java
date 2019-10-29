package TowerDefense;

import org.newdawn.slick.opengl.Texture;

import static Util.Artist.QuickLoad;

public enum TowerType {
    CannonBase(new Texture[]{QuickLoad("CannonBase.png"), QuickLoad("CannonGun.png")}, 2, 300,0.5f, 10),
    CannonSniper(new Texture[]{QuickLoad("CannonSniper.png"),QuickLoad("CannonGun.png")}, 5,300,0.5f, 20),
    CannonSpecial(new Texture[]{QuickLoad("CannonSpecial.png"),QuickLoad("CannonGun.png")}, 10,300,0.15f,30);
    ;
    public Texture[] textures;
    int damage, range, price;
    float firingSpeed;

    TowerType(Texture[] textures, int damage, int range, float firingSpeed, int price) {
        this.textures = textures;
        this.damage= damage;
        this.range= range;
        this.firingSpeed = firingSpeed;
        this.price = price;
    }
}
