package com.company;

/**
 * this class inherit MotionCard class.
 * this class create a card by sign : B.
 *
 * @author  mahmood-saneian.
 * @since   2021-4-30
 * @version 15.0.2
 */

public class CardB extends MotionCard{

    /**
     * this constructor get color of card & call superclass's constructor
     * & give color & score & sign to superclass's constructor.
     * @param color the color of card.
     */
    public CardB(String color) {
        super(color, "B", 12);
    }
}
