import java.util.ArrayList;

/**
 * Created by School on 4/9/2017.
 */
public class Table {
    private Card card1;
    private Card card2;
    private Card card3;
    private Card card4;
    private Card card5;

    private Card[] activeCards = new Card[5];
    private int numActiveCards;

    private boolean flushDraw;
    private boolean straightDraw;

    private Flop flop;
    private Turn turn;
    private River river;

    private DuplicateTableValues dupVal1, dupVal2; //maximum of 2 duplicate values on the table
    private String sameSuit;
    private boolean sameSuitBoolean = false;

    public Table(Card card1, Card card2, Card card3, Card card4, Card card5){


        this.card1 = card1;
        this.card2 = card2;
        this.card3 = card3;
        this.card4 = card4;
        this.card5 = card5;
        
        this.numActiveCards = 0;

        this.flop = new Flop(this);
        this.turn = new Turn(this);
        this.river = new River(this);


    }

    public void drawFlop(){
        this.flop.setDrawn(true);
        activeCards[numActiveCards++] = this.flop.getFlop()[0];
        activeCards[numActiveCards++] = this.flop.getFlop()[1];
        activeCards[numActiveCards++] = this.flop.getFlop()[2];
        printActiveCards();

        checkForDupsAndSuit();

    }
    
    public void drawTurn(){
        this.turn.setDrawn(true);

        activeCards[numActiveCards++] = this.turn.getTurn()[0];
        printActiveCards();
    }
    
    public void drawRiver(){
        this.river.setDrawn(true);
        activeCards[numActiveCards++] = this.river.getRiver()[0];
        printActiveCards();
    }

    public void checkForDupsAndSuit(){
        if(river.isDrawn()){
            checkForDupsAfterRiver();
            if(sameSuitBoolean){
                checkForSameSuitAfterRiver();
            }
        }
        else if(turn.isDrawn()){
            checkForDupsAfterTurn();
            if(sameSuitBoolean){
                checkForSameSuitAfterTurn();
            }
        }

        else if(flop.isDrawn()){
            checkForDupsAfterFlop();
            checkForSameSuitAfterFlop();
        }
    }

    public void checkForSameSuitAfterRiver(){
        if (!(activeCards[0].getSuitNum() == activeCards[4].getSuitNum())){
            sameSuitBoolean = false;
        }
    }
    public void checkForSameSuitAfterTurn(){
        if (!(activeCards[0].getSuitNum() == activeCards[3].getSuitNum())){
            sameSuitBoolean = false;
        }

    }
    public void checkForSameSuitAfterFlop(){
        if(activeCards[0].getSuitNum() == activeCards[1].getSuitNum() && activeCards[0].getSuitNum() == activeCards[2].getSuitNum()){
            sameSuitBoolean = true;
            sameSuit = activeCards[0].getSuit();
        }

    }

    public void checkForDupsAfterRiver(){
        int card5 = activeCards[4].getValueNum();

        if(!(dupVal1 != null && dupVal2 != null)){
            if(dupVal1 == null && dupVal2 == null){
                for (int i = 0; i < 4; i++) {
                    if(activeCards[i].getValueNum() == card5){
                        dupVal1 = new DuplicateTableValues(card5, 2, activeCards[i], activeCards[4]);
                    }
                }
            }
            else if(dupVal1 != null && dupVal2 == null){
                for (int i = 0; i < 4; i++) {
                    if(activeCards[i].getValueNum() == card5){
                        dupVal1 = new DuplicateTableValues(card5, 2, activeCards[i], activeCards[4]);
                    }
                }
            }
        }
    }

    public void checkForDupsAfterTurn(){
        int card4 = activeCards[3].getValueNum();
        if (dupVal1 != null && dupVal1.getValue() == card4){
            dupVal1.incrementNumDups(activeCards[4]);
        }
        else if(dupVal1 != null && dupVal1.getNumDups() < 3){
            for (int i = 0; i < 3; i++) {
                if(activeCards[i].getValueNum() == card4){
                    dupVal2 = new DuplicateTableValues(card4, 2,activeCards[i], activeCards[3]);
                }
            }
        }
        else if(dupVal1 == null){
            for (int i = 0; i < 3; i++) {
                if(activeCards[i].getValueNum() == card4){
                    dupVal1 = new DuplicateTableValues(card4, 2,activeCards[i], activeCards[3]);
                }
            }
        }
    }

    public void checkForDupsAfterFlop(){
        int card1 = activeCards[0].getValueNum();
        int card2 = activeCards[1].getValueNum();
        int card3 = activeCards[2].getValueNum();

        if(card1 == card2){
            if (card2 == card3){
                this.dupVal1 = new DuplicateTableValues(card1, 3, activeCards[0], activeCards[1], activeCards[2]);
            }
            else{
                this.dupVal1 = new DuplicateTableValues(card1, 2, activeCards[0], activeCards[1]);
            }
        }
        else if(card1 == card3){
            this.dupVal1 = new DuplicateTableValues(card1, 2, activeCards[0], activeCards[2]);
        }
        else if(card2 == card3){
            this.dupVal1 = new DuplicateTableValues(card2, 2, activeCards[1], activeCards[2]);
        }
    }

    public Flop getFlop() {
        return this.flop;
    }

    public Turn getTurn() {
        return this.turn;
    }

    public River getRiver() {
        return this.river;
    }

    public Card getCard1(){
        return card1; 
    }

    public Card getCard2() { 
        return card2;
    }

    public Card getCard3() {
        return card3;
    }

    public Card getCard4() {
        return card4;
    }

    public Card getCard5() {
        return card5;
    }

    public int getNumActiveCards() {
        return numActiveCards;
    }

    public Card[] getActiveCards() {
        return activeCards;
    }

    @Override
    public String toString() {
        String string = card1.toString() + ", " + card2.toString() + ", " + card3.toString() + ", " + card4.toString() + ", " + card5.toString() + "\n" + "\n";
        return string;
    }
    
    public void printActiveCards(){
       System.out.println("----------------------------------------------------------------------");
        for (int i = 0; i < numActiveCards; i++) {
            System.out.print(activeCards[i] + ", ");
        }
        System.out.println("\n");
    }



}

class DuplicateTableValues{

    private int value, numDups;
    private ArrayList<Card> dupCards = new ArrayList<>(4);

    public DuplicateTableValues(int value, int numDups, Card card1, Card card2){
            this.value = value;
            this.numDups = numDups;
            this.dupCards.add(card1);
            this.dupCards.add(card2);
    }

    public DuplicateTableValues(int value, int numDups, Card card1, Card card2, Card card3){
        this.value = value;
        this.numDups = numDups;
        this.dupCards.add(card1);
        this.dupCards.add(card2);
        this.dupCards.add(card3);
    }

    public int getNumDups() {
        return numDups;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void incrementNumDups(Card card) {
        this.numDups++;
        this.dupCards.add(card);
    }
}


