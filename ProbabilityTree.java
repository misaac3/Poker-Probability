/**
 * Created by School on 5/20/2017.
 */
public class ProbabilityTree{
    private double deck = 50;
    private double prob, totalProb = 1.0;
    //private double totalProb = 1.0;
    private ProbabilityTreeNode root;

    private String name;

    private int type;   //0 is pair, 1 is two pair, 2 is three of a kind
                        //3 is straight, 4 is flush, 5 is full house
                        //6 is four of a kind, 7 is straight flush , 8 is Royal Flush

    private ProbabilityTreeNode currentNode;

    private TargetCardArrayList targetCards;
    private Card distinguisingCard1, distinguisingCard2;



    public ProbabilityTree(double target, double needed, double targetDecrementation, int type, Card distinguisingCard1,
                           Card distinguisingCard2){

        this.currentNode = this.root = new ProbabilityTreeNode(target, deck, needed, targetDecrementation, prob, totalProb);
        this.root.setTotalProb(1.0);
        updateTree(root);
        this.type = type;

        if(type < 4 || type == 5 || type == 6){
            targetCards = new TargetCardArrayList(1);
        }
        else if(type == 4){
            targetCards = new TargetCardArrayList(2);
        }
        else{
            targetCards = new TargetCardArrayList(3);
        }

        if(type != 3 && type < 7){
            targetCards.intializeTargetCards(distinguisingCard1, type);
        }
        else if(type == 3){
            targetCards.intializeStraightTargets(distinguisingCard1.getValueNum()); //TODO when making trees for straights make new Cards when contruscting
        }
        else if (type == 7){
            targetCards.intializeSuitedStraightTargets(distinguisingCard1.getValueNum(), distinguisingCard1, 0);
        }
        else {
            targetCards.intializeSuitedStraightTargets(14, distinguisingCard1, 1);

        }

        this.distinguisingCard1 = distinguisingCard1;
        this.distinguisingCard2 = distinguisingCard2;

        System.out.println("\nI am printing from the ProbabilityTree Constructor\nMy distinguisingCard is " + distinguisingCard1 + " \nHere are my target Cards: " + targetCards + "\n");


    }

    public ProbabilityTree(double target, double needed, double targetDecrementation, int type, Card distinguisingCard1){

      //  this.name = assignName();
        this.currentNode = this.root = new ProbabilityTreeNode(target, deck, needed, targetDecrementation, prob, totalProb);
        this.root.setTotalProb(1.0);
        updateTree(root);
        this.type = type;
//        this.name = assignName();

        if(type < 4 || type == 5 || type == 6){
            targetCards = new TargetCardArrayList(1);
        }
        else if(type == 4){
            targetCards = new TargetCardArrayList(2);
        }
        else{
            targetCards = new TargetCardArrayList(3);
        }

        if(type != 3 && type < 7){
            targetCards.intializeTargetCards(distinguisingCard1, type);
        }
        else if(type == 3){
            targetCards.intializeStraightTargets(distinguisingCard1.getValueNum()); //TODO when making trees for straights make new Cards when contruscting
        }
        else if (type == 7){
            targetCards.intializeSuitedStraightTargets(distinguisingCard1.getValueNum(), distinguisingCard1, 0);
        }
        else {
            targetCards.intializeSuitedStraightTargets(14, distinguisingCard1, 1);

        }

        this.distinguisingCard1 = distinguisingCard1;

//System.out.println("\nI am printing from the ProbabilityTree Constructor\nMy distinguisingCard is " + distinguisingCard1 + " \nHere are my target Cards: " + targetCards + "\n");



    }


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

    public ProbabilityTreeNode getRoot() {
        return root;
    }

    public ProbabilityTreeNode getCurrentNode() {
        return currentNode;
    }


    //TODO Finish Method
    public String assignName(){
        String s = "";
        String baseName = "";
        switch (this.type){
            case 0:
                baseName = "pair of " + distinguisingCard1.getValue();
                break;
            case 1:
                baseName = "two pair of ";
                break;
            case 2:
                baseName = "three of a kind of " + distinguisingCard1.getValue();
                break;
            case 3:
                baseName = "straight of " + distinguisingCard1.getValue();
                break;
            case 4:
                baseName = "flush of " + distinguisingCard1.getSuit();
                break;
            case 5:
                baseName = "full house of ";
                break;
            case 6:
                baseName = "four of a kind of " + distinguisingCard1.getValue();
                break;
            case 7:
                baseName = "straight flush  of ";
                break;
            case 8:
                baseName = "royal flush of ";
                break;

        }


        return baseName;
    }

    public TargetCardArrayList getTargetCards() {
        return targetCards;
    }

    @Override
    public String toString() {
        return name;
    }
}