//TODO: Complete java docs and code in missing spots.
//Missing spots are marked by < YOUR_CODE_HERE >.
//Do NOT edit any other parts of the code.

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Comparator;

/**
 *  A list of squares within a single window.
 *
 *  <p>Adaptation of Nifty Assignment (<a href="http://nifty.stanford.edu/">...</a>) by
 *  Mike Clancy in 2001. Original code by Mike Clancy. Updated by
 *  K. Raven Russell.</p>
 */
public class SquareList {
	//You'll need some instance variables probably...
	//< YOUR_CODE_HERE >
	/**
	 * this is first node of the linked list.
	 */
	private Node<Square> head;
	/**
	 * this is tail the last node of the linked list.
	 */
	private Node<Square> tail;
	/**
	 * this is here the number of elem in the linked list.
	 */
	private int size;
	/**
	 * Initialize an empty list of squares.
	 */
	public SquareList() {
		//Any initialization code you need.

		//O(1)

		//< YOUR_CODE_HERE >
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * This is generic linked list class.
	 *
	 * @return Returns the head of the list of squares.
	 */
	Node<Square> getHead() {
		return head; //dummy return, replace this!
	}

	/**
	 * this return tail of squares.
	 *
	 * @return null if head is empty
	 */
	Node<Square> getTail() {
		//Returns the tail of the list of squares.

		//O(1)

		//We will use this method to test your
		//linked list implementaiton of this
		//class, so whether you are using
		//the generic linked list class or bare
		//nodes, you must still be able to return
		//the appropriate tail of the list.

		//< YOUR_CODE_HERE >

		//checks if head is empty,then return null
		if (head == null) {
			return null;
		}
        return tail; //dummy return, replace this!
	}
	/**
	 * Gets the number of squares in the list.
	 * @return Gets the number of squares in the list.
	 */
	public int numSquares() {
		//Gets the number of squares in the list.

		//O(1)

		//< YOUR_CODE_HERE >


		return size;
	}
	/**
	 * Add a square to the list. Newly added squares
	 * should be at the back end of the list.
	 * throw IllegalArgumentException for invalid squares.
	 * @param sq the square that will be added.
	 *
	 */
	public void add(Square sq) {
		//Add a square to the list. Newly added squares
		//should be at the back end of the list.

		//O(1)

		//throw IllegalArgumentException for invalid squares
		//Hint: What could make a square invalid? There are
		//no properties of squares that make them invalid, 
		//but there could be something wrong with the Square
		//object given to you.

		//< YOUR_CODE_HERE >
		if (sq == null) {
			throw new IllegalArgumentException("Invalid input. Square cannot be null");
		}
		Node<Square> newNode = new Node<>(sq);
		if (head == null) {
			head = newNode;
        } else {
			tail.next = newNode;
        }
        tail = newNode;
        size++;
	}

	/**
	 * Deletes all squares from the list that contain the.
	 * @param x position x Returns true if any squares get.
	 * @param y position y Returns true if any squares get.
	 * @return true if any squares were deleted.
	 */
	public boolean handleClick(int x, int y) {
		//Deletes all squares from the list that contain the 
		//position (x,y). Returns true if any squares get
		//deleted and returns false otherwise.

		//Returns true if any squares were deleted.

		//O(n) where n is the size of the list of squares

		//< YOUR_CODE_HERE >
		boolean deleted = false;
		Node<Square> current = head;
		Node<Square> previous = null;

		while (current != null) {
			if (current.value.contains(x, y)) {
				if (previous == null) {
					head = current.next;
				} else {
					previous.next = current.next;
				}
				if (current.next == null) {
					tail = previous;
				}

				deleted = true;
			}
			previous = current;
			current = current.next;
		}

		return deleted;
	}

	/**
	 * Gets an iterator for the list of squares.
	 * Squares are returned in the order added.
	 *
	 * @return the iterator requested.
	 */
	public Iterator<Square> elements() {
		//Note that this method uses your linked list!
		//so if the iterator doesn't work, that's on you...

		return new Iterator<>() {
			/**
			 *  The current node pointed to by the
			 *  iterator (containing the next value
			 *  to be returned).
			 */
			private Node<Square> current = getHead();

			/**
			 * {@inheritDoc}
			 */
			@Override
			public Square next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				Square ret = current.value;
				current = current.next;
				return ret;
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public boolean hasNext() {
				return (current != null);
			}
		};
	}

	/**
	 * Sorts the squares in the window by their creation time.
	 *
	 */
	public void sortCreation() {
		//Sorts the squares in the window by their creation time
		//(lower ids were created first). This should use your
		//ThreeTenLinkedList.sort() method you write in Part 5,
		//so don't do this until you have (a) read part 5,
		//(b) looked at the example in WindowStack, and (c) are 
		//sure you understand comparators.

		//O(n^2)

		//< YOUR_CODE_HERE >
		Comparator<Square> creationComparator = Comparator.comparingInt(Square::id);
		if (head == null || head.next == null) {
			return;
		}
		Node<Square> current = head;
		while (current != null) {
			Node<Square> nextNode = current.next;
			while (nextNode != null) {
				if (creationComparator.compare(current.value, nextNode.value) > 0) {
					// Swap values here
					Square temp = current.value;
					current.value = nextNode.value;
					nextNode.value = temp;
				}
				nextNode = nextNode.next;
			}
			current = current.next;
		}
	}

	/**
	 * Sorts the squares in the window by their location.
	 */

	public void sortLoc() {
		//Sorts the squares in the window by their location
		//in the window. Same rules as sorting the windows
		//in WindowStack. This should use your
		//ThreeTenLinkedList.sort() method you write in Part 5,
		//so don't do this until you have (a) read part 5,
		//(b) looked at the example in WindowStack, and (c) are 
		//sure you understand comparators

		//O(n^2)

		//< YOUR_CODE_HERE >
		Comparator<Square> locationComparator = (s1, s2) -> {
            // First compare by x-coordinate, if equal, compare by y-coordinate
            if (s1.getUpperLeftX() != s2.getUpperLeftX()) {
                return Integer.compare(s1.getUpperLeftX(), s2.getUpperLeftX());
            } else {
                return Integer.compare(s1.getUpperLeftY(), s2.getUpperLeftY());
            }
        };
		if (head == null || head.next == null) {
			return;
		}


		Node<Square> current = head;
		while (current != null) {
			Node<Square> nextNode = current.next;
			while (nextNode != null) {
				if (locationComparator.compare(current.value, nextNode.value) > 0) {
					// Swap values
					Square temp = current.value;
					current.value = nextNode.value;
					nextNode.value = temp;
				}
				nextNode = nextNode.next;
			}
			current = current.next;
		}
	}
}
