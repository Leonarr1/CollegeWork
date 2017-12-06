
import java.util.Iterator;
import java.util.Stack;

/**
 * Class FacebookCircles: calculates the statistics about the friendship circles in facebook data.
 *
 * @author Robert Leonard leonarr1@tcd.ie
 *
 * @version 01/12/15 02:03:28
 */




public class FacebookCircles 
{
	private final int V; 			// number of vertices (users)
	private int E; 					// number of edges (connections)
	private Bag <Integer>[] adj;	// adjacency lists 
	public CC connectedComponents;	// all components (circles)

	/**
	 * Constructor
	 * @param numberOfFacebookUsers : the number of users in the sample data.
	 * Each user will be represented with an integer id from 0 to numberOfFacebookUsers-1.
	 */
	public FacebookCircles(int numberOfFacebookUsers) 
	{
		this.V = numberOfFacebookUsers;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];

		for(int i = 0; i < V; i++)
		{
			adj[i] = new Bag<Integer>();
		}
	}

	public class Bag<Item> implements Iterable<Item>
	{
		private Node first;
		private int size = 1;

		private class Node
		{
			Item item;
			Node next;
			
		}

		public void add(Item item)
		{
			Node oldfirst = first;
			first = new Node();
			first.item = item;
			first.next = oldfirst;
			size++;
		}
		

		public Iterator<Item> iterator() 
		{
			
			return new ListIterator();
		}

		
		private class ListIterator implements Iterator<Item>
		{
			private Node current = first;

			public boolean hasNext() {
				return current != null;
			}

			public Item next() {
				Item item = current.item;
				current = current.next;
				return item;
			}
			
			public void remove(){
			}
			
			
		}
	}
	
	/*
	 * Connected Components
	 */
	public class CC
	{
		private boolean[] marked;
		private int[] id;
		private int[] size;
		private int count;
		private int[] edgeTo;

		public CC(FacebookCircles fc)
		{
			marked = new boolean[fc.getV()];
			id = new int[fc.getV()];
			size = new int[fc.getV()];
			edgeTo = new int[fc.getV()];
			for(int i = 0; i < fc.getV(); i++)
				if(!marked[i])
				{
					dfsIter(fc, i);
					count++;
				}
		}

		// Recursive function runs the risk off stack overflow
//		private void dfs(FacebookCircles fc, int v)
//		{
//			marked[v] = true;
//			id[v] = count;
//			size[count]++;
//			for(int w : fc.adj(v))
//				if(!marked[w])
//					dfs(fc, w);
//		}
//		
		private void dfsIter(FacebookCircles G, int s) {
		    Stack<Integer> stack = new Stack<Integer>();
		    stack.push(s);
		    while (!stack.isEmpty()) {
		        int v = stack.pop();
		        if (!marked[v]) {
		            marked[v] = true;
		            id[v] = count;
					size[count]++;
		            for (int w : G.adj(v)) {
		                if (!marked[w]) {
		                    edgeTo[w] = v;
		                    stack.push(w);
		                 }
		            }
		        }
		    }
		}
	
	}

	/**
	 * creates a friendship connection between two users, represented by their corresponding integer ids.
	 * @param user1 : int id of first user
	 * @param user2 : int id of second  user
	 */
	public void friends( int user1, int user2 ) 
	{
		adj[user1].add(user2);
		adj[user2].add(user1);
		E++;
	}


	public int getV() {
		return V;
	}

	/**
	 * @return the number of friend circles in the data already loaded.
	 */
	public int numberOfCircles() 
	{
		connectedComponents = new CC(this);
		return connectedComponents.count;
	}

	/**
	 * @return the size of the largest circle in the data already loaded.
	 */
	public int sizeOfLargestCircle() 
	{
		//CC cc = new CC(this);
		int largestCircle = 1;
		System.out.println("\nLargestCircle");
		for(int i = 0; i < connectedComponents.count; i++)
		{
			if( connectedComponents.size[i] > largestCircle)
				largestCircle = connectedComponents.size[i];
		}
		return largestCircle;
	}

	/**
	 * @return the size of the median circle in the data already loaded.
	 */
	public int sizeOfAverageCircle() {
		
		//CC cc = new CC(this);
		int count = connectedComponents.count; 
		int sizeTotal = 0;
		for(int v = 0; v < count; v++)
		{
			sizeTotal += connectedComponents.size[v];
		}
		return sizeTotal/count;
	}

	/**
	 * @return the size of the smallest circle in the data already loaded.
	 */
	public int sizeOfSmallestCircle() 
	{
		//CC cc = new CC(this);
		int smallestCircle=connectedComponents.size[0];
		for(int i = 0; i < connectedComponents.count; i++)
		{
			
			if( connectedComponents.size[i] < smallestCircle)
				smallestCircle = connectedComponents.size[i];
		}
		return smallestCircle;
	}

	public Iterable<Integer> adj(int v)
	{
		return adj[v];
	}
	
	
}
