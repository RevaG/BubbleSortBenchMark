import java.awt.BorderLayout;
import java.io.FileWriter;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

/**
 * 
 * @author Revani Govender
 * CMSC 451
 * Project1
 * 04/12/15
 * Using Eclipse 
 * 
 * This Class Benchmarks the sorts and displays them in a GUI and in the console
 * The benchmarks are average counts, standard deviation of counts, average time
 * and standard deviation of time for each sort
 *
 */

public class BenchmarkSorts {
	private int[] testSizes;
	private final int runs = 50;
	private int[][] recursiveCounts;
	private int [][] iterativeCounts;
	private long [][] recursiveTime;
	private long [][] iterativeTime;
	BubbleSort sort;
	private Object[][] iterativePrint; 
	private Object[][] recursivePrint;
	
	
	BenchmarkSorts(int[] sizes){
		sort = new BubbleSort();
		testSizes = sizes;
		iterativePrint = new Object[testSizes.length][5];
		recursivePrint = new Object[testSizes.length][5];
		recursiveCounts = new int[testSizes.length][runs];
		iterativeCounts = new int[testSizes.length][runs];
		
		recursiveTime = new long[testSizes.length][runs];
		iterativeTime = new long[testSizes.length][runs];
	
	}
	
	public void runSorts(){
		
		for (int i=0; i<testSizes.length; i++){
			System.out.println("\nNow Sorting lists with n = " + testSizes[i]);
			for(int j = 0; j<runs; j++){
				int[] createdArray = makeArray(testSizes[i]);
				int[] recursiveTest = new int[testSizes[i]];
				int[] iterativeTest = new int[testSizes[i]];
				
					System.arraycopy(createdArray, 0, recursiveTest, 0, createdArray.length);
					try{
					sort.recursiveSort(recursiveTest);
					}catch(StackOverflowError e){//if n is too large will break out of loop and return 0 for sorts
						System.out.println ("not enough memory to perform "
								+ "recursive sort with n = " + testSizes[i]);
						break;
					}
					recursiveCounts[i][j] = sort.getCount();
					recursiveTime[i][j] = sort.getTime();
					
					
					System.arraycopy(createdArray, 0, iterativeTest, 0, createdArray.length);
					sort.iterativeSort(iterativeTest);
					iterativeCounts[i][j] = sort.getCount();
					iterativeTime[i][j] = sort.getTime();
					
					System.out.print("-"); //track progress of sort

			}
		}
		
	}
	
	/**
	 * Displays the reports in the console as well as in a GUI table
	 * 
	 */
	public void displayReport(){
		double[] dataRecursive;
		double [] dataIterative;
        System.out.println("\n\nReports from the runs");
        
        String tableFormat = "%15s|%15s|%15s|%15s|";

        System.out.print("+");
        for(int i = 0; i < 135; i++) System.out.print("-");
        System.out.println("+");
        System.out.printf("|%-7s|%28s%35s|%28s%35s|%n", "Size N", "Iterative", "", "Recursive", "");
        System.out.print("+");
        for(int i = 0; i < 135; i++) System.out.print("-");
        System.out.println("+");
        System.out.printf("|%7s|", "");
        for(int i = 0; i < 2; i++) {
            System.out.printf(tableFormat, "Average", "Standard", "Average", "Standard");
        }
        System.out.printf("%n|%7s|", "");
        for(int i = 0; i < 2; i++) {
            System.out.printf(tableFormat, "Critical", "Deviation", "Execution", "Deviation");
        }
        System.out.printf("%n|%7s|", "");
        for(int i = 0; i < 2; i++) {
            System.out.printf(tableFormat, "Operation", "of Count", "Time", "of Time");
        }
        System.out.printf("%n|%7s|", "");
        for(int i = 0; i < 2; i++) {
            System.out.printf(tableFormat, "Count", "", "(Seconds)", "(Seconds)");
        }
        System.out.print("\n+");
        for(int i = 0; i < 135; i++) System.out.print("-");
        System.out.print("+");

        String valueFormat = "%,15.2f|%15.4f|%15.6f|%15.6f|";
        
        for(int i = 0; i < testSizes.length; i++) {
            System.out.printf("%n|%7d|", testSizes[i]);
                dataIterative = calculateValues(iterativeCounts[i], iterativeTime[i]);
                System.out.printf(valueFormat, dataIterative[0], dataIterative[1], dataIterative[2], dataIterative[3]);
                dataRecursive = calculateValues(recursiveCounts[i], recursiveTime[i]);
                System.out.printf(valueFormat, dataRecursive[0], dataRecursive[1], dataRecursive[2], dataRecursive[3]);
                
        }
       

        System.out.print("\n+");
        for(int i = 0; i < 135; i++) System.out.print("-");
        System.out.println("+");
        
        
        double[] dataIterative1;
        String[] dataIterativeString;
		double[] dataRecursive1;
		String[] dataRecursiveString;
		
		/*
		 *Used a string so that number formatter could be used
		 *to clean up the display in the table 
		 */
		try{
             	   FileWriter fileWriter = new FileWriter("Output2.csv");
             	   fileWriter.append(" , Iterative, , , ,Recursive, , , ,\n");
             	   fileWriter.append("n, Ave. Count, Std. Dev. Count, "
     	     		 		+ "Ave. Time, Std. Dev Time, Ave. Count, Std. Dev. Count, "
     	     		 		+ "Ave. Time, Std. Dev Time");
             	   fileWriter.append("\n");
             	  
	        for(int i = 0; i < testSizes.length; i++) {
	                dataIterative1 = calculateValues(iterativeCounts[i], iterativeTime[i]);
	                dataIterativeString = getStringValues(dataIterative1);
	                this.iterativePrint[i][0] = this.testSizes[i];
	                this.iterativePrint[i][1] = dataIterativeString[0];
	                this.iterativePrint[i][2] = dataIterativeString[1];
	                this.iterativePrint[i][3] = dataIterativeString[2];
	                this.iterativePrint[i][4] = dataIterativeString[3];
	
	
	                dataRecursive1 = calculateValues(recursiveCounts[i], recursiveTime[i]);
	                dataRecursiveString = getStringValues(dataRecursive1);
	                this.recursivePrint[i][0] = this.testSizes[i];
	                this.recursivePrint[i][1] = dataRecursiveString[0];
	                this.recursivePrint[i][2] = dataRecursiveString[1];
	                this.recursivePrint[i][3] = dataRecursiveString[2];
	                this.recursivePrint[i][4] = dataRecursiveString[3];
	                
	                String testSize = Integer.toString(this.testSizes[i]);
	               
	                fileWriter.append(testSize);
	                fileWriter.append(",");
	                fileWriter.append(dataIterativeString[0]);
	                fileWriter.append(",");
	                fileWriter.append(dataIterativeString[1]);
	                fileWriter.append(",");
	                fileWriter.append(dataIterativeString[2]);
	                fileWriter.append(",");
	                fileWriter.append(dataIterativeString[3]);
	                fileWriter.append(",");
	                fileWriter.append(dataRecursiveString[0]);
	                fileWriter.append(",");
	                fileWriter.append(dataRecursiveString[1]);
	                fileWriter.append(",");
	                fileWriter.append(dataRecursiveString[2]);
	                fileWriter.append(",");
	                fileWriter.append(dataRecursiveString[3]);
	                fileWriter.append("\n");
	               
	        }
	        
	        fileWriter.flush();
	        fileWriter.close();
         }catch(Exception e){
             	   System.out.print("Error in file writer");
                }
		
        String[] iterativeColumnNames ={
        		"n","Ave. Count", "Std. Dev. Count", 
        		"Ave. Time","Std. Dev Time"	
        };
        
        JTable tableIterative = new JTable(this.iterativePrint, iterativeColumnNames );
        tableIterative.setVisible(true);

        
        String[] recursiveColumnNames ={
        		"n","Ave. Count", "Std. Dev. Count", 
        		"Ave. Time","Std. Dev Time"		
        };
        
        final JTable tableRecursive = new JTable(this.recursivePrint, recursiveColumnNames);

        JFrame frame = new JFrame("Results");
        
        
        JScrollPane tablePaneIterative = new JScrollPane(tableIterative);
		JScrollPane tablePaneRecursive = new JScrollPane(tableRecursive);

        
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        
        panel.setBorder(BorderFactory.createTitledBorder("Iterative"));
        panel.add(tablePaneIterative, BorderLayout.CENTER);
        
        panel2.add(tablePaneRecursive, BorderLayout.CENTER);
        panel2.setBorder(BorderFactory.createTitledBorder("Recursive"));
        

        panel.setOpaque(true);
        
        tableIterative.setFillsViewportHeight(true);
        tableRecursive.setFillsViewportHeight(true);

        
        JSplitPane hpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel, panel2);
        hpane.setResizeWeight(0.5);
        
        frame.setContentPane(hpane);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
        frame.validate();
		
	}
		
	/**
	 * Sends in counts and times arrays and calculates:
	 * Average Time
	 * Average counts
	 * Standard Deviation Counts
	 * Standard Deviation Times
	 * 
	 * @param counts
	 * @param times
	 * @return double[] data
	 */
	private double[] calculateValues(int[] counts, long[] times) {
        double averageCount = 0;
        double averageTime = 0;
        double varianceCount = 0;
        double deviationCount = 0;
        double varianceTime = 0;
        double deviationTime = 0;

        for(int i = 0; i < counts.length; i++) {
            averageCount += counts[i];
            averageTime += times[i] / 1000000000.0; //converting to seconds
        }
//        System.out.print("counts length: " + times.length);
        averageCount = averageCount / counts.length;
        averageTime = averageTime / times.length;

        for(int i = 0; i < counts.length; i++) {
            varianceCount += Math.pow(averageCount - counts[i], 2);
            varianceTime += Math.pow(averageTime - (times[i]/1000000000.0),2);
        }

        //stddev = sqrt((ave - val)^2/(n))
        varianceCount = varianceCount / counts.length;
        varianceTime = varianceTime / times.length;
        
        deviationCount = Math.sqrt(varianceCount);
        deviationTime = Math.sqrt(varianceTime);

        double[] data = {averageCount, deviationCount, averageTime, deviationTime};
        return data;
    }
	
	/**
	 * 
	 * Takes the double data[] and makes it a string to utilize Decimal Formatter
	 * The new format uses scientific notation due to the high amount of decimal places.
	 * Decimal format: 0.00E00
	 * @param data
	 * @return dataString
	 */
	private String[] getStringValues(double[]data) {
		
		double averageCount = data[0];
		double deviationCount = data[1];
		double averageTime = data[2];
		double deviationTime = data[3];
		
		DecimalFormat formatter = new DecimalFormat("0.0000000E00");
        

        String deviationCountString = formatter.format(deviationCount);
        String deviationTimeString = formatter.format(deviationTime);
        
        String averageCountString = formatter.format(averageCount);
        String averageTimeString = formatter.format(averageTime);
        
        String[] dataString ={averageCountString, deviationCountString, averageTimeString, deviationTimeString};
		
		return dataString;
		
	}
	/**
	 * Takes array size and fills array with random numbers between 1 and 1000
	 * 
	 * @param size
	 * @return array
	 */
	public static int[] makeArray(int size){
		int[] array = new int[size];
		
		for(int i = 0; i<size; i++){
			array[i] = (int)(Math.random() * 1000);
		}
		return array;
	}

}//end BenchmarkSorts
