package TowerDefense;

import Util.Artist;
import org.lwjgl.Sys;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static Util.Artist.*;
import static Util.Clock.*;

public interface Enemy {
    public void Draw();
    public void Update();
    public int[] FindNextDir(Tile s);
    public boolean isAlive();
    public void Die();
}