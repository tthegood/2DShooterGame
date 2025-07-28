package me.mygdx.enday.Blessing;

import me.mygdx.enday.Screen.GameScreen;

public class DefenseBlessing extends Blessing {
    /**
     * Construct the blessing object.
     */
    public DefenseBlessing() {
        //Initialize Variables
        super();
        Rarity = 1;
        description = "+15% defense";
    }

    /**
     * Apply the blessing to the player
     */
    @Override
    public void apply() {
        GameScreen.defenseMultiplier += 15;
    }
}
