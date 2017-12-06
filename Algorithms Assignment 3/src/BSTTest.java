import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;



//-------------------------------------------------------------------------
/**
 *  Test class for BST
 *
 *  @version 3.1 12/11/15 19:22
 * 
 *  @author  Robert Leonard 16323797 leonarr1@tcd.ie
 */

@RunWith(JUnit4.class)
public class BSTTest
{

	@Test
	public void testHeigth() 
	{
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
	     assertEquals("Checking heigth of empty tree",
	            -1, bst.height());

	     					 //  -7
							 //   |-3
							 //   | |-1
							 //   | | |-null
		bst.put(7, 7);       //   | |  -2
		bst.put(8, 8);       //   | |   |-null 
		bst.put(3, 3);       //   | |    -null
		bst.put(1, 1);       //   |  -6
		bst.put(2, 2);       //   |   |-4 
		bst.put(6, 6);       //   |   | |-null
		bst.put(4, 4);       //   |   |  -5
		bst.put(5, 5);       //   |   |   |-null
							 //   |   |    -null
							 //   |    -null
							 //    -8
							 //     |-null
							 //      -null
		assertEquals("Checking heigth of the tree initilalised above",
	             4, bst.height());
		System.out.println("Height calculated: " + bst.height());
	
		bst = new BST<Integer, Integer>();
		bst.put(7, 7);
		System.out.println("Height calculated: " + bst.height());
		assertEquals("Checking heigth of a tree with one node",
	             0, bst.height());
	}


  /** <p>Test {@link BST#prettyPrintKeys()}.</p> */
      
 @Test
 public void testPrettyPrint() {
     BST<Integer, Integer> bst = new BST<Integer, Integer>();
     assertEquals("Checking pretty printing of empty tree",
             "-null\n", bst.prettyPrintKeys());
      
                          //  -7
                          //   |-3
                          //   | |-1
                          //   | | |-null 
     bst.put(7, 7);       //   | |  -2
     bst.put(8, 8);       //   | |   |-null
     bst.put(3, 3);       //   | |    -null
     bst.put(1, 1);       //   |  -6
     bst.put(2, 2);       //   |   |-4
     bst.put(6, 6);       //   |   | |-null
     bst.put(4, 4);       //   |   |  -5
     bst.put(5, 5);       //   |   |   |-null
                          //   |   |    -null
                          //   |    -null
                          //    -8
                          //     |-null
                          //      -null
     
     String result = 
      "-7\n" +
      " |-3\n" + 
      " | |-1\n" +
      " | | |-null\n" + 
      " | |  -2\n" +
      " | |   |-null\n" +
      " | |    -null\n" +
      " |  -6\n" +
      " |   |-4\n" +
      " |   | |-null\n" +
      " |   |  -5\n" +
      " |   |   |-null\n" +
      " |   |    -null\n" +
      " |    -null\n" +
      "  -8\n" +
      "   |-null\n" +
      "    -null\n";
     assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());
     }
 
 
     /** <p>Test {@link BST#delete(Comparable)}.</p> */
     @Test
     public void testDelete() {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         bst.delete(1);
         assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());
         
        

         bst.delete(null);
         
         bst.put(7, 7);   //        _7_
         bst.put(8, 8);   //      /     \
         bst.put(3, 3);   //    _3_      8
         bst.put(1, 1);   //  /     \ 
         bst.put(2, 2);   // 1       6
         bst.put(6, 6);   //  \     /
         bst.put(4, 4);   //   2   4
         bst.put(5, 5);   //        \
                          //         5
         System.out.println(bst.printKeysInOrder());
         
         assertEquals("Checking order of constructed tree",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
         System.out.println(bst.printKeysInOrder());
         bst.delete(9);
         assertEquals("Deleting non-existent key",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
         System.out.println(bst.printKeysInOrder());
         bst.delete(8);
         assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());
         System.out.println(bst.printKeysInOrder());
         bst.delete(6);
         assertEquals("Deleting node with single child",
                 "(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());
         System.out.println(bst.printKeysInOrder());
         bst.delete(3);
         assertEquals("Deleting node with two children",
                 "(((()1())2(()4(()5())))7())", bst.printKeysInOrder());
         System.out.println(bst.printKeysInOrder());
         System.out.println(bst.median());
         
         bst = new BST<Integer, Integer>();
         bst.put(7, 7);
         bst.delete(null);
         assertEquals("Deleting null from single element tree",
                 "(()7())", bst.printKeysInOrder());
         bst.delete(20);
         assertEquals("Deleting non existent from single element tree",
                 "(()7())", bst.printKeysInOrder());
     }
     
     @Test
     public void testMedian() 
     {
    	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	 bst.median();
         assertEquals("Testing median for an empty tree", null, bst.median()); 
         bst.put(7, 7);
         int testKey = bst.median();
         assertEquals("Testing median for a tree with one node", 7, testKey);
         
         
         				  //        _7_
         bst.put(8, 8);   //      /     \
         bst.put(3, 3);   //    _3_      8
         bst.put(1, 1);   //  /     \
         bst.put(2, 2);   // 1       6
         bst.put(6, 6);   //  \     /
         bst.put(4, 4);   //   2   4
         bst.put(5, 5);   //        \
                          //         5
         //System.out.println(bst.printKeysInOrder());
         //System.out.println(bst.median());
         testKey = bst.median();
         System.out.println(bst.printKeysInOrder()); 
         assertEquals("Finding the medain of an even tree",
                 4, testKey);
        // System.out.println(bst.median());
         
         bst.delete(4);
        // System.out.println(bst.printKeysInOrder());
         testKey = bst.median();
         assertEquals("Finding the medain of an even tree",
                 5, testKey);
     }
     
 
     @Test
     public void testMax()
     {
    	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	  
    	 bst.max();
    	 
    	 for(int i = 2; i < 50; i++ )
    	 {
    		 bst.put(i,i);
    	 }
    	 
    	 bst.max();
    	 //maxException.expect(NoSuchElementException.class);
         //maxException.expectMessage("calls max() with empty symbol table");
    	   
     }
     
 	
     
     @Test 
     public void testDeleteMax()
     {
    	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	 
    	 bst.deleteMax();
    	 
    	 for(int i = 2; i < 50; i++ )
    	 {
    		 bst.put(i,i);
    	 }
    	 
    	 bst.deleteMax();
     }
     
     
     @Test
     public void testContains()
     {
    	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	 
    	 assertFalse("Test contains() with an empty tree", bst.contains(4));
    	 
    	 bst.put(1,1);
    	 
    	 assertTrue("Test contains() with a non-empty tree ", bst.contains(1));
    	 
    	 for(int i = 2; i < 50; i++ )
    	 {
    		 bst.put(i,i);
    	 }
    	 
    	 assertTrue("Test contains() with a large tree ", bst.contains(25));
    	 
     }
     
     @Test
     public void testGet()
     {
    	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	 
    	 assertNull("Test get() with an empty tree", bst.get(4));
    	 
    	 bst.put(1,1);
    	 int testValue = bst.get(1);
    	 assertEquals("Test get() with a non-empty tree ",1 ,testValue );
    	 
    	 for(int i = 2; i <= 50; i++ )
    	 {
    		 bst.put(i,i);  
    	 }
    	 testValue = bst.get(25);
    	 assertEquals("Test get() with a large tree ",25 , testValue); 
    	 testValue = bst.get(10);
    	 assertEquals("Test get() with a large tree ",10 , testValue);
    	 testValue = bst.get(40);
    	 assertEquals("Test get() with a large tree ",40 , testValue); 
    	 testValue = bst.get(1);
    	 assertEquals("Test get() with a large tree ",1 , testValue);
    	 testValue = bst.get(50); 
    	 assertEquals("Test get() with a large tree ",50 , testValue);  
    	 
    	 for(int i = 1; i <= 50; i++ ) 
    	 {
    		 int random = (1 + (int) Math.random()*49);
    		 testValue = bst.get(random);  
    	 }
    	 
    	 bst = new BST<Integer, Integer>();
    	 
    	 bst.put(7, 7);   //        _7_
         bst.put(8, 8);   //      /     \
         bst.put(3, 3);   //    _3_      8
         bst.put(1, 1);   //  /     \
         bst.put(2, 2);   // 1       6
         bst.put(6, 6);   //  \     /
         bst.put(4, 4);   //   2   4
         bst.put(5, 5);   //        \
                          //         5
         for(int i = 1; i <= 8; i++ ) 
    	 {
    		 testValue = bst.get(i); 
    		 assertEquals("Test get() with a 2 sided tree ",i , testValue);  
    	 }
          
    	 
    	 
    	  
     }
     
     @Test
     public void testPut()
     {
    	 BST<Integer, String> bst = new BST<Integer, String>();
    	 String alpha = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    	 for(int i = 13; i < alpha.length(); i++ )
    	 { 
    		 bst.put(i,alpha.substring(i, i+1));
    	 }
    	 for(int i = 0; i < 13; i++ )
    	 {
    		 bst.put(i,alpha.substring(i, i+1));
    	 }
    	 
    	 bst.put(4, "X");
    	 
    	 bst.put(4, null);
    	 
    	// System.out.println(bst.prettyPrintKeys());

     }
}
