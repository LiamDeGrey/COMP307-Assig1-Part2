package main;

import logic.TestInstances;
import logic.TreeBuilder;

/**
 * Created by Liam on 29-Mar-15.
 */
public class Main {

    public static void main(String[] args) {
        if (args.length > 1) {
            TreeBuilder tree = new TreeBuilder(args[0]);
            new TestInstances(tree.getRoot(), args[1]);
        }
    }
}
