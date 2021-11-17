package com.company;


/**
 * this class inherit MotionCard class.
 * this class create a card by sign : 7 & color : Black.
 *
 * @author  mahmood-saneian.
 * @since   2021-4-30
 * @version 15.0.2
 */
public class CardSevenBlack extends MotionCard{

    /**
     * this constructor call superclass's constructor & give
     * color and score and sign to superclass's constructor.
     */
    public CardSevenBlack() {
        super("Black", "7", 15);
    }
}
