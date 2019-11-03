package TowerDefense;

import Util.Clock;
import org.lwjgl.opengl.Display;

import java.io.IOException;

import static Util.Artist.*;

public class Main {
    public Main() throws IOException {
        BeginSession();

        //Game game = new Game(map);
        while(!Display.isCloseRequested()){
            Clock.Update();

            //game.Update();
            StateManager.update();

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
    }

    public static void main(String[] args) throws IOException {
        new Main();
    }
}