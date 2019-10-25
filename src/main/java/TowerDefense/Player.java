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
    private ArrayList<Tower> towerList;
    boolean leftMouseDown = false, rightMouseButtonDown = false;


    public Player(TileGrid grid, WaveManager waveManager) {
        this.grid = grid;
        this.types = new TileType[3];
        this.types[0] = TileType.Grass;
        this.types[1] = TileType.Dirt;
        this.types[2] = TileType.Water;
        this.cur = 0;
        this.waveManager = waveManager;
        this.towerList = new ArrayList<Tower>();
    }

    public void setTile(){
        grid.setTile((int) Mouse.getX() / TILE_SIZE, (int)(Artist.HEIGHT - Mouse.getY() - 1 ) / TILE_SIZE, types[cur]);
    }

    public void update(){
        //leftMouseDown = false;
        for(Tower t: towerList){
            t.update();
            t.draw();
            t.updateEnemyList(waveManager.getCurrentWave().getEnemyList());
        }

        //Handle Mouse
        //Set the Tower
        if(Mouse.isButtonDown(0) && !leftMouseDown){
            towerList.add(new TowerCannonBlue(TowerType.CannonRed, grid.getTile(Mouse.getX()/ TILE_SIZE,(Artist.HEIGHT - Mouse.getY())/ TILE_SIZE), waveManager.getCurrentWave().getEnemyList()));
        }
        if(Mouse.isButtonDown(1) && !rightMouseButtonDown){
            towerList.add(new TowerCannonBlue(TowerType.CannonSniper, grid.getTile(Mouse.getX()/ TILE_SIZE,(Artist.HEIGHT - Mouse.getY())/ TILE_SIZE), waveManager.getCurrentWave().getEnemyList()));
        }

        leftMouseDown = Mouse.isButtonDown(0);
        rightMouseButtonDown = Mouse.isButtonDown(1);

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
