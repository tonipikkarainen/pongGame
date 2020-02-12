package managers;

import java.util.HashMap;

import com.pongplus.game.Application;

import screens.AbstractScreen;
import screens.GameScreen;
import screens.MenuScreen;

/**
 * Takes care of arranging screens.
 * @author tonipikkarainen
 * @date 11.2.2020
 */
public class GameScreenManager {

	private final Application app;
	private HashMap<GAMESTATE, AbstractScreen > screens;
	
	public enum GAMESTATE{
		MAIN_MENU,
		PLAY,
		SETTINGS
	}
   
	public GameScreenManager(Application app) {
		this.app = app;
		initGameScreens();
		setGameScreen(GAMESTATE.MAIN_MENU);
	}
	
	private void initGameScreens() {
		this.screens = new HashMap<GAMESTATE, AbstractScreen> ();
		this.screens.put(GAMESTATE.PLAY, new GameScreen(this.app));
		this.screens.put(GAMESTATE.MAIN_MENU, new MenuScreen(this.app));
		// here we can add new types of screens
	}
	
	public void setGameScreen(GAMESTATE nextScreen) {
		app.setScreen(screens.get(nextScreen));
	}
	
	public void dispose() {
		for (AbstractScreen screen : screens.values()) {
			if (screen != null) {
				screen.dispose();
			}
		}
	}
	
	
}
