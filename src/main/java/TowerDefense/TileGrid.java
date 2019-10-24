package TowerDefense;

import Util.Artist;

public class TileGrid {

    public Tile[][] map;

    public TileGrid(){
        map = new Tile[Artist.WIDTH/64][Artist.HEIGHT/64];
        for(int i=0;i<map.length;i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Grass);
            }
        }
    }

    public TileGrid(int[][] newMap) {
        map = new Tile[Artist.WIDTH /64][Artist.HEIGHT /64];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                switch (newMap[j][i]){
                    case 0:
                        map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Grass);
                        break;
                    case 1:
                        map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Dirt);
                        break;
                    case 2:
                        map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Water);
                        break;
                }
            }
        }
    }

    public void setTile(int xCoord, int yCoord, TileType type){
        map[xCoord][yCoord] = new Tile(xCoord*64, yCoord*64,64,64,type);
    }

    public Tile getTile(int xPlace, int yPlace){
        if(xPlace < Artist.WIDTH/64 && xPlace > -1 && yPlace < Artist.HEIGHT/64 && yPlace > -1)
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
