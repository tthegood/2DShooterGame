package me.mygdx.enday;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import me.mygdx.enday.Screen.GameScreen;

public class MyInputProcessor implements InputProcessor {

    /**
     * Method to detect one-time key press, used to
     * detect whether if the player clicked P, which means pause, and
     * detect whether if the player clicked TAB, which means displaying stats
     * @param keycode the LibGDX keycode of the key pressed
     * @return false, the internal LibGDX engine logic required that it must return false.
     */
    public boolean keyDown (int keycode) {
        if(keycode == Input.Keys.P && !GameScreen.blessed) {
            GameScreen.paused = !GameScreen.paused;
        }

        if(keycode == Input.Keys.TAB && !GameScreen.blessed) {
            GameScreen.displayingStat = !GameScreen.displayingStat;
        }
        return false;
    }

    /**
     * Unused method
     */
    public boolean keyUp (int keycode) {
        return false;
    }

    /**
     * Unused method
     */
    public boolean keyTyped (char character) {
        return false;
    }

    /**
     * Unused method
     */
    public boolean touchDown (int x, int y, int pointer, int button) {
        return false;
    }

    /**
     * Unused method
     */
    public boolean touchUp (int x, int y, int pointer, int button) {
        return false;
    }

    /**
     * Unused method
     */
    public boolean touchDragged (int x, int y, int pointer) {
        return false;
    }

    /**
     * Unused method
     */
    public boolean mouseMoved (int x, int y) {
        return false;
    }

    /**
     * Unused method
     */
    public boolean scrolled (float amountX, float amountY) {
        return false;
    }
}
