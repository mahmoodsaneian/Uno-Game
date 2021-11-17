package com.company;

import java.util.ArrayList;
import java.util.Random;

/**
 * this class manage cards of game.
 * first , create all cards and save in a list.
 * when  , a card to be picked up from list , this card remove from exist cards.
 * also this class can return a random card to player.
 *
 * @author  mahmood-saneian
 * @since   2021-4-30
 * @version 15.0.2
 */

public class ManageCards {
    //the list of card
    private ArrayList<Card> cards;

    /**
     * this constructor allocate memory for list of cards.
     * also this is call methods that create cards and store in the list.
     */
    public ManageCards() {
       cards = new ArrayList<>();
       createOrdinaryCards();
       createCardsTwo();
       createCardsSeven();
       createCardsEight();
       createCardsTen();
       createCardsA();
       createCardsB();
    }

    /*
     * this method create all ordinary cards and store them
     * in the list of cards.
     * this method just once call.
     * because we cannot have arbitrary number of ordinary cards.
     */
    private void createOrdinaryCards(){
        for (int i = 3;i < 7;i++){
            OrdinaryCard ordinaryCard = new OrdinaryCard("Blue", String.valueOf(i), i);
            cards.add(ordinaryCard);
        }
        for (int i = 3;i < 7;i++){
            OrdinaryCard ordinaryCard = new OrdinaryCard("Black", String.valueOf(i), i);
            cards.add(ordinaryCard);
        }
        for (int i = 3;i < 7;i++){
            OrdinaryCard ordinaryCard = new OrdinaryCard("Red", String.valueOf(i), i);
            cards.add(ordinaryCard);
        }
        for (int i = 3;i < 7;i++){
            OrdinaryCard ordinaryCard = new OrdinaryCard("Green", String.valueOf(i), i);
            cards.add(ordinaryCard);
        }
        OrdinaryCard ordinaryCard1 = new OrdinaryCard("Blue" , "C", 12);
        OrdinaryCard ordinaryCard2 = new OrdinaryCard("Blue" , "D", 13);
        OrdinaryCard ordinaryCard3 = new OrdinaryCard("Black", "C", 12);
        OrdinaryCard ordinaryCard4 = new OrdinaryCard("Black", "D", 13);
        OrdinaryCard ordinaryCard5 = new OrdinaryCard("Red"  , "C", 12);
        OrdinaryCard ordinaryCard6 = new OrdinaryCard("Red"  , "D", 13);
        OrdinaryCard ordinaryCard7 = new OrdinaryCard("Green", "C", 12);
        OrdinaryCard ordinaryCard8 = new OrdinaryCard("Green", "D", 13);
        cards.add(ordinaryCard1);
        cards.add(ordinaryCard2);
        cards.add(ordinaryCard3);
        cards.add(ordinaryCard4);
        cards.add(ordinaryCard5);
        cards.add(ordinaryCard6);
        cards.add(ordinaryCard7);
        cards.add(ordinaryCard8);
    }

    /*
     *this method creates all cards by sign 2.
     * then save them in the list of cards.
     * this method just once call.
     * because we cannot have arbitrary number of Two card.
     */
    private void createCardsTwo(){
        CardTwo cardTwo1 = new CardTwo("Blue");
        CardTwo cardTwo2 = new CardTwo("Black");
        CardTwo cardTwo3 = new CardTwo("Red");
        CardTwo cardTwo4 = new CardTwo("Green");
        cards.add(cardTwo1);
        cards.add(cardTwo2);
        cards.add(cardTwo3);
        cards.add(cardTwo4);
    }

    /*
     * this method creates all cards by sign 7.
     * then save them in the list of cards.
     * this method just once call.
     * because we cannot have arbitrary number of Seven card.
     */
    private void createCardsSeven(){
        CardSevenBlack cardSevenBlack = new CardSevenBlack();
        CardSevenOther cardSevenRed   = new CardSevenOther("Red");
        CardSevenOther cardSevenGreen = new CardSevenOther("Green");
        CardSevenOther cardSevenBlue  = new CardSevenOther("Blue");
        cards.add(cardSevenBlue);
        cards.add(cardSevenRed);
        cards.add(cardSevenGreen);
        cards.add(cardSevenBlack);
    }

    /*
     * this method creates all cards by sign 8.
     * then save them in the list of cards.
     * this method just once call.
     * because we cannot have arbitrary number of Eight card.
     */
    private void createCardsEight(){
        CardEight cardEightRed   = new CardEight("Red");
        CardEight cardEightGreen = new CardEight("Green");
        CardEight cardEightBlack = new CardEight("Black");
        CardEight cardEightBlue  = new CardEight("Blue");
        cards.add(cardEightRed);
        cards.add(cardEightGreen);
        cards.add(cardEightBlack);
        cards.add(cardEightBlue);
    }

    /*
     *this method creates all cards by sign 8.
     * then save them in the list of cards.
     * this method just once call.
     * because we cannot have arbitrary number of Eight card.
     */
    private void createCardsTen(){
        CardTen cardTenRed   = new CardTen("Red");
        CardTen cardTenGreen = new CardTen("Green");
        CardTen cardTenBlue  = new CardTen("Blue");
        CardTen cardTenBlack = new CardTen("Black");
        cards.add(cardTenRed);
        cards.add(cardTenGreen);
        cards.add(cardTenBlue);
        cards.add(cardTenBlack);
    }

    /*
     *this method creates all cards by sign A.
     * then save them in the list of cards.
     * this method just once call.
     * because we cannot have arbitrary number of card : A.
     */
    private void createCardsA(){
        CardA cardARed   = new CardA("Red");
        CardA cardAGreen = new CardA("Green");
        CardA cardABlue  = new CardA("Blue");
        CardA cardABlack = new CardA("Black");
        cards.add(cardARed);
        cards.add(cardAGreen);
        cards.add(cardABlue);
        cards.add(cardABlack);
    }

    /*
     *this method creates all cards by sign B.
     * then save them in the list of cards.
     * this method just once call.
     * because we cannot have arbitrary number of card : B.
     */
    private void createCardsB(){
        CardB cardBRed   = new CardB("Red");
        CardB cardBGreen = new CardB("Green");
        CardB cardBBlue  = new CardB("Blue");
        CardB cardBBlack = new CardB("Black");
        cards.add(cardBRed);
        cards.add(cardBGreen);
        cards.add(cardBBlue);
        cards.add(cardBBlack);
    }

    /**
     * this method give list of all cards to us.
     * @return list of cards.
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * this method find a random card & return it to us.
     * @return randim card.
     */
    public Card getRandomCard(){
        int random = new Random().nextInt(cards.size());
        Card card = cards.get(random);
        cards.remove(random);
        return card;
    }

    /**
     * this method remove a card from list of exist cards.
     * @param card a card that we want remove it from list of exist cards.
     */
    public void removeCard(Card card){
        cards.remove(card);
    }
}
