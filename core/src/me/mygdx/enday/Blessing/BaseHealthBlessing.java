package me.mygdx.enday.Blessing;

import me.mygdx.enday.Screen.GameScreen;

public class BaseHealthBlessing extends Blessing {
    /**
     * Construct the blessing object.
     */
    public BaseHealthBlessing() {
        //Initialize Variables
        super();
        Rarity = 2;
        description = "+70 base health";
    }

    /**
     * Apply the blessing to the player
     */
    @Override
    public void apply() {
        GameScreen.baseHealth += 70;
    }
}
