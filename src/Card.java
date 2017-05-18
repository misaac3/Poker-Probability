import com.sun.javaws.exceptions.InvalidArgumentException;
import java.*;

/**
 * Created by School on 4/5/2017.
 */



public class Card {
    private int suitNum;
    private int valueNum;
    private String suit;
    private String value;
    private int numStraightsIn;
    private boolean unconventionalMinStraight;
    private boolean unconventionalMaxStraight;
    private int minStraightStart;
    private int maxStraightEnd;

    public Card(int suitNum, int valueNum){
        //if(suitNum < 0 || suitNum > 4 || valueNum < 0 || valueNum > 13) throw new InvalidArgumentException;
        this.numStraightsIn = 5;
        this.unconventionalMinStraight = this.unconventionalMaxStraight = false;
        this.minStraightStart = valueNum - 4;
        this.maxStraightEnd = valueNum + 4;

        this.suitNum = suitNum;
        this.valueNum = valueNum;

        switch(suitNum){
            case 1: this.suit = "Spades"; break;
            case 2: this.suit = "Hearts"; break;
            case 3: this.suit = "Clubs"; break;
            case 4: this.suit = "Diamonds"; break;
        }

        switch(valueNum) {
            case 14:
                this.value = "Ace";
                this.numStraightsIn = 2;
                //TODO this. UNCONVENTIONAL???
                break;
            case 2:
                this.value = "2";
                this.numStraightsIn = 2;
                this.unconventionalMinStraight = true;
                this.minStraightStart = 2; //TODO but also for ACEs
                break;
            case 3:
                this.value = "3";
                this.numStraightsIn = 3;
                this.unconventionalMinStraight = true;
                this.minStraightStart = 2; //TODO but also for ACES;
                break;
            case 4:
                this.value = "4";
                this.numStraightsIn = 4;
                this.unconventionalMinStraight = true;
                this.minStraightStart = 2; //TODO but also for ACES;
                break;
            case 5:
                this.value = "5";
                break;
            case 6:
                this.value = "6";
                break;
            case 7:
                this.value = "7";
                break;
            case 8:
                this.value = "8";
                break;
            case 9:
                this.value = "9";
                break;
            case 10:
                this.value = "10";
                break;
            case 11:
                this.value = "Jack";
                this.numStraightsIn = 4;
                this.unconventionalMaxStraight = true;
                this.maxStraightEnd = 14;
                break;
            case 12:
                this.value = "Queen";
                this.numStraightsIn = 3;
                this.unconventionalMaxStraight = true;
                this.maxStraightEnd = 14;
                break;
            case 13:
                this.value = "King";
                this.numStraightsIn = 2;
                this.unconventionalMaxStraight = true;
                this.maxStraightEnd = 14;
                break;
        }
    }
    public String getValue() {
        return this.value;
    }

    public String getSuit(){
        return this.suit;
    }

    public int getSuitNum() {
        return suitNum;
    }

    public int getValueNum() {
        return valueNum;
    }

    public int getNumStraightsIn() {
        return numStraightsIn;
    }

    public int getMaxStraightEnd() {
        return maxStraightEnd;
    }

    public int getMinStraightStart() {
        return minStraightStart;
    }

    public String toString(){
        return value + " of " + suit;
    }
}
