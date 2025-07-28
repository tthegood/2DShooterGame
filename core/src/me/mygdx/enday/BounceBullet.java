package me.mygdx.enday;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public class BounceBullet extends Bullet implements Disposable {

    /**
     * Constructs a new BounceBullet object with the specified position, direction, and speed.
     * @param x The x coordinate of the bullet's initial position.
     * @param y The y coordinate of the bullet's initial position.
     * @param direction The direction vector of the bullet.
     * @param speed The speed of the bullet.
     */
    public BounceBullet(float x, float y, Vector2 direction, float speed) {
        super(x, y, direction, speed); //Create a BounceBullet using Bullet's default constructor
    }

    /**
     * Get the type of the bullet
     * @return the index indicating the type of the bullet
     */
    @Override
    public int getType() {
        return 2;
    }
}
