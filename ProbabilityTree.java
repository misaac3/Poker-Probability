/**
 * Created by School on 5/20/2017.
 */
public class ProbabilityTree{
    private double deck = 50;
    private double prob = 1.0;
    private double totalProb = 1.0;
    private ProbabilityTreeNode root;

    private String name;
    private int type; //0 is pair, 1 is two pair, 2 is three of a kind... ...8 is Royal Flush
    private ProbabilityTreeNode currentNode;
    private Card relevantCard1;
    private Card relevantCard2;
    private int numRelevantCards;

    //

    public ProbabilityTree(double target, double needed, double targetDecrementation){

        this.currentNode = this.root = new ProbabilityTreeNode(target, deck, needed, targetDecrementation, prob, totalProb);
        this.root.setTotalProb(1.0);
        updateTree(root);
        this.type = type;
    }

//    public ProbabilityTree(Card relevantCard, int type, double target, double needed, double targetDecrementation){
//
//        this.currentNode = this.root = new ProbabilityTreeNode(target, deck, needed, targetDecrementation, prob, totalProb);
//        this.type = type;
//        this.relevantCard1 = relevantCard;
//        this.numRelevantCards = 1;
//
//    }
//
//    public ProbabilityTree(Card relevantCard1, Card relevantCard2, int type, double target, double needed, double targetDecrementation){
//
//        this.currentNode = this.root = new ProbabilityTreeNode(target, deck, needed, targetDecrementation, prob, totalProb);
//        this.type = type;
//        this.relevantCard1 = relevantCard1;
//        this.relevantCard2 = relevantCard2;
//        this.numRelevantCards = 1;
//
//    }

    public void moveCurrentNode(String move){
        if (move.toLowerCase().equals("yes")){
            currentNode = currentNode.getYesNode();
        }
        else if (move.toLowerCase().equals("no")){
            currentNode = currentNode.getNoNode();
        }

        updateTree(currentNode);

    }

    public double getProbabilityOfAchievingHand(ProbabilityTreeNode startNode){
        double d = 0.0;
//        startNode.setTotalProb(1.0);
//        updateTree(startNode);
        d = pHelper(startNode, d);
        return d;
    }

    public void updateTree(ProbabilityTreeNode startNode){
        startNode.setTotalProb(1);
        updateTreeHelper(startNode);
    }

    public void updateTreeHelper(ProbabilityTreeNode node){
        if(node.getNoNode() != null && node.getYesNode() != null){
            node.getYesNode().setTotalProb(node.getTotalProb() * node.getYesNode().getProb());
            node.getNoNode().setTotalProb(node.getTotalProb() * node.getNoNode().getProb());
            updateTreeHelper(node.getYesNode());
            updateTreeHelper(node.getNoNode());

        }
    }

    public double pHelper(ProbabilityTreeNode node, double d) {
        if(node == null){
            return d;
        }

        if(node.isHandAchieved()){
            d += node.getTotalProb();
        }

        if(node.isLeaf() || node == null){
            return d;
        }

        d = pHelper(node.getYesNode(), d);
        d = pHelper(node.getNoNode(), d);

        return d;
    }

    public ProbabilityTreeNode getRoot() {
        return root;
    }

    public ProbabilityTreeNode getCurrentNode() {
        return currentNode;
    }

    public String assignName(){
        String s = "";
        String baseName = "";
        switch (this.type){
            case 0:
                baseName = "pair of ";
                break;
            case 1:
                baseName = "two pair of ";
                break;
            case 2:
                baseName = "three of a kind of ";
                break;
            case 3:
                baseName = "straight of ";
                break;
            case 4:
                baseName = "flush of ";
                break;
            case 5:
                baseName = "full house of ";
                break;
            case 6:
                baseName = "four of a kind of ";
                break;
            case 7:
                baseName = "straight flush  of ";
                break;
            case 8:
                baseName = "royal flush of ";
                break;

        }

        return s;
    }

}