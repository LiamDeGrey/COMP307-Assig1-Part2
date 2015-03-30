package logic;

import java.util.ArrayList;
import java.util.List;

public class TestInstances {
	private static final int TRUE = 0;
	private static final int FALSE = 1;

	private final Node root;
	private final List<String> classNames;
	private final List<String> attributeNames;
	private final List<Instance> allInstances;

	private double[] totalTrueFalse = new double[2];

	@SuppressWarnings("unchecked")
	public TestInstances(Node root, String fileName) {
		Object[] data = ReadInstances.readDataFile(fileName);

		this.root = root;
        this.classNames = (ArrayList<String>) data[ReadInstances.CLASSNAMES];
        this.attributeNames = (ArrayList<String>) data[ReadInstances.ATTRNAMES];
        this.allInstances = (ArrayList<Instance>) data[ReadInstances.INSTANCES];
        findAllClasses();
	}

	private void findAllClasses() {
		for (Instance instance : allInstances) {
			String className = getLeafNode(instance, root).getClassName();
			int index = (classNames.get(instance.getClassName()).equals(className))? TRUE : FALSE;
			totalTrueFalse[index]++;
			System.out.printf("\nfound class Name = %s, actual class name = %s", className, classNames.get(instance.getClassName()));
		}
		System.out.printf("\nCorrectly classified : %2.1f%%\n", (totalTrueFalse[TRUE] / (totalTrueFalse[TRUE] + totalTrueFalse[FALSE]))*100.00);
	}

	private LeafNode getLeafNode(Instance instance, Node node) {
		if (node instanceof LeafNode) {
			return (LeafNode) node;
		} else {
			BranchNode branch = (BranchNode) node;
			int indexOfAttrValue = attributeNames.indexOf(branch.getAttributeName());
			Node next = (instance.getValue(indexOfAttrValue))? branch.getTrueNode() : branch.getFalseNode();
			return getLeafNode(instance, next);
		}
	}

}
