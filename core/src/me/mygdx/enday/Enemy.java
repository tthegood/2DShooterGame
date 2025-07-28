package me.mygdx.enday;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;

import java.util.Random;

public class Enemy implements Disposable {
    //Constants
    public static final float width = 80;
    public static final float height = 130;

    //Variables
    private float speed;
    private Vector2 pos;
    private Vector2 velocity;
    private Random rng;
    private int direction;
    private float collisionTimer;
    private boolean faceLeft;
    private float health;
    private boolean isAbility;
    private float hurtTick;
    private float slowTick;

    /**
     * Constructs a new Enemy object with the specified health.
     * @param health The initial health of the enemy.
     */
    public Enemy(float health) {
        //Initialize Variables
        hurtTick = 0f;
        slowTick = 0f;
        this.health = health;
        faceLeft = true;
        rng = new Random();
        speed = 100f;
        collisionTimer = 0f;

        //Randomly choose a position and spawn the enemy outside of screen edge
        direction = rng.nextInt(4);
        if(direction == 0) {
            pos = new Vector2(-70, rng.nextInt(Enday.height));
        } else if(direction == 1) {
            pos = new Vector2(rng.nextInt(Enday.width+70)-70, Enday.height+10);
        } else if(direction == 2) {
            pos = new Vector2(Enday.width+10, rng.nextInt(Enday.height));
        } else {
            pos = new Vector2(rng.nextInt(Enday.width+70)-70, -110);
        }
    }

    /**
     * Updates the enemy's position and other properties based on the given delta time and player position.
     * @param delta The time in seconds since the last update.
     * @param playerPos The position of the player.
     */
    public void update(float delta, Vector2 playerPos) {
        //If the enemy is colliding, make it temporarily travel backwards to avoid collision
        float collisionVariable = 1;
        if(collisionTimer > 0) {
            collisionTimer -= delta;
            collisionVariable = -1;
        }

        //If berserker's ability knockback is active, all enemies are knockbacked by temporarily traveling backwards quickly
        //And the enemy is slowed by the ability
        if(isAbility) {
            collisionVariable = -6;
            slowTick = 5f;
        } else if(slowTick >= 0) {
            slowTick -= delta;
            collisionVariable *= 0.25;
        }

        //Update the position of the enemy
        Vector2 direction = new Vector2(playerPos).sub(new Vector2(pos.x+20, pos.y+10)).nor();
        if(direction.x < 0) faceLeft = true; //Check the facing direction of the enemy
        else faceLeft = false;
        velocity = new Vector2(direction).nor().scl(speed);
        pos.x += velocity.x * collisionVariable * delta;
        pos.y += velocity.y * collisionVariable * delta;
        isAbility = false;
        hurtTick -= delta;
    }

    /**
     * Returns the x coordinate of the bullet's position.
     * @return The x coordinate of the bullet's position.
     */
    public float getX() {
        return pos.x;
    }

    /**
     * Returns the y coordinate of the bullet's position.
     * @return The y coordinate of the bullet's position.
     */
    public float getY() {
        return pos.y;
    }

    /**
     * Returns the index of the animation that the screen should display
     * based on whether if the enemy is hit and what direction it's facing.
     * @return the index of the animation to display
     */
    public int getAnimationIndex() {
        if(faceLeft) {
            if(hurtTick > 0) return 3;
            return 1;
        } else {
            if(hurtTick > 0) return 2;
            return 0;
        }
    }

    /**
     * Returns the rectangle hitbox of the enemy
     * @return the rectangle hitbox of the enemy
     */
    public Rectangle getRect() {
        return new Rectangle(pos.x+25, pos.y+25, width-50, height-50);
    }

    /**
     * Deal damage to the enemy based on the damage given, give the enemy a hurttick to show that they are recently hit,
     * and check whether if they are dead.
     * @param damage the damage to take
     * @return whether if the enemy died from that damage
     */
    public boolean die(float damage) {
        hurtTick = 0.1f;
        health -= damage;
        return health <= 0;
    }

    /**
     * Do knockback to the enemy if it's colliding with other objects or if berserker used its ability
     * set the collisionTimer to 0.1 seconds.
     * @param isAbility whether if its from collision or berserker's ability.
     */
    public void handleCollision(boolean isAbility) {
        collisionTimer = 0.1f;
        this.isAbility = isAbility;
    }

    /**
     * Return whether if the enemy is slowed by berserker's ability.
     * @return whether if the enemy is slowed.
     */
    public boolean isSlowed() {
        return slowTick > 0;
    }

    /**
     * Unused method
     */
    @Override
    public void dispose() {
    }
}
