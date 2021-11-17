package com.company;

/**
 * this class inherit from Card class.
 * this class can call Cardclass's constructor
 * and create a card.
 *
 * @author  mahmood-saneian
 * @since   2021-4-30
 * @version 15.0.2
 */
public class MotionCard extends Card{

    /**
     * this constructor get a color & a sign & a score and call Cardclass's constructor.
     * @param color the color of card
     * @param sign  the sign  of card
     * @param score the score of card
     */
    public MotionCard(String color, String sign, int score) {
        super(color, sign, score);
    }
}
