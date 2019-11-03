package TowerDefense;

import Util.Artist;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.util.ArrayList;

import static TowerDefense.Game.TILE_SIZE;
import static TowerDefense.WaveManager.waveNumber;
import static Util.Artist.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

public class Player {
    public static int money, lives;
    private TileGrid grid;
    private TileType[] types;
    private int cur;
    private WaveManager waveManager;
    private ArrayList<Tower> towerList;
    boolean leftMouseDown = false, rightMouseButtonDown = false, holdingTower = false;
    private TrueTypeFont font, font1, font2;
    private Tower tempTower;



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
        Font awtFont1 = new Font("Times New Roman",Font.BOLD, 25);
        //Font awtFont2 = new Font("Times New Roman",Font.BOLD, 40);

        font = new TrueTypeFont(awtFont, true);
        font1 = new TrueTypeFont(awtFont1, true);
        //font2= new TrueTypeFont(awtFont2,true);
        this.tempTower = null;
    }

    public void setTile(){
        grid.setTile((int) Mouse.getX() / TILE_SIZE, (int)(Artist.HEIGHT - Mouse.getY() - 1 ) / TILE_SIZE, types[cur]);
    }

    public void init(){
        money = 100;
        lives = 20;
    }

    public void drawString(){
        //Color.white.bind();
        String m = "Money: " + money;
        String l = "Lives: " + lives;
        String w = "Waves: " + waveNumber;
        //String menu ="MENU TOWER: ";
        font1.drawString(20, 645 , m, Color.white);
        font1.drawString(200, 645, l, Color.white);
        font1.drawString(1100, 645, w, Color.white);
    }
    public void drawStringTower(TowerType towerType,int x, int y){
        //Color.white.bind();
        //setupFontMenuTower();
        String m = "Price: " + towerType.price ;
        String l = "Damage: " + towerType.damage;
        font1.drawString(x, y+30, m, Color.white);
        font1.drawString(x, y+65, l, Color.white);
    }


    public void update(){
        drawString();
        drawStringTower(TowerType.NormalTower,384,608);
        drawStringTower(TowerType.MachineGunTower,612,608);
        drawStringTower(TowerType.SniperTower,850,608);

        //leftMouseDown = false;
        //Update holding Tower
        if(holdingTower){
            tempTower.setX(getMouseTile().getX());
            tempTower.setY(getMouseTile().getY());
            DrawQuadTex(tempTower.textures[2],tempTower.getX() - (tempTower.getRange() - TILE_SIZE/2),tempTower.getY() - (tempTower.getRange() - TILE_SIZE/2), tempTower.getRange()*2, tempTower.getRange()*2);
            tempTower.draw();
        }
        for(Tower t: towerList){
            t.update();
            t.draw();
            t.updateEnemyList(waveManager.getCurrentWave().getEnemyList());
        }

        //Handle Mouse
        //Set the Tower
        if( Mouse.isButtonDown(0) && !leftMouseDown && !grid.getFloatTile(Mouse.getX(),Mouse.getY()).isPlaced && grid.getFloatTile(Mouse.getX(),Mouse.getY()).getType().buildable ){
            placeTower();
        }

        else if( Mouse.isButtonDown(1) && holdingTower){
            holdingTower = false;
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
    public Tile getMouseTile(){
        return grid.getTile(Mouse.getX()/TILE_SIZE,(HEIGHT - Mouse.getY() -1) / TILE_SIZE);
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money){
        this.money = money;
    }

    public void pickTower(Tower tower){
        tempTower = tower;
        holdingTower = true;
    }
    private void placeTower(){
        if(holdingTower) {
            if (money - tempTower.getPrice() >= 0) {
                towerList.add(tempTower);
                money -= tempTower.getPrice();
                grid.getFloatTile(Mouse.getX(), Mouse.getY()).isPlaced = true;
            }
        }
        holdingTower = false;
        tempTower=null;
    }
}
