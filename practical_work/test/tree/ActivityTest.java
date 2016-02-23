/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class ActivityTest {

    public ActivityTest() {
    }

    /**
     * Test of getProject_ID method and setProject_ID method, of class Activity.
     */
    @Test
    public void testGetSetProject_ID() {
        System.out.println("getSetProject_ID");
        Activity instance = new Activity();
        String expResult = "P_ID";
        instance.setProject_ID(expResult);
        String result = instance.getProject_ID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getActivity_ID method and setActivity_ID method, of class
     * Activity.
     */
    @Test
    public void testGetSetActivity_ID() {
        System.out.println("getSetActivity_ID");
        Activity instance = new Activity();
        String expResult = "A_ID";
        instance.setActivity_ID(expResult);
        String result = instance.getActivity_ID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method and setType method, of class Activity.
     */
    @Test
    public void testGetSetType() {
        System.out.println("getSetType");
        Activity instance = new Activity();
        String expResult = "Type1";
        instance.setType(expResult);
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDuration method and setDuration method, of class Activity.
     */
    @Test
    public void testGetSetDuration() {
        System.out.println("getSetDuration");
        Activity instance = new Activity();
        double expResult = 20.0;
        instance.setDuration(expResult);
        double result = instance.getDuration();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getDelay method and setDelay method, of class Activity.
     */
    @Test
    public void testGetSetDelay() {
        System.out.println("getSetDelay");
        Activity instance = new Activity();
        double expResult = 5.0;
        instance.setDelay(expResult);
        double result = instance.getDelay();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of compareTo method, of class Activity.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Activity o = new Activity();
        o.setDelay(20);
        Activity instance = new Activity();
        instance.setDelay(20);
        o.setProject_ID("ID");
        o.setActivity_ID("ID");
        instance.setProject_ID("ID");
        instance.setActivity_ID("ID");
        int expResult = 0;
        int result = instance.compareTo(o);
        assertEquals("Expected Result: 0 ", expResult, result);
        o.setDelay(10);
        expResult = 1;
        result = instance.compareTo(o);
        assertEquals("Expected Result: 1 ", expResult, result);
        o.setDelay(30);
        expResult = -1;
        result = instance.compareTo(o);
        assertEquals("Expected Result: -1 ", expResult, result);
    }

    /**
     * Test of toString method, of class Activity.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Activity instance = new Activity();
        instance.setProject_ID("ID_P");
        instance.setActivity_ID("ID");
        instance.setType("type");
        instance.setDelay(20);
        instance.setDuration(20);
        String expResult = "Activity: " + "Project ID: " + instance.getProject_ID() + "; ID: "
                + instance.getActivity_ID() + "; Type: " + instance.getType() + "; Duration: "
                + instance.getDuration() + "; Delay: " + instance.getDelay() + ';';
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
