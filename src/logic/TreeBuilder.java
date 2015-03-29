package logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liam on 29-Mar-15.
 */
public class TreeBuilder {
    public static final int DIE = 0;
    public static final int LIVE = 1;

    private final List<String> classNames;
    private final List<String> attributeNames;
    private final List<Instance> allInstances;

    public TreeBuilder(String fileName) {
        Object[] data = ReadInstances.readDataFile(fileName);
        this.classNames = (ArrayList<String>) data[ReadInstances.CLASSNAMES];
        this.attributeNames = (ArrayList<String>) data[ReadInstances.ATTRNAMES];
        this.allInstances = (ArrayList<Instance>) data[ReadInstances.INSTANCES];

        build(allInstances, attributeNames).report("");
    }

    //Returns root node
    public Node build (List<Instance> instances, List<String> attributes) {
        if (instances.isEmpty()) {
            return findMostProbableClass(allInstances);
        }
        if (findImpurity(instances) == 0) {
            return new LeafNode(classNames.get(instances.get(0).getClassName()), 1);
        }
        if (attributes.isEmpty()) {
            return findMostProbableClass(instances);
        } else {
            double bestWeightedImpurity = -1;
            String bestAttr = "";
            ArrayList<Instance> bestTrueInstances = null;
            ArrayList<Instance> bestFalseInstances = null;

            for (int i = 0; i < attributes.size(); i++) { //iterate through all attributes
                ArrayList<Instance> trueInstances = new ArrayList<>();
                ArrayList<Instance> falseInstances = new ArrayList<>();

                for (Instance instance : instances) {
                    if (instance.getValue(i)) trueInstances.add(instance);
                    else falseInstances.add(instance);
                }
                double trueImpurity = findImpurity(trueInstances);
                double falseImpurity = findImpurity(falseInstances);

                double weightedImpurity =
                        ((trueInstances.size() / (trueInstances.size() + falseInstances.size())) * trueImpurity)
                        + ((falseInstances.size() / (trueInstances.size() + falseInstances.size())) * falseImpurity);

                if (bestWeightedImpurity == -1 || weightedImpurity < bestWeightedImpurity) {
                    bestWeightedImpurity = weightedImpurity;
                    bestAttr = attributes.get(i);
                    bestTrueInstances = trueInstances;
                    bestFalseInstances = falseInstances;
                }
            }
            attributes.remove(bestAttr);
            Node left = build(bestTrueInstances, attributes);
            Node right = build(bestFalseInstances, attributes);
            return new BranchNode(bestAttr, left, right);
        }
    }

    private LeafNode findMostProbableClass(List<Instance> instances) {
        int[] total = new int[classNames.size()];
        for (Instance instance : instances) {
            total[instance.getClassName()]++;
        }

        int indexMax = (Math.max(total[DIE], total[LIVE]) == total[DIE])? DIE : LIVE;
        String className = classNames.get(indexMax);
        double probability = total[indexMax] / instances.size();
        return new LeafNode(className, probability);
    }

    private double findImpurity(List<Instance> instances) {
        int[] total = new int[classNames.size()];
        for (Instance instance : instances) {
            total[instance.getClassName()]++;
        }
        return (total[DIE] * total[LIVE]) / (Math.pow((total[DIE] + total[LIVE]), 2));
    }
}
