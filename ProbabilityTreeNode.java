/**
 * Created by School on 5/20/2017.
 */

public class ProbabilityTreeNode{
    private double target, deck, needed, targetDecrementation, prob, totalProb;
    private ProbabilityTreeNode yesNode, noNode;
    public  boolean handAchieved, isLeaf;

    public ProbabilityTreeNode(double target, double deck, double needed, double targetDecrementation, double prob, double totalProb){

        this.target = target;
        this.deck = deck;
        this.needed = needed;
        this.prob = prob;
      //  this.totalProb = totalProb;
        this.handAchieved = this.isLeaf = false;
        this.targetDecrementation = targetDecrementation;


//System.out.println("This node's Total Prob is " + totalProb);

        //Base case 1: needed is equal to (or less than) 0, meaning the hand is achieved.
        if(needed <= 0) {
            this.yesNode = this.noNode = null;
            this.handAchieved = isLeaf = true;
        }
        //Base case 2: deck is equal to (or less than) 45, meaning 5 cards have been put on the table.
        else if(deck <= 45){
            this.yesNode = this.noNode = null;
            this.isLeaf = false;
        }
        else{
            this.yesNode = new ProbabilityTreeNode(
                   target - targetDecrementation,deck -1,needed - 1, targetDecrementation, (target / deck) , totalProb * (target / deck));

            this.noNode = new ProbabilityTreeNode(
                    target,deck -1,needed, targetDecrementation, ((deck - target) / deck), totalProb * ((deck - target) / deck));
        }

    }

    public double getTotalProb() {
        return totalProb;
    }

    public ProbabilityTreeNode getYesNode() {
        return yesNode;
    }

    public ProbabilityTreeNode getNoNode() {
        return noNode;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public boolean isHandAchieved() {
        return handAchieved;
    }

    public void setTotalProb(double totalProb) {
        this.totalProb = totalProb;
    }

    public double getProb() {
        return prob;
    }


}

























//public class ProbabilityTreeNode {
//
////    private double numTargetCardsInDeck, numCardsLeftInDeck, probability;
////    private int numCardsFromDesiredHandObtained, numTargetCardDecrementation, heightCount, numNoNodes, numNoNodesNeededToStop;
////    private ProbabilityTreeNode yesNode, noNode;
////    private boolean isHandAchieved;
////    private int nodeType; //0 = root, 1 = yes, 2 = no
////    private double totalProbability;
////    private ProbabilityInfo probabilityInfo;
//
////    private ProbabilityTreeNode yesNode, noNode;
////    private boolean isHandAchieved;
////    private boolean isLeaf;
////
////    private ProbabilityInfo probabilityInfo;
////    private NodeInfo nodeInfo;
////
////    private double targetCards;
////
////    public ProbabilityTreeNode(ProbabilityInfo probabilityInfo, NodeInfo nodeInfo) {
////        this.isHandAchieved = false;
////        this.isLeaf = false;
////
////        if(nodeInfo.getNodeType() == 2){
////            this.targetCards = probabilityInfo.getNumCardsLeftInDeck() - probabilityInfo.getNumTargetCardsInDeck();
////        }
////        else{
////            this.targetCards = probabilityInfo.getNumTargetCardsInDeck();
////        }
////
////        if(nodeInfo.getNumCardsFromDesiredHandObtained() == nodeInfo.getNumCardsNeededToCompleteHand()){
////          //  this.yesNode = this.noNode = null;
////            this.isHandAchieved = true;
////            this.isLeaf = true;
////            return;
////        }
////        else if(nodeInfo.getHeightCount() >= 5 || nodeInfo.getNumNoNodes() >= nodeInfo.getNumNoNodesNeededToStop()){
////            this.isLeaf = true;
////
////            return;
////        }
////
////
////
////        this.probabilityInfo = probabilityInfo;
////        this.nodeInfo = nodeInfo;
//
////        //construction of probabilityInfo and nodeInfo
////        //
////        //for yesNode
////        ProbabilityInfo yesNodePI = new ProbabilityInfo(targetCards,
////                probabilityInfo.getNumCardsLeftInDeck() - 1,
////                probabilityInfo.getTotalProbability() * probabilityInfo.getProbability());
////
////        NodeInfo yesNodeNI = new NodeInfo(nodeInfo.getNumCardsFromDesiredHandObtained() + 1,
////                nodeInfo.getNumCardsNeededToCompleteHand(),
////                nodeInfo.getNumTargetCardDecrementation(),
////                nodeInfo.getNumNoNodes(),
////                nodeInfo.getNumNoNodesNeededToStop(),
////                nodeInfo.getHeightCount() + 1,
////                1);
////
////        this.yesNode = new ProbabilityTreeNode(yesNodePI, yesNodeNI);
////
////        ProbabilityInfo noNodePI = new ProbabilityInfo(targetCards,
////                probabilityInfo.getNumCardsLeftInDeck() - 1,
////                probabilityInfo.getTotalProbability() * probabilityInfo.getProbability());
////
////        NodeInfo noNodeNI = new NodeInfo(nodeInfo.getNumCardsFromDesiredHandObtained(),
////                nodeInfo.getNumCardsNeededToCompleteHand(),
////                nodeInfo.getNumTargetCardDecrementation(),
////                nodeInfo.getNumNoNodes() + 1,
////                nodeInfo.getNumNoNodesNeededToStop(),
////                nodeInfo.getHeightCount() + 1,
////                2);
////
////        this.noNode = new ProbabilityTreeNode(noNodePI, noNodeNI);
//
////        this.yesNode = new ProbabilityTreeNode(numTargetCardsInDeck - (double) numTargetCardDecrementation,
////                numCardsLeftInDeck - 1, numCardsFromDesiredHandObtained + 1, numTargetCardDecrementation,
////                numCardsNeededToCompleteHand, heightCount + 1, totalProbability * probability, numNoNodes, numNoNodesNeededToStop, 1);
////
////        this.noNode = new ProbabilityTreeNode(numTargetCardsInDeck, numCardsLeftInDeck - 1, numCardsFromDesiredHandObtained,
////                numTargetCardDecrementation, numCardsNeededToCompleteHand, heightCount + 1, totalProbability * probability,
////                numNoNodes + 1, numNoNodesNeededToStop, 2);
//
////    }
//
//
//    @Override
//    public String toString() {
//
//      String s = "";
//      if (yesNode == null || noNode == null) {
//          if (yesNode == null) {
//              s += "yesNode is null\n";
//          }
//
//          if (noNode == null) {
//              s += "noNode is null";
//          }
//
//          return s;
//      }
//      try {
//      String st = "";
//      switch (nodeInfo.getNodeType()) {
//          case 0:
//              st = "root";
//              break;
//          case 1:
//              st = "Yes Node";
//              break;
//          case 2:
//              st = "No Node";
//              break;
//      }
//
//
//      s += probabilityInfo.getNumTargetCardsInDeck() + " / " + probabilityInfo.getNumCardsLeftInDeck() + " = " + probabilityInfo.getProbability() +
//              "\n numCardsFromDesiredHandObtained = " + nodeInfo.getNumCardsFromDesiredHandObtained() +
//              "\n heightCount = " + nodeInfo.getHeightCount() +
//              "\n Type of Node: " + st +
//              "\n totalProbability: " + probabilityInfo.getTotalProbability() +
////              "\n\n yesNode: " + yesNode +
////              "\n\n noNode: " + noNode +
//              "\n----------------------------------------------------------------------------------";
//
//
//
//  }catch (NullPointerException e){
//      System.out.println("NullPointerException Caught");
//  }
//        return s;
//    }
//
//    public ProbabilityInfo getProbabilityInfo() {
//        return probabilityInfo;
//    }
//
//    public NodeInfo getNodeInfo() {
//        return nodeInfo;
//    }
//
//    //    public ProbabilityTreeNode getYesNode() {
////        return yesNode;
////    }
////
////    public ProbabilityTreeNode getNoNode() {
////        return noNode;
////    }
//}