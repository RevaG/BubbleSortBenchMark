/**
 * 
 * @author Revani Govender
 * CMSC 451
 * Project1
 * 04/12/15
 * Using Eclipse 
 * 
 * This Class implements Sort Interface
 * The class implements recursive and iterative sorts, checks if the
 * array is sorted, and returns the critical counts and time for each sort
 *
 */
public class BubbleSort implements SortInterface{
	private int count = 0;
	private long time;
	
	/**
	 * 
	 */
	@Override
	public void recursiveSort(int[] list){
		count = 0;//initialize the count before the recursive sort is called
		int n = list.length;
		
		long startTime = System.nanoTime();
		recursiveSort(list, n);
		time = System.nanoTime()-startTime;
		
		try{
			checkSortedArray(list);
		}catch(UnsortedException e){
			System.out.println("The Recursive Sort did not work");
		}
	}
	
	/**
	 * Recursively Bubble sorts the list
	 * 
	 * @param list
	 * @param n
	 * @return list
	 */
	public int[] recursiveSort(int[] list, int n) {
		count+=1; //how many times recursion is called

		if (n==0){
			return list;
		}
		
		for(int i=0; i < n-1; i++){
			if(list[i] > list[i+1]){ //if number to left is greater than number to right
				swap(list,i);
				count +=1;//counting swaps
			}
		}
		
		return recursiveSort(list, n-1);
		
	}

	/**
	 * Iteratively sorts the arrays
	 */
	@Override
	public void iterativeSort(int[] list) {
		long startTime = System.nanoTime();
		count = 0;
		boolean swapped;
		do{
			count +=1; //how many times entered into do loop
			swapped = false;
			for(int i = 0; i<list.length-1; i ++){
				if(list[i] > list[i+1]){ //if number to left is greater than number to right
					swap(list,i);
					swapped = true;
					count+=1;//counting swaps
				}
			
			}
		}while (swapped == true);
		
		time = System.nanoTime()-startTime;
		try{
				checkSortedArray(list);
			}catch(UnsortedException e){
				System.out.println("The Iterative Sort did not work");
			}
	}
	/**
	 * used to swap adjacent values
	 * @param list
	 * @param i
	 */
	public static void swap(int[] list, int i){
		int temp = list [i]; //create temp number holder
		list[i] = list[i+1]; //swap the numbers
		list[i+1] = temp; //swapping completion
	}

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public long getTime() {
		return time;
	}
	
	/**
	 * Checks that arrays are sorted
	 * @param list
	 * @throws UnsortedException
	 */
	public void checkSortedArray(int[] list) throws UnsortedException{
		for(int i = 0; i<list.length-1;i++){
			if(list[i] > list[i+1]){
				for(int j = 0; i<list.length-1;j++){
					System.out.println(" " + list[j]);
				}
			throw new UnsortedException("The array was nor sorted correctly: \n" +
				list[i] + " at indices " + i +" and " + list[i+1] + " at indices " + (i+1)); 
			}
		}
	}

}
