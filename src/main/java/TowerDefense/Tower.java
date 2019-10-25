package TowerDefense;

public class Tower implements  Entity {
    private float x,y;
    private int width, height;
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(float x) {
        this.x=x;
    }

    public void setY(float y) {
        this.y=y;
    }

    public void setWidth(int width) {
        this.width= width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void update() {

    }

    public void draw() {

    }
}
