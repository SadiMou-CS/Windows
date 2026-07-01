//TODO: Add JavaDocs ONLY. No other Editing.

/**
 * Paired of nodes head and tail.
 * This class used fpr head and tail of a linked list.
 *
 * @param <T> the type of value stored in this node.
 */
class Node<T> {
	/**
	 * the value will be store in this node.
	 */
	public T value;
	/**
	 * this a reference to the next node on linked list.
	 */
	public Node<T> next;
	/**
	 * this a reference to the previous node on linked list.
	 */
	public Node<T> prev;
	/**
	 * constructs a new node with the givem values.
	 *
	 * @Param the value stored in this node.
	 */
	public Node() { }
	/**
	 *constructs a new node with the givem values.
	 *
	 * @param value  the value stored in the node.
	 */
	public Node(T value) {
		this.value = value;
	}
}