package me.mygdx.enday.Blessing;

import me.mygdx.enday.Screen.GameScreen;

public class AttackBlessingT1 extends Blessing {
    /**
     * Construct the blessing object.
     */
    public AttackBlessingT1() {
        //Initialize Variables
        super();
        Rarity = 3;
        description = "+150% attack";
    }

    /**
     * Apply the blessing to the player
     */
    @Override
    public void apply() {
        GameScreen.attackMultiplier += 150;
    }
}
