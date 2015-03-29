package logic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Liam on 29-Mar-15.
 */
public class ReadInstances {
    public static final int CLASSNAMES = 0;
    public static final int ATTRNAMES = 1;
    public static final int INSTANCES = 2;

    private static Object[] data = new Object[3];

    public static Object[] readDataFile(String fname) {
        try {
            Scanner din = new Scanner(new File(fname));

            //Read in class names
            List<String> classNames = new ArrayList<>();
            for (Scanner s = new Scanner(din.nextLine()); s.hasNext();) {
                classNames.add(s.next());
            }
            data[CLASSNAMES] = classNames;

            //Read in attribute names
            List<String> attrNames = new ArrayList<>();
            for (Scanner s = new Scanner(din.nextLine()); s.hasNext();) {
                attrNames.add(s.next());
            }
            data[ATTRNAMES] = attrNames;

            //Read in instances
            data[INSTANCES] = readInstances(din, classNames);

            din.close();

            return data;
        }
        catch (IOException e) {
            throw new RuntimeException("Data File caused IO exception");
        }
    }

    public static List<Instance> readInstances(Scanner din, List<String> classNames){
        List<Instance> instances = new ArrayList<>();
        while (din.hasNext()){
            Scanner line = new Scanner(din.nextLine());
            instances.add(new Instance(classNames.indexOf(line.next()),line));
        }
        return instances;
    }
}
