import java.util.Random;

/**
 * Created by School on 4/5/2017.
 */
public class Deck {
    private Card[] deck;
    private Random rng = new Random();
    private int numCards;

    public Deck() {
        this.numCards = 52;
        this.deck = generateDeck();
        //this. deck = shuffleDeck();
    }

    private Card[] generateDeck() {
        Card[] deck = new Card[52];
        int count = 0;
        for (int y = 0; y < 4; y++) {
            for (int z = 0; z < 13; z++) {
                deck[count] = new Card(y + 1, z + 2);
                count++;
            }
        }

            return deck;
    }

    public Card[] getDeck(){
        return this.deck;
        }

    public void setDeck(Card[] deck) {
        this.deck = deck;
    }

    public int getNumCards() {
        return numCards;
    }

    public void printDeck() {
        for (int i = 0; i < 52; i++) {
//           if (deck[i] == null){
//               continue;
//           }

            System.out.println(deck[i]);
        }
        System.out.println();
    }

    public Card removeCard(){
        //int index = rng.nextInt(numCards);
        Card card = deck[--numCards];
        deck[numCards] = null;

        return card;
    }

    public Card removeCard(int index){
        Card card = deck[index];
        deck[index] = null;
        numCards--;

        return card;
    }

    public void threePlayersHaveFlushDraw(){
        int index = 46;
        int count = 0;
        for (int i = 0; i < deck.length && count < 7 ; i++) {
            if(count == 6 && deck[i].getSuitNum() == 3){
                Card card = deck[i];
                deck[51] = deck[i];
                deck[i] = card;
            }
            else if(deck[i].getSuitNum() == 3){
                Card card = deck[i];
                deck[index--] = deck[i];
                deck[i] = card;
                count++;
            }
        }
    }


    public void playerOneHasAFlushDrawAfterFlop(){
        int index = 46;
        int end = 51;
        int count = 0;
        for (int i = 0; i < deck.length && count < 5 ; i++) {
            if(count < 2 && deck[i].getSuitNum() == 3){
                Card card = deck[i];
                deck[index--] = deck[i];
                deck[i] = card;
                count++;
            }
            else if(deck[i].getSuitNum() == 3){
                Card card = deck[i];
                deck[end--] = deck[i];
                deck[i] = card;
                count++;
            }
        }
    }

    public void playerOneHasAPair(){
        int index = 46;
        int end = 51;
        int count = 0;
        for (int i = 0; i < deck.length && count < 2 ; i++) {
            if(deck[i].getValueNum() == 8){
                Card card = deck[i];
                deck[index--] = deck[i];
                deck[i] = card;
                count++;
            }
//            if(count < 2 && deck[i].getSuitNum() == 3){

//            }
//            else if(deck[i].getSuitNum() == 3){
//                Card card = deck[i];
//                deck[end--] = deck[i];
//                deck[i] = card;
//                count++;
//            }
        }
    }


}


