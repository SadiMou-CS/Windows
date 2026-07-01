//TODO: Linked list implementation (optional)
//      JavaDocs (not optional)
//      Static sorting methods (not optional)

import java.util.Comparator; //you'll need this

/**
 * Implementing public class in doubly linked list linked both direction.
 * @param <T> is generic in thw linked list node.
 */
class ThreeTenLinkedList<T> {
	//You may, but are not required to, implement some or
	//all of this generic linked list class to help you with
	//this project. If you do, you MUST use the provided
	//Node class for this linked list class. This means
	//that it must be a doubly linked list (and links in
	//both directions MUST work).
	
	//Alternatively, you may implement this project using
	//only the Node class itself (i.e. use "bare nodes"
	//in the classes that require linked lists).
	
	//Either way, you MUST do all your own work. Any other
	//implementations you have done in the past, anything
	//from the book, etc. should not be in front of you,
	//and you certainly shouldn't copy and paste anything
	//from any other source.
	
	//This is the only class where you are allowed to
	//implement public methods.
	
	//In "Part 5" of the project, you will also be implementing
	//the following static methods:

	/**
	 * Determine if the provided list is sorted based on the comparator in ascending order.
	 * @param pairs head and tail contains two nulls.
	 * @param comp sorted based on the comparator in ascending order.
	 * @param <X> the type of element in the linked list.
	 * @return true an empty list is sorted
	 */
	static <X> boolean isSorted(NodePair<X> pairs, Comparator<X> comp) {
		//Determine if the provided list is sorted based on the comparator
		//in ascending order.
		
		//For an empty linked list (e.g. the head-tail pair contains two nulls)
		//return true (an empty list is sorted).
		
		//For a null comparator or null pairs, throw an IllegalArgumentException.
		
		//O(n)
		
		//< YOUR_CODE_HERE >
		if (pairs ==null || comp == null)
		{
			throw new IllegalArgumentException ("Comparator or pairs cannot be null.");
		}
		if (pairs.head == null || pairs.head.next == null)
		{
			return true;
		}
		Node<X> current = pairs.head;
		while (current.next != null) {
			if (comp.compare(current.value, current.next.value) > 0) {
				return false;
			}
			current = current.next;
		}

		return true; // List is sorted here
	}

	/**
	 * sorts doubly linked list using comparator.
	 * the list is sorted by insertion or selection sort.
	 * @param pairs A {@link NodePair}  object head and tail
	 * @param comp A {@link NodePair} used comparator element from the doubly list.
	 * @param <X> is the type of element in the linked list.
	 * @return A {@link NodePair} head and tail from sortd list.
	 */
	static <X> NodePair<X> sort(NodePair<X> pairs, Comparator<X> comp) {
		
		//Using the comparator, sort the linked list in ascending order
		//("front" = "smaller", "back" = "bigger"). It is recommended that
		//you sort by moving *values* around rather than moving nodes.
		//Two simple sorting algorithms which will work well here (and
		//meet the big-O requirements if implemented correctly) are the
		//insertion sort (see textbook Ch8.3) and the selection sort.

		//Insertion sort quick summary: Go to each element in the linked list,
		//shift it "left" into the correct position.
		//Example: 1,3,0,2
		// 1 is at the start of the list, leave it alone
		// 3 is bigger than 1, leave it alone
		// 0 is smaller than 3, move it left: 1,0,3,2
		// 0 is smaller than 1, move it to the left: 0,1,3,2
		// 0 is at the start of the list, stop moving it left
		// 2 is smaller than 3, move it to the left: 0,1,2,3
		// 2 is bigger than 1, stop moving it to the left

		//Selection sort quick summary: Go to each index in the linked list,
		//find the smallest thing from that index and to the "right",
		//and swap it into that index.
		//Example: 1,3,0,2
		// index 0: the smallest thing from index 0 to the end is 0, swap it into the right place: 0,3,1,2
		// index 1: the smallest thing from index 1 to the end is 1, swap it into the right place: 0,1,3,2
		// index 2: the smallest thing from index 2 to the end is 2, swap it into the right place: 0,1,2,3
		// index 3: there is only one item from index 3 to the end, so this is in the right places
		
		//Regardless of the method you choose, your sort should be a stable sort.
		//This means that if there are two equal values, they do not change their
		//order relative to each other.
		//Example: 1, 2, 1
		//The first "1" (which I'll call "1a") should be sorted before
		//the second "1" (1b), so that the output is "1a, 1b, 2" and
		//never "1b, 1a, 2". The easiest way to test this is to put two
		//equal items in the list, sort, and confirm using == that the
		//correct object is in the correct place.
		
		//For an empty linked list (e.g. the head-tail pair contains two nulls)
		//return the original pairs back to the user.
		
		//For a null comparator or null pairs, throw an IllegalArgumentException.
		
		//O(n^2)
		
		//< YOUR_CODE_HERE >

		if (pairs == null || comp == null) {
			throw new IllegalArgumentException("Pairs or comparator cannot be null");
		}

		if (pairs.head == null || pairs.head.next == null) {
			return pairs;
		}

		Node<X> sortedHead = null;
		Node<X> current = pairs.head;

		while (current != null) {
			Node<X> next = current.next; // Save the next node before modifying links
			sortedHead = insertSorted(sortedHead, current, comp);
			current = next;
		}

		Node<X> newTail = sortedHead;
		while (newTail.next != null) {
			newTail = newTail.next;
		}

		return new NodePair<>(sortedHead, newTail);
	}
	/**
	 * Inserts a new node into a sorted linked list at the appropriate position
	 * based on the comparator.
	 * insert new node on sorted linkdlist.
	 * comparator and inserts the new node in the appropriate position.
	 * @param head can be null if the list is empty.
	 * @param newNode newnode will be listed to the list.
	 * @param comp comp the comparator used to determine the ordering of nodes
	 *  @param <X> the type of elements contained in the linked list nodes
	 * @return head and new node.
	 */
	private static <X> Node<X> insertSorted(Node<X> head, Node<X> newNode, Comparator<X> comp) {
		newNode.prev = null;

		if (head == null || comp.compare(newNode.value, head.value) < 0) {
			newNode.next = head;
			if (head != null) {
				head.prev = newNode;
			}
			return newNode;
		}

		Node<X> current = head;
		while (current.next != null && comp.compare(current.next.value, newNode.value) < 0) {
			current = current.next;
		}

		newNode.next = current.next;
		newNode.prev = current;
		current.next = newNode;

		if (newNode.next != null) {
			newNode.next.prev = newNode;
		}

		return head;
	}

	//Which uses the following nested class:@SuppressWarnings("checkstyle:Indentation")

	/**
	 * pair of node with a head and tail of a linked list.
	 * store head and tail together.
	 * retuens both head and tail nodes.
	 * @param <Y> the type of elements stored in the nodes.
	 */
	@SuppressWarnings("checkstyle:Indentation")
	static class NodePair<Y> {
		/**
		 * the final node head.
		 */
		public final Node<Y> head;
		/**
		 * final node of linked list tail.
		 */
		public final Node<Y> tail;
		/**
		 * This pair holds references to both the head and tail of a linked list.
		 * @param head the head node of linked list.
		 * @param tail the tail node of the linked list.
		 */
		public NodePair(Node<Y> head, Node<Y> tail) {
			this.head = head;
			this.tail = tail;
		}
	}
	/**
	 * the current value of the node of mylist.
	 * @param head the head of the node of the linkd list.
	 * @param <T> is the generic t class node
	 */
	public static <T> void mylist (Node<T> head) {
		Node<T> current = head;
		while (current != null) {
			System.out.print(current.value + " ");
			current = current.next;
		}
		System.out.println();
	}
	/**
	 * this is the main method and it creates doubly linked list.
	 * @param args the commandline argument in the implementation.
	 */
	public static void main(String[] args) {
		Node<Integer> n1 = new Node<>(4);
		Node<Integer> n2 = new Node<>(2);
		Node<Integer> n3 = new Node<>(5);
		Node<Integer> n4 = new Node<>(1);

		n1.next = n2; n2.prev = n1;
		n2.next = n3; n3.prev = n2;
		n3.next = n4; n4.prev = n3;

		NodePair<Integer> pairs = new NodePair<>(n1, n4);

		System.out.println("This is Before Sorting:");
		mylist(pairs.head);

		NodePair<Integer> sortedPairs = sort(pairs, Integer::compare);

		System.out.println("This is After Sorting:");
		mylist(sortedPairs.head);
	}

	//You may also use the above nested class elsewhere in your
	//project if you'd find that helpful.
}