package me.mygdx.enday.Blessing;

import me.mygdx.enday.Screen.GameScreen;

public class DmgMitigationBlessing extends Blessing {
    /**
     * Construct the blessing object.
     */
    public DmgMitigationBlessing() {
        //Initialize Variables
        super();
        Rarity = 2;
        description = "+7% DmgMitigation";
    }

    /**
     * Apply the blessing to the player
     */
    @Override
    public void apply() {
        GameScreen.dmgMitigation += 7;
    }
}
