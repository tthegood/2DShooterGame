package me.mygdx.enday.Buff;

import me.mygdx.enday.Screen.GameScreen;

public class SpeedUp extends Buff {
    //Variables
    float duration;
    boolean picked;

    /**
     * Constructs a new Buff object with the specified position.
     * @param x The x coordinate of the buff's position.
     * @param y The y coordinate of the buff's position.
     */
    public SpeedUp(float x, float y) {
        //Initialize variables
        super(x, y);
        duration = 10f;
        picked = false;
    }

    /**
     * Return whether if the buff is picked up by the player.
     * @return whether if the buff is picked up by the player.
     */
    @Override
    public boolean pickedUp() {
        return picked;
    }

    /**
     * Apply the buff to the player
     * @param delta the time in seconds since last applied
     * @return whether if the buff expired
     */
    @Override
    public boolean apply(float delta) {
        picked = true;
        GameScreen.tempSpeedAdd += 45;
        duration -= delta;
        if(duration <= 0) return true;
        else return false;
    }

    /**
     * Returns the index of the buff.
     * @return the index of the buff.
     */
    @Override
    public int getIndex() {
        return 2;
    }
}
