package me.mygdx.enday.Blessing;

import me.mygdx.enday.Screen.GameScreen;

public class AttackBlessing extends Blessing {
    /**
     * Construct the blessing object.
     */
    public AttackBlessing() {
        //Initialize Variables
        super();
        Rarity = 1;
        description = "+40% attack";
    }

    /**
     * Apply the blessing to the player
     */
    @Override
    public void apply() {
        GameScreen.attackMultiplier += 40;
    }
}
