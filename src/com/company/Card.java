package com.company;

import java.util.ArrayList;

/**
 * this class contains informations about a card
 * such as card's color & card's sign & card's score.
 * also this class can print card's information.
 *
 * @author  mahmood-saneian
 * @since   2021-4-30
 * @version 15.0.2
 */
public class Card {
    //the card's color
    private String color;
    //the card's sign
    private String sign;
    //the card's score
    private int    score;

    /**
     * this constructor get a color & a sign & a score then create a card.
     * @param color the color of card
     * @param sign  the sign  of card
     * @param score the score of card
     */
    public Card(String color, String sign, int score) {
        this.color = color;
        this.sign = sign;
        this.score = score;
    }

    /**
     * this method print sign of card according to color of card.
     */
    public void print(){
        switch (color){
                //if our card if Blue
            case "Blue":
                System.out.print(ConsoleColors.BLUE);
                break;
                //if our card is Black
            case "Black":
                System.out.print(ConsoleColors.WHITE);
                break;
                //if our Card is Red
            case "Red":
                System.out.print(ConsoleColors.RED);
                break;
                //if our card is Green
            case "Green":
                System.out.print(ConsoleColors.GREEN);
                break;
        }

        System.out.print(sign + "    "+ConsoleColors.RESET);
//        StringBuilder builder = new StringBuilder();
//        builder.append("----------").append('\n').append("|").append(sign).append("\t\t  |")
//                .append('\n').append("|\t\t  |").append('\n').append("|\t\t  |").append('\n').append("|").append("  \t").append(sign).append(" |")
//                .append('\n').append("--------");
//        System.out.print(builder);
//
    }

    /**
     * this method return color of card to us.
     * @return color of card.
     */
    public String getColor() {
        return color;
    }

    /**
     * this method return sign of card to us.
     * @return sign of card.
     */
    public String getSign() {
        return sign;
    }

    /**
     * this method return score of card to us
     * @return score of card.
     */
    public int getScore() {
        return score;
    }
}
