package logic;

/**
 * Created by Liam on 29-Mar-15.
 */
public class LeafNode extends Node {
    private final String className;
    private final double probability;

    public LeafNode(String className, double probability) {
        this.className = className;
        this.probability = probability;
    }

    @Override
    public void report(String indent){
        /*if (count==0)
            System.out.format("%sUnknown\n", indent);
        else*/
            System.out.format("%sClass %s, prob=%4.2f\n",
                    indent, className, probability);
    }
}
