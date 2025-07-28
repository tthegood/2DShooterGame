package me.mygdx.enday.Blessing;

import me.mygdx.enday.Screen.GameScreen;

public class HealthBlessing2 extends Blessing {
    /**
     * Construct the blessing object.
     */
    public HealthBlessing2() {
        //Initialize Variables
        super();
        Rarity = 1;
        description = "+70 health";
    }

    /**
     * Apply the blessing to the player
     */
    @Override
    public void apply() {
        GameScreen.healthAdd += 70;
    }
}
