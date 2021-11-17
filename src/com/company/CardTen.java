package com.company;


/**
 * this class inherit MotionCard class.
 * this class create a card by sign : 10.
 *
 * @author  mahmood-saneian.
 * @since   2021-4-30
 * @version 15.0.2
 */
public class CardTen extends MotionCard{

    /**
     * this constructor get color of card & call superclass's constructor
     * & give color & score & sign to superclass's constructor.
     * @param color the color of card.
     */
    public CardTen(String color) {
        super(color, "10", 10);
    }
}
