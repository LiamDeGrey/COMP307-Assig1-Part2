package main;

import logic.TreeBuilder;

/**
 * Created by Liam on 29-Mar-15.
 */
public class Main {

    public static void main(String[] args) {
        if (args.length > 0) {
            new TreeBuilder(args[0]);
        }
    }
}
