package main;

import logic.TestInstances;
import logic.TreeBuilder;

/**
 * Created by Liam on 29-Mar-15.
 */
public class Main {
	private static final int TRAININGFILE = 0;
	private static final int TESTFILE = 1;
	private static final int RUNS = 2;
	private static final String APPENDRUN = "-run";

	private static double totalAccuracy = 0.0;

	private static void test(String trainingFile, String testFile) {
		TreeBuilder tree = new TreeBuilder(trainingFile);
        TestInstances testInstances = new TestInstances(tree.getRoot(), tree.getLikelyClassName(), testFile);
		totalAccuracy += testInstances.findAllClasses();
		testInstances.printBaselineClassifier();
	}

	private static String[] getFileNames(String trainingFile, String testFile, int run) {
		String[] trainingName;
    	String[] testName;
        trainingName = trainingFile.split("\\.");
        testName = testFile.split("\\.");

        return new String[] {
        		trainingName[0]+APPENDRUN+String.format("%02d", run)+"."+trainingName[1],
        		testName[0]+APPENDRUN+String.format("%02d", run)+"."+testName[1]
        				};
	}

    public static void main(String[] args) {
    	if (args.length > 1) {
    		String trainingFile = args[TRAININGFILE];
    		String testFile = args[TESTFILE];

    		if (args.length < 3) {
    			test(trainingFile, testFile);
    		} else {
				int runs = Integer.parseInt(args[RUNS]);

    			String[] fileNames;
    	        for (int run = 1; run < runs + 1; run++){
    	        	fileNames = getFileNames(trainingFile, testFile, run);
    	        	System.out.println(fileNames[0]+", "+fileNames[1]);
    	        	test(fileNames[TRAININGFILE], fileNames[TESTFILE]);
    	        }

				System.out.printf("\nAverage accuracy over %d runs = %.1f%%\n", runs, (totalAccuracy / runs) * 100.00);
    		}
    	}
    }
}
