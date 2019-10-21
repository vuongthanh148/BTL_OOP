package TowerDefense;

public enum TileType {
    Grass("grass.png", true), Dirt("dirt.png", false), Water("water.png", true);


    String textureName;
    boolean buildable;

    TileType(String textureName, boolean buildable){
        this.textureName = textureName;
        this.buildable = buildable;
    }
}
