/**
 * Created by School on 4/19/2017.
 */
public class Turn {
    private Card card4;
    private Card[] turn = new Card[1];
    private boolean drawn;

    public Turn(Table table){
        this.turn[0] = this.card4 = table.getCard4();
        this.drawn = false;
    }

    public Card[] getTurn() {
        return turn;
    }

    public boolean isDrawn() {
        return drawn;
    }

    public void setDrawn(boolean drawn) {
        this.drawn = drawn;
    }

    public String toString() {
        return turn[0].toString();
    }



}
