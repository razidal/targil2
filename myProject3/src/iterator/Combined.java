package iterator;

import java.util.Iterator;

public class Combined<E> implements Iterable<E> {//class to combine to given sets
	private Iterable<E> first, second;
	public Combined(Iterable<E> first, Iterable<E> second) {
		this.first = first;
		this.second = second;
	}
	private class myIterator implements Iterator<E> {

		private int flag=1;
		private Iterator<E> first1,second1;
		public myIterator() {
			first1 = first.iterator();
			second1 = second.iterator();
		}
		private int iter() {//method to make give each set its turn
			if (!first1.hasNext() && !second1.hasNext()) //if the two sets have no next integer (empty or finished iterations)
				return 0;
			if (!first1.hasNext()) //if its the first set turn
				return 2;
			if (!second1.hasNext())//if its the second set turn
				return 1;
			if (flag==1) {
				flag=2;
				return 1;
			}
			flag=1;
			return 2;
		}
		@Override
		public boolean hasNext() { //method from Iterable that conditions the sets iterations when to stop
			return first1.hasNext() || second1.hasNext();
		}
		@Override
		public E next() { //method to go over elements
			int temp=iter();
			if(temp==1)  //the first element goes for the first set
				return first1.next();
			if(temp==2) //the second element goes for the second set
				return second1.next();
			return null;
		}
	}
	@Override
	public Iterator<E> iterator() { //works as an interface for Iterable methods
		return new myIterator();
	}
}
