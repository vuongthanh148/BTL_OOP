package TowerDefense.GameField.GameEntity.GameTile.Tile;

import Util.Drawer;

import static TowerDefense.GameState.Game.TILE_SIZE;
import static Util.Drawer.*;


public class TileGrid {

    public Tile[][] map;
    private int tilesWide, tilesHigh;

    public TileGrid(){ // Ham khoi tao set toan bo map la co? =))
        this.tilesWide = Drawer.WIDTH / TILE_SIZE;
        this.tilesHigh = Drawer.HEIGHT / TILE_SIZE;
        map = new Tile[Drawer.WIDTH / TILE_SIZE ][Drawer.HEIGHT / TILE_SIZE];
        for(int i=0;i<map.length;i++) {
            int j;
            for ( j = 0; j < map[i].length-1; j++) {
                map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Water);
            }
            map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.MenuTower);
        }
    }

    /*public TileGrid(int[][] newMap) {
        this.tilesWide = newMap[0].length;
        this.tilesHigh = newMap.length ;
        map = new Tile[tilesWide][tilesHigh];
        int i;
        for ( i = 0; i < map.length ; i++) {
            for (int j = 0; j < map[i].length; j++) {
                switch (newMap[j][i]){
                    case 0:
                        map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Grass);
                        break;
                    case 1:
                        map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Dirt);
                        break;
                    case 2:
                        map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Water);
                        break;
                    case 3:
                        map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.MenuTower);
                        break;

                }
            }
        }

        for (int j = 0; j < map.length; j++){
                map[ j][map[i].length - 1] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.MenuTower);
        }

    }*/

    public void setTile(int xCoord, int yCoord, TileType type){
        map[xCoord][yCoord] = new Tile(xCoord*TILE_SIZE, yCoord*TILE_SIZE,TILE_SIZE,TILE_SIZE,type);
    }

    public Tile getTile(int xPlace, int yPlace){
        if(xPlace < Drawer.WIDTH/TILE_SIZE && xPlace > -1 && yPlace < Drawer.HEIGHT/TILE_SIZE && yPlace > -1)
        return map[xPlace][yPlace];
        else {
            Tile tmp = new Tile(0,0,0,0, TileType.NULL);
            return tmp;
        }
    }

    public Tile getFloatTile(float xPlace, float yPlace){
        if(xPlace < Drawer.WIDTH && xPlace > -1 && yPlace < Drawer.HEIGHT && yPlace > -1)
            return map[(int)xPlace/TILE_SIZE][(int) (HEIGHT - 1 - yPlace)/ TILE_SIZE];
        else {
            Tile tmp = new Tile(0,0,0,0, TileType.Water);
            return tmp;
        }
    }

    public int getTilesWide() {
        return tilesWide;
    }

    public void setTilesWide(int tilesWide) {
        this.tilesWide = tilesWide;
    }

    public int getTilesHigh() {
        return tilesHigh;
    }

    public void setTilesHigh(int tilesHigh) {
        this.tilesHigh = tilesHigh;
    }

    public void DrawGrid(){

        for(int i=0;i<map.length;i++){
                for(int j=0;j<map[i].length;j++){
                    map[i][j].Draw();
                }
        }
    }


}
