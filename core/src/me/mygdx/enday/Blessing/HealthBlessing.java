package me.mygdx.enday.Blessing;

import me.mygdx.enday.Screen.GameScreen;

public class HealthBlessing extends Blessing {
    /**
     * Construct the blessing object.
     */
    public HealthBlessing() {
        //Initialize Variables
        super();
        Rarity = 1;
        description = "+20% health";
    }

    /**
     * Apply the blessing to the player
     */
    @Override
    public void apply() {
        GameScreen.healthMultiplier += 20;
    }
}
