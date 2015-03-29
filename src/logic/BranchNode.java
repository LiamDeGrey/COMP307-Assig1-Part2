package logic;


/**
 * Created by Liam on 29-Mar-15.
 */
public class BranchNode extends Node {
    private final String attr;

    public BranchNode(String attr, Node left, Node right) {
        super(attr, left, right);

        this.attr = attr;
    }
}
