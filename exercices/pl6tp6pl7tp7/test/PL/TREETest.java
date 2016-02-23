package PL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author DEI-ESINF
 */
public class TREETest {
    Integer[] arr = {20,15,10,13,8,17,40,50,30,7};
    int[] depth= {0,1,2,3,3,2,1,2,2,4};
    Integer[] inorderT= {7,8,10,13,15,17,20,30,40,50};
    Integer[] bstAscDes = {7, 8, 10, 13, 15, 17, 20, 50, 40, 30};
    TREE<Integer> instance;
        
    public TREETest() {
    }
    
    @Before
    public void setUp(){
        instance = new TREE();
        for(int i :arr)
            instance.insert(i);        
    }

 /**
     * Test of depth method, of class TREE.
     */
    @Test
    public void testDepth() {
        System.out.println("depth");
        for(int i=0; i<arr.length; i++)
            assertEquals(instance.depth(arr[i]), depth[i]);
    }
    
    /**
     * Test of contains method, of class TREE.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        for(Integer i : arr)
            assertTrue(instance.contains(i));
        assertFalse(instance.contains(999));        
        assertFalse(instance.contains(11));        
        assertFalse(instance.contains(0));        
    }


/**
     * Test of BSTIterator method, of class TREE.
     */
    @Test
    public void testBSTIterator() {
        System.out.println("BSTIterator");
        
        //-----<test next()
        Iterator<Integer> itr = instance.iterator();
        List<Integer> lResult = new ArrayList<Integer>();        
        while(itr.hasNext())
            lResult.add(itr.next());
        assertEquals(Arrays.asList(inorderT), lResult);

        
        //-----<test remove()
        itr = instance.iterator();
        boolean errorDetected=false;
        try{
            itr.remove();
        }catch(IllegalStateException e){
            errorDetected=true;
        }
        assertTrue("cannot call remove() before call next()",errorDetected);

        itr = instance.iterator();
        errorDetected=false;
        itr.next();
        try{
            itr.remove();
        }catch(IllegalStateException e){
            errorDetected=true;
        }
        assertFalse("remove() called after next()", errorDetected);
        assertFalse("after remove() the iterator becomes invalid", itr.hasNext());

    }
/**
     * Test of parent method, of class BST.
     */
    @Test
    public void testParent() {
        System.out.println("parent");
        
        assertEquals("the parent of 20 should be null", instance.parent(20),null);
        assertEquals("the parent of 15 should be 20", instance.parent(15),new Integer(20));
        assertEquals("the parent of 50 should be 40", instance.parent(50),new Integer(40));
        assertFalse("the parent of 50 should NOT be 30", (new Integer(30)).compareTo(instance.parent(50))==0);        
    }     
    /**
     * Test of isLeaf method, of class TREE.
     */
    @Test
    public void testIsLeaf() {
        System.out.println("isLeaf");
        assertTrue(instance.isLeaf(7));
        assertTrue(instance.isLeaf(13));
        assertTrue(instance.isLeaf(17));
        assertTrue(instance.isLeaf(30));
        assertTrue(instance.isLeaf(50));
        assertFalse(instance.isLeaf(40));
        assertFalse(instance.isLeaf(20));
        assertFalse(instance.isLeaf(8));
        assertFalse(instance.isLeaf(999));
    }    
    /**
     * Test of autumnTree method, of class TREE.
     */
    @Test
    public void testAutumnTree() {
        System.out.println("autumntree");
        TREE<Integer> newTree = (TREE<Integer>) instance.autumnTree();
        
        Integer[] a = {8,10,15,20,40};
        assertEquals(Arrays.asList(a), newTree.inOrder());
        System.out.println(newTree);
    }    
    /**
     * Test of ascdes method, of class TREE.
     */
    @Test
    public void testAscdes() {
        System.out.println("ascdes");
        assertEquals(Arrays.asList(bstAscDes),instance.ascdes());
    }

    /**
     * Test of iterator method, of class TREE.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator"); 
        Iterator<Integer> itr = instance.iterator();
        int index = 0;
        while(itr.hasNext()){
            assertEquals(inorderT[index], itr.next());
            index++;
        }
    }  
    
}
