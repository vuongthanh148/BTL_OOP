package TowerDefense.GameState;

import TowerDefense.GameField.GameEntity.GameTile.Tile.TileGrid;
import TowerDefense.GameField.GameEntity.GameTile.Tile.TileType;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;


import static TowerDefense.GameState.Game.TILE_SIZE;
import static Util.Drawer.HEIGHT;
import static Util.EditMap.*;


public class Editor {
    private TileGrid grid;
    private int index;
    private TileType[] types;
    private int cur = 0;

    public Editor() {
        grid = loadMap("Stage1");
        this.cur = 0;
        this.types = new TileType[4];
        this.types[0] = TileType.Grass;
        this.types[1] = TileType.Dirt;
        this.types[2] = TileType.Water;
        this.types[3] = TileType.MenuTower;
    }

    public void update() {
        grid.DrawGrid();
        if(Mouse.isButtonDown(0)){
            setTile();
        }

        //Handle Keyboard
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
                    case Keyboard.KEY_M:
                        cur = 3;
                        break;
                    case Keyboard.KEY_S:
                        String s = "Stage" + 3;
                        saveMap(s, grid);
                }
            }
        }
    }

    private void setTile() {
        grid.setTile((int) Math.floor(Mouse.getX() / TILE_SIZE), (int) Math.floor((HEIGHT - Mouse.getY() - 1) / TILE_SIZE), types[cur]);
    }
}
