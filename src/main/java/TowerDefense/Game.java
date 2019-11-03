package TowerDefense;

import UI.UI;
import org.lwjgl.input.Mouse;

import java.io.IOException;

public class Game {
    private TileGrid grid;
    private Player player;
    private WaveManager waveManager;
    private Enemy[] enemyTypes;
    public static final int TILE_SIZE = 64;
    private UI towerPickerUI;
    public String NamePause = "run.png";
    public String NameSpeed = "speed.png";
    public static boolean pause = true, accelerated = false;
    public static int gameSpeed = 1;



    public Game(int[][] map) throws IOException {
        grid = new TileGrid(map);
        enemyTypes = new Enemy[4];
        enemyTypes[0] = new NormalEnemy(0,8,grid);
        enemyTypes[1] = new SmallEnemy(0,8,grid);
        enemyTypes[2] = new TankerEnemy(0,8,grid);
        enemyTypes[3] = new BossEnemy(0,8,grid);
        waveManager = new WaveManager(enemyTypes,1, 20);
        player = new Player(grid, waveManager);
        player.init();
        setupUI();
        gameSpeed = 1;
    }


    private void setupUI(){
        towerPickerUI = new UI();
        towerPickerUI.addButton("NormalTower","NormalTower.png",320,640,64,64);
        towerPickerUI.addButton("MachineGunTower","MachineGunTower.png",548,640,64,64);
        towerPickerUI.addButton("SniperTower","SniperTower.png",776,640,64,64);
        towerPickerUI.addButton("ButtonPause",NamePause,1210,0,64,64);
        towerPickerUI.addButton("AccelerateSpeed",NameSpeed,1142,0,64,64);
    }

    private void updateUI(){
        towerPickerUI.draw();
        if(Mouse.next()){
            boolean mouseClicked = Mouse.isButtonDown(0);
            if(mouseClicked) {
                if (towerPickerUI.isButtonClicked("NormalTower")) {
                    player.pickTower(new TowerManager(TowerType.NormalTower, grid.getTile(0, 0), waveManager.getListEnemy()));
                }
                else if (towerPickerUI.isButtonClicked("SniperTower")) {
                    player.pickTower(new TowerManager(TowerType.SniperTower, grid.getTile(0, 0), waveManager.getListEnemy()));
                }
                else if (towerPickerUI.isButtonClicked("MachineGunTower")) {
                    player.pickTower(new TowerManager(TowerType.MachineGunTower, grid.getTile(0, 0), waveManager.getListEnemy()));
                }
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
    public void Update(){
        grid.DrawGrid(); // draw map
        waveManager.update(); // draw enemy
        player.update(); // draw tower
        updateUI(); //draw store
    }
}
