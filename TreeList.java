import java.util.ArrayList;

/**
 * Created by School on 7/8/2017.
 */


public class TreeList extends ArrayList<ProbabilityTree>{

    private int numTrees;

    public TreeList(){
        this.numTrees = 0;
    }

    @Override
    public boolean add(ProbabilityTree tree) {
        numTrees++;
        return super.add(tree);
    }
}
