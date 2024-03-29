package TowerDefense.GameState;

import ButtonManager.UI;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;
import TowerDefense.GameState.StateManager.GameState;

import java.io.IOException;

import static Util.Drawer.*;

public class MainMenu {
    private Texture background, startLogo;
    private UI menuUI;

    public MainMenu() throws IOException {
       background = QuickLoad ("mainmenu.png");
       startLogo = QuickLoad("gamelogo.png");
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
        DrawQuadTex(startLogo, 340, 50, startLogo.getTextureWidth(), startLogo.getTextureHeight());
        menuUI.draw();
        updateButtons();
    }
}
