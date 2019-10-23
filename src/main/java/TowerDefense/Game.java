package TowerDefense;

import static Util.Artist.*;

public class Game {
    private TileGrid grid;
    private Player player;
    private WaveManager waveManager;

    public Game(int[][] map){
        grid = new TileGrid(map);
        waveManager = new WaveManager(new Enemy( QuickLoad("enemy.png"),grid.getTile(1,8),
                grid, 64, 64, 100, 100 ), 1, 3);
        player = new Player(grid, waveManager);
    }

    public void Update(){
        grid.DrawGrid();
        waveManager.update();
        player.update();
    }
}
