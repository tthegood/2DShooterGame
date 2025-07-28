/**
 * This is an abstract class, all methods is to be overwritten.
 */

package me.mygdx.enday.Blessing;

public class Blessing {
    //Variables
    String description;
    int Rarity;

    /**
     * Default Constructor to be overwritten
     */
    public Blessing() {
    }

    /**
     * return the description of the blessing
     * @return the description of the blessing
     */
    public String getDescription() {
        return description;
    }

    /**
     * return the rarity of the blessing
     * @return the rarity of the blessing
     */
    public int getRarity() {
        return Rarity;
    }

    /**
     * return the index of the blessing
     * @return the index of the blessing
     */
    public int getIndex() {
        return -1;
    }

    /**
     * Apply the blessing, to be overwritten.
     */
    public void apply() {

    }
}
