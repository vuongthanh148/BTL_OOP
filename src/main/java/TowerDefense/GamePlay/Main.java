package TowerDefense.GamePlay;

import TowerDefense.GameStage.StateManager;
import Util.Timer;
import org.lwjgl.opengl.Display;

import java.io.IOException;

import static Util.Drawer.*;

public class Main {
    public Main() throws IOException {
        BeginSession();

        //Game game = new Game(map);
        while(!Display.isCloseRequested()){
            Timer.Update();

            //game.Update();
            StateManager.update();

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
    }

    public static void main(String[] args) throws IOException {
        //System.out.println(System.getProperty("java.library.path"));
        new Main();
    }
}