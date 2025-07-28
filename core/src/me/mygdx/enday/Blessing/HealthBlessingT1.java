package me.mygdx.enday.Blessing;

import me.mygdx.enday.Screen.GameScreen;

public class HealthBlessingT1 extends Blessing {
    /**
     * Construct the blessing object.
     */
    public HealthBlessingT1() {
        //Initialize Variables
        super();
        Rarity = 3;
        description = "+70% health";
    }

    /**
     * Apply the blessing to the player
     */
    @Override
    public void apply() {
        GameScreen.healthMultiplier += 70;
    }
}
