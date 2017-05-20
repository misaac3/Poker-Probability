/**
 * Created by School on 4/19/2017.
 */
public class River {
    private Card card5;
    private Card[] river = new Card[1];
    private boolean drawn;

    public River(Table table){
        this.river[0] = this.card5 = table.getCard5();
        this.drawn = false;
    }

    public boolean isDrawn() {
        return drawn;
    }

    public void setDrawn(boolean drawn) {
        this.drawn = drawn;
    }

    public Card[] getRiver() {
        return river;
    }

    public String toString() {
        return river[0].toString();
    }
}
