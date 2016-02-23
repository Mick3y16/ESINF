package project;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class ActivityTest {
    
    public ActivityTest() {
    }

    /**
     * Test of getActivity_ID method and setActivity_ID method, of class Activity.
     */
    @Test
    public void testGetSetActivity_ID() {
        System.out.println("getSetActivity_ID");
        Activity instance = new FixedCostActivity();
        String expResult = "key";
        instance.setActivity_ID(expResult);
        String result = instance.getActivity_ID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getActivity_Type method and setActivity_Type method, of class Activity.
     */
    @Test
    public void testGetSetActivity_Type() {
        System.out.println("getSetActivity_Type");
        Activity instance = new FixedCostActivity();
        ActivityCategory expResult = ActivityCategory.FCA;
        instance.setActivity_Type(expResult);
        ActivityCategory result = instance.getActivity_Type();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method and setDescription method, of class Activity.
     */
    @Test
    public void testGetSetDescription() {
        System.out.println("getSetDescription");
        Activity instance = new FixedCostActivity();
        String expResult = "Description";
        instance.setDescription(expResult);
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDuration method and setDuration method, of class Activity.
     */
    @Test
    public void testGetSetDuration() {
        System.out.println("getSetDuration");
        Activity instance = new FixedCostActivity();
        double expResult = 0.0;
        instance.setDuration(expResult);
        double result = instance.getDuration();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getTimeUnit method and setTimeUnit method, of class Activity.
     */
    @Test
    public void testGetSetTimeUnit() {
        System.out.println("getSetTimeUnit");
        Activity instance = new FixedCostActivity();
        TimeCategory expResult = TimeCategory.Day;
        instance.setTimeUnit(expResult);
        TimeCategory result = instance.getTimeUnit();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Activity.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Activity obj = new FixedCostActivity();
        Activity instance = new FixedCostActivity();
        instance.setActivity_ID("key");
        instance.setActivity_Type(ActivityCategory.FCA);
        instance.setDescription("description");
        instance.setDuration(0.0);
        instance.setTimeUnit(TimeCategory.Minute);
        obj.setActivity_ID("key");
        obj.setActivity_Type(ActivityCategory.FCA);
        obj.setDescription("description");
        obj.setDuration(0.0);
        obj.setTimeUnit(TimeCategory.Minute);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Activity.
     */
    @Test
    public void testEqualsNot() {
        System.out.println("equals");
        Activity obj = new FixedCostActivity();
        Activity instance = new FixedCostActivity();
        instance.setActivity_ID("key");
        instance.setActivity_Type(ActivityCategory.FCA);
        instance.setDescription("description");
        instance.setDuration(0.0);
        instance.setTimeUnit(TimeCategory.Minute);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Activity.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Activity instance = new FixedCostActivity();
        instance.setActivity_ID("key");
        instance.setActivity_Type(ActivityCategory.FCA);
        instance.setDescription("description");
        instance.setDuration(0.0);
        instance.setTimeUnit(TimeCategory.Minute);
        String expResult = "Activity: " + "Activity Key: " 
                + instance.getActivity_ID() + ", Activity Type: " 
                + instance.getActivity_Type() + ", Description: " 
                + instance.getDescription() + ", Duration " 
                + instance.getDuration() + ", Time Unit: " 
                + instance.getTimeUnit()+ ", Total Cost: " + 0.0 + ";\n";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
