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
public class VariableCostActivityTest {
    
    public VariableCostActivityTest() {
    }

    /**
     * Test of getCostTime method and setCostTime method, of class VariableCostActivity.
     */
    @Test
    public void testGetSetCostTime() {
        System.out.println("getSetCostTime");
        VariableCostActivity instance = new VariableCostActivity();
        double expResult = 2.0;
        instance.setCostTime(expResult);
        double result = instance.getCostTime();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getTotalTime method and setTotalTime method, of class VariableCostActivity.
     */
    @Test
    public void testGetSetTotalTime() {
        System.out.println("getSetTotalTime");
        VariableCostActivity instance = new VariableCostActivity();
        double expResult = 2.0;
        instance.setTotalTime(expResult);
        double result = instance.getTotalTime();
        assertEquals(expResult, result, 2.0);
    }

    /**
     * Test of equals method, of class VariableCostActivity.
     */
    @Test
    public void testEqualsNot() {
        System.out.println("equals");
        VariableCostActivity obj = new VariableCostActivity();
        VariableCostActivity instance = new VariableCostActivity();
        instance.setActivity_ID("key");
        instance.setActivity_Type(ActivityCategory.VCA);
        instance.setDescription("description");
        instance.setDuration(0.0);
        instance.setTimeUnit(TimeCategory.Minute);
        instance.setCostTime(2.0);
        instance.setTotalTime(2.0);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class VariableCostActivity.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        VariableCostActivity obj = new VariableCostActivity();
        VariableCostActivity instance = new VariableCostActivity();
        instance.setActivity_ID("key");
        instance.setActivity_Type(ActivityCategory.VCA);
        instance.setDescription("description");
        instance.setDuration(0.0);
        instance.setTimeUnit(TimeCategory.Minute);
        instance.setCostTime(2.0);
        instance.setTotalTime(2.0);
        obj.setActivity_ID("key");
        obj.setActivity_Type(ActivityCategory.VCA);
        obj.setDescription("description");
        obj.setDuration(0.0);
        obj.setTimeUnit(TimeCategory.Minute);
        obj.setCostTime(2.0);
        obj.setTotalTime(2.0);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class VariableCostActivity.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        VariableCostActivity instance = new VariableCostActivity();
        instance.setActivity_ID("key");
        instance.setActivity_Type(ActivityCategory.VCA);
        instance.setDescription("description");
        instance.setDuration(0.0);
        instance.setTimeUnit(TimeCategory.Minute);
        instance.setCostTime(2.0);
        instance.setTotalTime(2.0);
        String expResult = "Activity: " + "Activity Key: " 
                + instance.getActivity_ID() + ", Activity Type: " 
                + instance.getActivity_Type() + ", Description: " 
                + instance.getDescription() + ", Duration " 
                + instance.getDuration() + ", Time Unit: " 
                + instance.getTimeUnit()+ ", Cost Time: " 
                + instance.getCostTime() + ", Total Time: " 
                + instance.getTotalTime() + ";\n";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTypeAndCost method, of class VariableCostActivity.
     */
    @Test
    public void testGetTypeAndCost() {
        System.out.println("getTypeAndCost");
        VariableCostActivity instance = new VariableCostActivity();
        instance.setActivity_Type(ActivityCategory.VCA);
        instance.setCostTime(5.0);
        instance.setTotalTime(10.0);
        String expResult = "VCA 50.0€";
        String result = instance.getTypeAndCost();
        assertEquals(expResult, result);
    }
}
