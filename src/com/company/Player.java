package com.company;

import java.util.ArrayList;

/**
 * this class contains informations about a player who want play
 * this game such as name & score & list of cards that he/she has.
 * also this class contains methods that can check whether list of player's card
 * is empty or no & print all exist cards in the list of player's cards.
 *
 * @author  mahmood-saniean
 * @since   2021-4-30
 * @version 15.0.2
 */
public class Player {
    //the name of player
    private String name;
    //the score of player
    private int    score;
    //the list of player's cards
    private ArrayList<Card> cards;

    /**
     * this constructor get a name and create new player.
     * @param name the name of player.
     */
    public Player(String name) {
        this.name = name;
        score = 0;
        cards = new ArrayList<Card>();
    }

    /**
     * this method add a card in the list of card.
     * @param card the card that we want add in the list.
     */
    public void addCard(Card card){
        cards.add(card);
    }

    /**
     * this method remove a card from list of card.
     * @param card the card that we want remove it.
     */
    public void removeCard(Card card){
        cards.remove(card);
    }

    /**
     * this method add score of a card to sum of player's score.
     * @param score the amount of score.
     */
    public void increament(int score){
        this.score += score;
    }

    /**
     * this method sub score of a card to sum of player's score.
     * @param score the amount of score.
     */
    public void decreamnet(int score){
        this.score -= score;
    }

    /**
     * this method return name of player to us.
     * @return name of player
     */
    public String getName() {
        return name;
    }

    /**
     * this method return sum of score that player collect.
     * @return sum of score.
     */
    public int getScore() {
        return score;
    }

    /**
     * this method print all exist cards in the list of player's cards.
     */
    public void printCards(){
        for (Card card : cards)
            card.print();
        System.out.println();
    }

    /**
     * this method checks whether the list of cards is empty or no.
     * @return true or false.
     */
    public boolean isEmpty(){
        if (cards.size() == 0)
            return true;
        else
            return false;
    }

    /**
     * this method return list of all cards that player have them.
     * @return list of cards.
     */
    public ArrayList<Card> getCards() {
        return cards;
    }
}
