package com.company;


/**
 * this class inherit MotionCard class.
 * this class create a card by sign : 7.
 *
 * @author  mahmood-saneian.
 * @since   2021-4-30
 * @version 15.0.2
 */
public class CardSevenOther extends MotionCard{

    /**
     * this constructor get color of card & call superclass's constructor
     * & give color & score & sign to superclass's constructor.
     * @param color the color of card.
     */
    public CardSevenOther(String color) {
        super(color, "7", 10);
    }
}
