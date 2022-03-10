package iterator;

import java.util.Iterator;

public class TwoArrays implements Iterable<Integer> { //a class for combining two arrays
	private int[] a1;
	private int[] a2;
	private int count1, count2;
	public TwoArrays(int[] a1, int[] a2) {
		this.a1 = a1;
		this.a2 = a2;
		count1 = a1.length;
		count2 = a2.length;
	}
	public class myIterator implements Iterator<Integer> { //a method that uses Iterable methods
		private int i=0,j=0,flag=1;
		private int iter() { //method for each array's turn
			if (!(this.hasNext()))
				return 0;
			if (i>=count1) //check if index of the first array reached the limit
				return 2;
			if (j>=count2)//check if index of the second array reached the limit
				return 1;
			if (flag==1) { // a flag to go over again
				flag=2;
				return 1;
			}
			flag=1;
			return 2;
		}
		@Override
		public boolean hasNext() { //Iterable method for iterations and a condition for the arrays limits
			return i<count1 || j<count2;
		}
		@Override
		public Integer next() {//method to go over the two arrays
			int temp=iter(); 
			if (temp==1)//the first element goes for the first array
				return a1[i++];
			if (temp==2)//the second element goes for the second array
				return a2[j++];
			return null;
		}
	}
	@Override
	public Iterator<Integer> iterator() { //works as an interface for Iterable
		return new myIterator();
	}
}
