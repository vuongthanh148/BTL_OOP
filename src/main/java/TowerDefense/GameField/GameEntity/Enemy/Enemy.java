package TowerDefense.GameField.GameEntity.Enemy;

import TowerDefense.GameField.GameEntity.Bullet.Bullet;
import TowerDefense.GameField.GameEntity.GameEntity;
import TowerDefense.GameField.GameEntity.GameTile.Tile.Tile;
import TowerDefense.GameField.GameEntity.GameTile.Tile.TileGrid;
import org.newdawn.slick.opengl.Texture;

import java.io.IOException;

import static TowerDefense.GamePlay.Player.lives;
import static TowerDefense.GamePlay.Player.money;
import static TowerDefense.GameStage.Game.TILE_SIZE;
import static TowerDefense.GameStage.Game.gameSpeed;
import static Util.Drawer.DrawQuadTex;
import static Util.Drawer.QuickLoad;
import static Util.Timer.Delta;

public class Enemy implements GameEntity {
    public int height = TILE_SIZE, width = TILE_SIZE, currentCheckpoint,oldX, oldY, reward;
    float speed,x,y,health, startHealth;
    Texture texture, healthBackground, healthForeground, healthBroder ;// thêm thuộc tính thanh sức khỏe
    public static Tile startTile;
    public boolean first = true, alive = true;
    public TileGrid grid;
    public int[] directions;
    boolean found = false;

    public Enemy(int x, int y, TileGrid grid) {
        this.grid = grid;
        this.startTile = grid.getTile(x,y);
        this.x = x*64;
        this.y = y*64;
        this.grid = grid;
        this.width = TILE_SIZE;
        this.height = TILE_SIZE;
        this.directions = new int[2];
        this.oldX = -64;
        this.oldY = 448;
        //Xdir
        this.directions[0]=0;
        //YDir
        this.directions[1]=0;
        this.directions = FindNextDir(this.getStartTile());
    }

    public Enemy(Texture texture, Tile startTile, TileGrid grid,int width, int height,  float health, float speed, int reward) throws IOException {
        this.texture = texture;
        this.healthBackground = QuickLoad("HealthBackground.PNG");
        this.healthForeground= QuickLoad("HealthForeground.PNG");
        this.healthBroder= QuickLoad("HealthBorder.PNG");
        this.startTile = startTile;
        this.y = startTile.getY();
        this.x = startTile.getX();
        this.width = width;
        this.health = health;
        this.height = height;
        this.startHealth = health;
        this.speed = speed;
        this.grid = grid;
        this.directions = new int[2];
        this.oldX = -64;
        this.oldY = 512;
        this.reward = reward;
        //Xdir
        this.directions[0]=0;
        //YDir
        this.directions[1]=0;
    }



    public void draw(){
        float healthPercentage = health / startHealth;
        DrawQuadTex(texture,x,y,width,height);
        DrawQuadTex(healthBackground,x,y - 8,width,8);
        DrawQuadTex(healthForeground,x,y - 8,width* healthPercentage,8);
        DrawQuadTex(healthBroder,    x,y - 8,width,8);
    }

    public void update(){
        if(!found){
            directions = FindNextDir(grid.getTile((int) x / TILE_SIZE, (int) y / TILE_SIZE));
            found = true;
        }

        if(directions[0] != 2 ){
                if(Math.abs(x + Delta() * directions[0] * speed * gameSpeed - oldX) >= TILE_SIZE){
                    x = oldX + (int) 64*directions[0];
                    oldX = (int) x;
                    directions = FindNextDir(grid.getTile((int) x / TILE_SIZE, (int) y / TILE_SIZE));
                    //System.out.println("oldX: " + oldX + " X: " + x);
                }
                else x += Delta() * directions[0] * speed * gameSpeed;
                if(Math.abs(y + Delta() * directions[1] * speed * gameSpeed - oldY) >= TILE_SIZE) {
                    y = oldY + (int) 64*directions[1];
                oldY = (int) y;
                directions = FindNextDir(grid.getTile((int) x / TILE_SIZE, (int) y / TILE_SIZE));
                //System.out.println("oldY: " + oldY + " Y: " + y);
            }
            else y += Delta() * directions[1] * speed * gameSpeed;
        }
        else {
            Die();
            lives--;
        }
    }

    public boolean isCollided(Bullet b){
        if(b.getX() + b.getWidth() > this.x && b.getX() < this.x + this.width && b.getY() + b.getHeight() > this.y && b.getY() < this.y + this.height) return true;
        return false;
    }

   //Find wayyyyyy
    public int[] FindNextDir(Tile s){
        int[] dir = new int[2];
        Tile U = grid.getTile(s.getXPlace(),s.getYPlace() - 1);
        Tile R = grid.getTile(s.getXPlace() + 1,s.getYPlace());
        Tile D = grid.getTile(s.getXPlace(),s.getYPlace() + 1);
        Tile L = grid.getTile(s.getXPlace() - 1,s.getYPlace());
        if(s.getType() == U.getType() && this.directions[1] != 1) {
            dir[0] = 0;
            dir[1] = -1;
            //System.out.println("U");
        }
        else if(s.getType() == L.getType() && this.directions[0] != 1) {
            dir[0] = -1;
            dir[1] = 0;
            //System.out.println("L");
        }
        else if(s.getType() == D.getType() && this.directions[1] != -1)
        {
            dir[0] = 0;
            dir[1] = 1;
            //System.out.println("D");
        }
        else if(s.getType() == R.getType() && this.directions[0] != -1)
        {
            dir[0] = 1;
            dir[1] = 0;
            //System.out.println("R");
        }
        else{
            dir[0] = 2;
            dir[1] = 3;
        }
        return dir;
    }

    public void takeDamage(int damage){
        health -= damage;
        if(this.getX()/64 < 1 && this.getY()/64 == 8) System.out.println(health);
        if(health <= 0){
            money += 10;
            Die();
        }
    }

    public boolean isAlive(){
        return alive;
    }

    public void Die(){
        alive = false;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Tile getStartTile() {
        return startTile;
    }

    public void setStartTile(Tile startTile) {
        this.startTile = startTile;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public TileGrid getTileGrid() {
        return grid;
    }

    public void setTileGrid(TileGrid grid) {
        this.grid = grid;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }
}
