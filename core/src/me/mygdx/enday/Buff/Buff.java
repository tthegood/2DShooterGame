/**
 * This is an abstract class, all methods is to be overwritten.
 */

package me.mygdx.enday.Buff;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;

public class Buff implements Disposable {
    //Constants
    public static final float size = 45;

    //Variable
    float droppedTime;
    float x;
    float y;
    Rectangle rect;

    /**
     * Constructs a new Buff object with the specified position.
     * @param x The x coordinate of the buff's position.
     * @param y The y coordinate of the buff's position.
     */
    public Buff(float x, float y) {
        //Initialize variables
        droppedTime = 0f;
        this.x = x;
        this.y = y;
        rect = new Rectangle(x, y, size, size);
    }

    /**
     * Apply the buff to the player
     * @param delta the time in seconds since last applied
     * @return whether if the buff expired
     */
    public boolean apply(float delta) {
        return true;
    }

    /**
     * Return the rectangle hitbox of the buff.
     * @return the rectangle hitbox of the buff.
     */
    public Rectangle getRect() {
        return rect;
    }

    /**
     * Return whether if the buff is picked up by the player.
     * @return whether if the buff is picked up by the player.
     */
    public boolean pickedUp() {
        return false;
    }

    /**
     * Update the buff for how long it is dropped on the ground
     * @param delta the time in seconds since last update
     * @return whether if it is expired
     */
    public boolean update(float delta) {
        droppedTime += delta;
        return droppedTime > 10;
    }

    /**
     * Returns the x coordinate of the bullet's position.
     * @return The x coordinate of the bullet's position.
     */
    public float getX() {
        return x;
    }

    /**
     * Returns the y coordinate of the bullet's position.
     * @return The y coordinate of the bullet's position.
     */
    public float getY() {
        return y;
    }

    /**
     * Returns the index of the buff.
     * @return the index of the buff.
     */
    public int getIndex() {
        return -1;
    }

    /**
     * Unused method
     */
    @Override
    public void dispose() {
    }
}
