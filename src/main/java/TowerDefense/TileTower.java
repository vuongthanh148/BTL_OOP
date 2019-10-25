package TowerDefense;

import org.newdawn.slick.opengl.Texture;

import static Util.Artist.QuickLoad;

public enum TileTower {
    CannonRed(new Texture[]{QuickLoad("CannonBase.PNG"), QuickLoad("CannonGun.png")}, 10),
    CannonSniper(new Texture[]{QuickLoad("CannonSniper.png")}, 30);
    Texture[] textures;
    int damage;
    TileTower(Texture[] textures, int i) {
        this.textures = textures;
        this.damage= i;
    }
}
