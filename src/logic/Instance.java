package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Liam on 29-Mar-15.
 */
public class Instance {
    private int className;
    private List<Boolean> vals;

    public Instance(int cat, Scanner s){
        className = cat;
        vals = new ArrayList<>();
        while (s.hasNextBoolean()) vals.add(s.nextBoolean());
    }

    public boolean getValue(int index) {
        return vals.get(index);
    }

    public int getClassName(){
        return className;
    }
}