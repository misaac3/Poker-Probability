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
    private ArrayList<ArrayList<ProbabilityTree>> treeStorage = new ArrayList<ArrayList<ProbabilityTree>>(9);


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


    }

    public ArrayList<ArrayList<ProbabilityTree>> fillTreeStorage(){
        ArrayList<ArrayList<ProbabilityTree>> ts = new ArrayList<ArrayList<ProbabilityTree>>(9);
        if(!isPair){
            //pair
            createTree(card1, "pair");
            createTree(card2, "pair:");

            //two pair
                //createTree()




        }






       return ts;
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
       ProbabilityTree newTree = new ProbabilityTree(0, 0, 0, 0, card1);;
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




}
