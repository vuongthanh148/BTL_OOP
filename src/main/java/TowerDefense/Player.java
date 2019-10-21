package TowerDefense;

import Util.Artist;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Player {
    private TileGrid grid;
    private TileType[] types;
    private int cur;

    public Player(TileGrid grid) {
        this.grid = grid;
        this.types = new TileType[3];
        this.types[0] = TileType.Grass;
        this.types[1] = TileType.Dirt;
        this.types[2] = TileType.Water;
        this.cur = 0;
    }

    public void SetTile(){
        grid.setTile((int) Mouse.getX() / 64, (int)(Artist.HEIGHT - Mouse.getY()-1) / 64, types[cur]);
    }

    public void Update(){
        if(Mouse.isButtonDown(0)){
            SetTile();
        }

        while(Keyboard.next()){
            if(Keyboard.getEventKeyState()){
                switch ((Keyboard.getEventKey())){
                    case Keyboard.KEY_C:
                        cur = 0;
                        break;
                    case Keyboard.KEY_D:
                        cur = 1;
                        break;
                    case Keyboard.KEY_N:
                        cur = 2;
                        break;
                }
            }
        }
    }
}
