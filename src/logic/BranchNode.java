package logic;


/**
 * Created by Liam on 29-Mar-15.
 */
public class BranchNode extends Node {
    private final String attr;
    private final Node left;
    private final Node right;

    public BranchNode(String attr, Node left, Node right) {
        this.attr = attr;
        this.left = left;
        this.right = right;
    }

    @Override
    public void report(String indent){
        System.out.format("%s%s = True:\n",
                indent, attr);
        left.report(indent+" ");
        System.out.format("%s%s = False:\n",
                indent, attr);
        right.report(indent+" ");
    }
}
