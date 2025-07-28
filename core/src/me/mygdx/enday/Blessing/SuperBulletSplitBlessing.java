package me.mygdx.enday.Blessing;

import me.mygdx.enday.Screen.GameScreen;

public class SuperBulletSplitBlessing extends Blessing {
    /**
     * Construct the blessing object.
     */
    public SuperBulletSplitBlessing() {
        //Initialize Variables
        super();
        Rarity = 3;
        description = "SplitBullet buff \nsplits 4 more bullet";
    }

    /**
     * Return the index of the blessing.
     * @return the index of the blessing.
     */
    @Override
    public int getIndex() {
        return 5;
    }

    /**
     * Apply the blessing to the player
     */
    @Override
    public void apply() {
        GameScreen.superSplit = true;
    }
}
