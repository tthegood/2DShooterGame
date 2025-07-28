package me.mygdx.enday.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;
import me.mygdx.enday.Enday;

public class GameOverScreen implements Screen {
    //Constants
    private static final int exitWidth = 300;
    private static final int exitHeight = 150;
    private static final int exitY = 0;
    private static final int restartWidth = 450;
    private static final int restartHeight = 125;
    private static final int restartY = 220;
    private static final GlyphLayout finalScore = new GlyphLayout();
    private static final GlyphLayout highScoreGlyph = new GlyphLayout();

    //Variables
    Enday game;
    BitmapFont font;
    Music music;
    int score;
    Texture exitButtonActive;
    Texture exitButtonInactive;
    Texture restartButtonActive;
    Texture restartButtonInactive;
    Texture GameOver;
    FreeTypeFontGenerator generator;
    FileHandle scoreFile;
    int highScore;

    /**
     * Construct a new screen object to the main game.
     * @param game the main game object.
     * @param score the final score of the player.
     */
    public GameOverScreen(Enday game, int score) {
        //Initialize variables and assets
        this.score = score;
        this.game = game;
        music = Gdx.audio.newMusic(Gdx.files.internal("Empire.mp3"));
        exitButtonActive = new Texture("ExitActive.png");
        exitButtonInactive = new Texture("ExitInactive.png");
        restartButtonActive = new Texture("RestartActive.png");
        restartButtonInactive = new Texture("RestartInactive.png");
        GameOver = new Texture("GameOver.png");
        scoreFile = Gdx.files.local("HighScore.txt");
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font2.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.padTop = 2;
        parameter.padBottom = 2;
        parameter.padLeft = 2;
        parameter.padRight = 2;
        parameter.size = 50;
        font = generator.generateFont(parameter);
        highScore = Integer.parseInt(scoreFile.readString().trim());
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

        music.play(); //Play the music

        //If the final score is higher than the highest score, update it in the txt file
        if(score > highScore) {
            highScore = score;
            scoreFile.writeString(Integer.toString(highScore), false);
        }

        //Draw the GameOver title
        game.batch.draw(GameOver, Enday.width / 2f - 250, Enday.height-200, 500, 200);

        //Draw the exit button and check for player input
        if(Gdx.input.getX() < Enday.width / 2 + exitWidth/2 && Gdx.input.getX() > Enday.width / 2 - exitWidth/2 && Enday.height - Gdx.input.getY() < exitY + exitHeight && Enday.height - Gdx.input.getY() > exitY) {
            game.batch.draw(exitButtonActive, Enday.width / 2f - exitWidth/2f, exitY, exitWidth, exitHeight);
            if(Gdx.input.isTouched()) { //If the player clicks on exit button, exit the app
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(exitButtonInactive, Enday.width / 2f - exitWidth/2f, exitY, exitWidth, exitHeight);
        }

        //Draw the restart button and check for player input
        if(Gdx.input.getX() < Enday.width / 2 + restartWidth/2 && Gdx.input.getX() > Enday.width / 2 - restartWidth/2 && Enday.height - Gdx.input.getY() < restartY + restartHeight && Enday.height - Gdx.input.getY() > restartY) {
            game.batch.draw(restartButtonActive, Enday.width / 2f - restartWidth/2f, restartY, restartWidth, restartHeight);
            if(Gdx.input.isTouched()) { //If the player clicks on restart button, restart the game and dispose the music
                game.setScreen(new AbilityScreen(game));
                music.stop();
                music.dispose();
            }
        } else {
            game.batch.draw(restartButtonInactive, Enday.width / 2f - restartWidth/2f, restartY, restartWidth, restartHeight);
        }

        //Display the final score and highest score to the player
        finalScore.setText(font, "Final Score: " + score);
        highScoreGlyph.setText(font, "Highest Score: " + highScore);
        font.setColor(200,0,70,1);
        font.draw(game.batch, finalScore, (Gdx.graphics.getWidth() - finalScore.width) / 2, Enday.height/2f+finalScore.height);
        font.draw(game.batch, highScoreGlyph, (Gdx.graphics.getWidth() - highScoreGlyph.width) / 2, Enday.height/2f+highScoreGlyph.height+130);

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
     * Unused method
     */
    @Override
    public void dispose() {

    }
}
