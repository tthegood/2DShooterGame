package me.mygdx.enday.Blessing;

import me.mygdx.enday.Screen.GameScreen;

public class BaseDefenseBlessing extends Blessing {
    /**
     * Construct the blessing object.
     */
    public BaseDefenseBlessing() {
        //Initialize Variables
        super();
        Rarity = 2;
        description = "+5 base defense";
    }

    /**
     * Apply the blessing to the player
     */
    @Override
    public void apply() {
        GameScreen.baseDefense += 5;
    }
}
