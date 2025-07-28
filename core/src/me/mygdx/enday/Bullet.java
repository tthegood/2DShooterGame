package me.mygdx.enday;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public class Bullet implements Disposable {
    //Constants
    private static final float size = 35;

    //Variables
    private Vector2 pos;
    private Vector2 velocity;

    /**
     * Constructs a new Bullet object with the specified position, direction, and speed.
     * @param x The x coordinate of the bullet's initial position.
     * @param y The y coordinate of the bullet's initial position.
     * @param direction The direction vector of the bullet.
     * @param speed The speed of the bullet.
     */
    public Bullet(float x, float y, Vector2 direction, float speed) {
        //Initialize variables
        this.pos = new Vector2(x, y);
        this.velocity = new Vector2(direction).nor().scl(speed);
    }

    /**
     * Updates the player's position.
     * @param delta The time in seconds since the last render.
     */
    public void update(float delta) {
        pos.x += velocity.x * delta;
        pos.y += velocity.y * delta;
    }

    /**
     * Get the type of the bullet.
     * @return the index indicating the type of the bullet.
     */
    public int getType() {
        return 0;
    }

    /**
     * Returns the velocity vector.
     * @return The velocity vector.
     */
    public Vector2 getVector() {
        return velocity;
    }

    /**
     * Returns a Circle hitbox of the bullet.
     * @return The Circle hitbox of the bullet.
     */
    public Circle getCircle() {
        return new Circle(pos.x + size / 2, pos.y + size / 2, size / 2);
    }

    /**
     * Draws the bullet using the provided SpriteBatch and bullet model texture.
     * @param batch The SpriteBatch used for rendering.
     * @param bulletModel The texture representing the bullet model.
     */
    public void draw(SpriteBatch batch, Texture bulletModel) {
        batch.draw(bulletModel, pos.x, pos.y, size, size);
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
     * Unused method
     */
    @Override
    public void dispose() {
    }
}
