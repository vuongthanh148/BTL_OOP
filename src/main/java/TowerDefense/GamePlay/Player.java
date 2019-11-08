package TowerDefense.GamePlay;

import TowerDefense.GameField.GameEntity.GameTile.Tile.Tile;
import TowerDefense.GameField.GameEntity.GameTile.Tile.TileGrid;
import TowerDefense.GameField.GameEntity.GameTile.Tile.TileType;
import TowerDefense.GameField.GameEntity.GameTile.Tower.Tower;
import TowerDefense.GameField.GameEntity.GameTile.Tower.TowerType;
import TowerDefense.GameStage.EndGame;
import TowerDefense.GameStage.Game;
import TowerDefense.GameField.GameWave.WaveManager;
import Util.Artist;
import Util.Leveler;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static TowerDefense.GameStage.Game.TILE_SIZE;
import static TowerDefense.GameStage.Game.pause;
import static TowerDefense.GameStage.StateManager.gameover;
import static TowerDefense.GameField.GameWave.WaveManager.waveNumber;
import static Util.Artist.*;

public class Player {
    public static int money, lives;
    private static TileGrid grid;
    private TileType[] types;
    private int cur;
    private WaveManager waveManager;
    private static ArrayList<Tower> towerList;
    boolean leftMouseDown = false, rightMouseButtonDown = false;;
    public boolean holdingTower = false, choosingTowerUpgrade = false, choosingTowerSell = false;
    public static boolean victory = false;
    private TrueTypeFont font, font1, font2;
    private Tower tempTower;
    public static int stageNumber = 1;



    public Player(TileGrid grid, WaveManager waveManager) {
        this.grid = Game.grid;
        this.types = new TileType[3];
        this.types[0] = TileType.Grass;
        this.types[1] = TileType.Dirt;
        this.types[2] = TileType.Water;
        this.cur = 0;
        this.waveManager = waveManager;
        this.towerList = new ArrayList<Tower>();
        Font awtFont = new Font("Times New Roman",Font.BOLD, 20);
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

    public static void init(){
        money = 60 + (stageNumber-1)*10;
        lives = 10;
    }

    public void drawString(){
        //Color.white.bind();
        String m = "Money: " + money;
        String l = "Lives: " + lives;
        String w = "Waves: " + waveNumber;
        String sg = "Stage: " + stageNumber;
        String mg = "Machine Gun";
        String s = "Sniper Gun";
        String n = "Normal Gun";
        String sell = "Sell";
        String u = "Upgrade";
        //String menu ="MENU TOWER: ";
        font1.drawString(20, 655 , m, Color.white);
        font1.drawString(200, 655, l, Color.white);
        font1.drawString(20, 695, w, Color.white);
        font1.drawString(200, 695, sg, Color.white);
        font.drawString(330, 715, n, Color.white);
        font.drawString(558, 715, mg, Color.white);
        font.drawString(788, 715, s, Color.white);
        font.drawString(1075, 715, sell, Color.white);
        font.drawString(1150, 715, u, Color.white);
    }
    public void drawStringTower(TowerType towerType, int x, int y){
        //Color.white.bind();
        //setupFontMenuTower();
        String m = "Price: " + towerType.price ;
        String l = "Damage: " + towerType.damage;
        font1.drawString(x, y, m, Color.white);
        font1.drawString(x, y+30, l, Color.white);
    }

    public static void nextStage(){
        //reset placed
        for(int i=0;i<WIDTH/TILE_SIZE;i++){
            for(int j=0;j<HEIGHT/TILE_SIZE;j++){
                grid.getTile(i,j).isPlaced = false;
            }
        }
        //reset tower
        towerList.clear();
        //reset money and lives
        init();
        //Change map
        String s = "Stage" + Integer.toString(stageNumber);
        Game.grid = Leveler.loadMap(s);
    }


    public void update() throws IOException {
        this.grid = Game.grid;
        drawString();
        drawStringTower(TowerType.NormalTower,384,648);
        drawStringTower(TowerType.MachineGunTower,612,648);
        drawStringTower(TowerType.SniperTower,850,648);

        //leftMouseDown = false;

        //Update holding Tower
        if(holdingTower){
            tempTower.setX(getMouseTile().getX());
            tempTower.setY(getMouseTile().getY());
            //Draw range
            DrawQuadTex(tempTower.textures[3],tempTower.getX() - (tempTower.getRange() - TILE_SIZE/2),tempTower.getY() - (tempTower.getRange() - TILE_SIZE/2), tempTower.getRange()*2, tempTower.getRange()*2);
            tempTower.draw();
        }
        //update choosing tower
        else if(choosingTowerSell || choosingTowerUpgrade){
            DrawQuadTex(QuickLoad("pointer.png"), Mouse.getX(), HEIGHT -1 - Mouse.getY(), 32,32);
        }

        for(Tower t: towerList){
            t.update();
            t.draw();
            t.updateEnemyList(waveManager.getCurrentWave().getEnemyList());
        }
        if(stageNumber == 3 && waveNumber == 4) {
            stageNumber = 3;
            pause = true;
            gameover = new EndGame();
            victory = true;
            gameover.update();
        }
        if(lives == 0 ){
            pause = true;
            gameover = new EndGame();
            victory = false;
            gameover.update();
        }

        //Handle Mouse
        //Set the Tower
        if( Mouse.isButtonDown(0) && !leftMouseDown && !grid.getFloatTile(Mouse.getX(),Mouse.getY()).isPlaced && grid.getFloatTile(Mouse.getX(),Mouse.getY()).getType().buildable ){
            placeTower();
        }
        else if( Mouse.isButtonDown(1) && holdingTower){
            holdingTower = false;
        }

        /**
         * alo alo
         */
        //upgrade and sell tower
        if( Mouse.isButtonDown(0) && !leftMouseDown){
            chooseTower();
        }
        else if( Mouse.isButtonDown(1) ){
            choosingTowerSell = false;
            choosingTowerUpgrade = false;
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

    public void chooseTower(){
        for(int i=0;i<towerList.size();i++){
            if(getMouseTile().getX()/64 == towerList.get(i).getX()/64 && getMouseTile().getY()/64 == towerList.get(i).getY()/64 && towerList.get(i).type.towerLevel<2 ){
               if(choosingTowerSell ){
                   money += towerList.get(i).getPrice()/2;
                   grid.getTile((int) towerList.get(i).getX()/64,(int) towerList.get(i).getY()/64).isPlaced = false;
                   choosingTowerSell = false;
                   towerList.remove(i);
                }
               if(choosingTowerUpgrade && money >= towerList.get(i).getPrice()*(towerList.get(i).type.towerLevel+1)){
                   towerList.get(i).type.towerLevel++;
                   money -= towerList.get(i).getPrice()*towerList.get(i).type.towerLevel;
                   towerList.get(i).type.range += 50;
                   towerList.get(i).type.damage *= 1.2;
                   towerList.get(i).type.bulletSpeed *= 1.1;
                   choosingTowerUpgrade = false;
               }
            }
        }
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
