import java.util.Comparator;

/**
 * The MyLinked List creates a list which will store information necessary for
 * the Contacts class. Implements the List211 interface and uses all of its
 * methods.
 * 
 * @author ZacharyMartin
 * @param <E> which is a generic type.
 */
public class MyLinkedList<E> implements List211<E> {
	/**
	 * This DLinkednode class creates a double linked node which will record its
	 * index and then point to the next node, and to the previous node, if there
	 * is none, it will return null.
	 * 
	 * @author ZacharyMartin
	 * @param <E> which is a generic type.
	 */
	private static class DLinkedNode<E> {
		private E data;
		private DLinkedNode<E> next;
		private DLinkedNode<E> prev;

		/**
		 * @param dataItem which the data will be set to. points to null after.
		 */
		private DLinkedNode(E dataItem) {
			this.data = dataItem;
			this.next = null;
			this.prev = null;
		}

		/**
		 * Creates the double linked Node.
		 * 
		 * @param dataItem which stores the data.
		 * @param nextRef which points to the next Node.
		 */
		private DLinkedNode(E dataItem, DLinkedNode<E> nextRef) {
			this.data = dataItem;
			this.next = nextRef;
		}
	}

	private DLinkedNode<E> head;
	private DLinkedNode<E> tail;
	private int size;

	/**
	 * Sorts through the list, making sure the names in the contact list are in
	 * alphabetical order.
	 * 
	 * @param compare which is used in ContactComparator.
	 */
	public void insertionSort(Comparator<? super E> compare) {
		// StringBuilder sb = new StringBuilder();
		for (int i = 1; i < size; i++) {
			E temp = getNode(i).data;
			int j = i;
			while (j != 0 && compare.compare(temp, getNode(j - 1).data) < 0) {
				getNode(j).data = getNode(j - 1).data;
				j--;
			}
			getNode(j).data = temp;
		}
		// for (int i = 0; i < size; i++) {
		// sb.append(data[i]);
		// sb.append(",");
		// System.out.println(sb.toString());
		// }
	}

	/**
	 * Sorts through the list, making sure the names in the contact list are in
	 * alphabetical order.
	 * 
	 * @param compare which is used in ContactComparator.
	 */
	public void bubbleSort(Comparator<? super E> compare) {
		// StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size - 1; j++) {
				if (compare.compare(getNode(j).data, getNode(j + 1).data) > 0) {
					E temp = getNode(j).data;
					getNode(j).data = getNode(j + 1).data;
					getNode(j + 1).data = temp;
				}
			}
		}
		// for (int i = 0; i < size; i++) {
		// sb.append(data[i]);
		// sb.append(",");
		// System.out.println(sb.toString());
		// }
	}

	/**
	 * Sorts through the list, making sure the names in the contact list are in
	 * alphabetical order.
	 * 
	 * @param compare which is used in ContactComparator.
	 */
	public void selectionSort(Comparator<? super E> compare) {
		StringBuilder sb = new StringBuilder();
		long selectionStart = System.nanoTime();
		for (int i = 0; i < size - 1; i++) {
			int posMin = i;
			for (int j = i + 1; j < size; j++) {
				if (compare.compare(getNode(j).data, getNode(posMin).data) < 0) {
					posMin = j;
				}
			}
			E temp = getNode(i).data;
			getNode(i).data = getNode(posMin).data;
			getNode(posMin).data = temp;
		}
		// for (int i = 0; i < size; i++) {
		// sb.append(data[i]);
		// sb.append(",");
		// System.out.println(sb.toString());
		// }
	}

	/**
	 * The default constructor of the MyLinkedList class.
	 */
	public MyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	/**
	 * This is a helper method which adds a node first in the list.
	 * 
	 * @param entry which is stored in the node head.
	 */
	private void addFirst(E entry) {
		head = new DLinkedNode(entry, head);
		size++;
	}

	/**
	 * adds a node after the given node.
	 * 
	 * @param node where the node will be added after.
	 * @param entry stores the data.
	 */
	private void addAfter(DLinkedNode<E> node, E entry) {
		node.next = new DLinkedNode(entry, node.next);
		size++;
	}

	/**
	 * This method gets the specific Node at the given index.
	 * 
	 * @param index where the node will be examined at.
	 * @return node that is given.
	 */
	private DLinkedNode<E> getNode(int index) {
		DLinkedNode<E> node = head;
		for (int i = 0; i < index && node != null; i++) {
			node = node.next;
		}
		return node;

	}

	/**
	 * Helper Method.
	 * 
	 * @param node that indicates the node to be removed after.
	 * @return either temp data or null depending on the conditions.
	 */
	private E removeAfter(DLinkedNode<E> node) {
		DLinkedNode<E> temp = node.next;
		if (temp != null) {
			node.next = temp.next;
			size--;
			return temp.data;
		} else {
			return null;
		}
	}

	/**
	 * Helper Method.
	 * 
	 * @return either temp data or null depending on the conditions.
	 */
	private E removeFirst() {
		DLinkedNode<E> temp = head;
		if (head != null) {
			head = head.next;
			size--;
			return temp.data;
		} else {
			return null;
		}
	}

	@Override
	/**
	 * Adds the entry at the end of the list.
	 * 
	 * @param entry that the node will store
	 * @return true when the method is complete.
	 */
	public boolean add(E entry) {
		add(size, entry);
		return true;
	}

	@Override
	/**
	 * Adds the entry at any index given, as long as its inside the bounds.
	 * 
	 * @param index where the user wants to add the node.
	 * @entry which is the data the Node will store.
	 */
	public void add(int index, E entry) {
		if (index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		if (index == 0) {
			addFirst(entry);
		} else {
			DLinkedNode<E> node = getNode(index - 1);
			addAfter(node, entry);
		}
	}

	@Override
	/**
	 * Gets a node at a specific index and returns the data.
	 * 
	 * @param index which specifies which node to access. return data of the
	 *        node gotten by the helper method getNode().
	 * @return getNode(index).data.
	 */
	public E get(int index) {
		getNode(index);
		return getNode(index).data;
	}

	/**
	 * Removes the given node.
	 * 
	 * @param node which will be removed
	 */
	public E remove(DLinkedNode<E> node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
		size--;
		return node.data;
	}

	@Override
	/**
	 * Sets the index and element of a node.
	 * 
	 * @param index where the node will be set.
	 * @param element what the node will contain.
	 * @return old value the node is replacing.
	 */
	public E set(int index, E element) {
		if (index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		DLinkedNode<E> node = getNode(index);
		E old = node.data;
		node.data = element;
		return old;
	}

	@Override
	/**
	 * returns size.
	 */
	public int size() {
		return size;
	}

	/**
	 * @return sb.toString()
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(getNode(i).data);
			sb.append(",");
			sb.append('\n');
		}
		return sb.toString();
	}

	@Override
	/**
	 * removes a node at the given index.
	 * 
	 * @param index where the node will be removed from.
	 */
	public E remove(int index) {
		if (index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		if (index == 0) {
			return removeFirst();
		} else {
			DLinkedNode<E> node = getNode(index - 1);
			return removeAfter(node);
		}
	}
}
