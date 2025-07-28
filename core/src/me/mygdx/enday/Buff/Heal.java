package me.mygdx.enday.Buff;

import me.mygdx.enday.Screen.GameScreen;

public class Heal extends Buff {

    /**
     * Constructs a new Buff object with the specified position.
     * @param x The x coordinate of the buff's position.
     * @param y The y coordinate of the buff's position.
     */
    public Heal(float x, float y) {
        //Use default super constructor
        super(x, y);
    }

    /**
     * Apply the buff to the player
     * @param delta the time in seconds since last applied
     * @return whether if the buff expired
     */
    @Override
    public boolean apply(float delta) {
        //Vampire heals for more
        if(GameScreen.Ability == 1) {
            GameScreen.health += GameScreen.maxHealth*0.03;
        }
        if(GameScreen.Vampire) {
            GameScreen.health += GameScreen.maxHealth*0.06;
        }

        GameScreen.health += GameScreen.maxHealth*0.1;
        return true;
    }

    /**
     * Returns the index of the buff.
     * @return the index of the buff.
     */
    @Override
    public int getIndex() {
        return 4;
    }

    /**
     * Return whether if the buff is picked up by the player.
     * @return whether if the buff is picked up by the player.
     */
    @Override
    public boolean pickedUp() {
        return false;
    }
}
