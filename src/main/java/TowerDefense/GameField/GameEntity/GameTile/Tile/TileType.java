package TowerDefense.GameField.GameEntity.GameTile.Tile;

public enum TileType {
    Grass("grass.png", true), Dirt("dirt.png", false), Water("water.png", false), NULL("water.png", false), MenuTower("LastMap.png", false);


    String textureName;
    public boolean buildable;

    TileType(String textureName, boolean buildable){
        this.textureName = textureName;
        this.buildable = buildable;
    }
}
