/**
 * Created by Yuhan Huo from MacLachlan 2022-2023 Grade 12 Comp-Sci course final
 * CREDITS:
 * Font used in the project is created by Apostrophic Labs.
 * Bullet images created by wenrexa.
 * Music Created by Alex-Productions and Makai Symphony.
 * Marksman Icon from Riot Games
 * Berserker Icon from publicdomainvectors.org
 * Enemy movement frames created by ZeggyGames
 */

package me.mygdx.enday;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		//Set up a new game configuration and configure the screen
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setWindowedMode(Enday.width, Enday.height);
		config.setResizable(false);
		config.setTitle("Enday");

		//Start the game with the configuration
		new Lwjgl3Application(new Enday(), config);
	}
}
