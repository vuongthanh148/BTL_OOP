package TowerDefense;

import Util.Clock;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.Queue;

import static Util.Artist.HEIGHT;
import static Util.Artist.QuickLoad;

public class Editor {
    private TileGrid grid;
    private int index;
    private TileType[] types;

    public Editor() {
        grid = new TileGrid();
        this.index = 0;
    }

    public void update() {
        grid.DrawGrid();
        // Handle Mouse input
        if (Mouse.isButtonDown(0)) {
            setTile();
        }
        // Handle Keyboard Input
        while (Keyboard.next()) {
            if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
                Clock.ChangeMultiplier((int) 0.2f);
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState()) {
                Clock.ChangeMultiplier((int) -0.2f);
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_T && Keyboard.getEventKeyState()) {
                towerList.add(new TowerCannon(QuickLoad("cannonBase"), grid.getTile(18,9), 10, waveManager.getCurrentWave().getEnemyList()));
            }
        }
    }

    private void setTile() {
        grid.setTile((int) Math.floor(Mouse.getX() / 64), (int) Math.floor((HEIGHT - Mouse.getY() - 1) / 64, types[index]);
    }

    private void moveIndex() {
        index++;
        if (index >types.length - 1) {
            index = 0;
        }
    }
}
