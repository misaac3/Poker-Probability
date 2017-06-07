/**
 * Created by School on 5/22/2017.
 */
public class NodeInfo {
    private int numCardsFromDesiredHandObtained, numCardsNeededToCompleteHand, numTargetCardDecrementation, numNoNodes, numNoNodesNeededToStop, heightCount, nodeType;

    public NodeInfo(int numCardsFromDesiredHandObtained, int numCardsNeededToCompleteHand, int numTargetCardDecrementation, int numNoNodes, int numNoNodesNeededToStop, int heightCount, int nodeType ){
        this.numCardsFromDesiredHandObtained = numCardsFromDesiredHandObtained;
        this.numCardsNeededToCompleteHand = numCardsNeededToCompleteHand;
        this.numTargetCardDecrementation = numTargetCardDecrementation;
        this.numNoNodes = numNoNodes;
        this.numNoNodesNeededToStop = numNoNodesNeededToStop;
        this.heightCount = heightCount;
        this.nodeType = nodeType;
    }

    public int getNumCardsFromDesiredHandObtained() {
        return numCardsFromDesiredHandObtained;
    }

    public int getNumCardsNeededToCompleteHand() {
        return numCardsNeededToCompleteHand;
    }

    public int getHeightCount() {
        return heightCount;
    }

    public int getNumTargetCardDecrementation() {
        return numTargetCardDecrementation;
    }

    public int getNumNoNodes() {
        return numNoNodes;
    }

    public int getNodeType() {
        return nodeType;
    }

    public int getNumNoNodesNeededToStop() {
        return numNoNodesNeededToStop;
    }
}
