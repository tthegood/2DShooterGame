package me.mygdx.enday.Blessing;

import me.mygdx.enday.Screen.GameScreen;

public class DefenseBlessingT2 extends Blessing {
    /**
     * Construct the blessing object.
     */
    public DefenseBlessingT2() {
        //Initialize Variables
        super();
        Rarity = 2;
        description = "+30% defense";
    }

    /**
     * Apply the blessing to the player
     */
    @Override
    public void apply() {
        GameScreen.defenseMultiplier += 30;
    }
}
