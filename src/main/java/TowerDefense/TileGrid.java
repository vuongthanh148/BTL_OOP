package TowerDefense;

import Util.Artist;

import static TowerDefense.Game.TILE_SIZE;


public class TileGrid {

    public Tile[][] map;

    public TileGrid(){
        map = new Tile[Artist.WIDTH / TILE_SIZE][Artist.HEIGHT / TILE_SIZE];
        for(int i=0;i<map.length;i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Grass);
            }
        }
    }

    public TileGrid(int[][] newMap) {
        map = new Tile[Artist.WIDTH /TILE_SIZE][Artist.HEIGHT / TILE_SIZE];
        for (int i = 0; i < map.length; i++) {
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
                }
            }
        }
    }

    public void setTile(int xCoord, int yCoord, TileType type){
        map[xCoord][yCoord] = new Tile(xCoord*TILE_SIZE, yCoord*TILE_SIZE,TILE_SIZE,TILE_SIZE,type);
    }

    public Tile getTile(int xPlace, int yPlace){
        if(xPlace < Artist.WIDTH/TILE_SIZE && xPlace > -1 && yPlace < Artist.HEIGHT/TILE_SIZE && yPlace > -1)
        return map[xPlace][yPlace];
        else {
            Tile tmp = new Tile(0,0,0,0, TileType.Water);
            return tmp;
        }
    }

    public void DrawGrid(){
        for(int i=0;i<map.length;i++){
                for(int j=0;j<map[i].length;j++){
                    map[i][j].Draw();
                }
        }
    }
}
