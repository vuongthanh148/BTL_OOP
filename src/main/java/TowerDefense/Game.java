package TowerDefense;

import static Util.Artist.*;

public class Game {
    private TileGrid grid;
    private Player player;
    private WaveManager waveManager;

    //Temp Var
    TowerCannon tower;

    public Game(int[][] map){
        grid = new TileGrid(map);
        player = new Player(grid);
        waveManager = new WaveManager(new Enemy(QuickLoad("enemy.png"),grid.getTile(1,8),
                grid, 64, 64, 100, 80), 4, 10);
        tower = new TowerCannon(QuickLoad("CannonBase.png"), grid.getTile(4, 7), 10);
    }

    public void Update(){
        grid.DrawGrid();
        waveManager.Update();
        player.Update();
        tower.Update();
    }
}
