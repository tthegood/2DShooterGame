package me.mygdx.enday.Blessing;

import me.mygdx.enday.Screen.GameScreen;

public class AbilityBuffBlessing extends Blessing {
    /**
     * Construct the blessing object.
     */
    public AbilityBuffBlessing() {
        //Initialize Variables
        super();
        Rarity = 3;
        description = "Increase ability \nduration, decrease CD";
    }

    /**
     * Return the index of the blessing.
     * @return the index of the blessing.
     */
    @Override
    public int getIndex() {
        return 4;
    }

    /**
     * Apply the blessing to the player
     */
    @Override
    public void apply() {
        GameScreen.abilityCD *= 0.75;
        GameScreen.abilityDuration *= 1.4;
    }
}
