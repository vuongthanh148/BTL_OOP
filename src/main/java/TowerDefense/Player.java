package TowerDefense;

import Util.Artist;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import sun.java2d.loops.DrawLine;

import java.awt.*;
import java.util.ArrayList;

import static TowerDefense.Game.TILE_SIZE;
import static Util.Artist.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class Player {
    public static int money, lives;
    private TileGrid grid;
    private TileType[] types;
    private int cur;
    private WaveManager waveManager;
    private ArrayList<Tower> towerList;
    boolean leftMouseDown = false, rightMouseButtonDown = false;
    private TrueTypeFont font, font1;



    public Player(TileGrid grid, WaveManager waveManager) {
        this.grid = grid;
        this.types = new TileType[3];
        this.types[0] = TileType.Grass;
        this.types[1] = TileType.Dirt;
        this.types[2] = TileType.Water;
        this.cur = 0;
        this.waveManager = waveManager;
        this.towerList = new ArrayList<Tower>();
        this.money = money;
        Font awtFont = new Font("Times New Roman",Font.BOLD, 30);
        font = new TrueTypeFont(awtFont, true);
        font1 = new TrueTypeFont(awtFont, true);
    }

    public void setTile(){
        grid.setTile((int) Mouse.getX() / TILE_SIZE, (int)(Artist.HEIGHT - Mouse.getY() - 1 ) / TILE_SIZE, types[cur]);
    }

    public void init(){
        money = 30;
        lives = 1;
    }

    public void drawString(){
        //Color.white.bind();
        String m = "Money: " + money;
        String l = "Lives: " + lives;
        font.drawString(20, 10, m, Color.white);
        font.drawString(250, 10, l, Color.white);
    }


    public void update(){
        drawString();
        //leftMouseDown = false;
        for(Tower t: towerList){
            t.update();
            t.draw();
            t.updateEnemyList(waveManager.getCurrentWave().getEnemyList());
        }

        //Handle Mouse
        //Set the Tower
        if( Mouse.isButtonDown(0) && !leftMouseDown && !grid.getFloatTile(Mouse.getX(),Mouse.getY()).isPlaced && grid.getFloatTile(Mouse.getX(),Mouse.getY()).getType().buildable){
            if(money - TowerType.CannonRed.price >= 0){
                towerList.add(new TowerCannonBlue(TowerType.CannonRed, grid.getFloatTile(Mouse.getX(),Mouse.getY()), waveManager.getCurrentWave().getEnemyList()));
                money -= TowerType.CannonRed.price;
                grid.getFloatTile(Mouse.getX(),Mouse.getY()).isPlaced = true;
            }
        }

        if( Mouse.isButtonDown(1) && !rightMouseButtonDown && !grid.getFloatTile(Mouse.getX(),Mouse.getY()).isPlaced && grid.getFloatTile(Mouse.getX(),Mouse.getY()).getType().buildable){
            if(money - TowerType.CannonSniper.price >= 0){
                towerList.add(new TowerCannonBlue(TowerType.CannonSniper, grid.getFloatTile(Mouse.getX(),Mouse.getY()), waveManager.getCurrentWave().getEnemyList()));
                money -= TowerType.CannonSniper.price;
                grid.getFloatTile(Mouse.getX(),Mouse.getY()).isPlaced = true;
            }
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
