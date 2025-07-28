package me.mygdx.enday.Blessing;

import me.mygdx.enday.Screen.GameScreen;

public class DmgIncreaseBlessing extends Blessing {
    /**
     * Construct the blessing object.
     */
    public DmgIncreaseBlessing() {
        //Initialize Variables
        super();
        Rarity = 2;
        description = "+18% DmgIncrease";
    }

    /**
     * Apply the blessing to the player
     */
    @Override
    public void apply() {
        GameScreen.dmgIncrease += 18;
    }
}
