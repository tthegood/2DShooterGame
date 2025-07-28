package me.mygdx.enday.Blessing;

import me.mygdx.enday.Screen.GameScreen;

public class AttackSpeedBlessingT2 extends Blessing {
    /**
     * Construct the blessing object.
     */
    public AttackSpeedBlessingT2() {
        //Initialize Variables
        super();
        Rarity = 2;
        description = "+2 attackSpeed";
    }

    /**
     * Apply the blessing to the player
     */
    @Override
    public void apply() {
        GameScreen.attackSpeedAdd += 2;
    }
}
