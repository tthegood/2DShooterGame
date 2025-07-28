package me.mygdx.enday.Blessing;

import me.mygdx.enday.Screen.GameScreen;

public class DefenseBlessing2 extends Blessing {
    /**
     * Construct the blessing object.
     */
    public DefenseBlessing2() {
        //Initialize Variables
        super();
        Rarity = 1;
        description = "+6 defense";
    }

    /**
     * Apply the blessing to the player
     */
    @Override
    public void apply() {
        GameScreen.defenseAdd += 6;
    }
}
