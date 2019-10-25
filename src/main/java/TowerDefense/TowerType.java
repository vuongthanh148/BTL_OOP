package TowerDefense;

import org.newdawn.slick.opengl.Texture;

import static Util.Artist.QuickLoad;

public enum TowerType {
    CannonRed(new Texture[]{QuickLoad("CannonBase.PNG"), QuickLoad("CannonGun.png")}, 10, 1000,3),
    CannonSniper(new Texture[]{QuickLoad("CannonSniper.png"),QuickLoad("CannonGun.png")}, 10,10000,23);
    Texture[] textures;
    int damage, range;
    float firingSpeed;
    TowerType(Texture[] textures, int damage, int range, float firingSpeed) {
        this.textures = textures;
        this.damage= damage;
        this.range= range;
        this.firingSpeed = firingSpeed;
    }
}
