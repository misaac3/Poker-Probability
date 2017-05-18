import java.util.Random;
import java.util.Scanner;
import javax.swing.*;

/**
 * Created by School on 4/5/2017.
 */
public class Main {
    public static void main(String[] args) {


        Deck deck = new Deck();

        deck = shuffleDeck(deck);
        deck.printDeck();

        Card[] tableCards = new Card[5];
        Card card1 = deck.removeCard();
        Card card2 = deck.removeCard();
        Card card3 = deck.removeCard();
        Card card4 = deck.removeCard();
        Card card5 = deck.removeCard();

        Table table = new Table(card1, card2, card3, card4, card5);

        int numPlayer = 3;
        Player[] playerList = new Player[numPlayer];
        for(int i = 0; i < playerList.length; i++) {
            playerList[i] = new Player("Player" + (i+1), deck.removeCard(), deck.removeCard(), table);
        }

        for (int i = 0; i < playerList.length; i++) {
            System.out.println(playerList[i]);
        }

        System.out.println();

        printAndUpdateStraights(table, playerList);

        for (int i = 1; i < 4; i++) {


            if (i == 1) {
                table.drawFlop();
            }
            if (i == 2) {
                table.drawTurn();
            }
            if (i == 3) {
                table.drawRiver();
            }

            printPlayerCurrently(playerList, table);
            printFlushProb(playerList, table);
            printDupValues(playerList, table);
            //   printAndUpdateStraights(table, playerList);


        }



        //deck.printDeck();

    }

public static void printAndUpdateStraights(Table table, Player[] playerList){
    for (int j = 0; j < playerList.length; j++) {

        playerList[j].getPs().updateListOfStraights(table, playerList[j]);
        System.out.println(playerList[j].getName() + "'s possible straights:");
        playerList[j].getPs().printPossibleStraights();
        System.out.println();
    }
}

    public static String pairCheck(Player player, Table table){
        String s = "";
        String s1 = "";

        boolean hasPocketPair = false;


        boolean card1pair = false;
        int activeCardPair1;
        int card1PairCount = 0;

        boolean card2pair = false;
        int activeCardPair2;
        int card2PairCount = 0;

        if(player.getCard1().getSuitNum() == player.getCard2().getSuitNum()){
            card1PairCount++;
            hasPocketPair = true;
        }

        for(int i = 0; i < table.getNumActiveCards(); i++){

            if(player.getCard1().getValueNum() == table.getActiveCards()[i].getValueNum()){
                card1pair = true;
                card1PairCount++;
                activeCardPair1 = i;
            }
            if(player.getCard2().getValueNum() == table.getActiveCards()[i].getValueNum()){
                card2pair = true;
                card2PairCount++;
                activeCardPair2 = i;
            }
        }

        if(card1PairCount == 1){
            s += "a pair of " + player.getCard1().getValue() + "s " ;
        }
        else if (card1PairCount == 2){
            s += "a three of a kind with " + player.getCard1().getValue() + "s ";
        }
        else if (card1PairCount == 3){
            s += "a four  of a kind with " + player.getCard1().getValue() + "s ";
        }

        if(card2PairCount == 1){
            s1 += "a pair of " + player.getCard2().getValue() + "s " ;
        }
        else if (card2PairCount == 2){
            s1 += "a three of a kind with " + player.getCard2().getValue() + "s ";
        }
        else if (card2PairCount == 3){
            s1 += "a four  of a kind with " + player.getCard2().getValue() + "s ";
        }

        if (card1pair && card2pair){
            s = s + " and " + s1;
            return s;
        }
        else if (card1pair){
            return s;
        }
        else if(card2pair){
            return s1;
        }

        return "No Pairs";
    }

    public static String flushCheck(Player player, Table table){
        String s = "";
        String s1 = "";
        int numSuitMax = 1;
        int numSuitCard1 = 1;
        int numSuitCard2 = 1;

        boolean flushDrawInHand = false;
        boolean card1FlushDraw = false;
        boolean card2FlushDraw = false;

        if(player.getCard1().getSuitNum() == player.getCard2().getSuitNum()){
            numSuitMax++;
            flushDrawInHand = true;
        }
        if(!flushDrawInHand){
            numSuitCard1 = 1;
            numSuitCard2 = 1;
        }

        for (int i = 0; i < table.getNumActiveCards(); i++) {
            if(player.getCard1().getSuitNum() == table.getActiveCards()[i].getSuitNum()){
                if(flushDrawInHand){
                    numSuitMax++;
                }
                else{
                    numSuitCard1++;
                }
            }
        }

        if (!flushDrawInHand){
            for (int i = 0; i < table.getNumActiveCards(); i++) {
                if(player.getCard2().getSuitNum() == table.getActiveCards()[i].getSuitNum()){
                        numSuitCard2++;
                }
            }
            if(numSuitCard1 > numSuitCard2){
                card1FlushDraw = true;
                numSuitMax = numSuitCard1;
            }
            else{
                card2FlushDraw = true;
                numSuitMax = numSuitCard2;
            }
        }

        String flushSuit = (numSuitCard1 > numSuitCard2) ? player.getCard1().getSuit() : player.getCard2().getSuit();

        if(numSuitMax >= 5){
            s = "a FLUSH with ";
            if(flushDrawInHand || card1FlushDraw){
                s += player.getCard1().getSuit();
            }
            else{
                s += player.getCard2().getSuit();
            }
        }

        else if((5 - numSuitMax) <= (5 - table.getNumActiveCards())){
            s = "no flush, but needs " + (5 - numSuitMax) + " more " + flushSuit + " to get a flush";
        }

        else{
            s ="no flush possible";
        }

        return s;
    }

    public static Deck shuffleDeck(Deck deck) {
        Random rng = new Random(16314);
        Card[] shuffledDeck = new Card[52];
        int i = 0;

        while (!deckIsFull(shuffledDeck)) {

            int index = rng.nextInt(52);

            if (deck.getDeck()[index] != null) {
                shuffledDeck[i] = deck.removeCard(index);
                i++;

            }
            continue;
        }

        Deck d = new Deck();
        d.setDeck(shuffledDeck);
        return d;

    }

    public static void printPlayerCurrently(Player[] playerList, Table table){
        for (int i = 0; i < playerList.length; i++) {
            System.out.print(playerList[i].getName() + " has " + pairCheck(playerList[i], table) + "\n");
            System.out.print(playerList[i].getName() + " also has " + flushCheck(playerList[i], table) + "\n");
        }
        System.out.print("\n");
    }

    public static boolean deckIsFull(Card[] deck){
        for (int i = 0; i < deck.length; i++) {
            if(deck[i] == null)
                return false;
        }
        return true;
    }

    public static void printFlushProb(Player[] playerList, Table table) {
        for (int i = 0; i < playerList.length; i++) {
            if(probability.flushCheck(playerList[i], table, playerList.length) == 0){
                continue;
            }
            System.out.print("Probability that " + playerList[i].getName() + " gets a flush is " + probability.flushCheck(playerList[i], table, playerList.length) + "%" + "\n\n");
        }
    }

    public static String cardNumToString(int cardValue){
        String value = "";
        switch(cardValue) {
            case 14:
                value = "Ace";
                break;
            case 2:
                value = "2";
                break;
            case 3:
                value = "3";
                break;
            case 4:
                value = "4";
                break;
            case 5:
                value = "5";
                break;
            case 6:
                value = "6";
                break;
            case 7:
                value = "7";
                break;
            case 8:
                value = "8";
                break;
            case 9:
                value = "9";
                break;
            case 10:
                value = "10";
                break;
            case 11:
                value = "Jack";
                break;
            case 12:
                value = "Queen";
                break;
            case 13:
                value = "King";
                break;
        }
        return value;

    }

    public static void printDupValues(Player[] playersList, Table table){
        for (int i = 0; i < playersList.length; i++) {

            DuplicateValues dP = new DuplicateValues(playersList[i], table);
            int dupVal = 0;

            if (dP.hasPair()){
                if (dP.hasTrips()){
                    if (dP.hasQuads()){
                        dupVal = dP.getDupCard().getValueNum();
                        break;
                    }
                    dupVal = dP.getDupCard().getValueNum();
                    break;
                }
                dupVal = dP.getDupCard().getValueNum();
                break;
            }

            String value = cardNumToString(dupVal);

            if (dP.hasPair()){
                if (dP.hasTrips()){
                    if (dP.hasQuads()){
                        System.out.println(playersList[i].getName() + " has quad " + value + "s");
                    }
                    System.out.println(playersList[i].getName() + " has trip " + value + "s");
                }
                System.out.println(playersList[i].getName() + " has a pair of " + value + "s");
            }

        }
    }

//    public static void updatePlayersStraights(Player[] playersList, Table table){
//        for (int i = 0; i < playersList.length; i++) {
//            playersList[i].getPs().updateAllArrays(table, playersList[i]);
//        }
//    }

}
