package UI;

import TowerDefense.TowerType;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import java.awt.*;
import java.util.ArrayList;

import static Util.Artist.*;

public class UI {
    private ArrayList<Button> buttonList;
    private ArrayList<Button> buttonListTower;

    public UI() {
        buttonList = new ArrayList<Button>();
        buttonListTower = new ArrayList<Button>();
    }

    public void addButton(String name, String textureName, int x, int y) {
        buttonList.add(new Button(name, QuickLoad(textureName), x, y));
    }
    public void addButton(String name, String textureName, int x, int y, int width,int height) {
        buttonList.add(new Button(name, QuickLoad(textureName), x, y,width,height));
    }
    /*public void addButtonTower(String name, TowerType towerType, int x, int y) {
        buttonListTower.add(new Button(name, towerType, x, y,64));
    }*/

    public boolean isButtonClicked(String buttonName) {
        Button b = getButton(buttonName);
        float mouseY = HEIGHT - Mouse.getY() - 1;
        if (Mouse.getX() > b.getX() && Mouse.getX() < b.getX() + b.getWidth() &&
                mouseY > b.getY() && mouseY < b.getY() + b.getHeight()) {
            return true;
        }
        return false;
    }

    private Button getButton(String buttonName) {
        for (Button b: buttonList) {
            if (b.getName().equals(buttonName)) {
                return b;
            }
        }
        return null;
    }

    public void draw() {
        for (Button b: buttonList) {
            DrawQuadTex(b.getTexture(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
        }
        /*for (Button b: buttonListTower){
            for(Texture t : b.getTextures()){
                DrawQuadTex(t, b.getX(), b.getY(), b.getWidth(), b.getHeight());
            }
        }*/
    }
}
