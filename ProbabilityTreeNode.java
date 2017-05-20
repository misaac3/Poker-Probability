/**
 * Created by School on 5/20/2017.
 */
public class ProbabilityTreeNode {

    private double numTargetCards, numCardsLeftInDeck, probability;
    private int numCardsFromDesiredHand, numTargetCardDecrementation;
    private ProbabilityTreeNode yesNode, noNode;
    private boolean isHandAchieved;

    public ProbabilityTreeNode(double numTargetCards, double numCardsLeftInDeck, int numCardsFromDesiredHand, int numTargetCardDecrementation, int numCardsNeededToCompleteHand){
        //1st Base Case - Stops recursion if hand is achieved
        //makes
        if(numTargetCards == (double)numCardsNeededToCompleteHand){
            this.isHandAchieved = true;
            this.yesNode = this.noNode = null;
        }

        this.numTargetCards = numTargetCards;
        this.numCardsLeftInDeck = numCardsLeftInDeck;
        this.probability = this.numTargetCards / this.numCardsLeftInDeck;

        this.numCardsFromDesiredHand = numCardsFromDesiredHand;
        this.numTargetCardDecrementation = numTargetCardDecrementation;

        this.yesNode = new ProbabilityTreeNode(numTargetCards - (double)numTargetCardDecrementation, --numCardsLeftInDeck, numCardsFromDesiredHand++, numTargetCardDecrementation, numCardsNeededToCompleteHand);
        this.noNode = new ProbabilityTreeNode(numTargetCards, --numCardsLeftInDeck, numCardsFromDesiredHand, numTargetCardDecrementation, numCardsNeededToCompleteHand);

    }

    public double getNumCardsLeftInDeck() {
        return numCardsLeftInDeck;
    }

    public double getNumTargetCards() {
        return numTargetCards;
    }

    public double getProbability() {
        return probability;
    }

    public int getNumCardsFromDesiredHand() {
        return numCardsFromDesiredHand;
    }

    public ProbabilityTreeNode getNoNode() {
        return noNode;
    }

    public ProbabilityTreeNode getYesNode() {
        return yesNode;
    }

    public int getNumTargetCardDecrementation() {
        return numTargetCardDecrementation;
    }

    public boolean isHandAchieved() {
        return isHandAchieved;
    }
}
