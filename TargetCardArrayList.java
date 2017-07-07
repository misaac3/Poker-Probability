import java.util.ArrayList;

/**
 * Created by School on 7/6/2017.
 */
public class TargetCardArrayList extends ArrayList {
    //Type 1 is value, Type 2 is suit, Type 3 is Both
    private int type;

    public TargetCardArrayList(int type){
        this.type = type;

    }

    public boolean contains(Card card) {
        if(type == 1){
            Integer value = card.getValueNum();
            return super.contains(value);
        }
        else if (type == 2){
            String suit = card.getSuit();
            return super.contains(suit);
        }
        else{
            return super.contains(card);
        }

    }

    public void intializeTargetCards(Card card, int typeOfHand){
        int value = card.getValueNum();
        String suit = card.getSuit();
        int suitNum = card.getSuitNum();

        switch (typeOfHand){
            case 0:
                add(value);
                break;
            case 1:
                //TODO Two Pair
                break;
            case 2:
                add(value);
                add(value);
                break;
//            case 3:
//                for (int i = 1; i < 5; i++) {
//                    if(value -)
//                    add(value - i);
//                }
//                break;
            case 4:
                for (int i = 0; i < 4; i++) {
                    add(suit);
                }
                break;
            case 5: //TODO Full House
                break;
            case 6:
                add(value);
                add(value);
                add(value);
                break;
//            case 7:
//                for (int i = 0; i < 4; i++) {
//                    add(new Card(suitNum, ));
//                }
//                break;
//            case 8:
//                break;

        }
    }

    public void intializeStraightTargets(int highCardInStraight){
        int currVal = highCardInStraight;
        for (int i = 0; i < 4; i++) {

            if(currVal == 0){
                add(14);
            }
            else {
                add(currVal - 1);
                System.out.println("\nprinting from TargetCardArrayList\n I just added " + (currVal - 1) + " to the TargetCards");
            }

            currVal--;
        }

    }

    public void intializeSuitedStraightTargets(int highCardInStraight, Card card, int typeOfStraight){ //0 is straight flush, 1 is royal flush
        int currVal = highCardInStraight;
        int suitNum = card.getSuitNum();
        if(typeOfStraight == 1){
            for (int i = 0; i < 5; i++) {
                Card newCard = new Card(suitNum, i + 10);
                if(!(card.getValueNum() == newCard.getValueNum())){ // skips card already in Straight
                    add(newCard);
                }
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            if(currVal == 0){
                add(new Card(suitNum, 14));
            }
            else {
                add(new Card(suitNum, currVal - 1));
                System.out.println("\nprinting from TargetCardArrayList\n I just added " + (currVal - 1) + " to the TargetCards");            }
            currVal--;
        }

    }

    @Override
    public String toString() {
        String s = "Target card";
        if(size() == 1){
            s += " is ";
        }
        else {
            s += "s are ";
        }

        for (int i = 0; i < size(); i++) {
            if(type == 1 && (int)get(i) > 10){
                switch ((int) get(i)){
                    case 11:
                        s += "Jack ";
                        break;
                    case 12:
                        s += "Queen ";
                        break;
                    case 13:
                        s += "King ";
                        break;
                    case 14:
                        s += "Ace ";
                        break;
                }
            }
            else {
                s += get(i) + " ";
            }
        }

        return s;
    }
}
