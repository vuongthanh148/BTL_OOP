package TowerDefense;

import org.newdawn.slick.opengl.Texture;

import static Util.Artist.QuickLoad;

public enum TowerType {
    CannonRed(new Texture[]{QuickLoad("CannonBase.png"), QuickLoad("CannonGun.png")}, 10, 300,0.5f),
    CannonSniper(new Texture[]{QuickLoad("Cannon.png"),QuickLoad("CannonGun.png")}, 10,300,3f),
    Cannon(new Texture[]{QuickLoad("Cannon.png"),QuickLoad("CannonGun2.png")}, 10,300,3f);
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
