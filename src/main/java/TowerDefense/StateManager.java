package TowerDefense;

import java.io.IOException;

import static Util.Sound.backgroundSound;

public class StateManager {

    public static enum GameState {
        MAINMENU, GAME, EDITOR, GAMEOVER
    }

    public static GameState gameState = GameState.MAINMENU;
    public static MainMenu mainMenu;
    public static Game game;
    public static Editor editor;
    public static EndGame gameover;
    static boolean first = true;


    public static void update() throws IOException {
        if(first) {
            backgroundSound();
            first = false;
        }
        switch (gameState) {
            case MAINMENU:
                if (mainMenu == null) {
                    mainMenu = new MainMenu();
                }
                mainMenu.update();
                break;
            case GAME:
                if (game == null){
                    String s = "Stage1";
                    game = new Game(s);
                }
                game.Update();
                break;
            case EDITOR:
                if (editor == null) editor = new Editor();
                editor.update();
                break;
            case GAMEOVER:
                if(gameover == null) gameover = new EndGame();
                gameover.update();
                break;
        }
    }

    public static void setState(GameState newState) {
        gameState = newState;
    }
}
