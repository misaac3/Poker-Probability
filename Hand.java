import sun.awt.geom.AreaOp;

import java.util.ArrayList;

/**
 * Created by School on 5/30/2017.
 */
public class Hand {
    private Card card1;
    private Card card2;
    private Card highCard;
    private Card lowCard;
    private PossibleStraightArrays ps;
    private boolean hasStraightDrawInHand;
    private int cardsBetweenStraightDraw;
    private int numPossibleStraights;
    private String str;
//    private ArrayList<ArrayList<ProbabilityTree>> treeStorage = new ArrayList<ArrayList<ProbabilityTree>>(9);

//private ArrayList<TreeList> treeStorage = new ArrayList<TreeList>(9);
    private TreeList[] treeStorage = new TreeList[9];

    private boolean isPair, isSuited;


    public Hand(Card card1, Card card2){
        this.card1 = card1;
        this.card2 = card2;
        this.highCard = determineHighCard();
        this.lowCard = determineLowCard();

        this.hasStraightDrawInHand = hasStraightDrawInHand();
        this.numPossibleStraights = determineNumPossibleStraights();

        this.str = determineInitialHand();

      //  this.ps = new PossibleStraightArrays(table, this);

        this.isPair = determineInitialIsPair();
        this.isSuited = determineInitialIsSuited();

        this.treeStorage = fillTreeStorage();


    }

//    public ArrayList<TreeList> fillTreeStorage(){
//        ArrayList<TreeList>  ts = new ArrayList<TreeList> (9);
//        for (int i = 0; i < ts.size(); i++) {
//            ts.add(i, new TreeList());
//            System.out.println("The size of the TreeStorage is " + ts.size());
//        }
//        if(!isPair){
//            //pair
//            ts.get(0).add(createTree(card1, "pair"));
//            ts.get(0).add(createTree(card2, "pair"));
//
//            //two pair
//            ts.get(1).add(createTree(card1, card2, "two pair"));
//
//            //three of a kind
//            ts.get(2).add(createTree(card1, "three of a kind"));
//            ts.get(2).add(createTree(card2, "three of a kind"));
//
//            //straight
//            ts.add(3, createTreesForStraight(card1, card2));
//
//            //flush in if(suited)
//
//            //full house
//            //TODO
//
//            //four of a kind
//            ts.get(6).add(createTree(card1, "four of a kind"));
//            ts.get(6).add(createTree(card2, "four of a kind"));
//
//            //straight flush //TODO THIS ALWAYS HAPPENS UNLESS THEY ARE THE SAME SUIT AND WITHIN 5 OF EACH OTHER
////            ts.get(7).add(createTree(card1, "straight flush"));
//
//            //royal flush depends on if it is 10, J, Q, K, or A
//        }
//        else {
//            // pair
//            ts.get(0).add(createdCompletedTree(card1, card2, "pair"));
//
//            //two pair depends on table duplicates
//
//            //three of a kind
//            ts.get(2).add(createTree(card1, card2, "three of a kind"));
//
//            //straight
//            ts.add(3, createTreesForStraight(card1, card2));
//
//            //flush in if(suited)
//
//            //full house TODO
//
//            //four of a kind
//            ts.get(6).add(createTree(card1, card2, "four of a kind"));
//
//            //straight flush TODO THIS ALWAYS HAPPENS UNLESS THEY ARE THE SAME SUIT AND WITHIN 5 OF EACH OTHER
////            ts.get(7).add(createTree(card1, "straight flush"));
//
//            //royal flush depends on if it is 10, J, Q, K, or A
//        }
//
//        if (!isSuited){
//            //flush
//            ts.get(4).add(createTree(card1, "flush"));
//            ts.get(4).add(createTree(card2, "flush"));
//
//            //straight flush
//            ts.add(7, createTreesForStraightFlush(card1, card2));
//
//            //royal flush
//            if(card1.getValueNum() > 9){
//                ts.get(8).add(createTree(card1, "royal flush"));
//            }
//            if (card2.getValueNum() > 9){
//                ts.get(8).add(createTree(card2, "royal flush"));
//            }
//
//        }
//        else {
//            //flush
//            ts.get(4).add(createTree(card1, card2, "flush"));
//
//            //straight flush
//            ts.add(7, createTreesForStraight(card1, card2));
//
//            //royal flush
//            if(card1.getValueNum() > 9){
//                ts.get(8).add(createTree(card1, "royal flush"));
//            }
//            if (card2.getValueNum() > 9){
//                ts.get(8).add(createTree(card2, "royal flush"));
//            }
//
//
//        }
//

    public TreeList[] fillTreeStorage(){
        TreeList[]  ts = new TreeList[9];
        for (int i = 0; i < ts.length; i++) {
            ts[i] = new TreeList(i);
        }
        if(!isPair){
            //pair
            ts[0].add(createTree(card1, "pair"));
            ts[0].add(createTree(card2, "pair"));

            //two pair
            ts[1].add(createTree(card1, card2, "two pair"));

            //three of a kind
            ts[2].add(createTree(card1, "three of a kind"));
            ts[2].add(createTree(card2, "three of a kind"));

            //straight
            ts[3] = (createTreesForStraight(card1, card2));

            //flush in if(suited)

            //full house
            //TODO

            //four of a kind
            ts[6].add(createTree(card1, "four of a kind"));
            ts[6].add(createTree(card2, "four of a kind"));

            //straight flush //TODO THIS ALWAYS HAPPENS UNLESS THEY ARE THE SAME SUIT AND WITHIN 5 OF EACH OTHER
            ts[7].add(createTree(card1, "straight flush"));

            //royal flush depends on if it is 10, J, Q, K, or A
        }
        else {
            // pair
            ts[0].add(createdCompletedTree(card1, card2, "pair"));

            //two pair depends on table duplicates

            //three of a kind
            ts[2].add(createTree(card1, card2, "three of a kind"));

            //straight
            //ts[3].createTreesForStraight(card1, card2));

            //flush in if(suited)

            //full house TODO

            //four of a kind
            ts[6].add(createTree(card1, card2, "four of a kind"));

            //straight flush TODO THIS ALWAYS HAPPENS UNLESS THEY ARE THE SAME SUIT AND WITHIN 5 OF EACH OTHER
//            ts.get(7).add(createTree(card1, "straight flush"));

            //royal flush depends on if it is 10, J, Q, K, or A
        }

        if (!isSuited){
            //flush
            ts[4].add(createTree(card1, "flush"));
            ts[4].add(createTree(card2, "flush"));

            //straight flush
            //ts.add(7, createTreesForStraightFlush(card1, card2));

            //royal flush
            if(card1.getValueNum() > 9){
                ts[8].add(createTree(card1, "royal flush"));
            }
            if (card2.getValueNum() > 9){
                ts[8].add(createTree(card2, "royal flush"));
            }

        }
        else {
            //flush
            ts[4].add(createTree(card1, card2, "flush"));

            //straight flush
//            ts.add(7, createTreesForStraight(card1, card2));

            //royal flush
            if(card1.getValueNum() > 9){
                ts[8].add(createTree(card1, "royal flush"));
            }
            if (card2.getValueNum() > 9){
                ts[8].add(createTree(card2, "royal flush"));
            }


        }






       return ts;
    }

//    public ArrayList<TreeList> fillTreeStorage(){
//        ArrayList<TreeList>  ts = new ArrayList<TreeList> (9);
//        for (int i = 0; i < ts.size(); i++) {
//            ts.add(i, new TreeList());
//            System.out.println("The size of the TreeStorage is " + ts.size());
//        }
//        if(!isPair){
//            //pair
//            ts.get(0).add(createTree(card1, "pair"));
//            ts.get(0).add(createTree(card2, "pair"));
//
//            //two pair
//            ts.get(1).add(createTree(card1, card2, "two pair"));
//
//            //three of a kind
//            ts.get(2).add(createTree(card1, "three of a kind"));
//            ts.get(2).add(createTree(card2, "three of a kind"));
//
//            //straight
//            ts.add(3, createTreesForStraight(card1, card2));
//
//            //flush in if(suited)
//
//            //full house
//            //TODO
//
//            //four of a kind
//            ts.get(6).add(createTree(card1, "four of a kind"));
//            ts.get(6).add(createTree(card2, "four of a kind"));
//
//            //straight flush //TODO THIS ALWAYS HAPPENS UNLESS THEY ARE THE SAME SUIT AND WITHIN 5 OF EACH OTHER
////            ts.get(7).add(createTree(card1, "straight flush"));
//
//            //royal flush depends on if it is 10, J, Q, K, or A
//        }
//        else {
//            // pair
//            ts.get(0).add(createdCompletedTree(card1, card2, "pair"));
//
//            //two pair depends on table duplicates
//
//            //three of a kind
//            ts.get(2).add(createTree(card1, card2, "three of a kind"));
//
//            //straight
//            ts.add(3, createTreesForStraight(card1, card2));
//
//            //flush in if(suited)
//
//            //full house TODO
//
//            //four of a kind
//            ts.get(6).add(createTree(card1, card2, "four of a kind"));
//
//            //straight flush TODO THIS ALWAYS HAPPENS UNLESS THEY ARE THE SAME SUIT AND WITHIN 5 OF EACH OTHER
////            ts.get(7).add(createTree(card1, "straight flush"));
//
//            //royal flush depends on if it is 10, J, Q, K, or A
//        }
//
//        if (!isSuited){
//            //flush
//            ts.get(4).add(createTree(card1, "flush"));
//            ts.get(4).add(createTree(card2, "flush"));
//
//            //straight flush
//            ts.add(7, createTreesForStraightFlush(card1, card2));
//
//            //royal flush
//            if(card1.getValueNum() > 9){
//                ts.get(8).add(createTree(card1, "royal flush"));
//            }
//            if (card2.getValueNum() > 9){
//                ts.get(8).add(createTree(card2, "royal flush"));
//            }
//
//        }
//        else {
//            //flush
//            ts.get(4).add(createTree(card1, card2, "flush"));
//
//            //straight flush
//            ts.add(7, createTreesForStraight(card1, card2));
//
//            //royal flush
//            if(card1.getValueNum() > 9){
//                ts.get(8).add(createTree(card1, "royal flush"));
//            }
//            if (card2.getValueNum() > 9){
//                ts.get(8).add(createTree(card2, "royal flush"));
//            }
//
//
//        }
//
////    public TreeList[] fillTreeStorage(){
////        TreeList[]  ts = new TreeList[9];
////        if(!isPair){
////            //pair
////            ts[0].add(createTree(card1, "pair"));
////            ts[0].add(createTree(card2, "pair"));
////
////            //two pair
////            ts[1].add(createTree(card1, card2, "two pair"));
////
////            //three of a kind
////            ts[2].add(createTree(card1, "three of a kind"));
////            ts[2].add(createTree(card2, "three of a kind"));
////
////            //straight
////            //ts.add(3, createTreesForStraight(card1, card2));
////
////            //flush in if(suited)
////
////            //full house
////            //TODO
////
////            //four of a kind
////            ts[6].add(createTree(card1, "four of a kind"));
////            ts[6].add(createTree(card2, "four of a kind"));
////
////            //straight flush //TODO THIS ALWAYS HAPPENS UNLESS THEY ARE THE SAME SUIT AND WITHIN 5 OF EACH OTHER
//////            ts.get(7).add(createTree(card1, "straight flush"));
////
////            //royal flush depends on if it is 10, J, Q, K, or A
////        }
//////        else {
//////            // pair
//////            ts.get(0).add(createdCompletedTree(card1, card2, "pair"));
//////
//////            //two pair depends on table duplicates
//////
//////            //three of a kind
//////            ts.get(2).add(createTree(card1, card2, "three of a kind"));
//////
//////            //straight
//////            ts.add(3, createTreesForStraight(card1, card2));
//////
//////            //flush in if(suited)
//////
//////            //full house TODO
//////
//////            //four of a kind
//////            ts.get(6).add(createTree(card1, card2, "four of a kind"));
//////
//////            //straight flush TODO THIS ALWAYS HAPPENS UNLESS THEY ARE THE SAME SUIT AND WITHIN 5 OF EACH OTHER
////////            ts.get(7).add(createTree(card1, "straight flush"));
//////
//////            //royal flush depends on if it is 10, J, Q, K, or A
//////        }
//////
//////        if (!isSuited){
//////            //flush
//////            ts.get(4).add(createTree(card1, "flush"));
//////            ts.get(4).add(createTree(card2, "flush"));
//////
//////            //straight flush
//////            ts.add(7, createTreesForStraightFlush(card1, card2));
//////
//////            //royal flush
//////            if(card1.getValueNum() > 9){
//////                ts.get(8).add(createTree(card1, "royal flush"));
//////            }
//////            if (card2.getValueNum() > 9){
//////                ts.get(8).add(createTree(card2, "royal flush"));
//////            }
//////
//////        }
//////        else {
//////            //flush
//////            ts.get(4).add(createTree(card1, card2, "flush"));
//////
//////            //straight flush
//////            ts.add(7, createTreesForStraight(card1, card2));
//////
//////            //royal flush
//////            if(card1.getValueNum() > 9){
//////                ts.get(8).add(createTree(card1, "royal flush"));
//////            }
//////            if (card2.getValueNum() > 9){
//////                ts.get(8).add(createTree(card2, "royal flush"));
//////            }
//////
//////
//////        }
//
//
//
//
//
//
//        return ts;
//    }



    public ProbabilityTree createdCompletedTree(Card card1, Card card2, String handType){
        if (handType.toLowerCase().trim().equals("pair")){
            return new ProbabilityTree(2, 0, 1,0, card1);
        }
        else {
            return null;
        }
    }

    public TreeList createTreesForStraight(Card card1, Card card2){
        ArrayList<Integer> possibleStraights = new ArrayList<Integer>(10);
        int baseValue1 = card1.getValueNum() + 4; //ie 10 + 4 = 14, the straight where 10 is the lowest card is where
                                                  // 14(Ace) is the high card
       // int baseVal1 = newVal1;
        int count1 = 0;

        if (baseValue1 > 14){
            baseValue1 = 14;
         //   baseVal1 = 14;
        }

        while(count1 < 5){
            int entryValue = baseValue1 - count1;
            if(entryValue < card1.getValueNum() || entryValue < 5){
                break;
            }
            else {
                possibleStraights.add(entryValue);
                count1++;
            }
        }

        if(!isPair) {
            int baseValue2 = card2.getValueNum() + 4;
            int count2 = 0;

            while (count2 < 5) {
                int entryValue = baseValue2 - count2;
                if (!(possibleStraights.contains(entryValue))) {
                    if (entryValue < card2.getValueNum() || entryValue < 5) {
                        break;
                    } else {
                        possibleStraights.add(entryValue);
                        count2++;
                    }
                }
            }
        }

        TreeList tempTreeList = new TreeList(3);
        for (int i = 0; i < possibleStraights.size(); i++) {
            tempTreeList.add(new ProbabilityTree(16, 4, 4, 3, new Card(1,
                    possibleStraights.get(i))));

            tempTreeList.get(i).getTargetCards().updateInitialTargets(card1);

            if (!isPair){
            tempTreeList.get(i).getTargetCards().updateInitialTargets(card2);
            }
        }

        return tempTreeList;
    }

    public TreeList createTreesForStraightFlush(Card card1, Card card2){
        ArrayList<Integer> possibleStraights = new ArrayList<>(10);
        int baseValue1 = card1.getValueNum() + 4; //ie 10 + 4 = 14, the straight where 10 is the lowest card is where
        // 14(Ace) is the high card
        // int baseVal1 = newVal1;
        int count1 = 0;

        if (baseValue1 > 14){
            baseValue1 = 14;
            //   baseVal1 = 14;
        }

        while(count1 < 5){
            int entryValue = baseValue1 - count1;
            if(entryValue < card1.getValueNum() || entryValue < 5){
                break;
            }
            else {
                possibleStraights.add(entryValue);
                count1++;
            }
        }

        if(!isPair) {
            int baseValue2 = card2.getValueNum() + 4;
            int count2 = 0;

            while (count2 < 5) {
                int entryValue = baseValue2 - count2;
                if (!(possibleStraights.contains(entryValue))) {
                    if (entryValue < card2.getValueNum() || entryValue < 5) {
                        break;
                    } else {
                        possibleStraights.add(entryValue);
                        count2++;
                    }
                }
            }
        }

        TreeList tempTreeList = new TreeList(7);
        for (int i = 0; i < possibleStraights.size(); i++) {
            tempTreeList.add(new ProbabilityTree(4, 4, 4, 7, new Card(card1.getSuitNum(),
                    possibleStraights.get(i))));

            tempTreeList.get(i).getTargetCards().updateInitialTargets(card1);

            if (!isPair){
                tempTreeList.get(i).getTargetCards().updateInitialTargets(card2);
            }
        }

        return tempTreeList;
    }


    public ProbabilityTree createTree(Card card, String handType){
        switch (handType.toLowerCase().trim()){
            case "pair":
                return new ProbabilityTree(3, 1, 1,0, card);

            case "two pair":
                return new ProbabilityTree(6, 2, 1, 1, card);

            case "three of a kind":
                return new ProbabilityTree(3, 2, 1, 2, card);

            case "straight":
                return new ProbabilityTree(16, 4, 4, 3, card);

            case "flush":
                return new ProbabilityTree(12, 4, 1, 4, card);

            case "full house": //TODO
                break;

            case "four of a kind":
                return new ProbabilityTree(3, 3, 1, 6, card);

            case "straight flush":
                return new ProbabilityTree(4, 4, 1, 7, card);

            case "royal flush":
                return new ProbabilityTree(4, 4, 1, 8, card);

        }
        return new ProbabilityTree(1,1,1,1,card);
    }

    public ProbabilityTree createTree(Card card1, Card card2, String handType){
       ProbabilityTree newTree = new ProbabilityTree(0, 0, 0, 0, card1);
        switch (handType.toLowerCase().trim()){
            case "pair":
                newTree = new ProbabilityTree(3, 1, 1,0, card1);
                break;

            case "two pair":
                newTree = new ProbabilityTree(6, 2, 1, 1, card1);
                break;

            case "three of a kind":
                newTree =  new ProbabilityTree(3, 2, 1, 2, card1);
                break;

            case "straight":
                newTree =  new ProbabilityTree(16, 4, 4, 3, card1);
                break;

            case "flush":
                newTree =  new ProbabilityTree(12, 4, 1, 4, card1);
                break;

            case "full house": //TODO
                break;

            case "four of a kind":
                newTree =  new ProbabilityTree(3, 3, 1, 6, card1);
                break;

            case "straight flush":
                newTree =  new ProbabilityTree(4, 4, 1, 7, card1);
                break;

            case "royal flush":
                newTree =  new ProbabilityTree(4, 4, 1, 8, card1);
                break;

        }

        newTree.getTargetCards().updateInitialTargets(card2);
        return newTree;
    }













    public int determineNumPossibleStraights(){
        int num = 0;
        switch (getCardsBetweenStraightDraw()){
            case 5:
                num = getCard1().getNumStraightsIn() + getCard1().getNumStraightsIn();
                break;
            case 4:
                num = getCard1().getNumStraightsIn() + getCard1().getNumStraightsIn() - 1;
                break;
            case 3:
                num = getCard1().getNumStraightsIn() + getCard1().getNumStraightsIn() - 2;
                break;
            case 2:
                num = getCard1().getNumStraightsIn() + getCard1().getNumStraightsIn() - 3;
                break;
            case 1:
                num = getCard1().getNumStraightsIn() + getCard1().getNumStraightsIn() - 4;
                break;
            case 0:
                num = getCard1().getNumStraightsIn();
                break;
        }

        return num;
    }

    public  boolean determineInitialIsPair(){
        return card1.getValueNum() == card2.getValueNum();
    }

    public  boolean determineInitialIsSuited(){
        return card1.getSuitNum() == card2.getSuitNum();
    }

    public Card getCard1() {
        return card1;
    }

    public Card getCard2() {
        return card2;
    }

    public Card getHighCard() {
        return highCard;
    }

    public Card getLowCard() {
        return lowCard;
    }

    public Card determineHighCard() {
        if(card1.getValueNum() > card2.getValueNum()){
            return card1;
        }
        else{
            return card2;
        }
    }

    public Card determineLowCard(){
        if(highCard == card1){
            return card2;
        }

        return card1;
    }

    public int getNumPossibleStraights() {
        return numPossibleStraights;
    }

    public PossibleStraightArrays getPs() {
        return ps;
    }

    public String determineInitialHand(){
        String hand = "nothing";

        if(card1.getValue().equals(card2.getValue())){
            hand = "a Pair, ";
        }

        if(card1.getSuit().equals(card2.getSuit())){
            if(Math.abs(card1.getValueNum() - card2.getValueNum()) < 5){
                hand = "a Flush Draw and a Straight Draw";
            }
            else{
                hand = "a Flush Draw, ";
            }
        }


        return hand;

    }

    public int getCardsBetweenStraightDraw() {
        return cardsBetweenStraightDraw;
    }

    public String getStr() {
        return str;
    }

    public boolean hasStraightDrawInHand(){
        int cardDiff =  Math.abs(card1.getValueNum() - card2.getValueNum());
        this.cardsBetweenStraightDraw = cardDiff;
        if( cardDiff < 5) {
            return true;
        }
        return false;
    }

    public TreeList[] getTreeStorage() {
        return treeStorage;
    }
}
