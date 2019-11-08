package TowerDefense.GameStage;

import TowerDefense.GameField.GameEntity.Enemy.*;
import TowerDefense.GameField.GameEntity.GameTile.Tile.TileGrid;
import TowerDefense.GameField.GameEntity.GameTile.Tower.TowerManager;
import TowerDefense.GameField.GameEntity.GameTile.Tower.TowerType;
import TowerDefense.GamePlay.Player;
import TowerDefense.GameField.GameWave.*;
import UI.UI;
import org.lwjgl.input.Mouse;

import java.io.IOException;

import static Util.Leveler.loadMap;

public class Game {
    public static TileGrid grid;
    private Player player;
    private WaveManager waveManager;
    private Enemy[] enemyTypes;
    public static final int TILE_SIZE = 64;
    private UI towerPickerUI;
    public String NamePause = "run.png";
    public String NameSpeed = "speed.png";
    public String NameSound = "soundOn.png";
    public static boolean pause = true, accelerated = false, endgame = false, sound = true;
    public static int gameSpeed = 1;



    public Game(String path) throws IOException {
        grid = loadMap(path);
        enemyTypes = new Enemy[4];
        enemyTypes[0] = new NormalEnemy(0,8,grid);
        enemyTypes[1] = new SmallEnemy(0,8,grid);
        enemyTypes[2] = new TankerEnemy(0,8,grid);
        enemyTypes[3] = new BossEnemy(0,8,grid);
        waveManager = new WaveManager(enemyTypes,(float) 1.5, 20);
        player = new Player(grid, waveManager);
        player.init();
        setupUI();
        gameSpeed = 1;
    }


    private void setupUI(){
        towerPickerUI = new UI();
        towerPickerUI.addButton("NormalTower","NormalTower.png",        320,650,64,64);
        towerPickerUI.addButton("MachineGunTower","MachineGunTower.png",548,650,64,64);
        towerPickerUI.addButton("SniperTower","SniperTower.png",        776,650,64,64);
        towerPickerUI.addButton("ButtonPause",NamePause,1210,0,64,64);
        towerPickerUI.addButton("AccelerateSpeed",NameSpeed,1142,0,64,64);
        towerPickerUI.addButton("AdjustSound", NameSound, 1074, 0, 64,64);
        towerPickerUI.addButton("SellTower", "sell.png", 1060, 645, 64,64);
        towerPickerUI.addButton("UpgradeTower", "upgrade.png", 1150, 645, 64,64);

    }

    private void updateUI(){
        towerPickerUI.draw();
        if(Mouse.next()){
            boolean mouseClicked = Mouse.isButtonDown(0);
            if(mouseClicked) {
                //Set Tower
                if (towerPickerUI.isButtonClicked("NormalTower")) {
                    player.pickTower(new TowerManager(TowerType.NormalTower, grid.getTile(0, 0), waveManager.getListEnemy()));
                }
                else if (towerPickerUI.isButtonClicked("SniperTower")) {
                    player.pickTower(new TowerManager(TowerType.SniperTower, grid.getTile(0, 0), waveManager.getListEnemy()));
                }
                else if (towerPickerUI.isButtonClicked("MachineGunTower")) {
                    player.pickTower(new TowerManager(TowerType.MachineGunTower, grid.getTile(0, 0), waveManager.getListEnemy()));
                }
                //Sell Tower
                else if(towerPickerUI.isButtonClicked("SellTower")){
                    player.choosingTowerSell = true;
                }
                else if(towerPickerUI.isButtonClicked("UpgradeTower")){
                    player.choosingTowerUpgrade = true;
                }
                //Sound control
                if(towerPickerUI.isButtonClicked("AdjustSound")){
                    if(sound){
                        sound = false;
                        towerPickerUI.removeButton("AdjustSound");
                        NameSpeed = "soundOff.png";
                        towerPickerUI.addButton("AdjustSound",NameSpeed,1074,0,64,64);
                    }
                    else{
                        sound = true;
                        towerPickerUI.removeButton("AdjustSound");
                        NameSpeed = "soundOn.png";
                        towerPickerUI.addButton("AdjustSound",NameSpeed,1074,0,64,64);
                    }
                }
                //Game Speed
                if(towerPickerUI.isButtonClicked("AccelerateSpeed")){
                    if(!accelerated){
                        gameSpeed = 2;
                        accelerated = true;
                        towerPickerUI.removeButton("AccelerateSpeed");
                        NameSpeed = "stop.png";
                        towerPickerUI.addButton("AccelerateSpeed",NameSpeed,1142,0,64,64);
                    }
                    else{
                        gameSpeed = 1;
                        accelerated = false;
                        towerPickerUI.removeButton("AccelerateSpeed");
                        NameSpeed = "speed.png";
                        towerPickerUI.addButton("AccelerateSpeed",NameSpeed,1142,0,64,64);
                    }
                }
                //pause
                if(towerPickerUI.isButtonClicked("ButtonPause")){
                    pause = !pause;
                }
            }
        }
        if(pause){
            towerPickerUI.removeButton("ButtonPause");
            NamePause = "run.png";
            towerPickerUI.addButton("ButtonPause",NamePause,1210,0,64,64);
        }
        else{
            towerPickerUI.removeButton("ButtonPause");
            NamePause ="pause.png";
            towerPickerUI.addButton("ButtonPause",NamePause,1210,0,64,64);
        }

    }
    public void Update() throws IOException {
        grid.DrawGrid(); // draw map
        waveManager.update(); // draw enemy
        player.update(); // draw tower
        updateUI(); //draw store
    }
}
