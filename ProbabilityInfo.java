/**
 * Created by School on 5/20/2017.
 */
public class ProbabilityInfo {

    private double numTargetCardsInDeck, numCardsLeftInDeck, probability, totalProbability;
    //private int numCardsFromDesiredHandObtained;

    //, numTargetCardDecrementation;

    //, numNoNodes, numNoNodesNeededToStop;
    //heightCount,
    //private ProbabilityTreeNode yesNode, noNode;
    //private boolean isHandAchieved;
    //private int nodeType; //0 = root, 1 = yes, 2 = no



    //private ProbabilityInfo probabilityInfo;



    public ProbabilityInfo(double numTargetCardsInDeck, double numCardsLeftInDeck, double totalProbability){
        this.numTargetCardsInDeck = numTargetCardsInDeck;
        this.numCardsLeftInDeck = numCardsLeftInDeck;

        this.probability = this.numTargetCardsInDeck / this.numCardsLeftInDeck;

        this.totalProbability = totalProbability * this.probability;


    }



    public double getTotalProbability() {
        return totalProbability;
    }

    public double getProbability() {
        return probability;
    }

    public double getNumCardsLeftInDeck() {
        return numCardsLeftInDeck;
    }

    public double getNumTargetCardsInDeck() {
        return numTargetCardsInDeck;
    }





























    //    private double numTargetCards, numCardsLeftInDeck, probability;
//    private int numCardsFromDesiredHand, numTargetCardDecrementation, heightCount;
//
//
//    public ProbabilityInfo(double numTargetCardsInDeck, double numCardsLeftInDeck, int numCardsFromDesiredHandObtained, int numTargetCardDecrementation, int numCardsNeededToCompleteHand, int heightCount){
//        this.numTargetCards = numTargetCards;
//        this.numCardsLeftInDeck = numCardsLeftInDeck;
//        this.probability = this.numTargetCards / this.numCardsLeftInDeck;
//
//        this.numCardsFromDesiredHand = numCardsFromDesiredHand;
//        this.numTargetCardDecrementation = numTargetCardDecrementation;
//        this.heightCount = heightCount;
//    }
//
//    public double getNumCardsLeftInDeck() {
//        return numCardsLeftInDeck;
//    }
//
//    public double getNumTargetCards() {
//        return numTargetCards;
//    }
//
//    public double getProbability() {
//        return probability;
//    }
//
//    public int getNumCardsFromDesiredHand() {
//        return numCardsFromDesiredHand;
//    }
//
//    public int getNumTargetCardDecrementation() {
//        return numTargetCardDecrementation;
//    }
//
//    public int getHeightCount() {
//        return heightCount;
//    }
}
