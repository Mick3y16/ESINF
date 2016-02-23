/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class FixedCostActivityTest {
    
    public FixedCostActivityTest() {
    }

    /**
     * Test of getTotalCost method and setTotalCost method, of class FixedCostActivity.
     */
    @Test
    public void testGetSetTotalCost() {
        System.out.println("getSetTotalCost");
        FixedCostActivity instance = new FixedCostActivity();
        double expResult = 0.0;
        instance.setTotalCost(expResult);
        double result = instance.getTotalCost();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of equals method, of class FixedCostActivity.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        FixedCostActivity obj = new FixedCostActivity();
        FixedCostActivity instance = new FixedCostActivity();
        instance.setActivity_ID("key");
        instance.setActivity_Type(ActivityCategory.FCA);
        instance.setDescription("description");
        instance.setDuration(0.0);
        instance.setTimeUnit(TimeCategory.Minute);
        instance.setTotalCost(0.0);
        obj.setActivity_ID("key");
        obj.setActivity_Type(ActivityCategory.FCA);
        obj.setDescription("description");
        obj.setDuration(0.0);
        obj.setTimeUnit(TimeCategory.Minute);
        obj.setTotalCost(0.0);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class FixedCostActivity.
     */
    @Test
    public void testEqualsNot() {
        System.out.println("equals");
        FixedCostActivity obj = new FixedCostActivity();
        FixedCostActivity instance = new FixedCostActivity();
        instance.setActivity_ID("key");
        instance.setActivity_Type(ActivityCategory.FCA);
        instance.setDescription("description");
        instance.setDuration(0.0);
        instance.setTimeUnit(TimeCategory.Minute);
        instance.setTotalCost(0.0);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class FixedCostActivity.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        FixedCostActivity instance = new FixedCostActivity();
        instance.setActivity_ID("key");
        instance.setActivity_Type(ActivityCategory.FCA);
        instance.setDescription("description");
        instance.setDuration(0.0);
        instance.setTimeUnit(TimeCategory.Minute);
        instance.setTotalCost(0.0);
        String expResult = "Activity: " + "Activity Key: " 
                + instance.getActivity_ID() + ", Activity Type: " 
                + instance.getActivity_Type() + ", Description: " 
                + instance.getDescription() + ", Duration " 
                + instance.getDuration() + ", Time Unit: " 
                + instance.getTimeUnit()+ ", Total Cost: " 
                + instance.getTotalCost() + ";\n";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getTypeAndCost method, of class FixedCostActivity.
     */
    @Test
    public void testGetTypeAndCost() {
        System.out.println("getTypeAndCost");
        FixedCostActivity instance = new FixedCostActivity();
        instance.setTotalCost(50);
        instance.setActivity_Type(ActivityCategory.FCA);
        String expResult = "FCA 50.0€";
        String result = instance.getTypeAndCost();
        assertEquals(expResult, result);
    } 
}
