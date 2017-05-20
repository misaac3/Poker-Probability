import java.util.ArrayList;

/**
 * Created by School on 4/19/2017.
 */
public class probability {
//    public static double pOfFlush(Player player, Table table){
//        String mostSuit = "";
//        for (int i = 0; i < table.getNumActiveCards(); i++) {
//            if(ca)
//        }
//    }

    public static double straightCheck(Player player, Table table){
        double d = 1;

        double numTermsForProb = 0.0;

        if(!table.getFlop().isDrawn()) {
            if (player.getPs().isBothCardsInStraight()) {
                numTermsForProb = 3;
            } else {
                numTermsForProb = 4;
            }

            for (int i = 0; i < numTermsForProb; i++) {
                d = d * (4.0 / (50.0 - i));
            }
            return d * (double)player.getPs().getNumStraightsPossible();
        }
        else if (!table.getTurn().isDrawn()){

        }

        if(d >= 1){
            return 1;
        }

        return d;
    }

    public static double flushCheck(Player player, Table table, int numPlayers){
        String s = "";
        String s1 = "";
        int numSuitMax = 1;
        int numSuitCard1 = 1;
        int numSuitCard2 = 1;

        int mostSuitNum = 0;

        boolean flushDrawInHand = false;
        boolean card1FlushDraw = false;
        boolean card2FlushDraw = false;

        double probability = 0;
        int flushCount = 13;

        if(player.getCard1().getSuitNum() == player.getCard2().getSuitNum()){
            numSuitMax++;
            flushDrawInHand = true;
            mostSuitNum = player.getCard1().getSuitNum();
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
                mostSuitNum = player.getCard1().getSuitNum();
            }
            else{
                card2FlushDraw = true;
                numSuitMax = numSuitCard2;
                mostSuitNum = player.getCard2().getSuitNum();
            }
        }

        int numCardsNeededForFlush;
        if(numSuitMax == 5){
            return 100;
        }
        else{
            numCardsNeededForFlush = 5 - numSuitMax;
        }


        for (int i = 0; i < table.getNumActiveCards(); i++) {
            if(mostSuitNum == table.getActiveCards()[i].getSuitNum()){
                flushCount--;
            }
        }
        if(player.getCard1().getSuitNum() == mostSuitNum){
            flushCount--;
        }
        if(player.getCard2().getSuitNum() == mostSuitNum){
            flushCount--;
        }

        double numCardsOnTable = 0;
        double numTableCards = 0;
        if(table.getRiver().isDrawn()){
            return 0;
        }
        else if(table.getTurn().isDrawn()){
            numTableCards = 4;
        }
        else if(table.getFlop().isDrawn()){
            numTableCards = 3;
        }

        if(numCardsNeededForFlush > (5 - numTableCards)) {
            return 0;
        }


        numCardsOnTable = 2 + numTableCards;

        double pOfOneCardPicked = flushCount / (52-numCardsOnTable);
        double pOfTwoCardsPicked = flushCount / ((52-numCardsOnTable)) *  flushCount / ((52-numCardsOnTable - 1));
        double p = 0;

        if(numCardsNeededForFlush == 1){
            p = pOfOneCardPicked * (5 - numTableCards);
        }
        else if(numCardsNeededForFlush == 2){
            p = pOfTwoCardsPicked * (5 - numTableCards);
        }

        //probability = (flushCount/(double) (52 - numCardsOnTable)) / (5 - numTableCards) * (double) 100;
        probability = p *(double)100;

        return probability;
    }

    public static String typeOfStraight(Player player, Table table){
        String s = "";

        boolean hasStraightDraw = false;
        int cardsLeftToDraw = 0;
        if(table.getRiver().isDrawn()){

        }
        else if (table.getTurn().isDrawn()){
            cardsLeftToDraw = 1;
        }
        else if(table.getFlop().isDrawn()){
            cardsLeftToDraw = 2;
        }
        else{
            cardsLeftToDraw = 5;
        }

        Card focusCardHigh;
        Card focusCardLow;
        Card[][] cardsInStraightRange = new Card[7][9];
        int arrayCount = 0;
        for (int i = 0; i < table.getNumActiveCards(); i++) {
            if(Math.abs(player.getCard1().getValueNum() - table.getActiveCards()[i].getValueNum()) < 5){
                hasStraightDraw = true;
                focusCardHigh = player.getCard1();
                focusCardLow = table.getActiveCards()[i];
                if(focusCardHigh.getValueNum() < focusCardLow.getValueNum()){
                    Card temp = focusCardHigh;
                    focusCardHigh = focusCardLow;
                    focusCardLow = temp;
                }
                int count = 0;
                for (int j = 0; j < table.getNumActiveCards(); j++) {

                    if(table.getActiveCards()[j].getValueNum() < focusCardHigh.getValueNum() || table.getActiveCards()[i].getValueNum() > focusCardLow.getValueNum() ){
                        cardsInStraightRange[arrayCount][count++] = table.getActiveCards()[i];
                    }


                }
                arrayCount++;
                System.out.println(player.getName() + " has a straight draw with the cards " + player.getCard1() + " and " + table.getActiveCards()[i]);

            }
        }






        return s;
    }

    public static double pairCheck(Player player, Table table){
        double d = 0;
        boolean hasPair = false;

        if(player.getCard1().getValueNum() == player.getCard2().getValueNum()){
            hasPair = true;
        }


        return d;
    }


//    public static double straightCheck(Player player, Table table){
//        double p = 0;
//        PossibleStraights [] possStraightArray;
//        if(player.hasStraightDrawInHand()){
//            if (player.getCardsBetweenStraightDraw() == 1){
//             //   possStraightArray =
//            }
//        }
//
//
//
//
//
//
//
//
//
//
//
//        return p;
//    }

}
