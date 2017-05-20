/**
 * Created by School on 4/19/2017.
 */
public class Flop {
    private Card card1;
    private Card card2;
    private Card card3;

    private boolean drawn;

    private Card[] flop = new Card[3];

    public Flop(Table table){
        this.flop[0] = this.card1 = table.getCard1();
        this.flop[1] = this.card2 = table.getCard2();
        this.flop[2] = this.card3 = table.getCard3();

        this.drawn = false;
    }

    public boolean isDrawn() {
        return drawn;
    }

    public void setDrawn(boolean drawn) {
        this.drawn = drawn;
    }

    public Card[] getFlop() {
        return flop;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < 3; i++) {
            s += flop[i].toString();
        }
        return s;
    }


}
