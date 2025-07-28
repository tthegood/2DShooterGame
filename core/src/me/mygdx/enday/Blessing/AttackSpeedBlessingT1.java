package me.mygdx.enday.Blessing;

import me.mygdx.enday.Screen.GameScreen;

public class AttackSpeedBlessingT1 extends Blessing {
    /**
     * Construct the blessing object.
     */
    public AttackSpeedBlessingT1() {
        //Initialize Variables
        super();
        Rarity = 3;
        description = "+3.5 attackSpeed";
    }

    /**
     * Apply the blessing to the player
     */
    @Override
    public void apply() {
        GameScreen.attackSpeedAdd += 3.5;
    }
}
