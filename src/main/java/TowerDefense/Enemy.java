package TowerDefense;

import Util.Artist;
import org.lwjgl.Sys;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static Util.Artist.*;
import static Util.Clock.*;
import static sun.dc.pr.Rasterizer.TILE_SIZE;

public class Enemy {
    public int height = 64, width = 64, currentCheckpoint,oldX, oldY;
    float speed,x,y,health, startHealth;
    Texture texture, healthBackground, healthForeground, healthBroder ;// thêm thuộc tính thanh sức khỏe
    public Tile startTile;
    public boolean first = true, alive = true;
    public TileGrid grid;
    public int[] directions;

    public Enemy(Texture texture, Tile startTile, TileGrid grid, int width, int height,  float health, float speed){
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
        this.oldX = 0;
        this.oldY = 0;
        //Xdir
        this.directions[0]=0;
        //YDir
        this.directions[1]=0;
    }

    public Enemy(int x, int y, TileGrid grid) {
        this.grid = grid;
        this.startTile = grid.getTile(x,y);
        this.x = x;
        this.y = y;
        this.grid = grid;
        this.width = 64;
        this.height = 64;
        this.directions = new int[2];
        this.oldX = 0;
        this.oldY = 0;
        //Xdir
        this.directions[0]=0;
        //YDir
        this.directions[1]=0;

    }

    public void Draw(){
        float healthPercentage = health/ startHealth;
        DrawQuadTex(texture,x,y,width,height);
        DrawQuadTex(healthForeground,x,y-16,TILE_SIZE* healthPercentage,8);
        DrawQuadTex(healthBackground,x,y -16,width,8);

        DrawQuadTex(healthBroder,x,y-16,width,8);
    }

    public void Update(){
        /*if(first) first = false;
        else {
            if(CheckpointReached()){
                if(currentCheckpoint == checkpoints.size()-1) Die();
                else {
                    currentCheckpoint++;
                }
            }
            else{
                x += Delta() * checkpoints.get(currentCheckpoint).getxDir() * speed;
                y += Delta() * checkpoints.get(currentCheckpoint).getyDir() * speed;
            }
        }*/
        if(Math.abs((int) x - oldX) >= TILE_SIZE || Math.abs((int) y - oldY) >= TILE_SIZE) {
            directions = FindNextDir(grid.getTile((int) x / TILE_SIZE, (int) y / TILE_SIZE ));
            oldX = (int) x;
            oldY = (int) y;
            System.out.println("found");
        }
        System.out.println("x: "+ (int) x + " oldX: " + oldX + " y: " + (int) y + " oldY: " + oldY );
        if(directions[0] != 3 ){
            x += Delta()*directions[0]*speed;
            y += Delta()*directions[1]*speed;
        }
        else Die();
    }

    //////////////////////////////////////////////////////////
   /* private boolean CheckpointReached(){
        boolean reached = false;
        Tile t = checkpoints.get(currentCheckpoint).getTile();
        if(x>t.getX()- 3
                && x < t.getX()+3
                && y > t.getY()-3
                && y < t.getY() +3) {
            reached = true;
            x = t.getX();
            y = t.getY();
        }
        return reached;

    }


    private void PopulatedCheckpointList(){
        checkpoints.add(FindNextCP(startTile, directions = FindNextDir(startTile)));

        int counter = 0;
        boolean cont = true;
        while(cont){
            int[] currentDir = FindNextDir(checkpoints.get(counter).getTile());
            if(currentDir[0] == 2 || counter == 15) cont = false;
            else checkpoints.add(FindNextCP(checkpoints.get(counter).getTile(),
                    directions = FindNextDir(checkpoints.get(counter).getTile())));
            counter++;
        }
    }


    private CheckPoint FindNextCP(Tile s, int[] directions){
        Tile next = null;
        CheckPoint c = null;
        boolean found = false;
        int counter = 1;
        while(!found){

            if(s.getType() != grid.getTile(s.getXPlace() + directions[0]*counter,
                    s.getYPlace()+directions[1]*counter).getType()){
                found = true;
                counter -= 1;
                next = grid.getTile(s.getXPlace() + directions[0]*counter,
                        s.getYPlace()+directions[1]*counter);
            }

            counter++;
        }
        c = new CheckPoint(next, directions[0], directions[1]);
        return c;
    }*/
/////////////////////////////////////////////////////////////////////////
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
            dir[0] = 3;
            dir[1] = 3;
        }
        return dir;
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
}
