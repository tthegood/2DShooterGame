package me.mygdx.enday.Blessing;

import me.mygdx.enday.Screen.GameScreen;

public class AttackSpeedBlessing extends Blessing {
    /**
     * Construct the blessing object.
     */
    public AttackSpeedBlessing() {
        //Initialize Variables
        super();
        Rarity = 1;
        description = "+1 attackSpeed";
    }

    /**
     * Apply the blessing to the player
     */
    @Override
    public void apply() {
        GameScreen.attackSpeedAdd += 1;
    }
}
