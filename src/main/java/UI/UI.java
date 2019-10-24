package UI;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import java.awt.*;
import java.util.ArrayList;

import static Util.Artist.*;

public class UI {
    private ArrayList<Button> buttonList;

    public UI() {
        buttonList = new ArrayList<Button>();
    }

    public void addButton(String name, String textureName, int x, int y) {
        buttonList.add(new Button(name, QuickLoad(textureName), x, y));
    }

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
    }
}
