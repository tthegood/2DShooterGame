package me.mygdx.enday.Blessing;

import me.mygdx.enday.Screen.GameScreen;

public class SpeedBlessing extends Blessing {
    /**
     * Construct the blessing object.
     */
    public SpeedBlessing() {
        //Initialize Variables
        super();
        Rarity = 1;
        description = "+25 speed";
    }

    /**
     * Apply the blessing to the player
     */
    @Override
    public void apply() {
        GameScreen.speedAdd += 25;
    }
}
