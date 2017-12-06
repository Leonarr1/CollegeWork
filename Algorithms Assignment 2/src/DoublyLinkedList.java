import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  Robert Leonard
 *  @version 13/10/16 18:15
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

	/**
	 * private class DLLNode: implements a *generic* Doubly Linked List node.
	 */
	private class DLLNode
	{
		public final T data; // this field should never be updated. It gets its
		// value once from the constructor DLLNode.
		public DLLNode next;
		public DLLNode prev;

		/**
		 * Constructor
		 * @param theData : data of type T, to be stored in the node
		 * @param prevNode : the previous Node in the Doubly Linked List
		 * @param nextNode : the next Node in the Doubly Linked List
		 * @return DLLNode
		 */
		public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
		{
			data = theData;
			prev = prevNode;
			next = nextNode;
		}
	}

	// Fields head and tail point to the first and last nodes of the list.
	private DLLNode head, tail;
	private int size;

	/**
	 * Constructor
	 * @return DoublyLinkedList
	 */
	public DoublyLinkedList() 
	{
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Tests if the doubly linked list is empty
	 * @return true if list is empty, and false otherwise
	 *
	 * Worst-case asymptotic runtime cost: Theta(1).
	 *
	 * Justification:
	 * Because this method will only run once and perform 1 comparison regardless of list size we don't need to take that into account so the 
	 * method will have a worst-case asymptotic runtime cost of Theta(1)
	 */
	public boolean isEmpty()
	{
		return (size==0);
	}

	/**
	 * Inserts an element in the doubly linked list
	 * @param pos : The integer location at which the new data should be
	 *      inserted in the list. We assume that the first position in the list
	 *      is 0 (zero). If pos is less than 0 then add to the head of the list.
	 *      If pos is greater or equal to the size of the list then add the
	 *      element at the end of the list.
	 * @param data : The new data of class T that needs to be added to the list
	 * @return none
	 *
	 *	Worst-case asymptotic runtime cost:   Theta(n)
	 *
	 * 	Justification:
	 *  As with stated in the toString method justification we assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
	 *  Thus, every one iteration of the while-loop will have cost Theta(1).
	 *  Suppose the doubly-linked list has 'n' elements.
	 *  The while-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
	 */
	public void insertBefore( int pos, T data ) 
	{
		//DLLNode temp = new DLLNode(data, null, null);
		//add DLLNode to the head of the linked list
		if(pos<=0)	  
		{
			DLLNode temp = new DLLNode(data, null, head);
	        if(head != null ) 
	        {
	        	head.prev = temp;
	        }
	        head = temp;
	        if(tail == null) 
	        { 
	        	tail = temp;
	        }
	        size++;		
		}
		else if(pos>this.size-1)
		{
			DLLNode temp = new DLLNode(data, tail, null);
			if(tail != null) 
			{
				tail.next = temp;
			}
			tail = temp;
			if(head == null) 
			{ 
				head = temp;
			}
			size++;	
		}
		else
		{
			int nodePos = 0;
			DLLNode temp = new DLLNode(data, null, null);
			if(pos>=(this.size)/2)
			{
				nodePos = this.size - 1;
				DLLNode iter = tail;
				while(iter!=null)
				{
					if(pos==nodePos)
					{
						temp.prev = iter.prev;
						temp.next = iter;
						iter.prev.next = temp;
						iter.prev = temp;
						size++;
						return;
					}
					iter=iter.prev;
					nodePos--;
				}	
			}
			else if(pos<=(this.size)/2)
			{
				nodePos = 0;
				DLLNode iter = head;
				while(iter!=null)
				{
					if(pos==nodePos)
					{
						temp.prev = iter.prev;
						temp.next = iter;
						iter.prev.next = temp;
						iter.prev = temp;
						size++;
						return;
					}
					iter=iter.next;
					nodePos++;
				}
			}
		}
	}

	/**
	 * Returns the data stored at a particular position
	 * @param pos : the position
	 * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
	 *
	 *	Worst-case asymptotic runtime cost:   Theta(n)
	 *
	 * 	Justification:
	 *  As with stated in the toString method justification we assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
	 *  Thus, every one iteration of the while-loop will have cost Theta(1).
	 *  Suppose the doubly-linked list has 'n' elements.
	 *  The while-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
	 *
	 * Worst-case precise runtime cost: Theta(n/2)
	 *
	 * Justification:
	 * Although the runtime is still increased by increasing the input size, because the method iterates over the list, this does not mean the precise time would be Theta(n)
	 * because the implementation of the method will start at the beginning or the end of the list depending on which half of the list the desired data is in. Meaning the method 
	 * will only ever iterate over have the list Theta(n) / 2 = Theta(n/2).
	 */
	public T get(int pos) 
	{
		int nodePos = 0;
		if(pos>=(this.size)/2)
		{
			nodePos = this.size - 1;
			DLLNode iter = tail;
			while(iter!=null)
			{
				if(pos==nodePos)
				{
					
					return iter.data;
				}
				iter=iter.prev;
				nodePos--;
			}	
		}
		else if(pos<=(this.size)/2)
		{
			nodePos = 0;
			DLLNode iter = head;
			while(iter!=null)
			{
				if(pos==nodePos)
				{
					
					return iter.data;
				}
				iter=iter.next;
				nodePos++;
			}
		}
		return null;
	}

	/**
	 * Deletes the element of the list at position pos.
	 * First element in the list has position 0. If pos points outside the
	 * elements of the list then no modification happens to the list.
	 * @param pos : the position to delete in the list.
	 * @return true : on successful deletion, false : list has not been modified. 
	 *
	 *	Worst-case asymptotic runtime cost:   Theta(n)
	 *
	 * 	Justification:
	 *  As with stated in the toString method justification we assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
	 *  Thus, every one iteration of the while-loop will have cost Theta(1).
	 *  Suppose the doubly-linked list has 'n' elements.
	 *  The while-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
	 */
	public boolean deleteAt(int pos) 
	{
		int nodePos = 0;
		if(pos>=(this.size)/2)
		{
			nodePos = this.size - 1;
			DLLNode iter = tail;
			while(iter!=null)
			{
				if(pos==nodePos)
				{
					if(size==1)
					{
						head = null;
						tail = null;
					}
					else if(iter==tail)
					{
						iter.prev.next = null;
						tail = iter.prev;
					}
					else
					{
						iter.prev.next = iter.next;
						iter.next.prev = iter.prev;
					}
					size--;
					return true;
				}
				iter=iter.prev;
				nodePos--;
			}	
		}
		else if(pos<=(this.size)/2)
		{
			nodePos = 0;
			DLLNode iter = head;
			while(iter!=null)
			{
				if(pos==nodePos)
				{
					if(iter==head)
					{
						iter.next.prev = null;
						head = iter.next;
					}
					else
					{
					iter.prev.next = iter.next;
					iter.next.prev = iter.prev;
					}
					size--;
					return true;
				}
				iter=iter.next;
				nodePos++;
			}
		}
		return false;
	}

	/**
	 * Reverses the list.
	 * If the list contains "A", "B", "C", "D" before the method is called
	 * Then it should contain "D", "C", "B", "A" after it returns.
	 *
	 *	Worst-case asymptotic runtime cost:   Theta(n)
	 *
	 * 	Justification:
	 *  As with stated in the toString method justification we assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
	 *  Thus, every one iteration of the while-loop will have cost Theta(1).
	 *  Suppose the doubly-linked list has 'n' elements.
	 *  The while-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
	 */
	public void reverse()
	{
		if(this.isEmpty())
			return;
		else
		{
			DLLNode iter;
			DLLNode swap = new DLLNode(null, null, null);
			DLLNode temp = new DLLNode(null, null, null);
			swap = head;
			head = tail;
			tail = swap;
			iter = head;
			while(iter!=null)
			{
				temp.next = iter.next;
				iter.next = iter.prev;
				iter.prev = temp.next;
				iter = iter.next;	
			}
			
		}
	}


	/*----------------------- STACK */
	/**
	 * This method should behave like the usual push method of a Stack ADT.
	 * If only the push and pop methods are called the data structure should behave like a stack.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @param item : the item to push on the stack
	 *
	 * Worst-case asymptotic runtime cost: Theta(1).
	 *
	 * Justification:
	 * Because this method will only run once regardless of list size we don't need to take that into account so the 
	 * method will have a worst-case asymptotic runtime cost of Theta(1)
	 */
	public void push(T item) 
	{
		if(this.isEmpty())
		{
			DLLNode newItem = new DLLNode(item, null, null);
			head = newItem;
			tail = newItem;
		}
		else
		{
		DLLNode newItem = new DLLNode(item, null, head);
		head.prev = newItem;
		head = newItem;
		}
		size++;
	}

	/**
	 * This method should behave like the usual pop method of a Stack ADT.
	 * If only the push and pop methods are called the data structure should behave like a stack.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @return the last item inserted with a push; or null when the list is empty.
	 *
	 * Worst-case asymptotic runtime cost: Theta(1).
	 *
	 * Justification:
	 * Because this method will only run once regardless of list size we don't need to take that into account so the 
	 * method will have a worst-case asymptotic runtime cost of Theta(1)
	 */
	public T pop() 
	{
		if(this.isEmpty())
			return null;
		else if(size == 1)
		{
			DLLNode popItem = head;
			head=null;
			tail=null;
			size--;
			return popItem.data;
		}
		else
		{
		DLLNode popItem = head;
		head.next.prev = null;
		head = head.next;
		size--;
		return popItem.data;
		}
	}

	/*----------------------- QUEUE */

	/**
	 * This method should behave like the usual enqueue method of a Queue ADT.
	 * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @param item : the item to be enqueued to the stack
	 *
	 * Worst-case asymptotic runtime cost: Theta(1).
	 *
	 * Justification:
	 * Because this method will only run once regardless of list size we don't need to take that into account so the 
	 * method will have a worst-case asymptotic runtime cost of Theta(1)
	 */
	public void enqueue(T item) 
	{
		if(!this.isEmpty())
		{
		DLLNode newItem = new DLLNode(item, null, head);
		head.prev = newItem;
		head = newItem;
		}
		else
		{
			DLLNode newItem = new DLLNode(item, null, null);
			head=newItem;
			tail=newItem;
		}
		size++;
	}

	/**
	 * This method should behave like the usual dequeue method of a Queue ADT.
	 * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @return the earliest item inserted with a push; or null when the list is empty.
	 *
	 * Worst-case asymptotic runtime cost: Theta(1).
	 *
	 * Justification:
	 * Because this method will only run once regardless of list size we don't need to take that into account so the 
	 * method will have a worst-case asymptotic runtime cost of Theta(1)
	 */
	public T dequeue() 
	{
		
		if(this.isEmpty())
			return null;
		else if(size==1)
		{
			DLLNode dequeuItem = tail;
			tail = null;
			head = null;
			size--;
			return dequeuItem.data;
		}
		else
		{
			DLLNode dequeuItem = tail;
			tail.prev.next = null;
			tail = tail.prev;
			size--;
			return dequeuItem.data;
		}
				
	}


	/**
	 * @return a string with the elements of the list as a comma-separated
	 * list, from beginning to end
	 *
	 * Worst-case asymptotic runtime cost:   Theta(n)
	 *
	 * Justification:
	 *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
	 *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
	 *  Thus, every one iteration of the for-loop will have cost Theta(1).
	 *  Suppose the doubly-linked list has 'n' elements.
	 *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
	 */
	public String toString() 
	{
		StringBuilder s = new StringBuilder();
		boolean isFirst = true; 

		// iterate over the list, starting from the head
		for (DLLNode iter = head; iter != null; iter = iter.next)
		{
			if (!isFirst)
			{
				s.append(",");
			} else {
				isFirst = false;
			}
			s.append(iter.data.toString());
		}

		return s.toString();
	}


}


