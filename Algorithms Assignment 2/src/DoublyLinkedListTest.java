import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author Robert Leonard 
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);       
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);        
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);        
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);        
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );
        testDLL.insertBefore(3,3);        
        assertEquals( "Checking insertBefore to a list containing 9 elements at position 3 - expected the element at position 3", "7,4,5,3,6,1,2,3,8,9", testDLL.toString() );
        
        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);        
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);        
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);        
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
     }
    
    @Test
    public void testReverse()
    {
    	DoublyLinkedList<String> testDLL = new DoublyLinkedList<String>();
    	testDLL.reverse();
    	assertEquals( "Checking reverse a list containing 0 elements", "", testDLL.toString() );
    	
        testDLL.insertBefore(0,"A");
        testDLL.insertBefore(1,"B");
        testDLL.insertBefore(2,"C");
        testDLL.insertBefore(3,"D");
        
        testDLL.reverse();
        //System.out.println(testDLL.toString());
        assertEquals( "Checking reverse a list containing 4 elements at position 0", "D,C,B,A", testDLL.toString() );  	
        
        testDLL.reverse();
        //System.out.println(testDLL.toString());
        assertEquals( "Checking reverse a list containing 4 elements a second time at position 0", "A,B,C,D", testDLL.toString() );  	
    }
    
    @Test
    public void testPush()
    {
    	 DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	 testDLL.push(1);
    	 assertEquals( "Checking push to a list containing 0 elements ", "1", testDLL.toString() );
    	 //System.out.println(testDLL.toString());
    	 testDLL.push(2);
    	 testDLL.push(3);
    	 testDLL.push(4);
    	 
    	 testDLL.push(5);
    	 assertEquals( "Checking push to a list containing 4 elements ", "5,4,3,2,1", testDLL.toString() );
    	// System.out.println(testDLL.toString());
    }
    
    @Test
    public void testPop()
    {
    	 DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	 
    	 assertNull("Checking that a pop from an empty list returns null", testDLL.pop());
    	 assertEquals( "Checking pop from a list containing 0 elements ", "", testDLL.toString() );
    	 //System.out.println(testDLL.toString());
    	 
    	 
    	 testDLL.push(2);
    	 int testNo = testDLL.pop();
    	 assertEquals( "Checking pop from a list containing 1 elements ", 2, testNo );
    	 assertEquals( "Checking pop from a list containing 1 elements checking the list is empty", "", testDLL.toString() );
    	 
    	 testDLL.push(3);
    	 testDLL.push(4);
    	 testDLL.push(5);
    	 
    	 testNo = testDLL.pop();
    	 assertEquals( "Checking pop from a list containing 1 elements ", 5, testNo );
    	 testNo = testDLL.pop();
    	 assertEquals( "Checking pop from a list containing 1 elements ", 4, testNo );
    	 testNo = testDLL.pop();
    	 assertEquals( "Checking pop from a list containing 1 elements ", 3, testNo );
    	 testDLL.pop();
    	 assertNull("Checking that a pop from an empty list returns null", testDLL.pop());
    }
    
    @Test
    public void testEnqueue()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
   	 testDLL.enqueue(1);
   	 assertEquals( "Checking push to a list containing 0 elements ", "1", testDLL.toString() );
   	 //System.out.println("Enqueue: " + testDLL.toString());
   	 testDLL.enqueue(2);
   	 testDLL.enqueue(3);
   	 testDLL.enqueue(4);
   	 
   	 testDLL.enqueue(5);
   	 assertEquals( "Checking push to a list containing 4 elements ", "5,4,3,2,1", testDLL.toString() );
   	 //System.out.println("Enqueue: " + testDLL.toString());
    	
    }
    
    @Test
    public void testDequeue()
    {
    	 DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	 
    	 assertNull("Checking that a pop from an empty list returns null", testDLL.dequeue());
    	 assertEquals( "Checking pop from a list containing 0 elements ", "", testDLL.toString() );
    	// System.out.println(testDLL.toString());
    	 
    	 
    	 testDLL.enqueue(2);
    	 int testNo = testDLL.dequeue();
    	 assertEquals( "Checking pop from a list containing 1 elements ", 2, testNo );
    	 assertEquals( "Checking pop from a list containing 1 elements checking the list is empty", "", testDLL.toString() );
    	 
    	 testDLL.enqueue(3);
    	 testDLL.enqueue(4);
    	 testDLL.enqueue(5);
    	 
    	 testNo = testDLL.dequeue();
    	 assertEquals( "Checking pop from a list containing 1 elements ", 3, testNo );
    	 testNo = testDLL.dequeue();
    	 assertEquals( "Checking pop from a list containing 1 elements ", 4, testNo );
    	 testNo = testDLL.dequeue();
    	 assertEquals( "Checking pop from a list containing 1 elements ", 5, testNo );
    	 testDLL.dequeue();
    	 assertNull("Checking that a pop from an empty list returns null", testDLL.dequeue());
    	 assertEquals( "Checking pop from a list containing 1 elements checking the list is empty", "", testDLL.toString() );
    }
    
    @Test
    public void testIsEmpty()
    {
    	DoublyLinkedList<String> testDLL = new DoublyLinkedList<String>();
    	
    	assertTrue( "Checking isEmpty true for a list containing 0 elements",  testDLL.isEmpty());
    	
        testDLL.push("A");
        assertFalse( "Checking isEmpty false for a list containing 1 elements",  testDLL.isEmpty());
        
        testDLL.push("B");
        testDLL.push("C");
        testDLL.push("D");
        assertFalse( "Checking isEmpty false for a list containing 4 elements",  testDLL.isEmpty());
        
        testDLL.pop();
        testDLL.pop();
        testDLL.pop();
        testDLL.pop();
        assertTrue( "Checking isEmpty true for a list containing 0 elements after being emptied",  testDLL.isEmpty());
    }
    
    @Test
    public void testDeleteAt()
    {
    	DoublyLinkedList<String> testDLL = new DoublyLinkedList<String>();
    	
    	assertFalse("Checking deleteAt for an empty list", testDLL.deleteAt(0));
    	
    	testDLL.insertBefore(0,"A");
    	assertTrue("Checking deleteAt(0) for a list containing 1 element", testDLL.deleteAt(0));

    	testDLL.insertBefore(0,"A");
    	testDLL.insertBefore(1,"B");
    	testDLL.insertBefore(2,"C");
    	testDLL.insertBefore(3,"D");
    	assertTrue(testDLL.deleteAt(3));
    	//System.out.println("Delete test: " + testDLL.toString());
    	assertEquals( "Checking deleteAt from the tail a list containing 4 elements", "A,B,C", testDLL.toString() );
    	
    	testDLL.deleteAt(0);
    	//System.out.println("Delete test: " + testDLL.toString());
    	assertEquals( "Checking deleteAt from the head a list containing 3 elements", "B,C", testDLL.toString() );
    	
    	//Refill list
    	testDLL.insertBefore(0,"A");
    	testDLL.insertBefore(3,"D");
    	
    	assertTrue(testDLL.deleteAt(2));
    	//System.out.println("Delete test: " + testDLL.toString());
    	assertEquals( "Checking deleteAt from the middle of an even sized containing 4 elements", "A,B,D", testDLL.toString() );
    	
    	testDLL.insertBefore(2,"C");
    	assertTrue(testDLL.deleteAt(1));
    	assertEquals( "Checking deleteAt iterating from the head of a list containing 4 elements", "A,C,D", testDLL.toString() );
    	
    	testDLL.insertBefore(1,"B");
    	assertTrue(testDLL.deleteAt(2));
    	assertEquals( "Checking deleteAt iterating from the tail of a list containing 4 elements", "A,B,D", testDLL.toString() );
    	
    	assertTrue(testDLL.deleteAt(1));
    	//System.out.println("Delete test: " + testDLL.toString());
    	assertEquals( "Checking deleteAt from the middle of an odd sized list containing 3 elements", "A,D", testDLL.toString() );
    	
    	//Refill list
    	testDLL.insertBefore(1,"B");
    	testDLL.insertBefore(2,"C");
    	
    	assertFalse("Checking deleteAt values greater than list size", testDLL.deleteAt(5));
    	assertFalse("Checking deleteAt values greater than list size", testDLL.deleteAt(-5));
    	
    	
    }
    
    @Test
    public void testGet()
    {
    	DoublyLinkedList<String> testDLL = new DoublyLinkedList<String>();
    	
    	assertNull(testDLL.get(4));

    	testDLL.insertBefore(0,"Zero");
    	testDLL.insertBefore(1,"One");
    	testDLL.insertBefore(2,"Two");
    	testDLL.insertBefore(3,"Three");
    	
    	assertNull("Checking get for values greater than list size", testDLL.get(4));
    	assertNull("Checking get for values lower than list size", testDLL.get(-4));
    	//System.out.println("Get test: " + testDLL.get(0));
    	assertEquals( "Checking get from the head of a list containing 4 elements", "Zero", testDLL.get(0) );
    	assertEquals( "Checking get from the tail of a list containing 4 elements", "Three", testDLL.get(3) );
    	
    	assertEquals( "Checking get iterating from the head of a list containing 4 elements", "One", testDLL.get(1) );
    	assertEquals( "Checking get iterating from the Tail of a list containing 4 elements", "Two", testDLL.get(2) );
    	
    	assertEquals( "Checking get from the middle of an even sized containing 4 elements", "Two", testDLL.get(2) );
    	testDLL.deleteAt(3);
    	//System.out.println("Get test: " + testDLL.toString());
    	assertEquals( "Checking deleteAt from the middle of an odd sized list containing 3 elements", "Two", testDLL.get(2) );
    }
    
    @Test
    public void testToString()
    {
    	DoublyLinkedList<String> testDLL = new DoublyLinkedList<String>();
    	
    	assertEquals( "Checking toString for an empty list ", "", testDLL.toString() );
    	
    	testDLL.insertBefore(0,"Zero");
    	testDLL.insertBefore(1,"One");
    	testDLL.insertBefore(2,"Two");
    	testDLL.insertBefore(3,"Three");
    	
    	assertEquals( "Checking toString for a list containing 4 elements ", "Zero,One,Two,Three", testDLL.toString() );
    }
}
