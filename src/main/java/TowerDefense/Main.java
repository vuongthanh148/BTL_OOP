package TowerDefense;

import Util.Clock;
import org.lwjgl.opengl.Display;

import static Util.Artist.*;

public class Main {
    public Main(){
        BeginSession();

        int[][] map={
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
                {0,2,2,2,2,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
                {0,1,2,2,2,2,2,2,2,2,2,2,2,2,0,0,0,0,0,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
                {0,2,2,2,2,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        };
        Game game = new Game(map);
        while(!Display.isCloseRequested()){
            Clock.Update();

            game.Update();

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
    }

    public static void main(String[] args) {
        new Main();
    }
}