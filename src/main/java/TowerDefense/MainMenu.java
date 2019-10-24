package TowerDefense;

import org.newdawn.slick.opengl.Texture;

import static Util.Artist.*;
import static com.sun.deploy.trace.TraceLevel.UI;

public class MainMenu {
    private Texture background;
    private UI menuUI;

    public MainMenu() {
        background = QuickLoad ("mainmenu");
        menuUI = new UI();
        menuUI.("Play", "playButton", WIDTH / 2 - 128, (int) (HEIGHT * 0.45f));
    }

    public void update() {
        DrawQuadTex(background, 0, 0, 20*64, 10*64);
        draw();
    }

    public void draw(){
        DrawQuadTex(background, 0, 0, 2048, 1024);
    }
}
