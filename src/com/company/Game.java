package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 * this class manages game.
 * get inputs from user and call suitable methods.
 * also give cards to players & print suitable messages.
 * when ,game is finished , print names of players with their scores.
 *
 * @author mahmood-saneian
 * @version 15.0.2
 * @since 2021-4-30
 */
public class Game {

    //object from Scanner class
    private static Scanner scanner = new Scanner(System.in);
    //object from ManageCards class
    private static ManageCards manageCards = new ManageCards();

    public static void main(String[] args) {
        //list of players who want play game with together
        ArrayList<Player> players = new ArrayList<Player>();
        //number of players
        int numberOfPlayer = 0;

        System.out.println("1.play with bot" + "\n"
                + "2.play with friends");
        int choose = scanner.nextInt();

        switch (choose) {
            case 1:
                String name = getName();
                Player player = new Player(name);
                players.add(player);
                numberOfPlayer = getNumberOfPlayers();
                for (int i = 0; i < numberOfPlayer - 1; i++) {
                    player = new Player("computer" + String.valueOf(i + 1));
                    players.add(player);
                }
                break;
            case 2:
                numberOfPlayer = getNumberOfPlayers();
                System.out.println("enter characteristics of players");
                for (int i = 0; i < numberOfPlayer; i++) {
                    name = getName();
                    player = new Player(name);
                    players.add(player);
                }
                break;
        }

        //give 7 random cards to all players
        for (int i = 0; i < numberOfPlayer; i++) {
             chooseBetweenCardsOnTheSource(7, players.get(i));
        }

        //the start card
        Card startCard = manageCards.getRandomCard();
        manageCards.removeCard(startCard);
        //the player who must start the game
        int random = new Random().nextInt(players.size());
        System.out.println("\n" + players.get(random).getName() + " start the game");


        if (choose == 2)
            multipleGame(players, numberOfPlayer, startCard, random, manageCards.getCards());
        else if (choose == 1)
            singleGame(players, numberOfPlayer, startCard, random, manageCards.getCards());
    }

    /**
     * this method get name from user.
     *
     * @return name.
     */
    public static String getName() {
        System.out.println("enter name of player");
        String name = scanner.next();
        return name;
    }

    /**
     * this method get number of players who want play game with together
     *
     * @return
     */
    public static int getNumberOfPlayers() {
        System.out.println("enter number of players" + "\n" + "enter 3 or 4 or 5");
        int numberOfPlayer = 0;
        while (true) {
            numberOfPlayer = scanner.nextInt();
            if (numberOfPlayer > 2 && numberOfPlayer < 6)
                break;
            else
                System.out.println("invalid input");
        }
        return numberOfPlayer;
    }

    /**
     * This method runs a multiple-player game
     *
     * @param players         list of all players
     * @param numberOfPlayers number of players who want play
     * @param startCard       the start card
     * @param startPlayer     index of start player in list
     * @param remainCards     list of remain cards in the source
     */
    public static void multipleGame(ArrayList<Player> players, int numberOfPlayers, Card startCard, int startPlayer, ArrayList<Card> remainCards) {
        Card lastCard = startCard;
        int turn = startPlayer;
        String direction = "clockwise";
        ArrayList<Card> sourceCards = remainCards;
        String sentenceColor = lastCard.getColor();

        //loop of game
        mainGame:
        while (true) {
            //Check whether game is finished or no
            for (int i = 0; i < numberOfPlayers; i++) {
                if (players.get(i).isEmpty()) {
                    System.out.println(players.get(i).getName() + " finished all cards");
                    System.out.println("scores : ");
                    showScores(players);
                    break mainGame;
                }
            }

            ArrayList<Card> playerCards = players.get(turn).getCards();
            String sign = " ";
            String color = " ";
            //Continue Game
            //Check whether palyer can play game or no
            if (checkUserCondition(playerCards, lastCard.getSign(), sentenceColor)) {
                System.out.print("last card on the ground : ");
                lastCard.print();
                System.out.println("the color of sentence : " + sentenceColor);
                System.out.println("\n" + "Turn : " + players.get(turn).getName() + "\n" +
                        "choose between following cards");
                players.get(turn).printCards();
                System.out.println("enter sign of card");
                sign = scanner.next();
                System.out.println("enter color of card");
                color = scanner.next();
                //give a card from source to user because he/she doesn't have suitable card(last card in the source).
            } else {
                int lastIndex = sourceCards.size() - 1;
                Card card = sourceCards.get(lastIndex);
                playerCards.add(card);
                manageCards.removeCard(card);
                sign = card.getSign();
                color = card.getColor();
            }
            boolean find = false;

            for (Card card : playerCards) {
                //Find selected card
                if (card.getSign().equals(sign) && card.getColor().equals(color)) {
                    //Check conditions
                    if (card.getColor().equals(sentenceColor) || card.getSign().equals(lastCard.getSign())) {
                        find = true;
                        int lastTurn = turn;
                        //Increase turn
                        turn = increaseTurn(turn, direction, numberOfPlayers);
                        sourceCards.add(lastCard);
                        lastCard = card;
                        sentenceColor = lastCard.getColor();
                        //Special modes
                        if (card instanceof CardTwo) {
                            String name = getName();
                            int index = findPlayer(name, players);
                            int random = new Random(playerCards.size()).nextInt();
                            Card card1 = playerCards.get(random);
                            players.get(lastTurn).removeCard(card1);
                            players.get(index).addCard(card1);
                            players.get(lastTurn).decreamnet(card1.getScore());
                            players.get(index).increament(card1.getScore());

                        } else if (card instanceof CardSevenOther) {
                            players.get(lastTurn).increament(card.getScore());
                            System.out.println("Two cards were given to the person after you. list of your cards : ");
                            chooseBetweenCardsOnTheSource(2, players.get(turn));
                            printCards(players.get(turn).getCards());

                        } else if (card instanceof CardSevenBlack) {
                            players.get(lastTurn).increament(card.getScore());
                            System.out.println("four cards were given to the person after you. list of your cards : ");
                            chooseBetweenCardsOnTheSource(4, players.get(turn));
                            printCards(players.get(turn).getCards());

                        } else if (card instanceof CardEight) {
                            players.get(lastTurn).increament(card.getScore());
                            turn = decreaseTurn(turn, direction, numberOfPlayers);

                        } else if (card instanceof CardTen) {
                            players.get(lastTurn).increament(card.getScore());
                            decreaseTurn(turn, direction, numberOfPlayers);
                            decreaseTurn(turn, direction, numberOfPlayers);
                            if (direction.equals("clockwise"))
                                direction = "anticlockwise";
                            else
                                direction = "clockwise";

                        } else if (card instanceof CardA) {
                            players.get(lastTurn).increament(card.getScore());
                            turn = increaseTurn(turn, direction, numberOfPlayers);

                        } else if (card instanceof CardB) {
                            players.get(lastTurn).increament(card.getScore());
                            System.out.println("please choose color of sentence");
                            String input = scanner.next();
                            if (input.equals("Blue") || input.equals("Black") || input.equals("Green") || input.equals("Red"))
                                sentenceColor = input;

                        } else if (card instanceof OrdinaryCard) {
                            players.get(lastTurn).increament(card.getScore());
                        }else {
                            System.out.println("invalid input");
                        }

                        players.get(lastTurn).removeCard(card);
                        break;
                    }
                }
            }
            //if card isn't found
            if (find == false) {
                System.out.println("You do not have a suitable card");
                increaseTurn(turn, direction, numberOfPlayers);
            }
            //End of while
        }
    }

    /**
     * this method runs a single person with some computer player game.
     * @param players         list of all players.
     * @param numberOfPlayers number of players who want play game
     * @param startCard       the start card
     * @param startPlayer     the index of start player in the list
     * @param remainCard      list of remain cards in source
     */
    public static void singleGame(ArrayList<Player> players, int numberOfPlayers, Card startCard, int startPlayer, ArrayList<Card> remainCard) {
        int turn = startPlayer;
        Card lastCard = startCard;
        String direction = "clockwise";
        ArrayList<Card> sourceCards = remainCard;
        String sentenceColor = lastCard.getColor();

        mainGame:
        while (true) {
            //Check whether game is finished or no
            for (int i = 0; i < numberOfPlayers; i++) {
                if (players.get(i).isEmpty()) {
                    System.out.println(players.get(i).getName() + " finished all cards");
                    System.out.println("scores : ");
                    showScores(players);
                    break mainGame;
                }
            }
            //Continue game
            String sign = " ";
            String color = " ";
            System.out.print("last card on the ground : ");
            lastCard.print();
            System.out.println("\n" + "the color of the sentence : " + sentenceColor);
            //if turn of user
            if (turn == 0) {
                if (checkUserCondition(players.get(turn).getCards(), lastCard.getSign(), sentenceColor)) {
                    System.out.println("\n" + "Turn : " + players.get(turn).getName() + "\n" +
                            "choose between following cards");
                    players.get(turn).printCards();
                    System.out.println("enter sign of card");
                    sign = scanner.next();
                    System.out.println("enter color of card");
                    color = scanner.next();
                } else {
                    System.out.println("turn of you but you don't have suitable card.last card in the source cards give you");
                    int lastIndex = sourceCards.size() - 1;
                    Card card = sourceCards.get(lastIndex);
                    players.get(turn).addCard(card);
                    manageCards.removeCard(card);
                    sign = card.getSign();
                    color = card.getColor();
                }
                //if computer turn
            } else {
                System.out.println("Turn : " + players.get(turn).getName());
                if (checkUserCondition(players.get(turn).getCards(), lastCard.getSign(), sentenceColor)) {
                    Card card = computerPlayGame(players.get(turn).getCards(), lastCard.getSign(), sentenceColor);
                    sign = card.getSign();
                    color = card.getColor();
                } else {
                    int lastIndex = sourceCards.size() - 1;
                    Card card = sourceCards.get(lastIndex);
                    players.get(turn).addCard(card);
                    manageCards.removeCard(card);
                    sign = card.getSign();
                    color = card.getColor();
                }
            }

            boolean find = false;

            for (Card card : players.get(turn).getCards()) {
                //Find Selected Card
                if (card.getSign().equals(sign) && card.getColor().equals(color)) {
                    //if conditions are true
                    if (card.getColor().equals(sentenceColor) || card.getSign().equals(lastCard.getSign())) {
                        find = true;
                        int lastTurn = turn;
                        //Increse turn
                        turn = increaseTurn(turn, direction, numberOfPlayers);
                        sourceCards.add(lastCard);
                        lastCard = card;
                        sentenceColor = lastCard.getColor();
                        //Special modes
                        if (card instanceof CardTwo) {
                            int index = lastTurn;
                            if (lastTurn == 0) {
                                String name = getName();
                                index = findPlayer(name, players);
                            } else {
                                do {
                                    index = new Random().nextInt(players.get(lastTurn).getCards().size() - 1);
                                } while (index != lastTurn);
                            }
                            int random = new Random().nextInt(players.get(lastTurn).getCards().size());
                            Card card1 = players.get(lastTurn).getCards().get(random);
                            players.get(lastTurn).removeCard(card1);
                            players.get(index).addCard(card1);
                            players.get(lastTurn).decreamnet(card1.getScore());
                            players.get(index).increament(card1.getScore());

                        } else if (card instanceof CardSevenOther) {
                            players.get(lastTurn).increament(card.getScore());
                            System.out.println("Two cards were given to the next person.");
                            chooseBetweenCardsOnTheSource(2, players.get(turn));

                        } else if (card instanceof CardSevenBlack) {
                            players.get(lastTurn).increament(card.getScore());
                            System.out.println("four cards were given to the next person");
                            chooseBetweenCardsOnTheSource(4, players.get(turn));

                        } else if (card instanceof CardEight) {
                            players.get(lastTurn).increament(card.getScore());
                            turn = decreaseTurn(turn, direction, numberOfPlayers);

                        } else if (card instanceof CardTen) {
                            players.get(lastTurn).increament(card.getScore());
                            if (direction.equals("clockwise"))
                                direction = "anticlockwise";
                            else
                                direction = "clockwise";

                        } else if (card instanceof CardA) {
                            players.get(lastTurn).increament(card.getScore());
                            turn = increaseTurn(turn, direction, numberOfPlayers);

                        } else if (card instanceof CardB) {
                                players.get(lastTurn).increament(card.getScore());
                                if (turn == 0){
                                    System.out.println("choose color of the sentence");
                                    String input = scanner.next();
                                    if (input.equals("Blue") || input.equals("Black") || input.equals("Green") || input.equals("Red"))
                                        sentenceColor = input;
                                }else {
                                    int random = new Random().nextInt(3);
                                    if (random == 0)
                                        sentenceColor = "Blue";
                                    if (random == 1)
                                        sentenceColor = "Black";
                                    if (random == 2)
                                        sentenceColor = "Red";
                                    if (random == 3)
                                        sentenceColor = "Green";
                                }
                        }else if (card instanceof OrdinaryCard) {
                            players.get(lastTurn).increament(card.getScore());
                        }else {
                            System.out.println("invalid input");
                        }

                        players.get(lastTurn).removeCard(card);
                        break;
                    }
                }
                //End for loop
            }
            //if card isn't found
            if (find == false) {
                System.out.println("player cannot play game");
            }
            showUpdateCards(players);
            //End while loop
        }

    }

    /**
     * this method increase index of turn(on digit)
     * @param turn             index of turn.
     * @param direction        the direction of game.
     * @param numberOfPlayers  number of players who play game.
     * @return updetad turn
     */
    public static int increaseTurn(int turn, String direction, int numberOfPlayers) {
        if (direction.equals("clockwise")) {
            turn = (turn + 1) % numberOfPlayers;
        } else {
            turn = (turn - 1);
            if (turn < 0)
                turn = numberOfPlayers;
        }
        return turn;
    }

    /**
     * this method decrease index of turn(on digit)
     * @param turn             index of turn.
     * @param direction        the direction of game.
     * @param numberOfPlayers  number of players who play game.
     * @return updetad turn
     */
    public static int decreaseTurn(int turn, String direction, int numberOfPlayers) {
        if (direction.equals("clockwise")) {
            turn = (turn - 1);
            if (turn < 0)
                turn = numberOfPlayers;
        } else {
            turn = (turn + 1) % numberOfPlayers;
        }
        return turn;
    }

    /**
     * this method give random card to player
     * @param number number of random card that we want give to player
     * @param player the player that we want give card them.
     */
    public static void chooseBetweenCardsOnTheSource(int number, Player player) {
        for (int i = 0; i < number; i++) {
            Card card = manageCards.getRandomCard();
            manageCards.removeCard(card);
            player.addCard(card);
            player.increament(card.getScore());
        }
    }

    /**
     * this method print all exist cards in a list.
     * @param cards list of cards that we want print them.
     */
    public static void printCards(ArrayList<Card> cards) {
        for (Card card : cards)
            card.print();
        System.out.println();

    }

    /**
     * thes method find a player in the list of player
     * @param name    name of player who we want find it.
     * @param players list of all players.
     * @return index of player in the list.
     */
    public static int findPlayer(String name, ArrayList<Player> players) {
        int index = 6;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().equals(name))
                index = i;
        }
        return index;
    }

    /**
     * this method shows names and scores of all players.
     * also sort scores.
     * @param players list of all players.
     */
    public static void showScores(ArrayList<Player> players) {
        Iterator<Player> it = players.iterator();
        while (it.hasNext()) {
            Player player = it.next();
            int min = player.getScore();
            Player tmp = player;
            for (Player player1 : players) {
                if (player1.getScore() < min) {
                    min = player1.getScore();
                    tmp = player1;
                }
            }
            System.out.println(tmp.getName() + " : " + tmp.getScore());
            players.remove(tmp);
        }
    }

    /**
     * this method checks whether a player can play game or no.
     * in other words, if player has a card that equals sign or color by last card return true.
     * @param playerCards list of all cards of player
     * @param sign        sign of last card
     * @param color       color of the sentence
     * @return
     */
    public static boolean checkUserCondition(ArrayList<Card> playerCards, String sign, String color) {
        for (Card card : playerCards) {
            if (card.getColor().equals(color) || card.getSign().equals(sign))
                return true;
        }
        return false;
    }

    /**
     * This method handles computer selections
     * @param cards list of cards of computer
     * @param sign  sign of last card
     * @param color color of the sentence
     * @return selected card.
     */
    public static Card computerPlayGame(ArrayList<Card> cards, String sign, String color) {
        Card selectedCard = cards.get(0);
        for (Card card : cards) {
            if (card.getSign().equals(sign) || card.getSign().equals(color)) {
                selectedCard = card;
                break;
            }
        }
        return selectedCard;
    }

    /**
     * this method print cards of all players after each move
     * in the single player game.
     * @param players list of all players.
     */
    public static void showUpdateCards(ArrayList<Player> players) {
        System.out.println("Cards until the end of this hand");
        for (Player player : players) {
            System.out.print(player.getName() + " : ");
            for (Card card : player.getCards()) {
                card.print();
            }
            System.out.println();
        }
    }
}
