/*
 * Copyright 2021 Marc Liberatore.
 */

package list.exercises;

import java.util.ArrayList;
import java.util.List;


public class ExtendedArrayList<E> extends ArrayList<E> {
	
	/**
	 * Counts the number of elements in this list that are equal to e.
	 * 
	 * Uses .equals to check for equality. 
	 * 
	 * @param e the element to count
	 * @return the number of elements equal to e
	 */
	public int count(E e) {
		int count = 0;
		for (E element : this) {
			if (element.equals(e)) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Rotates the list right n places.
	 * 
	 * Rotating a list right means moving the last item to the front of the list:
	 * 
	 * 1, 2, 3, 4, 5 when rotated right once becomes 5, 1, 2, 3, 4
	 * 
	 * A list is rotated right two places as follows:
	 * 
	 * 4, 5, 1, 2, 3
	 * 
	 * and so on.
	 * 
	 * @param n the distance to rotate the list right
	 */
	public void rotateRight(int n) {
		int size = this.size();
		if (size == 0) {
			return;
		}
		
		n = n % size; // Handle cases where n is greater than the size of the list
		
		for (int i = 0; i < n; i++) {
			E lastElement = this.remove(size - 1);
			this.add(0, lastElement);
		}
		
	}	
	
	/**
	 * Intersperses e between each existing element of the list.
	 * 
	 * For example, given the list: "hey", "ho", "hi", if we intersperse "yo" we get:
	 * "hey", "yo", "ho", "yo", "hi"
	 * 
	 * @param e the element to intersperse
	 */
	public void intersperse(E e) {
		int size = this.size();
		for (int i = 1; i < size; i += 2) {
			this.add(i, e);
			size++; // Update the size as we're adding elements
		}
	}
	
	/**
	 * Returns a copy of this list in reverse order.
	 * 
	 * This list is unmodified by this operation.
	 * 
	 * @return a reversed copy of the list
	 */
	public List<E> reversed() {
		List<E> reversedList = new ArrayList<>();
		for (int i = this.size() - 1; i >= 0; i--) {
			reversedList.add(this.get(i));
		}
		return reversedList;
	}
	}

