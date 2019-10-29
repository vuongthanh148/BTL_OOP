package TowerDefense;

import UI.UI;
import org.lwjgl.input.Mouse;
import java.awt.*;

import static Util.Artist.*;

public class Game {
    private TileGrid grid;
    private Player player;
    private WaveManager waveManager;
    private Enemy[] enemyTypes;
    public static final int TILE_SIZE = 64;
    private UI towerPickerUI;

    public Game(TileGrid grid){
        this.grid = grid;
        enemyTypes = new Enemy[4];
        enemyTypes[0] = new NormalEnemy(2,8,grid);
        enemyTypes[1] = new SmallEnemy(2,8,grid);
        enemyTypes[2] = new TankerEnemy(2,8,grid);
        enemyTypes[3] = new BossEnemy(2,8,grid);
        waveManager = new WaveManager(enemyTypes,2, 7);
        player = new Player(grid, waveManager);
        setupUI();
    }


    public Game(int[][] map){
        grid = new TileGrid(map);
        enemyTypes = new Enemy[4];
        enemyTypes[0] = new NormalEnemy(2,8,grid);
        enemyTypes[1] = new SmallEnemy(2,8,grid);
        enemyTypes[2] = new TankerEnemy(2,8,grid);
        enemyTypes[3] = new BossEnemy(2,8,grid);
        waveManager = new WaveManager(enemyTypes,2, 7);
        player = new Player(grid, waveManager);
        player.init();
        setupUI();

    }


    private void setupUI(){
        towerPickerUI = new UI();
        towerPickerUI.addButton("CannonBase","CannonBase.png",0,576,64,64);

        towerPickerUI.addButton("CannonSniper","CannonSniper.png",228,576,80,100);
        towerPickerUI.addButton("CannonSpecial","CannonSpecial.png",456,570,80,75);

    }
    private void updateUI(){
        grid.getFloatTile(0,576).isPlaced = true;
        grid.getFloatTile(64,576).isPlaced = true;
        grid.getFloatTile(128,576).isPlaced = true;
        towerPickerUI.draw();
        if(Mouse.next()) {
            boolean mouseClicked = Mouse.isButtonDown(0);
            if(mouseClicked) {
                if (towerPickerUI.isButtonClicked("CannonBase")) {
                    player.pickTower(new TowerCannonBlue(TowerType.CannonBase, grid.getTile(0, 0), waveManager.getListEnemy()));
                }
                if (towerPickerUI.isButtonClicked("CannonSniper")) {
                    player.pickTower(new TowerCannonBlue(TowerType.CannonSniper, grid.getTile(0, 0), waveManager.getListEnemy()));
                }
                if (towerPickerUI.isButtonClicked("CannonSpecial")) {
                    player.pickTower(new TowerCannonBlue(TowerType.CannonSpecial, grid.getTile(0, 0), waveManager.getListEnemy()));
                }
            }
        }

    }
    public void Update(){
        grid.DrawGrid(); // draw map
        waveManager.update(); // draw enemy
        player.update(); // draw tower
        updateUI();
    }
}
