package me.mygdx.enday.Blessing;

import me.mygdx.enday.Screen.GameScreen;

public class AttackBlessingT2 extends Blessing {
    /**
     * Construct the blessing object.
     */
    public AttackBlessingT2() {
        //Initialize Variables
        super();
        Rarity = 2;
        description = "+80% attack";
    }

    /**
     * Apply the blessing to the player
     */
    @Override
    public void apply() {
        GameScreen.attackMultiplier += 80;
    }
}
