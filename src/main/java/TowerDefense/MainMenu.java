package TowerDefense;

import UI.UI;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;
import TowerDefense.StateManager.GameState;

import static TowerDefense.Game.TILE_SIZE;
import static Util.Artist.*;
import static com.sun.deploy.trace.TraceLevel.UI;

public class MainMenu<UI> {
    private Texture background;
    private UI menuUI;

    public MainMenu() {
       background = QuickLoad ("mainmenu.png");
       menuUI = new UI();
       menuUI.addButton("Play", "playButton.png", WIDTH / 2 - 128, (int) (HEIGHT * 0.45f));
       menuUI.addButton("Editor", "editor.png", WIDTH /2 - 128 , (int) (HEIGHT*0.55f));
       menuUI.addButton("Quit", "quit.png", WIDTH/2 - 128, (int) (HEIGHT*0.65f));
    }

    private void updateButtons(){
        if(Mouse.isButtonDown(0)){
            if(menuUI.isButtonClicked("Play")) StateManager.setState(GameState.GAME);
            if(menuUI.isButtonClicked("Quit")) System.exit(0);
            if(menuUI.isButtonClicked("Editor")) StateManager.setState(GameState.EDITOR);
        }
    }

    public void update() {
        DrawQuadTex(background, 0, 0, 2048, 1024);
        menuUI.draw();
        updateButtons();
    }
}
