/**
 * 
 * @author Revani Govender
 * CMSC 451
 * Project1
 * 04/12/15
 * Using Eclipse 
 *   
 * This project benchmarks Bubble Sort Recursive and Iterative methods
 *
 */
public class SortMain{


	public static void main(String[] args) {
		int[] sizes = {5, 10, 20, 30, 40, 50, 100, 500, 1000, 2000, 3000, 4000, 5000, 
				6000, 7000, 8000, 9000, 10000, 15000};
		
		BenchmarkSorts sort = new BenchmarkSorts(sizes);
		sort.runSorts();
		sort.displayReport();
	}

}
