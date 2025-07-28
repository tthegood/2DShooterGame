package me.mygdx.enday.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import me.mygdx.enday.Enday;

public class MenuScreen implements Screen {
    //Constants
    private static final int exitWidth = 300;
    private static final int exitHeight = 150;
    private static final int playWidth = 350;
    private static final int playHeight = 200;
    private static final int ruleWidth = 320;
    private static final int ruleHeight = 135;
    private static final int backWidth = 280;
    private static final int backHeight = 110;
    private static final int backX = 1230;
    private static final int backY = 735;
    private static final int playY = 450 - playHeight/2;
    private static final int exitY = 0;
    private static final int ruleY = 620;

    //Variables
    Enday game;
    Music music;
    Texture playButtonActive;
    Texture playButtonInactive;
    Texture exitButtonActive;
    Texture exitButtonInactive;
    Texture rulesActive;
    Texture rulesInactive;
    Texture backButtonActive;
    Texture backButtonInactive;
    Texture rules;
    Boolean displayingRules;

    /**
     * Construct a new screen object to the main game.
     * @param game the main game object.
     */
    public MenuScreen(Enday game) {
        //Initializing the assets variables
        this.game = game;
        displayingRules = false;
        music = Gdx.audio.newMusic(Gdx.files.internal("Empire.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        playButtonActive = new Texture("PlayActive.png");
        playButtonInactive = new Texture("PlayInactive.png");
        exitButtonActive = new Texture("ExitActive.png");
        exitButtonInactive = new Texture("ExitInactive.png");
        rulesActive = new Texture("RulesActive.png");
        rulesInactive = new Texture("RulesInactive.png");
        backButtonActive = new Texture("BackActive.png");
        backButtonInactive = new Texture("BackInactive.png");
        rules = new Texture("Rules.png");
    }

    /**
     * Unused method
     */
    @Override
    public void show() {

    }

    /**
     * Method used automatically by the LibGDX engine each tick to update and render the screen
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        //Clears the screen and begin drawing scene
        ScreenUtils.clear(0, 0, 0, 1);
        game.batch.begin();

        music.play(); //Start playing music

        //If the player clicked on the rule button, display the rules and a BACK button
        if(displayingRules) {
            game.batch.draw(rules, 0, 0, 1600, 900);
            if (Gdx.input.getX() < backX + backWidth && Gdx.input.getX() > backX && Enday.height - Gdx.input.getY() < backY + backHeight && Enday.height - Gdx.input.getY() > backY) {
                game.batch.draw(backButtonActive, backX, backY, backWidth, backHeight);
                if (Gdx.input.isTouched()) { //Go back the main manu if the player clicked BACK button
                    displayingRules = false;
                }
            } else {
                game.batch.draw(backButtonInactive, backX, backY, backWidth, backHeight);
            }
        } else {
            //Draw the rule button and check for player input
            if (Gdx.input.getX() < Enday.width / 2 + ruleWidth / 2 && Gdx.input.getX() > Enday.width / 2 - ruleWidth / 2 && Enday.height - Gdx.input.getY() < ruleY + ruleHeight && Enday.height - Gdx.input.getY() > ruleY) {
                game.batch.draw(rulesActive, Enday.width / 2f - ruleWidth / 2f, ruleY, ruleWidth, ruleHeight);
                if (Gdx.input.isTouched()) { //If the player clicks on rule button, display rules
                    displayingRules = true;
                }
            } else {
                game.batch.draw(rulesInactive, Enday.width / 2f - ruleWidth / 2f, ruleY, ruleWidth, ruleHeight);
            }

            //Draw the play button and check for player input
            if (Gdx.input.getX() < Enday.width / 2 + playWidth / 2 && Gdx.input.getX() > Enday.width / 2 - playWidth / 2 && Enday.height - Gdx.input.getY() < playY + playHeight && Enday.height - Gdx.input.getY() > playY) {
                game.batch.draw(playButtonActive, Enday.width / 2f - playWidth / 2f, playY, playWidth, playHeight);
                if (Gdx.input.isTouched()) { //If the player clicks on play button, start the game and dispose this screen
                    game.setScreen(new AbilityScreen(game));
                    music.stop();
                    this.dispose();
                }
            } else {
                game.batch.draw(playButtonInactive, Enday.width / 2f - playWidth / 2f, playY, playWidth, playHeight);
            }

            //Draw the exit button and check for player input
            if (Gdx.input.getX() < Enday.width / 2 + exitWidth / 2 && Gdx.input.getX() > Enday.width / 2 - exitWidth / 2 && Enday.height - Gdx.input.getY() < exitY + exitHeight && Enday.height - Gdx.input.getY() > exitY) {
                game.batch.draw(exitButtonActive, Enday.width / 2f - exitWidth / 2f, exitY, exitWidth, exitHeight);
                if (Gdx.input.isTouched()) { //If the player clicks on exit button, exit the app
                    Gdx.app.exit();
                }
            } else {
                game.batch.draw(exitButtonInactive, Enday.width / 2f - exitWidth / 2f, exitY, exitWidth, exitHeight);
            }
        }

        game.batch.end(); //End drawing scene
    }

    /**
     * Unused method
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * Unused method
     */
    @Override
    public void pause() {

    }

    /**
     * Unused method
     */
    @Override
    public void resume() {

    }

    /**
     * Unused method
     */
    @Override
    public void hide() {

    }

    /**
     * Disposes the resources used by and releases the memory.
     */
    @Override
    public void dispose() {
        music.dispose();
        playButtonActive.dispose();
        playButtonInactive.dispose();
        exitButtonActive.dispose();
        exitButtonInactive.dispose();
    }
}
