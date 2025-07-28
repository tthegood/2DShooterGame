package me.mygdx.enday.Blessing;

import me.mygdx.enday.Screen.GameScreen;

public class BaseAttackBlessing extends Blessing {
    /**
     * Construct the blessing object.
     */
    public BaseAttackBlessing() {
        //Initialize Variables
        super();
        Rarity = 2;
        description = "+10 base attack";
    }

    /**
     * Apply the blessing to the player
     */
    @Override
    public void apply() {
        GameScreen.baseAttack += 10;
    }
}
