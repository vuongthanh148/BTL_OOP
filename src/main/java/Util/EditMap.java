package Util;

import TowerDefense.GameField.GameEntity.GameTile.Tile.Tile;
import TowerDefense.GameField.GameEntity.GameTile.Tile.TileGrid;
import TowerDefense.GameField.GameEntity.GameTile.Tile.TileType;

import java.io.*;

public class EditMap {

    public static void saveMap(String mapName, TileGrid grid) {
        String mapData = "";
        for (int i = 0; i < grid.getTilesWide(); i++) {
            for (int j = 0; j < grid.getTilesHigh(); j++) {
                mapData += getTileID(grid.getTile(i, j));
            }
        }
        try {
            File file = new File(mapName);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(mapData);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TileGrid loadMap(String mapName) {
        TileGrid grid = new TileGrid();
        try {
            BufferedReader br = new BufferedReader(new FileReader(mapName));
            String data = br.readLine();
            for (int i = 0; i < grid.getTilesWide(); i++) {
                for (int j = 0; j < grid.getTilesHigh(); j++) {
                    grid.setTile(i, j, getTileType(data.substring(i * grid.getTilesHigh() + j, i * grid.getTilesHigh() + j + 1)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return grid;
    }

    public static TileType getTileType(String ID) {
        TileType type = TileType.NULL;
        if ("0".equals(ID)) {
            type = TileType.Grass;
        } else if ("1".equals(ID)) {
            type = TileType.Dirt;
        } else if ("2".equals(ID)) {
            type = TileType.Water;
        } else if ("3".equals(ID)) {
            type = TileType.MenuTower;
        } else if ("4".equals(ID)) {
            type = TileType.NULL;}
        return type;
    }

    public static String getTileID(Tile t) {
        String ID = "E";
        switch (t.getType()) {
            case Grass :
                ID = "0";
                break;
            case Dirt:
                ID = "1";
                break;
            case Water:
                ID = "2";
                break;
            case MenuTower:
                ID = "3";
                break;
            case NULL:
                ID = "4";
                break;
        }
        return ID;
    }
}
