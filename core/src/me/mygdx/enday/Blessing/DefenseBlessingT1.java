package me.mygdx.enday.Blessing;

import me.mygdx.enday.Screen.GameScreen;

public class DefenseBlessingT1 extends Blessing {
    /**
     * Construct the blessing object.
     */
    public DefenseBlessingT1() {
        //Initialize Variables
        super();
        Rarity = 3;
        description = "+45% defense";
    }

    /**
     * Apply the blessing to the player
     */
    @Override
    public void apply() {
        GameScreen.defenseMultiplier += 45;
    }
}
