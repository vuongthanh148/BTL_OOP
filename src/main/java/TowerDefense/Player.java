package TowerDefense;

import Util.Artist;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;

import static TowerDefense.Game.TILE_SIZE;
import static Util.Artist.*;

public class Player {
    private TileGrid grid;
    private TileType[] types;
    private int cur;
    private WaveManager waveManager;
    private ArrayList<TowerCannon> towerList;
    boolean leftMouseDown = false;


    public Player(TileGrid grid, WaveManager waveManager) {
        this.grid = grid;
        this.types = new TileType[3];
        this.types[0] = TileType.Grass;
        this.types[1] = TileType.Dirt;
        this.types[2] = TileType.Water;
        this.cur = 0;
        this.waveManager = waveManager;
        this.towerList = new ArrayList<TowerCannon>();
    }

    public void setTile(){
        grid.setTile((int) Mouse.getX() / TILE_SIZE, (int)(Artist.HEIGHT - Mouse.getY() - 1 ) / TILE_SIZE, types[cur]);
    }

    public void update(){
        //leftMouseDown = false;
        for(TowerCannon t: towerList){
            t.update();
        }

        //Handle Mouse
        //Set the Tower
        if(Mouse.isButtonDown(0) && !leftMouseDown){
            towerList.add(new TowerCannon(QuickLoad("cannonBase.png"), grid.getTile(Mouse.getX()/ TILE_SIZE,(Artist.HEIGHT - Mouse.getY())/ TILE_SIZE),10, waveManager.getCurrentWave().getEnemyList()));
        }

        leftMouseDown = Mouse.isButtonDown(0);

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
                }
            }
        }
    }
}
