package UI;

import TowerDefense.TowerType;
import org.newdawn.slick.opengl.Texture;

public class Button {
    private Texture texture;
    private Texture[] textures;
    private String name;
    private int x, y, width, height;

    public Button( String name, Texture texture, int x, int y, int width, int height) {
        this.name = name;
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
   /* public Button(String name, TowerType towerType, int x, int y, int side) {
        this.name = name;
        this.textures = towerType.textures;
        this.x = x;
        this.y = y;
        this.width = side;
        this.height = side;
    }*/

    public Button(String name, Texture texture, int x, int y) {
        this.name = name;
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = texture.getImageWidth();
        this.height = texture.getImageHeight();
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Texture[] getTextures() {
        return textures;
    }

    public void setTextures(Texture[] textures) {
        this.textures = textures;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
