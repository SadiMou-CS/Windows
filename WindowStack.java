//TODO: Complete java docs and code in missing spots.
//Missing spots are marked by < YOUR_CODE_HERE >.
//Do NOT edit any other parts of the code.

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Comparator;

/**
 *  A stack of windows within the window.
 *
 *  <p>Adaptation of Nifty Assignment (<a href="http://nifty.stanford.edu/">...</a>) by
 *  Mike Clancy in 2001. Original code by Mike Clancy. Updated by
 *  K. Raven Russell.</p>
 */
public class WindowStack {
	//You'll need some instance variables probably...
	//< YOUR_CODE_HERE >
	/**
	 * a top stack of window.
	 */
	private Node<Window> topStack;
	/**
	 * A stack of windows within the window stack.
	 */
	public WindowStack() {
		//Any initialization code you need.
		
		//O(1)
		
		//< YOUR_CODE_HERE >
		topStack = null;
	}

	/**
	 * this get top of the stack of window.
	 * @return Returns the head (top) of the stack of windows may return null if appropriate.
	 */
	public Node<Window> getTop() {
		return topStack;
	}
	/**
	 * linked list implementation the generic linked list class.
	 * @return Returns the tail (bottom) of the stack of windows.
	 */
	public Node<Window> getBottom() {
		if(topStack == null)
		{
			return null;
		}
		Node<Window> current = topStack;
		while (current.next != null) {
			current = current.next;
		}
		return current; //dummy return, replace this!
	}

	/**
	 * Gets the number of windows in the stack.
	 * @return return number of stacks.
	 */
	public int numWindows() {
		//Gets the number of windows in the stack.
		
		//O(1)
		int numStack = 0;
		Node<Window> current = topStack;

		while (current != null) {
			numStack++;  // Count each node (window)
			current = current.next;
		}
		return numStack;
	}

	/**
	 * Add a window at the top of the stack.
	 * @param r the element of the linked list.
	 */
	public void add(Window r) {
		//Add a window at the top of the stack.
		
		//O(1)
		
		//throw IllegalArgumentException for invalid windows
		
		//Note: the "top" of the stack should
		//be the head of your linked list. And
		//remember that the top of the window 
		//stack should be the selected window.
		
		//< YOUR_CODE_HERE >
		if (r==null)
		{
			throw new IllegalArgumentException("Invalid Windows.");
		}
		Node<Window> newNode = new Node<>(r);
		newNode.next= (topStack);
		if (topStack != null) {
			topStack.prev = newNode;
		}
		topStack = newNode;
		r.setSelected(true);
	}
	/**
	 * handle The mouse clicking  at position (x,y).
	 * For a left-click,stack or pass the "click action" onto the.
	 * window at the top of the stack. Right clicks remove windows.
	 * @param x x The x-coordinate of the mouse click.
	 * @param y y The y-coordinate of the mouse click.
	 * @param leftClick Left clicks move windows to the top of the.
	 * @return Returns true if the click was handled, otherwise false.
	 */
	
	public boolean handleClick (int x, int y, boolean leftClick) {
		//The mouse has been clicked at position (x,y).

		//SUMMARY of Click Actions (more detail below):
		//Left clicks move windows to the top of the
		//stack or pass the "click action" onto the
		//window at the top of the stack. Right clicks
		//remove windows.

		//Returns true if the click was handled, false
		//if no window was found.

		//O(n) where n is the number of windows in the stack


		//Details:

		//Find the top-most window on the stack (if any)
		//that contains the given coordinate.


		//For a left click:

		//If the window is not at the top of the stack,
		//move it to the top of the stack without
		//disturbing the order of the other windows.
		//Mark this window as the "selected" window (and
		//ensure the previous selected window is no longer
		//selected).

		//If the window is at the top of the stack already,
		//ask the window to handle a click-event (using the
		//Window's handleClick() method).

		//If none of the windows on the stack were clicked
		//on, just return.


		//For a right click:

		//Remove the window clicked on from the stack
		//completely (even if it isn't on the top of
		//the stack!). The window that is on the top
		//of the stack, after this removal, should be 
		//the selected window.


		//Hint #1: This would be a great time to use helper
		//methods! If you just write one giant method...
		//it'll be much harder to debug...

		//Hint #2: Make sure to use the methods you wrote
		//in the Window class. Don't write those again!


		//< YOUR_CODE_HERE >

		Node<Window> current = topStack;
		while (current != null) {
			Window window = current.value; // Get the window at the current node

			if (window.contains(x, y)) {  // Check if the window contains the clicked point
				if (leftClick) {
					if (current != topStack) {
						// If the window isn't at the top of the stack, move it to the top
						removeWindowFromStack(window);
						add(window);  // Add it back to the top of the stack
					}
					window.handleClick(x, y);  // Delegate the click event to the window
				} else {
					// Right-click: remove the window from the stack
					removeWindowFromStack(window);

					// After removal, set the new top window as selected
					if (topStack != null) {
						topStack.value.setSelected(true);
					}
				}
				return true;  // Return true when a click is successfully handled
			}
			current = current.next;  // Move to the next node in the stack
		}

		return false;  // Return false if no window was clicked
	}

	// Helper method to remove the window from the stack

	/**
	 * Iterate through the stack to find and remove the window.
	 *
	 * @param window If the window to be removed is the top of the stack.
	 */
	private void removeWindowFromStack(Window window) {
		// Special case: if the stack is empty, do nothing
		if (topStack == null) {
			return;
		}

		// If the window to be removed is the top of the stack
		if (topStack.value.equals(window)) {
			topStack = topStack.next;  // Move the top pointer to the next node
			return;
		}

		// Iterate through the stack to find and remove the window
		Node<Window> current = topStack;
		while (current.next != null) {
			if (current.next.value.equals(window)) {
				current.next = current.next.next;
				if (current.next !=null)
				{
					current.next.prev = current;
				}// Remove the window
				return;
			}
			current = current.next;  // Move to the next node
		}
	}

	/**
	 *  Gets an iterator for the stack of windows.
	 *  Windows are returned from bottom to top.
	 *  
	 *  @return the iterator requested
	 */
	public Iterator<Window> windows() {
		//Note that this method uses your linked list!
		//so if the iterator doesn't work, that's on you...
		
		return new Iterator<>() {
			/**
			 *  The current node pointed to by the
			 *  iterator (containing the next value
			 *  to be returned).
			 */
			private Node<Window> current = getBottom();
			
			/**
			 * {@inheritDoc}
			 */
			@Override
			public Window next() {
				if(!hasNext()) {
					throw new NoSuchElementException();
				}
				Window ret = current.value;
				current = current.prev;
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
	 * Sorts the windows in the stack by their area (length x width).
	 * make the returned list the head and tail of this list
	 */
	public void sortSize() {
		//Sorts the windows in the stack by their area (length x width).
		//MOST of this is done for you, but you still need to assign
		//the returned head and tail back.
		
		//unselect the top window
		this.getTop().value.setSelected(false);
		
		//create a way to compare windows by area
		Comparator<Window> comp = (w1, w2) -> (w1.getWidth()*w1.getHeight())-(w2.getWidth()*w2.getHeight());
		
		//create a pair of nodes to pass into the sort function
		ThreeTenLinkedList.NodePair<Window> pair = new ThreeTenLinkedList.NodePair<>(getTop(), getBottom());
		
		//call the sort function with the comparator
		ThreeTenLinkedList.NodePair<Window> ret = ThreeTenLinkedList.sort(pair, comp);
		
		//make the returned list the head and tail of this list
		//this is for PART 5 of the project... don't try to do this
		//before you complete ThreeTenLinkedList.sort()!
		//< YOUR_CODE_HERE >
		topStack =ret.head;
		
		//re-select the top of the stack
		this.getTop().value.setSelected(true);
	}

	/**
	 * Sorts the windows in the stack by their upper left.
	 * corner loction. Right things (bigger-X) are on top.
	 * of left things (smaller-X). Tie-breaker: lower.
	 */
	public void sortLoc() {
		//Sorts the windows in the stack by their upper left
		//corner loction. Right things (bigger-X) are on top
		//of left things (smaller-X). Tie-breaker: lower
		//things (bigger-Y) top of  higher things (smaller-Y).

		//This should use your ThreeTenLinkedList.sort() method you
		//write in Part 5, so don't do this until you have (a) read
		//part 5, (b) looked at the example in sortSize() above, and
		//(c) are sure you understand comparators.
		
		//O(n^2)
		
		//< YOUR_CODE_HERE >
		Comparator<Window> comp = (w1, w2) -> {
            int xcompare = Integer.compare(w1.getUpperLeftX(), w2.getUpperLeftX());
            if (xcompare != 0) {
                return xcompare;
            }


            return Integer.compare(w2.getUpperLeftY(), w1.getUpperLeftY());
        };

		ThreeTenLinkedList.NodePair<Window> pair = new ThreeTenLinkedList.NodePair<>(getTop(), getBottom());
		ThreeTenLinkedList.NodePair<Window> ret = ThreeTenLinkedList.sort(pair, comp);
		topStack = ret.head;


		this.getTop().value.setSelected(true);
	}
}


