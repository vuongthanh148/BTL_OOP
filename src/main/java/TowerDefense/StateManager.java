package TowerDefense;

public class StateManager {

    public static enum GameState {
        MAINMENU, GAME, EDITOR
    }

    public static GameState gameState = GameState.MAINMENU;
    public static MainMenu mainMenu;
    public static Game game;
    //public static Editor editor;

    public static void update() {
        switch (gameState) {
            case MAINMENU:
                if (mainMenu == null) {
                    mainMenu = new MainMenu();
                }
                mainMenu.update();
                break;
            case GAME:
                break;
            case EDITOR:
                break;
        }
    }
}
