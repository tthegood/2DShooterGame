package me.mygdx.enday.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;
import me.mygdx.enday.Enday;

public class AbilityScreen implements Screen {
    //Variables
    private final Enday game;
    private final BitmapFont font;
    private final Texture[] Abilities;
    private final FreeTypeFontGenerator generator;
    private final FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    /**
     * Construct a new screen object to the main game.
     * @param game the main game object.
     */
    public AbilityScreen(Enday game) {
        //Initialize Variables and assets
        this.game = game;
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font2.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.padTop = 2;
        parameter.padBottom = 2;
        parameter.padLeft = 2;
        parameter.padRight = 2;
        parameter.size = 50;
        font = generator.generateFont(parameter);
        Abilities = new Texture[] {
                new Texture("VampireDescription.png"),
                new Texture("MarksmanDescription.png"),
                new Texture("BerserkerDescription.png")};
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

        //Tell the player to select their class/ability
        font.draw(game.batch, "Select Your Class", 600, 880);

        //Draw the classes/abilities and their description
        for (int i = 0; i < Abilities.length; i++) {
            game.batch.draw(Abilities[i], 50+i*520, 60, 450, 660);
        }

        //If the player clicked on a class, start the game with the class selected and dispose this screen
        if (Gdx.input.justTouched()) {
            float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();
            float touchX = Gdx.input.getX();

            if(touchY >= 60 && touchY <= 720) {
                for(int i = 0; i < 3; i++) {
                    if(touchX >= 50+i*520 && touchX <= 500+i*520) {
                        game.setScreen(new GameScreen(game, i+1));
                        this.dispose();
                    }
                }
            }
        }

        game.batch.end(); //End drawing Scene
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
     * Dispose the assets used to clear memories used
     */
    @Override
    public void dispose() {
        font.dispose();
    }
}
