package me.mygdx.enday.Blessing;

import me.mygdx.enday.Screen.GameScreen;

public class HealthBlessingT2 extends Blessing {
    /**
     * Construct the blessing object.
     */
    public HealthBlessingT2() {
        //Initialize Variables
        super();
        Rarity = 2;
        description = "+40% health";
    }

    /**
     * Apply the blessing to the player
     */
    @Override
    public void apply() {
        GameScreen.healthMultiplier += 40;
    }
}
