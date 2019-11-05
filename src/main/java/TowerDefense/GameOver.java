package TowerDefense;

import UI.UI;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;
import TowerDefense.StateManager.GameState;

import java.io.IOException;

import static TowerDefense.Game.TILE_SIZE;
import static TowerDefense.WaveManager.waveNumber;
import static Util.Artist.*;
import static TowerDefense.StateManager.*;
import static com.sun.deploy.trace.TraceLevel.UI;

public class GameOver {
    private Texture background;
    private UI menuUI;

    public GameOver() {
        //background = QuickLoad ("mainmenu.png");
        menuUI = new UI();
        menuUI.addButton("Play", "playButton.png", WIDTH / 2 - 128, (int) (HEIGHT * 0.45f));
        menuUI.addButton("Quit", "quit.png", WIDTH/2 - 128, (int) (HEIGHT*0.55f));
    }

    private void updateButtons() throws IOException{
        if(Mouse.isButtonDown(0)){
            if(menuUI.isButtonClicked("Play")) {
                StateManager.setState(GameState.GAME);
                String s = "Wave1";
                game = new Game(s);
            }
            if(menuUI.isButtonClicked("Quit")) System.exit(0);
        }
    }

    public void update() throws IOException {
        //DrawQuadTex(background, 0, 0, 2048, 1024);
        menuUI.draw();
        updateButtons();
    }
}
