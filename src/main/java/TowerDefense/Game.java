package TowerDefense;

import static Util.Artist.*;

public class Game {
    private TileGrid grid;
    private Player player;
    private WaveManager waveManager;
    private Enemy[] enemyTypes;
    public static final int TILE_SIZE = 64;

    public Game(TileGrid grid){
        this.grid = grid;
        enemyTypes = new Enemy[4];
        enemyTypes[0] = new NormalEnemy(2,8,grid);
        enemyTypes[1] = new SmallEnemy(2,8,grid);
        enemyTypes[2] = new TankerEnemy(2,8,grid);
        enemyTypes[3] = new BossEnemy(2,8,grid);
        waveManager = new WaveManager(enemyTypes,2, 7);
        player = new Player(grid, waveManager);
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
    }

    public void Update(){
        grid.DrawGrid();
        waveManager.update();
        player.update();
    }
}
