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
        //PossibleStraightArrays.

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


