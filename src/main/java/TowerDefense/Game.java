package TowerDefense;

import static Util.Artist.*;

public class Game {
    private TileGrid grid;
    private Player player;
    private Wave wave;

    //Temp Var
    TowerCannon tower;

    public Game(int[][] map){
        grid = new TileGrid(map);
        player = new Player(grid);
        wave = new Wave(2, new Enemy(QuickLoad("enemy.png"),grid.getTile(2,8),
                grid, 64, 64, 100, 64));
        tower = new TowerCannon(QuickLoad("CannonBase.png"), grid.getTile(4, 7), 10);
    }

    public void Update(){
        grid.DrawGrid();
        wave.Update();
        player.Update();
        tower.Update();

    }
}
