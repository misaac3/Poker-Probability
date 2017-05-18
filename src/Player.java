import java.util.ArrayList;

/**
 * Created by School on 4/8/2017.
 */
public class Player {
    private Card card1;
    private Card card2;
    private String hand;
    private Card highCard;
    private Card lowCard;
    private String name;
    private boolean hasStraightDrawInHand;
    private int cardsBetweenStraightDraw;
    private int numPossibleStraights;

  //  private ArrayList<PossibleStraights> listOfPS = new ArrayList<>(10);

    private PossibleStraightArrays ps;
    //private DuplicateValues dupVal;
    public Player (String name, Card card1, Card card2, Table table){
        this.card1 = card1;
        this.card2 = card2;
        this.name = name;
        this.hand = determineInitialHand();
        this.highCard = determineHighCard();
        this.lowCard = determineLowCard();
        //this.dupVal = new DuplicateValues(this)
        this.hasStraightDrawInHand = hasStraightDrawInHand();
        this.numPossibleStraights = determineNumPossibleStraights();

        this.ps = new PossibleStraightArrays(table, this);

     //   this.listOfPS =

    }




    public int getNumPossibleStraights() {
        return numPossibleStraights;
    }

    public PossibleStraightArrays getPs() {
        return ps;
    }
//
//  public void updatePlayersPossibleStraights(Table table){
//        ps.updateAllArrays(table, this);
//    }


    public String toString(){
        return name + " has " + card1 + " and " + card2 + " giving him " + hand + " with a high card of " + highCard;
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

    public Card determineHighCard() {
        if(card1.getValueNum() > card2.getValueNum()){
            return card1;
        }
        else{
            return card2;
        }
    }

    public int getCardsBetweenStraightDraw() {
        return cardsBetweenStraightDraw;
    }

    public Card determineLowCard(){
        if(highCard == card1){
            return card2;
        }

        return card1;
    }

    public Card getCard1() {
        return card1;
    }

    public Card getCard2() {
        return card2;
    }

    public String getName() {
        return name;
    }

    public Card getHighCard() {
        return highCard;
    }


    public boolean hasStraightDrawInHand(){
        int cardDiff =  Math.abs(card1.getValueNum() - card2.getValueNum());
        this.cardsBetweenStraightDraw = cardDiff;
            if( cardDiff < 5) {
                return true;
        }
        return false;
    }

    public Card getLowCard() {
        return lowCard;
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
}
