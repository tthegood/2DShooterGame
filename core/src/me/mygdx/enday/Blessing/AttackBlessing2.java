package me.mygdx.enday.Blessing;

import me.mygdx.enday.Screen.GameScreen;

public class AttackBlessing2 extends Blessing {
    /**
     * Construct the blessing object.
     */
    public AttackBlessing2() {
        //Initialize Variables
        super();
        Rarity = 1;
        description = "+30 attack";
    }

    /**
     * Apply the blessing to the player
     */
    @Override
    public void apply() {
        GameScreen.attackAdd += 30;
    }
}
