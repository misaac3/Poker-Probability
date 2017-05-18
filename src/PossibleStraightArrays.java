import java.util.ArrayList;

/**
 * Created by School on 5/8/2017.
 */
public class PossibleStraightArrays {
//    PossibleStraights[] possibleStraightArray;
//
//    public PossibleStraightArrays(Player player){
//        possibleStraightArray = new PossibleStraights[player.getNumPossibleStraights()];
//
//        int lowCardHighCount = player.getLowCard().getValueNum() + 4;
//        for (int i = 0; i < player.getLowCard().getNumStraightsIn(); i++) {
//            possibleStraightArray[i] = new PossibleStraights(player, player.getLowCard().getMaxStraightEnd());
//        }
//
//    }
//}


    private int maxValueOfStraight = 5;

    private PossibleStraights[] allStraights = new PossibleStraights[11];

    private PossibleStraights possibleStraight4;
    private PossibleStraights possibleStraight5;
    private PossibleStraights possibleStraight6;
    private PossibleStraights possibleStraight7;
    private PossibleStraights possibleStraight8;
    private PossibleStraights possibleStraight9;
    private PossibleStraights possibleStraight10;
    private PossibleStraights possibleStraight11;
    private PossibleStraights possibleStraight12;
    private PossibleStraights possibleStraight13;
    private PossibleStraights possibleStraight14;
    //the number after array is the high card

    private int numStraightsPossible;

    private ArrayList<PossibleStraights> highestProbabiltiyStraight;

    private Table table;
    private Player player;
    private int indexOfMostLikely;
    private boolean[] activeStraightPossible = new boolean[9];
    private int[] numCardsNeeded = new int[9];

    private ArrayList<PossibleStraights> listOfBPS = new ArrayList<>(11);

    private ArrayList<Integer> listOfNumsNeeded = new ArrayList<>(11);

    public PossibleStraightArrays(Table table, Player player) {


        allStraights[0] = this.possibleStraight4 = createPossibleStraight(maxValueOfStraight++);
        allStraights[1] = this.possibleStraight5 = createPossibleStraight(maxValueOfStraight++);
        allStraights[2] = this.possibleStraight6 = createPossibleStraight(maxValueOfStraight++);
        allStraights[3] = this.possibleStraight7 = createPossibleStraight(maxValueOfStraight++);
        allStraights[4] = this.possibleStraight8 = createPossibleStraight(maxValueOfStraight++);
        allStraights[5] = this.possibleStraight9 = createPossibleStraight(maxValueOfStraight++);
        allStraights[6] = this.possibleStraight10 = createPossibleStraight(maxValueOfStraight++);
        allStraights[7] = this.possibleStraight11 = createPossibleStraight(maxValueOfStraight++);
        allStraights[8] = this.possibleStraight12 = createPossibleStraight(maxValueOfStraight++);
        allStraights[9] = this.possibleStraight13 = createPossibleStraight(maxValueOfStraight++);
        allStraights[10] = this.possibleStraight14 = createPossibleStraight(maxValueOfStraight++);

//        for (int i = 0; i < allStraights.length; i++) {
//            listOfPS.add(allStraights[i]);
//        }

        highestProbabiltiyStraight = new ArrayList<PossibleStraights>(9);

        this.table = table;
        this.numStraightsPossible = 0;

//        for (int i = 0; i < activeStraightPossible.length; i++) {
//            this.activeStraightPossible[i] = false;
//            this.numCardsNeeded[i] = 5;
//        }
    }

    public void updateListOfStraights(Table table, Player player){
        if(!table.getFlop().isDrawn()){
            updatePreFlop(player);
        }

        else if (!table.getTurn().isDrawn()){
            updatePostFlop(table);
        }
        else if (!table.getRiver().isDrawn()){
            updatePostTurn(table);
        }
        else if (table.getRiver().isDrawn()){
            updatePostRiver(table);
        }

        System.out.println("numStraightsPossible: " + numStraightsPossible);
    }

    public void updatePreFlop(Player player){
        int minNumLeft = 5;


        for (int i = 0; i < allStraights.length; i++) {
            for (int j = 0; j < allStraights[i].getStraightNums().length; j++) {
                if (player.getHighCard().getValueNum() == allStraights[i].getStraightNums()[j]){
                    allStraights[i].setPossible(true);
                    allStraights[i].decrementNumLeft();
                    this.numStraightsPossible++;
                    allStraights[i].setHasCard(j);
                }

                else if(player.getLowCard().getValueNum() == allStraights[i].getStraightNums()[j]){
                    if(!allStraights[i].isPossible()){
                        allStraights[i].setPossible(true);
                        this.numStraightsPossible++;
                    }

                    allStraights[i].decrementNumLeft();
                    allStraights[i].setHasCard(j);
                }
            }

            if (allStraights[i].getNumLeft() < minNumLeft) {
                listOfBPS.clear();
                minNumLeft = allStraights[i].getNumLeft();
                this.indexOfMostLikely = i;
                listOfBPS.add(allStraights[i]);
            } else if (allStraights[i].getNumLeft() == minNumLeft) {
                listOfBPS.add(allStraights[i]);
            }

            if (!allStraights[i].isPossible()) {
                allStraights[i] = null;
            }
          //updateListOfBPS(i, minNumLeft);
        }

        updateNumsNeeded();

//        String s = "";
//        for (int i = 0; i <listOfBPS.size() ; i++) {
//            s += listOfBPS.get(i) + "\n";
//            System.out.println("---TEST--- \n" + "s: " + s + "\n---TEST---");
//        }


    }

    public void updatePostFlop(Table table){//}, Player player) {
        int minNumLeft = 5;
        int card1 = table.getFlop().getFlop()[0].getValueNum();
        int card2 = table.getFlop().getFlop()[1].getValueNum();
        int card3 = table.getFlop().getFlop()[2].getValueNum();
        listOfBPS.clear();

        int cardsLeft = 2;

        for (int i = 0; i < allStraights.length; i++) {

            if (allStraights[i] == null){
                continue;
            }

//            if (allStraights[i].getNumLeft() > cardsLeft){
//                System.out.println(allStraights[i].getNumLeft());
//                allStraights[i].setPossible(false);
//                allStraights[i] = null;
//                this.numStraightsPossible--;
//                continue;
//            }
            if (allStraights[i].isPossible()) {

            for (int j = 0; allStraights[i] != null && j < allStraights[i].getStraightNums().length ; j++) {

               if (card1 == allStraights[i].getStraightNums()[j] ||
                       card2 == allStraights[i].getStraightNums()[j] ||
                       card3 == allStraights[i].getStraightNums()[j]) {
                   allStraights[i].decrementNumLeft();
                   allStraights[i].setHasCard(j);
               }



//                if (allStraights[i].getNumLeft() <= minNumLeft) {
//                    minNumLeft = allStraights[i].getNumLeft();
//                    this.indexOfMostLikely = i;
//                    listOfBPS.add(allStraights[i]);
//                }
            }
            }
//            if (allStraights[i].getNumLeft() <= minNumLeft) {
//                listOfBPS.clear();
//                minNumLeft = allStraights[i].getNumLeft();
//                this.indexOfMostLikely = i;
//                listOfBPS.add(allStraights[i]);
//            }

//  updateListOfBPS(i, minNumLeft);
            if (allStraights[i].getNumLeft() > cardsLeft){
                allStraights[i].setPossible(false);
                allStraights[i] = null;
                this.numStraightsPossible--;
            }
            if (allStraights[i] == null){
                continue;
            }

            if (allStraights[i].getNumLeft() < minNumLeft) {
                listOfBPS.clear();
                minNumLeft = allStraights[i].getNumLeft();
                this.indexOfMostLikely = i;
                if(!listOfBPS.contains(allStraights[i])){
                    listOfBPS.add(allStraights[i]);
                }            }
            else if (allStraights[i].getNumLeft() == minNumLeft) {
                if(!listOfBPS.contains(allStraights[i])){
                    listOfBPS.add(allStraights[i]);
                }
            }

//            if (!allStraights[i].isPossible()) {
//                allStraights[i] = null;
//            }
        }

        if (numStraightsPossible == 0){
            this.listOfBPS.clear();
        }
        updateNumsNeeded();

    }

    public void updatePostTurn(Table table){
        int minNumLeft = 5;
        int card1 = table.getTurn().getTurn()[0].getValueNum();
        listOfBPS.clear();

        int cardsLeft = 1;

        for (int i = 0; i < allStraights.length; i++) {
            if (allStraights[i] == null){
                continue;
            }

            if (allStraights[i].isPossible()) {
                for (int j = 0; allStraights[i] != null && j < allStraights[i].getStraightNums().length ; j++) {
                    if (card1 == allStraights[i].getStraightNums()[j]){
                        allStraights[i].decrementNumLeft();
                        allStraights[i].setHasCard(j);
                    }
                }
            }

            if (allStraights[i].getNumLeft() > cardsLeft){
                allStraights[i].setPossible(false);
                allStraights[i] = null;
                this.numStraightsPossible--;
            }
            if (allStraights[i] == null){
                continue;
            }

            if (allStraights[i].getNumLeft() < minNumLeft) {
                listOfBPS.clear();
                minNumLeft = allStraights[i].getNumLeft();
                this.indexOfMostLikely = i;
                if(!listOfBPS.contains(allStraights[i])){
                    listOfBPS.add(allStraights[i]);
                }
            }
            else if (allStraights[i].getNumLeft() == minNumLeft) {
                if(!listOfBPS.contains(allStraights[i])){
                    listOfBPS.add(allStraights[i]);
                }
            }

//            if (!allStraights[i].isPossible()) {
//                allStraights[i] = null;
//            }
        }

        if (numStraightsPossible == 0){
            this.listOfBPS.clear();
        }
        updateNumsNeeded();
    }

    public void updatePostRiver(Table table){
        int minNumLeft = 5;
        int card1 = table.getRiver().getRiver()[0].getValueNum();

        int cardsLeft = 0;

        for (int i = 0; i < allStraights.length; i++) {

            if (allStraights[i] == null){
                continue;
            }

//            if (allStraights[i].getNumLeft() > cardsLeft){
//                System.out.println(allStraights[i].getNumLeft());
//                allStraights[i].setPossible(false);
//                allStraights[i] = null;
//                this.numStraightsPossible--;
//                continue;
//            }
            if (allStraights[i].isPossible()) {

                for (int j = 0; allStraights[i] != null && j < allStraights[i].getStraightNums().length ; j++) {

                    if (card1 == allStraights[i].getStraightNums()[j]){
                            allStraights[i].decrementNumLeft();
                    allStraights[i].setHasCard(j);
                    }



//                if (allStraights[i].getNumLeft() <= minNumLeft) {
//                    minNumLeft = allStraights[i].getNumLeft();
//                    this.indexOfMostLikely = i;
//                    listOfBPS.add(allStraights[i]);
//                }
                }
            }
//            if (allStraights[i].getNumLeft() <= minNumLeft) {
//                listOfBPS.clear();
//                minNumLeft = allStraights[i].getNumLeft();
//                this.indexOfMostLikely = i;
//                listOfBPS.add(allStraights[i]);
//            }

//  updateListOfBPS(i, minNumLeft);
            if (allStraights[i].getNumLeft() > cardsLeft){
                allStraights[i].setPossible(false);
                allStraights[i] = null;
                this.numStraightsPossible--;
            }
            if (allStraights[i] == null){
                continue;
            }

            if (allStraights[i].getNumLeft() < minNumLeft) {
                listOfBPS.clear();
                minNumLeft = allStraights[i].getNumLeft();
                this.indexOfMostLikely = i;
                if(!listOfBPS.contains(allStraights[i])){
                    listOfBPS.add(allStraights[i]);
                }            }
            else if (allStraights[i].getNumLeft() == minNumLeft) {
                if(!listOfBPS.contains(allStraights[i])){
                    listOfBPS.add(allStraights[i]);
                }
            }

//            if (!allStraights[i].isPossible()) {
//                allStraights[i] = null;
//            }
        }

        if (numStraightsPossible == 0){
            this.listOfBPS.clear();
        }
        updateNumsNeeded();
    }


    public void printPossibleStraights(){
        if(numStraightsPossible == 0){
            System.out.println("There are no possible straights for this player");
            return;
        }

        for (int i = 0; i < allStraights.length; i++) {
            if(allStraights[i] != null){
                System.out.println(allStraights[i]);
            }
        }

        String s = "";
     //   System.out.println("listOfBPS.size(): " + listOfBPS.size());
        for (int i = 0; i <listOfBPS.size() ; i++) {
            s += listOfBPS.get(i) + "\n";
        }
//        System.out.println("\n" + "s: " + s);
        if(listOfBPS.size() == 1) {
            System.out.println("\nThe most likely straight is: \n" + s);
        }
        else{
            System.out.println("\nThe most likely straights are: \n" + s);
        }

        s = "";
        for (int j = 0; j < 5; j++) {
            if (allStraights[j] == null){
                continue;
            }
            if(allStraights[indexOfMostLikely].getHasCard()[j] != -1){
                s += allStraights[indexOfMostLikely].getStraightNums()[j] + ", ";
            }
        }

        System.out.println("cards that would help a straight are " + listOfNumsNeeded);
    }

//    public void updateListOfBPS(int i, int minNumLeft){
//        if (allStraights[i].getNumLeft() < minNumLeft) {
//            listOfBPS.clear();
//            minNumLeft = allStraights[i].getNumLeft();
//            this.indexOfMostLikely = i;
//            listOfBPS.add(allStraights[i]);
//        } else if (allStraights[i].getNumLeft() == minNumLeft) {
//            listOfBPS.add(allStraights[i]);
//        }
//
//        if (!allStraights[i].isPossible()) {
//            allStraights[i] = null;
//        }
//    }

    public void updateNumsNeeded(){



        for (int i = 0; i <listOfBPS.size() ; i++) {
            for (int j = 0; j < 5; j++) {
                if(listOfBPS.get(i).getHasCard()[j] != -1){
                    if (!listOfNumsNeeded.contains(listOfBPS.get(i).getHasCard()[j])){
                        this.listOfNumsNeeded.add(listOfBPS.get(i).getHasCard()[j]);
                    }
                }
            }
        }
        if(numStraightsPossible == 0){
            this.listOfNumsNeeded.clear();
        }
    }

    @Override
    public String toString() {
        String s = "";

        for (int i = 0; i < activeStraightPossible.length; i++) {
            if(activeStraightPossible[i]){
                System.out.println(allStraights[i]);
            }
        }
        return s;
    }

    public PossibleStraights createPossibleStraight(int maxValue) {
        PossibleStraights a = new  PossibleStraights(maxValue);//PossibleStraights[5;
        if (maxValue == 4) {
            a.setStraightNums(0, 14);
        }
        return a;
    }
}

