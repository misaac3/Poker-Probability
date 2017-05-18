public class PossibleStraights {
    private int[] straightNums = new int[5];
    private boolean isPossible;
    private int numLeft;
    private int maxValue;
    private boolean hasStraight;
    private int[] hasCard = new int[5];


    public PossibleStraights(int maxValue){
        this.maxValue = this.straightNums[4] = maxValue;
        this.straightNums[3] = maxValue -1;
        this.straightNums[2] = maxValue -2;
        this.straightNums[1] = maxValue -3;
        this.straightNums[0] = maxValue -4;
        for (int i = 0; i < straightNums.length; i++) {
            if(straightNums[i] == 1) {
                straightNums[i] = 14;
            }
        }

        this.hasStraight = false; //TODO should this be false at first??????

        this.isPossible = false;

        for (int i = 0; i < hasCard.length; i++) {
            hasCard[i] = straightNums[i];
        }
        this. numLeft = 5;
    }


    public void decrementNumLeft() {
        this.numLeft--;
    }

    public void setStraightNums(int index, int value) {
        this.straightNums[index] = value;
    }

    public boolean isPossible() {
        return isPossible;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public int getNumLeft() {
        return numLeft;
    }

    public int[] getStraightNums() {
        return straightNums;
    }

    public void setPossible(boolean possible) {
        isPossible = possible;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < straightNums.length; i++) {
            if(straightNums[i] < 11){
                s += straightNums[i] + ", ";
            }
            else{
                String st = "";
                switch (straightNums[i]){
                    case(11) : st = "J, "; break;
                    case(12) : st = "Q, "; break;
                    case(13) : st = "K, "; break;
                    case(14) : st = "A, "; break;
                }
                s+= st;

            }
        }

        return s;
    }

    public int[] getHasCard() {
        return hasCard;
    }

    public void setHasCard( int index) {
        this.hasCard[index] = -1;
    }

    public void update(Table table, Player player){
        if (isPossible){

            for (int i = 0; i < straightNums.length; i++) {
                if (table.getRiver().isDrawn()) {
                    if (straightNums[i] == table.getActiveCards()[4].getValueNum()){
                        numLeft--;
                    }
                }
                if (table.getTurn().isDrawn()) {
                    if (straightNums[i] == table.getActiveCards()[3].getValueNum()){
                        numLeft--;
                    }
                    if (numLeft > 1){
                        isPossible = false;
                    }
                }
                if (table.getFlop().isDrawn()) {
                    for (int j = 0; j < 3; j++) {
                        if (straightNums[i] == table.getActiveCards()[j].getValueNum()) {
                            numLeft--;
                        }
                        if (numLeft > 2){
                            isPossible = false;
                        }
                    }
                }

                if (!table.getFlop().isDrawn()){
                    if (straightNums[i] == player.getLowCard().getValueNum() || straightNums[i] == player.getHighCard().getValueNum()) {
                        if (straightNums[i] == player.getLowCard().getValueNum()) {
                            numLeft--;

                        }
                        if (straightNums[i] == player.getHighCard().getValueNum()) {
                            numLeft--;

                        }
                    }
                    else{
                        isPossible = false;
                    }
                }
            }
        }
    }



























    //    //private int num;
//    private boolean isPossible;
//   // private PossibleStraightEntry[] straight = PossibleStraightEntry[5];
//    private boolean[] hasCard = new boolean[5];
//    private int numCardsNeeded;
//
//    public PossibleStraights(Player player, int highCard) {
//        this.numCardsNeeded = 5;
//        int z = 4;
//
////        for (int i = 0; i < straight.length; i++) {
////
////        }
//    }
//
//    public boolean isPossible() {
//        return isPossible;
//    }
//
//    public boolean getHasCard(int index) {
//        return hasCard[index];
//    }
////
////    public PossibleStraightEntry[] getStraight() {
////        return straight;
////    }
//
//
//    public class PossibleStraightEntry{
//        private int num;
//        private boolean playerHas;
//        public PossibleStraightEntry(int num){
//            this.num = num;
//            this.playerHas = false;
//        }
//
//        public boolean playerHas() {
//            return playerHas;
//        }
//
//        public int getNum() {
//            return num;
//        }
//    }
//
//}




///**
// * Created by School on 4/20/2017.
// */
//public class PossibleStraights {
//    int maxValueOfStraight = 4;
//    private int[] array1 = createPossibleStraight(maxValueOfStraight++);
//    private int[] array2 = createPossibleStraight(maxValueOfStraight++);
//    private int[] array3 = createPossibleStraight(maxValueOfStraight++);
//    private int[] array4 = createPossibleStraight(maxValueOfStraight++);
//    private int[] array5 = createPossibleStraight(maxValueOfStraight++);
//    private int[] array6 = createPossibleStraight(maxValueOfStraight++);
//    private int[] array7 = createPossibleStraight(maxValueOfStraight++);
//    private int[] array8 = createPossibleStraight(maxValueOfStraight++);
//    private int[] array9 = createPossibleStraight(maxValueOfStraight++);
//
//    String s;
//
//    public PossibleStraights(Player player, Table table){
//      //this.s =
//
//    }
//
//    public int[] createPossibleStraight(int maxValue){
//        int[] a = new int[5];
//        a[0] = maxValue - 4;
//        a[1] = maxValue - 3;
//        a[2] = maxValue - 2;
//        a[3] = maxValue - 1;
//        a[4] = maxValue;
//
//        if (maxValue == 4){
//            a[0] = 13;
//        }
//        return a;
//    }
//
//    public String possibleStraights(Player player, Table table){
//        String string = "";
//
//        if (!table.getFlop().isDrawn()){ //pre-flop
//            if (!player.hasStraightDrawInHand()){ //player has no straight draw
//                return player.getName() + " currently has no straight draw";
//            }
//            else{ //player has straight draw
//                switch (player.getCardsBetweenStraightDraw()){
//                    case 1:
//                }
//            }
//        }
//
//        return string;
//    }
}
