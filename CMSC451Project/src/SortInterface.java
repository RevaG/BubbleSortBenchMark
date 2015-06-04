/**
 * 
 * @author Revani Govender
 * CMSC 451
 * Project1
 * 04/12/15
 * Using Eclipse 
 * 
 * Interface to utilize iterative and recursive sorts
 *
 */
public interface SortInterface {
	public void recursiveSort(int[] list);
	
	public void iterativeSort(int[] list);
	
	public int getCount();
	
	public long getTime();
}
