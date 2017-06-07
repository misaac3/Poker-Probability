/**
 * Created by School on 4/20/2017.
 */
public class DuplicateValues {

    private boolean hasPair;
    private boolean hasTrips;
    private boolean hasQuads;

    private Card dupCard;

    public DuplicateValues(Player player, Table table){
        this.hasPair = checkForPairs(player);
        this.hasTrips = checkForTrips(player, table);
        this.hasQuads = checkForQuads(player, table);

    }

    public void updateBooleans(Player player, Table table){

    }

    public boolean checkForPairs(Player player){
        if(player.getHand().getCard1().getValueNum() == player.getHand().getCard2().getValueNum()) {
            this.dupCard = player.getHand().getCard1();
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkForTrips(Player player, Table table){
        if(this.hasPair) {
            for (int i = 0; i < table.getNumActiveCards(); i++) {
                if (player.getHand().getCard1().getValueNum() == table.getActiveCards()[i].getValueNum()) {
                    this.dupCard = table.getActiveCards()[i];
                    return true;
                }
            }
        }

        return false;

    }

    public boolean checkForQuads(Player player, Table table){
        int count = 0;
        if(this.hasPair) {
            for (int i = 0; i < table.getNumActiveCards(); i++) {
                if (player.getHand().getCard1().getValueNum() == table.getActiveCards()[i].getValueNum()) {
                    count++;
                    if (count >= 2) {
                        this.dupCard = table.getActiveCards()[i];
                        return true;
                    }
                }
            }
        }return false;

    }

    public boolean hasPair() {
        return hasPair;
    }

    public boolean hasTrips() {
        return hasTrips;
    }

    public boolean hasQuads() {
        return hasQuads;
    }

    public Card getDupCard() {
        return dupCard;
    }
}
