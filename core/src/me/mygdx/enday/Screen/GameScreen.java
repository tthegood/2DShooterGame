package me.mygdx.enday.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import me.mygdx.enday.*;
import me.mygdx.enday.Blessing.*;
import me.mygdx.enday.Buff.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static java.lang.Float.min;
import static java.lang.Math.max;

public class GameScreen implements Screen {
    //Constants
    public static final float playerWidth = 60f;
    public static final float playerHeight = 100f;
    public static final float bulletSpeed = 1000f;
    static final float AnimaSpeed = 0.1f;
    static final float spawnSpeedIncreaseCD = 15f;

    //Variables
    public static float abilityCD;
    public static float fireCD;
    public static float abilityDuration;
    float lastTimeUsedAbility;
    float lastSpawnSpeedIncreaseTime;
    float lastSpawnTime;
    float lastShootTime;
    float spawnCD;
    float gameOverBuffer;
    float immuneTick;
    boolean alive;
    public static float health;
    int score;
    boolean isMoving;
    ArrayList<Bullet> bullets;
    ArrayList<Enemy> enemies;
    ArrayList<Buff> buffs;
    int[] buffLvl;
    Texture[] buffModels;
    Texture[] bulletModels;
    Texture[] AbilityModels;
    Texture[] RarityModels;
    Blessing[] blessingsToChoose;
    Texture background;
    Texture healthBar;
    Texture healthBarYellow;
    Texture healthBarRed;
    Texture frame;
    Texture playerModel;
    Texture playerHurt;
    Texture rerollDice;
    Texture rerollDiceActive;
    TextureRegion[][] rolls;
    Animation<TextureRegion>[] move;
    Animation<TextureRegion>[] enemyMove;
    ArrayList<ArrayList<Blessing>> blessings;
    Music music;
    BitmapFont font;
    BitmapFont smallFont;
    BitmapFont normalFont;
    float x;
    float y;
    float stateTime;
    Enday game;
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    Random rng;
    private static GlyphLayout healthGlyph;
    public static int Ability;
    public static Boolean bulletSplit;
    public static Boolean bulletBounce;
    public static Boolean bulletPenetrate;
    public static Boolean knockBackBlast;
    public static Boolean invincible;
    public static Boolean Vampire;
    public static Boolean superSplit;
    MyInputProcessor inputProcessor;
    public static Boolean paused;
    public static Boolean displayingStat;
    public static Boolean blessed;
    Boolean reroll;
    Boolean blessDrawn;
    Boolean blessChosen;
    float timeFromLastBless;
    float timeElapsed;

    //Player stat variables
    public static float baseAttack;
    public static float baseHealth;
    public static float baseAttackSpeed;
    public static float baseDefense;
    public static float baseSpeed;
    public static float baseEnemyHealth;
    public static float baseEnemyAttack;
    public static float dmgMitigation;
    public static float dmgIncrease;
    public static float attackAdd;
    public static float attackMultiplier;
    public static float defenseAdd;
    public static float defenseMultiplier;
    public static float healthAdd;
    public static float healthMultiplier;
    public static float attackSpeedAdd;
    public static float speedAdd;
    float enemyHealthMultiplier;
    float enemyAttackMultiplier;
    float attack;
    public static float maxHealth;
    float attackSpeed;
    float speed;
    float defense;
    float enemyHealth;
    float enemyAttack;
    public static float tempAttackMultiplier;
    public static float tempAttackSpeedAdd;
    public static float tempSpeedAdd;

    /**
     * Construct a new screen object to the main game.
     * @param game the main game object.
     * @param Ability the ability index that the player chose.
     */
    public GameScreen(Enday game, int Ability) {
        //Initializing player's and enemy's base and initial stats
        if(Ability == 1) {
            healthMultiplier = 25;
        } else {
            healthMultiplier = 0;
        }
        if(Ability == 2) {
            baseAttack = 47;
            baseAttackSpeed = 6;
        } else {
            baseAttack = 40;
            baseAttackSpeed = 5;
        }
        if(Ability == 3) {
            baseDefense = 33;
            dmgMitigation = 10;
        } else {
            baseDefense = 28;
            dmgMitigation = 0;
        }
        baseHealth = 250;
        baseSpeed = 220;
        dmgIncrease = 0;

        baseEnemyHealth = 100;
        baseEnemyAttack = 60;

        attackAdd = 0;
        attackMultiplier = 0;
        defenseAdd = 0;
        defenseMultiplier = 0;
        healthAdd = 0;
        attackSpeedAdd = 0;
        speedAdd = 0;
        enemyHealthMultiplier = 0;
        enemyAttackMultiplier = 0;
        attack = 0;
        speed = 0;
        maxHealth = 0;
        attackSpeed = 0;
        defense = 0;
        enemyHealth = 0;
        enemyAttack = 0;

        tempAttackMultiplier = 0;
        tempAttackSpeedAdd = 0;
        tempSpeedAdd = 0;

        health = baseHealth;

        //Sets the ability CD and duration based on the ability they chose
        if(Ability == 1) {
            abilityCD = 80f;
            abilityDuration = 14f;
        } else if(Ability == 2) {
            abilityCD = 100f;
            abilityDuration = 10f;
        } else {
            abilityCD = 67f;
            abilityDuration = 6.5f;
        }

        //Initializing variables
        timeElapsed = 0f;
        paused = false;
        displayingStat = false;
        GameScreen.Ability = Ability;
        rng = new Random();
        spawnCD = 1.5f;
        gameOverBuffer = 1f;
        score = 0;
        alive = true;
        isMoving = false;
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font2.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.padTop = 2;
        parameter.padBottom = 2;
        parameter.padLeft = 2;
        parameter.padRight = 2;
        parameter.size = 50;
        font = generator.generateFont(parameter);
        parameter.size = 30;
        smallFont = generator.generateFont(parameter);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("NormalFont.ttf"));
        normalFont = generator.generateFont(parameter);
        font.setColor(0,0,0,1);
        smallFont.setColor(0,0,0,1);
        bulletSplit = false;
        bulletBounce = false;
        bulletPenetrate = false;
        knockBackBlast = false;
        invincible = false;
        Vampire = false;
        superSplit = false;
        blessDrawn = false;
        blessChosen = false;
        blessed = true;
        reroll = true;
        timeFromLastBless = 0;
        x = (float) Enday.width /2 - playerWidth/2;
        y = (float) Enday.height /2 - playerHeight/2;
        buffLvl = new int[4];
        buffModels = new Texture[] {new Texture("DmgUp.png"),
                                    new Texture("FireRateUp.png"),
                                    new Texture("SpeedUp.png"),
                                    new Texture("BulletSplit.png"),
                                    new Texture("Heal.png")};
        bulletModels = new Texture[] {new Texture("Bullet.png"),
                                      new Texture("SplitBullet.png"),
                                      new Texture("BounceBullet.png")};
        AbilityModels = new Texture[] {new Texture("Vampire.png"),
                                       new Texture("Marksman.png"),
                                       new Texture("Berserker.png")};
        RarityModels = new Texture[] {new Texture("Grey.png"),
                                      new Texture("Blue.png"),
                                      new Texture("Gold.png")};
        enemyMove = new Animation[] {
                        new Animation<>(0.1f,
                        new TextureRegion(new Texture("Enemy.png")).split(48, 48)[0]),
                        new Animation<>(0.1f,
                        new TextureRegion(new Texture("EnemyReverse.png")).split(48, 48)[0]),
                        new Animation<>(0.1f,
                        new TextureRegion(new Texture("EnemyHurt.png")).split(48, 48)[0]),
                        new Animation<>(0.1f,
                        new TextureRegion(new Texture("EnemyHurtReverse.png")).split(48, 48)[0])};
        healthGlyph = new GlyphLayout();
        immuneTick = 0f;
        lastShootTime = 0f;
        lastSpawnTime = 0f;
        lastSpawnSpeedIncreaseTime = 0f;
        lastTimeUsedAbility = 1000f;
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        buffs = new ArrayList<>();
        playerModel = new Texture("PlayerMovement1.png");
        playerHurt = new Texture("PlayerHurt.png");
        rerollDice = new Texture("Reroll.png");
        rerollDiceActive = new Texture("RerollActive.png");
        frame = new Texture("Black.png");
        rolls = new TextureRegion[1][2];
        rolls[0] = new TextureRegion[] {new TextureRegion(new Texture("PlayerMovement2.png")),
                                        new TextureRegion(new Texture("PlayerMovement1.png"))};
        move = new Animation[1];
        Animation<TextureRegion> anim = new Animation<>(AnimaSpeed, rolls[0]);
        move[0] = anim;
        healthBar = new Texture("Health.png");
        healthBarYellow = new Texture("HealthYellow.png");
        healthBarRed = new Texture("HealthRed.png");
        blessings = new ArrayList<>();
        SetUpBlessings();
        blessingsToChoose = new Blessing[3];
        inputProcessor = new MyInputProcessor();
        Gdx.input.setInputProcessor(inputProcessor);
        this.game = game;
    }

    /**
     * Method used automatically by the LibGDX engine, used here to initialize music
     */
    @Override
    public void show() {
        //Initialize music and background
        music = Gdx.audio.newMusic(Gdx.files.internal("Dragon.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        background = new Texture("grass.png");
    }

    /**
     * Method used automatically by the LibGDX engine each tick to update and render the screen
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {

        game.batch.begin(); //Begin drawing the scene

        //Check if player is in stat-displaying mode which they pressed Tab to access
        if(displayingStat) {
            DisplayStat(); //Display the current player and enemy stat
        }

        //Randomly draw 3 blessings for the player to choose if blessed variable is true
        if(blessed) {
            ScreenUtils.clear(0, 0, 0, 1);

            //Draw 3 blessings
            if(!blessDrawn) {
                blessDrawn = true;
                blessingsToChoose = chooseBless();
            }

            //Let the player choose
            if(!blessChosen) {
                bless(blessingsToChoose);
            } else { //Continues game after player chosen their blessings
                blessDrawn = false;
                blessChosen = false;
                blessed = false;
            }
        }

        //If the player paused, pause the game and display pausing message
        if(paused) {
            font.setColor(1, 1, 1, 0.5f);
            font.draw(game.batch, "Paused", Enday.width/2f-100, Enday.height/2f+50);
        }

        //If the game is not paused, playing not choosing blessing and not displaying stats, continues the game
        if(!paused && !blessed && !displayingStat) {

            ScreenUtils.clear(0, 0, 0, 1);

            effectReset(); //Reset player's effect to be re-calculated and re-applied

            //If the player is dead, gives them a 1-second buffer and go to GameOverScreen
            if (!alive) {
                gameOverBuffer -= delta;
                if (gameOverBuffer <= 0) {
                    music.stop();
                    music.dispose();
                    this.dispose();
                    game.setScreen(new GameOverScreen(game, score));
                }
            }

            //Let the player choose another blessing every 55 seconds and give them a reroll chance
            if(timeFromLastBless >= 55) {
                blessed = true;
                reroll = true;
                timeFromLastBless = 0;
            }

            game.batch.draw(background, 0, 0, Enday.width, Enday.height); //Draw background

            Rectangle playerRect = new Rectangle(x, y, playerWidth, playerHeight); //Update player's hitbox

            //Update all buffs, apply current buffs, pickup buffs touched by players, remove expired buff
            Arrays.fill(buffLvl, 0);
            for (int i = buffs.size() - 1; i >= 0; i--) {
                Buff b = buffs.get(i);
                if (!b.pickedUp()) {
                    game.batch.draw(buffModels[b.getIndex()], b.getX(), b.getY(), Buff.size, Buff.size);
                    if(b.update(delta)) {
                        buffs.remove(i);
                    }
                }
                if (playerRect.overlaps(b.getRect()) || b.pickedUp()) {
                    if (b.getIndex() >= 3 || buffLvl[b.getIndex()] < 3) {
                        if (b.apply(delta)) {
                            buffs.remove(i);
                        } else {
                            if (b.getIndex() != -1) {
                                buffLvl[b.getIndex()] += 1;
                            }
                        }
                    }
                }
            }

            //If the player is alive, check for player input
            isMoving = false;
            if (alive) {
                //Update player's position
                if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
                    isMoving = true;
                    x += speed * delta;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
                    isMoving = true;
                    x -= speed * delta;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
                    isMoving = true;
                    y += speed * delta;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
                    isMoving = true;
                    y -= speed * delta;
                }
                if (x > Enday.width - playerWidth) x = Enday.width - playerWidth;
                if (x < 0) x = 0;
                if (y > Enday.height - playerHeight) y = Enday.height - playerHeight;
                if (y < 0) y = 0;

                //Check if player shot a bullet
                if (Gdx.input.isTouched()) {
                    if (lastShootTime >= fireCD) {
                        shootBullet(Gdx.input.getX(), Enday.height - Gdx.input.getY());
                        lastShootTime = 0f;
                    }
                }

                //Check if player used ability
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    if (lastTimeUsedAbility >= abilityCD) {
                        lastTimeUsedAbility = 0f;
                    }
                }
            }

            //If ability is in-effect, apply them
            if (lastTimeUsedAbility <= abilityDuration) {
                if (Ability == 1) Vampire();
                else if (Ability == 2) BulletMaster();
                else Berserker();
            }

            StatReset(); //re-calculate player's current stat

            //Set the player's health to max health at the start of the game
            if(timeElapsed == 0) {
                health = maxHealth;
            }

            //Update immune tick
            if (immuneTick > 0) {
                immuneTick -= delta;
            }

            //Spawn new Enemies
            if (lastSpawnTime >= spawnCD && enemies.size() <= 400) {
                enemies.add(new Enemy(enemyHealth));
                lastSpawnTime = 0f;
            }

            //Increase enemy spawn speed, health, and attack every 15 seconds
            if (lastSpawnSpeedIncreaseTime >= spawnSpeedIncreaseCD) {
                //Increase enemy's attack after 10 minutes
                if (timeElapsed >= 600) {
                    enemyAttackMultiplier += 4;
                    baseEnemyAttack *= 1.023;
                }

                //Increase enemy's health after 7 minutes
                if (timeElapsed >= 420) {
                    enemyHealthMultiplier += 5;
                    baseEnemyHealth *= 1.023;
                }

                //Always increase enemy spawn speed
                spawnCD *= 0.89;
                lastSpawnSpeedIncreaseTime = 0f;
            }

            //Update time-related variables
            lastSpawnTime += delta;
            lastShootTime += delta;
            lastSpawnSpeedIncreaseTime += delta;
            lastTimeUsedAbility += delta;
            stateTime += delta;
            timeElapsed += delta;
            timeFromLastBless += delta;

            if (alive) music.play(); //Play the music

            //Displaying current applied buffs
            for (int i = 0; i < buffLvl.length; i++) {
                if (buffLvl[i] != 0) {
                    game.batch.draw(buffModels[i], 10, 100 + i * 60, 40, 40);
                    if (i <= 2) {
                        smallFont.draw(game.batch, "Lv." + buffLvl[i], 45, 150 + i * 60);
                    }
                }
            }

            //Draw the player's model
            if (immuneTick > 0) {
                game.batch.draw(playerHurt, x, y, playerWidth, playerHeight);
            } else if (isMoving) {
                game.batch.draw(move[0].getKeyFrame(stateTime, true), x, y, playerWidth, playerHeight);
            } else {
                game.batch.draw(playerModel, x, y, playerWidth, playerHeight);
            }

            //Update enemy's models and draw them
            for (Enemy e : enemies) {
                e.update(delta, new Vector2(x, y));
                if(e.isSlowed()) {
                    game.batch.draw(enemyMove[e.getAnimationIndex()].getKeyFrame(stateTime*0.25f, true), e.getX(), e.getY(), Enemy.width, Enemy.height);
                } else {
                    game.batch.draw(enemyMove[e.getAnimationIndex()].getKeyFrame(stateTime, true), e.getX(), e.getY(), Enemy.width, Enemy.height);
                }
            }

            //Update bullet position and draw them
            for (int i = bullets.size() - 1; i >= 0; i--) {
                Bullet b = bullets.get(i);

                //Check if bullet is outside of border, which will be removed
                if (b.getX() > Enday.width || b.getX() < 0 || b.getY() > Enday.height || b.getY() < 0) {
                    if (bulletBounce) { //If marksman's ability is active, bullets bounce from border
                        bounceBullet(b.getX(), b.getY(), b.getVector());
                        bullets.get(bullets.size() - 1).update(delta);
                        bullets.get(bullets.size() - 1).draw(game.batch, bulletModels[b.getType()]);
                    }
                    bullets.remove(i);

                } else {
                    b.update(delta);
                    b.draw(game.batch, bulletModels[b.getType()]);
                }
            }

            //Check for collisions
            for (int i = enemies.size() - 1; i >= 0; i--) {
                Enemy e = enemies.get(i);
                Rectangle enemyRect = e.getRect();

                if (knockBackBlast) { //If Berserker's ability is active, knockback all enemies
                    e.handleCollision(true);
                }

                boolean dead = false; //Variable representing whether if the enemy has died

                //Check for bullet-enemy collision
                for (int j = bullets.size() - 1; j >= 0 && !dead; j--) {
                    Bullet b = bullets.get(j);
                    Circle bulletCircle = b.getCircle();

                    //Calculate current bullet damage
                    float dealDmg = attack*(1+dmgIncrease/100f);
                    if (b.getType() == 1) dealDmg *= 0.5;

                    //If bullet hit enemy, remove bullet and make enemy take damage
                    if (Intersector.overlaps(bulletCircle, enemyRect)) {

                        //Check if marksman's ability is active which make bullets penetrate enemies and won't be removed when hitting an enemy
                        if (!bulletPenetrate) {
                            //Otherwise remove them
                            b.dispose();
                            bullets.remove(j);
                        }

                        //Check if enemy is killed by the bullet
                        if (e.die(dealDmg)) {

                            //Generate a temporary buff
                            generateBuff(e);

                            //heals player if the player is a vampire
                            if (Ability == 1) {
                                if (Vampire) health += maxHealth * 0.03;
                                else health += maxHealth * 0.01;
                            }

                            //Split the bullet if the bullet-split buff is active
                            if (bulletSplit && b.getType() != 1) {
                                splitBullet(e.getX(), e.getY());
                            }

                            //Increases player's score and removes the enemy, marking it as dead
                            score += 100;
                            enemies.remove(i);
                            dead = true;
                        }
                    }
                }

                //If the enemy touches the player and the player is not in immune tick, make the player take damage and give them immune tick, and knockback the enemy for collision
                if (enemyRect.overlaps(playerRect) && immuneTick <= 0 && !invincible) {
                    float damage = max(maxHealth*0.02f, (enemyAttack - defense) * (1-dmgMitigation/100f));
                    takeDamage(damage);
                    e.handleCollision(false);
                }

                //If the enemy collides with another enemy, knockback the enemy by temporarily make it go backward to avoid collision
                for (int j = i - 1; j >= 0 && !dead; j--) {
                    if (isColliding(e, enemies.get(j))) {
                        e.handleCollision(false);
                    }
                }
            }

            //Draw current buffs in effect and their levels
            game.batch.draw(AbilityModels[Ability - 1], Enday.width - 140, 100, 120, 120);

            //Draw current ability duration and CD
            font.setColor(100, 0, 0, 1);
            font.draw(game.batch, (int) max((int) abilityCD - lastTimeUsedAbility, 0) + "s cd", Enday.width - 130, 120);
            font.draw(game.batch, (int) max((int) abilityDuration - lastTimeUsedAbility, 0) + "s", Enday.width - 100, 320);

            //Draw current score and frame-per-second
            font.draw(game.batch, "Score: " + score, 10, Enday.height);
            normalFont.draw(game.batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, Enday.height-130);

            //Draw current HealthBar
            healthGlyph.setText(smallFont, (int) Math.min(maxHealth,health) + "/" + (int) maxHealth);
            smallFont.draw(game.batch, healthGlyph, (Gdx.graphics.getWidth() - healthGlyph.width) / 2, 60);
            if (health / maxHealth > 0.5) {
                game.batch.draw(healthBar, 0, 0, (float) Enday.width / maxHealth * health, 10);
            } else if (health / maxHealth > 0.2) {
                game.batch.draw(healthBarYellow, 0, 0, (float) Enday.width / maxHealth * health, 10);
            } else {
                game.batch.draw(healthBarRed, 0, 0, (float) Enday.width / maxHealth * health, 10);
            }

            //Draw time elapsed
            float second = timeElapsed%60;
            if(second < 10) font.draw(game.batch, (int) timeElapsed/60 + ":0" + (int) timeElapsed%60 + " elapsed", Enday.width-315, Enday.height);
            else font.draw(game.batch, (int) timeElapsed/60 + ":" + (int) timeElapsed%60 + " elapsed", Enday.width-315, Enday.height);
        }

        game.batch.end(); //End drawing the scene
    }

    /**
     * Reduces the player's health by the specified amount of damage.
     * Sets the immuneTick to 0.4 seconds, giving the player a brief period of immunity after taking damage.
     * If the player's health reaches 0, sets the alive flag to false.
     * @param damage a float indicating the amount of damage to be taken
     */
    public void takeDamage(float damage) {
        health -= damage;
        if(health < 0) health = 0; //Adjust health if it drops below 0
        immuneTick = 0.4f;

        //Kill the player if their health drop to 0
        if(health <= 0) {
            alive = false;
        }
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
     * Disposes of all the resources used by the game when it is no longer needed.
     * Calls the `dispose` method on various objects to release their associated resources.
     */
    @Override
    public void dispose() {
        //Dispose images and musics
        music.dispose();
        playerHurt.dispose();
        playerModel.dispose();
        font.dispose();
        background.dispose();
        healthBar.dispose();

        //Dispose animation frames
        for (TextureRegion[] tArray : rolls) {
            for(TextureRegion t: tArray) {
                t.getTexture().dispose();
            }
        }

        //Dispose animations
        for (Animation<TextureRegion> a : move) {
            for (TextureRegion f : a.getKeyFrames()) {
                f.getTexture().dispose();
            }
        }

        //Dispose bullet objects
        for (Bullet b : bullets) {
            b.dispose();
        }

        //Dispose enemy objects
        for (Enemy e : enemies) {
            e.dispose();
        }
    }

    /**
     * Resets the stats of the player to their initial values to re-calculate temporary buffs
     * Adjust the value of health and damage-mitigation if they exceed the max value
     */
    private void StatReset() {
        //Reset stats
        attack = baseAttack + attackAdd + baseAttack*(attackMultiplier+tempAttackMultiplier)/100f;
        maxHealth = baseHealth + healthAdd + baseHealth*healthMultiplier/100f;
        defense = baseDefense + defenseAdd + baseDefense*defenseMultiplier/100f;
        attackSpeed = baseAttackSpeed + attackSpeedAdd + tempAttackSpeedAdd;
        speed = baseSpeed + speedAdd + tempSpeedAdd;
        enemyHealth = baseEnemyHealth*(1+enemyHealthMultiplier/100f);
        enemyAttack = baseEnemyAttack*(1+enemyAttackMultiplier/100f);
        fireCD = 1f/attackSpeed;

        tempAttackMultiplier = 0;
        tempAttackSpeedAdd = 0;
        tempSpeedAdd = 0;

        //Vampire get increased MaxHealth
        if(Vampire) {
            maxHealth += baseHealth*0.5;
        }

        //Adjust values that exceeds their maximum
        if (health > maxHealth) {
            health = maxHealth;
        }

        if(dmgMitigation > 70) {
            dmgMitigation = 70;
        }
    }

    /**
     * Reset the current temporary effects of the player for it to be re-calculated
     */
    private void effectReset() {
        //Reset all temporary effects
        bulletSplit = false;
        bulletBounce = false;
        bulletPenetrate = false;
        knockBackBlast = false;
        invincible = false;
        Vampire = false;
    }

    /**
     * Creates and shoots a bullet from the player's position towards the mouse's position.
     * @param mouseX The x-coordinate of the mouse position.
     * @param mouseY The y-coordinate of the mouse position.
     */
    public void shootBullet(float mouseX, float mouseY) {
        //Calculate positions
        Vector2 playerPos = new Vector2(x+50, y+55);
        Vector2 mousePos = new Vector2(mouseX, mouseY);
        Vector2 direction = new Vector2(mousePos).sub(playerPos).nor();

        //Create new bullet and add it to arraylist
        Bullet bullet = new Bullet(x+50, y+55, direction, bulletSpeed);
        bullets.add(bullet);
    }

    /**
     * Checks if two enemies are colliding with each other.
     * @param a The first enemy.
     * @param b The second enemy.
     * @return true if the enemies are colliding, false otherwise.
     */
    public boolean isColliding(Enemy a, Enemy b) {
        Rectangle rectA = a.getRect();
        Rectangle rectB = b.getRect();

        return rectA.overlaps(rectB);
    }

    /**
     * Creates split bullets around the specified position.
     * @param x The x-coordinate of the position.
     * @param y The y-coordinate of the position.
     */
    public void splitBullet(float x, float y) {
        bullets.add(new SplitBullet(x, y, new Vector2(0, -1), bulletSpeed));
        bullets.add(new SplitBullet(x, y, new Vector2(0, 1), bulletSpeed));
        bullets.add(new SplitBullet(x, y, new Vector2(1, 0), bulletSpeed));
        bullets.add(new SplitBullet(x, y, new Vector2(-1, 0), bulletSpeed));

        //If SuperSplit buff is in effect, split 4 more bullets
        if(superSplit) {
            bullets.add(new SplitBullet(x, y, new Vector2(1, 1), bulletSpeed));
            bullets.add(new SplitBullet(x, y, new Vector2(-1, -1), bulletSpeed));
            bullets.add(new SplitBullet(x, y, new Vector2(-1, 1), bulletSpeed));
            bullets.add(new SplitBullet(x, y, new Vector2(1, -1), bulletSpeed));
        }
    }

    /**
     * Creates a bouncing bullet at the specified position with the given velocity vector.
     * Used to bounce bullets off the edges.
     * @param x The x-coordinate of the original bullet.
     * @param y The y-coordinate of the original bullet.
     * @param v The velocity vector of the original bullet.
     */
    public void bounceBullet(float x, float y, Vector2 v) {
        //Bounce the bullet off the edge by creating a new bullet with its horizontal/vertical direction reversed, based on which edge it touches
        Bullet bullet;
        if(x >= Enday.width || x <= 0) {
            bullet = new BounceBullet(x, y, new Vector2(-v.x, v.y).nor(), bulletSpeed);
        } else {
            bullet = new BounceBullet(x, y, new Vector2(v.x, -v.y).nor(), bulletSpeed);
        }
        bullets.add(bullet);
    }

    /**
     * Activates the Vampire ability, which heal for 100 health upon use.
     * Sets the Vampire state to true.
     */
    public void Vampire() {
        if(lastTimeUsedAbility == 0) {
            health += 100;
        }
        Vampire = true;
    }

    /**
     * Activates the BulletMaster/Marksman ability, which grants the player power to penetrate enemies and bounce bullet off edges.
     */
    public void BulletMaster() {
        bulletBounce = true;
        bulletPenetrate = true;
    }

    /**
     * Activates the Berserker ability, which grants player invincibility, knockback all enemies for 0.25 seconds and damages them.
     */
    public void Berserker() {
        invincible = true;

        //Knockback all enemies for 0.25 seconds
        if(lastTimeUsedAbility <= 0.25) {
            knockBackBlast = true;
        }

        //Deal damage to all enemies upon use
        if(lastTimeUsedAbility == 0) {
            for(int i = enemies.size() - 1; i >= 0; i--) {
                Enemy e = enemies.get(i);
                if(e.die(attack)) {
                    generateBuff(e);
                    score += 100;
                    enemies.remove(i);
                }
            }
        }
    }

    /**
     * Generates a random buff after an enemy dies.
     * Randomly selects whether if a buff should spawn and its type and creates a corresponding buff object at the enemy's position.
     * Adds the created buff to the list of buffs.
     * @param e The enemy that just died that caused the buff to generate.
     */
    public void generateBuff(Enemy e) {
        int ifBuff = rng.nextInt(5);
        if(ifBuff == 0) {
            int whichBuff = rng.nextInt(5);
            if(whichBuff == 0) {
                Heal newBuff = new Heal(e.getX(), e.getY());
                buffs.add(newBuff);
            } else if (whichBuff == 1) {
                DmgUp newBuff = new DmgUp(e.getX(), e.getY());
                buffs.add(newBuff);
            } else if (whichBuff == 2) {
                FireRateUp newBuff = new FireRateUp(e.getX(), e.getY());
                buffs.add(newBuff);
            } else if (whichBuff == 3) {
                SpeedUp newBuff = new SpeedUp(e.getX(), e.getY());
                buffs.add(newBuff);
            } else if (whichBuff == 4) {
                BulletSplit newBuff = new BulletSplit(e.getX(), e.getY());
                buffs.add(newBuff);
            }
        }
    }

    /**
     * Sets up the blessings for the players.
     * Creates and initializes different ArrayLists of blessings and adds them to the blessings list.
     */
    public void SetUpBlessings() {
        //Tier-3 blessings (gray)
        ArrayList<Blessing> blessing1 = new ArrayList<>();
        blessing1.add(new AttackBlessing());
        blessing1.add(new AttackSpeedBlessing());
        blessing1.add(new DefenseBlessing());
        blessing1.add(new AttackBlessing2());
        blessing1.add(new DefenseBlessing2());
        blessing1.add(new HealthBlessing());
        blessing1.add(new HealthBlessing2());
        blessing1.add(new SpeedBlessing());

        //Tier-2 blessings (blue)
        ArrayList<Blessing> blessing2 = new ArrayList<>();
        blessing2.add(new AttackBlessingT2());
        blessing2.add(new AttackSpeedBlessingT2());
        blessing2.add(new DefenseBlessingT2());
        blessing2.add(new HealthBlessingT2());
        blessing2.add(new BaseAttackBlessing());
        blessing2.add(new BaseHealthBlessing());
        blessing2.add(new BaseDefenseBlessing());
        blessing2.add(new DmgIncreaseBlessing());
        blessing2.add(new DmgMitigationBlessing());

        //Tier-1 blessings (gold)
        ArrayList<Blessing> blessing3 = new ArrayList<>();
        blessing3.add(new AttackBlessingT1());
        blessing3.add(new AttackSpeedBlessingT1());
        blessing3.add(new DefenseBlessingT1());
        blessing3.add(new HealthBlessingT1());
        blessing3.add(new AbilityBuffBlessing());
        blessing3.add(new SuperBulletSplitBlessing());

        //Add the different tiers to the blessing list
        blessings.add(blessing1);
        blessings.add(blessing2);
        blessings.add(blessing3);
    }

    /**
     * Randomly selects 3 blessings from the list of all blessings, with higher tiers rarer to get, for the player to choose.
     * @return an array of 3 blessings that the player can choose from.
     */
    public Blessing[] chooseBless() {
        Blessing[] blessingsToChoose = new Blessing[3];
        for(int i = 0; i < 3; i++) {
            int rarity = rng.nextInt(100);
            if(rarity >= 92 && !blessings.get(2).isEmpty()) { //Tier-1
                int which = rng.nextInt(blessings.get(2).size());
                blessingsToChoose[i] = blessings.get(2).get(which);
            } else if(rarity >= 70) { //Tier-2
                int which = rng.nextInt(blessings.get(1).size());
                blessingsToChoose[i] = blessings.get(1).get(which);
            } else { //Tier-3
                int which = rng.nextInt(blessings.get(0).size());
                blessingsToChoose[i] = blessings.get(0).get(which);
            }

        }
        return blessingsToChoose;
    }

    /**
     * Allows the player to choose a blessing from the given array of 3 blessings.
     * Displays the blessings and handles player input to select a blessing.
     * @param blessingsToChoose an array of blessings to choose from.
     */
    public void bless(Blessing[] blessingsToChoose) {
        //Tell the user to select a blessing
        font.setColor(1,1,0,1);
        font.draw(game.batch, "Select Your Blessing", 600, 880);

        //Draw the blessings and their description
        for(int i = 0; i < 3; i++) {
            game.batch.draw(RarityModels[blessingsToChoose[i].getRarity()-1], 50+i*520, 130, 450, 590);
            normalFont.draw(game.batch, blessingsToChoose[i].getDescription(), 150+i*520, 230);
        }

        //Draw the reroll button
        //If the player clicks on the reroll button, re-draw the 3 blessings and remove their reroll chance
        if(reroll) {
            if(Gdx.input.getX() <= Enday.width/2f + 55 && Gdx.input.getX() >= Enday.width/2f - 55 && Enday.height - Gdx.input.getY() >= 5 && Enday.height - Gdx.input.getY() <= 115) {
                game.batch.draw(rerollDiceActive, Enday.width/2f - 55, 5, 110, 110);
                if(Gdx.input.justTouched()) {
                    if(timeElapsed != 0) reroll = false;
                    blessDrawn = false;
                }
            } else {
                game.batch.draw(rerollDice, Enday.width/2f - 55, 5, 110, 110);
            }
        }

        //Apply the clicked blessing to the player
        if(Gdx.input.justTouched()) {
            float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();
            float touchX = Gdx.input.getX();

            if(touchY >= 130 && touchY <= 650) {
                for(int i = 0; i < 3; i++) {
                    if(touchX >= 50+i*520 && touchX <= 500+i*520) {
                        blessingsToChoose[i].apply();
                        if(blessingsToChoose[i].getIndex() != -1) {
                            blessings.get(2).remove(blessingsToChoose[i]);
                        }
                        blessChosen = true;
                    }
                }
            }
        }
    }

    /**
     * Displays the player's stats on the screen.
     */
    public void DisplayStat() {
        game.batch.draw(frame, Enday.width/2f - 300, Enday.height/2f - 300, 600, 600);
        GlyphLayout stat = new GlyphLayout();
        String stats =  "                       Stats\n" +
                        "MaxHealth = " + baseHealth + " + " + healthAdd + " + " + healthMultiplier + "%" + '\n' +
                        "Attack = " + baseAttack + " + " + attackAdd + " + " + attackMultiplier + "%" + '\n' +
                        "AttackSpeed = " + baseAttackSpeed + " + " + attackSpeedAdd + '\n' +
                        "Defense = " + baseDefense + " + " + defenseAdd + " + " + defenseMultiplier + "%" + '\n' +
                        "Speed = " + baseSpeed + " + " + speedAdd + '\n' +
                        "DamageIncrease = " + dmgIncrease + "%" + '\n' +
                        "DamageMitigation = " + dmgMitigation + "%" + '\n' +
                        "EnemyHealth = " + baseEnemyHealth + " + " + enemyHealthMultiplier + "%" + '\n' +
                        "EnemyAttack = " + baseEnemyAttack + " + " + enemyAttackMultiplier + "%" + '\n';

        stat.setText(normalFont, stats);
        normalFont.draw(game.batch, stat, (Enday.width - stat.width) / 2, Enday.height/2f+stat.height/2);
    }
}
