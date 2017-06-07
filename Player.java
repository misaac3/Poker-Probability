import java.util.ArrayList;

/**
 * Created by School on 4/8/2017.
 */
public class Player {
    private Hand hand;
    private String name;

    public Player (String name, Card card1, Card card2, Table table){

        this.name = name;

        this.hand = new Hand(card1, card2);

    }

    public String toString(){
        return name + " has " + hand.getCard1() + " and " + hand.getCard2() + " giving him " + hand.getStr() + " with a high card of " + hand.getHighCard();
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }
}
