package TowerDefense.GameState;

import TowerDefense.GamePlay.Player;
import ButtonManager.UI;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;
import TowerDefense.GameState.StateManager.GameState;

import java.io.IOException;

import static Util.Drawer.*;
import static TowerDefense.GameState.StateManager.*;

public class EndGame {
    private Texture defeatLogo, victoryLogo;
    private UI menuUI;

    public EndGame() {
        defeatLogo = QuickLoad ("defeat.png");
        victoryLogo = QuickLoad("victory.png");
        menuUI = new UI();
        menuUI.addButton("Play", "restart.png", 284, (int) (HEIGHT * 0.65f));
        menuUI.addButton("Quit", "quit.png",740, (int) (HEIGHT*0.65f));
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
        menuUI.draw();
        if(Player.victory) DrawQuadTex(victoryLogo, 390, 0, defeatLogo.getTextureWidth(), defeatLogo.getTextureHeight());
        else DrawQuadTex(defeatLogo, 390, 0, defeatLogo.getTextureWidth(), defeatLogo.getTextureHeight());
        updateButtons();
    }
}
