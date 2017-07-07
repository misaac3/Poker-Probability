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
        if(isPair){
           // ts.get(0).add(new ProbabilityTree(2,0, 1));
        }





       return ts;
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
