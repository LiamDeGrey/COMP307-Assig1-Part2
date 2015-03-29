package logic;

/**
 * Created by Liam on 29-Mar-15.
 */
public class LeafNode extends Node {
    private final String className;
    private final double probability;

    public LeafNode(String className, double probability) {
        super("", null, null);

        this.className = className;
        this.probability = probability;
    }
}
