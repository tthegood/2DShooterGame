package me.mygdx.enday;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.mygdx.enday.Screen.MenuScreen;

public class Enday extends Game {
	//Constants
	public static final int width = 1600;
	public static final int height = 900;

	//Variables
	public SpriteBatch batch;

	/**
	 * Creates a new game.
	 */
	@Override
	public void create () {
		//Initialize Variables
		batch = new SpriteBatch();

		//Send the player to Menu Screen
		setScreen(new MenuScreen(this));
	}

	/**
	 * Method used automatically by the LibGDX engine to render the screen
	 */
	@Override
	public void render () {
		super.render();
	}

	/**
	 * Dispose the assets used to clear memories used
	 */
	@Override
	public void dispose () {
		batch.dispose();
	}
}
